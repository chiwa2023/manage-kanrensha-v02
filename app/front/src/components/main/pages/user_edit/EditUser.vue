<script setup lang="ts">
import { ref, type Ref } from 'vue';
import { type LeastUserDtoInterface } from 'seijishikin-jp-normalize_common-tool';
import { getLoginUser } from '../../utils/getLoginUser';
import UserDetailEdit from '../../common/user/UserDetailEdit.vue';
import AllUserInfo from '../../common/user_info/AllUserInfo.vue';
import router from '../../../../router';
import RoutePathConstants from '../../../../routePathConstants';

// ユーザ呼び出し
const userDto: Ref<LeastUserDtoInterface> = ref(getLoginUser());

function recieveCancelEditUser() {
    router.back();
}

function recieveEditUserInterface() {
    // 複数回同一データ編集するとエラーとなるのでログアウトする
    // MEMO 不評の場合はback側で再ログインできる対応をする
    router.push(RoutePathConstants.PAGE_LOGOUT);
}
</script>
<template>

    <!-- ユーザメニュー兼チェック -->
    <AllUserInfo :user-dto="userDto"></AllUserInfo>

    <h1>ユーザ編集</h1>
    <UserDetailEdit :user-dto="userDto" :edit-user-id="userDto.userPersonId"
        @send-cancel-edit-user="recieveCancelEditUser" @send-edit-user-interface="recieveEditUserInterface"></UserDetailEdit>

</template>
<style scoped></style>
