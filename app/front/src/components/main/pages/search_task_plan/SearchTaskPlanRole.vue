<script setup lang="ts">
import { ref, type Ref } from 'vue';
import ShowTaskRole from '../../common/show_task/ShowTaskRole.vue';
import type { LeastUserDtoInterface } from 'seijishikin-jp-normalize_common-tool';
import { getLoginUser } from '../../utils/getLoginUser.ts';
import router from '../../../../router.ts';
import ManagerInfo from '../../common/user_info/ManagerInfo.vue';

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
    <ManagerInfo :user-dto="userDto"></ManagerInfo>

    <h1>タスク計画検索(権限)</h1>

    <!-- タスク計画検索 -->
    <ShowTaskRole :is-search-condition="true" :user-dto="userDto" @send-canceel-show-task="recieveCancelShowTask">
    </ShowTaskRole>

</template>
<style scoped></style>
