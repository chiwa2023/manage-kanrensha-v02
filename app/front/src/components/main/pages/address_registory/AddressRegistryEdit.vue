<script setup lang="ts">
import { PagingControl, type LeastUserDtoInterface } from 'seijishikin-jp-normalize_common-tool';
import { ref, type Ref } from 'vue';
import { getLoginUser } from '../../utils/getLoginUser';
import { SearchAddressRegistoryResultDto, type SearchAddressRegistoryResultDtoInterface } from '../../dto/address_registory/searchAddressRegistoryResultDto';
import mockGetAddressRsdtList from '../../../test/pages/address_resgistory/mock/mockGetAddressRsdtList';
import { AddressRsdtTemplateEntity, type AddressRsdtTemplateEntityInterface } from '../../entity/addressRsdtTemplateEntity';
import { SearchLocalGovernmentCapsuleDto, type SearchLocalGovernmentCapsuleDtoInterface } from '../../dto/address_registory/searchLocalGovernmentCapsuleDto';
import { SearchLocalGovernmentResultDto, type SearchLocalGovernmentResultDtoInterface } from '../../dto/address_registory/searchLocalGovernmentResultDto';
import mockGetLgList from '../../../test/pages/address_resgistory/mock/mockGetLgList';
import ManagerInfo from '../../common/user_info/ManagerInfo.vue';


// よく使う定数
// const BLANK: string = "";
const INIT_NUMBER: number = 0;
const INIT_BOOLEAN: boolean = false;
// const SERVER_STATUS_OK: number = 200;
// const SERVER_STATUS_ERROR: number = 400;
const SEARCH_LIMIT: number = 20;

// ユーザ呼び出し
const userDto: Ref<LeastUserDtoInterface> = ref(getLoginUser());

// 地方自治体検索
const selectedIdLg: Ref<string> = ref("");
const capsuleDtoLocalGov: Ref<SearchLocalGovernmentCapsuleDtoInterface> = ref(new SearchLocalGovernmentCapsuleDto());
const resultDtoLocalGov: Ref<SearchLocalGovernmentResultDtoInterface> = ref(new SearchLocalGovernmentResultDto());

// Paging(地方自治体コード)
const pageNumberLgcode: Ref<number> = ref(INIT_NUMBER);
const allCountLgcode: Ref<number> = ref(INIT_NUMBER);
const limitLgcode: Ref<number> = ref(SEARCH_LIMIT);
// Paging(住所詳細)
const pageNumberDetail: Ref<number> = ref(INIT_NUMBER);
const allCountDetail: Ref<number> = ref(INIT_NUMBER);
const limitDetail: Ref<number> = ref(SEARCH_LIMIT);

// 地方自治体検索
function onSearchLocalGov() {
    resultDtoLocalGov.value = mockGetLgList(capsuleDtoLocalGov.value.pageNumber);
    allCountLgcode.value = resultDtoLocalGov.value.listAllCity.length;

    // 住所の部分一致から自治体コードに紐づくアドレス・ベース・レジストリ住居検索処理(0件メッセージあり)
    // getAuthorizedPromiseArea().then(token => {
    //     if (token !== "") {
    //         // const conditionDto: SaveAddressRegistoryCapsuleInterface = new SaveAddressRegistoryCapsuleEntity();
    //         // conditionDto.addressRsdtTemplateEntity = entityEdit.value;
    //
    //         const url = urlBack + "/local-gov/search";
    //         const method = "POST";
    //         const body = JSON.stringify(null);
    //         const headers = {
    //             'Accept': 'application/json',
    //             'Content-Type': 'application/json',
    //             'X-AUTH-TOKEN': 'Bearer ' + token
    //         };
    //         fetch(url, { method, headers, body })
    //             .then(async (response) => {
    //                 // const resultDto: FrameworkMessageAndResultInterface = await response.json();
    //
    //                 // alert(resultDto.message);
    //             })
    //             .catch((e) => { alert(e); });
    //     } else {
    //         alert("エラーのつもり");
    //     }
    // });

    // pageOptionLocalGov.value = getPagingOption(resultDtoLocalGov.value);
}


