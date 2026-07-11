<script setup lang="ts">
import { ref, type Ref } from 'vue';
import RoutePathConstants from '../../../../routePathConstants';
import { getLoginUser } from '../../utils/getLoginUser';
import type { LeastUserDtoInterface } from 'seijishikin-jp-normalize_common-tool';
import router from '../../../../router';
import { getErrorMessage, MessageConstants, MessageView } from 'seijishikin-jp-normalize_common-tool';
import { DeleteUserCapsuleDto, type DeleteUserCapsuleDtoInterface } from '../../dto/user/deleteUserCapsuleDto';
import AllUserInfo from '../../common/user_info/AllUserInfo.vue';
import getAuthorizedPromiseArea from '../../dto/login/getAuthorizedPromiseArea';
import { AccessTokenNotFoundError, TokenRefreshError } from '../../dto/login/errors';

// back側アクセス
const urlBack: string = RoutePathConstants.DOMAIN + RoutePathConstants.BASE_PATH;

// よく使う定数
const BLANK: string = "";
// const INIT_NUMBER: number = 0;
const SERVER_STATUS_ACCEPTED: number = 201;
const INQUIRE_FLG: boolean = false;
const ERR_MESS_ONLY: boolean = true;
const MESS_PAGE_NAME: string = "退会処理";
const INIT_CALLER: string = "no branch";

// ユーザ呼び出し
const userDto: Ref<LeastUserDtoInterface> = ref(getLoginUser());

// メッセージ表示定数
const infoLevel: Ref<number> = ref(MessageConstants.LEVEL_NONE);
const messageType: Ref<number> = ref(MessageConstants.VIEW_NONE);
const caller: Ref<string> = ref(INIT_CALLER);
const message: Ref<string> = ref(BLANK);

// 送信内容Dto
const capsuleDto: Ref<DeleteUserCapsuleDtoInterface> = ref(new DeleteUserCapsuleDto());
capsuleDto.value.userDto = userDto.value;

const attentionText: string = "退会確認";
function onWithdraw() {

    // 自分自身の退会をするので退会者情報に本人ユーザ最小限を登録
    capsuleDto.value.userDto = userDto.value;

    // 退会アラート
    infoLevel.value = MessageConstants.LEVEL_WARNING;
    message.value = "このまま退会処理をします。ログインして情報編集または関連者登録APIの利用ができなくなりますがよろしいですか?";
    caller.value = attentionText;
    // 表示
    messageType.value = MessageConstants.VIEW_YES_NO;
}

function onCancel() {
    router.back();
}

const withdrawText: string = "退会完了";
function recieveSubmit(button: string, callerMethod: string) {

    // 退会処理完了後トーストを出して自動ログアウト
    if (withdrawText === callerMethod) {
        infoLevel.value = 0;
        messageType.value = 0;
        router.push(RoutePathConstants.PAGE_LOGOUT);
    }

    // 退会に関する注意喚起後Yesで退会処理
    if (button === MessageConstants.BUTTON_YES && callerMethod === attentionText) {
        doWithrraw();
    }

    infoLevel.value = 0;
    messageType.value = 0;
    caller.value = INIT_CALLER;
}


function doWithrraw() {
    // 処理実行
    getAuthorizedPromiseArea().then(token => {
        const url = urlBack + "/edit-user/delete";
        const method = "POST";
        const body = JSON.stringify(capsuleDto.value);
        const headers = {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'X-AUTH-TOKEN': 'Bearer ' + token
        };
        fetch(url, { method, headers, body })
            .then(async (response) => {
                const resultDto = await response.json();
                message.value = resultDto.message;
                if (SERVER_STATUS_ACCEPTED == response.status) {
                    infoLevel.value = MessageConstants.LEVEL_ERROR;
                    messageType.value = MessageConstants.VIEW_OK;
                } else {
                    caller.value = withdrawText;
                    messageType.value = MessageConstants.VIEW_TOAST;
                    infoLevel.value = MessageConstants.LEVEL_INFO;
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


</script>
<template>
    <!-- ユーザメニュー兼チェック -->
    <AllUserInfo :user-dto="userDto"></AllUserInfo>

    <h1>退会処理</h1>

    <div class="one-line">
        <div class="left-area">
            退会理由
        </div>
        <div class="right-area">
            <textarea type="text" v-model="capsuleDto.withdrawReason"></textarea>
        </div>
    </div>

    <div class="one-line">

        <div class="left-area">
            退会
        </div>
        <div class="right-area">
            <button @click="onWithdraw">退会する</button>
        </div>
    </div>

    <div class="footer">
        <button @click="onCancel" class="footer-button">キャンセル</button>
    </div>

    <!-- メッセージ表示    -->
    <div class="overMessage" v-if="messageType !== MessageConstants.VIEW_NONE">
        <MessageView :info-level="infoLevel" :message-type="messageType" :title="MESS_PAGE_NAME" :message="message"
            :caller="caller" @send-submit="recieveSubmit">
        </MessageView>
    </div>

</template>
<style scoped></style>
