<script setup lang="ts">
import { ref, type Ref } from 'vue';
import RoutePathConstants from '../../../../routePathConstants';
import { getLoginUser } from '../../utils/getLoginUser';
import type { LeastUserDtoInterface } from 'seijishikin-jp-normalize_common-tool/dist/components/main/dto/user/leastUserDto';
import router from '../../../../router';
import MockAllUserInfo from '../../../test/common/user_info/MockAllUserInfo.vue';
import { MessageConstants, MessageView } from 'seijishikin-jp-normalize_common-tool';
import { DeleteUserCapsuleDto, type DeleteUserCapsuleDtoInterface } from '../../dto/user/deleteUserCapsuleDto';

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

// 送信内容Dto
const capsuleDto: Ref<DeleteUserCapsuleDtoInterface> = ref(new DeleteUserCapsuleDto());

function onWithdraw() {

    // 自分自身の退会をするので退会者情報に本人ユーザ最小限を登録
    capsuleDto.value.userDto = userDto.value;

    // 退会アラート
    infoLevel.value = MessageConstants.LEVEL_WARNING;
    title.value = "退会処理";
    message.value = "このまま退会処理をします。ログインして情報編集または関連者登録APIの利用ができなくなりますがよろしいですか?";
    // 表示
    messageType.value = MessageConstants.VIEW_YES_NO;

    // getAuthorizedPromiseArea().then(token => {
    //     if (token !== "") {
    //         const capsuleDto: Ref<FrameworkCapsuleInterface> = ref(new FrameworkCapsuleDto());
    //         capsuleDto.value.userPersonLeastDto = userLeastDto.value;
    //         // TODO capsuleDtoを新設しUserDtoを削除対象、操作者両方に指定する処理
    //         const url = urlBack + "/user/delete";
    //         const method = "POST";
    //         const body = JSON.stringify(capsuleDto);
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
    // 退会に関するYes,NO処理
    if(button === "yes"){
        alert("退会継続");
    }
    infoLevel.value = 0;
    messageType.value = 0;
}
</script>
<template>
    <!-- ユーザメニュー兼チェック -->
    <MockAllUserInfo :user-dto="userDto"></MockAllUserInfo>

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

    <!-- メッセージ表示 -->
    <div class="overMessage" v-if="messageType !== MessageConstants.VIEW_NONE">
        <MessageView :info-level="infoLevel" :message-type="messageType" :title="title" :message="message"
            @send-submit="recieveSubmit">
        </MessageView>
    </div>

</template>
<style scoped></style>
