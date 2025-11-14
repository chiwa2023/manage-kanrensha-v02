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


    <a href="#">サンプル運営者</a><br>

    <!--
    <h3>関連者メニュー</h3>
    <RouterLink :to=RoutePathConstants.PAGE_REGI_PARTNER_MANAGE>関連者管理</RouterLink><br>
    <RouterLink :to=RoutePathConstants.PAGE_WORKS_APPROVAL>作業内容承認</RouterLink><br>

    <hr>

    <h3>郵便番号メニュー</h3>
    <RouterLink :to=RoutePathConstants.PAGE_POSTAL_BUILDING>フロア郵便番号住所追加</RouterLink><br>
    <RouterLink :to=RoutePathConstants.PAGE_POSTAL_EDIT>郵便番号編集</RouterLink><br>
    <RouterLink :to=RoutePathConstants.PAGE_POSTAL_SABUN>郵便番号差分更新</RouterLink><br>
    <hr>

    <h3>アドレス・ベース・レジストリ</h3>
    <RouterLink :to=RoutePathConstants.PAGE_ADDRESS_REGI_EDIT>アドレス・ベース・レジストリ編集</RouterLink><br>
    <RouterLink :to=RoutePathConstants.PAGE_ADDRESS_REGI_SABUN>アドレス・ベース・レジストリ差分更新</RouterLink><br>
    <hr>

    <h3>関連者履歴一括登録</h3>
    <RouterLink :to=RoutePathConstants.PAGE_REGI_BULK_HISTORY_CORP>企業・団体履歴登録</RouterLink><br>
    <RouterLink :to=RoutePathConstants.PAGE_REGI_BULK_HISTORY_PERSON>個人履歴登録</RouterLink><br>
    <RouterLink :to=RoutePathConstants.PAGE_REGI_BULK_HISTORY_POLI_ORG>政治団体履歴登録</RouterLink><br>

    <h3>関連者マスタ一括登録</h3>
    <RouterLink :to=RoutePathConstants.PAGE_REGI_BULK_MASTER_CORP>企業・団体マスタ登録</RouterLink><br>
    <RouterLink :to=RoutePathConstants.PAGE_REGI_BULK_MASTER_PERSON>個人マスタ登録</RouterLink><br>
    <RouterLink :to=RoutePathConstants.PAGE_REGI_BULK_MASTER_POLI_ORG>政治団体マスタ登録</RouterLink><br>
    <RouterLink :to=RoutePathConstants.PAGE_REGI_COMBINE_CORP>個人－企業／団体紐づけ登録</RouterLink><br>
    <RouterLink :to=RoutePathConstants.PAGE_REGI_COMBINE_POLI_ORG>個人－政治団体紐づけ登録</RouterLink><br>

    <hr>
    <h3>関連者XMLから一括登録</h3>
    <RouterLink :to=RoutePathConstants.PAGE_ADD_XML>関連者XMLから一括登録</RouterLink><br>

    <hr>
    <h3>関連者マスタ標準データダウンロード</h3>
    <RouterLink :to=RoutePathConstants.PAGE_DOWNLOAD_MASTER_STD>関連者マスタ標準データダウンロード</RouterLink><br>
    <RouterLink :to=RoutePathConstants.PAGE_DOWNLOAD_SABUN_MASTER_STD>差分関連者マスタ標準データダウンロード</RouterLink><br>

    <hr>
    <RouterLink :to=RoutePathConstants.PAGE_EDIT_MANAGER>所属組織編集(登録)</RouterLink><br>
    <RouterLink :to=RoutePathConstants.PAGE_ACCEPT_MANAGER_PERSON>個人と組織紐づけ承認</RouterLink><br>

    <hr>
    <RouterLink :to=RoutePathConstants.PAGE_LOGIN>ログインページ</RouterLink><br>
 -->

    <RouterLink :to="RoutePathConstants.PAGE_LOGOUT">ログアウト</RouterLink><br>

</template>
<style scoped></style>
