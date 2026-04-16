<script setup lang="ts">
import type { LeastUserDtoInterface } from 'seijishikin-jp-normalize_common-tool';
import { ref, type Ref } from 'vue';
import { getLoginUser } from '../../utils/getLoginUser';
import MockManagerInfo from '../../../test/common/user_info/MockManagerInfo.vue';
import MockReadCsv from '../../../test/common/read_csv/MockReadCsv.vue';
import type { StorageFileDtoInterface } from '../../dto/storage_file/storageFileDto';
// import ManagerInfo from '../../common/user_info/ManagerInfo.vue';

// ユーザ呼び出し
const userDto: Ref<LeastUserDtoInterface> = ref(getLoginUser());

// function recieveTextData(data: string) {

//     tableData.value = parseCSV(data);
//     const title: string[] = [
//         "1 全国地方公共団体コード",
//         "2 （旧）郵便番号",
//         "3 郵便番号",
//         "4 都道府県名",
//         "5 市区町村名",
//         "6 町域名",
//         "7 都道府県名",
//         "8 市区町村名",
//         "9 町域名",
//         "10 一町域が二以上の郵便番号で表される場合の表示",
//         "11 小字毎に番地が起番されている町域の表示",
//         "12 丁目を有する町域の場合の表示",
//         "13 一つの郵便番号で二以上の町域を表す場合の表示",
//         "14 更新の表示",
//         "15 変更理由"
//     ];
//     tableData.value.unshift(title);
// }


function recieveStorageFileAdd(storageFileDto: StorageFileDtoInterface) {
}
function recieveStorageFileDelete(storageFileDto: StorageFileDtoInterface) {
}



function onCancel() {
    alert("キャンセル");
    history.back();
}
function onSave() {
    alert("保存");
}
</script>
<template>
    <!-- 管理者メニュー兼チェック -->
    <MockManagerInfo :user-dto="userDto"></MockManagerInfo>

    <h1>郵便番号差分修正</h1><br>

    <h3 class="accent-h3">追加ファイル選択</h3>
    <!-- csv読み出し10行 -->
    <MockReadCsv @send-storage-file-interface="recieveStorageFileAdd" :user-dto="userDto"></MockReadCsv>

    <hr>
    
    <!-- 削除ファイル用 -->
    <h3 class="accent-h3">削除ファイル選択</h3>
    <!-- csv読み出し10行 -->
    <MockReadCsv @send-storage-file-interface="recieveStorageFileDelete" :user-dto="userDto"></MockReadCsv>

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
