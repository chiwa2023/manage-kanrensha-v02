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

    <!-- 
    <h3>関連者メニュー</h3>
    <RouterLink :to=RoutePathConstants.PAGE_REGI_PARTNER_MANAGE>関連者管理</RouterLink>
    <hr>
    -->
    <!-- 
    <h4>関連者履歴一括登録</h4>
    <hr>
        <RouterLink :to=RoutePathConstants.PAGE_REGI_COMBINE_KIGYOU>個人－企業／団体紐づけ登録</RouterLink>
    <RouterLink :to=RoutePathConstants.PAGE_REGI_COMBINE_POLI_ORG>個人－政治団体紐づけ登録</RouterLink>
    -->

    <div class="menu-container">
        <div class="menu-section">
            <h4 class="menu-title">関連者マスタ一括登録</h4>
            <RouterLink :to="RoutePathConstants.PAGE_REGI_BULK_HISTORY_KIGYOU" class="menu-item">企業・団体履歴登録</RouterLink>
            <RouterLink :to="RoutePathConstants.PAGE_REGI_BULK_HISTORY_PERSON" class="menu-item">個人履歴登録</RouterLink>
            <RouterLink :to="RoutePathConstants.PAGE_REGI_BULK_HISTORY_POLI_ORG" class="menu-item">政治団体履歴登録</RouterLink>
            <RouterLink :to="RoutePathConstants.PAGE_REGI_BULK_MASTER_KIGYOU" class="menu-item">企業・団体マスタ登録</RouterLink>
            <RouterLink :to="RoutePathConstants.PAGE_REGI_BULK_MASTER_PERSON" class="menu-item">個人マスタ登録</RouterLink>
            <RouterLink :to="RoutePathConstants.PAGE_REGI_BULK_MASTER_POLI_ORG" class="menu-item">政治団体マスタ登録</RouterLink>
            <RouterLink :to="RoutePathConstants.PAGE_REGI_BULK_COMBINE_KIGYOU" class="menu-item">関連者個人企業・団体紐づけ登録</RouterLink>
            <RouterLink :to="RoutePathConstants.PAGE_REGI_BULK_COMBINE_SEIJIDANTAI" class="menu-item">関連者個人政治団体紐づけ登録</RouterLink>
            <RouterLink :to="RoutePathConstants.PAGE_WORKS_APPROVAL" class="menu-item">作業内容承認</RouterLink>
        </div>

        <div class="menu-section">
            <h4 class="menu-title">関連者XMLから一括登録</h4>
            <RouterLink :to="RoutePathConstants.PAGE_ADD_XML" class="menu-item">関連者XMLから一括登録</RouterLink>
        </div>

        <div class="menu-section">
            <h4 class="menu-title">関連者管理(新規・編集・削除)</h4>
            <RouterLink :to="RoutePathConstants.PAGE_KANRENSHA_MANAGE" class="menu-item">関連者管理</RouterLink>
        </div>

        <div class="menu-section">
            <h4 class="menu-title">関連者マスタ標準データダウンロード</h4>
            <RouterLink :to="RoutePathConstants.PAGE_DOWNLOAD_MASTER_STD" class="menu-item">関連者マスタ標準データダウンロード
            </RouterLink>
            <RouterLink :to="RoutePathConstants.PAGE_DOWNLOAD_SABUN_MASTER_STD" class="menu-item">差分関連者マスタ標準データダウンロード
            </RouterLink>
        </div>
    </div>

    <div class="menu-container">
        <div class="menu-section">
            <h4 class="menu-title">住所編集</h4>
            <RouterLink :to="RoutePathConstants.PAGE_POSTAL_BUILDING" class="menu-item">フロア郵便番号建物住所入力</RouterLink>
            <RouterLink :to="RoutePathConstants.PAGE_POSTAL_EDIT" class="menu-item">郵便番号編集</RouterLink>
            <RouterLink :to="RoutePathConstants.PAGE_POSTAL_SABUN" class="menu-item">郵便番号差分取込</RouterLink>
            <RouterLink :to="RoutePathConstants.PAGE_ADDRESS_REGI_EDIT" class="menu-item">住所編集</RouterLink>
            <RouterLink :to="RoutePathConstants.PAGE_ADDRESS_REGI_SABUN" class="menu-item">住所差分取込</RouterLink>
        </div>
    </div>

    <div class="menu-container">
        <div class="menu-section">
            <h4 class="menu-title">利用者組織</h4>
            <RouterLink :to="RoutePathConstants.PAGE_REGIST_RIYOUSHA_ORG" class="menu-item">利用者組織編集</RouterLink>
            <a href="#" class="menu-item">組織・個人紐づけ</a>
        </div>
    </div>

    <div class="menu-container">
        <div class="menu-section">
            <h4 class="menu-title">本人情報編集</h4>
            <RouterLink :to="RoutePathConstants.PAGE_EDIT_RIYOUSHA_MANAGER" class="menu-item">名称・住所・連絡先編集</RouterLink>
        </div>

        <div class="menu-section">
            <h4 class="menu-title">権限メニュー</h4>
            <RouterLink :to=RoutePathConstants.PAGE_ADMIN_ACCEPT class="menu-item">管理者から推薦諾否</RouterLink>
        </div>
    </div>

    <RouterLink :to=RoutePathConstants.PAGE_MENU_MANAGER>ページで見る</RouterLink><br>

    <div class="menu-container">
        <div class="menu-section">
            <h4 class="menu-title">ログアウト</h4>
            <RouterLink :to="RoutePathConstants.PAGE_LOGOUT" class="menu-item">ログアウト</RouterLink>
        </div>
    </div>
</template>
<style scoped></style>
