<script setup lang="ts">
import { ref, type Ref } from 'vue';
import { UploadContentCapsuleDto, type UploadContentCapsuleDtoInterface } from '../../dto/storage_file/uploadContentCapsuleDto';
import { MessageConstants, type LeastUserDtoInterface } from 'seijishikin-jp-normalize_common-tool';
import getAuthorizedPromiseArea from '../../dto/login/getAuthorizedPromiseArea';
import RoutePathConstants from '../../../../routePathConstants';
import type { LookAheadCsvResultDtoInterface } from '../../dto/storage_file/lookAheadCsvResultDto';
import { AccessTokenNotFoundError, TokenRefreshError } from '../../dto/login/errors';
// import UploadContentCapsuleInterface from '../../../dto/storage_file/uploadContentCapsuleDto';
// import UploadContentCapsuleDto from '../../../dto/storage_file/uploadContentCapsuleDto';
// import getAuthorizedPromiseArea from '../../../dto/login/getAuthorizedPromiseArea';
// import type LookAheadCsvResultInterface from '../../../dto/storage_file/lookAheadCsvResultDto';
// import RoutePathConstants from '../../../routePathConstants';

//props,emit
const props = defineProps<{ userDto: LeastUserDtoInterface }>()
const emits = defineEmits(["sendStorageFileInterface"]);

// back側アクセス
const urlBack: string = RoutePathConstants.DOMAIN + RoutePathConstants.BASE_PATH;

// よく使う定数
const BLANK: string = "";
// const INIT_NUMBER: number = 0;
//const SEARCH_LIMIT: number = 20;
const SERVER_STATUS_OK: number = 200;
// const SERVER_STATUS_ERROR: number = 400;

// メッセージボックス表示定数
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
                const file: File | undefined = selectFileInput.value.files[0];
                if (undefined !== file) {
                    capsuleDto.value.uploadFileDto.fileName = file.name;
                    // ファイルをバイト取得
                    const reader: FileReader = new FileReader();
                    reader.readAsDataURL(file);
                    reader.onload = async () => {
                        if (reader.result !== null) {
                            capsuleDto.value.uploadFileDto.fileContent = String(reader.result);
                            capsuleDto.value.userDto = props.userDto;
                            // back側に接続して10行頭出し
                            getAuthorizedPromiseArea().then(token => {
                                const url = urlBack + "/csv/look-ahead";
                                const method = "POST";
                                const body = JSON.stringify(capsuleDto.value);
                                const headers = {
                                    'Accept': 'application/json',
                                    'Content-Type': 'application/json',
                                    'X-AUTH-TOKEN': 'Bearer ' + token
                                };
                                fetch(url, { method, headers, body })
                                    .then(async (response) => {
                                        const resultDto: LookAheadCsvResultDtoInterface = await response.json();
                                        if (SERVER_STATUS_OK === response.status) {
                                            tableData.value = resultDto.tableData;
                                            emits("sendStorageFileInterface", resultDto.storageFileDto);
                                        } else {
                                            infoLevel.value = MessageConstants.LEVEL_WARNING;
                                            messageType.value = MessageConstants.VIEW_OK;
                                            title.value = "csvをアップロードできませんでした";
                                            message.value = resultDto.message;
                                            return;
                                        }
                                    })
                                    .catch((error) => { alert(error); });
                            }).catch((e) => {
                                infoLevel.value = MessageConstants.LEVEL_ERROR;
                                messageType.value = MessageConstants.VIEW_OK;

                                if (e instanceof AccessTokenNotFoundError) {
                                    // トークン保持ができていない場合
                                    title.value = "現在トークンが存在しません";
                                    message.value = e.message;
                                    return;
                                }
                                if (e instanceof TokenRefreshError) {
                                    // 取得に失敗している場合
                                    title.value = "有効期限まじかのトークンを再取得できませんでした";
                                    message.value = e.message;
                                    return;
                                }
                                title.value = "システムエラーが発生しました";
                                message.value = "システム管理者にお問い合わせください";
                                return;
                            });
                        }
                    }
                }
            } else {
                infoLevel.value = MessageConstants.LEVEL_ERROR;
                messageType.value = MessageConstants.VIEW_OK;
                title.value = "ファイルを読み取りできませんでした";
                message.value = "ファイル読み取りができませんでした。ファイルの指定が間違っている、あるいは壊れていないかお確かめください";
                return;
            }
        }
    }
}

function removeQuote(data: string): string {
    return data.replace('"', '').replace('"', '');
}

function recieveSubmit(button: string) {
    console.log(button); // 警告除け
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
