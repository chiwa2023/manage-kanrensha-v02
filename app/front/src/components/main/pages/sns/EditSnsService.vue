<script setup lang="ts">
import { ref, toRaw, type Ref } from 'vue';
import { SearchSnsServiceResultDto, type SearchSnsServiceResultDtoInterface } from '../../dto/sns/searchSnsServiceResultDto';
import { SnsServiceEntity, type SnsServiceEntityInterface } from '../../entity/snsServiceEntity';
import RoutePathConstants from '../../../../routePathConstants';
import { AccessTokenNotFoundError, getAuthorizedPromiseArea, getErrorMessage, getErrorUniqueIdMessage, getLoginUser, MessageConstants, MessageView, NaturalTextSearchPagingCapsuleDto, PagingControl, TokenRefreshError, type FrameworkMessageAndResultDtoInterface, type LeastUserDtoInterface, type NaturalTextSearchPagingCapsuleDtoInterface } from 'seijishikin-jp-normalize_common-tool';
import { EditSnsServiceCapsuleDto, type EditSnsServiceCapsuleDtoInterface } from '../../dto/sns/editSnsServiceCapsuleDto';
import ManagerInfo from '../../common/user_info/ManagerInfo.vue';

// よく使う定数
const BLANK: string = "";
const INIT_NUMBER: number = 0;
// const SERVER_STATUS_OK: number = 200;
const SERVER_STATUS_ERROR: number = 400;
const SEARCH_LIMIT: number = 20;

// メッセージボックス表示定数
const INQUIRE_FLG: boolean = false;
const ERR_MESS_ONLY: boolean = true;
const MESS_PAGE_NAME: string = "SNSサービス編集";
const INIT_CALLER: string = "no branch";
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

const capsuleDto: Ref<NaturalTextSearchPagingCapsuleDtoInterface> = ref(new NaturalTextSearchPagingCapsuleDto());
const resultDto: Ref<SearchSnsServiceResultDtoInterface> = ref(new SearchSnsServiceResultDto);
const editEntity: Ref<SnsServiceEntityInterface> = ref(new SnsServiceEntity());

