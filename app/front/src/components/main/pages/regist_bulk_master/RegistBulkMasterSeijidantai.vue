<script setup lang="ts">
import { computed, onMounted, ref, type ComputedRef, type Ref } from 'vue';
import { getLoginUser } from '../../utils/getLoginUser';
import { RegistDataByCsvFileCapsuleDto, type RegistDataByCsvFileCapsuleDtoInterface } from '../../dto/storage_file/registDataByCsvFileCapsuleDto';
import { FrameworkCapsuleDto, getErrorMessage, MessageConstants, MessageView, type FrameworkCapsuleDtoInterface, type FrameworkMessageAndResultDtoInterface, type LeastUserDtoInterface } from 'seijishikin-jp-normalize_common-tool';
import { StorageFileDto, type StorageFileDtoInterface } from '../../dto/storage_file/storageFileDto';
import EditWkTblMinSeijidantai from '../../common/wktbl_edit_min/EditWkTblMinSeijidantai.vue';
import EditWkTblStdSeijidantai from '../../common/wktbl_edit_std/EditWkTblStdSeijidantai.vue';
import ManagerInfo from '../../common/user_info/ManagerInfo.vue';
import ReadCsv from '../../common/read_csv/ReadCsv.vue';
import RoutePathConstants from '../../../../routePathConstants';
import getAuthorizedPromiseArea from '../../dto/login/getAuthorizedPromiseArea';
import { AccessTokenNotFoundError, TokenRefreshError } from '../../dto/login/errors';
import { useRoute } from 'vue-router';
import { nextTransferPassStore } from '../../stores/nextTransferPass';
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
const MESS_PAGE_NAME: string = "政治団体マスタCSV一括登録";
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

