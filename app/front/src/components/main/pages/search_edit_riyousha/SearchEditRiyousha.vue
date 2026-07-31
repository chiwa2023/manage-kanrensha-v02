<script setup lang="ts">
import { ref, type Ref } from 'vue';
import { getErrorMessage, MessageConstants, MessageView, PagingControl, } from 'seijishikin-jp-normalize_common-tool';
import AdminInfo from '../../common/user_info/AdminInfo.vue';
import type { FrameworkMessageAndResultDtoInterface, LeastUserDtoInterface } from 'seijishikin-jp-normalize_common-tool';
import { getLoginUser } from '../../utils/getLoginUser';
import RoutePathConstants from '../../../../routePathConstants';
import { SearchRiyoushaAllCapsuleDto, type SearchRiyoushaAllCapsuleDtoInterface } from '../../dto/riyousha/searchRiyoushaAllCapsuleDto';
import { SearchRiyoushaAllResultDto, type SearchRiyoushaAllResultDtoInterface } from '../../dto/riyousha/searchRiyoushaAllResultDto';
import { AccessTokenNotFoundError, TokenRefreshError } from '../../dto/login/errors';
import getAuthorizedPromiseArea from '../../dto/login/getAuthorizedPromiseArea';
import UserRoleConstants from '../../dto/user/userRoleConstants';
import { RiyoushaManagerMasterEntity, type RiyoushaManagerMasterEntityInterface } from '../../entity/riyoushaManagerMasterEntity';
import type { RiyoushaManagerDtoInterface } from '../../dto/riyousha/riyoushaManagerDto';
import { SaveRiyoushaManagerCapsuleDto, type SaveRiyoushaManagerCapsuleDtoInterface } from '../../dto/riyousha/saveRiyoushaManagerCapsuleDto';
import type { RiyoushaPartnerApiDtoInterface } from '../../dto/riyousha/riyoushaPartnerApiDto';
import { SaveRiyoushaPartnerApiCapsuleDto, type SaveRiyoushaPartnerApiCapsuleDtoInterface } from '../../dto/riyousha/saveRiyoushaPartnerApiCapsuleDto';
import { RiyoushaPartnerApiMasterEntity, type RiyoushaPartnerApiMasterEntityInterface } from '../../entity/riyoushaPartnerApiMasterEntity';
import RiyoushaManagerEdit from '../../common/riyousha_edit/RiyoushaManagerEdit.vue';
import { type SearchViewCombineAliveRiyoushaResultDtoInterface } from '../../dto/riyousha/searchViewCombineAliveRiyoushaResultDto';
import { GetRiyoushaMasterCapsuleDto, type GetRiyoushaMasterCapsuleDtoInterface } from '../../dto/riyousha/getRiyoushaMasterCapsuleDto';
import type { GetRiyoushaMasterResultDtoInterface } from '../../dto/riyousha/getRiyoushaMasterResultDto';
import RiyoushaPartnerApiEdit from '../../common/riyousha_edit/RiyoushaPartnerApiEdit.vue';
import { DeleteRiyoushaMasterCapsuleDto, type DeleteRiyoushaMasterCapsuleDtoInterface } from '../../dto/riyousha/deleteRiyoushaMasterCapsuleDto';

// よく使う定数
const BLANK: string = "";
const INIT_NUMBER: number = 0;
const INIT_BOOLEAN: boolean = false;
const SEARCH_LIMIT: number = 20;
// const SERVER_STATUS_OK: number = 200;
// const SERVER_STATUS_ERROR: number = 400;
const INQUIRE_FLG: boolean = false;
const ERR_MESS_ONLY: boolean = true;
const MESS_PAGE_NAME: string = "利用者APIパートナー編集";
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

// 検索用変数
const capsuleDto: Ref<SearchRiyoushaAllCapsuleDtoInterface> = ref(new SearchRiyoushaAllCapsuleDto());
const resultDto: Ref<SearchRiyoushaAllResultDtoInterface> = ref(new SearchRiyoushaAllResultDto());

