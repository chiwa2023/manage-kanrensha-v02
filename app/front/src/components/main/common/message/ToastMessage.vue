<script setup lang="ts">
import { MessageConstants } from '../../dto/message/messageConstants';

// props,emit
const props = defineProps<{ title: string, message: string }>();
const emits = defineEmits(["sendSubmit"]);
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

setTimeout(() => {
    // 親コンポーネントにメッセージを閉じるよう通知
    emits("sendSubmit",props.title);
}, 4500); // 4.5秒後に実行

</script>
<template>
    <!-- トーストメッセージ -->
    <div class="toast-message" :class="MessageConstants.CLASS_INFO">
        <strong>{{ title }}</strong>
        <p>{{ message }}</p>
    </div>
</template>
<style scoped>
.msgbox-info.message-dialog-title {
    background-color: var(--primary-color);
}
.toast-message.msgbox-info {
    background-color: var(--success-color);
    color: var(--text-color);
    border: 1px solid #c3e6cb;
}
</style>
