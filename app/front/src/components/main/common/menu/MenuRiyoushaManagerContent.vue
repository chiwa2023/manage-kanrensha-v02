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
        <!--
    <h4>関連者メニュー</h4>
    <RouterLink :to=RoutePathConstants.PAGE_REGI_PARTNER_MANAGE>関連者管理</RouterLink><br>
    <RouterLink :to=RoutePathConstants.PAGE_WORKS_APPROVAL>作業内容承認</RouterLink><br>

    <hr>

    <h4>郵便番号メニュー</h4>
    <RouterLink :to=RoutePathConstants.PAGE_POSTAL_BUILDING>フロア郵便番号住所追加</RouterLink><br>
    <RouterLink :to=RoutePathConstants.PAGE_POSTAL_EDIT>郵便番号編集</RouterLink><br>
    <RouterLink :to=RoutePathConstants.PAGE_POSTAL_SABUN>郵便番号差分更新</RouterLink><br>
    <hr>

    <h4>アドレス・ベース・レジストリ</h4>
    <RouterLink :to=RoutePathConstants.PAGE_ADDRESS_REGI_EDIT>アドレス・ベース・レジストリ編集</RouterLink><br>
    <RouterLink :to=RoutePathConstants.PAGE_ADDRESS_REGI_SABUN>アドレス・ベース・レジストリ差分更新</RouterLink><br>
    <hr>

    <h4>関連者履歴一括登録</h4>

    <hr>

        <RouterLink :to=RoutePathConstants.PAGE_REGI_COMBINE_KIGYOU>個人－企業／団体紐づけ登録</RouterLink><br>
    <RouterLink :to=RoutePathConstants.PAGE_REGI_COMBINE_POLI_ORG>個人－政治団体紐づけ登録</RouterLink><br>

 -->
        <div class="menu-section">
            <h4 class="menu-title">関連者マスタ一括登録</h4>
            <RouterLink :to="RoutePathConstants.PAGE_REGI_BULK_HISTORY_KIGYOU" class="menu-item">企業・団体履歴登録</RouterLink>
            <RouterLink :to="RoutePathConstants.PAGE_REGI_BULK_HISTORY_PERSON" class="menu-item">個人履歴登録</RouterLink>
            <RouterLink :to="RoutePathConstants.PAGE_REGI_BULK_HISTORY_POLI_ORG" class="menu-item">政治団体履歴登録</RouterLink>
            <RouterLink :to="RoutePathConstants.PAGE_REGI_BULK_MASTER_KIGYOU" class="menu-item">企業・団体マスタ登録</RouterLink>
            <RouterLink :to="RoutePathConstants.PAGE_REGI_BULK_MASTER_PERSON" class="menu-item">個人マスタ登録</RouterLink>
            <RouterLink :to="RoutePathConstants.PAGE_REGI_BULK_MASTER_POLI_ORG" class="menu-item">政治団体マスタ登録</RouterLink>
        </div>

        <div class="menu-section">
            <h4 class="menu-title">関連者XMLから一括登録</h4>
            <RouterLink :to="RoutePathConstants.PAGE_ADD_XML" class="menu-item">関連者XMLから一括登録</RouterLink>
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
            <h4 class="menu-title">利用者組織</h4>
            <RouterLink :to="RoutePathConstants.PAGE_REGIST_RIYOUSHA_ORG" class="menu-item">利用者組織編集</RouterLink>
            <a href="#" class="menu-item">組織・個人紐づけ</a>
        </div>

        <div class="menu-section">
            <h4 class="menu-title">本人情報編集</h4>
            <RouterLink :to="RoutePathConstants.PAGE_EDIT_RIYOUSHA_MANAGER" class="menu-item">名称・住所・連絡先編集</RouterLink>
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
