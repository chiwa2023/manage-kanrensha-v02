<script setup lang="ts">
import { computed, onMounted, ref, type ComputedRef, type Ref } from 'vue';
import ReadCsv from '../../common/read_csv/ReadCsv.vue';
import { StorageFileDto, type StorageFileDtoInterface } from '../../dto/storage_file/storageFileDto';
import { RegistDataByCsvFileCapsuleDto, type RegistDataByCsvFileCapsuleDtoInterface } from '../../dto/storage_file/registDataByCsvFileCapsuleDto';
import RoutePathConstants from '../../../../routePathConstants';
import { FrameworkCapsuleDto, MessageConstants, MessageView, type FrameworkCapsuleDtoInterface, type FrameworkMessageAndResultDtoInterface, type LeastUserDtoInterface } from 'seijishikin-jp-normalize_common-tool';
import { getLoginUser } from '../../utils/getLoginUser';
import getAuthorizedPromiseArea from '../../dto/login/getAuthorizedPromiseArea';
import { AccessTokenNotFoundError, TokenRefreshError } from '../../dto/login/errors';
import ManagerInfo from '../../common/user_info/ManagerInfo.vue';
import EditWkTblCombineOrg from '../../common/wktbl_combine/EditWkTblCombineOrg.vue';
import { useRoute } from 'vue-router';
import { nextTransferPassStore } from '../../stores/nextTransferPass';
import router from '../../../../router';

// よく使う定数
const BLANK: string = "";
const INIT_NUMBER: number = 0;
// const INIT_BOOLEAN: boolean = false;
//const SEARCH_LIMIT: number = 20;
// const SERVER_STATUS_OK: number = 200;
// const SERVER_STATUS_ERROR: number = 400;

// メッセージボックス表示定数
const infoLevel: Ref<number> = ref(MessageConstants.LEVEL_NONE);
const messageType: Ref<number> = ref(MessageConstants.VIEW_NONE);
const title: Ref<string> = ref(BLANK);
const message: Ref<string> = ref(BLANK);

// back側アクセス
const urlBack: string = RoutePathConstants.DOMAIN + RoutePathConstants.BASE_PATH;

// ユーザ呼び出し
const userDto: Ref<LeastUserDtoInterface> = ref(getLoginUser());

const route = useRoute();
onMounted(() => {
    // 直リンク(パスチェックあり)を許容ロジック
    if (INIT_NUMBER === userDto.value.userPersonId) {
        const passStore = nextTransferPassStore()
        passStore.fullPath = route.fullPath;
        router.push(RoutePathConstants.PAGE_LOGIN);
        return;
    }
});

// サンプル表示
const templateViewButtonText: ComputedRef<String> = computed(() => isVisibleTemplate.value ? "CSVサンプルを隠す" : "CSVサンプルを表示する");
const isVisibleTemplate: Ref<boolean> = ref(false);
function viewSample() {
    isVisibleTemplate.value = !isVisibleTemplate.value;
}

const capsuleDto: Ref<RegistDataByCsvFileCapsuleDtoInterface> = ref(new RegistDataByCsvFileCapsuleDto());
capsuleDto.value.userDto = userDto.value;


// 再処理起動条件(ユーザ)
const retryCapsuleDto: Ref<FrameworkCapsuleDtoInterface> = ref(new FrameworkCapsuleDto());
retryCapsuleDto.value.userDto = userDto.value;


