<script setup lang="ts">
import { ref, watch, type Ref } from 'vue';
import RoutePathConstants from '../../../../routePathConstants';

// props,emmits
const props = defineProps<{ viewRole: string }>();
const emits = defineEmits(["sendCanceelMenu"]);

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
    <p>
        <RouterLink :to=RoutePathConstants.PAGE_USER_EDIT>個人名・権限編集</RouterLink><br>
        <RouterLink :to=RoutePathConstants.PAGE_REFRESH_PASSWORD>パスワード更新</RouterLink><br>
        <br>
        <RouterLink :to=RoutePathConstants.PAGE_LOGOUT>ログアウト</RouterLink><br>
        <br>
        <hr>
        <RouterLink :to=RoutePathConstants.PAGE_USER_WITHDRAW>退会</RouterLink><br>
    </p>
    <div class="footer_sub">
        <button @click="onCancel" class="footer-button">閉じる</button>
    </div>
</template>
<style scoped></style>
