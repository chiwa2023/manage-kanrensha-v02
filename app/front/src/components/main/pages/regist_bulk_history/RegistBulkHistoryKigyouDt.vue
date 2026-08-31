<script setup lang="ts">
import { computed, onMounted, ref, type ComputedRef, type Ref } from 'vue';
import { StorageFileDto, type StorageFileDtoInterface } from '../../dto/storage_file/storageFileDto';
import { RegistDataByCsvFileCapsuleDto, type RegistDataByCsvFileCapsuleDtoInterface } from '../../dto/storage_file/registDataByCsvFileCapsuleDto';
import { FrameworkCapsuleDto, getErrorMessage, MessageConstants, MessageView, type FrameworkCapsuleDtoInterface, type FrameworkMessageAndResultDtoInterface, type LeastUserDtoInterface } from 'seijishikin-jp-normalize_common-tool';
import EditWkTblHistoryKigyouDt from '../../common/wktbl_edit_history/EditWkTblHistoryKigyouDt.vue';
import { getLoginUser } from '../../utils/getLoginUser';
import ManagerInfo from '../../common/user_info/ManagerInfo.vue';
import ReadCsv from '../../common/read_csv/ReadCsv.vue';
import getAuthorizedPromiseArea from '../../dto/login/getAuthorizedPromiseArea';
import RoutePathConstants from '../../../../routePathConstants';
import { AccessTokenNotFoundError, TokenRefreshError } from '../../dto/login/errors';
import { nextTransferPassStore } from '../../stores/nextTransferPass';
import { useRoute } from 'vue-router';
import router from '../../../../router';

// よく使う定数
const BLANK: string = "";
const INIT_NUMBER: number = 0;
const INIT_BOOLEAN: boolean = false;
//const SEARCH_LIMIT: number = 20;
// const SERVER_STATUS_OK: number = 200;
// const SERVER_STATUS_ERROR: number = 400;
const INQUIRE_FLG: boolean = false;
const ERR_MESS_ONLY: boolean = true;
const MESS_PAGE_NAME: string = "関連者企業団体履歴一括処理再処理";
const INIT_CALLER: string = "no branch";

// メッセージボックス表示定数
const infoLevel: Ref<number> = ref(MessageConstants.LEVEL_NONE);
const messageType: Ref<number> = ref(MessageConstants.VIEW_NONE);
const caller: Ref<string> = ref(INIT_CALLER);
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

function onCancel() {
    history.back();
}

function onSave() {
    getAuthorizedPromiseArea().then(token => {
        const url = urlBack + "/regist-bulk-history/retry-kigyou-dt";
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
                    return;
                } else {
                    infoLevel.value = MessageConstants.LEVEL_INFO;
                    messageType.value = MessageConstants.VIEW_TOAST;
                    message.value = resultDto.message;
                    return;
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

function onBatchByFile() {
    getAuthorizedPromiseArea().then(token => {
        const url = urlBack + "/regist-bulk-history/execute-kigyou-dt";
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


// ファイル保全情報受信
function recieveStorageFileInterface(storageFileDto: StorageFileDtoInterface) {
    capsuleDto.value.storageFileDto = storageFileDto;
}

function recieveSubmit() {
    infoLevel.value = 0;
    messageType.value = 0;
}
</script>
<template>
    <!-- 管理者メニュー兼チェック -->
    <ManagerInfo :user-dto="userDto"></ManagerInfo>

    <h1>関連者企業・団体履歴一括登録</h1>

    <!-- csv読み出し10行 -->
    <ReadCsv @send-storage-file-interface="recieveStorageFileInterface" :user-dto="userDto"></ReadCsv>

    <div class="one-line">
        <button @click="onBatchByFile">頭出ししたcsvファイルで一括処理</button>
    </div>

    <div class="one-line">
        <button @click="viewSample">{{ templateViewButtonText }}</button><br>
    </div>
    <div class="one-line">
        <div v-if="isVisibleTemplate">
            ヘッダ必須。1行目は読み飛ばすので、ないと1行目が登録されません<br>
            ファイル内は5列です。
            <table>
                <tbody>
                    <tr>
                        <th class="hojo">要件</th>
                        <th>団体名称</th>
                        <th>団体住所</th>
                        <th>団体代表者氏名</th>
                        <th>関連者番号(企業・団体)</th>
                        <th>代表者関連者番号</th>
                    </tr>
                    <tr>
                        <th class="hojo">説明</th>
                        <th class="explain">必須</th>
                        <th class="explain">必須</th>
                        <th class="explain">任意<br>(項目省略不可)</th>
                        <th class="explain">必須</th>
                        <th class="explain">任意<br>(項目省略不可)</th>
                    </tr>
                    <tr>
                        <th class="hojo">データ例</th>
                        <td>ぼったくり企業</td>
                        <td>和歌山県架空市実在町</td>
                        <td>代表者　太郎</td>
                        <td>12-345-ABCCDEF</td>
                        <td>8-7654-1</td>
                    </tr>
                    <tr>
                        <th class="hojo">データ例(同一行許容)<br>・システム側で処理停止</th>
                        <td>ぼったくり企業</td>
                        <td>和歌山県架空市実在町</td>
                        <td>代表者　太郎</td>
                        <td>12-345-ABCCDEF</td>
                        <td>8-7654-1</td>
                    </tr>
                    <tr>
                        <th class="hojo">データ例</th>
                        <td>ふんだくり企業</td>
                        <td>宮崎県架空市実在町</td>
                        <td>代表者　次郎</td>
                        <td>23-45657-QWERTY</td>
                        <td>8-7654-3</td>
                    </tr>
                    <tr>
                        <th class="hojo">データ例(最小入力例)</th>
                        <td>職業組合A</td>
                        <td>山形県架空市実在町</td>
                        <td></td>
                        <td>12-33-44-55</td>
                        <td></td>
                    </tr>
                    <tr>
                        <th class="hojo">データ例</th>
                        <td>職業組合B</td>
                        <td>山梨県架空市実在町</td>
                        <td>代表者　四郎</td>
                        <td>99-88-77-66</td>
                        <td>8-7654-5</td>
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

    <!-- メッセージ表示    -->
    <div class="overMessage" v-if="messageType !== MessageConstants.VIEW_NONE">
        <MessageView :info-level="infoLevel" :message-type="messageType" :title="MESS_PAGE_NAME" :message="message"
            :caller="caller" @send-submit="recieveSubmit">
        </MessageView>
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
