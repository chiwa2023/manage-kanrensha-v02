<script setup lang="ts">
import { onMounted, ref, watch, type Ref } from 'vue';
import { MessageConstants, MessageView, ViewInputAccess, ViewInputAddress, ViewInputPersonName } from 'seijishikin-jp-normalize_common-tool';
import { RiyoushaManagerDto, type RiyoushaManagerDtoInterface } from '../../dto/riyousha/riyoushaManagerDto';
import type { RiyoushaManagerMasterEntityInterface } from '../../entity/riyoushaManagerMasterEntity';
import getAuthorizedPromiseArea from '../../dto/login/getAuthorizedPromiseArea';
import RoutePathConstants from '../../../../routePathConstants';
import { AccessTokenNotFoundError, TokenRefreshError } from '../../dto/login/errors';

// props,emit
const props = defineProps<{ editEntity: RiyoushaManagerMasterEntityInterface }>();
const emits = defineEmits(["sendCancelManager", "sendManagerInterface"]);

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


const inputManagerDto: Ref<RiyoushaManagerDtoInterface> = ref(new RiyoushaManagerDto());

onMounted(() => {
    // リストからの変更
    onChangeEntity();
});

watch(props, () => {
    // リストからの変更
    onChangeEntity();
});


function onChangeEntity() {
    // 編集しないときはgetしない    
    if (props.editEntity.riyoushaManagerId !== 0) {

        getAuthorizedPromiseArea().then(token => {
            const url = urlBack + "/riyousha/get-manager";
            const method = "POST";
            const body = JSON.stringify(props.editEntity);
            const headers = {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
                'X-AUTH-TOKEN': 'Bearer ' + token
            };
            fetch(url, { method, headers, body })
                .then(async (response) => {
                    inputManagerDto.value = await response.json();
                    message.value = inputManagerDto.value.message;
                    if (inputManagerDto.value.isFailure) {
                        infoLevel.value = MessageConstants.LEVEL_WARNING;
                        messageType.value = MessageConstants.VIEW_OK;
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

function onCancel() {
    emits("sendCancelManager");
}

function onSave() {
    emits("sendManagerInterface", inputManagerDto.value);
}


function recieveSubmit(button: string) {
    console.log(button); // 警告除け
    infoLevel.value = 0;
    messageType.value = 0;
}
</script>
<template>

    <h3 class="accent-h3">情報入力</h3><br>

    <div class="one-line">
        <div class="left-area">
            関連者コード
        </div>
        <div class="right-area">
            <input type="text" disabled="true" v-model="inputManagerDto.riyoushaManagerMasterCode"
                class="left-space code-input">
        </div>
    </div>

    <!-- 個人姓名入力-->
    <ViewInputPersonName :edit-dto="inputManagerDto.inputPersonNameDto"></ViewInputPersonName>

    <!-- 住所入力 -->
    <ViewInputAddress :edit-dto="inputManagerDto.inputAddressDto"></ViewInputAddress>

    <!-- 連絡先入力 -->
    <ViewInputAccess :edit-dto="inputManagerDto.inputAccessDto"></ViewInputAccess>

    <div class="footer">
        <button class="footer-button" @click="onCancel">キャンセル</button>
        <button class="footer-button left-space" @click="onSave">送信</button>
    </div>

    <!-- メッセージ表示 -->
    <div class="overMessage" v-if="messageType !== MessageConstants.VIEW_NONE">
        <MessageView :info-level="infoLevel" :message-type="messageType" :title="title" :message="message"
            @send-submit="recieveSubmit">
        </MessageView>
    </div>

</template>
<style scoped></style>
