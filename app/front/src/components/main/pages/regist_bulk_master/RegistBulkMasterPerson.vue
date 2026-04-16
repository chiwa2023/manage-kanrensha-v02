<script setup lang="ts">
import { computed, ref, type ComputedRef, type Ref } from 'vue';
import type { LeastUserDtoInterface } from '../../dto/user/leastUserDto';
import { getLoginUser } from '../../utils/getLoginUser';
import MockManagerInfo from '../../../test/common/user_info/MockManagerInfo.vue';
import { RegistDataByCsvFileCapsuleDto, type RegistDataByCsvFileCapsuleDtoInterface } from '../../dto/storage_file/registDataByCsvFileCapsuleDto';
import { FrameworkCapsuleDto, type FrameworkCapsuleDtoInterface } from 'seijishikin-jp-normalize_common-tool';
import type { StorageFileDtoInterface } from '../../dto/storage_file/storageFileDto';
import MockReadCsv from '../../../test/common/read_csv/MockReadCsv.vue';
import EditWkTblStdPerson from '../../common/wktbl_edit_std/EditWkTblStdPerson.vue';
import EditWkTblMinPerson from '../../common/wktbl_edit_min/EditWkTblMinPerson.vue';


const INIT_BOOLEAN: boolean = false;

// ユーザ呼び出し
const userDto: Ref<LeastUserDtoInterface> = ref(getLoginUser());

// back側アクセス
//const urlBack: string = RoutePathConstants.DOMAIN_BACK + RoutePathConstants.PATH_BACK;

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
    alert("キャンセル");
    history.back();
}