function onCancel() {
    history.back();
}
function onSave() {

    title.value = "個人－企業団体履歴一括処理再処理";
    getAuthorizedPromiseArea().then(token => {
        const url = urlBack + "/regist-combine/retry";
        const method = "POST";
        const body = JSON.stringify(retryCapsuleDto.value);
        const headers = {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'X-AUTH-TOKEN': 'Bearer ' + token
        };
        fetch(url, { method, headers, body })
            .then(async (response) => {
                const resultDto: FrameworkMessageAndResultDtoInterface = await response.json();
                if (resultDto.isFailure) {
                    infoLevel.value = MessageConstants.LEVEL_ERROR;
                    messageType.value = MessageConstants.VIEW_OK;
                    message.value = resultDto.message;
                    return;
                } else {
                    infoLevel.value = MessageConstants.LEVEL_INFO;
                    messageType.value = MessageConstants.VIEW_TOAST;
                    message.value = resultDto.message;
                    return;
                }
            })
            .catch((error) => {
                alert(error);
                infoLevel.value = MessageConstants.LEVEL_ERROR;
                messageType.value = MessageConstants.VIEW_OK;
                message.value = "システム管理者にお問い合わせください";
                return;

            });
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

function onBatchByFile() {

    title.value = "個人－企業団体紐づけCSV一括登録";
    getAuthorizedPromiseArea().then(token => {
        const url = urlBack + "/regist-combine/execute-kigyou-dt";
        const method = "POST";
        const body = JSON.stringify(capsuleDto.value);
        const headers = {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'X-AUTH-TOKEN': 'Bearer ' + token
        };
        fetch(url, { method, headers, body })
            .then(async (response) => {
                const resultDto: FrameworkMessageAndResultDtoInterface = await response.json();
                message.value = resultDto.message;
                // 処理が成功したら再登録できないようにアップロードファイル情報を初期化
                if (resultDto.isFailure) {
                    infoLevel.value = MessageConstants.LEVEL_WARNING;
                    messageType.value = MessageConstants.VIEW_OK;
                } else {
                    capsuleDto.value.storageFileDto = new StorageFileDto();
                    infoLevel.value = MessageConstants.LEVEL_INFO;
                    messageType.value = MessageConstants.VIEW_TOAST;
                }
            })
            .catch((error) => {
                alert(error);
                infoLevel.value = MessageConstants.LEVEL_ERROR;
                messageType.value = MessageConstants.VIEW_OK;
                message.value = "システム管理者にお問い合わせください";
                return;
            });
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

// ファイル保全情報受信
function recieveStorageFileInterface(storageFileDto: StorageFileDtoInterface) {
    capsuleDto.value.storageFileDto = storageFileDto;
}

function recieveSubmit(button: string) {
    console.log(button); // 警告除け
    infoLevel.value = 0;
    messageType.value = 0;
}
</script>
<template>
    <!-- 管理者メニュー兼チェック -->
    <ManagerInfo :user-dto="userDto"></ManagerInfo>

    <h1>関連者個人－企業／団体紐づけ登録</h1>

    <!-- csv読み出し10行-->
    <ReadCsv :user-dto="userDto" @send-storage-file-interface="recieveStorageFileInterface"></ReadCsv>

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
                        <th>個人関連者コード</th>
                        <th>個人名称</th>
                        <th>企業・団体関連者コード</th>
                        <th>企業・団体名称</th>
                        <th>紐づけ開始年</th>
                        <th>紐づけ終了年</th>
                    </tr>
                    <tr>
                        <th class="hojo">説明</th>
                        <th class="explain">必須</th>
                        <th class="explain">必須</th>
                        <th class="explain">必須</th>
                        <th class="explain">必須</th>
                        <th class="explain">任意</th>
                        <th class="explain">任意</th>
                    </tr>
                    <tr>
                        <th class="hojo">データ例</th>
                        <td>12-34567-8901-2345-67890</td>
                        <td>迂回献金　太郎</td>
                        <td>1-2345-67-890123-4567890</td>
                        <td>ふんだくり企業</td>
                        <td>2021</td>
                        <td>2024</td>
                    </tr>
                </tbody>
            </table>
            <a href="sample_csv/sample_combine_kigyou_dt.csv">上記内容サンプルcsvをダウンロード</a>
        </div>
    </div>

    <!-- 登録結果と編集 -->
    <EditWkTblCombineOrg org-type="kigyou-dt" :user-dto="userDto"></EditWkTblCombineOrg>

    <div class="footer">
        <button @click="onCancel" class="footer-button">キャンセル</button>
        <button @click="onSave" class="footer-button left-space">送信</button>
    </div>

    <!-- メッセージ表示 -->
    <div class="overMessage" v-if="messageType !== MessageConstants.VIEW_NONE">
        <MessageView :info-level="infoLevel" :message-type="messageType" :title="title" :message="message"
            @send-submit="recieveSubmit">
        </MessageView>
    </div>

</template>
<style scoped>
:root {
    --cell_width: 200 px;
}

table {
    border-style: solid;
    border-width: 1px;
}

table.std {
    border-style: solid;
    border-width: 1px;
    width: calc(200px * 26);
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
    width: --cell_width px;
}

th.explain {
    background-color: lightcyan;
    width: --cell_width px;
}
</style>
