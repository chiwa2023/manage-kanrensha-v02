<script setup lang="ts">
import { computed, ref, watch, type ComputedRef, type Ref } from 'vue';
import UploadFile from '../../common/file/UploadFile.vue';
import { getErrorMessage, MessageConstants, MessageView, SearchKanrenshaKigyouDt, SearchKanrenshaPerson, SearchKanrenshaSeijidantai, type FrameworkMessageAndResultDtoInterface, type KanrenshaKigyouDtMasterEntityInterface, type KanrenshaPersonMasterEntityInterface, type KanrenshaSeijidantaiMasterEntityInterface, type LeastUserDtoInterface } from 'seijishikin-jp-normalize_common-tool';
import { getLoginUser } from '../../utils/getLoginUser.ts';
import ManagerInfo from '../../common/user_info/ManagerInfo.vue';
import type { StorageFileDtoInterface } from '../../dto/storage_file/storageFileDto.ts';
import { MoveKanrenshaCodePromoteCapsuleDto, type MoveKanrenshaCodePromoteCapsuleDtoInterface } from '../../dto/kanrensha/moveKanrenshaCodePromoteCapsuleDto.ts';
import getAuthorizedPromiseArea from '../../dto/login/getAuthorizedPromiseArea.ts';
import RoutePathConstants from '../../../../routePathConstants.ts';
import { AccessTokenNotFoundError, TokenRefreshError } from '../../dto/login/errors.ts';


// よく使う定数
const BLANK: string = "";
// const INIT_NUMBER: number = 0;
const INIT_BOOLEAN: boolean = false;
// const SERVER_STATUS_OK: number = 200;
const SERVER_STATUS_ERROR: number = 400;
// const SEARCH_LIMIT: number = 20;
// メッセージボックス表示定数
const INQUIRE_FLG: boolean = false;
const ERR_MESS_ONLY: boolean = true;
const MESS_PAGE_NAME: string = "個人－政治団体履歴一括処理再処理";
const INIT_CALLER: string = "no branch";
const infoLevel: Ref<number> = ref(MessageConstants.LEVEL_NONE);
const messageType: Ref<number> = ref(MessageConstants.VIEW_NONE);
const caller: Ref<string> = ref(INIT_CALLER);
const message: Ref<string> = ref(BLANK);

// back側アクセス
const urlBack: string = RoutePathConstants.DOMAIN + RoutePathConstants.BASE_PATH;

// ユーザ呼び出し
const userDto: Ref<LeastUserDtoInterface> = ref(getLoginUser());

const viewStatus: Ref<number> = ref(1); // とりあえず個人スタート
const isSearch: Ref<boolean> = ref(INIT_BOOLEAN);
const isSetAbolish: Ref<boolean> = ref(INIT_BOOLEAN);

const capsuleDto: Ref<MoveKanrenshaCodePromoteCapsuleDtoInterface> = ref(new MoveKanrenshaCodePromoteCapsuleDto());

// ファイル保全情報受信
function recieveStorageFileInterface(storageFileDto: StorageFileDtoInterface) {
    capsuleDto.value.storageFileDto = storageFileDto;
}

// 検索コンポーネント制御
const isSearchKigyouDt: ComputedRef<boolean> = computed(() => {
    return isSearch.value && viewStatus.value == 2;
});

const isSearchPerson: ComputedRef<boolean> = computed(() => {
    return isSearch.value && viewStatus.value == 1;
});

const isSearchSeijidantai: ComputedRef<boolean> = computed(() => {
    return isSearch.value && viewStatus.value == 3;
});

watch(viewStatus, () => {
    capsuleDto.value.originKanrenshaCode = BLANK;
    capsuleDto.value.originName = BLANK;
    capsuleDto.value.abolishKanrenshaCode = BLANK;
    capsuleDto.value.abolishKanrenshaName = BLANK;
    capsuleDto.value.isAbolishLast = INIT_BOOLEAN;
});

function onCancel() {
    history.back();
}

