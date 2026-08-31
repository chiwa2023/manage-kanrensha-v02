<script setup lang="ts">
import { ref, type Ref } from 'vue';
import RiyoushaOrgEdit from '../../common/riyousha_edit/RiyoushaOrgEdit.vue';
import { getErrorMessage, getErrorUniqueIdMessage, MessageConstants, MessageView, PagingControl, type FrameworkMessageAndResultDtoInterface, type LeastUserDtoInterface } from 'seijishikin-jp-normalize_common-tool';
import { getLoginUser } from '../../utils/getLoginUser';
import AdminInfo from '../../common/user_info/AdminInfo.vue';
import getAuthorizedPromiseArea from '../../dto/login/getAuthorizedPromiseArea';
import { AccessTokenNotFoundError, TokenRefreshError } from '../../dto/login/errors';
import { SearchRiyoushaOrgCapsuleDto, type SearchRiyoushaOrgCapsuleDtoInterface } from '../../dto/riyousha/searchRiyoushaOrgCapsuleDto';
import RoutePathConstants from '../../../../routePathConstants';
import { SearchRiyoushaOrgResultDto, type SearchRiyoushaOrgResultDtoInterface } from '../../dto/riyousha/searchRiyoushaOrgResultDto';
import type { RiyoushaOrgDtoInterface } from '../../dto/riyousha/riyoushaOrgDto';
import { SaveRiyoushaOrgCapsuleDto, type SaveRiyoushaOrgCapsuleDtoInterface } from '../../dto/riyousha/saveRiyoushaOrgCapsuleDto';
import type { RiyoushaOrgMasterEntityInterface } from '../../entity/riyoushaOrgMasterEntity';
import { UpdateRiyoushaOrgCapsuleDto, type UpdateRiyoushaOrgCapsuleDtoInterface } from '../../dto/riyousha/updateRiyoushaOrgCapsuleDto';

// よく使う定数
const BLANK: string = "";
const INIT_NUMBER: number = 0;
const INIT_BOOLEAN: boolean = false;
const SEARCH_LIMIT: number = 20;
// const SERVER_STATUS_OK: number = 200;
// const SERVER_STATUS_ERROR: number = 400;
const INQUIRE_FLG: boolean = false;
const ERR_MESS_ONLY: boolean = true;
const MESS_PAGE_NAME: string = "利用者組織検索";
const INIT_CALLER: string = "no branch";

// メッセージボックス表示定数
const infoLevel: Ref<number> = ref(MessageConstants.LEVEL_NONE);
const messageType: Ref<number> = ref(MessageConstants.VIEW_NONE);
const caller: Ref<string> = ref(INIT_CALLER);
const message: Ref<string> = ref(BLANK);

// Paging
const pageNumber: Ref<number> = ref(INIT_NUMBER);
const allCount: Ref<number> = ref(INIT_NUMBER);
const limit: Ref<number> = ref(SEARCH_LIMIT);

// back側アクセス
const urlBack: string = RoutePathConstants.DOMAIN + RoutePathConstants.BASE_PATH;

// ユーザ呼び出し
const userDto: Ref<LeastUserDtoInterface> = ref(getLoginUser());
const selectedOrgId: Ref<number> = ref(INIT_NUMBER);

// 検索条件
const capsuleDto: Ref<SearchRiyoushaOrgCapsuleDtoInterface> = ref(new SearchRiyoushaOrgCapsuleDto());
const resultDto: Ref<SearchRiyoushaOrgResultDtoInterface> = ref(new SearchRiyoushaOrgResultDto());

