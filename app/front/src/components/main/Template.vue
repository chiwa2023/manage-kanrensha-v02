<script setup lang="ts">
import { ref, type Ref } from 'vue';
import type SelectOptionNumberInterface from '../main/dto/select_options/selectOptionNumberDto';
import SelectOptionNumberDto from '../main/dto/select_options/selectOptionNumberDto';
import SearchHoujinNo from './common/search_houjin_no/SearchHoujinNo.vue';

// よく使う定数
// const BLANK: string = "";
// const INIT_NUMBER: number = 0;
// const SERVER_STATUS_OK: number = 200;
// const SERVER_STATUS_ERROR: number = 400;

// ラジオボタン入力サンプル
const radioInputData: Ref<string> = ref("");

const selectedTableLine: Ref<number> = ref(-1);

// ページング
const pageOption: Ref<SelectOptionNumberInterface[]> = ref([]);

// ページングMockデータ
const options:SelectOptionNumberInterface = new SelectOptionNumberDto();
options.value = 0;
options.text = "1-50";
pageOption.value.push(options);

function onChangePaging() {
    // TODO (ページング情報をコンポーネントから受け取り)検索処理を実行
}

const showContentA: string = "a";
const showContentB: string = "b";
const showContentC: string = "c";
const viewStatus1: Ref<string> = ref(showContentA);
const viewStatus2: Ref<string> = ref(showContentA);

// 法人検索
const isCorpSearch: Ref<boolean> = ref(false);

function onRaiseCorpNoSearch() {
    isCorpSearch.value = true;
}

/**
* 法人番号キャンセル選択なし
*/
function recieveCancelCorpNo() {
    // 非表示
    isCorpSearch.value = false;
}

/**
* 法人番号選択データ受信
*/
function recieveCorpNoInterface() {

    // TODO 選択された方法を受信し親ページに格納
    // 非表示
    isCorpSearch.value = false;
}

