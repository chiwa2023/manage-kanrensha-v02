<script setup lang="ts">
import { ref, type Ref } from 'vue';
import { getLoginUser, KanrenshaManage, type LeastUserDtoInterface } from 'seijishikin-jp-normalize_common-tool';
import ManagerInfo from '../../common/user_info/ManagerInfo.vue';

// よく使う定数
// const BLANK: string = "";
// const SERVER_STATUS_OK: number = 200;
// const SERVER_STATUS_ERROR: number = 400;
// const INQUIRE_FLG: boolean = false;
// const ERR_MESS_ONLY: boolean = true;
// const MESS_PAGE_NAME: string = "関連者企業・団体／個人／政治団体編集";
// const INIT_CALLER: string = "no branch";

// メッセージボックス表示定数
// const infoLevel: Ref<number> = ref(MessageConstants.LEVEL_NONE);
// const messageType: Ref<number> = ref(MessageConstants.VIEW_NONE);
// const caller: Ref<string> = ref(INIT_CALLER);
// const message: Ref<string> = ref(BLANK);

// ユーザ呼び出し
const userDto: Ref<LeastUserDtoInterface> = ref(getLoginUser());

// 法人番号APIキー
const houjinAppId: string = import.meta.env.VITE_HOUJIN_NO_API;

</script>
<template>

    <!-- 管理者メニュー兼チェック -->
    <ManagerInfo :user-dto="userDto"></ManagerInfo>

    <!-- 関連者検索編集統合 -->
    <KanrenshaManage :user-dto="userDto" :houjin-api-key="houjinAppId"></KanrenshaManage>

</template>
<style scoped></style>
