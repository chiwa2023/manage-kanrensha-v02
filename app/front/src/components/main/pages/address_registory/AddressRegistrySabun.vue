<script setup lang="ts">
import type { LeastUserDtoInterface } from 'seijishikin-jp-normalize_common-tool';
import { ref, type Ref } from 'vue';
import { getLoginUser } from '../../utils/getLoginUser';
import MockReadCsv from '../../../test/common/read_csv/MockReadCsv.vue';
import type { StorageFileDtoInterface } from '../../dto/storage_file/storageFileDto';
import ManagerInfo from '../../common/user_info/ManagerInfo.vue';

// ユーザ呼び出し
const userDto: Ref<LeastUserDtoInterface> = ref(getLoginUser());


function recieveStorageFileBlock(storageFileDto: StorageFileDtoInterface) {
    //     tableData.value.splice(0);
    //     const allData: Ref<string[][]> = ref([[]]);
    //     allData.value = parseCSV(data);
    //     for (let i: number = 0; i < 10; i++) {
    //         tableData.value.push(allData.value[i])
    //     }
    //     readFile.value = "番地ファイル";
}

function recieveStorageFileRsdt(storageFileDto: StorageFileDtoInterface) {

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
    <!-- 管理者メニュー兼チェック -->
    <ManagerInfo :user-dto="userDto"></ManagerInfo>

    <h1>アドレスベースレジストリ差分修正</h1><br>

    <h3 class="accent-h3">番地ファイル選択</h3>
    <!-- csv読み出し10行 -->
    <MockReadCsv @send-storage-file-interface="recieveStorageFileBlock" :user-dto="userDto"></MockReadCsv>

    <hr>

    <!-- 住居ファイル用 -->
    <h3>住居ファイル選択</h3>
    <MockReadCsv @send-storage-file-interface="recieveStorageFileRsdt" :user-dto="userDto"></MockReadCsv>

    <div class="footer">
        <button @click="onCancel" class="footer-button">キャンセル</button>
        <button @click="onSave" class="footer-button left-space">送信</button>
    </div>

</template>
<style scoped>
table {
    border-style: solid;
    border-width: 1px;
}

td {
    border-style: solid;
    border-width: 1px;
}

th {
    border-style: solid;
    border-width: 1px;
    text-align: center;
}
</style>