</script>
<template>
    <h1>ページタイトル</h1>

    <!-- ユーザrole別制御コンポーネント -->
    <div style="background-color: #FF0000;padding-left: 0.7%;">
        <div style=" background-color: white;z-index: 2;padding-left: 1.3%;opacity: 1;text-align: right;">
            <div style="float: left;">
                SE権限
            </div>
            <div style="padding-right: 2.5%;">
                <!-- 必要アイコンはここに追加 -->
                <div style="float: right;" class="left-space">
                    <img src="#" style="width: 80px;height: 80px;">
                </div>
                <div class="left-space">
                    <br>
                    ※遷移メニュー
                    <br>
                </div>
            </div>
        </div>
    </div>

    <h3>ページ使用のための前説</h3>
    <div class="one-line">
        入力に関する説明事項など各種平文
    </div>
    <div class="clear-both"></div>

    <h3>情報入力</h3>
    <div class="left-area">
        団体名
    </div>
    <div class="right-area">
        <input type="text" class="max-input" placeholder="団体名かな"></input><br>
        <input type="text" class="max-input" placeholder="団体名"></input>
    </div>
    <div class="clear-both"></div>

    <div class="left-area">
        団体名登録情報
    </div>
    <div class="right-area">
        コード：<input type="text" class="code-input" :disabled="true">
        名：<input type="text" class="name-input left-space" :disabled="true"></input>
        <button class="left-space" @click="onRaiseCorpNoSearch">検索</button>
    </div>
    <div class="clear-both"></div>

    <div class="left-area">
        団体属性情報A
    </div>
    <div class="right-area">
        <input type="radio" v-model="radioInputData" value="A-1">属性A-1</input>
        <input type="radio" v-model="radioInputData" value="A-2">属性A-2</input>
        <input type="radio" v-model="radioInputData" value="A-3">属性A-3</input>
        <input type="radio" v-model="radioInputData" value="A-4">属性A-4</input>
    </div>
    <div class="clear-both"></div>

    <div class="left-area">
        団体属性情報B
    </div>
    <div class="right-area">
        <input type="checkbox">属性A-1</input>
        <input type="checkbox">属性A-2</input>
        <input type="checkbox">属性A-3</input>
        <input type="checkbox">属性A-4</input>
    </div>
    <div class="clear-both"></div>

    <div class="left-area">
        有効期間
    </div>
    <div class="right-area">
        <input type="date"></input>から<input type="date"></input>まで
    </div>
    <div class="clear-both"></div>

    <div class="left-area">
        指定日時
    </div>
    <div class="right-area">
        <input type="datetime-local"></input>から<input type="datetime-local"></input>まで
    </div>
    <div class="clear-both"></div>

    <h3>検索結果表示</h3>
    <div class="one-line" style="overflow: scroll;">
        <!-- ページング -->
        <select @change="onChangePaging">
            <option v-for="option in pageOption" :key="option.value" :value="option.value"> {{ option.text
                }}
            </option>
        </select><br>
        <button>すべての行を選択する</button><br>
        <table style="width: 3000px;">
            <tbody>
                <tr>
                    <th>行選択</th>
                    <th>団体名</th>
                    <th>値2</th>
                    <th>値3</th>
                    <th>値4</th>
                    <th>値5</th>
                    <th>&nbsp;</th>
                </tr>
                <tr>
                    <td class="table-column-select-row"><input type="radio" v-model="selectedTableLine" value="0"></td>
                    <td>だんたいめい</td>
                    <td>(12345A)<br>団体名称</td>
                    <td><input type="text" class="code-input"><br><input type="text" class="text-input"></input></td>
                    <td>123,4567</td>
                    <td>2025-01-03 12:34:56</td>
                    <td class="table-column-button"><button>この行を削除</button></td>
                </tr>
                <tr>
                    <td class="table-column-select-row"><input type="checkbox"></input> </td>
                    <td>だんたいめい</td>
                    <td>(12345A)<br>団体名称</td>
                    <td><input type="text" class="code-input"><br><input type="text" class="text-input"></input></td>
                    <td>123,4567</td>
                    <td>2025-01-03 12:34:56</td>
                    <td class="table-column-button"><button>この行を削除</button></td>
                </tr>
            </tbody>
        </table>

        <!-- ページング -->
        <select @change="onChangePaging">
            <option v-for="option in pageOption" :key="option.value" :value="option.value"> {{ option.text
                }}
            </option>
        </select><br>
    </div>
    <div class="clear-both"><br></div>

    <h3>コンテンツタブ切り替え(短)</h3>

    <div class="left-area">
        団体タイプ
    </div>
    <div class="right-area">
        <input type="radio" v-model="viewStatus1" :value=showContentA>団体タイプA</input>
        <input type="radio" v-model="viewStatus1" :value=showContentB>団体タイプB</input>
        <input type="radio" v-model="viewStatus1" :value=showContentC>団体タイプC</input>
    </div>
    <div class="clear-both"></div>
    <div class="left-area">
        &nbsp;
    </div>
    <div class="right-area">
        <div v-if="showContentA === viewStatus1">
            コンテンツA
        </div>
        <div v-if="showContentB === viewStatus1">
            コンテンツB
        </div>
        <div v-if="showContentC === viewStatus1">
            コンテンツC
        </div>
    </div>
    <div class="clear-both"><br></div>

    <h3>コンテンツタブ切り替え(長)</h3>

    <div class="one-line">
        表示コンテンツ：
        <input type="radio" v-model="viewStatus2" :value=showContentA>団体タイプA</input>
        <input type="radio" v-model="viewStatus2" :value=showContentB>団体タイプB</input>
        <input type="radio" v-model="viewStatus2" :value=showContentC>団体タイプC</input>

        <br>
        <div v-if="showContentA === viewStatus2">
            コンテンツA
        </div>
        <div v-if="showContentB === viewStatus2">
            コンテンツB
        </div>
        <div v-if="showContentC === viewStatus2">
            コンテンツC
        </div>
    </div>
    <div class="clear-both"><br></div>

    <hr>

    <div class="footer">
        <button class="footer-button">キャンセル</button>
        <button class="footer-button left-space">送信</button>
    </div>

    <!-- 検索コンポーネント -->
    <div v-if="isCorpSearch" class="overBackground"></div>
    <div v-if="isCorpSearch">
        <div class="overComponent">
            <SearchHoujinNo v-if="isCorpSearch" @send-cancel-houjin-no="recieveCancelCorpNo"
                @send-houjin-no-interface="recieveCorpNoInterface"></SearchHoujinNo>
        </div>
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
</style>
