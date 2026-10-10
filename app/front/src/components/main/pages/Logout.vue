<script setup lang="ts">
import RoutePathConstants from '../../../routePathConstants';
import router from '../../../router';
import { logout } from '../utils/logout';

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

// ログアウト処理
logout();

// ログインページに遷移
router.push(RoutePathConstants.PAGE_LOGIN);
</script>
<template>
    ログアウトしています...<br>
    <RouterLink :to=RoutePathConstants.PAGE_LOGIN>ログインページへ</RouterLink><br>
</template>
<style scoped></style>
