<script setup lang="ts">
import { computed, ref, type ComputedRef, type Ref } from 'vue';
import type { LeastUserDtoInterface } from '../../dto/user/leastUserDto';
import MockManagerInfo from '../../../test/common/user_info/MockManagerInfo.vue';
import type { StorageFileDtoInterface } from '../../dto/storage_file/storageFileDto';
import { RegistDataByCsvFileCapsuleDto, type RegistDataByCsvFileCapsuleDtoInterface } from '../../dto/storage_file/registDataByCsvFileCapsuleDto';
import { FrameworkCapsuleDto, type FrameworkCapsuleDtoInterface } from '../../dto/frameworkCapsuleDto';
import EditWkTblHistoryKigyouDt from '../../common/wktbl_edit_history/EditWkTblHistoryKigyouDt.vue';
import { getLoginUser } from '../../utils/getLoginUser';
import MockReadCsv from '../../../test/common/read_csv/MockReadCsv.vue';

// back側アクセス
// const urlBack: string = RoutePathConstants.DOMAIN_BACK + RoutePathConstants.PATH_BACK;

// ユーザ呼び出し
const userDto: Ref<LeastUserDtoInterface> = ref(getLoginUser());


// サンプル表示
const templateViewButtonText: ComputedRef<String> = computed(() => isVisibleTemplate.value ? "CSVサンプルを隠す" : "CSVサンプルを表示する");
const isVisibleTemplate: Ref<boolean> = ref(false);
function viewSample() {
    isVisibleTemplate.value = !isVisibleTemplate.value;
}


function onCancel() {
    history.back();
}

function onSave() {
    alert("実行");
    // getAuthorizedPromiseArea().then(token => {
    //     const url = urlBack + "/regist-bulk-history/retry-kigyou-dt";
    //     const method = "POST";
    //     const body = JSON.stringify(retryCapsuleDto.value);
    //     const headers = {
    //         'Accept': 'application/json',
    //         'Content-Type': 'application/json',
    //         'X-AUTH-TOKEN': 'Bearer ' + token
    //     };
    //     fetch(url, { method, headers, body })
    //         .then(async (response) => {
    //             const resultDto: FrameworkMessageAndResultInterface = await response.json();
    //             alert(resultDto.message);
    //         })
    //         .catch((error) => { alert(error); });
    // });
}

function onBatchByFile() {
    alert("実行");
    // getAuthorizedPromiseArea().then(token => {
    //     const url = urlBack + "/regist-bulk-history/execute-kigyou-dt";
    //     const method = "POST";
    //     const body = JSON.stringify(capsuleDto.value);
    //     const headers = {
    //         'Accept': 'application/json',
    //         'Content-Type': 'application/json',
    //         'X-AUTH-TOKEN': 'Bearer ' + token
    //     };
    //     fetch(url, { method, headers, body })
    //         .then(async (response) => {
    //             const resultDto: FrameworkMessageAndResultInterface = await response.json();
    //             alert(resultDto.message);
    //             // 処理が成功したら再登録できないようにアップロードファイル情報を初期化
    //             if (response.status === 200) {
    //                 capsuleDto.value.storageFileDto = new StorageFileDto();
    //             }
    //         })
    //         .catch((error) => { alert(error); });
    // });
}

// ファイルからバッチ起動条件
const capsuleDto: Ref<RegistDataByCsvFileCapsuleDtoInterface> = ref(new RegistDataByCsvFileCapsuleDto());
capsuleDto.value.userDto = userDto.value;

// 再処理起動条件(ユーザ)
const retryCapsuleDto: Ref<FrameworkCapsuleDtoInterface> = ref(new FrameworkCapsuleDto());
retryCapsuleDto.value.userDto = userDto.value;

// ファイル保全情報受信
function recieveStorageFileInterface(storageFileDto: StorageFileDtoInterface) {
    capsuleDto.value.storageFileDto = storageFileDto;
}

</script>
<template>
    <!-- 管理者メニュー兼チェック -->
    <MockManagerInfo :user-dto="userDto"></MockManagerInfo>

    <h1>関連者企業・団体履歴一括登録</h1>

    <!-- csv読み出し10行  -->
    <MockReadCsv @send-storage-file-interface="recieveStorageFileInterface" :user-dto="userDto"></MockReadCsv>


    <div class="one-line">
        <button @click="onBatchByFile">頭出ししたcsvファイルで一括処理</button>
    </div>

    <div class="one-line">
        <button @click="viewSample">{{ templateViewButtonText }}</button><br>
    </div>
    <div class="one-line">
        <div v-if="isVisibleTemplate">
            ヘッダ必須。1行目は読み飛ばすので、ないと1行目が登録されません<br>
            最初の1列は不要です。(ファイル内は4列)
            <table>
                <tbody>
                    <tr>
                        <th class="hojo">要件</th>
                        <th>団体名称</th>
                        <th>団体住所</th>
                        <th>団体代表者氏名</th>
                        <th>関連者コード</th>
                    </tr>
                    <tr>
                        <th class="hojo">説明</th>
                        <th class="explain">必須</th>
                        <th class="explain">必須</th>
                        <th class="explain">任意<br>(項目省略不可)</th>
                        <th class="explain">必須</th>
                    </tr>
                    <tr>
                        <th class="hojo">データ例</th>
                        <td>ふんだくり企業</td>
                        <td>和歌山県実在市山麓町</td>
                        <td>代表者　太郎</td>
                        <td>1-234-55678</td>
                    </tr>
                    <tr>
                        <th class="hojo">データ例</th>
                        <td>超元素製造組合</td>
                        <td>宮崎県架空市湖畔町</td>
                        <td></td>
                        <td>2-345-6789</td>
                    </tr>
                </tbody>
            </table>
            <a href="sample_csv/sample_bulk_history_kigyou-dt.csv">上記内容サンプルcsvをダウンロード</a>
        </div>
    </div>

    <!-- 登録結果と編集 -->
    <EditWkTblHistoryKigyouDt :user-dto="userDto"></EditWkTblHistoryKigyouDt>
    
    <div class="footer">
        <button @click="onCancel" class="footer-button">キャンセル</button>
        <button @click="onSave" class="footer-button left-space">送信</button>
    </div>

</template>

<style scoped>
th.hojo {
    background-color: lightgray;
}

th.explain {
    background-color: lightcyan;
}
</style>
