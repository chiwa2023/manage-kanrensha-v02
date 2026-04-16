<script setup lang="ts">
import { ref, watch, type Ref } from 'vue';
import UserRoleConstants from '../../dto/user/userRoleConstants';
import MenuRiyoushaAdminContent from './MenuRiyoushaAdminContent.vue';
import MenuRiyoushaManagerContent from './MenuRiyoushaManagerContent.vue';
import MenuKanrenshaContent from './MenuKanrenshaContent.vue';
import MenuRiyoushaPartnerApiContent from './MenuRiyoushaPartnerApiContent.vue';

// props,emmits
const props = defineProps<{ viewRole: string }>();
const emits = defineEmits(["sendCanceelMenu"]);

//仮
// よく使う定数
//const BLANK: string = "";
// const INIT_NUMBER: number = 0;
// const SERVER_STATUS_OK: number = 200;
// const SERVER_STATUS_ERROR: number = 400;
// メッセージボックス表示定数
//const infoLevel: Ref<number> = ref(MessageConstants.LEVEL_NONE);
//const messageType: Ref<number> = ref(MessageConstants.VIEW_NONE);
//const title: Ref<string> = ref(BLANK);
//const message: Ref<string> = ref(BLANK);

const vRole: Ref<string> = ref(props.viewRole);
watch(props, () => {
    vRole.value = props.viewRole;
});


/** キャンセル押下 */
function onCancel() {
    emits("sendCanceelMenu");
}
</script>
<template>

    <!-- 利用者SE権限メニュー -->
    <div v-if="UserRoleConstants.ROLE_ADMIN === vRole">
        <h1>SE権限メニュー</h1>
        <MenuRiyoushaAdminContent></MenuRiyoushaAdminContent>
    </div>

    <!-- 利用者運営者権限メニュー -->
    <div v-if="UserRoleConstants.ROLE_MANAGER === vRole || UserRoleConstants.ROLE_ADMIN === vRole">
        <h1>運営者メニュー</h1>
        <MenuRiyoushaManagerContent>
        </MenuRiyoushaManagerContent>
    </div>

    <!-- APIユーザ権限メニュー -->
    <div v-if="UserRoleConstants.ROLE_PARTNER_API === vRole">
        <h1>API利用者メニュー</h1>
        <MenuRiyoushaPartnerApiContent></MenuRiyoushaPartnerApiContent>
    </div>

    <!-- 関連者権限メニュー -->
    <div
        v-if="UserRoleConstants.ROLE_KANRENSHA_KIGYOU_DT === vRole || UserRoleConstants.ROLE_KANRENSHA_PERSON === vRole || UserRoleConstants.ROLE_KANRENSHA_SEIJIDANTAI === vRole">
        <h1>関連者用メニュー</h1>
        <MenuKanrenshaContent></MenuKanrenshaContent>
    </div>

    <div class="footer">
        <button @click="onCancel" class="footer-button">キャンセル</button>
    </div>

</template>
<style scoped></style>
