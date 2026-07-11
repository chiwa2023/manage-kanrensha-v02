<script setup lang="ts">
import { getErrorMessage, getErrorUniqueIdMessage, InputLgcode, MessageConstants, MessageView, PagingControl, type FrameworkMessageAndResultDtoInterface, type LeastUserDtoInterface } from 'seijishikin-jp-normalize_common-tool';
import { ref, toRaw, type Ref } from 'vue';
import { getLoginUser } from '../../utils/getLoginUser';
import { SearchPostalCodeCapsuleDto, type SearchPostalCodeCapsuleDtoInterface } from '../../dto/address_postal/searchPostalCodeCapsuleDto';
import { SearchPostalCodeResultDto, type SearchPostalCodeResultDtoInterface } from '../../dto/address_postal/searchPostalCodeResultDto';
import { AddressPostalEntity, type AddressPostalEntityInterface } from '../../entity/addressPostalEntity';
import ManagerInfo from '../../common/user_info/ManagerInfo.vue';
import getAuthorizedPromiseArea from '../../dto/login/getAuthorizedPromiseArea.ts';
import RoutePathConstants from '../../../../routePathConstants.ts';
import { AccessTokenNotFoundError, TokenRefreshError } from '../../dto/login/errors.ts';
import { SavePostalCapsuleDto, type SavePostalCapsuleDtoInterface } from '../../dto/address_postal/savePostalCapsuleDto.ts';
import type { SelectOptionStringDtoInterface } from '../../dto/select_options/selectOptionStringDto.ts';

// よく使う定数
const BLANK: string = "";
const INIT_NUMBER: number = 0;
// const INIT_BOOLEAN: boolean = false;
// const SERVER_STATUS_OK: number = 200;
// const SERVER_STATUS_ERROR: number = 400;
const SEARCH_LIMIT: number = 20;
const INQUIRE_FLG: boolean = false;
const ERR_MESS_ONLY: boolean = true;
const MESS_PAGE_NAME: string = "郵便番号編集";
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


// 検索条件と検索結果Dt0
const capsuleDto: Ref<SearchPostalCodeCapsuleDtoInterface> = ref(new SearchPostalCodeCapsuleDto());
const resultDto: Ref<SearchPostalCodeResultDtoInterface> = ref(new SearchPostalCodeResultDto());

