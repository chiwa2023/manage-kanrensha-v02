<script setup lang="ts">
import { onBeforeMount, ref, watch, type Ref } from 'vue';
import RoutePathConstants from '../../../../routePathConstants';
import UserRoleConstants from '../../dto/user/userRoleConstants';
import type { LeastUserDtoInterface } from 'seijishikin-jp-normalize_common-tool';

// props,emmits
const props = defineProps<{ viewRole: string, userDto: LeastUserDtoInterface }>();
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
        // case UserRoleConstants.ADMIN:
        //     personEditUrl.value = RoutePathConstants.PAGE_EDIT_RIYOUSHA_MANAGER;
        //     break;
        // case UserRoleConstants.MANAGER:
        //     personEditUrl.value = RoutePathConstants.PAGE_EDIT_RIYOUSHA_MANAGER;
        //     break;
        // case UserRoleConstants.PARTNER_API:
        //     personEditUrl.value = RoutePathConstants.PAGE_EDIT_RIYOUSHA_PARTNER;
        //     break;

        // 関連者は一人につき1パターンのみ紐づけるので、持っているroleを確認すると特定できる
        case UserRoleConstants.ROLE_KANRENSHA_KIGYOU_DT:
            personEditUrl.value = RoutePathConstants.PAGE_KANRENSHA_MYSELF;
            break;
        case UserRoleConstants.ROLE_KANRENSHA_PERSON:
            personEditUrl.value = RoutePathConstants.PAGE_KANRENSHA_MYSELF;
            break;
        case UserRoleConstants.ROLE_KANRENSHA_SEIJIDANTAI:
            personEditUrl.value = RoutePathConstants.PAGE_KANRENSHA_MYSELF;
            break;

        default:
            // 利用者の場合
            if (UserRoleConstants.ADMIN == props.userDto.riyoushaRole) {
                personEditUrl.value = RoutePathConstants.PAGE_EDIT_RIYOUSHA_MANAGER;
                break;
            }
            if (UserRoleConstants.MANAGER == props.userDto.riyoushaRole) {
                personEditUrl.value = RoutePathConstants.PAGE_EDIT_RIYOUSHA_MANAGER;
                break;
            }
            if (UserRoleConstants.PARTNER_API == props.userDto.riyoushaRole) {
                personEditUrl.value = RoutePathConstants.PAGE_EDIT_RIYOUSHA_PARTNER;
                break;
            }

            // TODO 関連者でも利用者でもない場合
            // ex.運営者で登録してAPIユーザに切り替えた場合
            // ユーザ登録直後離脱し、詳細情報を登録していない場合
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
        <div v-if="vRole != BLANK">
            <RouterLink class="menu-item" :to=personEditUrl>個人情報編集</RouterLink>
            <RouterLink class="menu-item" :to=RoutePathConstants.PAGE_USER_EDIT>個人名・権限編集</RouterLink>
        </div>
        <RouterLink class="menu-item" :to=RoutePathConstants.PAGE_REFRESH_PASSWORD>パスワード更新</RouterLink>
        <RouterLink :to="RoutePathConstants.PAGE_LOGOUT" class="menu-item">ログアウト</RouterLink>
        <RouterLink :to="RoutePathConstants.PAGE_USER_WITHDRAW" class="menu-item">退会</RouterLink>

        <div class="footer_sub">
            <button @click="onCancel" class="footer-button">閉じる</button>
        </div>
    </div>

</template>
<style scoped></style>
