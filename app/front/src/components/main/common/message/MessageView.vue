<script setup lang="ts">
import { ref, type Ref } from 'vue';
import { MessageConstants } from '../../dto/message/messageConstants';
import ToastMessage from './ToastMessage.vue';

// よく使う定数
const BLANK: string = "";
// const INIT_NUMBER: number = 0;
// const SERVER_STATUS_OK: number = 200;
// const SERVER_STATUS_ERROR: number = 400;

// props,emit
const props = defineProps<{ infoLevel: number, messageType: number, title: string, message: string }>();
const emits = defineEmits(["sendSubmit"]);

const levelClass: Ref<string> = ref(getLevelClass());

function onButtonNo() {
    emits("sendSubmit", "no");
}

function onButtonYes() {
    emits("sendSubmit", "yes");
}

function onSubmit() {
    emits("sendSubmit", "ok");
}

/**
 * 情報レベルをクラスに変換する
 */
function getLevelClass(): string {

    if (MessageConstants.LEVEL_INFO === props.infoLevel) {
        return MessageConstants.CLASS_INFO;
    }
    if (MessageConstants.LEVEL_WARNING === props.infoLevel) {
        return MessageConstants.CLASS_WARNING;
    }
    if (MessageConstants.LEVEL_ERROR === props.infoLevel) {
        return MessageConstants.CLASS_ERROR;
    }
    return BLANK;
}

function recieveSubmit() {
    // Toastの終了通知を中継する
    emits("sendSubmit", props.title);
}

</script>
<template>

    <!-- トーストメッセージ -->
    <div v-if="props.messageType === MessageConstants.VIEW_TOAST">
        <ToastMessage :title="title" :message="message" @send-submit="recieveSubmit"></ToastMessage>
    </div>

    <!-- YES/NOボタンダイアログ -->
    <div v-if="props.messageType === MessageConstants.VIEW_YES_NO" class="message-dialog">
        <div :class="levelClass" class="message-dialog-title">{{ title }}</div>
        <div class="message-dialog-content">
            {{ message }}
        </div>
        <div class="message-dialog-buttons">
            <button @click="onButtonNo">いいえ</button>
            <button @click="onButtonYes" class="left-space primary-button">はい</button>
        </div>
    </div>

    <!-- OKボタンダイアログ -->
    <div v-if="props.messageType === MessageConstants.VIEW_OK" class="message-dialog">
        <div :class="levelClass" class="message-dialog-title">{{ title }}</div>
        <div class="message-dialog-content">
            {{ message }}
        </div>
        <div class="message-dialog-buttons">
            <button @click="onSubmit" class="primary-button">OK</button>
        </div>
    </div>

</template>
<style scoped>
.message-dialog {
    background-color: white;
    border: 1px solid var(--border-color);
    border-radius: 8px;
    box-shadow: 0 4px 12px rgba(0, 0, 0, 0.15);
    overflow: hidden;
    /* Ensures border-radius applies to children */
}

.message-dialog-title {
    padding: 1rem;
    font-weight: bold;
    color: white;
    font-size: 1.1em;
    /* Background color will come from levelClass */
}

.msgbox-info.message-dialog-title {
    background-color: var(--primary-color);
}

.msgbox-warning.message-dialog-title {
    background-color: var(--warning-color);
    color: var(--text-color);
}

.msgbox-error.message-dialog-title {
    background-color: var(--role-admin-color);
}

.message-dialog-content {
    padding: 1rem;
    font-size: 1.1em;
}

.message-dialog-buttons {
    display: flex;
    justify-content: flex-end;
    padding: 1rem;
    border-top: 1px solid var(--border-color);
    background-color: var(--secondary-color);
}

.primary-button {
    background-color: var(--primary-color);
    color: white;
}

/* New Toast Styles */
.toast-message {
    position: fixed;
    left: 50%;
    transform: translateX(-50%);
    bottom: -100px;
    /* Initial off-screen position */
    padding: 1rem 1.5rem;
    border-radius: 8px;
    z-index: 40;
    box-shadow: 0 4px 12px rgba(0, 0, 0, 0.15);
    text-align: center;
    min-width: 350px;
    max-width: 600px;
    animation: toast-animation 4s ease-in-out forwards;
}

.toast-message strong {
    font-size: 1.2em;
}

.toast-message p {
    margin: 0.5rem 0 0;
    font-size: 1.2em;
}

.toast-message.msgbox-info {
    background-color: var(--success-color);
    color: var(--text-color);
    border: 1px solid #c3e6cb;
}

.toast-message.msgbox-warning {
    background-color: var(--warning-color);
    color: var(--text-color);
}

.toast-message.msgbox-error {
    background-color: var(--role-admin-color);
    color: white;
}

@keyframes toast-animation {
    0% {
        bottom: -100px;
        /* Start off-screen bottom */
        opacity: 0;
    }

    10% {
        bottom: 33.33%;
        /* Stop at 1/3 from bottom */
        opacity: 1;
    }

    90% {
        bottom: 33.33%;
        /* Stay at 1/3 from bottom */
        opacity: 1;
    }

    100% {
        bottom: -100px;
        /* Disappear off-screen bottom */
        opacity: 0;
    }
}
</style>
