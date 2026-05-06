<script setup lang="ts">
import MenuRiyoushaPartnerApiContent from '../../common/menu/MenuRiyoushaPartnerApiContent.vue';
import { type LeastUserDtoInterface } from 'seijishikin-jp-normalize_common-tool';
import { computed, ref, type ComputedRef, type Ref } from 'vue';
import { getLoginUser } from '../../utils/getLoginUser';
import PartnerApiInfo from '../../common/user_info/PartnerApiInfo.vue';

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

const notHasDetailInfo: ComputedRef<boolean> = computed(
    () => userDto.value.kanrenshaCode == "" && userDto.value.riyoushaCode == 0);

</script>
<template>

    <!-- 運営者 -->
    <PartnerApiInfo :user-dto="userDto"></PartnerApiInfo>

    <h1>APIパートナー向けメニュー</h1><br>

    <div v-if="notHasDetailInfo">
        <p>
            連絡先等の詳細情報が登録されていない場合は、編集等の機能はご利用できません。<br>
            アイコンをクリックして出現した『メニュー>個人情報編集』から情報の追加をお願いします。
        </p>
    </div>
    <div v-if="!notHasDetailInfo">
        <MenuRiyoushaPartnerApiContent></MenuRiyoushaPartnerApiContent>
    </div>


</template>
<style scoped></style>
