<script setup lang="ts">
import { onMounted, ref, type Ref } from 'vue';
import ManagerInfo from '../../common/user_info/ManagerInfo.vue';
import { FrameworkCapsuleDto, getErrorMessage, MessageConstants, MessageView, PagingControl, type FrameworkCapsuleDtoInterface, type FrameworkMessageAndResultDtoInterface, type LeastUserDtoInterface } from 'seijishikin-jp-normalize_common-tool';
import { getLoginUser } from '../../utils/getLoginUser.ts';
import type { WkTblAddressRsdtChangeEntityInterface } from '../../entity/wkTblAddressRsdtChangeEntity.ts';
import type { WkTblAddressRsdtDeleteEntityInterface } from '../../entity/wkTblAddressRsdtDeleteEntity.ts';
import { AccessTokenNotFoundError, TokenRefreshError } from '../../dto/login/errors.ts';
import getAuthorizedPromiseArea from '../../dto/login/getAuthorizedPromiseArea.ts';
import RoutePathConstants from '../../../../routePathConstants.ts';
import { SearchWkTblAddressRsdtCapsuleDto, type SearchWkTblAddressRsdtCapsuleDtoInterface } from '../../dto/address_registory/searchWkTblAddressRsdtCapsuleDto.ts';
import { SearchWkTblRsdtChangeResultDto, type SearchWkTblRsdtChangeResultDtoInterface } from '../../dto/address_registory/searchWkTblRsdtChangeResultDto.ts';
import { SearchWkTblRsdtDeleteResultDto, type SearchWkTblRsdtDeleteResultDtoInterface } from '../../dto/address_registory/searchWkTblRsdtDeleteResultDto.ts';
import SearchDeleteWkTblAddress from '../../common/address_registory/SearchDeleteWkTblAddress.vue';
import SearchChangeWkTblAddress from '../../common/address_registory/SearchChangeWkTblAddress.vue';

// よく使う定数
const BLANK: string = "";
const INIT_NUMBER: number = 0;
// const SERVER_STATUS_OK: number = 200;
// const SERVER_STATUS_ERROR: number = 400;
const SEARCH_LIMIT: number = 20;
const INQUIRE_FLG: boolean = false;
const ERR_MESS_ONLY: boolean = true;
const MESS_PAGE_NAME: string = "アドレス・ベース・レジストリ差分一括更新";
const INIT_CALLER: string = "no branch";

// メッセージボックス表示定数
const infoLevel: Ref<number> = ref(MessageConstants.LEVEL_NONE);
const messageType: Ref<number> = ref(MessageConstants.VIEW_NONE);
const caller: Ref<string> = ref(INIT_CALLER);
const message: Ref<string> = ref(BLANK);

// back側アクセス
const urlBack: string = RoutePathConstants.DOMAIN + RoutePathConstants.BASE_PATH;

// Paging
const pageNumberChange: Ref<number> = ref(INIT_NUMBER);
const allCountChange: Ref<number> = ref(INIT_NUMBER);
const limitChange: Ref<number> = ref(SEARCH_LIMIT);
const pageNumberDelete: Ref<number> = ref(INIT_NUMBER);
const allCountDelete: Ref<number> = ref(INIT_NUMBER);
const limitDelete: Ref<number> = ref(SEARCH_LIMIT);

// ユーザ呼び出し
const userDto: Ref<LeastUserDtoInterface> = ref(getLoginUser());

// 編集対象    
const listChange: Ref<WkTblAddressRsdtChangeEntityInterface[]> = ref([]);
const listDelete: Ref<WkTblAddressRsdtDeleteEntityInterface[]> = ref([]);


const capsuleDtoChange: Ref<SearchWkTblAddressRsdtCapsuleDtoInterface> = ref(new SearchWkTblAddressRsdtCapsuleDto());
capsuleDtoChange.value.allCount = allCountChange.value;
capsuleDtoChange.value.limit = limitChange.value;
capsuleDtoChange.value.pageNumber = pageNumberChange.value;
capsuleDtoChange.value.userDto = userDto.value;

const capsuleDtoDelete: Ref<SearchWkTblAddressRsdtCapsuleDtoInterface> = ref(new SearchWkTblAddressRsdtCapsuleDto());
capsuleDtoDelete.value.allCount = allCountDelete.value;
capsuleDtoDelete.value.limit = limitDelete.value;
capsuleDtoDelete.value.pageNumber = pageNumberDelete.value;
capsuleDtoDelete.value.userDto = userDto.value;

