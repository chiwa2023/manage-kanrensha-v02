<script setup lang="ts">
import { ref, type Ref } from 'vue';
import router from '../../../../router';
import RoutePathConstants from '../../../../routePathConstants';
import type { FrameworkMessageAndResultDtoInterface, LeastUserDtoInterface } from 'seijishikin-jp-normalize_common-tool';
import { getLoginUser } from '../../utils/getLoginUser';
import { getErrorMessage, MessageConstants, MessageView } from 'seijishikin-jp-normalize_common-tool';
import { RefreshPasswordCapsuleDto, type RefreshPasswordCapsuleDtoInterface } from '../../dto/user/refreshPasswordCapsuleDto';
import PasswordInput from '../../common/user/PasswordInput.vue';
import AllUserInfo from '../../common/user_info/AllUserInfo.vue';
import getAuthorizedPromiseArea from '../../dto/login/getAuthorizedPromiseArea';
import { AccessTokenNotFoundError, TokenRefreshError } from '../../dto/login/errors';

// back側アクセス
const urlBack: string = RoutePathConstants.DOMAIN + RoutePathConstants.BASE_PATH;

// よく使う定数
const BLANK: string = "";
// const INIT_NUMBER: number = 0;
// const SERVER_STATUS_OK: number = 200;
const SERVER_ACCEPTED: number = 201;
// const SERVER_STATUS_ERROR: number = 400;
const INQUIRE_FLG: boolean = false;
const ERR_MESS_ONLY: boolean = true;
const MESS_PAGE_NAME: string = "パスワード更新";
const INIT_CALLER: string = "no branch";

// ユーザ呼び出し
const userDto: Ref<LeastUserDtoInterface> = ref(getLoginUser());

// メッセージ表示定数
const infoLevel: Ref<number> = ref(MessageConstants.LEVEL_NONE);
const messageType: Ref<number> = ref(MessageConstants.VIEW_NONE);
const caller: Ref<string> = ref(INIT_CALLER);
const message: Ref<string> = ref(BLANK);

// パスワード再入力
const reInputPassword: Ref<string> = ref("");

const capsuleDto: Ref<RefreshPasswordCapsuleDtoInterface> = ref(new RefreshPasswordCapsuleDto());
capsuleDto.value.userDto = userDto.value;

function onSave() {

    if (reInputPassword.value !== capsuleDto.value.newPassword) {
        // パスワードの再入力が異なる場合はメッセージを出して離脱
        infoLevel.value = MessageConstants.LEVEL_ERROR;
        message.value = "新しいパスワードと再入力の値が異なります。入力をやり直してください";
        // 表示
        messageType.value = MessageConstants.VIEW_OK;
        return;
    }

    getAuthorizedPromiseArea().then(token => {
        const url = urlBack + "/edit-user/refresh-password";
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
                messageType.value = MessageConstants.VIEW_OK;
                message.value = resultDto.message;
                if (SERVER_ACCEPTED == response.status) {
                    infoLevel.value = MessageConstants.LEVEL_ERROR;
                } else {
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

function onCancel() {
    router.back();
}

function recieveSubmit() {
    infoLevel.value = 0;
    messageType.value = 0;
}

function recievePasswordOld(password: string) {
    capsuleDto.value.oldPassword = password;
}
function recievePasswordNew(password: string) {
    capsuleDto.value.newPassword = password;
}
function recievePasswordReInput(password: string) {
    reInputPassword.value = password;
}

</script>
<template>

    <!-- ユーザメニュー兼チェック -->
    <AllUserInfo :user-dto="userDto"></AllUserInfo>

    <h1>パスワード変更</h1>

    <div class="one-line">
        変更するパスワードを入力してください
    </div>

    <div class="one-line">
        <div class="left-area">
            現在のパスワード
        </div>
        <div class="right-area">
            <PasswordInput :password="capsuleDto.oldPassword" @send-password="recievePasswordOld"></PasswordInput>
        </div>
    </div>

    <div class="one-line">
        <div class="left-area">
            新しいパスワード
        </div>
        <div class="right-area">
            <PasswordInput :password="capsuleDto.newPassword" @send-password="recievePasswordNew"></PasswordInput>
        </div>
    </div>

    <div class="one-line">
        <div class="left-area">
            パスワードの再入力
        </div>
        <div class="right-area">
            <PasswordInput :password="reInputPassword" @send-password="recievePasswordReInput"></PasswordInput>
        </div>
    </div>

    <div class="footer">
        <button @click="onCancel" class="footer-button">キャンセル</button>
        <button @click="onSave" class="footer-button left-space">保存</button>
    </div>

    <!-- メッセージ表示    -->
    <div class="overMessage" v-if="messageType !== MessageConstants.VIEW_NONE">
        <MessageView :info-level="infoLevel" :message-type="messageType" :title="MESS_PAGE_NAME" :message="message"
            :caller="caller" @send-submit="recieveSubmit">
        </MessageView>
    </div>

</template>
<style scoped></style>
