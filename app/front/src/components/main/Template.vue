<script setup lang="ts">
import { ref, type Ref } from 'vue';
import {
    InputAccessDto, InputAddressDto, InputOrgNameDto, InputPersonNameDto,
    InputShokugyouDto, MessageConstants, MessageView, MockViewInputAddress, MockViewInputAddressShort, PagingControl,
    SearchHoujinNo, ViewInputAccess, ViewInputOrgName, ViewInputPersonName, ViewInputShokugyou,
    type HoujinNoDtoInterface,
    type InputAccessDtoInterface, type InputAddressDtoInterface,
    type InputOrgNameDtoInterface, type InputPersonNameDtoInterface,
    type InputShokugyouDtoInterface
} from 'seijishikin-jp-normalize_common-tool';

// よく使う定数
const BLANK: string = "";
//const INIT_NUMBER: number = 0;
// const SERVER_STATUS_OK: number = 200;
// const SERVER_STATUS_ERROR: number = 400;
// メッセージ表示定数
const infoLevel: Ref<number> = ref(MessageConstants.LEVEL_NONE);
const messageType: Ref<number> = ref(MessageConstants.VIEW_NONE);
const title: Ref<string> = ref(BLANK);
const message: Ref<string> = ref(BLANK);

// Paging
const pageNumber: Ref<number> = ref(6); // Mock data
const allCount: Ref<number> = ref(123); // Mock data
const limit: Ref<number> = ref(10); // Mock data

// ラジオボタン入力サンプル
const radioInputData: Ref<string> = ref("");
const selectedTableLine: Ref<number> = ref(-1);

// コンテンツ
const showContentA: string = "a";
const showContentB: string = "b";
const showContentC: string = "c";
const viewStatus1: Ref<string> = ref(showContentA);
const viewStatus2: Ref<string> = ref(showContentA);

// 法人検索
const isCorpSearch: Ref<boolean> = ref(false);
const houjinNo:Ref<string> = ref(BLANK);
const houjinName:Ref<string> = ref(BLANK);

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
function recieveCorpNoInterface(sendDto:HoujinNoDtoInterface) {
    houjinNo.value = sendDto.houjinNo;
    houjinName.value = sendDto.houjinName;    
    // 非表示
    isCorpSearch.value = false;
}

function recievePagingNumber(selecteddNumber: number) {
    pageNumber.value = selecteddNumber;
    alert("ページ情報受信");
}

// メッセージ表示

function onInfo() {
    infoLevel.value = MessageConstants.LEVEL_INFO;
    title.value = "情報タイトル";
    message.value = "メッセージ1";
    // 表示
    messageType.value = MessageConstants.VIEW_TOAST;
}

function onWarning() {
    infoLevel.value = MessageConstants.LEVEL_WARNING;
    title.value = "警告タイトル";
    message.value = "メッセージ2";
    // 表示
    messageType.value = MessageConstants.VIEW_YES_NO;
}

function onError() {
    infoLevel.value = MessageConstants.LEVEL_ERROR;
    title.value = "エラータイトル";
    message.value = "メッセージ3";
    // 表示
    messageType.value = MessageConstants.VIEW_OK;
}

function recieveSubmit(button: string) {
    alert(button);
    // TODO ボタンタイプ別の挙動はこの中で変える

    // 非表示
    infoLevel.value = 0;
    messageType.value = 0;
}

// 共通入力用変数
const inputOrgNameDto: Ref<InputOrgNameDtoInterface> = ref(new InputOrgNameDto());
const inputPersonNameDto: Ref<InputPersonNameDtoInterface> = ref(new InputPersonNameDto());
const inputAccessDto: Ref<InputAccessDtoInterface> = ref(new InputAccessDto());
const inputAddressDto: Ref<InputAddressDtoInterface> = ref(new InputAddressDto());
const inputAddressDtoShort: Ref<InputAddressDtoInterface> = ref(new InputAddressDto());
const inputShokugyouDto: Ref<InputShokugyouDtoInterface> = ref(new InputShokugyouDto());

