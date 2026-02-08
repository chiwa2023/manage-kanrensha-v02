<script setup lang="ts">
import { ref, type Ref } from 'vue';
import type { KanrenshaPersonPropertyEntityInterface } from '../../entity/kanrenshaPersonPropertyEntity';
import mockGetPersonApprovaShokugyouList from '../../../test/pages/works_approval/mock/mockGetPersonApprovalShokugyouList';
import { InputCompareShokugyou, InputShokugyouDto, type InputShokugyouDtoInterface } from 'seijishikin-jp-normalize_common-tool';
//仮
// よく使う定数
const BLANK: string = "";
const INIT_NUMBER: number = 0;
const INIT_BOOLEAN: boolean = false;
// const SERVER_STATUS_OK: number = 200;
// const SERVER_STATUS_ERROR: number = 400;
// const SEARCH_LIMIT: number = 20;
// メッセージボックス表示定数
//const infoLevel: Ref<number> = ref(MessageConstants.LEVEL_NONE);
//const messageType: Ref<number> = ref(MessageConstants.VIEW_NONE);
//const title: Ref<string> = ref(BLANK);
//const message: Ref<string> = ref(BLANK);

//初期表示日時
const yesterday: Date = new Date();
yesterday.setDate(yesterday.getDate() - 1);
const yesterdayText: string = yesterday.toISOString().substring(0, 10);

// 検索期間
const searchStartDate: Ref<string> = ref(yesterdayText);
const searchEndDate: Ref<string> = ref(yesterdayText);

const isSearchApproval: Ref<boolean> = ref(true);

const listPersonShokugyou: Ref<KanrenshaPersonPropertyEntityInterface[]> = ref([]);
function onSearch() {
    listPersonShokugyou.value = mockGetPersonApprovaShokugyouList();
}

const inputShokugyouDto: Ref<InputShokugyouDtoInterface> = ref(new InputShokugyouDto());
const isShokugyouInput: Ref<boolean> = ref(INIT_BOOLEAN);
const storedId: Ref<number> = ref(INIT_NUMBER);
function onShokugyouInput(selectedId: number) {

    const tempEntity: KanrenshaPersonPropertyEntityInterface | undefined
        = listPersonShokugyou.value.filter((e) => selectedId === e.kanrenshaPersonPropertyId)[0];

    if (tempEntity !== undefined) {
        storedId.value = selectedId;
        // dtoとentityで型が異なるで1件ずつつなぎ合わせる
        inputShokugyouDto.value.gyoushu = tempEntity.gyoushu;
        inputShokugyouDto.value.yakushoku = tempEntity.yakushoku;
        inputShokugyouDto.value.shokugyouUserWrite = tempEntity.shokugyouUserWrite;
        inputShokugyouDto.value.houjinNo = tempEntity.kigyouDtNo;
        inputShokugyouDto.value.houjinName = tempEntity.kigyouDtName;
        inputShokugyouDto.value.houjinAddress = tempEntity.kigyouDtAddress;

        isShokugyouInput.value = true;

    }

}

function recieveCancelInputShokugyou() {
    isShokugyouInput.value = false;
}
function recieveInputShokugyouInterface(dataDto: InputShokugyouDtoInterface) {

    const tempEntity: KanrenshaPersonPropertyEntityInterface | undefined
        = listPersonShokugyou.value.filter((e) => storedId.value === e.kanrenshaPersonPropertyId)[0];

    // dtoとentityで型が異なるで1件ずつつなぎ合わせる
    if (tempEntity !== undefined) {
        //tempEntity.allShokugyou = dataDto.allShokugyou;
        tempEntity.gyoushu = dataDto.gyoushu;
        tempEntity.yakushoku = dataDto.yakushoku;
        tempEntity.shokugyouUserWrite = dataDto.shokugyouUserWrite;
        tempEntity.kigyouDtNo = dataDto.houjinNo;
        tempEntity.kigyouDtName = dataDto.houjinName;
        tempEntity.kigyouDtAddress = dataDto.houjinAddress;
        tempEntity.isShokyouEdit = (BLANK !== dataDto.shokugyouUserWrite);

    }

    isShokugyouInput.value = false;

}

function onCancel() {
    history.back();

}
function onSave() {
    alert("保存");
}
</script>
<template>
    <h1>個人職業</h1>

    <div class="one-line">
        <div class="left-area">
            検索期間
        </div>
        <div class="right-area">
            <span><input type="date" v-model="searchStartDate" value="1">から</span>
            <span class="left-space"><input type="date" v-model="searchEndDate" value="3">まで</span>
        </div>
    </div>
    <div class="one-line">
        <div class="left-area">
            承認必要のみ
        </div>
        <div class="right-area">
            <input type="checkbox" v-model="isSearchApproval">承認必要作業のみ表示する
        </div>
    </div>
    <div class="one-line">
        <div class="left-area">
            検索
        </div>
        <div class="right-area">
            <button @click="onSearch">検索</button>
        </div>
    </div>


    <h3>検索結果(職業)</h3>
    <div class="one-line-scroll">
        <table>
            <tbody>
                <tr>
                    <th>関連者番号</th>
                    <th>名前</th>
                    <th>業種</th>
                    <th>役職</th>
                    <th>自己申告職業</th>
                    <th>法人番号</th>
                    <th>法人名称</th>
                    <th>法人住所</th>
                    <th>編集</th>
                    <th>承認</th>
                    <th>&nbsp;</th>
                </tr>
            </tbody>
            <tbody v-for="entity in listPersonShokugyou" :key="entity.kanrenshaPersonPropertyId">
                <tr>
                    <td>{{ entity.personKanrenshaCode }} </td>
                    <td>{{ entity.kanrenshaName }} </td>
                    <td>{{ entity.gyoushu }} </td>
                    <td>{{ entity.yakushoku }} </td>
                    <td>{{ entity.shokugyouUserWrite }} </td>
                    <td>{{ entity.kigyouDtNo }} </td>
                    <td>{{ entity.kigyouDtName }} </td>
                    <td>{{ entity.kigyouDtAddress }} </td>
                    <td><input type="checkbox" v-model="entity.isShokyouEdit" disabled="true">編集あり</td>
                    <td><input type="checkbox" v-model="entity.isShokyouAccept">登録内容を承認</td>
                    <td><button @click="onShokugyouInput(entity.kanrenshaPersonPropertyId)">編集</button></td>
                </tr>
            </tbody>
        </table>
    </div>




    <!-- 職業入力(紐づけなし) -->
    <div v-if="isShokugyouInput" class="overBackground"></div>
    <div v-if="isShokugyouInput" class="overComponent">
        <InputCompareShokugyou :edit-dto="inputShokugyouDto" :isfooter="true"
            @send-cancel-input-shokugyou="recieveCancelInputShokugyou"
            @send-input-shokugyou-interface="recieveInputShokugyouInterface">
        </InputCompareShokugyou>
    </div>

    <div class="footer">
        <button @click="onCancel" class="footer-button">キャンセル</button>
        <button @click="onSave" class="footer-button left-space">送信</button>
    </div>

</template>
<style scoped></style>
