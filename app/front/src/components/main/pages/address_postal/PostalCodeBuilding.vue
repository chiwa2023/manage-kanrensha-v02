<script setup lang="ts">
import { InputAddressDto, InputBuildingAddress, MessageConstants, MessageView, PagingControl, type InputAddressDtoInterface, type LeastUserDtoInterface } from 'seijishikin-jp-normalize_common-tool';
import { onBeforeMount, ref, type Ref } from 'vue';
import { getLoginUser } from '../../utils/getLoginUser';
import { SearchPostalIllegularResultDto, type SearchPostalIllegularResultDtoInterface } from '../../dto/address_postal/searchPostalIllegularResultDto';
import mockGetIllegularItem from '../../../test/pages/address_postal/mock/mockGetIllegularItem';
import { GetDetailPostalIllegularResultDto, type GetDetailPostalIllegularResultDtoInterface } from '../../dto/address_postal/getDetailPostalIllegularResultDto';
import { PostalIrregularItemDto, type PostalIrregularItemDtoInterface } from '../../dto/address_postal/postalIrregularItemDto';
import mockGetIllegularAddress from '../../../test/pages/address_postal/mock/mockGetIllegularAddress';
import ManagerInfo from '../../common/user_info/ManagerInfo.vue';


// よく使う定数
const BLANK: string = "";
const INIT_NUMBER: number = 0;
const INIT_BOOLEAN: boolean = false;
// const SERVER_STATUS_OK: number = 200;
// const SERVER_STATUS_ERROR: number = 400;
const SEARCH_LIMIT: number = 20;
// メッセージ表示定数
const infoLevel: Ref<number> = ref(MessageConstants.LEVEL_NONE);
const messageType: Ref<number> = ref(MessageConstants.VIEW_NONE);
const title: Ref<string> = ref(BLANK);
const message: Ref<string> = ref(BLANK);

// ユーザ呼び出し
const userDto: Ref<LeastUserDtoInterface> = ref(getLoginUser());

// Paging
const pageNumber: Ref<number> = ref(INIT_NUMBER);
const allCount: Ref<number> = ref(INIT_NUMBER);
const limit: Ref<number> = ref(SEARCH_LIMIT);

const isAddressInput: Ref<boolean> = ref(INIT_BOOLEAN);
const commonAddress: Ref<string> = ref("");


const resultDtoItem: Ref<SearchPostalIllegularResultDtoInterface> = ref(new SearchPostalIllegularResultDto());

onBeforeMount(() => {
    //const capsuleDtoItem: Ref<FrameworkPagingDtoInterface> = ref(new FrameworkPagingDto());
    resultDtoItem.value = mockGetIllegularItem();
    allCount.value = resultDtoItem.value.listItem.length;


    // 初期で建物住所を全件取得する
    // 建物の地階データを全件取得する
    // getAuthorizedPromiseArea().then(token => {
    //     if (token !== "") {
    //         // const conditionDto: SaveAddressRegistoryCapsuleInterface = new SaveAddressRegistoryCapsuleEntity();
    //         // conditionDto.addressRsdtTemplateEntity = entityEdit.value;
    //
    //         const url = urlBack + "/postal-irregular/building";
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


});



// 編集対象の詳細リスト
// const capsuleDtoIllegular: Ref<GetDetailPostalIllegularCapsuleInterface> = ref(new GetDetailPostalIllegularCapsuleDto());
const resultDtoIllegular: Ref<GetDetailPostalIllegularResultDtoInterface> = ref(new GetDetailPostalIllegularResultDto());

const editDto: Ref<InputAddressDtoInterface> = ref(new InputAddressDto());

