<script setup lang="ts">
import MockManagerInfo from '../../../test/common/user_info/MockManagerInfo.vue';
import MenuRiyoushaManagerContent from '../../common/menu/MenuRiyoushaManagerContent.vue';
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

    <!-- 運営者 -->
    <MockManagerInfo :user-dto="userDto"></MockManagerInfo>

    <h1>運営者メニュー</h1><br>
    <MenuRiyoushaManagerContent></MenuRiyoushaManagerContent>

    {{ userDto.userPersonId }}<br>
    {{ userDto.userPersonName }}<br>

</template>
<style scoped></style>
