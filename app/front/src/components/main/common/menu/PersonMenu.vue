<script setup lang="ts">
import { onBeforeMount, ref, watch, type Ref } from 'vue';
import RoutePathConstants from '../../../../routePathConstants';
import UserRoleConstants from '../../dto/user/userRoleConstants';

// props,emmits
const props = defineProps<{ viewRole: string }>();
const emits = defineEmits(["sendCanceelMenu"]);

//仮
// よく使う定数
const BLANK: string = "";
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

onBeforeMount(() => {
    setAnchor();
});

// 個人の情報編集画面は権限が決まってから遷移先が決まる
const personEditUrl: Ref<string> = ref(BLANK);
function setAnchor() {
    switch (vRole.value) {
        case UserRoleConstants.ROLE_MANAGER, UserRoleConstants.ROLE_ADMIN:
            personEditUrl.value = RoutePathConstants.PAGE_EDIT_RIYOUSHA_MANAGER;
            break;

        case UserRoleConstants.ROLE_PARTNER_API:
            personEditUrl.value = RoutePathConstants.PAGE_EDIT_RIYOUSHA_PARTNER;
            break;

        // case UserRoleConstants.ROLE_KANRENSHA_KIGYOU_DT:
        //     personEditUrl.value = RoutePathConstants.PAGE_EDIT_RIYOUSHA_PARTNER;
        //     break;
        // case UserRoleConstants.ROLE_KANRENSHA_PERSON:
        //     personEditUrl.value = RoutePathConstants.PAGE_EDIT_RIYOUSHA_PARTNER;
        //     break;
        // case UserRoleConstants.ROLE_KANRENSHA_SEIJIDANTAI:
        //     personEditUrl.value = RoutePathConstants.PAGE_EDIT_RIYOUSHA_PARTNER;
        //     break;

        default:
            // TODO 該当権限がない場合の処理
            break;
    }
}


/** キャンセル押下 */
function onCancel() {
    emits("sendCanceelMenu");
}
</script>
<template>
    {{ vRole }}

    <div style="overflow-y: scroll">
        <RouterLink :to="RoutePathConstants.PAGE_SEARCH_TASK_PLAN" class="menu-item">タスク計画検索</RouterLink>
        <RouterLink class="menu-item" :to=personEditUrl>個人情報編集</RouterLink>
        <RouterLink class="menu-item" :to=RoutePathConstants.PAGE_USER_EDIT>個人名・権限編集</RouterLink>
        <RouterLink class="menu-item" :to=RoutePathConstants.PAGE_REFRESH_PASSWORD>パスワード更新</RouterLink>
        <RouterLink :to="RoutePathConstants.PAGE_LOGOUT" class="menu-item">ログアウト</RouterLink>
        <RouterLink :to="RoutePathConstants.PAGE_USER_WITHDRAW" class="menu-item">退会</RouterLink>

        <div class="footer_sub">
            <button @click="onCancel" class="footer-button">閉じる</button>
        </div>
    </div>

</template>
<style scoped></style>