// 初期表示データフォーマットは最小
const formatMin: string = "min";
const formatStd: string = "std";
const isVisibleFormat: Ref<string> = ref(formatMin);

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

    let url = "";
    getAuthorizedPromiseArea().then(token => {
        if (isVisibleFormat.value === formatMin) {
            url = urlBack + "/regist-bulk-master-min/retry-seijidantai";
        }
        if (isVisibleFormat.value === formatStd) {
            url = urlBack + "/regist-bulk-master-std/retry-seijidantai";
        }
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

    let url = "";
    getAuthorizedPromiseArea().then(token => {
        // 最小と標準で接続先切り替え(起動条件のパラメータ内容は変わらない)
        if (isVisibleFormat.value === formatMin) {
            url = urlBack + "/regist-bulk-master-min/execute-seijidantai";
        }
        if (isVisibleFormat.value === formatStd) {
            url = urlBack + "/regist-bulk-master-std/execute-seijidantai";
        }
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
                    return;
                } else {
                    capsuleDto.value.storageFileDto = new StorageFileDto();
                    infoLevel.value = MessageConstants.LEVEL_INFO;
                    messageType.value = MessageConstants.VIEW_TOAST;
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

function recieveSubmit() {
    infoLevel.value = 0;
    messageType.value = 0;
}
</script>
<template>
    <!-- 管理者メニュー兼チェック -->
    <ManagerInfo :user-dto="userDto"></ManagerInfo>

    <h1>関連者政治団体マスタ一括登録</h1>

    <div class="one-line">
        <div class="left-area">
            データフォーマット<br>(最小／標準)
        </div>
        <div class="right-area">
            <span><input type="radio" v-model="isVisibleFormat" :value="formatMin" id="dataFormat">最小</span>
            <span class="left-space"><input type="radio" v-model="isVisibleFormat" :value="formatStd"
                    id="dataFormat">標準</span>
        </div>
    </div>

    <!-- csv読み出し10行 -->
    <ReadCsv @send-storage-file-interface="recieveStorageFileInterface" :user-dto="userDto"></ReadCsv>

    <div class="one-line">
        <button @click="onBatchByFile">頭出ししたcsvファイルで一括処理</button>
    </div>

    <div v-if="isVisibleFormat === formatMin">
        <div class="one-line">
            <button @click="viewSample">{{ templateViewButtonText }}</button>
        </div>
        <div v-if="isVisibleTemplate">
            <div class="one-line">
                ヘッダ必須。1行目は読み飛ばすので、ないと1行目が登録されません<br>
                最初の1列は不要です。(ファイル内は4列)
            </div>
            <div class="one-line-scroll">
                <table>
                    <tbody>
                        <tr>
                            <th class="hojo">要件</th>
                            <th>名称</th>
                            <th>全住所</th>
                            <th>代表者名</th>
                            <th>政治団体区分</th>
                        </tr>
                        <tr>
                            <th class="hojo">説明</th>
                            <th class="explain">必須</th>
                            <th class="explain">必須</th>
                            <th class="explain">任意<br>(項目省略不可)</th>
                            <th class="explain">必須<br>政党:01、政党支部:02、政治資金団体:03<br>18条2項1規定団体:04、その他の政治団体:05、その他の政治団体支部:06
                            </th>
                        </tr>
                        <tr>
                            <th class="hojo">データ例</th>
                            <td>ちゃらんぽらん政治団体</td>
                            <td>和歌山県架空市実在町</td>
                            <td>代表者　太郎</td>
                            <td>05</td>
                        </tr>
                        <tr>
                            <th class="hojo">データ例(同一行許容)<br>・システム側で処理停止</th>
                            <td>ちゃらんぽらん政治団体</td>
                            <td>和歌山県架空市実在町</td>
                            <td>代表者　太郎</td>
                            <td>05</td>
                        </tr>
                        <tr>
                            <th class="hojo">データ例</th>
                            <td>いいかげん政治団体</td>
                            <td>宮崎県架空市実在町</td>
                            <td>代表者　次郎</td>
                            <td>04</td>
                        </tr>
                        <tr>
                            <th class="hojo">データ例(最小入力例)</th>
                            <td>政治団体A</td>
                            <td>山形県架空市実在町</td>
                            <td></td>
                            <td>03</td>
                        </tr>
                        <tr>
                            <th class="hojo">データ例</th>
                            <td>政治団体B</td>
                            <td>山梨県架空市実在町</td>
                            <td>代表者　四郎</td>
                            <td>03</td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <div class="one-line">
                <a href="sample_csv/sample_bulk_master_min_seijidantai.csv">上記内容サンプルcsvをダウンロード</a>
            </div>
        </div>
    </div>

    <!-- 登録結果と編集 -->
    <EditWkTblMinSeijidantai v-if="isVisibleFormat === formatMin" :user-dto="userDto"></EditWkTblMinSeijidantai>

    <h3 v-if="isVisibleFormat !== formatMin">標準フォーマット</h3>
    <div v-if="isVisibleFormat !== formatMin">
        <div class="one-line">
            <button @click="viewSample">{{ templateViewButtonText }}</button>
        </div>
        <div v-if="isVisibleTemplate">
            <div class="one-line">
                ヘッダ必須。1行目は読み飛ばすので、ないと1行目が登録されません<br>
                最初の1列は不要です。(ファイル内は24列)
            </div>
            <div class="one-line-scroll">
                <table class="std">
                    <tbody>
                        <tr>
                            <th class="hojo">要件</th>
                            <th>名称</th>
                            <th>全住所</th>
                            <th>政治団体代表者</th>
                            <th>政治団体区分</th>
                            <th>政治団体番号</th>
                            <th>住所郵便番号まで</th>
                            <th>住所番地まで</th>
                            <th>住所建物まで</th>
                            <th>郵便番号1</th>
                            <th>郵便番号2</th>
                            <th>電話番号市外局番</th>
                            <th>電話番号局番</th>
                            <th>電話番号番号</th>
                            <th>メールアドレス</th>
                            <th>自分の公式サイト</th>
                            <th>SNS名称</th>
                            <th>SNSアカウント</th>
                            <th>関連者団体名称かな</th>
                            <th>団体代表者関連者コード</th>
                            <th>会計責任者関連者個人コード</th>
                            <th>会計責任者関連者個人氏名</th>
                            <th>地方公共団体コード</th>
                            <th>町字Id</th>
                            <th>街区Id</th>
                            <th>地番Id</th>
                            <th>住居Id</th>
                            <th>住居2Id</th>
                        </tr>
                        <tr>
                            <th class="hojo">説明</th>
                            <th class="explain">必須</th>
                            <th class="explain">必須</th>
                            <th class="explain">任意</th>
                            <th class="explain">必須<br>政党:01、政党支部:02、政治資金団体:03<br>18条2項1規定団体:04、その他の政治団体:05、その他の政治団体支部:06
                            </th>
                            <th class="explain">任意<br>30文字まで</th>
                            <th class="explain">必須</th>
                            <th class="explain">必須</th>
                            <th class="explain">任意</th>
                            <th class="explain">任意<br>8文字まで</th>
                            <th class="explain">任意<br>8文字まで</th>
                            <th class="explain">必須<br>10文字まで</th>
                            <th class="explain">必須<br>10文字まで</th>
                            <th class="explain">必須<br>10文字まで</th>
                            <th class="explain">必須</th>
                            <th class="explain">任意</th>
                            <th class="explain">任意</th>
                            <th class="explain">任意</th>
                            <th class="explain">任意</th>
                            <th class="explain">任意</th>
                            <th class="explain">任意</th>
                            <th class="explain">任意</th>
                            <th class="explain">任意<br>8文字まで</th>
                            <th class="explain">任意<br>9文字まで</th>
                            <th class="explain">任意<br>5文字まで</th>
                            <th class="explain">任意<br>17文字まで</th>
                            <th class="explain">任意<br>5文字まで</th>
                            <th class="explain">任意<br>7文字まで</th>
                        </tr>

                        <tr>
                            <th class="hojo">データ例</th>
                            <td>未来創造党</td>
                            <td>東京都千代田区永田町1-1-1</td>
                            <td>佐藤健</td>
                            <td>01</td>
                            <td>12345</td>
                            <td>東京都千代田区永田町</td>
                            <td>1-1-1</td>
                            <td>国会議事堂内</td>
                            <td>100</td>
                            <td>0014</td>
                            <td>03</td>
                            <td>1111</td>
                            <td>2222</td>
                            <td>info@mirai-sozo.jp</td>
                            <td>https://mirai-sozo.jp</td>
                            <td>X</td>
                            <td>@mirai_sozo</td>
                            <td>みらいそうぞうとう</td>
                            <td>P-001</td>
                            <td>A-001</td>
                            <td>会計責任者A</td>
                            <td>13101</td>
                            <td>001</td>
                            <td>001</td>
                            <td>001</td>
                            <td>001</td>
                            <td>001A</td>
                        </tr>
                        <tr>
                            <th class="hojo">データ例(同一行許容)<br>・システム側で処理停止</th>
                            <td>未来創造党</td>
                            <td>東京都千代田区永田町1-1-1</td>
                            <td>佐藤健</td>
                            <td>01</td>
                            <td>12345</td>
                            <td>東京都千代田区永田町</td>
                            <td>1-1-1</td>
                            <td>国会議事堂内</td>
                            <td>100</td>
                            <td>0014</td>
                            <td>03</td>
                            <td>1111</td>
                            <td>2222</td>
                            <td>info@mirai-sozo.jp</td>
                            <td>https://mirai-sozo.jp</td>
                            <td>X</td>
                            <td>@mirai_sozo</td>
                            <td>みらいそうぞうとう</td>
                            <td>P-001</td>
                            <td>A-001</td>
                            <td>会計責任者A</td>
                            <td>13101</td>
                            <td>001</td>
                            <td>001</td>
                            <td>001</td>
                            <td>001</td>
                            <td>001A</td>
                        </tr>
                        <tr>
                            <th class="hojo">データ例</th>
                            <td>日本革新の会</td>
                            <td>大阪府大阪市北区中之島2-2-2</td>
                            <td>代表　鈴木一郎</td>
                            <td>05</td>
                            <td>54321</td>
                            <td>大阪府大阪市北区中之島</td>
                            <td>2-2-2</td>
                            <td>大阪府庁内</td>
                            <td>530</td>
                            <td>0001</td>
                            <td>06</td>
                            <td>3333</td>
                            <td>4444</td>
                            <td>contact@nippon-kakushin.org</td>
                            <td>https://nippon-kakushin.org</td>
                            <td>Facebook</td>
                            <td>nippon.kakushin</td>
                            <td>にっぽんかくしんのかい</td>
                            <td>P-002</td>
                            <td>A-002</td>
                            <td>会計責任者B</td>
                            <td>27100</td>
                            <td>002</td>
                            <td>002</td>
                            <td>002</td>
                            <td>002</td>
                            <td>002B</td>
                        </tr>
                        <tr>
                            <th class="hojo">データ例(最小入力例)</th>
                            <td>地域みらいネットワーク</td>
                            <td>北海道札幌市中央区北1条西2丁目</td>
                            <td>高橋さくら</td>
                            <td>05</td>
                            <td></td>
                            <td>北海道札幌市中央区北1条西2丁目</td>
                            <td></td>
                            <td>札幌市時計台ビル</td>
                            <td></td>
                            <td></td>
                            <td>011</td>
                            <td>5555</td>
                            <td>6666</td>
                            <td>info@chiiki-mirai.net</td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                        </tr>
                        <tr>
                            <th class="hojo">データ例</th>
                            <td>国民の声を届ける党</td>
                            <td>福岡県福岡市博多区博多駅前3-3-3</td>
                            <td>田中美咲</td>
                            <td>01</td>
                            <td>11223</td>
                            <td>福岡県福岡市博多区博多駅前</td>
                            <td>3-3-3</td>
                            <td>博多駅前ビル</td>
                            <td>812</td>
                            <td>0011</td>
                            <td>092</td>
                            <td>7777</td>
                            <td>8888</td>
                            <td>voice@kokumin-no-koe.jp</td>
                            <td>https://kokumin-no-koe.jp</td>
                            <td></td>
                            <td></td>
                            <td>こくみのこえをとどけるとう</td>
                            <td>P-004</td>
                            <td>A-004</td>
                            <td>会計責任者D</td>
                            <td>40132</td>
                            <td>004</td>
                            <td>004</td>
                            <td>004</td>
                            <td>004</td>
                            <td>004D</td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <div class="one-line">
                <a href="sample_csv/sample_bulk_master_std_seijidantai.csv">上記内容サンプルcsvをダウンロード</a>
            </div>
        </div>
    </div>

    <!-- 登録結果と編集 -->
    <EditWkTblStdSeijidantai v-if="isVisibleFormat !== formatMin" :user-dto="userDto"></EditWkTblStdSeijidantai>

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
.one-line-scroll {
    width: 100%;
    overflow-x: auto;
    white-space: nowrap;
}

table {
    border-style: solid;
    border-width: 1px;
}

table.std {
    border-style: solid;
    border-width: 1px;
    table-layout: fixed;
}

td {
    border-style: solid;
    border-width: 1px;
}

th {
    border-style: solid;
    border-width: 1px;
    width: 200px;
    min-width: 200px;
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
