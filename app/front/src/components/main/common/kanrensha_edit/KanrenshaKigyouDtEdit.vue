<script setup lang="ts">
import {
    MessageConstants, MessageView, type LeastUserDtoInterface, ViewInputAccess,
    ViewInputOrgName, ViewInputAddress, ViewInputKanrenshaLeast, type HoujinNoDtoInterface, SearchHoujinNo,
    HoujinSbtsConstants,
    type KanrenshaKigyouDtMasterEntityInterface,
} from 'seijishikin-jp-normalize_common-tool';
import { computed, onMounted, ref, watch, type ComputedRef, type Ref } from 'vue';
import { KanrenshaKigyouDtDto, type KanrenshaKigyouDtDtoInterface } from '../../dto/kanrensha/kanrenshaKigyouDtDto';
import getAuthorizedPromiseArea from '../../dto/login/getAuthorizedPromiseArea';
import RoutePathConstants from '../../../../routePathConstants';
import { GetKanrenshaKigyouDtCapsuleDto, type GetKanrenshaKigyouDtCapsuleDtoInterface } from '../../dto/kanrensha/getKanrenshaKigyouDtCapsuleDto';
import type { GetKanrenshaKigyouDtResultDtoInterface } from '../../dto/kanrensha/getKanrenshaKigyouDtResultDto';
import { AccessTokenNotFoundError, TokenRefreshError } from '../../dto/login/errors';

// props,emit
const props = defineProps<{ editEntity: KanrenshaKigyouDtMasterEntityInterface, userDto: LeastUserDtoInterface }>();
const emits = defineEmits(["sendCancelKigyouDt", "sendKigyouDtInterface"]);

// 編集Dto
const editKigyouDtDto: Ref<KanrenshaKigyouDtDtoInterface> = ref(new KanrenshaKigyouDtDto());

// よく使う定数
const BLANK: string = "";
// const SERVER_STATUS_OK: number = 200;
// const SERVER_STATUS_ERROR: number = 400;

// メッセージボックス表示定数
const infoLevel: Ref<number> = ref(MessageConstants.LEVEL_NONE);
const messageType: Ref<number> = ref(MessageConstants.VIEW_NONE);
const title: Ref<string> = ref(BLANK);
const message: Ref<string> = ref(BLANK);

// back側アクセス
const urlBack: string = RoutePathConstants.DOMAIN + RoutePathConstants.BASE_PATH;

watch(props, () => {
    load();
});
onMounted(() => {
    load();
});

function load() {
    const capsuleDto: GetKanrenshaKigyouDtCapsuleDtoInterface = new GetKanrenshaKigyouDtCapsuleDto();
    capsuleDto.userDto = props.userDto;
    capsuleDto.masterKigyouDtEntity = props.editEntity;

    getAuthorizedPromiseArea().then(token => {
        const url = urlBack + "/user-kanrensha/get-kigyou-dt";
        const method = "POST";
        const body = JSON.stringify(capsuleDto);
        const headers = {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'X-AUTH-TOKEN': 'Bearer ' + token
        };
        fetch(url, { method, headers, body })
            .then(async (response) => {
                const resultDto: GetKanrenshaKigyouDtResultDtoInterface = await response.json();
                message.value = resultDto.message;
                // 処理が成功したら再登録できないようにアップロードファイル情報を初期化
                if (resultDto.isFailure) {
                    infoLevel.value = MessageConstants.LEVEL_WARNING;
                    messageType.value = MessageConstants.VIEW_OK;
                } else {
                    editKigyouDtDto.value = resultDto.kanrenshaKigyouDtDto;
                }
            })
            .catch((error) => {
                alert(error);
                infoLevel.value = MessageConstants.LEVEL_ERROR;
                messageType.value = MessageConstants.VIEW_OK;
                message.value = "システム管理者にお問い合わせください";
                return;
            });
    }).catch((e) => {
        infoLevel.value = MessageConstants.LEVEL_ERROR;
        messageType.value = MessageConstants.VIEW_OK;

        if (e instanceof AccessTokenNotFoundError) {
            // トークン保持ができていない場合
            title.value = "現在トークンが存在しません";
            message.value = e.message;
            return;
        }
        if (e instanceof TokenRefreshError) {
            // 取得に失敗している場合
            title.value = "有効期限まじかのトークンを再取得できませんでした";
            message.value = e.message;
            return;
        }
        title.value = "システムエラーが発生しました";
        message.value = "システム管理者にお問い合わせください";
        return;
    });

}


