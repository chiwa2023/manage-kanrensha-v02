<script setup lang="ts">
import MockKanrenshaInfo from '../../../test/common/user_info/MockKanrenshaInfo.vue';
import MenuKanrenshaContent from '../../common/menu/MenuKanrenshaContent.vue';
import { type LeastUserDtoInterface } from '../../dto/user/leastUserDto';
import { ref, type Ref } from 'vue';
import { getLoginUser } from '../../utils/getLoginUser';

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
    <MockKanrenshaInfo :user-dto="userDto"></MockKanrenshaInfo>

    <h1>関連者用メニュー</h1><br>
    <MenuKanrenshaContent></MenuKanrenshaContent>

</template>
<style scoped></style>
