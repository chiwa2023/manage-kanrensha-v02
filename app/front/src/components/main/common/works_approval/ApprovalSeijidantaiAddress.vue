<script setup lang="ts">
import { ref, type Ref } from 'vue';
import { InputAddressDto, InputCompareAddress, InputDate, MessageConstants, MessageView, PagingControl, type FrameworkMessageAndResultDtoInterface, type InputAddressDtoInterface, type LeastUserDtoInterface } from 'seijishikin-jp-normalize_common-tool';
import { SearchWorksApprovalCapsuleDto, type SearchWorksApprovalCapsuleDtoInterfce } from '../../dto/works_approval/searchWorksApprovalCapsuleDto';
import type { KanrenshaAddressBaseEntityInterface } from '../../entity/kanrenshaAddressBaseEntity';
import { SearchWorksApprovalResultDto, type SearchWorksApprovalResultDtoInterface } from '../../dto/works_approval/searchWorksApprovalResultDto';
import RoutePathConstants from '../../../../routePathConstants';
import { AccessTokenNotFoundError, TokenRefreshError } from '../../dto/login/errors';
import getAuthorizedPromiseArea from '../../dto/login/getAuthorizedPromiseArea';
import { SaveWorksApprovalCapsuleDto, type SaveWorksApprovalCapsuleDtoInterface } from '../../dto/works_approval/saveWorksApprovalCapsuleDto';

//props,emit
const props = defineProps<{ userDto: LeastUserDtoInterface }>()

// よく使う定数
const BLANK: string = "";
const INIT_NUMBER: number = 0;
const INIT_BOOLEAN: boolean = false;
const SERVER_STATUS_OK: number = 200;
// const SERVER_STATUS_ERROR: number = 400;
const SEARCH_LIMIT: number = 20;
// メッセージボックス表示定数
const infoLevel: Ref<number> = ref(MessageConstants.LEVEL_NONE);
const messageType: Ref<number> = ref(MessageConstants.VIEW_NONE);
const title: Ref<string> = ref(BLANK);
const message: Ref<string> = ref(BLANK);

// Paging
const pageNumber: Ref<number> = ref(INIT_NUMBER);
const allCount: Ref<number> = ref(INIT_NUMBER);
const limit: Ref<number> = ref(SEARCH_LIMIT);

// back側アクセス
const urlBack: string = RoutePathConstants.DOMAIN + RoutePathConstants.BASE_PATH;


// 検索期間
const isPortalAddressInput: Ref<boolean> = ref(INIT_BOOLEAN);
const editDto: Ref<InputAddressDtoInterface> = ref(new InputAddressDto());
const capsuleDto: Ref<SearchWorksApprovalCapsuleDtoInterfce> = ref(new SearchWorksApprovalCapsuleDto());
const resultDto: Ref<SearchWorksApprovalResultDtoInterface> = ref(new SearchWorksApprovalResultDto());


function onSearch() {
    //listPersonAdsdress.value = mockGetSeijidantaiNoApprovalList();
    //allCount.value = listPersonAdsdress.value.length;

    // 検索実行
    capsuleDto.value.limit = limit.value;
    capsuleDto.value.allCount = allCount.value;
    capsuleDto.value.pageNumber = pageNumber.value;
    getAuthorizedPromiseArea().then(token => {
        const url = urlBack + "/works-approval/search-seijidantai";
        const method = "POST";
        const body = JSON.stringify(capsuleDto.value);
        const headers = {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'X-AUTH-TOKEN': 'Bearer ' + token
        };
        fetch(url, { method, headers, body })
            .then(async (response) => {
                resultDto.value = await response.json();
                if (SERVER_STATUS_OK === response.status) {
                    //ページング
                    allCount.value = resultDto.value.resultDtoAddress.allCount;
                    pageNumber.value = resultDto.value.resultDtoAddress.pageNumber;
                    limit.value = resultDto.value.resultDtoAddress.limit;
                } else {
                    infoLevel.value = MessageConstants.LEVEL_WARNING;
                    messageType.value = MessageConstants.VIEW_OK;
                    title.value = "作業承認を検索しました";
                    message.value = "検索結果を取得できませんでした";
                    return;
                }
            })
            .catch((error) => {
                alert(error);
                infoLevel.value = MessageConstants.LEVEL_ERROR;
                messageType.value = MessageConstants.VIEW_OK;
                title.value = "システムエラーが発生しました";
                message.value = "システム管理者にお問い合わせください";
            });
    }).catch((e) => {
        infoLevel.value = MessageConstants.LEVEL_ERROR;
        messageType.value = MessageConstants.VIEW_OK;

        if (e instanceof AccessTokenNotFoundError) {
            // トークン保持ができていない場合
            title.value = "現在トークンが存在しません";
            message.value = e.message;
            return;
        }
        if (e instanceof TokenRefreshError) {
            // 取得に失敗している場合
            title.value = "有効期限まじかのトークンを再取得できませんでした";
            message.value = e.message;
            return;
        }
        title.value = "システムエラーが発生しました";
        message.value = "システム管理者にお問い合わせください";
        return;
    });
}