// 法人検索
const isHoujinNoSearch: Ref<boolean> = ref(false);
const houjinNoName: Ref<string> = ref(BLANK);
const houjinNoAddress: Ref<string> = ref(BLANK);

const isGaikokuHoujin: ComputedRef<boolean> = computed(() =>
    HoujinSbtsConstants.GAIKOKU === editKigyouDtDto.value.houjinSbts);

function onHoujinSearch() {
    isHoujinNoSearch.value = true;
}

/**
* 法人番号受信
*/
function recieveHoujinNoInterface(selectedDto: HoujinNoDtoInterface) {
    //非表示
    isHoujinNoSearch.value = false;
    editKigyouDtDto.value.houjinNo = selectedDto.houjinNo;
    houjinNoName.value = selectedDto.houjinName;
    houjinNoAddress.value = selectedDto.addressPrefecture + selectedDto.addressCity + selectedDto.addressBlock;
    editKigyouDtDto.value.houjinSbts = selectedDto.houjinSbts;
    // 名称が未入力の場合のみ補足
    if (BLANK === editKigyouDtDto.value.inputOrgNameDto.orgName && BLANK === editKigyouDtDto.value.inputOrgNameDto.orgNameKana) {
        editKigyouDtDto.value.inputOrgNameDto.orgNameKana = selectedDto.houjinNameKana;
        editKigyouDtDto.value.inputOrgNameDto.orgName = selectedDto.houjinName;
    }
}

/**
* 法人番号キャンセル
*/
function recieveCancelHoujinNo() {
    //非表示
    isHoujinNoSearch.value = false;
}

function onCancel() {
    emits("sendCancelKigyouDt");
}

function onSave() {

    emits("sendKigyouDtInterface", editKigyouDtDto.value);
    //     // 編集か新規作成かでアクセス先を変えるだけ
    //     let url = BLANK;
    //     if (props.isEditNew) {
    //         url = urlBack + "/add-user/partner-corp";
    //     } else {
    //         url = urlBack + "/user-kanrensha/edit-corp";
    //     }

    //     getAuthorizedPromiseArea().then(token => {
    //         const capsuleDto: Ref<SaveKanrenshaCorpCapsuleInterface> = ref(new SaveKanrenshaCorpCapsuleDto());
    //         editCorpDto.value.isCombineUser = props.isCombineUser;
    //         capsuleDto.value.userPersonLeastDto = props.userDto;
    //         capsuleDto.value.kanrenshaCorpDto = editCorpDto.value;
    //         if (token !== BLANK) {
    //             // 保存処理
    //             const method = "POST";
    //             const body = JSON.stringify(capsuleDto.value);
    //             const headers = {
    //                 'Accept': 'application/json',
    //                 'Content-Type': 'application/json',
    //                 'X-AUTH-TOKEN': 'Bearer ' + token
    //             };
    //             fetch(url, { method, headers, body })
    //                 .then(async (response) => {
    //                     // 結果を受け取ってメッセージ表示
    //                     const resultDto: FrameworkResultInterface = await response.json();
    //                     alert(resultDto.message);
    //                 })
    //                 .catch((e) => { alert(e); });
    //         } else {
    //             alert("エラーのつもり");
    //         }
    //     });
}

