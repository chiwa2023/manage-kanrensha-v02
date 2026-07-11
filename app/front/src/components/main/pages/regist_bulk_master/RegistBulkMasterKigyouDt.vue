<script setup lang="ts">
import { computed, onMounted, ref, type ComputedRef, type Ref } from 'vue';
import { getLoginUser } from '../../utils/getLoginUser';
import { FrameworkCapsuleDto, getErrorMessage, MessageConstants, MessageView, type FrameworkCapsuleDtoInterface, type FrameworkMessageAndResultDtoInterface, type LeastUserDtoInterface } from 'seijishikin-jp-normalize_common-tool';
import { RegistDataByCsvFileCapsuleDto, type RegistDataByCsvFileCapsuleDtoInterface } from '../../dto/storage_file/registDataByCsvFileCapsuleDto';
import { StorageFileDto, type StorageFileDtoInterface } from '../../dto/storage_file/storageFileDto';
import EditWkTblMinKigyouDt from '../../common/wktbl_edit_min/EditWkTblMinKigyouDt.vue';
import EditWkTblStdKigyouDt from '../../common/wktbl_edit_std/EditWkTblStdKigyouDt.vue';
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
const MESS_PAGE_NAME: string = "関連者企業団体マスタ一括処理再処理";
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
            url = urlBack + "/regist-bulk-master-min/retry-kigyou-dt";
        }
        if (isVisibleFormat.value === formatStd) {
            url = urlBack + "/regist-bulk-master-std/retry-kigyou-dt";
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
            url = urlBack + "/regist-bulk-master-min/execute-kigyou-dt";
        }
        if (isVisibleFormat.value === formatStd) {
            url = urlBack + "/regist-bulk-master-std/execute-kigyou-dt";
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

function recieveSubmit() {
    infoLevel.value = 0;
    messageType.value = 0;
}
</script>
<template>
    <!-- 管理者メニュー兼チェック -->
    <ManagerInfo :user-dto="userDto"></ManagerInfo>

    <h1>関連者企業・団体マスタ一括登録</h1>
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

    <h3 v-if="isVisibleFormat === formatMin">最小フォーマット</h3>

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
                            <th>法人番号</th>
                        </tr>
                        <tr>
                            <th class="hojo">説明</th>
                            <th class="explain">必須</th>
                            <th class="explain">必須</th>
                            <th class="explain">任意<br>(項目省略不可)</th>
                            <th class="explain">必須<br>数字13桁</th>
                        </tr>
                        <tr>
                            <th class="hojo">データ例</th>
                            <td>ぼったくり企業</td>
                            <td>和歌山県架空市実在町</td>
                            <td>代表者　太郎</td>
                            <td>1234567890123</td>
                        </tr>
                        <tr>
                            <th class="hojo">データ例(同一行許容)<br>・システム側で処理停止</th>
                            <td>ぼったくり企業</td>
                            <td>和歌山県架空市実在町</td>
                            <td>代表者　太郎</td>
                            <td>1234567890123</td>
                        </tr>
                        <tr>
                            <th class="hojo">データ例</th>
                            <td>ふんだくり企業</td>
                            <td>宮崎県架空市実在町</td>
                            <td>代表者　次郎</td>
                            <td>1234567890222</td>
                        </tr>
                        <tr>
                            <th class="hojo">データ例(最小入力例)</th>
                            <td>職業組合A</td>
                            <td>山形県架空市実在町</td>
                            <td></td>
                            <td>1234567890333</td>
                        </tr>
                        <tr>
                            <th class="hojo">データ例</th>
                            <td>職業組合B</td>
                            <td>山梨県架空市実在町</td>
                            <td>代表者　四郎</td>
                            <td>1234567890444</td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <div class="one-line">
                <a href="sample_csv/sample_bulk_master_min_kigyou-dt.csv">上記内容サンプルcsvをダウンロード</a>
            </div>
        </div>
    </div>

    <!-- 登録結果と編集 -->
    <EditWkTblMinKigyouDt v-if="isVisibleFormat === formatMin" :user-dto="userDto"></EditWkTblMinKigyouDt>

    <h3 v-if="isVisibleFormat !== formatMin">標準フォーマット</h3>
    <div v-if="isVisibleFormat !== formatMin">
        <div class="one-line">
            <button @click="viewSample">{{ templateViewButtonText }}</button>
        </div>
        <div v-if="isVisibleTemplate" style="overflow: scroll;">
            <div class="one-line">
                ヘッダ必須。1行目は読み飛ばすので、ないと1行目が登録されません<br>
                最初の1列は不要です。(ファイル内は23列)
            </div>
            <div class="one-line-scroll">
                <table class="std">
                    <tbody>
                        <tr>
                            <th class="hojo">要件</th>
                            <th>名称</th>
                            <th>全住所</th>
                            <th>団体代表者</th>
                            <th>法人番号</th>
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
                            <th>外国籍該否</th>
                            <th>法人種別</th>
                            <th>関連者団体名称かな</th>
                            <th>支店該当</th>
                            <th>団体代表者関連者コード</th>
                            <th>SNS名称コード</th>
                            <th>SNSアカウント</th>
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
                            <th class="explain">必須<br>13文字まで</th>
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
                            <th class="explain">任意<br>法人種別が401の時は該当<br>"はい","1","true","該当"<br>のいずれかが設定されているときは該当</th>
                            <th class="explain">任意</th>
                            <th class="explain">任意</th>
                            <th class="explain">任意<br>"はい","1","true","該当"<br>のいずれかが設定されているときは該当</th>
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
                            <td>超元素製造組合</td>
                            <td>和歌山県架空市山麓町</td>
                            <td>組合長　花子</td>
                            <td>1-233-4444</td>
                            <td>和歌山県実在市山麓町</td>
                            <td>3番地の4</td>
                            <td>四角ビル202号</td>
                            <td>012</td>
                            <td>3456</td>
                            <td>023</td>
                            <td>4567</td>
                            <td>8901</td>
                            <td>aaa@bbb.net</td>
                            <td>https://bbb.net/aaa</td>
                            <td>はい</td>
                            <td>401</td>
                            <td>ちょうげんそせいぞうくみあい</td>
                            <td>いいえ</td>
                            <td>98-777</td>
                            <td>弱小SNS</td>
                            <td>@bbb_aaa</td>
                            <td>098</td>
                            <td>765</td>
                            <td>432</td>
                            <td>543</td>
                            <td>210</td>
                            <td>777</td>
                        </tr>
                        <tr>
                            <th class="hojo">データ例(同一行許容)<br>・システム側で処理停止</th>
                            <td>超元素製造組合</td>
                            <td>和歌山県架空市山麓町</td>
                            <td>組合長　花子</td>
                            <td>1-233-4444</td>
                            <td>和歌山県実在市山麓町</td>
                            <td>3番地の4</td>
                            <td>四角ビル202号</td>
                            <td>012</td>
                            <td>3456</td>
                            <td>023</td>
                            <td>4567</td>
                            <td>8901</td>
                            <td>aaa@bbb.net</td>
                            <td>https://bbb.net/aaa</td>
                            <td>はい</td>
                            <td>401</td>
                            <td>ちょうげんそせいぞうくみあい</td>
                            <td>いいえ</td>
                            <td>98-777</td>
                            <td>弱小SNS</td>
                            <td>@bbb_aaa</td>
                            <td>098</td>
                            <td>765</td>
                            <td>432</td>
                            <td>543</td>
                            <td>210</td>
                            <td>777</td>
                        </tr>
                        <tr>
                            <th class="hojo">データ例</th>
                            <td>テクノロジー開発ラボ</td>
                            <td>東京都仮想区未来1-1-1</td>
                            <td>主任研究員　太郎</td>
                            <td>2-567-8901</td>
                            <td>東京都仮想区未来</td>
                            <td>1-1-1</td>
                            <td>未来ビル101号</td>
                            <td>100</td>
                            <td>0001</td>
                            <td>03</td>
                            <td>1234</td>
                            <td>5678</td>
                            <td>taro@techlab.jp</td>
                            <td>https://techlab.jp</td>
                            <td>いいえ</td>
                            <td>501</td>
                            <td>テクノロジーかいはつラボ</td>
                            <td>いいえ</td>
                            <td>12-345</td>
                            <td>未来SNS</td>
                            <td>@tech_taro</td>
                            <td>131</td>
                            <td>001</td>
                            <td>002</td>
                            <td>003</td>
                            <td>004</td>
                            <td>005</td>
                        </tr>
                        <tr>
                            <th class="hojo">データ例(最小入力例)</th>
                            <td>グリーンエネルギー研究会</td>
                            <td>大阪府架空市エコ町</td>
                            <td></td>
                            <td>3-111-2222</td>
                            <td>大阪府架空市エコ町</td>
                            <td>5-5-5</td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td>06</td>
                            <td>9876</td>
                            <td>5432</td>
                            <td>midori@eco.org</td>
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
                            <td></td>
                        </tr>
                        <tr>
                            <th class="hojo">データ例</th>
                            <td>地域活性化協議会</td>
                            <td>福岡県仮想市活性町</td>
                            <td>会長　健一</td>
                            <td>4-789-0123</td>
                            <td>福岡県仮想市活性町</td>
                            <td>2-2-2</td>
                            <td>活性化センター2F</td>
                            <td>810</td>
                            <td>0001</td>
                            <td>092</td>
                            <td>3456</td>
                            <td>7890</td>
                            <td>kenichi@local.net</td>
                            <td>https://local.net</td>
                            <td>いいえ</td>
                            <td>403</td>
                            <td>ちいきかっせいかきょうぎかい</td>
                            <td>いいえ</td>
                            <td>34-567</td>
                            <td>地域SNS</td>
                            <td>@local_ken</td>
                            <td>401</td>
                            <td>200</td>
                            <td>201</td>
                            <td>202</td>
                            <td>203</td>
                            <td>204</td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <div class="one-line">
                <a href="sample_csv/sample_bulk_master_std_kigyou-dt.csv">上記内容サンプルcsvをダウンロード</a><br>
            </div>
        </div>
    </div>

    <!-- 登録結果と編集 -->
    <EditWkTblStdKigyouDt v-if="isVisibleFormat !== formatMin" :user-dto="userDto"></EditWkTblStdKigyouDt>

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
