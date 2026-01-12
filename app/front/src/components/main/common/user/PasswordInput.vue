<script setup lang="ts">
import { onMounted, ref, type Ref } from 'vue';
const emits = defineEmits(["sendPassword"]);

// props,emits
const props = defineProps<{ password: string }>()
const passwordData: Ref<string> = ref("");


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

onMounted(() => {
    passwordData.value = props.password;
});

// パスワード可視／不可視切り替えロジック
const isPasswordVisible: Ref<boolean> = ref(false);
const passwordInputType: Ref<string> = ref("password");
function changeVisiblePassword() {
    isPasswordVisible.value = !isPasswordVisible.value;
    if (isPasswordVisible.value) {
        passwordInputType.value = "text";
    } else {
        passwordInputType.value = "password";
    }
}

function send() {
    emits("sendPassword", passwordData.value);
}

</script>
<template>

    <input :type="passwordInputType" v-model="passwordData" autocomplete="off" @input="send">
    <span @click="changeVisiblePassword" class="password-toggle-icon">
        <img v-show="!isPasswordVisible" src="../../../../assets/password_hidden.png">
        <img v-show="isPasswordVisible" src="../../../../assets/password_visible.png">
    </span>

</template>
<style scoped>
.password-toggle-icon {
    right: 0.75rem;
    cursor: pointer;
    height: 1.35em;
}

.password-toggle-icon img {
    height: 1.35em;
    width: auto;
}
</style>
