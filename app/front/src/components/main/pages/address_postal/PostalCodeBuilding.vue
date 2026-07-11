<script setup lang="ts">
import { FrameworkPagingDto, getErrorMessage, getErrorUniqueIdMessage, InputAddressDto, InputBuildingAddress, MessageConstants, MessageView, PagingControl, type FrameworkMessageAndResultDtoInterface, type FrameworkPagingDtoInterface, type InputAddressDtoInterface, type LeastUserDtoInterface } from 'seijishikin-jp-normalize_common-tool';
import { onMounted, ref, type Ref } from 'vue';
import { getLoginUser } from '../../utils/getLoginUser';
import { SearchPostalIllegularResultDto, type SearchPostalIllegularResultDtoInterface } from '../../dto/address_postal/searchPostalIllegularResultDto';
import { GetDetailPostalIllegularResultDto, type GetDetailPostalIllegularResultDtoInterface } from '../../dto/address_postal/getDetailPostalIllegularResultDto';
import ManagerInfo from '../../common/user_info/ManagerInfo.vue';
import getAuthorizedPromiseArea from '../../dto/login/getAuthorizedPromiseArea.ts';
import RoutePathConstants from '../../../../routePathConstants.ts';
import { AccessTokenNotFoundError, TokenRefreshError } from '../../dto/login/errors.ts';
import { GetDetailPostalIllegularCapsuleDto, type GetDetailPostalIllegularCapsuleDtoInterface } from '../../dto/address_postal/getDetailPostalIllegularCapsuleDto.ts';
import { SavePostalIrregularCapsuleDto, type SavePostalIrregularCapsuleDtoInterface } from '../../dto/address_postal/savePostalIrregularCapsuleDto.ts';
import { AddressPostalIrregularEntity, type AddressPostalIrregularEntityInterface } from '../../entity/addressPostalIrregularEntity.ts';

// よく使う定数
const BLANK: string = "";
const INIT_NUMBER: number = 0;
const INIT_BOOLEAN: boolean = false;
// const SERVER_STATUS_OK: number = 200;
// const SERVER_STATUS_ERROR: number = 400;
const SEARCH_LIMIT: number = 20;
const INQUIRE_FLG: boolean = false;
const ERR_MESS_ONLY: boolean = true;
const MESS_PAGE_NAME: string = "フロア郵便番号追加";
const INIT_CALLER: string = "no branch";

// メッセージ表示定数
const infoLevel: Ref<number> = ref(MessageConstants.LEVEL_NONE);
const messageType: Ref<number> = ref(MessageConstants.VIEW_NONE);
const caller: Ref<string> = ref(INIT_CALLER);
const message: Ref<string> = ref(BLANK);

// back側アクセス
const urlBack: string = RoutePathConstants.DOMAIN + RoutePathConstants.BASE_PATH;

// ユーザ呼び出し
const userDto: Ref<LeastUserDtoInterface> = ref(getLoginUser());

// Paging
const pageNumber: Ref<number> = ref(INIT_NUMBER);
const allCount: Ref<number> = ref(INIT_NUMBER);
const limit: Ref<number> = ref(SEARCH_LIMIT);

const isAddressInput: Ref<boolean> = ref(INIT_BOOLEAN);
const commonAddress: Ref<string> = ref("");


const capsuleDtoItem: Ref<FrameworkPagingDtoInterface> = ref(new FrameworkPagingDto());
const resultDtoItem: Ref<SearchPostalIllegularResultDtoInterface> = ref(new SearchPostalIllegularResultDto());
onMounted(() => {

    // 初期で建物住所を取得する
    onSearch();
});