function onSave() {

    //let url = "";
    // getAuthorizedPromiseArea().then(token => {
    //     if (isVisibleFormat.value === formatMin) {
    //         url = urlBack + "/regist-bulk-master-min/retry-person";
    //     }
    //     if (isVisibleFormat.value === formatStd) {
    //         url = urlBack + "/regist-bulk-master-std/retry-person";
    //     }

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
    //let url = "";
    // getAuthorizedPromiseArea().then(token => {
    //     // 最小と標準で接続先切り替え(起動条件のパラメータ内容は変わらない)
    //     if (isVisibleFormat.value === formatMin) {
    //         url = urlBack + "/regist-bulk-master-min/execute-person";
    //     }
    //     if (isVisibleFormat.value === formatStd) {
    //         url = urlBack + "/regist-bulk-master-std/execute-person";
    //     }
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

    <h1>関連者個人マスタ一括登録</h1>

    <div class="one-line">
        <div class="left-area">
            データフォーマット(最小／標準)
        </div>
        <div class="right-area">
            <span><input type="radio" v-model="isVisibleFormat" :value="formatMin" id="dataFormat">最小</span>
            <span class="left-space"><input type="radio" v-model="isVisibleFormat" :value="formatStd"
                    id="dataFormat">標準</span>
        </div>
    </div>

    <!-- csv読み出し10行 -->
    <MockReadCsv @send-storage-file-interface="recieveStorageFileInterface" :user-dto="userDto"></MockReadCsv>

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
                最初の1列は不要です。(ファイル内は3列)
            </div>
            <div class="one-line-scroll">
                <table>
                    <tbody>
                        <tr>
                            <th class="hojo">要件</th>
                            <th>名称</th>
                            <th>全住所</th>
                            <th>代表者名</th>
                        </tr>
                        <tr>
                            <th class="hojo">説明</th>
                            <th class="explain">必須</th>
                            <th class="explain">必須</th>
                            <th class="explain">任意<br>(項目省略不可)</th>
                        </tr>
                        <tr>
                            <th class="hojo">データ例</th>
                            <td>迂回献金　太郎</td>
                            <td>和歌山県架空市実在町</td>
                            <td>経営者</td>
                        </tr>
                        <tr>
                            <th class="hojo">データ例(同一行許容)<br>・システム側で処理停止</th>
                            <td>迂回献金　太郎</td>
                            <td>和歌山県架空市実在町</td>
                            <td>経営者</td>
                        </tr>
                        <tr>
                            <th class="hojo">データ例</th>
                            <td>迂回献金　次郎</td>
                            <td>宮崎県架空市実在町</td>
                            <td>教師</td>
                        </tr>
                        <tr>
                            <th class="hojo">データ例(最小入力例)</th>
                            <td>寄付上限　花子</td>
                            <td>山形県架空市実在町</td>
                            <td></td>
                        </tr>
                        <tr>
                            <th class="hojo">データ例</th>
                            <td>寄付上限　直子</td>
                            <td>山梨県架空市実在町</td>
                            <td>弁護士</td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <div class="one-line">
                <a href="sample_csv/sample_bulk_master_min_person.csv">上記内容サンプルcsvをダウンロード</a>
            </div>
        </div>
    </div>
    <!-- 登録結果と編集 -->
    <EditWkTblMinPerson v-if="isVisibleFormat === formatMin" :user-dto="userDto"></EditWkTblMinPerson>

    <h3 v-if="isVisibleFormat !== formatMin">標準フォーマット</h3>
    <div v-if="isVisibleFormat !== formatMin">
        <div class="one-line">
            <button @click="viewSample">{{ templateViewButtonText }}</button>
        </div>
        <div v-if="isVisibleTemplate">
            <div class="one-line">
                ヘッダ必須。1行目は読み飛ばすので、ないと1行目が登録されません<br>
                最初の1列は不要です。(ファイル内は28列)
            </div>
            <div class="one-line-scroll">
                <table class="std">
                    <tbody>
                        <tr>
                            <th class="hojo">要件</th>
                            <th>名称</th>
                            <th>全住所</th>
                            <th>個人職業</th>
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
                            <th>SNSサービス名称</th>
                            <th>SNSサービスアカウント</th>
                            <th>外国籍該否</th>
                            <th>姓名の姓</th>
                            <th>姓名の名</th>
                            <th>姓名のミドルネーム</th>
                            <th>姓名の姓のかな</th>
                            <th>姓名の名のかな</th>
                            <th>姓名のミドルネームのかな</th>
                            <th>職業の業種</th>
                            <th>職業の役職</th>
                            <th>職業のユーザ記載</th>
                            <th>職業法人番号</th>
                            <th>職業法人住所</th>
                            <th>職業法人名</th>
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
                            <th class="explain">任意<br>"はい","1","true","該当"の<br>いずれかが設定されているときは該当</th>
                            <th class="explain">任意</th>
                            <th class="explain">任意</th>
                            <th class="explain">任意</th>
                            <th class="explain">任意</th>
                            <th class="explain">任意</th>
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
                            <td>山田太郎</td>
                            <td>東京都千代田区1-1-1</td>
                            <td>会社員</td>
                            <td>東京都千代田区</td>
                            <td>1-1</td>
                            <td>丸の内ビル1F</td>
                            <td>100</td>
                            <td>0001</td>
                            <td>03</td>
                            <td>1234</td>
                            <td>5678</td>
                            <td>taro.yamada@example.com</td>
                            <td>https://taro-yamada.com</td>
                            <td>X</td>
                            <td>@taro_yamada</td>
                            <td>いいえ</td>
                            <td>山田</td>
                            <td>太郎</td>
                            <td>ジェイムズ</td>
                            <td>やまだ</td>
                            <td>たろう</td>
                            <td>じぇいむず</td>
                            <td>IT</td>
                            <td>エンジニア</td>
                            <td>ソフトウェア開発</td>
                            <td>1234567890123</td>
                            <td>東京都千代田区</td>
                            <td>株式会社ABC</td>
                            <td>13101</td>
                            <td>001</td>
                            <td>001</td>
                            <td>001</td>
                            <td>001</td>
                            <td>001</td>
                        </tr>
                        <tr>
                            <th class="hojo">データ例(同一行許容)<br>・システム側で処理停止</th>
                            <td>山田太郎</td>
                            <td>東京都千代田区1-1-1</td>
                            <td>会社員</td>
                            <td>東京都千代田区</td>
                            <td>1-1</td>
                            <td>丸の内ビル1F</td>
                            <td>100</td>
                            <td>0001</td>
                            <td>03</td>
                            <td>1234</td>
                            <td>5678</td>
                            <td>taro.yamada@example.com</td>
                            <td>https://taro-yamada.com</td>
                            <td>X</td>
                            <td>@taro_yamada</td>
                            <td>いいえ</td>
                            <td>山田</td>
                            <td>太郎</td>
                            <td>ジェイムズ</td>
                            <td>やまだ</td>
                            <td>たろう</td>
                            <td>じぇいむず</td>
                            <td>IT</td>
                            <td>エンジニア</td>
                            <td>ソフトウェア開発</td>
                            <td>1234567890123</td>
                            <td>東京都千代田区</td>
                            <td>株式会社ABC</td>
                            <td>13101</td>
                            <td>001</td>
                            <td>001</td>
                            <td>001</td>
                            <td>001</td>
                            <td>001</td>
                        </tr>
                        <tr>
                            <th class="hojo">データ例</th>
                            <td>佐藤花子</td>
                            <td>大阪府大阪市中央区2-2-2</td>
                            <td>公務員</td>
                            <td>大阪府大阪市中央区</td>
                            <td>2-2</td>
                            <td>大阪市役所</td>
                            <td>540</td>
                            <td>0002</td>
                            <td>06</td>
                            <td>9876</td>
                            <td>5432</td>
                            <td>hanako.sato@example.jp</td>
                            <td></td>
                            <td>Facebook</td>
                            <td>hanako.sato</td>
                            <td>いいえ</td>
                            <td>佐藤</td>
                            <td>花子</td>
                            <td></td>
                            <td>さとう</td>
                            <td>はなこ</td>
                            <td></td>
                            <td>行政</td>
                            <td>一般職</td>
                            <td>地方公務員</td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td>27100</td>
                            <td>002</td>
                            <td>002</td>
                            <td>002</td>
                            <td>002</td>
                            <td>002</td>
                        </tr>
                        <tr>
                            <th class="hojo">データ例(最小入力例)</th>
                            <td>田中一郎</td>
                            <td>福岡県福岡市博多区3-3-3</td>
                            <td>自営業</td>
                            <td>福岡県福岡市博多区</td>
                            <td>3-3</td>
                            <td>博多ビル3F</td>
                            <td></td>
                            <td></td>
                            <td>092</td>
                            <td>1111</td>
                            <td>2222</td>
                            <td>ichiro.tanaka@example.net</td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td>田中</td>
                            <td>一郎</td>
                            <td></td>
                            <td>たなか</td>
                            <td>いちろう</td>
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
                            <td>鈴木美咲</td>
                            <td>愛知県名古屋市中区4-4-4</td>
                            <td>学生</td>
                            <td>愛知県名古屋市中区</td>
                            <td>4-4</td>
                            <td>大学寮</td>
                            <td>460</td>
                            <td>0004</td>
                            <td>052</td>
                            <td>3333</td>
                            <td>4444</td>
                            <td>misaki.suzuki@example.co.jp</td>
                            <td></td>
                            <td>TikTok</td>
                            <td>@misaki_s</td>
                            <td>いいえ</td>
                            <td>鈴木</td>
                            <td>美咲</td>
                            <td>アン</td>
                            <td>すずき</td>
                            <td>みさき</td>
                            <td>あん</td>
                            <td></td>
                            <td></td>
                            <td>大学生</td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td>23100</td>
                            <td>004</td>
                            <td>004</td>
                            <td>004</td>
                            <td>004</td>
                            <td>004</td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <div class="one-line">
                <a href="sample_csv/sample_bulk_master_std_person.csv">上記内容サンプルcsvをダウンロード</a><br>
            </div>
        </div>
    </div>
    <!-- 登録結果と編集 -->
    <EditWkTblStdPerson v-if="isVisibleFormat !== formatMin" :user-dto="userDto"></EditWkTblStdPerson>

    <div class="footer">
        <button @click="onCancel" class="footer-button">キャンセル</button>
        <button @click="onSave" class="footer-button left-space">送信</button>
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
}

th.explain {
    background-color: lightcyan;
}
</style>
