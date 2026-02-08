<script setup lang="ts">
import { ref, type Ref } from 'vue';
import mockGetPersonApprovalAddressList from '../../../test/pages/works_approval/mock/mockGetPersonApprovalAddressList';
import type { KanrenshaPersonAddressEntityInterface } from '../../entity/kanrenshaPersonAddressEntity';
import { InputAddressDto, InputCompareAddress, PagingControl, type InputAddressDtoInterface } from 'seijishikin-jp-normalize_common-tool';

//仮
// よく使う定数
// const BLANK: string = "";
const INIT_NUMBER: number = 0;
const INIT_BOOLEAN: boolean = false;
// const SERVER_STATUS_OK: number = 200;
// const SERVER_STATUS_ERROR: number = 400;
const SEARCH_LIMIT: number = 20;
// メッセージボックス表示定数
//const infoLevel: Ref<number> = ref(MessageConstants.LEVEL_NONE);
//const messageType: Ref<number> = ref(MessageConstants.VIEW_NONE);
//const title: Ref<string> = ref(BLANK);
//const message: Ref<string> = ref(BLANK);

// Paging
const pageNumber: Ref<number> = ref(INIT_NUMBER);
const allCount: Ref<number> = ref(INIT_NUMBER);
const limit: Ref<number> = ref(SEARCH_LIMIT);



//初期表示日時
const yesterday: Date = new Date();
yesterday.setDate(yesterday.getDate() - 1);
const yesterdayText: string = yesterday.toISOString().substring(0, 10);

// 検索期間
const searchStartDate: Ref<string> = ref(yesterdayText);
const searchEndDate: Ref<string> = ref(yesterdayText);

const isSearchApproval: Ref<boolean> = ref(true);

const isPortalAddressInput: Ref<boolean> = ref(INIT_BOOLEAN);
const editDto: Ref<InputAddressDtoInterface> = ref(new InputAddressDto());

const listPersonAdsdress: Ref<KanrenshaPersonAddressEntityInterface[]> = ref([]);
function onSearch() {
    listPersonAdsdress.value = mockGetPersonApprovalAddressList();
    allCount.value = listPersonAdsdress.value.length;
}

const storedId: Ref<number> = ref(INIT_NUMBER);
function onPortalAddressInput(selectedId: number) {

    const tempEntity: KanrenshaPersonAddressEntityInterface | undefined
        = listPersonAdsdress.value.filter((e) => selectedId === e.kanrenshaPersonAddressId)[0];

    if (tempEntity !== undefined) {
        storedId.value = selectedId;
        editDto.value.postalcode1 = tempEntity.postalcode1;
        editDto.value.postalcode2 = tempEntity.postalcode2;
        editDto.value.addressPostal = tempEntity.addressPostal;
        editDto.value.addressBlock = tempEntity.addressBlock;
        editDto.value.addressBuilding = tempEntity.addressBuilding;
        editDto.value.lgCode = tempEntity.lgCode;
        editDto.value.machiazaId = tempEntity.machiazaId;
        editDto.value.blkId = tempEntity.blkId;
        editDto.value.prcId = tempEntity.prcId;
        editDto.value.rsdtId = tempEntity.rsdtId;
        editDto.value.rsdt2Id = tempEntity.rsdt2Id;
        editDto.value.isPostalEdit = tempEntity.isPostalEdit;
        editDto.value.isBlockEdit = tempEntity.isBlockEdit;
        editDto.value.isBuildingEdit = tempEntity.isBuildingEdit;
        isPortalAddressInput.value = true;
    }

}
function recieveCancelInputPortalAddress() {
    isPortalAddressInput.value = false;
}
function recieveInputPortalAddressInterface(addressDto: InputAddressDtoInterface) {

    const tempEntity: KanrenshaPersonAddressEntityInterface | undefined
        = listPersonAdsdress.value.filter((e) => storedId.value === e.kanrenshaPersonAddressId)[0];

    if (tempEntity !== undefined) {
        tempEntity.postalcode1 = addressDto.postalcode1;
        tempEntity.postalcode2 = addressDto.postalcode2;
        tempEntity.addressPostal = addressDto.addressPostal;
        tempEntity.addressBlock = addressDto.addressBlock;
        tempEntity.addressBuilding = addressDto.addressBuilding;
        tempEntity.lgCode = addressDto.lgCode;
        tempEntity.machiazaId = addressDto.machiazaId;
        tempEntity.blkId = addressDto.blkId;
        tempEntity.prcId = addressDto.prcId;
        tempEntity.rsdtId = addressDto.rsdtId;
        tempEntity.rsdt2Id = addressDto.rsdt2Id;
        tempEntity.isPostalEdit = addressDto.isPostalEdit;
        tempEntity.isBlockEdit = addressDto.isBlockEdit;
        tempEntity.isBuildingEdit = addressDto.isBuildingEdit;
    }
    isPortalAddressInput.value = false;
}

