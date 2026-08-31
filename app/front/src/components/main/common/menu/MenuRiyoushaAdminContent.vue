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
            <h4 class="menu-title">利用者管理</h4>
            <RouterLink class="menu-item" :to=RoutePathConstants.PAGE_RIYOUSHA_SEARCH>利用者検索／編集</RouterLink>
            <RouterLink class="menu-item" :to=RoutePathConstants.PAGE_SEARCH_RIYOUSHA_ORG>利用者組織検索／編集</RouterLink>
        </div>
    </div>

    <div class="menu-container">
        <div class="menu-section">
            <h4 class="menu-title">ダンプcsv処理</h4>
            <RouterLink :to="RoutePathConstants.PAGE_DUMP_MASTER" class="menu-item">関連者マスタ最小csvダンプ(指定期間まで)</RouterLink>
            <RouterLink :to="RoutePathConstants.PAGE_DUMP_HISTORY" class="menu-item">関連者履歴csvダンプ(指定期間まで)</RouterLink>
            <RouterLink :to="RoutePathConstants.PAGE_DUMP_MASTER_STD" class="menu-item">関連者マスタ標準csvダンプ(指定期間まで)
            </RouterLink>
            <RouterLink :to="RoutePathConstants.PAGE_DUMP_SABUN_MASTER" class="menu-item">関連者マスタ最小csv差分ダンプ</RouterLink>
            <RouterLink :to="RoutePathConstants.PAGE_DUMP_SABUN_HISTORY" class="menu-item">関連者履歴csv差分ダンプ</RouterLink>
            <RouterLink :to="RoutePathConstants.PAGE_DUMP_SABUN_MASTER_STD" class="menu-item">関連者マスタ標準csv差分ダンプ
            </RouterLink>
        </div>
    </div>

    <div class="menu-container">
        <div class="menu-section">
            <h4 class="menu-title">タスク設定</h4>
            <RouterLink :to="RoutePathConstants.PAGE_SEARCH_TASK_INFO" class="menu-item">タスク情報一覧・編集 </RouterLink>
            <RouterLink :to="RoutePathConstants.PAGE_TIMER_YOTEI" class="menu-item">予定実行一覧・編集 </RouterLink>
        </div>
    </div>

    <div class="menu-container">
        <div class="menu-section">
            <h4 class="menu-title">SE権限メニュー</h4>
            <RouterLink class="menu-item" :to=RoutePathConstants.PAGE_USER_CHANGE>他者ユーザ変更</RouterLink>
            <RouterLink class="menu-item" :to=RoutePathConstants.PAGE_ADMIN_PROMOTE>管理者からSE権限へ引き上げ</RouterLink>
        </div>
    </div>

    <RouterLink :to=RoutePathConstants.PAGE_MENU_ADMIN>ページで見る</RouterLink><br>

    <div class="menu-container">
        <div class="menu-section">
            <h4 class="menu-title">ログアウト</h4>
            <RouterLink :to="RoutePathConstants.PAGE_LOGOUT" class="menu-item">ログアウト</RouterLink>
        </div>
    </div>

</template>
<style scoped></style>