</script>
<template>
    <div class="container">
        <!-- ユーザrole別制御コンポーネント -->
        <div class="user-role-container">
            <div class="user-role-content">
                <div class="user-role-title">
                    SE権限
                </div>
                <!-- 遷移メニュー -->
                <div class="user-role-menu-wrapper">
                    <div class="left-space">
                        遷移メニュー
                    </div>
                </div>
                <div class="user-role-menu-wrapper">
                    <!-- 必要アイコンはここに追加 -->
                    <div class="left-space user-role-icon-container">
                        <img src="#" class="user-role-icon">
                    </div>
                </div>
            </div>
        </div>

        <h1>ページタイトル</h1>

        <h3>ページ使用のための前説</h3>
        <div class="one-line">
            入力に関する説明事項など各種平文
        </div>

        <h3 class="accent-h3">情報入力</h3>
        <div class="one-line">
            <div class="left-area">
                団体名
            </div>
            <div class="right-area">
                <div class="form-group-vertical">
                    <textarea class="max-input" placeholder="団体名かな"></textarea>
                    <input type="text" class="max-input" placeholder="団体名"></input>
                </div>
            </div>
        </div>

        <div class="one-line">
            <div class="left-area">
                団体名登録情報
            </div>
            <div class="right-area">
                コード：<input type="text" v-model="houjinNo" class="short-input" :disabled="true">
                名：<input type="text" v-model="houjinName" class="name-input left-space" :disabled="true"></input>
                <button class="left-space" @click="onRaiseCorpNoSearch">検索</button>
            </div>
        </div>

        <div class="one-line">
            <div class="left-area">
                団体属性情報A
            </div>
            <div class="right-area">
                <input type="radio" v-model="radioInputData" value="A-1">属性A-1</input>
                <input type="radio" v-model="radioInputData" value="A-2">属性A-2</input>
                <input type="radio" v-model="radioInputData" value="A-3">属性A-3</input>
                <input type="radio" v-model="radioInputData" value="A-4">属性A-4</input>
            </div>
        </div>

        <div class="one-line">
            <div class="left-area">
                団体属性情報B
            </div>
            <div class="right-area">
                <input type="checkbox">属性A-1</input>
                <input type="checkbox">属性A-2</input>
                <input type="checkbox">属性A-3</input>
                <input type="checkbox">属性A-4</input>
            </div>
        </div>

        <div class="one-line">
            <div class="left-area">
                有効期間
            </div>
            <div class="right-area">
                <input type="date"></input>から<input type="date"></input>まで
            </div>
        </div>

        <div class="one-line">
            <div class="left-area">
                指定日時
            </div>
            <div class="right-area">
                <input type="datetime-local"></input>から<input type="datetime-local"></input>まで
            </div>
        </div>

        <h3 class="accent-h3">検索結果表示</h3>
        <!-- ページング -->
        <PagingControl :all-count="allCount" :limit="limit" :page-number="pageNumber"
            @send-paging-number="recievePagingNumber"></PagingControl>
        <div class="one-line-scroll">
            <table>
                <thead>
                    <tr>
                        <th>行選択</th>
                        <th>団体名</th>
                        <th>値2</th>
                        <th>値3</th>
                        <th>値4</th>
                        <th>値5</th>
                        <th>&nbsp;</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td class="table-column-select-row"><input type="radio" v-model="selectedTableLine" value="0">
                        </td>
                        <td>だんたいめい</td>
                        <td>(12345A)<br>団体名称</td>
                        <td>
                            <div class="form-group-vertical">
                                <input type="text" class="code-input"><input type="text" class="text-input"></input>
                            </div>
                        </td>
                        <td>123,4567</td>
                        <td>2025-01-03 12:34:56</td>
                        <td class="table-column-button"><button>この行を削除</button></td>
                    </tr>
                    <tr>
                        <td class="table-column-select-row"><input type="checkbox"></input> </td>
                        <td>だんたいめい</td>
                        <td>(12345A)<br>団体名称</td>
                        <td>
                            <div class="form-group-vertical">
                                <input type="text" class="code-input"><input type="text" class="text-input"></input>
                            </div>
                        </td>
                        <td>123,4567</td>
                        <td>2025-01-03 12:34:56</td>
                        <td class="table-column-button"><button>この行を削除</button></td>
                    </tr>
                </tbody>
            </table>
        </div>
        <!-- ページング -->
        <PagingControl :all-count="allCount" :limit="limit" :page-number="pageNumber"
            @send-paging-number="recievePagingNumber"></PagingControl>

        <h3 class="accent-h3">コンテンツタブ切り替え(短)</h3>
        <div class="one-line">
            <div class="left-area">
                団体タイプ
            </div>
            <div class="right-area">
                <input type="radio" v-model="viewStatus1" :value=showContentA>団体タイプA</input>
                <input type="radio" v-model="viewStatus1" :value=showContentB>団体タイプB</input>
                <input type="radio" v-model="viewStatus1" :value=showContentC>団体タイプC</input>
            </div>
        </div>
        <div class="one-line">
            <div class="left-area">
                &nbsp;
            </div>
            <div class="right-area">
                <div v-if="showContentA === viewStatus1"
                    :class="{ 'active-content-area': showContentA === viewStatus1 }">
                    コンテンツA
                </div>
                <div v-if="showContentB === viewStatus1"
                    :class="{ 'active-content-area': showContentB === viewStatus1 }">
                    コンテンツB
                </div>
                <div v-if="showContentC === viewStatus1"
                    :class="{ 'active-content-area': showContentC === viewStatus1 }">
                    コンテンツC
                </div>
            </div>
        </div>

        <h3 class="accent-h3">コンテンツタブ切り替え(長)</h3>

        <div class="one-line">
            表示コンテンツ：
            <input type="radio" v-model="viewStatus2" :value=showContentA>団体タイプA</input>
            <input type="radio" v-model="viewStatus2" :value=showContentB>団体タイプB</input>
            <input type="radio" v-model="viewStatus2" :value=showContentC>団体タイプC</input>

        </div>

        <div class="one-line">
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

        <h3 class="accent-h3">メッセージ</h3>
        <div class="one-line">
            <div class="left-area">
                メッセージ表示
            </div>
            <div class="right-area">
                <button @click="onInfo">情報</button>
                <button class="left-space" @click="onWarning">警告(YES/NO)</button>
                <button class="left-space" @click="onError">エラー(OK)</button>
            </div>
        </div>

        <!-- 団体名称入力 -->
        <ViewInputOrgName :edit-dto="inputOrgNameDto"></ViewInputOrgName>

        <!-- 姓名入力 -->
        <ViewInputPersonName :edit-dto="inputPersonNameDto"></ViewInputPersonName>

        <!-- 住所 -->
        <MockViewInputAddress :edit-dto="inputAddressDto"></MockViewInputAddress>

        <!-- 住所短縮 -->
        <MockViewInputAddressShort :edit-dto="inputAddressDtoShort"></MockViewInputAddressShort>

        <!-- 連絡先 -->
        <ViewInputAccess :edit-dto="inputAccessDto"></ViewInputAccess>

        <!-- 職業 -->
        <ViewInputShokugyou :edit-dto="inputShokugyouDto"></ViewInputShokugyou>

        <div class="footer">
            <button class="footer-button">キャンセル</button>
            <button class="footer-button left-space">送信</button>
        </div>

    </div>
    <!-- 検索コンポーネント -->
    <div v-if="isCorpSearch" class="overBackground"></div>
    <div v-if="isCorpSearch">
        <div class="overComponent">
            <SearchHoujinNo v-if="isCorpSearch" @send-cancel-houjin-no="recieveCancelCorpNo"
                @send-houjin-no-interface="recieveCorpNoInterface"></SearchHoujinNo>
        </div>
    </div>

    <!-- メッセージ表示 -->
    <div class="overMessage" v-if="messageType !== MessageConstants.VIEW_NONE">
        <MessageView :info-level="infoLevel" :message-type="messageType" :title="title" :message="message"
            @send-submit="recieveSubmit">
        </MessageView>
    </div>

</template>
<style scoped>
table {
    border-collapse: collapse;
    width: 3000px;
    /* or a specific width */
}
</style>