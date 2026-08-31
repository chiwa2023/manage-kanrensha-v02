<script setup lang="ts">
import RoutePathConstants from '../../../../routePathConstants';

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
            <RouterLink class="menu-item" :to=RoutePathConstants.PAGE_KANRENSHA_MYSELF>本人情報編集</RouterLink>
        </div>
    </div>

    <RouterLink class="menu-item" :to=RoutePathConstants.PAGE_MENU_KANRENSHA>ページで見る</RouterLink><br>

    <div class="menu-container">
        <div class="menu-section">
            <h4 class="menu-title">ログアウト</h4>
            <RouterLink :to="RoutePathConstants.PAGE_LOGOUT" class="menu-item">ログアウト</RouterLink>
        </div>
    </div>

</template>
<style scoped></style>
