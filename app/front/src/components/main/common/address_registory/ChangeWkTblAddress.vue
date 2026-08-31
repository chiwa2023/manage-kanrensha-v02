<script setup lang="ts">
import { ref, toRaw, type Ref } from 'vue';
import SearchChangeWkTblAddress from './SearchChangeWkTblAddress.vue';
import { WkTblAddressRsdtChangeEntity, type WkTblAddressRsdtChangeEntityInterface } from '../../entity/wkTblAddressRsdtChangeEntity.ts';
import { SearchWkTblAddressRsdtCapsuleDto, type SearchWkTblAddressRsdtCapsuleDtoInterface } from '../../dto/address_registory/searchWkTblAddressRsdtCapsuleDto.ts';
import { getErrorMessage, MessageConstants, MessageView, PagingControl, type FrameworkMessageAndResultDtoInterface, type LeastUserDtoInterface } from 'seijishikin-jp-normalize_common-tool';
import getAuthorizedPromiseArea from '../../dto/login/getAuthorizedPromiseArea.ts';
import RoutePathConstants from '../../../../routePathConstants.ts';
import { AccessTokenNotFoundError, TokenRefreshError } from '../../dto/login/errors.ts';
import { SearchWkTblRsdtChangeResultDto, type SearchWkTblRsdtChangeResultDtoInterface } from '../../dto/address_registory/searchWkTblRsdtChangeResultDto.ts';
import { EditWktblRsdtChangeCapsuleDto, type EditWktblRsdtChangeCapsuleDtoInterface } from '../../dto/address_registory/editWktblRsdtChangeCapsuleDto.ts';

// props,emit
const props = defineProps<{ userDto: LeastUserDtoInterface }>();

// よく使う定数
const BLANK: string = "";
const INIT_NUMBER: number = 0;
// const INIT_BOOLEAN: boolean = false;
const SEARCH_LIMIT: number = 20;
const INQUIRE_FLG: boolean = false;
const ERR_MESS_ONLY: boolean = true;
const MESS_PAGE_NAME: string = "住所ワークテーブル更新";
const INIT_CALLER: string = "no branch";

// メッセージボックス表示定数
const infoLevel: Ref<number> = ref(MessageConstants.LEVEL_NONE);
const messageType: Ref<number> = ref(MessageConstants.VIEW_NONE);
const caller: Ref<string> = ref(INIT_CALLER);
const message: Ref<string> = ref(BLANK);

// back側アクセス
const urlBack: string = RoutePathConstants.DOMAIN + RoutePathConstants.BASE_PATH;

// Paging
const pageNumber: Ref<number> = ref(INIT_NUMBER);
const allCount: Ref<number> = ref(INIT_NUMBER);
const limit: Ref<number> = ref(SEARCH_LIMIT);

const capsuleDtoSearch: Ref<SearchWkTblAddressRsdtCapsuleDtoInterface> = ref(new SearchWkTblAddressRsdtCapsuleDto());
capsuleDtoSearch.value.allCount = allCount.value;
capsuleDtoSearch.value.limit = limit.value;
capsuleDtoSearch.value.pageNumber = pageNumber.value;
capsuleDtoSearch.value.userDto = props.userDto;

const resultDto: Ref<SearchWkTblRsdtChangeResultDtoInterface> = ref(new SearchWkTblRsdtChangeResultDto());

