<script setup lang="ts">
import { ref, type Ref } from 'vue';
import ShowTask from '../../common/show_task/ShowTask.vue';
import type { LeastUserDtoInterface } from 'seijishikin-jp-normalize_common-tool';
import { getLoginUser } from '../../utils/getLoginUser';
import router from '../../../../router';
import AllUserInfo from '../../common/user_info/AllUserInfo.vue';

//仮
// よく使う定数
// const BLANK: string = "";
// const INIT_NUMBER: number = 0;
// const SERVER_STATUS_OK: number = 200;
// const SERVER_STATUS_ERROR: number = 400;

// ユーザ呼び出し
const userDto: Ref<LeastUserDtoInterface> = ref(getLoginUser());

function recieveCancelShowTask() {
    router.back();
}

</script>
<template>
    <!-- 全メニュー兼チェック -->
    <AllUserInfo :user-dto="userDto"></AllUserInfo>

    <h1>タスク計画検索</h1>

    <!-- タスク計画検索 -->
    <ShowTask :is-search-condition="true" :user-dto="userDto" @send-canceel-show-task="recieveCancelShowTask">
    </ShowTask>

</template>
<style scoped></style>
