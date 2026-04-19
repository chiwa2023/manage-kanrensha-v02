<script setup lang="ts">
import { computed, ref, type ComputedRef, type Ref } from 'vue';
import { getLoginUser } from '../../utils/getLoginUser';
import { RegistDataByCsvFileCapsuleDto, type RegistDataByCsvFileCapsuleDtoInterface } from '../../dto/storage_file/registDataByCsvFileCapsuleDto';
import { FrameworkCapsuleDto, type FrameworkCapsuleDtoInterface, type LeastUserDtoInterface } from 'seijishikin-jp-normalize_common-tool';
import type { StorageFileDtoInterface } from '../../dto/storage_file/storageFileDto';
import MockReadCsv from '../../../test/common/read_csv/MockReadCsv.vue';
import EditWkTblHistorySeijidantai from '../../common/wktbl_edit_history/EditWkTblHistorySeijidantai.vue';
import ManagerInfo from '../../common/user_info/ManagerInfo.vue';

const INIT_BOOLEAN: boolean = false;

// ユーザ呼び出し
const userDto: Ref<LeastUserDtoInterface> = ref(getLoginUser());

// back側アクセス
// const urlBack: string = RoutePathConstants.DOMAIN + RoutePathConstants.BASE_PATH;

// サンプル表示
const templateViewButtonText: ComputedRef<String> = computed(() => isVisibleTemplate.value ? "CSVサンプルを隠す" : "CSVサンプルを表示する");
const isVisibleTemplate: Ref<boolean> = ref(INIT_BOOLEAN);
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
    <ManagerInfo :user-dto="userDto"></ManagerInfo>

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
                        <th>名称</th>
                        <th>全住所</th>
                        <th>代表者名</th>
                        <th>関連者番号(政治団体)</th>
                        <th>代表者関連者コード</th>
                    </tr>
                    <tr>
                        <th class="hojo">説明</th>
                        <th class="explain">必須</th>
                        <th class="explain">必須</th>
                        <th class="explain">任意<br>(項目省略不可)</th>
                        <th class="explain">必須</th>
                        <th class="explain">任意</th>
                    </tr>
                    <tr>
                        <th class="hojo">データ例</th>
                        <td>ちゃらんぽらん団体</td>
                        <td>和歌山県架空市実在町</td>
                        <td>代表者　太郎</td>
                        <td>12-345-ABCCDEF</td>
                        <td>8-7654-1</td>
                    </tr>
                    <tr>
                        <th class="hojo">データ例(同一行許容)<br>・システム側で処理停止</th>
                        <td>ちゃらんぽらん団体</td>
                        <td>和歌山県架空市実在町</td>
                        <td>代表者　太郎</td>
                        <td>12-345-ABCCDEF</td>
                        <td>8-7654-2</td>
                    </tr>
                    <tr>
                        <th class="hojo">データ例</th>
                        <td>いいかげん政治団体</td>
                        <td>宮崎県架空市実在町</td>
                        <td>代表者　次郎</td>
                        <td>23-45657-QWERTY</td>
                        <td>8-7654-3</td>
                    </tr>
                    <tr>
                        <th class="hojo">データ例(最小入力例)</th>
                        <td>政治団体A</td>
                        <td>山形県架空市実在町</td>
                        <td></td>
                        <td>12-33-44-55</td>
                        <td></td>
                    </tr>
                    <tr>
                        <th class="hojo">データ例</th>
                        <td>政治団体B</td>
                        <td>山梨県架空市実在町</td>
                        <td>代表者　四郎</td>
                        <td>99-88-77-66</td>
                        <td>8-7654-5</td>
                    </tr>
                </tbody>
            </table>
            <a href="sample_csv/sample_bulk_history_seijidantai.csv">上記内容サンプルcsvをダウンロード</a>
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
