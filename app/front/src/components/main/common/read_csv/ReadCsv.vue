<script setup lang="ts">
import { ref, type Ref } from 'vue';
import { UploadContentCapsuleDto, type UploadContentCapsuleDtoInterface } from '../../dto/storage_file/uploadContentCapsuleDto';
import type { LeastUserDtoInterface } from '../../dto/user/leastUserDto';
// import UploadContentCapsuleInterface from '../../../dto/storage_file/uploadContentCapsuleDto';
// import UploadContentCapsuleDto from '../../../dto/storage_file/uploadContentCapsuleDto';
// import getAuthorizedPromiseArea from '../../../dto/login/getAuthorizedPromiseArea';
// import type LookAheadCsvResultInterface from '../../../dto/storage_file/lookAheadCsvResultDto';
// import RoutePathConstants from '../../../routePathConstants';

//props,emit
const props = defineProps<{ userDto: LeastUserDtoInterface }>()
const emits = defineEmits(["sendStorageFileInterface"]);

// back側アクセス
//const urlBack: string = RoutePathConstants.DOMAIN_BACK + RoutePathConstants.PATH_BACK;

// 文字コード
const capsuleDto: Ref<UploadContentCapsuleDtoInterface> = ref(new UploadContentCapsuleDto());
capsuleDto.value.uploadFileDto.charset = "UTF-8";

//ファイル指定ダイアログ
const selectFileInput: Ref<HTMLInputElement | undefined> = ref<HTMLInputElement>();
const tableData: Ref<string[][]> = ref([[]]);

/**
 * ファイル選択ダイアログを表示する
 */
function onReadButton() {
    if (selectFileInput.value !== undefined) {
        selectFileInput.value.click();
    }
}

/**
 * 指定されたファイルを読み込む
 */
async function readTextFile() {
    // if (selectFileInput.value !== null) {
    //     if (selectFileInput.value !== undefined) {
    //         if (selectFileInput.value.files !== null) {
    //             const file: File = selectFileInput.value.files[0];
    //             capsuleDto.value.uploadFileDto.fileName = file.name;
    //             // ファイルをバイト取得
    //             const reader: FileReader = new FileReader();
    //             reader.readAsDataURL(file);
    //             reader.onload = async () => {
    //                 if (reader.result !== null) {
    //                     capsuleDto.value.uploadFileDto.fileContent = String(reader.result);
    //                     getAuthorizedPromiseArea().then(token => {
    //                         const url = urlBack + "/csv/look-ahead";
    //                         const method = "POST";
    //                         const body = JSON.stringify(capsuleDto.value);
    //                         const headers = {
    //                             'Accept': 'application/json',
    //                             'Content-Type': 'application/json',
    //                             'X-AUTH-TOKEN': 'Bearer ' + token
    //                         };
    //                         fetch(url, { method, headers, body })
    //                             .then(async (response) => {
    //                                 if (response.status < 400) {
    //                                     const resultDto: LookAheadCsvResultInterface = await response.json();

    //                                     if (response.status === 200) {
    //                                         tableData.value = resultDto.tableData;
    //                                         //TODO 保存したファイル情報は親に渡す
    //                                         emits("sendStorageFileInterface", resultDto.storageFileDto);
    //                                     } else {
    //                                         alert(resultDto.message);
    //                                     }
    //                                 }
    //                             })
    //                             .catch((error) => { alert(error); });
    //                     });
    //                 }
    //             }
    //         } else {
    //             alert("ユーザが取れない");
    //         }
    //     }
    // }
}

function removeQuote(data: string): string {
    return data.replace('"', '').replace('"', '');
}

</script>
<template>
    <h3>CSVファイル選択</h3>

        <div class="one-line">

    <div class="left-area">
        読取りファイルの指定<br>
    </div>
    <div class="right-area">
        <input ref="selectFileInput" type="file" accept=".csv" @change="readTextFile" style="display:none;">
        <button @click="onReadButton">ファイルを指定して読み取り</button>
        文字が読めない場合
        <select v-model="capsuleDto.uploadFileDto.charset">
            <option value="UTF-8">UTF-8</option>
            <option value="Shift_JIS">Shift_JIS(Windows)</option>
        </select>
    </div>
    </div>

    <h3>読み取り結果(最初の10行)</h3>
    <div class="one-line">
        <table>
            <tbody>
                <tr v-for="row, index of tableData" :key="index">
                    <td v-for="cell, index of row" :key="index">
                        {{ removeQuote(cell) }}
                    </td>
                </tr>
            </tbody>
        </table>
    </div>

</template>
<style scoped>
    /** 
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
}
    */
</style>
