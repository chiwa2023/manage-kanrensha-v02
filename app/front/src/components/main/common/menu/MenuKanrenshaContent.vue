<script setup lang="ts">
import RoutePathConstants from '../../../../routePathConstants';

//仮
// よく使う定数
// const BLANK: string = "";
// const INIT_NUMBER: number = 0;
// const SERVER_STATUS_OK: number = 200;
// const SERVER_STATUS_ERROR: number = 400;
// メッセージボックス表示定数
//const infoLevel: Ref<number> = ref(MessageConstants.LEVEL_NONE);
//const messageType: Ref<number> = ref(MessageConstants.VIEW_NONE);
//const title: Ref<string> = ref(BLANK);
//const message: Ref<string> = ref(BLANK);

</script>
<template>

    <div class="menu-container">
        <div class="menu-section">
            <h4 class="menu-title">利用者組織</h4>
            <RouterLink :to="RoutePathConstants.PAGE_REGIST_RIYOUSHA_ORG" class="menu-item">利用者組織編集</RouterLink>
            <a href="#" class="menu-item">組織・個人紐づけ</a>
        </div>

        <div class="menu-section">
            <h4 class="menu-title">本人情報編集</h4>
            <a href="#" class="menu-item">パスワード更新</a><br>
            <a href="#" class="menu-item">名称・住所・連絡先編集</a><br>
            <a href="#" class="menu-item">退会</a><br>
            <!--
    <RouterLink :to=RoutePathConstants.PAGE_REFLESH_PASSWORD>パスワード更新</RouterLink><br>
    <RouterLink :to=RoutePathConstants.PAGE_USER_EDIT>ユーザ編集</RouterLink><br>
    <RouterLink :to=RoutePathConstants.PAGE_CHANGE_ROLE>権限変更</RouterLink><br>
    <RouterLink :to=RoutePathConstants.PAGE_USER_WITHDRAW>退会</RouterLink><br>
    -->

        </div>
    </div>

    <div class="menu-container">
        <div class="menu-section">
            <h4 class="menu-title">ログアウト</h4>
            <RouterLink :to="RoutePathConstants.PAGE_LOGOUT" class="menu-item">ログアウト</RouterLink>
        </div>
    </div>

</template>
<style scoped></style>