const resultDtoChange: Ref<SearchWkTblRsdtChangeResultDtoInterface> = ref(new SearchWkTblRsdtChangeResultDto());
const resultDtoDelete: Ref<SearchWkTblRsdtDeleteResultDtoInterface> = ref(new SearchWkTblRsdtDeleteResultDto());

onMounted(async () => {
    // 削除リストと編集リスト(自動処理決定)のみを表示
    const isChange: boolean = await recievePagingNumberChange(0);
    const isDelete: boolean = await recievePagingNumberDelete(0);

    if (listChange.value.length === 0 && listDelete.value.length === 0 && isChange && isDelete) {
        infoLevel.value = MessageConstants.LEVEL_WARNING;
        messageType.value = MessageConstants.VIEW_OK;
        message.value = "検索結果が0件でした";
    }
});


function onCancel() {
    history.back();
}
function onSave() {
    const capsuleDto: FrameworkCapsuleDtoInterface = new FrameworkCapsuleDto();
    capsuleDto.userDto = userDto.value;

    // 編集された郵便番号差分を保存
    getAuthorizedPromiseArea().then(token => {
        const url = urlBack + "/wktbl-address-rsdt/update";
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
                } else {
                    infoLevel.value = MessageConstants.LEVEL_INFO;
                    messageType.value = MessageConstants.VIEW_TOAST;
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
function recieveSubmit() {

    // 非表示
    infoLevel.value = 0;
    messageType.value = 0;
}


async function recievePagingNumberChange(selecteddNumber: number): Promise<boolean> {
    pageNumberChange.value = selecteddNumber;
    // 編集側を呼び出し
    getAuthorizedPromiseArea().then(token => {
        const url = urlBack + "/wktbl-address-rsdt/change-search";
        const method = "POST";
        const body = JSON.stringify(capsuleDtoChange.value);
        const headers = {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'X-AUTH-TOKEN': 'Bearer ' + token
        };
        fetch(url, { method, headers, body })
            .then(async (response) => {
                resultDtoChange.value = await response.json();
                listChange.value = resultDtoChange.value.listEntity;
                allCountChange.value = resultDtoChange.value.allCount;
                limitChange.value = resultDtoChange.value.limit;
                pageNumberChange.value = resultDtoChange.value.pageNumber;
                return true;
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

    return false;
}

async function recievePagingNumberDelete(selecteddNumber: number): Promise<boolean> {
    pageNumberDelete.value = selecteddNumber;
    // 削除側を呼び出し
    getAuthorizedPromiseArea().then(token => {
        const url = urlBack + "/wktbl-address-rsdt/delete-search";
        const method = "POST";
        const body = JSON.stringify(capsuleDtoDelete.value);
        const headers = {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'X-AUTH-TOKEN': 'Bearer ' + token
        };
        fetch(url, { method, headers, body })
            .then(async (response) => {
                resultDtoDelete.value = await response.json();
                listDelete.value = resultDtoDelete.value.listEntity;
                allCountDelete.value = resultDtoDelete.value.allCount;
                limitDelete.value = resultDtoDelete.value.limit;
                pageNumberDelete.value = resultDtoDelete.value.pageNumber;
                return true;
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

    return false;

}

</script>
<template>

    <!-- 管理者メニュー兼チェック -->
    <ManagerInfo :user-dto="userDto"></ManagerInfo>

    <h1>アドレス・ベース・レジストリ差分一括変更</h1>

    <!-- 更新検索コンポーネント -->
    <h3>変更・追加対象リスト</h3>
    <SearchChangeWkTblAddress :list-entity="listChange" :is-edit="true"></SearchChangeWkTblAddress>
    <!-- ページング  -->
    <PagingControl :all-count="allCountChange" :limit="limitChange" :page-number="pageNumberChange"
        @send-paging-number="recievePagingNumberChange"></PagingControl>

    <hr>

    <!-- 削除検索コンポーネント -->
    <h3>削除対象リスト</h3>
    <SearchDeleteWkTblAddress :list-entity="listDelete" :is-edit="true"></SearchDeleteWkTblAddress>
    <!-- ページング  -->
    <PagingControl :all-count="allCountDelete" :limit="limitDelete" :page-number="pageNumberDelete"
        @send-paging-number="recievePagingNumberDelete"></PagingControl>

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
