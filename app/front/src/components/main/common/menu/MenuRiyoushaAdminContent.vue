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
    <h4>SE権限メニュー</h4>
    <!--
    <RouterLink :to=RoutePathConstants.PAGE_USER_DELETE>ユーザ削除</RouterLink><br>
    <RouterLink :to=RoutePathConstants.PAGE_USER_CHANGE>ユーザ編集</RouterLink><br>
    <RouterLink :to=RoutePathConstants.PAGE_PROMOTE_ROLE>運営者からSE権限へ引き上げ</RouterLink><br>
    <RouterLink :to=RoutePathConstants.PAGE_REGI_PARTNER_MANAGE>関連者管理</RouterLink><br>
    <RouterLink :to=RoutePathConstants.PAGE_WORKS_APPROVAL>作業内容承認</RouterLink><br>
 -->


    <div class="menu-container">
        <div class="menu-section">
            <h4>利用者管理</h4>
            <RouterLink class="menu-item" :to=RoutePathConstants.PAGE_RIYOUSHA_SEARCH>利用者検索／編集</RouterLink><br>
        </div>

        <div class="menu-section">
            <h4>ダンプcsv処理</h4>
            <RouterLink :to="RoutePathConstants.PAGE_DUMP_MASTER" class="menu-item">関連者マスタcsvダンプ(指定期間まで)</RouterLink>
            <br>
            <RouterLink :to="RoutePathConstants.PAGE_DUMP_HISTORY" class="menu-item">関連者履歴csvダンプ(指定期間まで)</RouterLink>
            <br>
            <RouterLink :to="RoutePathConstants.PAGE_DUMP_MASTER_STD" class="menu-item">関連者マスタ標準csvダンプ(指定期間まで)
            </RouterLink><br>
            <RouterLink :to="RoutePathConstants.PAGE_DUMP_SABUN_MASTER" class="menu-item">関連者マスタcsv差分ダンプ</RouterLink>
            <br>
            <RouterLink :to="RoutePathConstants.PAGE_DUMP_SABUN_HISTORY" class="menu-item">関連者履歴csv差分ダンプ</RouterLink>
            <br>
            <RouterLink :to="RoutePathConstants.PAGE_DUMP_SABUN_MASTER_STD" class="menu-item">関連者マスタ標準csv差分ダンプ
            </RouterLink><br>
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
