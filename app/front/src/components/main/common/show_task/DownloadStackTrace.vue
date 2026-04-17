<script setup lang="ts">
import { ref, type Ref } from 'vue';
// import OneFileBlobInterface from '../../../dto/storage_file/oneFileBlobDto';
// import OneFileBlobDto from '../../../dto/storage_file/oneFileBlobDto';
import type { LeastUserDtoInterface } from 'seijishikin-jp-normalize_common-tool';
import { GetTaskStackTraceCapsuleDto, type GetTaskStackTraceCapsuleDtoInterface } from '../../dto/task_plan/getTaskStackTraceCapsuleDto';
import { OneFileBlobDto, type OneFileBlobDtoInterface } from '../../dto/storage_file/oneFileBlobDto';
// import GetTaskStackTraceCapsuleDto from '../../../dto/task_plan/getTaskStackTraceCapsuleDto';
// import type UserPersonLeastInterface from '../../../dto/user/userPersonLeastDto';

//props
const props = defineProps<{ taskPlanCode: number, taskYear: number, userDto: LeastUserDtoInterface }>();

/**
 * ファイルダウンロード
 */
async function onDownload() {
    alert(props.taskPlanCode);
    const capsuleDto: GetTaskStackTraceCapsuleDtoInterface = new GetTaskStackTraceCapsuleDto();
    capsuleDto.taskPlanCode = props.taskPlanCode;
    capsuleDto.taskYear = props.taskYear;
    capsuleDto.userDto = props.userDto;

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

    //ダミーデータ
    const oneFileBlobDto: Ref<OneFileBlobDtoInterface> = ref(new OneFileBlobDto());
    oneFileBlobDto.value.fileName = "1234.txt";
    oneFileBlobDto.value.fileContentBase64 = "1234";

    const blob: Blob = new Blob([oneFileBlobDto.value.fileContentBase64], { "type": "text/plain'", });

    //リンクを作成して強制発火
    let anchorElement = document.createElement('a');
    anchorElement.href = URL.createObjectURL(blob);
    anchorElement.download = oneFileBlobDto.value.fileName;
    document.body.appendChild(anchorElement);
    anchorElement.click();
}
</script>
<template>
    <button @click="onDownload">stack trace</button>
</template>
<style scoped></style>
