<script setup lang="ts">
import { getErrorMessage, InputLgcode, MessageConstants, MessageView, type FrameworkMessageAndResultDtoInterface, type LeastUserDtoInterface } from 'seijishikin-jp-normalize_common-tool';
import { ref, type Ref } from 'vue';
import { getLoginUser } from '../../utils/getLoginUser';
import type { StorageFileDtoInterface } from '../../dto/storage_file/storageFileDto';
import ManagerInfo from '../../common/user_info/ManagerInfo.vue';
import ReadCsv from '../../common/read_csv/ReadCsv.vue';
import { SabunPreparAddressRsdtCapsuleDto, type SabunPreparAddressRsdtCapsuleDtoInterface } from '../../dto/address_registory/sabunPreparAddressRsdtCapsuleDto.ts';
import RoutePathConstants from '../../../../routePathConstants.ts';
import getAuthorizedPromiseArea from '../../dto/login/getAuthorizedPromiseArea.ts';
import { AccessTokenNotFoundError, TokenRefreshError } from '../../dto/login/errors.ts';
import type { SelectOptionStringDtoInterface } from '../../dto/select_options/selectOptionStringDto.ts';

// よく使う定数
const BLANK: string = "";
// const SERVER_STATUS_OK: number = 200;
// const SERVER_STATUS_ERROR: number = 400;
const INQUIRE_FLG: boolean = false;
const ERR_MESS_ONLY: boolean = true;
const MESS_PAGE_NAME: string = "アドレス・ベース・レジストリ差分ファイル書き出し";
const INIT_CALLER: string = "no branch";

// メッセージボックス表示定数
const infoLevel: Ref<number> = ref(MessageConstants.LEVEL_NONE);
const messageType: Ref<number> = ref(MessageConstants.VIEW_NONE);
const caller: Ref<string> = ref(INIT_CALLER);
const message: Ref<string> = ref(BLANK);

// back側アクセス
const urlBack: string = RoutePathConstants.DOMAIN + RoutePathConstants.BASE_PATH;

// ユーザ呼び出し
const userDto: Ref<LeastUserDtoInterface> = ref(getLoginUser());

const capsuleDto: Ref<SabunPreparAddressRsdtCapsuleDtoInterface> = ref(new SabunPreparAddressRsdtCapsuleDto());

// 地番差分ファイルを受け取り
function recieveStorageFileParcel(storageFileDto: StorageFileDtoInterface) {
    capsuleDto.value.parcelFileDto = storageFileDto;
}

// 住居差分ファイルを受け取り
function recieveStorageFileRsdt(storageFileDto: StorageFileDtoInterface) {
    capsuleDto.value.rsdtFileDto = storageFileDto;
}

function onCancel() {
    history.back();
}
function onSave() {
    capsuleDto.value.userDto = userDto.value;

    getAuthorizedPromiseArea().then(token => {
        const url = urlBack + "/wktbl-address-rsdt/prepare";
        const method = "POST";
        const body = JSON.stringify(capsuleDto.value);
        const headers = {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'X-AUTH-TOKEN': 'Bearer ' + token
        };
        fetch(url, { method, headers, body })
            .then(async (response) => {
                const resultDto: FrameworkMessageAndResultDtoInterface = await response.json();
                message.value = resultDto.message;
                if (resultDto.isFailure) {
                    infoLevel.value = MessageConstants.LEVEL_WARNING;
                    messageType.value = MessageConstants.VIEW_OK;
                } else {
                    // 処理が成功したら再登録できないようにアップロードファイル情報を初期化
                    capsuleDto.value = new SabunPreparAddressRsdtCapsuleDto();
                    infoLevel.value = MessageConstants.LEVEL_INFO;
                    messageType.value = MessageConstants.VIEW_TOAST;
                }
            })
            .catch((error) => {
                message.value = getErrorMessage(error, ERR_MESS_ONLY);
                infoLevel.value = MessageConstants.LEVEL_ERROR;
                messageType.value = MessageConstants.VIEW_OK;
                return;
            });
    }).catch((e) => {
        infoLevel.value = MessageConstants.LEVEL_ERROR;
        messageType.value = MessageConstants.VIEW_OK;

        // トークン保持または取得に失敗している場合
        if (e instanceof AccessTokenNotFoundError || e instanceof TokenRefreshError) {
            message.value = e.message;
            return;
        }

        message.value = getErrorMessage(e, INQUIRE_FLG);
        return;
    });
}

function recieveSubmit() {
    infoLevel.value = 0;
    messageType.value = 0;
}

// 地方自治体コードを受信
function recieveLgCode(optionDto: SelectOptionStringDtoInterface) {
    capsuleDto.value.lgCode = optionDto.value;
}

</script>
<template>
    <!-- 管理者メニュー兼チェック -->
    <ManagerInfo :user-dto="userDto"></ManagerInfo>

    <h1>アドレスベースレジストリ差分修正</h1><br>

    <!-- 検索条件 -->
    <div class="one-line">
        <div class="left-area">
            地方自治体コード
        </div>
        <div class="right-area">
            <InputLgcode :is-digit5="false" :lg-code="capsuleDto.lgCode" @send-lg-code="recieveLgCode">
            </InputLgcode>
        </div>
    </div>

    <!-- 地番ファイル用 -->
    <h3 class="accent-h3">番地ファイル(parcel)選択</h3>
    <ReadCsv @send-storage-file-interface="recieveStorageFileParcel" :user-dto="userDto"></ReadCsv>

    <hr>

    <!-- 住居ファイル用 -->
    <h3>住居ファイル(rsdt)選択</h3>
    <ReadCsv @send-storage-file-interface="recieveStorageFileRsdt" :user-dto="userDto"></ReadCsv>

    <div class="footer">
        <button @click="onCancel" class="footer-button">キャンセル</button>
        <button @click="onSave" class="footer-button left-space">送信</button>
    </div>

    <!-- メッセージ表示    -->
    <div class="overMessage" v-if="messageType !== MessageConstants.VIEW_NONE">
        <MessageView :info-level="infoLevel" :message-type="messageType" :title="MESS_PAGE_NAME" :message="message"
            :caller="caller" @send-submit="recieveSubmit">
        </MessageView>
    </div>

</template>
<style scoped>
table {
    border-style: solid;
    border-width: 1px;
}

td {
    border-style: solid;
    border-width: 1px;
}

th {
    border-style: solid;
    border-width: 1px;
    text-align: center;
}
</style>
