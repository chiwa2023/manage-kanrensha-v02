<script setup lang="ts">
import { ref, type Ref } from 'vue';
import { getErrorMessage, MessageConstants, MessageView, type LeastUserDtoInterface } from 'seijishikin-jp-normalize_common-tool';
import { GetTaskStackTraceCapsuleDto, type GetTaskStackTraceCapsuleDtoInterface } from '../../dto/task_plan/getTaskStackTraceCapsuleDto';
import { OneFileBlobResultDto, type OneFileBlobResultDtoInterface } from '../../dto/storage_file/oneFileBlobResultDto';
import getAuthorizedPromiseArea from '../../dto/login/getAuthorizedPromiseArea';
import RoutePathConstants from '../../../../routePathConstants';
import { AccessTokenNotFoundError, TokenRefreshError } from '../../dto/login/errors';

//props
const props = defineProps<{ taskPlanCode: number, taskYear: number, userDto: LeastUserDtoInterface }>();


// よく使う定数
const BLANK: string = "";
// const INIT_NUMBER: number = 0;
// const SEARCH_LIMIT: number = 20;
// const SERVER_STATUS_OK: number = 200;
const INQUIRE_FLG: boolean = false;
const ERR_MESS_ONLY: boolean = true;
const MESS_PAGE_NAME: string = "スタックトレース取得";
const INIT_CALLER: string = "no branch";

// メッセージボックス表示定数
const infoLevel: Ref<number> = ref(MessageConstants.LEVEL_NONE);
const messageType: Ref<number> = ref(MessageConstants.VIEW_NONE);
const caller: Ref<string> = ref(INIT_CALLER);
const message: Ref<string> = ref(BLANK);

// back側アクセス
const urlBack: string = RoutePathConstants.DOMAIN + RoutePathConstants.BASE_PATH;

const resultDto: Ref<OneFileBlobResultDtoInterface> = ref(new OneFileBlobResultDto());

/**
 * ファイルダウンロード
 */
async function onDownload() {
    const capsuleDto: GetTaskStackTraceCapsuleDtoInterface = new GetTaskStackTraceCapsuleDto();
    capsuleDto.taskPlanCode = props.taskPlanCode;
    capsuleDto.taskYear = props.taskYear;
    capsuleDto.userDto = props.userDto;


    getAuthorizedPromiseArea().then(token => {
        const url = urlBack + "/stack-trace/get-by-code";
        const method = "POST";
        const body = JSON.stringify(capsuleDto);
        const headers = {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'X-AUTH-TOKEN': 'Bearer ' + token
        };
        fetch(url, { method, headers, body })
            .then(async (response) => {

                resultDto.value = await response.json();

                if (resultDto.value.isFailure) {
                    // メッセージ
                    infoLevel.value = MessageConstants.LEVEL_WARNING;
                    messageType.value = MessageConstants.VIEW_OK;
                    message.value = getErrorMessage(resultDto.value.message, ERR_MESS_ONLY);
                } else {
                    //Base64文字列からMIMEType不明(=application/octet-stream)Blobに変換
                    const bin: string = window.atob(resultDto.value.fileContentBase64);
                    const buffer = new Uint8Array(bin.length);
                    for (var i = 0; i < bin.length; i++) {
                        buffer[i] = bin.charCodeAt(i);
                    }
                    const blob: Blob = new Blob([buffer.buffer], { "type": "application/octet-stream", });

                    //リンクを作成して強制発火
                    let anchorElement = document.createElement('a');
                    anchorElement.href = URL.createObjectURL(blob);
                    anchorElement.download = resultDto.value.fileName;
                    document.body.appendChild(anchorElement);
                    anchorElement.click();
                }
            })
            .catch((e) => {
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
    });

    // if (props.saveStorageResultDto.shoshouId === 0) {
    //     //書証Dtoが初期値のままの場合は即離脱
    //     alert("呼び出すファイルを指定していません");
    //     return;
    // }
    // //実接続 TODO 本番環境で使用する
    // const url = "http://localhost:9080/get-file-binary";
    // const method = "POST";
    // const body = JSON.stringify(props.saveStorageResultDto);
    // const headers = {
    //     'Accept': 'application/json',
    //     'Content-Type': 'application/json'
    // };

    // fetch(url, { method, headers, body })
    //     .then(async (response) => {

    //         //データを取得
    //         if (200 === response.status) {
    //             //レスポンスの取得
    //             const oneFileBlobDto: OneFileBlobInterface = await response.json();

    //             //Base64文字列からMIMEType不明(=application/octet-stream)Blobに変換
    //             const bin: string = atob(oneFileBlobDto.fileContentBase64);
    //             const buffer = new Uint8Array(bin.length);
    //             for (var i = 0; i < bin.length; i++) {
    //                 buffer[i] = bin.charCodeAt(i);
    //             }
    //             const blob: Blob = new Blob([buffer.buffer], { "type": "application/octet-stream", });

    //             //リンクを作成して強制発火
    //             let anchorElement = document.createElement('a');
    //             anchorElement.href = URL.createObjectURL(blob);
    //             anchorElement.download = oneFileBlobDto.fileName;
    //             document.body.appendChild(anchorElement);
    //             anchorElement.click();
    //         }

    //     })
    //     .catch((error) => { alert(error); });

    // // ダミーデータ
    // const oneFileBlobDto: Ref<OneFileBlobDtoInterface> = ref(new OneFileBlobDto());
    // oneFileBlobDto.value.fileName = "1234.txt";
    // oneFileBlobDto.value.fileContentBase64 = "1234";
    // const blob: Blob = new Blob([oneFileBlobDto.value.fileContentBase64], { "type": "text/plain'", });

    // //リンクを作成して強制発火
    // let anchorElement = document.createElement('a');
    // anchorElement.href = URL.createObjectURL(blob);
    // anchorElement.download = oneFileBlobDto.value.fileName;
    // document.body.appendChild(anchorElement);
    // anchorElement.click();
}

function recieveSubmit() {
    infoLevel.value = 0;
    messageType.value = 0;
}
</script>
<template>
    <button @click="onDownload">stack trace</button>

    <!-- メッセージ表示    -->
    <div class="overMessage" v-if="messageType !== MessageConstants.VIEW_NONE">
        <MessageView :info-level="infoLevel" :message-type="messageType" :title="MESS_PAGE_NAME" :message="message"
            :caller="caller" @send-submit="recieveSubmit">
        </MessageView>
    </div>

</template>
<style scoped></style>
