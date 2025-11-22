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

    <h4>利用者組織</h4>
    <RouterLink :to=RoutePathConstants.PAGE_REGIST_RIYOUSHA_ORG>利用者組織編集</RouterLink><br>
    <a href="#">組織・個人紐づけ</a>
    <a href="#">Bearer更新</a><br>

    <h4>本人情報編集</h4>

    <RouterLink :to=RoutePathConstants.PAGE_EDIT_RIYOUSHA_PARTNER>名称・住所・連絡先編集</RouterLink><br>

    <br>
    <hr>
    <br>

    <RouterLink :to="RoutePathConstants.PAGE_LOGOUT">ログアウト</RouterLink><br>

</template>
<style scoped></style>