function onSearch() {

    capsuleDto.value.allCount = allCount.value;
    capsuleDto.value.limit = limit.value;
    capsuleDto.value.pageNumber = pageNumber.value;

    // 入力された検索語で郵便番号検索をする
    getAuthorizedPromiseArea().then(token => {
        const url = urlBack + "/sns-service/search";
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

                if (response.status > SERVER_STATUS_ERROR) {
                    message.value = getErrorMessage(BLANK, INQUIRE_FLG);
                    infoLevel.value = MessageConstants.LEVEL_ERROR;
                    messageType.value = MessageConstants.VIEW_OK;
                    return;
                }

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

function recieveSubmit(button: string, callerMethod: string) {

    // 削除のYes/No
    if (MessageConstants.BUTTON_YES === button && callerMethod === deleteText) {
        onDelete(selectedId.value);
    }

    // 非表示
    infoLevel.value = 0;
    messageType.value = 0;
    caller.value = INIT_CALLER;
}

function onChangeEdit(id: number) {

    // 編集があれば保存を促す
    onShowDetail(id);
}

const deleteText: string = "delete";

const selectedId: Ref<number> = ref(INIT_NUMBER);
function onDeleteEdit(id: number) {

    // 編集があれば保存を促す
    selectedId.value = id;
    infoLevel.value = MessageConstants.LEVEL_WARNING;
    message.value = "このデータを削除してよいですか？";
    messageType.value = MessageConstants.VIEW_YES_NO;
    caller.value = deleteText;
    return;
}

function onShowDetail(id: number) {

    const tmpEntity: SnsServiceEntityInterface | undefined
        = resultDto.value.list.filter(e => e.snsServiceId === id)[0];
    if (tmpEntity !== undefined) {
        editEntity.value = structuredClone(toRaw(tmpEntity));
    } else {
        infoLevel.value = MessageConstants.LEVEL_ERROR;
        messageType.value = MessageConstants.VIEW_OK;
        message.value = getErrorUniqueIdMessage(id);
        return;
    }
}

function onCancel() {
    history.back();

}
function onSave() {

    const capsuleDto: EditSnsServiceCapsuleDtoInterface = new EditSnsServiceCapsuleDto();
    capsuleDto.userDto = userDto.value;
    capsuleDto.snsServiceEntity = editEntity.value;

    // 編集されたSNSサービス
    getAuthorizedPromiseArea().then(token => {
        const url = urlBack + "/sns-service/save";
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

                if (response.status > SERVER_STATUS_ERROR) {
                    message.value = getErrorMessage(resultDto.message, ERR_MESS_ONLY);
                    infoLevel.value = MessageConstants.LEVEL_ERROR;
                    messageType.value = MessageConstants.VIEW_OK;
                    return;
                }

                if (resultDto.isFailure) {
                    message.value = resultDto.message;
                    infoLevel.value = MessageConstants.LEVEL_WARNING;
                    messageType.value = MessageConstants.VIEW_OK;
                    return;
                } else {
                    message.value = resultDto.message;
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

function onAdd() {
    editEntity.value = new SnsServiceEntity();
}

function onDelete(id: number) {

    const capsuleDto: EditSnsServiceCapsuleDtoInterface = new EditSnsServiceCapsuleDto();
    capsuleDto.userDto = userDto.value;
    capsuleDto.snsServiceEntity = editEntity.value;

    const tmpEntity: SnsServiceEntityInterface | undefined
        = resultDto.value.list.filter(e => e.snsServiceId === id)[0];

    if (tmpEntity === undefined) {
        infoLevel.value = MessageConstants.LEVEL_ERROR;
        messageType.value = MessageConstants.VIEW_OK;
        message.value = getErrorUniqueIdMessage(id);
        return;
    } else {
        capsuleDto.snsServiceEntity = tmpEntity;
    }

    // 編集された郵便番号を削除
    getAuthorizedPromiseArea().then(token => {
        const url = urlBack + "/sns-service/delete";
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

                if (response.status > SERVER_STATUS_ERROR) {
                    message.value = getErrorMessage(resultDto.message, ERR_MESS_ONLY);
                    infoLevel.value = MessageConstants.LEVEL_ERROR;
                    messageType.value = MessageConstants.VIEW_OK;
                    return;
                }

                if (resultDto.isFailure) {
                    message.value = resultDto.message;
                    infoLevel.value = MessageConstants.LEVEL_WARNING;
                    messageType.value = MessageConstants.VIEW_OK;
                    return;
                } else {
                    message.value = resultDto.message;
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

    <!-- 管理者メニュー兼チェック -->
    <ManagerInfo :user-dto="userDto"></ManagerInfo>

    <h1>SNS編集</h1><br>

    <h3>検索条件</h3><br>

    <div class="one-line">
        <div class="left-area">
            検索語(全文検索)
        </div>
        <div class="right-area">
            <input type="text" v-model="capsuleDto.searchNaturalWords" class="max-input">
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


    <h3>検索結果</h3><br>

    <div class="one-line-scroll">
        <table>
            <tbody>
                <tr>
                    <th>名称</th>
                    <th>URL</th>
                    <th>&nbsp</th>
                    <th>&nbsp</th>
                </tr>
                <tr v-for="entity of resultDto.list" :key="entity.snsServiceId">
                    <td>{{ entity.snsServiceCode }} <br> {{ entity.snsServiceName }}</td>
                    <td>{{ entity.snsPortalUrl }}</td>
                    <td><button @click="onChangeEdit(entity.snsServiceId)">編集</button></td>
                    <td><button @click="onDeleteEdit(entity.snsServiceId)">削除</button></td>
                </tr>
            </tbody>
        </table>
    </div>
    <!-- ページング  -->
    <PagingControl :all-count="allCount" :limit="limit" :page-number="pageNumber"
        @send-paging-number="recievePagingNumber"></PagingControl>


    <h3>編集</h3><br>

    <div class="one-line">
        <div class="left-area">
            新規
        </div>
        <div class="right-area">
            <button @click="onAdd">新規</button>
        </div>
    </div>

    <div class="one-line">
        <div class="left-area">
            サービスコード
        </div>
        <div class="right-area">
            <div v-if="0 == editEntity.snsServiceId">
                新規追加
            </div>
            <div v-if="0 != editEntity.snsServiceId">
                <input type="text" v-model="editEntity.snsServiceCode" class="name-input" disabled="true">
            </div>
        </div>
    </div>


    <div class="one-line">
        <div class="left-area">
            サービス名称
        </div>
        <div class="right-area">
            <input type="text" v-model="editEntity.snsServiceName" class="name-input">
        </div>
    </div>


    <div class="one-line">
        <div class="left-area">
            サービスURL
        </div>
        <div class="right-area">
            <input type="url" v-model="editEntity.snsPortalUrl" class="name-input">
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
