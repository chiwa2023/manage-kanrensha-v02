<script setup lang="ts">
import { ref, type Ref } from 'vue';
import type { LeastUserDtoInterface } from '../../dto/user/leastUserDto';
import { UploadContentCapsuleDto, type UploadContentCapsuleDtoInterface } from '../../dto/storage_file/uploadContentCapsuleDto';
import { LookAheadPublishXmlResultDto, type LookAheadPublishXmlResultDtoInterface } from '../../dto/storage_file/lookAheadPublishXmlResultDto';

//props,emit
const props = defineProps<{ userDto: LeastUserDtoInterface }>()
const emits = defineEmits(["sendStorageFileInterface"]);

// back側アクセス
//const urlBack: string = RoutePathConstants.DOMAIN_BACK + RoutePathConstants.PATH_BACK;

// 文字コード
const capsuleDto: Ref<UploadContentCapsuleDtoInterface> = ref(new UploadContentCapsuleDto());

//ファイル指定ダイアログ
const selectFileInput: Ref<HTMLInputElement | undefined> = ref<HTMLInputElement>();

// 処理結果
const resultDto: Ref<LookAheadPublishXmlResultDtoInterface> = ref(new LookAheadPublishXmlResultDto());

/**
* ファイル選択ダイアログを表示する
*/
function onReadButton() {
    if (selectFileInput.value !== undefined) {
        selectFileInput.value.click();
    }
}


/**
 * ファイル選択ダイアログを表示する
 */
function readXmlFile() {
    if (selectFileInput.value !== null) {
        if (selectFileInput.value !== undefined) {
            if (selectFileInput.value.files !== null && selectFileInput.value.files !== undefined) {
                const file: File | undefined = selectFileInput.value.files[0];
                if (file !== undefined) {
                    capsuleDto.value.uploadFileDto.fileName = file.name;
                    // ファイルをバイト取得
                    const reader: FileReader = new FileReader();
                    reader.readAsDataURL(file);
                    reader.onload = async () => {
                        if (reader.result !== null) {
                            capsuleDto.value.uploadFileDto.fileContent = String(reader.result);
                            // getAuthorizedPromiseArea().then(token => {
                            //     const url = urlBack + "/xml/look-ahead";
                            //     const method = "POST";
                            //     const body = JSON.stringify(capsuleDto.value);
                            //     const headers = {
                            //         'Accept': 'application/json',
                            //         'Content-Type': 'application/json',
                            //         'X-AUTH-TOKEN': 'Bearer ' + token
                            //     };
                            //     fetch(url, { method, headers, body })
                            //         .then(async (response) => {
                            //             if (response.status < 400) {
                            //                 resultDto.value = await response.json();
                            //                 if (response.status === 200) {
                            //                     //保存したファイル情報は親に渡す
                            //                     emits("sendStorageFileInterface", resultDto.value.storageFileDto);
                            //                 }
                            //                 alert(resultDto.value.message);

                            //             }
                            //         })
                            //         .catch((error) => { alert(error); });
                            // });
                        }
                    }
                }
            } else {
                alert("ユーザが取れない");
            }
        }
    }
}

</script>
<template>
    <h3>公式XMLファイル選択</h3>
    <div class="left-area">
        読取りファイルの指定<br>
    </div>
    <div class="right-area">
        <input ref="selectFileInput" type="file" accept=".xml" @change="readXmlFile" style="display:none;">
        <button @click="onReadButton">ファイルを指定して読み取り</button>
    </div>

    <div class="one-line">
        <div class="left-area">
            XMLの種類
        </div>
        <div class="right-area">
            {{ resultDto.app }}
        </div>
    </div>

    <div class="one-line">
        <div class="left-area">
            アプリバージョン
        </div>
        <div class="right-area">
            {{ resultDto.version }}
        </div>
    </div>

    <div class="left-area">
        <div class="one-line">
            政治団体<br>
        </div>
        <div class="right-area">
            {{ resultDto.dantaiName }}({{ resultDto.houkokuNen }})
        </div>
    </div>

</template>
<style scoped></style>
