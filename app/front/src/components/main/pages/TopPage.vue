<script setup lang="ts">
import { MessageConstants, MessageView } from 'seijishikin-jp-normalize_common-tool';
import RoutePathConstants from '../../../routePathConstants';
import { ref, type Ref } from 'vue';
// import RoutePathConstants from './routePathConstants';
// import type UserPersonLeastInterface from './dto/user/userPersonLeastDto';
// import UserPersonLeastDto from './dto/user/userPersonLeastDto';
// import UserRoleConstants from './dto/user/userRoleConstants';

// const userDto: Ref<UserPersonLeastInterface> = ref(new UserPersonLeastDto());
// userDto.value.listRoles.push(UserRoleConstants.ROLE_ADMIN);
// userDto.value.listRoles.push(UserRoleConstants.ROLE_MANAGER);
// userDto.value.listRoles.push(UserRoleConstants.ROLE_COMRADE);
// userDto.value.listRoles.push(UserRoleConstants.ROLE_PARTNER_PERSON);
// userDto.value.listRoles.push(UserRoleConstants.ROLE_PARTNER_CORP);
// userDto.value.listRoles.push(UserRoleConstants.ROLE_PARTNER_POLI_ORG);
// userDto.value.userPersonCode=190;
// sessionStorage.setItem("userDto", JSON.stringify(userDto.value));

// よく使う定数
const BLANK: string = "";
// const INIT_NUMBER: number = 0;
const SERVER_STATUS_OK: number = 200;
// const SERVER_STATUS_ERROR: number = 400;

// メッセージ表示定数
const infoLevel: Ref<number> = ref(MessageConstants.LEVEL_NONE);
const messageType: Ref<number> = ref(MessageConstants.VIEW_NONE);
const title: Ref<string> = ref(BLANK);
const message: Ref<string> = ref(BLANK);

// back側疎通確認
const testResult: Ref<string> = ref(BLANK);
function onBackendTest() {
    const url = RoutePathConstants.DOMAIN + "/trial-access";
    const method = "GET";
    const headers = {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
    };
    fetch(url, { method, headers })
        .then(async (response) => {
            const status:string = await response.text();
            if (SERVER_STATUS_OK === response.status && status !== null) {
                testResult.value = status;

                infoLevel.value = MessageConstants.LEVEL_INFO;
                title.value = "back側疎通確認";
                message.value = "疎通確認できました";
                // 表示
                messageType.value = MessageConstants.VIEW_TOAST;
            } else {
                infoLevel.value = MessageConstants.LEVEL_INFO;
                title.value = "back側疎通確認";
                message.value = "疎通はしていますがレスポンスが戻っていません";
                // 表示
                messageType.value = MessageConstants.VIEW_TOAST;
            }
        })
        .catch((error) => {
            alert(error);
            // メッセージ文面は考える
            infoLevel.value = MessageConstants.LEVEL_ERROR;
            title.value = "システム担当者に連絡";
            message.value = error;
            // 表示
            messageType.value = MessageConstants.VIEW_OK;
        });
}

function recieveSubmit(button: string) {
    console.log(button); // 警告除け
    // TODO ボタンタイプ別の挙動はこの中で変える

    // 非表示
    infoLevel.value = 0;
    messageType.value = 0;
}
</script>
<template>
    <div class="container">

        <h1>政治資金関連者標準化サイト</h1>

        <h3>ログイン処理を省略・・・</h3>
        <!-- 
    <RouterLink :to=RoutePathConstants.PAGE_MENU_MANAGER>管理者メニュー</RouterLink><br>
    <RouterLink :to=RoutePathConstants.PAGE_MENU_COMRADE>APIユーザメニュー</RouterLink><br>
    <RouterLink :to=RoutePathConstants.PAGE_MENU_PARTNER>関連者メニュー</RouterLink><br>
    <hr>
    <RouterLink :to=RoutePathConstants.PAGE_MENU_ADMIN>SEメニュー</RouterLink><br>
    <hr>
    <RouterLink :to=RoutePathConstants.PAGE_DOWNLOAD_HISTORY>関連者履歴データダウンロード(公開文書記載水準)</RouterLink><br>
    <RouterLink :to=RoutePathConstants.PAGE_DOWNLOAD_MASTER_MIN>関連者マスタ最小ダウンロード(公開文書記載水準)</RouterLink><br>
    <RouterLink :to=RoutePathConstants.PAGE_DOWNLOAD_SABUN_HISTORY>関連者履歴データダウンロード差分(公開文書記載水準)</RouterLink><br>
    <RouterLink :to=RoutePathConstants.PAGE_DOWNLOAD_SABUN_MASTER_MIN>関連者マスタ最小ダウンロード差分(公開文書記載水準)</RouterLink><br>
    <hr>
    <RouterLink :to=RoutePathConstants.PAGE_COMPONENT>コンポーネント作成台紙</RouterLink><br>
    -->

        <RouterLink :to=RoutePathConstants.PAGE_RIYOUSHA_SEARCH>利用者検索</RouterLink><br>

        <hr>
        <h3>開発用</h3>

        本当に初回だけback側疎通確認<button @click="onBackendTest" class="left-space">確認</button><br>
        <textarea v-model="testResult" class="max-input"> </textarea><br>

        <RouterLink :to=RoutePathConstants.PAGE_DEVELOP_TEMPLATE>テンプレートと共通ツールカタログ</RouterLink><br>

        <a href="#">開発用台紙</a><br>

        <!-- メッセージ表示 -->
        <div class="overMessage" v-if="messageType !== MessageConstants.VIEW_NONE">
            <MessageView :info-level="infoLevel" :message-type="messageType" :title="title" :message="message"
                @send-submit="recieveSubmit">
            </MessageView>
        </div>

    </div>
</template>
<style scoped></style>
