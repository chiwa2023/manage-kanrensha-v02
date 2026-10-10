<script setup lang="ts">
import { ref, type Ref } from 'vue';
import { UploadContentCapsuleDto, type UploadContentCapsuleDtoInterface } from '../../dto/storage_file/uploadContentCapsuleDto';
import { getErrorMessage, MessageConstants, MessageView, type LeastUserDtoInterface } from 'seijishikin-jp-normalize_common-tool';
import getAuthorizedPromiseArea from '../../dto/login/getAuthorizedPromiseArea';
import RoutePathConstants from '../../../../routePathConstants';
import { AccessTokenNotFoundError, TokenRefreshError } from '../../dto/login/errors';
import type { UploadFileResultDtoInterface } from '../../dto/storage_file/uploadFileResultDto';

//props,emit
const props = defineProps<{ userDto: LeastUserDtoInterface }>()
const emits = defineEmits(["sendStorageFileInterface"]);

// back側アクセス
const urlBack: string = RoutePathConstants.DOMAIN + RoutePathConstants.BASE_PATH;

// よく使う定数
const BLANK: string = "";
// const INIT_NUMBER: number = 0;
//const SEARCH_LIMIT: number = 20;
// const SERVER_STATUS_OK: number = 200;
const SERVER_STATUS_ERROR: number = 400;
const INQUIRE_FLG: boolean = false;
const ERR_MESS_ONLY: boolean = true;
const MESS_PAGE_NAME: string = "CSVファイル読み取り";
const INIT_CALLER: string = "no branch";

// メッセージボックス表示定数
const infoLevel: Ref<number> = ref(MessageConstants.LEVEL_NONE);
const messageType: Ref<number> = ref(MessageConstants.VIEW_NONE);
const caller: Ref<string> = ref(INIT_CALLER);
const message: Ref<string> = ref(BLANK);

// 文字コード
const capsuleDto: Ref<UploadContentCapsuleDtoInterface> = ref(new UploadContentCapsuleDto());
capsuleDto.value.uploadFileDto.charset = "UTF-8";

//ファイル指定ダイアログ
const selectFileInput: Ref<HTMLInputElement | undefined> = ref<HTMLInputElement>();

const fileName: Ref<string> = ref(BLANK);
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
                                const url = urlBack + "/file/upload";
                                const method = "POST";
                                const body = JSON.stringify(capsuleDto.value);
                                const headers = {
                                    'Accept': 'application/json',
                                    'Content-Type': 'application/json',
                                    'X-AUTH-TOKEN': 'Bearer ' + token
                                };
                                fetch(url, { method, headers, body })
                                    .then(async (response) => {
                                        const resultDto: UploadFileResultDtoInterface = await response.json();

                                        if (response.status > SERVER_STATUS_ERROR) {
                                            message.value = getErrorMessage(resultDto.message, ERR_MESS_ONLY);
                                            infoLevel.value = MessageConstants.LEVEL_ERROR;
                                            messageType.value = MessageConstants.VIEW_OK;
                                            return;
                                        }

                                        if (resultDto.isFailure) {
                                            infoLevel.value = MessageConstants.LEVEL_WARNING;
                                            messageType.value = MessageConstants.VIEW_OK;
                                            return;
                                        } else {
                                            fileName.value = file.name;
                                            emits("sendStorageFileInterface", resultDto.storageFileDto);
                                        }
                                    })
                                    .catch((error) => {
                                        message.value = getErrorMessage(error, ERR_MESS_ONLY);
                                        infoLevel.value = MessageConstants.LEVEL_ERROR;
                                        messageType.value = MessageConstants.VIEW_OK;
                                        return;
                                    });
                            }).catch((e) => {
                                infoLevel.value = MessageConstants.LEVEL_ERROR;
                                messageType.value = MessageConstants.VIEW_OK;

                                // トークン保持または取得に失敗している場合
                                if (e instanceof AccessTokenNotFoundError || e instanceof TokenRefreshError) {
                                    message.value = e.message;
                                    return;
                                }

                                message.value = getErrorMessage(e, INQUIRE_FLG);
                                return;
                            });
                        }
                    }
                } else {
                    infoLevel.value = MessageConstants.LEVEL_ERROR;
                    messageType.value = MessageConstants.VIEW_OK;
                    message.value = "ファイル読み取りができませんでした。ファイルの指定が間違っている、あるいは壊れていないかお確かめください";
                    return;
                }
            } else {
                infoLevel.value = MessageConstants.LEVEL_ERROR;
                messageType.value = MessageConstants.VIEW_OK;
                message.value = "ファイル読み取りができませんでした。ファイルの指定が間違っている、あるいは壊れていないかお確かめください";
                return;
            }
        }
    }
}


function recieveSubmit() {
    infoLevel.value = 0;
    messageType.value = 0;
}
</script>
<template>
    <div class="one-line">
        <div class="left-area">
            アップロードファイルの指定<br>
        </div>
        <div class="right-area">
            <input ref="selectFileInput" type="file" accept=".csv" @change="readTextFile" style="display:none;">
            <button @click="onReadButton">アップロード</button>
            ファイル名{{ fileName }}
        </div>
    </div>


    <!-- メッセージ表示    -->
    <div class="overMessage" v-if="messageType !== MessageConstants.VIEW_NONE">
        <MessageView :info-level="infoLevel" :message-type="messageType" :title="MESS_PAGE_NAME" :message="message"
            :caller="caller" @send-submit="recieveSubmit">
        </MessageView>
    </div>

</template>
<style scoped></style>