function onSearch() {
    // listRiyoushaOrg.value = mockGetRiyoushaOrgMasterList();
    // allCount.value = listRiyoushaOrg.value.length;
    capsuleDto.value.allCount = allCount.value;
    capsuleDto.value.pageNumber = pageNumber.value;
    capsuleDto.value.limit = limit.value;

    getAuthorizedPromiseArea().then(token => {
        const url = urlBack + "/riyousha-org/search";
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
                allCount.value = resultDto.value.allCount;
                limit.value = resultDto.value.limit;
                pageNumber.value = resultDto.value.pageNumber;

                if (resultDto.value.listRiyoushaOrg.length === 0) {
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

const isOrgEdit: Ref<boolean> = ref(INIT_BOOLEAN);
function onEdit(selectedId: number) {
    selectedOrgId.value = selectedId;
    isOrgEdit.value = true;
}

let orgDeleteId: number = 0;
const deleteText: string = "delete";
function onDelete(editIndex: number) {
    infoLevel.value = MessageConstants.LEVEL_WARNING;
    messageType.value = MessageConstants.VIEW_YES_NO;
    message.value = "削除すると戻すことができません。よろしいですか？";
    orgDeleteId = editIndex;
    caller.value = deleteText;
}

function recieveCancelRiyoushaOrg() {
    isOrgEdit.value = false;
}

function recieveRiyoushaOrgInterface(editDto: RiyoushaOrgDtoInterface) {

    // 保存処理
    const capsuleDto: SaveRiyoushaOrgCapsuleDtoInterface = new SaveRiyoushaOrgCapsuleDto();
    capsuleDto.userDto = userDto.value;
    capsuleDto.riyoushaOrgDto = editDto;

    getAuthorizedPromiseArea().then(token => {
        const url = urlBack + "/riyousha-org/update";
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
    isOrgEdit.value = false;
}

function doDelete() {
    const deleteEntity: RiyoushaOrgMasterEntityInterface | undefined =
        resultDto.value.listRiyoushaOrg.filter((e) => orgDeleteId === e.riyoushaOrgMasterId)[0];

    if (undefined !== deleteEntity) {
        const capsuleDtoDelete: UpdateRiyoushaOrgCapsuleDtoInterface = new UpdateRiyoushaOrgCapsuleDto();
        capsuleDtoDelete.userDto = userDto.value;
        capsuleDtoDelete.masterEntity = deleteEntity;

        getAuthorizedPromiseArea().then(token => {
            const url = urlBack + "/riyousha-org/delete";
            const method = "POST";
            const body = JSON.stringify(capsuleDtoDelete);
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
                    // 削除が終わったら初期化
                    orgDeleteId = INIT_NUMBER;
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
    } else {
        infoLevel.value = MessageConstants.LEVEL_ERROR;
        messageType.value = MessageConstants.VIEW_OK;
        message.value = getErrorUniqueIdMessage(orgDeleteId);
        return;
    }
}

function onCancel() {
    history.back();
}
function recievePagingNumber(selecteddNumber: number) {
    pageNumber.value = selecteddNumber;
    // onSearchでページング複写
    onSearch();
}

function recieveSubmit(button: string, callerMethod: string) {

    // ダイアログでyesなら削除
    if (callerMethod === deleteText && MessageConstants.BUTTON_YES === button) {
        doDelete();
    }

    infoLevel.value = 0;
    messageType.value = 0;
    caller.value = INIT_CALLER;
}
</script>
<template>
    <!-- SE権限 -->
    <AdminInfo :user-dto="userDto"></AdminInfo>

    <h1>利用者組織検索</h1>

    <h3>検索条件</h3>
    <div class="one-line">
        <div class="left-area">検索語</div>
        <div class="right-area"><input type="text" v-model="capsuleDto.searchNaturalWords"></div>
    </div>
    <div class="one-line">
        <div class="left-area">検索</div>
        <div class="right-area"><button @click="onSearch">検索</button></div>
    </div>

    <h3>検索結果</h3>
    <div class="one-line-scroll">
        <table>
            <tbody>
                <tr>
                    <th>コード</th>
                    <th>名称</th>
                    <th>住所</th>
                    <th>&nbsp;</th>
                    <th>&nbsp;</th>
                </tr>
            </tbody>

            <tbody>
                <tr v-for="entity in resultDto.listRiyoushaOrg" :key="entity.riyoushaOrgMasterId">
                    <td>{{ entity.riyoushaOrgMasterCode }}</td>
                    <td>({{ entity.allNameKana }})<br>{{ entity.allName }}</td>
                    <td>{{ entity.addressAll }}</td>
                    <td><button @click="onEdit(entity.riyoushaOrgMasterCode)">編集</button></td>
                    <td><button @click="onDelete(entity.riyoushaOrgMasterId)">削除</button></td>
                </tr>
            </tbody>
        </table>
    </div>
    <!-- ページング -->
    <PagingControl :all-count="allCount" :limit="limit" :page-number="pageNumber"
        @send-paging-number="recievePagingNumber"></PagingControl>

    <div class="footer">
        <button class="footer-button" @click="onCancel">キャンセル</button>
    </div>

    <!-- 利用者組織編集画面 -->
    <div v-if="isOrgEdit">
        <RiyoushaOrgEdit :user-dto="userDto" :selected-id="selectedOrgId"
            @send-cancel-riyousha-org="recieveCancelRiyoushaOrg"
            @send-riyousha-org-interface="recieveRiyoushaOrgInterface"></RiyoushaOrgEdit>
    </div>

    <!-- メッセージ表示    -->
    <div class="overMessage" v-if="messageType !== MessageConstants.VIEW_NONE">
        <MessageView :info-level="infoLevel" :message-type="messageType" :title="MESS_PAGE_NAME" :message="message"
            :caller="caller" @send-submit="recieveSubmit">
        </MessageView>
    </div>

</template>
<style scoped></style>