function onSearch() {
    capsuleDtoSearch.value.allCount = allCount.value;
    capsuleDtoSearch.value.pageNumber = pageNumber.value;
    capsuleDtoSearch.value.limit = limit.value;
    capsuleDtoSearch.value.userDto = props.userDto;

    getAuthorizedPromiseArea().then(token => {
        const url = urlBack + "/wktbl-address-rsdt/change-search";
        const method = "POST";
        const body = JSON.stringify(capsuleDtoSearch.value);
        const headers = {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'X-AUTH-TOKEN': 'Bearer ' + token
        };
        fetch(url, { method, headers, body })
            .then(async (response) => {
                resultDto.value = await response.json();
                allCount.value = resultDto.value.allCount;
                limit.value = resultDto.value.limit;
                pageNumber.value = resultDto.value.pageNumber;
                if (resultDto.value.allCount === 0) {
                    infoLevel.value = MessageConstants.LEVEL_WARNING;
                    messageType.value = MessageConstants.VIEW_OK;
                    message.value = "検索結果が0件でした";
                }
            })
            .catch((error) => {
                infoLevel.value = MessageConstants.LEVEL_ERROR;
                messageType.value = MessageConstants.VIEW_OK;
                message.value = getErrorMessage(error, ERR_MESS_ONLY);
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

function onDelete() {

    const capsuleDto: EditWktblRsdtChangeCapsuleDtoInterface = new EditWktblRsdtChangeCapsuleDto();
    capsuleDto.userDto = props.userDto;
    capsuleDto.editEntity = entityEdit.value;

    // 編集された郵便番号を削除
    getAuthorizedPromiseArea().then(token => {
        const url = urlBack + "/wktbl-address-rsdt/delete-change";
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
                message.value = resultDto.message;
                if (resultDto.isFailure) {
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
                infoLevel.value = MessageConstants.LEVEL_ERROR;
                messageType.value = MessageConstants.VIEW_OK;
                message.value = getErrorMessage(error, ERR_MESS_ONLY);
                return;
            });
    }).catch((e) => {
        infoLevel.value = MessageConstants.LEVEL_ERROR;
        messageType.value = MessageConstants.VIEW_OK;

        // トークン保持または 取得に失敗している場合
        if (e instanceof AccessTokenNotFoundError || e instanceof TokenRefreshError) {
            message.value = e.message;
            return;
        }
        // それ以外の不測の例外
        message.value = getErrorMessage(e, INQUIRE_FLG);
        return;
    });
}

function recievePagingNumber(selecteddNumber: number) {
    pageNumber.value = selecteddNumber;

    // onSearchでページング複写
    onSearch();
}


const entityEdit: Ref<WkTblAddressRsdtChangeEntityInterface> = ref(new WkTblAddressRsdtChangeEntity())

function recieveEdit(entity: WkTblAddressRsdtChangeEntityInterface) {
    entityEdit.value = structuredClone(toRaw(entity));
}

const methodDeleete: string = "";
function recieveDelete(entity: WkTblAddressRsdtChangeEntityInterface) {
    entityEdit.value = entity;
    infoLevel.value = MessageConstants.LEVEL_WARNING;
    message.value = "このデータを削除してよいですか？";
    caller.value = methodDeleete;
    messageType.value = MessageConstants.VIEW_YES_NO;
}

function recieveSubmit(button: string, callerMethod: string) {
    // 削除からのメッセージ呼び出しかつyesの場合
    if (callerMethod === methodDeleete && button === MessageConstants.BUTTON_YES) {
        onDelete();
    }

    // 非表示にして呼び出し元は初期化
    infoLevel.value = 0;
    messageType.value = 0;
    caller.value = INIT_CALLER;
}

function onCancel() {
    history.back();
}
function onSave() {

    const capsuleDto: EditWktblRsdtChangeCapsuleDtoInterface = new EditWktblRsdtChangeCapsuleDto();
    capsuleDto.userDto = props.userDto;
    capsuleDto.editEntity = entityEdit.value;

    getAuthorizedPromiseArea().then(token => {
        const url = urlBack + "/wktbl-address-rsdt/edit-change";
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
                message.value = resultDto.message;
                if (resultDto.isFailure) {
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
</script>
<template>
    <h3>アドレス・ベース・レジストリ差分更新対象</h3>
    <h3>検索条件の指定</h3>

    <!-- 検索条件 -->
    <div class="one-line">
        <div class="left-area">
            地方自治体コード
        </div>
        <div class="right-area">
            <input type="checkbox" v-model="capsuleDtoSearch.isSearchHistory">履歴も検索する
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

    <!-- 検索コンポーネント -->
    <SearchChangeWkTblAddress :list-entity="resultDto.listEntity" :is-edit="true" @send-delete="recieveDelete"
        @send-edit="recieveEdit"></SearchChangeWkTblAddress>

    <!-- ページング -->
    <PagingControl :all-count="allCount" :limit="limit" :page-number="pageNumber"
        @send-paging-number="recievePagingNumber"></PagingControl>

    <h3>編集内容</h3>

    <div class="one-line">
        <div class="left-area">
            地方自治体コード
        </div>
        <div class="right-area">
            {{ entityEdit.lgCode }}
        </div>
    </div>

    <div class="one-line">
        <div class="left-area">
            郵便番号
        </div>
        <div class="right-area">
            <input v-model="entityEdit.postalcode1" type="text" class="short-input">&nbsp;-&nbsp;
            <input v-model="entityEdit.postalcode2" type="text" class="short-input">
        </div>
    </div>

    <div class="one-line">
        <div class="left-area">
            住所番地
        </div>
        <div class="right-area">
            <textarea v-model="entityEdit.addressBlock" class="max-input"></textarea>
        </div>
    </div>

    <div class="one-line">
        <div class="left-area">
            住所建物
        </div>
        <div class="right-area">
            <textarea v-model="entityEdit.addressBuilding" class="max-input"></textarea>
        </div>
    </div>

    <div class="one-line">
        <div class="left-area">
            住所コード
        </div>
        <div class="right-area">
            <div class="form-group-vertical">
                <div>
                    <span>町字Id</span><input type="text" v-model="entityEdit.machiazaId" class="short-input left-space">
                </div>
                <div>
                    <span>地番Id</span><input type="text" v-model="entityEdit.prcId" class="short-input left-space">
                </div>
                <div>
                    <span>街区Id</span><input type="text" v-model="entityEdit.blkId" class="short-input left-space">
                </div>
                <div>
                    <span>住居Id</span><input type="text" v-model="entityEdit.rsdtId" class="short-input left-space">
                </div>
                <div>
                    <span>住居2Id</span><input type="text" v-model="entityEdit.rsdt2Id" class="short-input left-space">
                </div>
            </div>
        </div>
    </div>

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
<style scoped></style>
