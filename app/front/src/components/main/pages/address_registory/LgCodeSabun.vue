<script setup lang="ts">
import { ref, type Ref } from 'vue';
import type { StorageFileDtoInterface } from '../../dto/storage_file/storageFileDto';
import type { LeastUserDtoInterface } from 'seijishikin-jp-normalize_common-tool';
import { getLoginUser } from '../../utils/getLoginUser';

//仮
// よく使う定数
// const BLANK: string = "";
// const INIT_NUMBER: number = 0;
// const SERVER_STATUS_OK: number = 200;
// const SERVER_STATUS_ERROR: number = 400;
// const SEARCH_LIMIT: number = 20;
// メッセージボックス表示定数
//const infoLevel: Ref<number> = ref(MessageConstants.LEVEL_NONE);
//const messageType: Ref<number> = ref(MessageConstants.VIEW_NONE);
//const title: Ref<string> = ref(BLANK);
//const message: Ref<string> = ref(BLANK);

// ユーザ呼び出し
const userDto: Ref<LeastUserDtoInterface> = ref(getLoginUser());

function recieveStorageFile(storageFileDto: StorageFileDtoInterface) {

    //     tableData.value.splice(0);
    //     const allData: Ref<string[][]> = ref([[]]);
    //     allData.value = parseCSV(data);
    //     for (let i: number = 0; i < 10; i++) {
    //         tableData.value.push(allData.value[i])
    //     }
    //     readFile.value = "住居ファイル";
}


function onCancel() {
    history.back();
}
function onSave() {
    alert("保存");
}

</script>
<template>
    <h1>地方自治体コード差分</h1>

    <!-- 住居ファイル用 -->
    <h3>地方自治体コードファイル選択</h3>
    <MockReadCsv @send-storage-file-interface="recieveStorageFile" :user-dto="userDto"></MockReadCsv>

    <div class="footer">
        <button @click="onCancel" class="footer-button">キャンセル</button>
        <button @click="onSave" class="footer-button left-space">送信</button>
    </div>

</template>
<style scoped></style>
