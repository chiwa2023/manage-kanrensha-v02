<script setup lang="ts">
import { onBeforeMount, ref, type Ref } from 'vue';
import { getLoginUser } from '../../utils/getLoginUser';
import RiyoushaOrgEdit from '../../common/riyousha_edit/RiyoushaOrgEdit.vue';
import { SelectOptionNumberDto, type SelectOptionNumberDtoInterface } from '../../dto/select_options/selectOptionNumberDto';
import type { LeastUserDtoInterface } from 'seijishikin-jp-normalize_common-tool';
import ManagerInfo from '../../common/user_info/ManagerInfo.vue';

//仮
// よく使う定数
// const BLANK: string = "";
const INIT_NUMBER: number = 0;
// const SERVER_STATUS_OK: number = 200;
// const SERVER_STATUS_ERROR: number = 400;
// メッセージボックス表示定数
//const infoLevel: Ref<number> = ref(MessageConstants.LEVEL_NONE);
//const messageType: Ref<number> = ref(MessageConstants.VIEW_NONE);
//const title: Ref<string> = ref(BLANK);
//const message: Ref<string> = ref(BLANK);

// ユーザ呼び出し
const userDto: Ref<LeastUserDtoInterface> = ref(getLoginUser());

const riyoshaOrgoptions: Ref<SelectOptionNumberDtoInterface[]> = ref([]);
const selectedOrgId: Ref<number> = ref(INIT_NUMBER);
onBeforeMount(() => {

    const dto1: SelectOptionNumberDtoInterface = new SelectOptionNumberDto();
    dto1.value = 0;
    dto1.text = "新規";

    riyoshaOrgoptions.value.push(dto1);

    const dto2: SelectOptionNumberDtoInterface = new SelectOptionNumberDto();
    dto2.value = 143;
    dto2.text = "利用者組織1";
    riyoshaOrgoptions.value.push(dto2);

});

function recieveCancelRiyoushaOrg() {
    history.back();
}

</script>
<template>
    <!-- 管理者メニュー兼チェック -->
    <ManagerInfo :user-dto="userDto"></ManagerInfo>

    <h1>利用者組織登録・編集</h1><br>

    <h3 class="accent-h3">利用者組織の指定</h3><br>

    <div class="one-line">
        <div class="left-area">
            編集する利用者組織
        </div>
        <div class="right-area">
            <select v-model="selectedOrgId">
                <option v-for="dto of riyoshaOrgoptions" :value="dto.value">{{ dto.text }}</option>
            </select>
        </div>
    </div>

    <!-- 利用者編集 -->
    <RiyoushaOrgEdit :user-dto="userDto" :selected-id="selectedOrgId"
        @send-cancel-riyousha-org="recieveCancelRiyoushaOrg"></RiyoushaOrgEdit>

</template>
<style scoped></style>
