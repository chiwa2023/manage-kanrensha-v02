<script setup lang="ts">
import { computed, ref, type ComputedRef, type Ref } from 'vue';
import { getLoginUser } from '../../utils/getLoginUser';
import MockManagerInfo from '../../../test/common/user_info/MockManagerInfo.vue';
import type { LeastUserDtoInterface } from '../../dto/user/leastUserDto';
import { RegistDataByCsvFileCapsuleDto, type RegistDataByCsvFileCapsuleDtoInterface } from '../../dto/storage_file/registDataByCsvFileCapsuleDto';
import { FrameworkCapsuleDto, type FrameworkCapsuleDtoInterface } from 'seijishikin-jp-normalize_common-tool';
import type { StorageFileDtoInterface } from '../../dto/storage_file/storageFileDto';
import MockReadCsv from '../../../test/common/read_csv/MockReadCsv.vue';
import EditWkTblHistorySeijidantai from '../../common/wktbl_edit_history/EditWkTblHistorySeijidantai.vue';

// ユーザ呼び出し
const userDto: Ref<LeastUserDtoInterface> = ref(getLoginUser());

// back側アクセス
// const urlBack: string = RoutePathConstants.DOMAIN_BACK + RoutePathConstants.PATH_BACK;

// サンプル表示
const templateViewButtonText: ComputedRef<String> = computed(() => isVisibleTemplate.value ? "CSVサンプルを隠す" : "CSVサンプルを表示する");
const isVisibleTemplate: Ref<boolean> = ref(false);
function viewSample() {
    isVisibleTemplate.value = !isVisibleTemplate.value;
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

function onCancel() {
    history.back();
}

function onSave() {
    alert("実行");
    // getAuthorizedPromiseArea().then(token => {
    //     const url = urlBack + "/regist-bulk-history/retry-seijidantai";
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
    alert("バッチ処理");
    // getAuthorizedPromiseArea().then(token => {
    //     const url = urlBack + "/regist-bulk-history/execute-seijidantai";
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
    //         })
    //         .catch((error) => { alert(error); });
    // });
}

</script>
<template>
    <!-- 管理者メニュー兼チェック -->
    <MockManagerInfo :user-dto="userDto"></MockManagerInfo>

    <h1>関連者政治団体履歴一括登録</h1>

    <!-- csv読み出し10行 -->
    <MockReadCsv @send-storage-file-interface="recieveStorageFileInterface" :user-dto="userDto"></MockReadCsv>

    <div class="one-line">
        <button @click="onBatchByFile">頭出ししたcsvファイルで一括処理</button>
    </div>
    <div class="one-line">
        <button @click="viewSample">{{ templateViewButtonText }}</button>
    </div>

    <div class="one-line">
        <div v-if="isVisibleTemplate">
            ヘッダ必須。1行目は読み飛ばすので、ないと1行目が登録されません<br>
            最初の1列は不要です。(ファイル内は4列)
            <table>
                <tbody>
                    <tr>
                        <th class="hojo">要件</th>
                        <th>政治団体名称</th>
                        <th>政治団体住所</th>
                        <th>政治団体代表者</th>
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
                        <td>ちゃらんぽらん政治団体</td>
                        <td>和歌山県実在市山麓町</td>
                        <td>代表者　太郎</td>
                        <td>123-ABCD-8E01</td>
                    </tr>
                    <tr>
                        <th class="hojo">データ例</th>
                        <td>いいかげん政治団体</td>
                        <td>宮崎県架空市湖畔町</td>
                        <td></td>
                        <td>234-BCDE-9F12</td>
                    </tr>
                </tbody>
            </table>
            <a href="sample_csv/sample_bulk_history_poli_org.csv">上記内容サンプルcsvをダウンロード</a>
        </div>
    </div>

    <!-- 登録結果と編集 -->
    <EditWkTblHistorySeijidantai :user-dto="userDto"></EditWkTblHistorySeijidantai>

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
}

th.hojo {
    background-color: lightgray;
}

th.explain {
    background-color: lightcyan;
}
</style>