function onSearch() {
    capsuleDto.value.allCount = allCount.value;
    capsuleDto.value.limit = limit.value;
    capsuleDto.value.pageNumber = pageNumber.value;

    getAuthorizedPromiseArea().then(token => {
        const url = urlBack + "/riyousha/search-all";
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
                if (resultDto.value.listAllRiyousha.length == 0) {
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


function onEdit(index: number) {

    const editDto: SearchViewCombineAliveRiyoushaResultDtoInterface | undefined =
        resultDto.value.listAllRiyousha[index];

    if (editDto !== undefined) {
        const capsuleDto: GetRiyoushaMasterCapsuleDtoInterface = new GetRiyoushaMasterCapsuleDto();
        capsuleDto.riyoushaRole = editDto.roleBase;
        capsuleDto.riyoushaCode = editDto.riyoushaCode;

        getAuthorizedPromiseArea().then(token => {
            const url = urlBack + "/riyousha/get-myself";
            const method = "POST";
            const body = JSON.stringify(capsuleDto);
            const headers = {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
                'X-AUTH-TOKEN': 'Bearer ' + token
            };
            fetch(url, { method, headers, body })
                .then(async (response) => {
                    const resultDtoMySelf: GetRiyoushaMasterResultDtoInterface = await response.json();
                    message.value = resultDtoMySelf.message;
                    // 処理が成功したら再登録できないようにアップロードファイル情報を初期化
                    if (resultDtoMySelf.isFailure) {
                        infoLevel.value = MessageConstants.LEVEL_WARNING;
                        messageType.value = MessageConstants.VIEW_OK;
                    } else {
                        // editEntityを変更すると自動で呼び出し
                        if (UserRoleConstants.MANAGER === editDto.roleBase) {
                            editEntityManager.value = resultDtoMySelf.managerMasterEntity;
                            isManagerEdit.value = true;
                            isPartnerEdit.value = false;
                        } else {
                            editEntityPartner.value = resultDtoMySelf.partnerApiMasterEntity;
                            isPartnerEdit.value = true;
                            isManagerEdit.value = false;
                        }
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
}

let personDeleteIndex: number = INIT_NUMBER;
const deleteText: string = "delete";
function onDelete(index: number) {
    infoLevel.value = MessageConstants.LEVEL_WARNING;
    messageType.value = MessageConstants.VIEW_YES_NO;
    message.value = "削除すると戻すことができません。よろしいですか？";
    personDeleteIndex = index;
    caller.value = deleteText;
}

function doDelete() {
    // 削除対象を決定
    const editDto: SearchViewCombineAliveRiyoushaResultDtoInterface | undefined =
        resultDto.value.listAllRiyousha[personDeleteIndex];
    if (editDto !== undefined) {

        const capsuleDtoDelete: DeleteRiyoushaMasterCapsuleDtoInterface = new DeleteRiyoushaMasterCapsuleDto();
        capsuleDtoDelete.userDto = userDto.value;
        capsuleDtoDelete.riyoushaRole = editDto.roleBase;
        capsuleDtoDelete.riyoushaCode = editDto.riyoushaCode;

        getAuthorizedPromiseArea().then(token => {
            const url = urlBack + "/riyousha/delete";
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
                    } else {
                        infoLevel.value = MessageConstants.LEVEL_INFO;
                        messageType.value = MessageConstants.VIEW_TOAST;
                    }
                    // 削除が終わったら初期化
                    personDeleteIndex = INIT_NUMBER;
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




function onCancel() {
    history.back();
}


function recieveCancelManager() {
    // 連続処理の可能性があるので明確に遷移を支持されない限り遷移しない
    isManagerEdit.value = false;
}

// 編集Entity
const editEntityManager: Ref<RiyoushaManagerMasterEntityInterface> = ref(new RiyoushaManagerMasterEntity());
const editEntityPartner: Ref<RiyoushaPartnerApiMasterEntityInterface> = ref(new RiyoushaPartnerApiMasterEntity());

const isManagerEdit: Ref<boolean> = ref(INIT_BOOLEAN);
const isPartnerEdit: Ref<boolean> = ref(INIT_BOOLEAN);

function recieveManagerInterface(editDto: RiyoushaManagerDtoInterface) {

    editDto.isCombineUser = true; // 初期登録時は紐づけ処理する

    const capsuleDto: SaveRiyoushaManagerCapsuleDtoInterface = new SaveRiyoushaManagerCapsuleDto();
    capsuleDto.userDto = userDto.value;
    capsuleDto.riyoushaManagerDto = editDto;

    getAuthorizedPromiseArea().then(token => {
        const url = urlBack + "/riyousha/save-manager";
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
                    isManagerEdit.value = false;
                } else {
                    infoLevel.value = MessageConstants.LEVEL_INFO;
                    messageType.value = MessageConstants.VIEW_TOAST;
                    isManagerEdit.value = false;
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


function recieveCancelPartnerApi() {
    isPartnerEdit.value = false;
}

function recievePartnerApiInterface(editDto: RiyoushaPartnerApiDtoInterface) {

    const capsuleDto: SaveRiyoushaPartnerApiCapsuleDtoInterface = new SaveRiyoushaPartnerApiCapsuleDto();
    capsuleDto.userDto = userDto.value;
    capsuleDto.riyoushaPartnerApiDto = editDto;

    getAuthorizedPromiseArea().then(token => {
        const url = urlBack + "/riyousha/save-partner-api";
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
                    isManagerEdit.value = false;
                } else {
                    infoLevel.value = MessageConstants.LEVEL_INFO;
                    messageType.value = MessageConstants.VIEW_TOAST;
                    isManagerEdit.value = false;
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

    <!-- SEページ -->
    <AdminInfo :user-dto="userDto"></AdminInfo>

    <h1>利用者検索</h1> <br>

    <h3 class="accent-h3">検索条件</h3>

    <div class="one-line">
        <div class="left-area">
            検索対象
        </div>
        <div class="right-area">
            <input type="checkbox" v-model="capsuleDto.isPartnerApiSearch">API接続者</input>
            <input type="checkbox" v-model="capsuleDto.isManagerSearch" class="left-space">運営者</input>
            <input type="checkbox" v-model="capsuleDto.isAdminSearch" class="left-space">SE権限</input>
        </div>
    </div>
    <div class="one-line">
        <div class="left-area">
            検索語
        </div>
        <div class="right-area">
            <input type="text" class="name-input" v-model="capsuleDto.searchNaturalWords">
            <button @click="onSearch" class="left-space">検索</button>
        </div>
    </div>

    <h3 class="accent-h3">検索結果</h3>
    <PagingControl :all-count="allCount" :limit="limit" :page-number="pageNumber"
        @send-paging-number="recievePagingNumber"></PagingControl>

    <div class="one-line">
        <table>
            <tbody>
                <tr>
                    <th>権限</th>
                    <th>姓名</th>
                    <th>&nbsp;</th>
                    <th>&nbsp;</th>
                </tr>
                <tr v-for="(dto, index) of resultDto.listAllRiyousha" :key="index">
                    <td>{{ UserRoleConstants.getLabel(dto.roleHas) }} </td>
                    <td>{{ dto.allName }} </td>
                    <td><button @click="onEdit(index)">編集</button></td>
                    <td><button @click="onDelete(index)">削除</button></td>
                </tr>
            </tbody>
        </table>

    </div>

    <PagingControl :all-count="allCount" :limit="limit" :page-number="pageNumber"
        @send-paging-number="recievePagingNumber"></PagingControl>

    <!-- メッセージ表示    -->
    <div class="overMessage" v-if="messageType !== MessageConstants.VIEW_NONE">
        <MessageView :info-level="infoLevel" :message-type="messageType" :title="MESS_PAGE_NAME" :message="message"
            :caller="caller" @send-submit="recieveSubmit">
        </MessageView>
    </div>

    <!-- 運営者編集 -->
    <div v-if="isManagerEdit">
        <RiyoushaManagerEdit :user-dto="userDto" :edit-entity="editEntityManager"
            @send-cancel-manager="recieveCancelManager" @send-manager-interface="recieveManagerInterface">
        </RiyoushaManagerEdit>
    </div>

    <!-- API接続者編集 -->
    <div v-if="isPartnerEdit">
        <RiyoushaPartnerApiEdit :user-dto="userDto" :edit-entity="editEntityPartner"
            @send-cancel-partner-api="recieveCancelPartnerApi" @send-partner-api-interface="recievePartnerApiInterface">
        </RiyoushaPartnerApiEdit>
    </div>

    <div v-if="!isManagerEdit && !isPartnerEdit" class="footer">
        <button @click="onCancel" class="footer-button">キャンセル</button>
    </div>

</template>
<style scoped></style>
