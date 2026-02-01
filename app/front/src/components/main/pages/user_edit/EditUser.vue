<script setup lang="ts">
import { ref, type Ref } from 'vue';
import { type LeastUserDtoInterface } from 'seijishikin-jp-normalize_common-tool';
import { getLoginUser } from '../../utils/getLoginUser';
import UserDetailEdit from '../../common/user/UserDetailEdit.vue';
import AllUserInfo from '../../common/user_info/AllUserInfo.vue';
import router from '../../../../router';

// ユーザ呼び出し
const userDto: Ref<LeastUserDtoInterface> = ref(getLoginUser());

function recieveCancelEditUser() {
    router.back();
}

</script>
<template>

    <!-- ユーザメニュー兼チェック -->
    <AllUserInfo :user-dto="userDto"></AllUserInfo>

    <h1>ユーザ編集</h1>
    <UserDetailEdit :user-dto="userDto" :edit-user-id="userDto.userPersonId"
        @send-cancel-edit-user="recieveCancelEditUser"></UserDetailEdit>

</template>
<style scoped></style>