function recievePagingNumber(selecteddNumber: number) {
    pageNumber.value = selecteddNumber;
    alert("ページ情報受信");
}

function onCancel() {
    history.back();

}
function onSave() {
    alert("保存");
}

</script>
<template>
    <h3>個人住所検索条件</h3>

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

    <h3>個人検索結果(住所)</h3>
    <div class="one-line-scroll">
        <table>
            <tbody>
                <tr>
                    <th>関連者番号</th>
                    <th>名前</th>
                    <th>住所郵便番号まで</th>
                    <th>住所番地</th>
                    <th>住所建物</th>
                    <th>承認</th>
                    <th>&nbsp;</th>
                </tr>
            </tbody>
            <tbody v-for="entity in listPersonAdsdress" :key="entity.kanrenshaPersonAddressId">
                <tr>
                    <td>{{ entity.personKanrenshaCode }} </td>
                    <td>{{ entity.kanrenshaName }} </td>
                    <td><input type="text" v-model="entity.addressPostal" :disabled="!entity.isPostalEdit">
                        <br><input type="checkbox" v-model="entity.isPostalEdit">編集あり
                    </td>
                    <td><input type="text" v-model="entity.addressBlock" :disabled="!entity.isBlockEdit">
                        <br><input type="checkbox" v-model="entity.isBlockEdit">編集あり
                    </td>
                    <td><input type="text" v-model="entity.addressBuilding" :disabled="!entity.isBuildingEdit">
                        <br><input type="checkbox" v-model="entity.isBuildingEdit">編集あり
                    </td>
                    <td>
                        <input type="checkbox" v-model="entity.isPostalAccept">郵便番号まで住所を承認<br>
                        <input type="checkbox" v-model="entity.isBlockAccept">番地まで住所を承認<br>
                        <input type="checkbox" v-model="entity.isBuildingAccept">建物住所を承認
                    </td>
                    <td><button @click="onPortalAddressInput(entity.kanrenshaPersonAddressId)">編集</button></td>
                </tr>
            </tbody>
        </table>
    </div>
    <!-- Paging         -->
    <PagingControl :all-count="allCount" :limit="limit" :page-number="pageNumber"
        @send-paging-number="recievePagingNumber"></PagingControl>


    <!-- 住所入力 -->
    <div v-if="isPortalAddressInput" class="overBackground"></div>
    <div v-if="isPortalAddressInput" class="overComponent">
        <InputCompareAddress :edit-dto="editDto" @send-cancel-input-address="recieveCancelInputPortalAddress"
            @send-input-address-interface="recieveInputPortalAddressInterface"></InputCompareAddress>
    </div>

    <div class="footer">
        <button @click="onCancel" class="footer-button">キャンセル</button>
        <button @click="onSave" class="footer-button left-space">送信</button>
    </div>


</template>
<style scoped></style>
