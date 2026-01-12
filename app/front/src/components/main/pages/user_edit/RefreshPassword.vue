<script setup lang="ts">
import { ref, type Ref } from 'vue';
import router from '../../../../router';
import MockAllUserInfo from '../../../test/common/user_info/MockAllUserInfo.vue';
import RoutePathConstants from '../../../../routePathConstants';
import type { LeastUserDtoInterface } from 'seijishikin-jp-normalize_common-tool/dist/components/main/dto/user/leastUserDto';
import { getLoginUser } from '../../utils/getLoginUser';
import { MessageConstants, MessageView } from 'seijishikin-jp-normalize_common-tool';
import { RefreshPasswordCapsuleDto, type RefreshPasswordCapsuleDtoInterface } from '../../dto/user/refreshPasswordCapsuleDto';
import PasswordInput from '../../common/user/PasswordInput.vue';

// back側アクセス
const urlBack: string = RoutePathConstants.DOMAIN + RoutePathConstants.BASE_PATH;

// よく使う定数
const BLANK: string = "";
// const INIT_NUMBER: number = 0;
// const SERVER_STATUS_OK: number = 200;
// const SERVER_STATUS_ERROR: number = 400;

// ユーザ呼び出し
const userDto: Ref<LeastUserDtoInterface> = ref(getLoginUser());

// メッセージ表示定数
const infoLevel: Ref<number> = ref(MessageConstants.LEVEL_NONE);
const messageType: Ref<number> = ref(MessageConstants.VIEW_NONE);
const title: Ref<string> = ref(BLANK);
const message: Ref<string> = ref(BLANK);

// パスワード再入力
const reInputPassword: Ref<string> = ref("");

const capsuleDto: Ref<RefreshPasswordCapsuleDtoInterface> = ref(new RefreshPasswordCapsuleDto());
function onSave() {

    if (reInputPassword.value !== capsuleDto.value.newPassword) {
        // パスワードの再入力が異なる場合はメッセージを出して離脱
        infoLevel.value = MessageConstants.LEVEL_ERROR;
        title.value = "新しいパスワード入力ミス";
        message.value = "新しいパスワードと再入力の値が異なります。入力をやり直してください";
        // 表示
        messageType.value = MessageConstants.VIEW_OK;
        return;
    }

    // getAuthorizedPromiseArea().then(token => {
    //     if (token !== "") {
    //         // パスワード更新
    //         const url = urlBack + "/edit-user/refresh-password";
    //         const method = "POST";
    //         const body = JSON.stringify(capsuleDto.value);
    //         const headers = {
    //             'Accept': 'application/json',
    //             'Content-Type': 'application/json',
    //             'X-AUTH-TOKEN': 'Bearer ' + token
    //         };
    //         fetch(url, { method, headers, body })
    //             .then(async (response) => {
    //                 // 結果を受け取ってメッセージ表示
    //                 const resultDto: FrameworkResultInterface = await response.json();
    //                 alert(resultDto.message);
    //             })
    //             .catch((e) => { alert(e); });
    //     } else {
    //         alert("エラーのつもり");
    //     }
    // });

}

function onCancel() {
    router.back();
}

function recieveSubmit(button: string) {
    console.log(button);
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
    <MockAllUserInfo :user-dto="userDto"></MockAllUserInfo>

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

    <!-- メッセージ表示 -->
    <div class="overMessage" v-if="messageType !== MessageConstants.VIEW_NONE">
        <MessageView :info-level="infoLevel" :message-type="messageType" :title="title" :message="message"
            @send-submit="recieveSubmit">
        </MessageView>
    </div>

</template>
<style scoped></style>