const storedId: Ref<number> = ref(INIT_NUMBER);
function onPortalAddressInput(selectedId: number) {

    const tempEntity: KanrenshaAddressBaseEntityInterface | undefined
        = resultDto.value.resultDtoAddress.listAddress.filter((e) => selectedId === e.kanrenshaAddressId)[0];

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

    const tempEntity: KanrenshaAddressBaseEntityInterface | undefined
        = resultDto.value.resultDtoAddress.listAddress.filter((e) => storedId.value === e.kanrenshaAddressId)[0];

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
    const capsuleDtoSave: SaveWorksApprovalCapsuleDtoInterface = new SaveWorksApprovalCapsuleDto();
    capsuleDtoSave.userDto = props.userDto;
    capsuleDtoSave.listAddress = resultDto.value.resultDtoAddress.listAddress;

    getAuthorizedPromiseArea().then(token => {
        const url = urlBack + "/works-approval/save-address";
        const method = "POST";
        const body = JSON.stringify(capsuleDtoSave);
        const headers = {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'X-AUTH-TOKEN': 'Bearer ' + token
        };
        fetch(url, { method, headers, body })
            .then(async (response) => {

                const resultDtoSave: FrameworkMessageAndResultDtoInterface = await response.json();
                title.value = "作業承認登録";
                message.value = resultDtoSave.message;
                if (resultDtoSave.isFailure) {
                    infoLevel.value = MessageConstants.LEVEL_WARNING;
                    messageType.value = MessageConstants.VIEW_OK;
                    return;
                } else {
                    infoLevel.value = MessageConstants.LEVEL_INFO;
                    messageType.value = MessageConstants.VIEW_TOAST;
                    return;
                }
            })
            .catch((error) => {
                alert(error);
                infoLevel.value = MessageConstants.LEVEL_ERROR;
                messageType.value = MessageConstants.VIEW_OK;
                title.value = "システムエラーが発生しました";
                message.value = "システム管理者にお問い合わせください";
            });
    }).catch((e) => {
        infoLevel.value = MessageConstants.LEVEL_ERROR;
        messageType.value = MessageConstants.VIEW_OK;

        if (e instanceof AccessTokenNotFoundError) {
            // トークン保持ができていない場合
            title.value = "現在トークンが存在しません";
            message.value = e.message;
            return;
        }
        if (e instanceof TokenRefreshError) {
            // 取得に失敗している場合
            title.value = "有効期限まじかのトークンを再取得できませんでした";
            message.value = e.message;
            return;
        }
        title.value = "システムエラーが発生しました";
        message.value = "システム管理者にお問い合わせください";
        return;
    });
}

// コンポーネントから時刻受け取り
function recieveDate(date: Date, index: number) {
    if (0 == index) {
        capsuleDto.value.startDate = date;
    }
    if (1 == index) {
        capsuleDto.value.endDate = date;
    }
}
function recieveSubmit(button: string) {
    console.log(button); // 警告除け
    infoLevel.value = 0;
    messageType.value = 0;
}
</script>
<template>
    <h3>政治団体住所検索条件</h3>

    <div class="one-line">
        <div class="left-area">
            検索期間
        </div>
        <div class="right-area">
            <span>
                <InputDate :date="capsuleDto.startDate" :index="0" :is-edit="true" @send-date="recieveDate"></InputDate>
                から
            </span>
            <span class="left-space">
                <InputDate :date="capsuleDto.endDate" :index="1" :is-edit="true" @send-date="recieveDate"></InputDate>まで
            </span>
        </div>
    </div>
    <div class="one-line">
        <div class="left-area">
            承認必要のみ
        </div>
        <div class="right-area">
            <input type="checkbox" v-model="capsuleDto.isExcludeFinishedTask">承認必要作業のみ表示する
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

    <h3>政治団体検索結果(住所)</h3>
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
            <tbody v-for="entity in resultDto.resultDtoAddress.listAddress" :key="entity.kanrenshaAddressId">
                <tr>
                    <td>{{ entity.kanrenshaCode }} </td>
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
                    <td><button @click="onPortalAddressInput(entity.kanrenshaAddressId)">編集</button></td>
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

    <!-- メッセージ表示 -->
    <div class="overMessage" v-if="messageType !== MessageConstants.VIEW_NONE">
        <MessageView :info-level="infoLevel" :message-type="messageType" :title="title" :message="message"
            @send-submit="recieveSubmit">
        </MessageView>
    </div>

</template>
<style scoped></style>