// 編集対象の変更
const dtoEdit: Ref<PostalIrregularItemDtoInterface> = ref(new PostalIrregularItemDto());
const storeId: Ref<number> = ref(INIT_NUMBER);
function onChangeEdit(id: number) {

    // 住所入力がされている場合は保存せずに消去していいか確認
    if (BLANK !== commonAddress.value) {
        storeId.value = id;
        infoLevel.value = MessageConstants.LEVEL_WARNING;
        title.value = "未保存データが存在";
        message.value = "住所入力がされています。保存せず新たな編集対象を表示してよいですか?";
        messageType.value = MessageConstants.VIEW_YES_NO;

        return;
    } else {
        onShowDetail(id);
    }



    // capsuleDtoIllegular.value.lgCode = dtoEdit.value.lgCode;

    // 選択された建物の全フロアデータを取得する
    // getAuthorizedPromiseArea().then(token => {
    //     if (token !== "") {
    //         // const conditionDto: SaveAddressRegistoryCapsuleInterface = new SaveAddressRegistoryCapsuleEntity();
    //         // conditionDto.addressRsdtTemplateEntity = entityEdit.value;
    //
    //         const url = urlBack + "/postal-irregular/building-detail";
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


function onShowDetail(id: number) {

    const tempDto: PostalIrregularItemDtoInterface | undefined
        = resultDtoItem.value.listItem.filter(e => e.addressPostalIrregularId === id)[0];
    if (tempDto !== undefined) {
        dtoEdit.value = tempDto;
    }
    resultDtoIllegular.value = mockGetIllegularAddress(dtoEdit.value.addressName);
    commonAddress.value = BLANK;
}




function onCancel() {
    history.back();

}
function onSave() {
    alert("保存");

    // 入力された住所を全フロア住所に展開する
    // getAuthorizedPromiseArea().then(token => {
    //     if (token !== "") {
    //         // const conditionDto: SaveAddressRegistoryCapsuleInterface = new SaveAddressRegistoryCapsuleEntity();
    //         // conditionDto.addressRsdtTemplateEntity = entityEdit.value;
    //
    //         const url = urlBack + "/postal-irregular/save-building";
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
    commonAddress.value = BLANK;
}

function recievePagingNumber(selecteddNumber: number) {
    pageNumber.value = selecteddNumber;
    alert("ページ情報受信");
}

function onAddressInput() {
    isAddressInput.value = true;
}

function recieveInputAddressInterface(data: InputAddressDtoInterface) {

    // 編集内容を複写
    const postal: string = data.addressPostal;
    const block: string = data.addressBlock;

    commonAddress.value = postal + block;
    for (const entity of resultDtoIllegular.value.listIrregular) {
        entity.addressPostal = postal;
        entity.addressBlock = block;
    }

    isAddressInput.value = false;
}
function recieveCancelInputAddress() {
    isAddressInput.value = false;
}

function recieveSubmit(button: string) {

    if ("yes" === button) {
        onShowDetail(storeId.value);
    }

    // 非表示
    infoLevel.value = 0;
    messageType.value = 0;
}
</script>
<template>
    <!-- 管理者メニュー兼チェック -->
    <ManagerInfo :user-dto="userDto"></ManagerInfo>

    <h1>フロア郵便番号追加</h1>

    <h3>フロア郵便番号建物検索</h3>
    <div class="one-line-scroll">
        <table>
            <tbody>
                <tr>
                    <th>郵便番号</th>
                    <th>住所名</th>
                    <th>地方自治体コード</th>
                    <th>住所郵便番号まで</th>
                    <th>住所番地まで</th>
                    <th>&nbsp;</th>

                </tr>
                <tr v-for="dto of resultDtoItem.listItem" :key="dto.addressPostalIrregularId">
                    <td>{{ dto.postal1 }}</td>
                    <td>{{ dto.addressName }}</td>
                    <td>{{ dto.lgCode }}</td>
                    <td>{{ dto.inputAddress.addressPostal }}</td>
                    <td>{{ dto.inputAddress.addressBlock }}</td>
                    <td><button @click="onChangeEdit(dto.addressPostalIrregularId)">編集</button></td>
                </tr>
            </tbody>
        </table>
    </div>
    <!-- ページング  -->
    <PagingControl :all-count="allCount" :limit="limit" :page-number="pageNumber"
        @send-paging-number="recievePagingNumber"></PagingControl>

    <h3>該当建物詳細</h3>

    <div class="one-line-scroll">
        <table>
            <tbody>
                <tr>
                    <th>郵便番号</th>
                    <th>住所共通</th>
                    <th>住所詳細</th>
                    <th>住所郵便番号まで</th>
                    <th>住所番地まで</th>
                </tr>
                <tr v-for="entity of resultDtoIllegular.listIrregular" :key="entity.addressPostalIrregularId">
                    <td>{{ entity.postal1 }}</td>
                    <td>{{ entity.addressName }}</td>
                    <td>{{ entity.addressOrg }}</td>
                    <td>{{ entity.addressPostal }}</td>
                    <td>{{ entity.addressBlock }}</td>
                </tr>
            </tbody>
        </table>
    </div>
    <div class="clear-both"><br></div>

    <h3>住所入力</h3>
    <div class="one-line">
        <div class="left-area">
            共通住所
        </div>
        <div class="right-area">
            <input type="text" v-model="commonAddress" disabled="true"><button @click="onAddressInput">編集</button>
        </div>
    </div>

    <div class="footer">
        <button @click="onCancel" class="footer-button">キャンセル</button>
        <button @click="onSave" class="footer-button left-space">送信</button>
    </div>

    <!-- 専用住所入力 -->
    <div v-if="isAddressInput" class="overBackground"></div>
    <div v-if="isAddressInput" class="overComponent">
        <InputBuildingAddress :edit-dto="editDto" @send-cancel-input-address="recieveCancelInputAddress"
            @send-input-address-interface="recieveInputAddressInterface"></InputBuildingAddress>
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