function onSearch() {

    // 建物の地階データを取得する
    capsuleDtoItem.value.allCount = allCount.value;
    capsuleDtoItem.value.limit = limit.value;
    capsuleDtoItem.value.pageNumber = pageNumber.value;

    getAuthorizedPromiseArea().then(token => {
        const url = urlBack + "/postal-irregular/building";
        const method = "POST";
        const body = JSON.stringify(capsuleDtoItem.value);
        const headers = {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'X-AUTH-TOKEN': 'Bearer ' + token
        };
        fetch(url, { method, headers, body })
            .then(async (response) => {
                resultDtoItem.value = await response.json();
                allCount.value = resultDtoItem.value.allCount;
                limit.value = resultDtoItem.value.limit;
                pageNumber.value = resultDtoItem.value.pageNumber;

                if (resultDtoItem.value.allCount == 0) {
                    infoLevel.value = MessageConstants.LEVEL_WARNING;
                    messageType.value = MessageConstants.VIEW_OK;
                    message.value = "検索結果が存在しませんでした";
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

// 編集対象の詳細リスト
const resultDtoIllegular: Ref<GetDetailPostalIllegularResultDtoInterface> = ref(new GetDetailPostalIllegularResultDto());

const editAddressDto: Ref<InputAddressDtoInterface> = ref(new InputAddressDto());
// 編集対象の変更
const entityEdit: Ref<AddressPostalIrregularEntityInterface> = ref(new AddressPostalIrregularEntity());

const storeId: Ref<number> = ref(INIT_NUMBER);
const showText: string = "show";
function onChangeEdit(id: number) {

    // 住所入力がされている場合は保存せずに消去していいか確認
    if (BLANK !== commonAddress.value) {
        storeId.value = id;
        infoLevel.value = MessageConstants.LEVEL_WARNING;
        message.value = "住所入力がされて未保存データです。保存せず新たな編集対象を表示してよいですか?";
        messageType.value = MessageConstants.VIEW_YES_NO;
        caller.value = showText;
        return;
    } else {
        onShowDetail(id);
    }
}


function onShowDetail(id: number) {
    const tempDto: AddressPostalIrregularEntityInterface | undefined
        = resultDtoItem.value.listItem.filter(e => e.addressPostalIrregularId === id)[0];
    if (tempDto === undefined) {
        infoLevel.value = MessageConstants.LEVEL_ERROR;
        messageType.value = MessageConstants.VIEW_OK;
        message.value = getErrorUniqueIdMessage(id);
        return;
    }

    entityEdit.value = tempDto;

    // 同一建物のデータをすべて取得
    const capsuleDto: GetDetailPostalIllegularCapsuleDtoInterface = new GetDetailPostalIllegularCapsuleDto();
    capsuleDto.addressWords = entityEdit.value.addressName;

    getAuthorizedPromiseArea().then(token => {
        const url = urlBack + "/postal-irregular/building-detail";
        const method = "POST";
        const body = JSON.stringify(capsuleDto);
        const headers = {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'X-AUTH-TOKEN': 'Bearer ' + token
        };
        fetch(url, { method, headers, body })
            .then(async (response) => {
                resultDtoIllegular.value = await response.json();

                if (resultDtoItem.value.allCount == 0) {
                    infoLevel.value = MessageConstants.LEVEL_WARNING;
                    messageType.value = MessageConstants.VIEW_OK;
                    message.value = "検索結果が存在しませんでした";
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
    commonAddress.value = BLANK;
}


function onCancel() {
    history.back();

}
function onSave() {
    // 入力された住所を全フロア住所に展開する
    const capsuleDto: SavePostalIrregularCapsuleDtoInterface = new SavePostalIrregularCapsuleDto();
    capsuleDto.userDto = userDto.value;
    entityEdit.value.addressPostal = editAddressDto.value.addressPostal;
    entityEdit.value.addressBlock = editAddressDto.value.addressBlock;
    capsuleDto.addressPostalIrregularEntity = entityEdit.value;

    getAuthorizedPromiseArea().then(token => {
        const url = urlBack + "/postal-irregular/save-building";
        const method = "POST";
        const body = JSON.stringify(capsuleDto);
        const headers = {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'X-AUTH-TOKEN': 'Bearer ' + token
        };
        fetch(url, { method, headers, body })
            .then(async (response) => {
                const resultDto: FrameworkMessageAndResultDtoInterface = await response.json();
                if (resultDto.isFailure) {
                    infoLevel.value = MessageConstants.LEVEL_WARNING;
                    messageType.value = MessageConstants.VIEW_OK;
                    message.value = resultDto.message;
                } else {
                    infoLevel.value = MessageConstants.LEVEL_INFO;
                    messageType.value = MessageConstants.VIEW_TOAST;
                    message.value = resultDto.message;
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

    commonAddress.value = BLANK;
}

function recievePagingNumber(selecteddNumber: number) {
    pageNumber.value = selecteddNumber;
    // onSearchでページング複写
    onSearch();
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

function recieveSubmit(button: string, callerMethod: string) {
    // 住所入力が存在するときだけは確認する
    if (MessageConstants.BUTTON_YES === button && callerMethod === showText) {
        onShowDetail(storeId.value);
    }

    // 非表示
    infoLevel.value = 0;
    messageType.value = 0;
    caller.value = INIT_CALLER;
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
                <tr v-for="entity of resultDtoItem.listItem" :key="entity.addressPostalIrregularId">
                    <td>{{ entity.postalcode1 }}{{ entity.postalcode2 }}</td>
                    <td>{{ entity.addressOrg }}</td>
                    <td>{{ entity.lgCode }}</td>
                    <td>{{ entity.addressPostal }}</td>
                    <td>{{ entity.addressBlock }}</td>
                    <td><button @click="onChangeEdit(entity.addressPostalIrregularId)">編集</button></td>
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
                    <td>{{ entity.postalcode1 }}{{ entity.postalcode2 }}</td>
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
        <InputBuildingAddress :edit-dto="editAddressDto" @send-cancel-input-address="recieveCancelInputAddress"
            @send-input-address-interface="recieveInputAddressInterface"></InputBuildingAddress>
    </div>

    <!-- メッセージ表示    -->
    <div class="overMessage" v-if="messageType !== MessageConstants.VIEW_NONE">
        <MessageView :info-level="infoLevel" :message-type="messageType" :title="MESS_PAGE_NAME" :message="message"
            :caller="caller" @send-submit="recieveSubmit">
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