function recieveSubmit(button: string) {
    console.log(button); // 警告除け
    infoLevel.value = 0;
    messageType.value = 0;
}
</script>
<template>
    <h3>収支報告書公開情報</h3>
    <div class="one-line">
        <div class="left-area">
            関連者コード
        </div>
        <div class="right-area">
            <input type="text" disabled="true" v-model="editKigyouDtDto.kigyouDtKanrenshaCode" class="max-input">
        </div>
    </div>
    <div class="one-line">
        <div class="left-area">
            企業／団体名称
        </div>
        <div class="right-area">
            <input type="text" v-model="editKigyouDtDto.inputOrgNameDto.orgName" disabled="true" class="max-input">
        </div>
    </div>
    <div class="one-line">
        <div class="left-area">
            全住所
        </div>
        <div class="right-area">
            <input type="text" v-model="editKigyouDtDto.inputAddressDto.addressAll" disabled="true" class="max-input">
        </div>
    </div>

    <div class="one-line">
        <div class="left-area">
            代表者
        </div>
        <div class="right-area">
            <input type="text" v-model="editKigyouDtDto.orgDelegateLeastDto.personName" disabled="true"
                class="max-input">
        </div>
    </div>

    <hr>

    <h3>編集内容(基礎)</h3>

    <div class="one-line">
        <div class="left-area">
            法人番号
        </div>
        <div class="right-area">
            <div class="form-group-vertical">
                <div>
                    <input type="text" v-model="editKigyouDtDto.houjinNo" class="code-input" disabled="true"></input>
                    <input type="text" v-model="houjinNoName" class="name-input left-space" disabled="true"></input>
                    <button class="left-space" @click="onHoujinSearch">検索</button>
                </div>
                <input type="text" v-model="houjinNoAddress" class="max-input" disabled="true"></input>
            </div>

        </div>
    </div>

    <!-- 団体名入力 -->
    <ViewInputOrgName :edit-dto="editKigyouDtDto.inputOrgNameDto"></ViewInputOrgName>

    <!-- 住所入力 -->
    <ViewInputAddress :edit-dto="editKigyouDtDto.inputAddressDto"></ViewInputAddress>

    <!-- 連絡先入力 -->
    <ViewInputAccess :edit-dto="editKigyouDtDto.inputAccessDto"></ViewInputAccess>

    <!-- 代表者入力 -->
    <ViewInputKanrenshaLeast :edit-dto="editKigyouDtDto.orgDelegateLeastDto" title="団体代表者"></ViewInputKanrenshaLeast>

    <hr>

    <h3>編集内容(違反判定情報)</h3>

    <div class="one-line">
        <div class="left-area">
            外国籍企業<br>(法人種別)
        </div>
        <div class="right-area">
            <div class="form-group-vertical">
                <span><input v-model="isGaikokuHoujin" type="checkbox" disabled="true">外国籍企業である</span>
                <span> {{ HoujinSbtsConstants.getLabel(editKigyouDtDto.houjinSbts) }}</span>
            </div>
        </div>
    </div>


    <!-- 
    <hr>
    <h3>変更履歴</h3>
    <div class="left-area">
        履歴表示
    </div>
    <div class="right-area">
        <button>展開</button>
    </div>
    <div class="clear-both"></div>
 -->

    <div class="footer">
        <button @click="onCancel" class="footer-button">キャンセル</button>
        <button @click="onSave" class="footer-button left-space">送信</button>
    </div>

    <!-- 法人番号検索 -->
    <div v-if="isHoujinNoSearch" class="overBackground"></div>
    <div v-if="isHoujinNoSearch">
        <div class="overComponent">
            <SearchHoujinNo @send-cancel-houjin-no="recieveCancelHoujinNo"
                @send-houjin-no-interface="recieveHoujinNoInterface"></SearchHoujinNo>
        </div>
    </div>

    <!-- メッセージ表示 -->
    <div class="overMessage" v-if="messageType !== MessageConstants.VIEW_NONE">
        <MessageView :info-level="infoLevel" :message-type="messageType" :title="title" :message="message"
            @send-submit="recieveSubmit">
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
}
</style>
