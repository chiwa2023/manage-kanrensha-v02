<script setup lang="ts">
import { ref, type Ref } from 'vue';
import { MessageConstants, MessageView, PagingControl, } from 'seijishikin-jp-normalize_common-tool';
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

// よく使う定数
const BLANK: string = "";
const INIT_NUMBER: number = 0;
const INIT_BOOLEAN: boolean = false;
const SEARCH_LIMIT: number = 20;
// const SERVER_STATUS_OK: number = 200;
// const SERVER_STATUS_ERROR: number = 400;

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
                    title.value = "利用者検索";
                    infoLevel.value = MessageConstants.LEVEL_WARNING;
                    messageType.value = MessageConstants.VIEW_OK;
                    message.value = "検索結果が0件でした";
                }
            })
            .catch((error) => {
                alert(error);
                title.value = "利用者検索";
                infoLevel.value = MessageConstants.LEVEL_ERROR;
                messageType.value = MessageConstants.VIEW_OK;
                message.value = "システム管理者にお問い合わせください";
                return;
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
                    alert(error);
                    infoLevel.value = MessageConstants.LEVEL_ERROR;
                    messageType.value = MessageConstants.VIEW_OK;
                    message.value = "システム管理者にお問い合わせください";
                    return;
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
}

function onDelete(index: number) {
    alert("削除" + index);
}


function recievePagingNumber(selecteddNumber: number) {
    pageNumber.value = selecteddNumber;
    alert("ページ情報受信");
}

function recieveSubmit(button: string) {
    console.log(button); // 警告除け
    infoLevel.value = 0;
    messageType.value = 0;
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

    title.value = "利用者運営者編集";
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
                alert(error);
                infoLevel.value = MessageConstants.LEVEL_ERROR;
                messageType.value = MessageConstants.VIEW_OK;
                message.value = "システム管理者にお問い合わせください";
                return;
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


function recieveCancelPartnerApi() {
    isPartnerEdit.value = false;
}

function recievePartnerApiInterface(editDto: RiyoushaPartnerApiDtoInterface) {

    const capsuleDto: SaveRiyoushaPartnerApiCapsuleDtoInterface = new SaveRiyoushaPartnerApiCapsuleDto();
    capsuleDto.userDto = userDto.value;
    capsuleDto.riyoushaPartnerApiDto = editDto;

    title.value = "利用者APIパートナー編集";
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
                alert(error);
                infoLevel.value = MessageConstants.LEVEL_ERROR;
                messageType.value = MessageConstants.VIEW_OK;
                message.value = "システム管理者にお問い合わせください";
                return;
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

    <!-- メッセージ表示 -->
    <div class="overMessage" v-if="messageType !== MessageConstants.VIEW_NONE">
        <MessageView :info-level="infoLevel" :message-type="messageType" :title="title" :message="message"
            @send-submit="recieveSubmit">
        </MessageView>
    </div>

    <!-- 運営者編集 -->
    <div v-if="isManagerEdit">
        <RiyoushaManagerEdit :edit-entity="editEntityManager" @send-cancel-manager="recieveCancelManager"
            @send-manager-interface="recieveManagerInterface">
        </RiyoushaManagerEdit>
    </div>

    <!-- API接続者編集 -->
    <div v-if="isPartnerEdit">
        <RiyoushaPartnerApiEdit :edit-entity="editEntityPartner" @send-cancel-partner-api="recieveCancelPartnerApi"
            @send-partner-api-interface="recievePartnerApiInterface"></RiyoushaPartnerApiEdit>
    </div>

    <div v-if="!isManagerEdit && !isPartnerEdit" class="footer">
        <button @click="onCancel" class="footer-button">キャンセル</button>
    </div>

</template>
<style scoped></style>