// 編集対象
const entityEdit: Ref<AddressPostalEntityInterface> = ref(new AddressPostalEntity());
const entityPre: Ref<AddressPostalEntityInterface> = ref(new AddressPostalEntity());
function onSearch() {

    capsuleDto.value.allCount = allCount.value;
    capsuleDto.value.limit = limit.value;
    capsuleDto.value.pageNumber = pageNumber.value;
    
    // 入力された検索語で郵便番号検索をする
    getAuthorizedPromiseArea().then(token => {
        const url = urlBack + "/postal-code/search";
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

const updateText: string = "update";
function onAddEntity() {
    // TODO 編集があれば保存を促す
    if (isDifferEntity()) {
        selectedId.value = INIT_NUMBER;
        infoLevel.value = MessageConstants.LEVEL_WARNING;
        message.value = "住所入力がされて未保存データです。保存せず新たな編集対象を表示してよいですか?";
        messageType.value = MessageConstants.VIEW_YES_NO;
        caller.value = updateText;
        return;
    } else {
        onShowDetail(INIT_NUMBER);
    }
}

const selectedId: Ref<number> = ref(INIT_NUMBER);
function onChangeEdit(id: number) {

    // 編集があれば保存を促す
    if (isDifferEntity()) {
        selectedId.value = id;
        infoLevel.value = MessageConstants.LEVEL_WARNING;
        message.value = "住所入力がされて未保存データです。保存せず新たな編集対象を表示してよいですか?";
        messageType.value = MessageConstants.VIEW_YES_NO;
        caller.value = updateText;
        return;
    }
    else {
        onShowDetail(id);
    }
}

const deleteText: string = "delete";
function onDeleteEdit(id: number) {

    // 編集があれば保存を促す
    selectedId.value = id;
    infoLevel.value = MessageConstants.LEVEL_WARNING;
    message.value = "このデータを削除してよいですか？";
    messageType.value = MessageConstants.VIEW_YES_NO;
    caller.value = deleteText;
    return;
}

function isDifferEntity(): boolean {
    return entityEdit.value.postalcode1 !== entityPre.value.postalcode1
        || entityEdit.value.postalcode2 !== entityPre.value.postalcode2
        || entityEdit.value.addressOrg !== entityPre.value.addressOrg
}

function onDelete(id: number) {

    const capsuleDto: SavePostalCapsuleDtoInterface = new SavePostalCapsuleDto();
    capsuleDto.userDto = userDto.value;

    const tmpEntity: AddressPostalEntity | undefined
        = resultDto.value.listItem.filter(e => e.addressPostalId === id)[0];
    if (tmpEntity === undefined) {
        infoLevel.value = MessageConstants.LEVEL_ERROR;
        messageType.value = MessageConstants.VIEW_OK;
        message.value = getErrorUniqueIdMessage(id);
        return;
    } else {
        capsuleDto.addressPostalEntity = tmpEntity;
    }

    // 編集された郵便番号を削除
    getAuthorizedPromiseArea().then(token => {
        const url = urlBack + "/postal-code/delete";
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

function onCancel() {
    history.back();

}
function onSave() {

    const capsuleDto: SavePostalCapsuleDtoInterface = new SavePostalCapsuleDto();
    capsuleDto.userDto = userDto.value;
    capsuleDto.addressPostalEntity = entityEdit.value;

    // 編集された郵便番号を保存
    getAuthorizedPromiseArea().then(token => {
        const url = urlBack + "/postal-code/edit";
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

function recievePagingNumber(selecteddNumber: number) {
    pageNumber.value = selecteddNumber;
    // onSearchでページング複写
    onSearch();
}

function recieveSubmit(button: string, callerMethod: string) {

    // 削除のYes/No
    if (MessageConstants.BUTTON_YES === button && callerMethod === deleteText) {
        onDelete(selectedId.value);
    }

    // 未保存の更新Yes/No
    if (MessageConstants.BUTTON_YES === button && callerMethod === updateText) {
        onShowDetail(selectedId.value);
    }

    // 非表示
    infoLevel.value = 0;
    messageType.value = 0;
    caller.value = INIT_CALLER;
}

function onShowDetail(id: number) {

    if (INIT_NUMBER === id) {
        entityEdit.value = new AddressPostalEntity();
        entityPre.value = new AddressPostalEntity();
    } else {
        entityPre.value = structuredClone(toRaw(entityEdit.value));
        const tmpEntity: AddressPostalEntity | undefined
            = resultDto.value.listItem.filter(e => e.addressPostalId === id)[0];
        if (tmpEntity !== undefined) {
            entityEdit.value = structuredClone(toRaw(tmpEntity));
            //entityPre.value = structuredClone(toRaw(tmpEntity));
        } else {
            infoLevel.value = MessageConstants.LEVEL_ERROR;
            messageType.value = MessageConstants.VIEW_OK;
            message.value = getErrorUniqueIdMessage(id);
            return;
        }
    }
}

function recieveLgCode(optionDto: SelectOptionStringDtoInterface) {
    entityEdit.value.lgCode = optionDto.value;
}
</script>
<template>
    <!-- 管理者メニュー兼チェック -->
    <ManagerInfo :user-dto="userDto"></ManagerInfo>

    <h1>郵便番号編集</h1>

    <h3>編集郵便番号の検索条件</h3>
    <div class="one-line">
        <div class="left-area">
            郵便番号(前方一致)
        </div>
        <div class="right-area">
            <input type="text" v-model="capsuleDto.searchPostalcode1">
            <input type="text" v-model="capsuleDto.searchPostalcode2" class="left-space">
        </div>
    </div>
    <div class="one-line">
        <div class="left-area">
            地名(前方一致)
        </div>
        <div class="right-area">
            <input type="text" v-model="capsuleDto.searchAddressName">
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

    <h3>検索結果</h3>

    <div class="one-line-scroll">
        <table>
            <tbody>
                <tr>
                    <th>郵便番号</th>
                    <th>原文書住所</th>
                    <th>表示住所</th>
                    <th>&nbsp</th>
                    <th>&nbsp</th>
                </tr>
                <tr v-for="entity of resultDto.listItem" :key="entity.addressPostalId">
                    <td>{{ entity.postalcode1 }}{{ entity.postalcode2 }}</td>
                    <td>{{ entity.addressOrg }}</td>
                    <td>{{ entity.addressName }}</td>
                    <td><button @click="onChangeEdit(entity.addressPostalId)">編集</button></td>
                    <td><button @click="onDeleteEdit(entity.addressPostalId)">削除</button></td>
                </tr>
            </tbody>
        </table>
    </div>
    <!-- ページング  -->
    <PagingControl :all-count="allCount" :limit="limit" :page-number="pageNumber"
        @send-paging-number="recievePagingNumber"></PagingControl>

    <h3>編集</h3>
    <div class="one-line">
        <div class="left-area">
            追加
        </div>
        <div class="right-area">
            <button @click="onAddEntity">追加</button>
        </div>
    </div>

    <div class="one-line">
        <div class="left-area">
            地方自治体コード
        </div>
        <div class="right-area">
            <InputLgcode :is-digit5="false" :lg-code="entityEdit.lgCode" @send-lg-code="recieveLgCode"></InputLgcode>
        </div>
    </div>

    <div class="one-line">
        <div class="left-area">
            郵便番号
        </div>
        <div class="right-area">
            <input type="text" v-model="entityEdit.postalcode1" class="code-input">
            <input type="text" v-model="entityEdit.postalcode2" class="code-input left-space">
        </div>
    </div>

    <div class="one-line">
        <div class="left-area">
            原文書住所
        </div>
        <div class="right-area">
            <input type="text" v-model="entityEdit.addressOrg" class="max-input">
        </div>
    </div>

    <div class="one-line">
        <div class="left-area">
            表示住所
        </div>
        <div class="right-area">
            <input type="text" v-model="entityEdit.addressName" class="max-input">
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