// 選択された自治体から住居までリストを取得
// const selectedIdRsdt: Ref<number> = ref(0);
// const pageOptionRsdt: Ref<SelectOptionNumberInterface[]> = ref([]);
// const capsuleDtoRsdt: Ref<SearchAddressRegistoryCapsuleInterface> = ref(new SearchAddressRegistoryCapsuleDto());
const resultDtoRsdt: Ref<SearchAddressRegistoryResultDtoInterface> = ref(new SearchAddressRegistoryResultDto());

function onChangeEditLocalGov(id: number) {
    //const entityLg: AddressAllCityInterface = resultDtoLocalGov.value.listAllCity.filter((e) => e.addressAllCityId === id)[0];
    resultDtoRsdt.value = mockGetAddressRsdtList("12345");
    allCountDetail.value = resultDtoRsdt.value.listRsdt.length;
    // 自治体コードをキーにした検索処理
    // getAuthorizedPromiseArea().then(token => {
    //     if (token !== "") {
    //         // const conditionDto: SaveAddressRegistoryCapsuleInterface = new SaveAddressRegistoryCapsuleEntity();
    //         // conditionDto.addressRsdtTemplateEntity = entityEdit.value;
    //
    //         const url = urlBack + "/address-regi-rsdt/search";
    //         const method = "POST";
    //         const body = JSON.stringify(null);
    //         const headers = {
    //             'Accept': 'application/json',
    //             'Content-Type': 'application/json',
    //             'X-AUTH-TOKEN': 'Bearer ' + token
    //         };
    //         fetch(url, { method, headers, body })
    //             .then(async (response) => {
    //                 // const resultDto: FrameworkMessageAndResultInterface = await response.json();
    //
    //                 // alert(resultDto.message);
    //             })
    //             .catch((e) => { alert(e); });
    //     } else {
    //         alert("エラーのつもり");
    //     }
    // });

    // pageOptionRsdt.value = getPagingOption(resultDtoRsdt.value);
}



function onCancel() {
    alert("キャンセル");
    history.back();
}
function onSave() {
    alert("保存");

    // アドレス・ベース・レジストリ住居　保存処理
    // getAuthorizedPromiseArea().then(token => {
    //     if (token !== "") {
    //         // const conditionDto: SaveAddressRegistoryCapsuleInterface = new SaveAddressRegistoryCapsuleEntity();
    //         // conditionDto.addressRsdtTemplateEntity = entityEdit.value;
    //
    //         const url = urlBack + "/address-regi-rsdt/save";
    //         const method = "POST";
    //         const body = JSON.stringify(null);
    //         const headers = {
    //             'Accept': 'application/json',
    //             'Content-Type': 'application/json',
    //             'X-AUTH-TOKEN': 'Bearer ' + token
    //         };
    //         fetch(url, { method, headers, body })
    //             .then(async (response) => {
    //                 // const resultDto: FrameworkMessageAndResultInterface = await response.json();
    //
    //                 // alert(resultDto.message);
    //             })
    //             .catch((e) => { alert(e); });
    //     } else {
    //         alert("エラーのつもり");
    //     }
    // });

}


const isAddressEdit: Ref<boolean> = ref(INIT_BOOLEAN);
const editEntity: Ref<AddressRsdtTemplateEntityInterface> = ref(new AddressRsdtTemplateEntity());
function onEdit(selectedId: number) {
    const tempEntity: AddressRsdtTemplateEntityInterface | undefined =
        resultDtoRsdt.value.listRsdt.filter((e) => selectedId === e.addressRsdtId)[0];
    if (tempEntity !== undefined) {
        storedId.value = selectedId;
        editEntity.value = tempEntity;
        isAddressEdit.value = true;
    }
}
function recieveCancelInputAddress() {
    isAddressEdit.value = false;
}

const storedId: Ref<number> = ref(INIT_NUMBER);

