<script setup lang="ts">
import { ref, type Ref } from 'vue';
import type { LeastUserDtoInterface } from '../../../main/dto/user/leastUserDto';
import { UploadContentCapsuleDto, type UploadContentCapsuleDtoInterface } from '../../../main/dto/storage_file/uploadContentCapsuleDto';
import { MessageConstants } from 'seijishikin-jp-normalize_common-tool';
import mockCreateTableData from './mockCreateTableData';

//props,emit
const props = defineProps<{ userDto: LeastUserDtoInterface }>()
const emits = defineEmits(["sendStorageFileInterface"]);

// よく使う定数
const BLANK: string = "";

// メッセージ表示定数
const infoLevel: Ref<number> = ref(MessageConstants.LEVEL_NONE);
const messageType: Ref<number> = ref(MessageConstants.VIEW_NONE);
const title: Ref<string> = ref(BLANK);
const message: Ref<string> = ref(BLANK);

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
    if (selectFileInput.value !== null) {
        if (selectFileInput.value !== undefined) {
            if (selectFileInput.value.files !== null) {
                if (selectFileInput.value.files[0] != undefined) {
                    const file: File = selectFileInput.value.files[0];
                    capsuleDto.value.uploadFileDto.fileName = file.name;
                    // ファイルをバイト取得
                    const reader: FileReader = new FileReader();
                    reader.readAsDataURL(file);
                    reader.onload = async () => {
                        if (reader.result !== null) {
                            capsuleDto.value.uploadFileDto.fileContent = String(reader.result);

                            // ファイル読み取り
                            tableData.value = mockCreateTableData();
                        }
                    }
                }
            } else {
                onError();
            }
        }
    }
}

function removeQuote(data: string): string {
    return data.replace('"', '').replace('"', '');
}



// メッセージ表示
function onError() {
    infoLevel.value = MessageConstants.LEVEL_ERROR;
    title.value = "ファイル指定例外";
    message.value = "ファイルが読み取れませんでした";
    // 表示
    messageType.value = MessageConstants.VIEW_OK;
}

function recieveSubmit(button: string) {
    alert(button);
    // TODO ボタンタイプ別の挙動はこの中で変える

    // 非表示
    infoLevel.value = 0;
    messageType.value = 0;
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

    <!-- メッセージ表示 -->
    <div class="overMessage" v-if="messageType !== MessageConstants.VIEW_NONE">
        <MessageView :info-level="infoLevel" :message-type="messageType" :title="title" :message="message"
            @send-submit="recieveSubmit">
        </MessageView>
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
