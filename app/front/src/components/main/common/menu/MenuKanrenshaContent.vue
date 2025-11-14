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


    <a href="#">メニューサンプル関連者</a><br>

    <!--
    <RouterLink :to=RoutePathConstants.PAGE_REFLESH_PASSWORD>パスワード更新</RouterLink><br>
    <RouterLink :to=RoutePathConstants.PAGE_USER_EDIT>ユーザ編集</RouterLink><br>
    <RouterLink :to=RoutePathConstants.PAGE_CHANGE_ROLE>権限変更</RouterLink><br>
    <RouterLink :to=RoutePathConstants.PAGE_USER_WITHDRAW>退会</RouterLink><br>
    -->
 
     <RouterLink :to="RoutePathConstants.PAGE_LOGOUT">ログアウト</RouterLink><br>

</template>
<style scoped></style>
