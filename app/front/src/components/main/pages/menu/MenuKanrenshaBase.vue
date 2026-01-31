<script setup lang="ts">
import MenuKanrenshaContent from '../../common/menu/MenuKanrenshaContent.vue';
import { type LeastUserDtoInterface } from 'seijishikin-jp-normalize_common-tool';
import { ref, type Ref } from 'vue';
import { getLoginUser } from '../../utils/getLoginUser';
import KanrenshaInfo from '../../common/user_info/KanrenshaInfo.vue';

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

// ユーザ呼び出し
const userDto: Ref<LeastUserDtoInterface> = ref(getLoginUser());

</script>
<template>
    <!-- 関連者 -->
    <KanrenshaInfo :user-dto="userDto"></KanrenshaInfo>

    <h1>関連者用メニュー</h1><br>
    <MenuKanrenshaContent></MenuKanrenshaContent>

</template>
<style scoped></style>
