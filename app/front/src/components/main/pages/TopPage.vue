<script setup lang="ts">
import RoutePathConstants from '../../../routePathConstants';
// import RoutePathConstants from './routePathConstants';
// import type UserPersonLeastInterface from './dto/user/userPersonLeastDto';
// import UserPersonLeastDto from './dto/user/userPersonLeastDto';
// import UserRoleConstants from './dto/user/userRoleConstants';

// const userDto: Ref<UserPersonLeastInterface> = ref(new UserPersonLeastDto());
// userDto.value.listRoles.push(UserRoleConstants.ROLE_ADMIN);
// userDto.value.listRoles.push(UserRoleConstants.ROLE_MANAGER);
// userDto.value.listRoles.push(UserRoleConstants.ROLE_COMRADE);
// userDto.value.listRoles.push(UserRoleConstants.ROLE_PARTNER_PERSON);
// userDto.value.listRoles.push(UserRoleConstants.ROLE_PARTNER_CORP);
// userDto.value.listRoles.push(UserRoleConstants.ROLE_PARTNER_POLI_ORG);
// userDto.value.userPersonCode=190;
// sessionStorage.setItem("userDto", JSON.stringify(userDto.value));

</script>
<template>
    <div class="container">

        <h1>政治資金関連者標準化サイト</h1>

        <h3>ログイン処理を省略・・・</h3>
        <!-- 
    <RouterLink :to=RoutePathConstants.PAGE_MENU_MANAGER>管理者メニュー</RouterLink><br>
    <RouterLink :to=RoutePathConstants.PAGE_MENU_COMRADE>APIユーザメニュー</RouterLink><br>
    <RouterLink :to=RoutePathConstants.PAGE_MENU_PARTNER>関連者メニュー</RouterLink><br>
    <hr>
    <RouterLink :to=RoutePathConstants.PAGE_MENU_ADMIN>SEメニュー</RouterLink><br>
    <hr>
    <RouterLink :to=RoutePathConstants.PAGE_DOWNLOAD_HISTORY>関連者履歴データダウンロード(公開文書記載水準)</RouterLink><br>
    <RouterLink :to=RoutePathConstants.PAGE_DOWNLOAD_MASTER_MIN>関連者マスタ最小ダウンロード(公開文書記載水準)</RouterLink><br>
    <RouterLink :to=RoutePathConstants.PAGE_DOWNLOAD_SABUN_HISTORY>関連者履歴データダウンロード差分(公開文書記載水準)</RouterLink><br>
    <RouterLink :to=RoutePathConstants.PAGE_DOWNLOAD_SABUN_MASTER_MIN>関連者マスタ最小ダウンロード差分(公開文書記載水準)</RouterLink><br>
    <hr>
    <RouterLink :to=RoutePathConstants.PAGE_COMPONENT>コンポーネント作成台紙</RouterLink><br>
    -->

        <RouterLink :to=RoutePathConstants.PAGE_RIYOUSHA_SEARCH>利用者検索</RouterLink><br>

        <hr>
        <h3>開発用</h3>
        <RouterLink :to=RoutePathConstants.PAGE_DEVELOP_TEMPLATE>テンプレートと共通ツールカタログ</RouterLink><br>
        <a href="#">開発用台紙</a><br>


    </div>
</template>
<style scoped></style>
