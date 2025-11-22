<script setup lang="ts">
import RiyoushaPartnerApiEdit from '../../common/riyousha_edit/RiyoushaPartnerApiEdit.vue';

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

</script>
<template>

    <h1>API接続者編集</h1><br>

    <!-- API接続者編集 -->
    <RiyoushaPartnerApiEdit></RiyoushaPartnerApiEdit>

    <div class="footer">
        <button class="footer-button">キャンセル</button>
        <button class="footer-button left-space">送信</button>
    </div>

</template>
<style scoped></style>