function onSave() {

    capsuleDto.value.userDto = userDto.value;
    capsuleDto.value.kanrenshaKbn = viewStatus.value;

    if ("" === capsuleDto.value.storageFileDto.fileName) {
        infoLevel.value = MessageConstants.LEVEL_ERROR;
        messageType.value = MessageConstants.VIEW_OK;
        message.value = "書証が提出されません。個人を証明する画像情報等を追加してください";
        return;
    }

    getAuthorizedPromiseArea().then(token => {
        const url = urlBack + "/kanrensha-code-move/promote";
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

                if (response.status > SERVER_STATUS_ERROR) {
                    message.value = getErrorMessage(resultDto.message, ERR_MESS_ONLY);
                    infoLevel.value = MessageConstants.LEVEL_ERROR;
                    messageType.value = MessageConstants.VIEW_OK;
                    return;
                }

                if (resultDto.isFailure) {
                    infoLevel.value = MessageConstants.LEVEL_ERROR;
                    messageType.value = MessageConstants.VIEW_OK;
                    message.value = resultDto.message;
                    return;
                } else {
                    infoLevel.value = MessageConstants.LEVEL_INFO;
                    messageType.value = MessageConstants.VIEW_TOAST;
                    message.value = resultDto.message;
                    return;
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


function onSearchOrgin() {
    isSetAbolish.value = false;

    // 検索コンポーネントを開く
    isSearch.value = true;
}

function onSearchAbolish() {
    isSetAbolish.value = true;
    // 検索コンポーネントを開く
    isSearch.value = true;
}


function recievePersonInterface(editDto: KanrenshaPersonMasterEntityInterface) {
    if (isSetAbolish.value) {
        capsuleDto.value.abolishKanrenshaCode = editDto.personKanrenshaCode;
        capsuleDto.value.abolishKanrenshaName = editDto.kanrenshaName;
    } else {
        capsuleDto.value.originKanrenshaCode = editDto.personKanrenshaCode;
        capsuleDto.value.originName = editDto.kanrenshaName;
    }

    // 閉じる
    isSearch.value = false;
}

function recieveKigyouDtInterface(editDto: KanrenshaKigyouDtMasterEntityInterface) {
    if (isSetAbolish.value) {
        capsuleDto.value.abolishKanrenshaCode = editDto.kigyouDtKanrenshaCode;
        capsuleDto.value.abolishKanrenshaName = editDto.kanrenshaName;
    } else {
        capsuleDto.value.originKanrenshaCode = editDto.kigyouDtKanrenshaCode;
        capsuleDto.value.originName = editDto.kanrenshaName;
    }

    // 閉じる
    isSearch.value = false;
}

function recieveSeijidantaiInterface(editDto: KanrenshaSeijidantaiMasterEntityInterface) {

    if (isSetAbolish.value) {
        capsuleDto.value.abolishKanrenshaCode = editDto.seijidantaiKanrenshaCode;
        capsuleDto.value.abolishKanrenshaName = editDto.kanrenshaName;
    } else {
        capsuleDto.value.originKanrenshaCode = editDto.seijidantaiKanrenshaCode;
        capsuleDto.value.originName = editDto.kanrenshaName;
    }

    // 閉じる
    isSearch.value = false;
}



function recieveCancelPerson() {
    // 閉じる
    isSearch.value = false;
}

function recieveCancelKigyouDt() {
    // 閉じる
    isSearch.value = false;
}


function recieveCancelSeijidantai() {
    // 閉じる
    isSearch.value = false;
}

function recieveSubmit() {
    infoLevel.value = 0;
    messageType.value = 0;
}
</script>
<template>

    <!-- 管理者メニュー兼チェック -->
    <ManagerInfo :user-dto="userDto"></ManagerInfo>

    <h1>関連者コード移動申請(運営者)</h1><br>

    <h3>関連者コード移動申請</h3>

    <div class="one-line">
        <div class="left-area">
            関連者区分
        </div>
        <div class="right-area">
            <span><input type="radio" id="editSelect" v-model="viewStatus" value="1">1.個人</span>
            <span class="left-space"><input type="radio" id="editSelect" v-model="viewStatus" value="2">2.企業／団体</span>
            <span class="left-space"><input type="radio" id="editSelect" v-model="viewStatus" value="3">3.政治団体</span>
        </div>
    </div>

    <h3>コード移動が必要なことを証明する書証</h3>
    <UploadFile :user-dto="userDto" @send-storage-file-interface="recieveStorageFileInterface"></UploadFile>


    <h3>移動コードの指定</h3>

    <div class="one-line">
        <div class="left-area">
            移動元(廃止)
        </div>
        <div class="right-area">
            <input type="text" class="code-input" v-model="capsuleDto.abolishKanrenshaCode" disabled="true">
            <span class="left-space">名称：<input type="text" v-model="capsuleDto.abolishKanrenshaName" class="name-input"
                    disabled="true"></span>
            <span class="left-space"><button @click="onSearchAbolish">検索</button></span>
        </div>
    </div>

    <div class="one-line">
        <div class="left-area">
            移動先(残すコード)
        </div>
        <div class="right-area">
            <input type="text" class="code-input" v-model="capsuleDto.originKanrenshaCode" disabled="true">
            <span class="left-space">名称：<input type="text" v-model="capsuleDto.originName" class="name-input"
                    disabled="true"></span>
            <span class="left-space"><button @click="onSearchOrgin">検索</button></span>
        </div>
    </div>

    <div class="one-line">
        <div class="left-area">
            移動理由
        </div>
        <div class="right-area">
            <textarea class="max-input" v-model="capsuleDto.moveReason"></textarea>
        </div>
    </div>

    <div class="one-line">
        <div class="left-area">
            最新データ選択
        </div>
        <div class="right-area">
            <input type="checkbox" v-model="capsuleDto.isAbolishLast"> 廃止データを最新にする
        </div>
    </div>



    <div class="footer">
        <button @click="onCancel" class="footer-button">キャンセル</button>
        <button @click="onSave" class="footer-button left-space">送信</button>
    </div>



    <!-- 個人検索 -->
    <div v-if="isSearchPerson" class="overBackground"></div>
    <div v-if="isSearchPerson">
        <div class="overComponent">
            <SearchKanrenshaPerson v-if="isSearchPerson" @send-person-interface="recievePersonInterface"
                :is-raise-commponet="true" @send-cancel-person="recieveCancelPerson">
            </SearchKanrenshaPerson>
        </div>
    </div>

    <!-- 企業団体検索 -->
    <div v-if="isSearchKigyouDt" class="overBackground"></div>
    <div v-if="isSearchKigyouDt">
        <div class="overComponent">
            <SearchKanrenshaKigyouDt v-if="isSearchKigyouDt" @send-kigyou-dt-interface="recieveKigyouDtInterface"
                :is-raise-commponet="true" @send-cancel-kigyou-dt="recieveCancelKigyouDt">
            </SearchKanrenshaKigyouDt>
        </div>
    </div>

    <!-- 政治団体検索 -->
    <div v-if="isSearchSeijidantai" class="overBackground"></div>
    <div v-if="isSearchSeijidantai">
        <div class="overComponent">
            <SearchKanrenshaSeijidantai v-if="isSearchSeijidantai"
                @send-seijidantai-interface="recieveSeijidantaiInterface" :is-raise-commponet="true"
                @send-cancel-seijidantai="recieveCancelSeijidantai">
            </SearchKanrenshaSeijidantai>
        </div>
    </div>

    <!-- メッセージ表示    -->
    <div class="overMessage" v-if="messageType !== MessageConstants.VIEW_NONE">
        <MessageView :info-level="infoLevel" :message-type="messageType" :title="MESS_PAGE_NAME" :message="message"
            :caller="caller" @send-submit="recieveSubmit">
        </MessageView>
    </div>

</template>
<style scoped></style>
