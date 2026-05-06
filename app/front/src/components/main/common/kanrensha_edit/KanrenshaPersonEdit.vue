<script setup lang="ts">
import { onMounted, ref, watch, type Ref } from 'vue';
import { KanrenshaPersonDto, type KanrenshaPersonDtoInterface } from '../../dto/kanrensha/kanrenshaPersonDto';
import RoutePathConstants from '../../../../routePathConstants';
import { GetKanrenshaPersonCapsuleDto, type GetKanrenshaPersonCapsuleDtoInterface } from '../../dto/kanrensha/getKanrenshaPersonCapsuleDto';
import { MessageConstants, MessageView, ViewInputAccess, ViewInputAddress, ViewInputPersonName, ViewInputShokugyou, type KanrenshaPersonMasterEntityInterface, type LeastUserDtoInterface } from 'seijishikin-jp-normalize_common-tool';
import getAuthorizedPromiseArea from '../../dto/login/getAuthorizedPromiseArea';
import type { GetKanrenshaPersonResultDtoInterface } from '../../dto/kanrensha/getKanrenshaPersonResultDto';
import { AccessTokenNotFoundError, TokenRefreshError } from '../../dto/login/errors';

// props,emmits
const props = defineProps<{ editEntity: KanrenshaPersonMasterEntityInterface, userDto: LeastUserDtoInterface }>();
const emits = defineEmits(["sendCancelPerson", "sendPersonInterface"]);

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

// 編集Dto
const editPersonDto: Ref<KanrenshaPersonDtoInterface> = ref(new KanrenshaPersonDto());

watch(props, () => {
    load();
});
onMounted(() => {
    load();
});

function load() {
    if (0 != props.editEntity.kanrenshaPersonMasterId) {
        const capsuleDto: GetKanrenshaPersonCapsuleDtoInterface = new GetKanrenshaPersonCapsuleDto();
        capsuleDto.userDto = props.userDto;
        capsuleDto.masterPersonEntity = props.editEntity;

        getAuthorizedPromiseArea().then(token => {
            const url = urlBack + "/user-kanrensha/get-person";
            const method = "POST";
            const body = JSON.stringify(capsuleDto);
            const headers = {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
                'X-AUTH-TOKEN': 'Bearer ' + token
            };
            fetch(url, { method, headers, body })
                .then(async (response) => {
                    const resultDto: GetKanrenshaPersonResultDtoInterface = await response.json();
                    message.value = resultDto.message;
                    // 処理が成功したら再登録できないようにアップロードファイル情報を初期化
                    if (resultDto.isFailure) {
                        infoLevel.value = MessageConstants.LEVEL_WARNING;
                        messageType.value = MessageConstants.VIEW_OK;
                    } else {
                        editPersonDto.value = resultDto.kanrenshaPersonDto;
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
}

/**国籍を確認する */
function nationarityConfirm() {

    infoLevel.value = MessageConstants.LEVEL_WARNING;
    messageType.value = MessageConstants.VIEW_OK;
    title.value = "個人国籍情報確認";
    message.value = "機能が作成されていません";

    // チェックされた対象だけに絞る
    // const list: PersonNoInterface[] = ref([]);
    // list.push(inputPersonNoDto);

    // // API接続時には不要な回答リスト初期処理
    // listInquireAnswer.value.splice(0);

    // // 外部APIに国籍情報問い合わせ
    // // TODO (現在はRelationPersonNoを送付しているが、PersonNoDtoを送付する形に変更)
    // // 国籍問い合わせInquireNationality.vueも編集
    // const url = "http://localhost:7080/inquire-nationarity";
    // const method = "POST";
    // const body = JSON.stringify(list.value);
    // const headers = {
    //     'Accept': 'application/json',
    //     'Content-Type': 'application/json'
    // };
    // fetch(url, { method, headers, body })
    //     .then(async (response) => {
    //         listInquireAnswer.value = await response.json();
    //         listInquireAnswer.value[0];
    //     })
    //     .catch((error) => { alert(error); });

}

function onCancel() {
    emits("sendCancelPerson");
}

function onSave() {
    emits("sendPersonInterface", editPersonDto.value);
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
            <input type="text" disabled="true" v-model="editPersonDto.personKanrenshaCode" class="max-input">
        </div>
    </div>

    <div class="one-line">
        <div class="left-area">
            姓名
        </div>
        <div class="right-area">
            <input type="text" v-model="editPersonDto.inputPersonNameDto.allName" disabled="true" class="max-input">
        </div>
    </div>
    <div class="one-line">
        <div class="left-area">
            住所
        </div>
        <div class="right-area">
            <input type="text" v-model="editPersonDto.inputAddressDto.addressAll" disabled="true" class="max-input">
        </div>
    </div>
    <div class="one-line">
        <div class="left-area">
            職業
        </div>
        <div class="right-area">
            <input type="text" disabled="true" v-model="editPersonDto.inputShokugyouDto.allShokugyou" class="max-input">
        </div>
    </div>

    <hr>

    <h3>収支報告書基礎</h3>

    <!-- 姓名入力 -->
    <ViewInputPersonName :edit-dto="editPersonDto.inputPersonNameDto" :is-raise-edit-view="true"></ViewInputPersonName>
    <!-- 住所入力 -->
    <ViewInputAddress :edit-dto="editPersonDto.inputAddressDto"></ViewInputAddress>
    <!-- 職業入力 -->
    <ViewInputShokugyou :edit-dto="editPersonDto.inputShokugyouDto"></ViewInputShokugyou>
    <!-- 連絡先入力 -->
    <ViewInputAccess :edit-dto="editPersonDto.inputAccessDto"></ViewInputAccess>

    <hr>

    <h3>編集内容(違反判定情報)</h3>

    <div class="one-line">
        <div class="left-area">
            国籍
        </div>
        <div class="right-area">
            <input type="checkbox" v-model="editPersonDto.isForeign">外国人である<span class="left-space">
                <button @click="nationarityConfirm">確認する(未実装)</button></span>
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
    <hr>
    -->

    <div class="footer">
        <button @click="onCancel" class="footer-button">キャンセル</button>
        <button @click="onSave" class="footer-button left-space">送信</button>
    </div>

    <!-- メッセージ表示 -->
    <div class="overMessage" v-if="messageType !== MessageConstants.VIEW_NONE">
        <MessageView :info-level="infoLevel" :message-type="messageType" :title="title" :message="message"
            @send-submit="recieveSubmit">
        </MessageView>
    </div>

</template>

<style scoped></style>
