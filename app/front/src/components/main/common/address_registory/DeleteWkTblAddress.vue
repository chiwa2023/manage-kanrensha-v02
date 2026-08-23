<script setup lang="ts">
import { ref, type Ref } from 'vue';
import { WkTblAddressRsdtDeleteEntity, type WkTblAddressRsdtDeleteEntityInterface } from '../../entity/wkTblAddressRsdtDeleteEntity';
import SearchDeleteWkTblAddress from './SearchDeleteWkTblAddress.vue';
import { SearchWkTblAddressRsdtCapsuleDto, type SearchWkTblAddressRsdtCapsuleDtoInterface } from '../../dto/address_registory/searchWkTblAddressRsdtCapsuleDto.ts';
import { getErrorMessage, MessageConstants, MessageView, PagingControl, type FrameworkMessageAndResultDtoInterface, type LeastUserDtoInterface } from 'seijishikin-jp-normalize_common-tool';
import { AccessTokenNotFoundError, TokenRefreshError } from '../../dto/login/errors.ts';
import getAuthorizedPromiseArea from '../../dto/login/getAuthorizedPromiseArea.ts';
import { SearchWkTblRsdtDeleteResultDto, type SearchWkTblRsdtDeleteResultDtoInterface } from '../../dto/address_registory/searchWkTblRsdtDeleteResultDto.ts';
import RoutePathConstants from '../../../../routePathConstants.ts';
import { EditWktblRsdtDeleteCapsuleDto, type EditWktblRsdtDeleteCapsuleDtoInterface } from '../../dto/address_registory/editWktblRsdtDeleteCapsuleDto.ts';

// props,emit
const props = defineProps<{ userDto: LeastUserDtoInterface }>();

// よく使う定数
const BLANK: string = "";
const INIT_NUMBER: number = 0;
// const INIT_BOOLEAN: boolean = false;
const SEARCH_LIMIT: number = 20;
const INQUIRE_FLG: boolean = false;
const ERR_MESS_ONLY: boolean = true;
const MESS_PAGE_NAME: string = "アドレス・ベース・レジストリ削除";
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

const resultDto: Ref<SearchWkTblRsdtDeleteResultDtoInterface> = ref(new SearchWkTblRsdtDeleteResultDto());

function onSearch() {
    capsuleDtoSearch.value.allCount = allCount.value;
    capsuleDtoSearch.value.pageNumber = pageNumber.value;
    capsuleDtoSearch.value.limit = limit.value;
    capsuleDtoSearch.value.userDto = props.userDto;

    getAuthorizedPromiseArea().then(token => {
        const url = urlBack + "/wktbl-address-rsdt/delete-search";
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


function recievePagingNumber(selecteddNumber: number) {
    pageNumber.value = selecteddNumber;
    // onSearchでページング複写
    onSearch();
}


const entityEdit: Ref<WkTblAddressRsdtDeleteEntityInterface> = ref(new WkTblAddressRsdtDeleteEntity())

// function recieveEdit(entity: WkTblAddressRsdtDeleteEntityInterface) {
//     entityEdit.value = structuredClone(toRaw(entity));
// }

const deleteText: string = "delete";
function recieveDelete(entity: WkTblAddressRsdtDeleteEntityInterface) {
    entityEdit.value = entity;
    infoLevel.value = MessageConstants.LEVEL_WARNING;
    message.value = "このデータを削除してよいですか？";
    messageType.value = MessageConstants.VIEW_YES_NO;
    caller.value = deleteText;
}

function recieveSubmit(button: string, callerMethod: string) {

    if (MessageConstants.BUTTON_YES === button && callerMethod === deleteText) {
        onDelete();
    }

    // 非表示
    infoLevel.value = 0;
    messageType.value = 0;
    caller.value = INIT_CALLER;
}
function onDelete() {

    const capsuleDto: EditWktblRsdtDeleteCapsuleDtoInterface = new EditWktblRsdtDeleteCapsuleDto();
    capsuleDto.userDto = props.userDto;
    capsuleDto.editEntity = entityEdit.value;

    // 編集された郵便番号を削除
    getAuthorizedPromiseArea().then(token => {
        const url = urlBack + "/wktbl-address-rsdt/delete-delete";
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

function onCancel() {
    history.back();
}

</script>
<template>
    <h3>アドレス・ベース・レジストリ差分削除対象</h3>

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
    <SearchDeleteWkTblAddress :list-entity="resultDto.listEntity" :is-edit="true" @send-delete="recieveDelete">
    </SearchDeleteWkTblAddress>

    <!-- ページング -->
    <PagingControl :all-count="allCount" :limit="limit" :page-number="pageNumber"
        @send-paging-number="recievePagingNumber"></PagingControl>

    <div class="footer">
        <button @click="onCancel" class="footer-button">キャンセル</button>
    </div>

    <!-- メッセージ表示    -->
    <div class="overMessage" v-if="messageType !== MessageConstants.VIEW_NONE">
        <MessageView :info-level="infoLevel" :message-type="messageType" :title="MESS_PAGE_NAME" :message="message"
            :caller="caller" @send-submit="recieveSubmit">
        </MessageView>
    </div>

</template>
<style scoped></style>
