<script setup lang="ts">
import { computed, onBeforeMount, ref, watch, type ComputedRef, type Ref } from 'vue';
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
    setAnchor();
});
onBeforeMount(() => {
    setAnchor();
});

// 個人の情報編集画面は権限が決まってから遷移先が決まる
const personEditUrl: Ref<string> = ref(BLANK);
function setAnchor() {
    // 関連者重複を許さないが、利用者は重複を許すので、
    // 詳細データが紐づけられている権限を詳細情報編集先としてピックアップする
    if(props.userDto.riyoushaCode !=  0){
        vRole.value = "ROLE_"+props.userDto.riyoushaRole;
    }
    // 詳細登録をしていないかつ全ユーザ共通で個人メニューを開くと、
    // メニュー切り替えができないので救済措置
    if (BLANK == vRole.value &&
        props.userDto.kanrenshaCode == BLANK &&
        props.userDto.riyoushaCode == 0 &&
        props.userDto.listRoles.length == 1) {
        if (undefined !== props.userDto.listRoles[0]) {
            vRole.value = props.userDto.listRoles[0];
        }
    }

    switch (vRole.value) {
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
        case UserRoleConstants.ROLE_ADMIN:
            personEditUrl.value = RoutePathConstants.PAGE_EDIT_RIYOUSHA_MANAGER;
            break;
        case UserRoleConstants.ROLE_MANAGER:
            personEditUrl.value = RoutePathConstants.PAGE_EDIT_RIYOUSHA_MANAGER;
            break;
        case UserRoleConstants.ROLE_PARTNER_API:
            personEditUrl.value = RoutePathConstants.PAGE_EDIT_RIYOUSHA_PARTNER;
            break;

        default:
            // TODO 関連者でも利用者でもない場合
            // ex.運営者で登録してAPIユーザに切り替えた場合

            break;
    }
}

const notHasDetailInfo: ComputedRef<boolean> = computed(
    () => props.userDto.kanrenshaCode == "" && props.userDto.riyoushaCode == 0);

/** キャンセル押下 */
function onCancel() {
    emits("sendCanceelMenu");
}
</script>
<template>
    <div style="overflow-y: scroll">
        <RouterLink :to="RoutePathConstants.PAGE_SEARCH_TASK_PLAN" class="menu-item" v-if="!notHasDetailInfo">
            タスク計画検索</RouterLink>
        <RouterLink class="menu-item" :to=personEditUrl>個人情報編集</RouterLink>
        <RouterLink class="menu-item" v-if="!notHasDetailInfo" :to=RoutePathConstants.PAGE_USER_EDIT>個人名・権限編集
        </RouterLink>
        <RouterLink class="menu-item" :to=RoutePathConstants.PAGE_REFRESH_PASSWORD>パスワード更新</RouterLink>
        <RouterLink :to="RoutePathConstants.PAGE_LOGOUT" class="menu-item">ログアウト</RouterLink>
        <RouterLink :to="RoutePathConstants.PAGE_USER_WITHDRAW" class="menu-item">退会</RouterLink>

        <div class="footer_sub">
            <button @click="onCancel" class="footer-button">閉じる</button>
        </div>
    </div>

</template>
<style scoped></style>
