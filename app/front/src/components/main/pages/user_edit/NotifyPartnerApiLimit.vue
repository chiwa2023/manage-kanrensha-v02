<script setup lang="ts">
import { getErrorMessage, InputDate, MessageConstants, MessageView, type FrameworkMessageAndResultDtoInterface, type LeastUserDtoInterface } from 'seijishikin-jp-normalize_common-tool';
import router from '../../../../router';
import { ref, type Ref } from 'vue';
import { NotifyPartnerApiLimitCapsuleDto, type NotifyPartnerApiLimitCapsuleDtoInterface } from '../../dto/user/notifyPartnerApiLimitCapsuleDto';
import RoutePathConstants from '../../../../routePathConstants';
import { getLoginUser } from '../../utils/getLoginUser';
import getAuthorizedPromiseArea from '../../dto/login/getAuthorizedPromiseArea';
import { AccessTokenNotFoundError, TokenRefreshError } from '../../dto/login/errors';
import AdminInfo from '../../common/user_info/AdminInfo.vue';


// よく使う定数
const BLANK: string = "";
// const INIT_NUMBER: number = 0;
// const SERVER_STATUS_OK: number = 200;
const SERVER_STATUS_ERROR: number = 400;
// const SEARCH_LIMIT: number = 20;
// メッセージボックス表示定数
const INQUIRE_FLG: boolean = false;
const ERR_MESS_ONLY: boolean = true;
const MESS_PAGE_NAME: string = "長期トークン期限切れ通知";
const INIT_CALLER: string = "no branch";
const infoLevel: Ref<number> = ref(MessageConstants.LEVEL_NONE);
const messageType: Ref<number> = ref(MessageConstants.VIEW_NONE);
const caller: Ref<string> = ref(INIT_CALLER);
const message: Ref<string> = ref(BLANK);


// back側アクセス
const urlBack: string = RoutePathConstants.DOMAIN + RoutePathConstants.BASE_PATH;


// ユーザ呼び出し
const userDto: Ref<LeastUserDtoInterface> = ref(getLoginUser());

// 実行条件
const capsuleDto: Ref<NotifyPartnerApiLimitCapsuleDtoInterface> = ref(new NotifyPartnerApiLimitCapsuleDto());

function onCancel() {
    router.back();
}

function onSave() {

    capsuleDto.value.userDto = userDto.value;

    getAuthorizedPromiseArea().then(token => {
        const url = urlBack + "/partner-api/notify-limit";
        const method = "POST";
        const body = JSON.stringify(capsuleDto.value);
        const headers = {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'X-AUTH-TOKEN': 'Bearer ' + token
        };
        fetch(url, { method, headers, body })
            .then(async (response) => {

                const resultDtoSave: FrameworkMessageAndResultDtoInterface = await response.json();

                if (response.status > SERVER_STATUS_ERROR) {
                    message.value = getErrorMessage(resultDtoSave.message, ERR_MESS_ONLY);
                    infoLevel.value = MessageConstants.LEVEL_ERROR;
                    messageType.value = MessageConstants.VIEW_OK;
                    return;
                }

                message.value = resultDtoSave.message;
                if (resultDtoSave.isFailure) {
                    infoLevel.value = MessageConstants.LEVEL_ERROR;
                    messageType.value = MessageConstants.VIEW_OK;
                    return;
                } else {
                    infoLevel.value = MessageConstants.LEVEL_INFO;
                    messageType.value = MessageConstants.VIEW_TOAST;
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

function recieveSubmit() {
    // 非表示
    infoLevel.value = 0;
    messageType.value = 0;
}

// コンポーネントから時刻受け取り
function recieveDate(date: Date) {
    capsuleDto.value.checkDate = date;
}

</script>
<template>

    <!-- SE権限 -->
    <AdminInfo :user-dto="userDto"></AdminInfo>

    <h1>APIパートナー長期トークン期限切れ通知(強制)</h1><br>

    <h3>通知条件指定</h3>
    <div class="one-line">
        <div class="left-area">
            チェック日付
        </div>
        <div class="right-area">
            <InputDate :date="capsuleDto.checkDate" :index="0" :is-edit="true" @send-date="recieveDate"></InputDate>
        </div>
    </div>

    <div class="footer">
        <button @click="onCancel" class="footer-button">キャンセル</button>
        <button @click="onSave" class="footer-button left-space">通知処理</button>
    </div>

    <!-- メッセージ表示    -->
    <div class="overMessage" v-if="messageType !== MessageConstants.VIEW_NONE">
        <MessageView :info-level="infoLevel" :message-type="messageType" :title="MESS_PAGE_NAME" :message="message"
            :caller="caller" @send-submit="recieveSubmit">
        </MessageView>
    </div>

</template>
<style scoped></style>