function recieveInputAddressInterface(dto: AddressRsdtTemplateEntityInterface) {

    const tempEntity: AddressRsdtTemplateEntityInterface | undefined =
        resultDtoRsdt.value.listRsdt.filter((e) => storedId.value === e.addressRsdtId)[0];
    if (tempEntity !== undefined) {
        // 型が異なるので1フィールドずつ複写
        tempEntity.postalcode1 = dto.postalcode1;
        tempEntity.postalcode2 = dto.postalcode2;
        tempEntity.addressPostal = dto.addressPostal;
        tempEntity.addressBlock = dto.addressBlock;
        tempEntity.addressBuilding = dto.addressBuilding;
        tempEntity.lgCode = dto.lgCode;
        tempEntity.machiazaId = dto.machiazaId;
        tempEntity.blkId = dto.blkId;
        tempEntity.prcId = dto.prcId;
        tempEntity.rsdtId = dto.rsdtId;
        tempEntity.rsdt2Id = dto.rsdt2Id;

        tempEntity.effectDate = dto.effectDate;
        tempEntity.abolishDate = dto.abolishDate;
    }
    isAddressEdit.value = false;
}

function recievePagingNumberDetail() {

}
function recievePagingNumberLgcode() {

}


</script>
<template>
    <!-- 管理者メニュー兼チェック -->
    <ManagerInfo :user-dto="userDto"></ManagerInfo>

    <h1>アドレスベースレジストリ編集</h1>

    <h3>編集地方自治体の指定</h3>

    <!-- ページング(API接続者) -->
    <div class="one-line">
        <div class="left-area">
            検索条件(部分一致)
        </div>
        <div class="right-area">
            <input type="text" v-model="capsuleDtoLocalGov.addressWords">
        </div>
    </div>

    <div class="one-line">
        <div class="left-area">
            検索
        </div>
        <div class="right-area">
            <button @click="onSearchLocalGov">検索</button>
        </div>
    </div>


    <div class="one-line-scroll">
        <table>
            <tbody>
                <tr>
                    <th>&nbsp;</th>
                    <th>地方公共団体コード</th>
                    <th>地方団体名</th>
                </tr>
                <tr v-for="entity of resultDtoLocalGov.listAllCity" :key="entity.addressAllCityId">
                    <td><input type="radio" v-model="selectedIdLg" id="listLg" :value="entity.addressAllCityId"
                            @click="onChangeEditLocalGov(entity.addressAllCityId)"> </td>
                    <td>{{ entity.lgCode }}</td>
                    <td>{{ entity.addressName }}</td>
                </tr>
            </tbody>
        </table>
    </div>
    <!-- ページング  -->
    <PagingControl :all-count="allCountLgcode" :limit="limitLgcode" :page-number="pageNumberLgcode"
        @send-paging-number="recievePagingNumberLgcode"></PagingControl>

    <h3>地方自治体詳細検索結果</h3>

    <div class="one-line-scroll">
        <table>
            <tbody>
                <tr>
                    <th>地方公共団体コード</th>
                    <th>郵便番号</th>
                    <th>町字コード</th>
                    <th>街区コード</th>
                    <th>地番コード</th>
                    <th>住所番地まで</th>
                    <th>住所建物</th>
                    <th>&nbsp;</th>
                    <th>&nbsp;</th>
                </tr>
                <tr v-for="entity of resultDtoRsdt.listRsdt" :key="entity.addressRsdtId">
                    <td>{{ entity.lgCode }}</td>
                    <td>{{ entity.postalcode1 }} - {{ entity.postalcode2 }}</td>
                    <td>{{ entity.machiazaId }}</td>
                    <td>{{ entity.blkId }}</td>
                    <td>{{ entity.prcId }}</td>
                    <td>{{ entity.addressBlock }}</td>
                    <td>{{ entity.addressBuilding }}</td>
                    <td><button @click="onEdit(entity.addressRsdtId)">編集</button></td>
                    <td><button>削除</button></td>
                </tr>
            </tbody>
        </table>
    </div>
    <!-- ページング -->
    <PagingControl :all-count="allCountDetail" :limit="limitDetail" :page-number="pageNumberDetail"
        @send-paging-number="recievePagingNumberDetail"></PagingControl>

    <!-- 検索コンポーネント -->
    <div v-if="isAddressEdit" class="overBackground"></div>
    <div class="overComponent" v-if="isAddressEdit">
        <EditAddress :edit-dto="editEntity" @send-cancel-input-address="recieveCancelInputAddress"
            @send-input-address-interface="recieveInputAddressInterface"></EditAddress>
    </div>

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
    text-align: center;
}
</style>
