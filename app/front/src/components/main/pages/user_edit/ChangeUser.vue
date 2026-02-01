<script setup lang="ts">
import { ref, type Ref } from 'vue';
import RoutePathConstants from '../../../../routePathConstants';
import router from '../../../../router';
import { LeastUserDto, MessageConstants, MessageView, PagingControl, type LeastUserDtoInterface } from 'seijishikin-jp-normalize_common-tool';
import { getLoginUser } from '../../utils/getLoginUser';
import type { UserPersonEntityInterface } from '../../entity/userPersonEntity';
import UserDetailEdit from '../../common/user/UserDetailEdit.vue';
import { SearchUserCapsuleDto, type SearchUserCapsuleDtoInterface } from '../../dto/user/searchUserCapsuleDto';
import UserRoleConstants from '../../dto/user/userRoleConstants';
import AdminInfo from '../../common/user_info/AdminInfo.vue';
import getAuthorizedPromiseArea from '../../dto/login/getAuthorizedPromiseArea';
import { AccessTokenNotFoundError, TokenRefreshError } from '../../dto/login/errors';
import type { SearchUserEntityResultDtoInterface } from '../../dto/user/searchUserEntityResultDto';
import { DeleteUserCapsuleDto, type DeleteUserCapsuleDtoInterface } from '../../dto/user/deleteUserCapsuleDto';

// よく使う定数
const BLANK: string = "";
//const INIT_NUMBER: number = 0;
//const SERVER_STATUS_OK: number = 200;
//const SERVER_ACCEPTED: number = 202;
// const SERVER_STATUS_ERROR: number = 400;
const SERVER_STATUS_ACCEPTED: number = 201;
const SEARCH_LIMIT: number = 20;

// back側アクセス
const urlBack: string = RoutePathConstants.DOMAIN + RoutePathConstants.BASE_PATH;

// メッセージボックス表示定数
const infoLevel: Ref<number> = ref(MessageConstants.LEVEL_NONE);
const messageType: Ref<number> = ref(MessageConstants.VIEW_NONE);
const title: Ref<string> = ref(BLANK);
const message: Ref<string> = ref(BLANK);

// Paging
const pageNumber: Ref<number> = ref(0); // Mock data
const allCount: Ref<number> = ref(0); // Mock data
const limit: Ref<number> = ref(SEARCH_LIMIT); // Mock data


// ユーザ呼び出し
const userDto: Ref<LeastUserDtoInterface> = ref(getLoginUser());

// 検索
const listEntity: Ref<UserPersonEntityInterface[]> = ref([]);
const capsuleDto: Ref<SearchUserCapsuleDtoInterface> = ref(new SearchUserCapsuleDto());
const hasConditionManager: Ref<boolean> = ref(true);
const hasConditionPartnerApi: Ref<boolean> = ref(true);
const hasConditionPerson: Ref<boolean> = ref(true);
const hasConditionKigyouDt: Ref<boolean> = ref(true);
const hasConditionSeijidantai: Ref<boolean> = ref(true);
function onSearch() {

    // 検索条件の設定
    capsuleDto.value.listRole.splice(0);
    capsuleDto.value.limit = limit.value;

    if (hasConditionManager) {
        capsuleDto.value.listRole.push(UserRoleConstants.MANAGER);
    }
    if (hasConditionPartnerApi) {
        capsuleDto.value.listRole.push(UserRoleConstants.PARTNER_API);
    }
    if (hasConditionPerson) {
        capsuleDto.value.listRole.push(UserRoleConstants.KANRENSHA_PERSON);
    }
    if (hasConditionKigyouDt) {
        capsuleDto.value.listRole.push(UserRoleConstants.KANRENSHA_KIGYOU_DT);
    }
    if (hasConditionSeijidantai) {
        capsuleDto.value.listRole.push(UserRoleConstants.KANRENSHA_SEIJIDANTAI);
    }

    // 検索実行
    getAuthorizedPromiseArea().then(token => {
        const url = urlBack + "/edit-user/search";
        const method = "POST";
        const body = JSON.stringify(capsuleDto.value);
        const headers = {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'X-AUTH-TOKEN': 'Bearer ' + token
        };
        fetch(url, { method, headers, body })
            .then(async (response) => {
                const resultDto: SearchUserEntityResultDtoInterface = await response.json();

                if (resultDto.listPersonEntity.length === 0) {
                    infoLevel.value = MessageConstants.LEVEL_INFO;
                    // トークン保持ができていない場合
                    messageType.value = MessageConstants.VIEW_TOAST;
                    title.value = "ユーザ検索";
                    message.value = "検索結果が0件でした";

                } else {
                    listEntity.value = resultDto.listPersonEntity;
                    allCount.value = resultDto.allCount;
                    pageNumber.value = resultDto.pageNumber;
                }
            })
            .catch((e) => {
                if (e instanceof AccessTokenNotFoundError) {
                    infoLevel.value = MessageConstants.LEVEL_ERROR;
                    // トークン保持ができていない場合
                    messageType.value = MessageConstants.VIEW_OK;
                    title.value = "現在トークンが存在しません";
                    message.value = e.message;
                    return;
                }
                if (e instanceof TokenRefreshError) {
                    // 取得に失敗している場合
                    infoLevel.value = MessageConstants.LEVEL_ERROR;
                    messageType.value = MessageConstants.VIEW_OK;
                    title.value = "有効期限まじかのトークンを再取得できませんでした";
                    message.value = e.message;
                    return;
                }
                infoLevel.value = MessageConstants.LEVEL_ERROR;
                messageType.value = MessageConstants.VIEW_OK;
                title.value = "システムエラーが発生しました";
                message.value = "システム管理者にお問い合わせください";
            });
    });
}

const isViewEdit: Ref<boolean> = ref(false);

function onEdit(selectedId: number) {
    selectedUserId.value = selectedId;
    isViewEdit.value = true;
}

const deleteUserDto: LeastUserDtoInterface = new LeastUserDto();
function onDelete(selectedId: number) {

    const entityDelete: UserPersonEntityInterface | undefined = listEntity.value.filter((e) => e.userPersonId === selectedId)[0];
    if (entityDelete === undefined) {
        infoLevel.value = MessageConstants.LEVEL_ERROR;
        // トークン保持ができていない場合
        messageType.value = MessageConstants.VIEW_OK;
        title.value = "ユーザ削除処理";
        message.value = "削除するユーザが指定できませんでした。";
        // ユーザが指定できない場合はメッセージを出して離脱
        return;
    } else {
        deleteUserDto.userPersonId = entityDelete.userPersonId;
        deleteUserDto.userPersonCode = entityDelete.userPersonCode;
        deleteUserDto.userPersonName = entityDelete.userPersonName;

        infoLevel.value = MessageConstants.LEVEL_WARNING;
        // トークン保持ができていない場合
        messageType.value = MessageConstants.VIEW_YES_NO;
        title.value = "ユーザ削除処理";
        message.value = entityDelete.userPersonName + "を削除します。戻すことはできません。よろしいですか";
    }
}

// ユーザ検索と選択
const selectedUserId: Ref<number> = ref(0);


function recieveCancelEditUser() {
    isViewEdit.value = false;
}

function recieveEditUserInterface() {
    isViewEdit.value = false;
    // 更新後に最新データ取得
    onSearch();
}




function onCancel() {
    router.push(RoutePathConstants.PAGE_LOGIN);
}

function recieveSubmit(button: string) {
    // 削除前の確認
    if (button === "yes") {

        const capsuleDtoDelete: DeleteUserCapsuleDtoInterface = new DeleteUserCapsuleDto();
        capsuleDtoDelete.userDto = deleteUserDto;
        capsuleDtoDelete.withdrawReason = "SE権限者による作業"; // TODO 必要ならば入力窓作成
        // 処理実行
        getAuthorizedPromiseArea().then(token => {
            const url = urlBack + "/edit-user/delete";
            const method = "POST";
            const body = JSON.stringify(capsuleDtoDelete);
            const headers = {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
                'X-AUTH-TOKEN': 'Bearer ' + token
            };
            fetch(url, { method, headers, body })
                .then(async (response) => {
                    const resultDto = await response.json();
                    title.value = "ユーザ削除処理";
                    if (SERVER_STATUS_ACCEPTED == response.status) {
                        infoLevel.value = MessageConstants.LEVEL_ERROR;
                        messageType.value = MessageConstants.VIEW_OK;
                        message.value = resultDto.message;
                    } else {
                        infoLevel.value = MessageConstants.LEVEL_INFO;
                        messageType.value = MessageConstants.VIEW_TOAST;
                        message.value = "ユーザ削除処理が完了しました";
                        onSearch();
                    }
                })
                .catch((e) => {
                    if (e instanceof AccessTokenNotFoundError) {
                        infoLevel.value = MessageConstants.LEVEL_ERROR;
                        // トークン保持ができていない場合
                        messageType.value = MessageConstants.VIEW_OK;
                        title.value = "現在トークンが存在しません";
                        message.value = e.message;
                        return;
                    }
                    if (e instanceof TokenRefreshError) {
                        // 取得に失敗している場合
                        infoLevel.value = MessageConstants.LEVEL_ERROR;
                        messageType.value = MessageConstants.VIEW_OK;
                        title.value = "有効期限まじかのトークンを再取得できませんでした";
                        message.value = e.message;
                        return;
                    }
                    infoLevel.value = MessageConstants.LEVEL_ERROR;
                    messageType.value = MessageConstants.VIEW_OK;
                    title.value = "システムエラーが発生しました";
                    message.value = "システム管理者にお問い合わせください";
                });
        });
    }

    infoLevel.value = 0;
    messageType.value = 0;
}

function recievePagingNumber(selecteddNumber: number) {
    capsuleDto.value.pageNumber = selecteddNumber;
    onSearch();
}
</script>
<template>

    <!-- ユーザメニュー兼チェック -->
    <AdminInfo :user-dto="userDto"></AdminInfo>

    <h1>ユーザ変更</h1>

    <h3>ユーザ検索条件</h3>
    <div class="one-line">
        <div class="left-area">
            ユーザ名
        </div>
        <div class="right-area">
            <input type="text" v-model="capsuleDto.name">
        </div>
    </div>

    <div class="one-line">
        <div class="left-area">
            ユーザ権限
        </div>
        <div class="right-area">
            <div class="form-group-vertical">
                <span>
                    <input type="checkbox" v-model="hasConditionManager">運営者
                    <input type="checkbox" v-model="hasConditionPartnerApi" class="left-space">APIパートナー
                </span>
                <span>

                    <input type="checkbox" v-model="hasConditionPerson">関連者個人
                    <input type="checkbox" v-model="hasConditionKigyouDt" class="left-space">関連者企業／団体
                    <input type="checkbox" v-model="hasConditionSeijidantai" class="left-space">政治団体
                </span>
            </div>
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

    <h3>ユーザ検索結果</h3>
    <!-- ページング -->
    <PagingControl :all-count="allCount" :limit="limit" :page-number="pageNumber"
        @send-paging-number="recievePagingNumber"></PagingControl>

    <!-- 選択されたユーザを編集 -->
    <div class="one-line-scroll">
        <table>
            <tbody>
                <tr>
                    <th>コード</th>
                    <th>名前</th>
                    <th>&nbsp;</th>
                    <th>&nbsp;</th>
                </tr>
                <tr v-for="entity of listEntity" :key="entity.userPersonId">
                    <td>{{ entity.userPersonCode }}</td>
                    <td>{{ entity.userPersonName }}</td>
                    <td><button @click="onEdit(entity.userPersonId)">編集</button></td>
                    <td><button @click="onDelete(entity.userPersonId)">削除</button></td>
                </tr>
            </tbody>
        </table>
    </div>
    <div class="footer">
        <button @click="onCancel" class="footer-button">キャンセル</button>
    </div>

    <!-- ユーザ編集 -->
    <div v-if="isViewEdit" class="overComponent">
        <UserDetailEdit :user-dto="userDto" :edit-user-id="selectedUserId"
            @send-cancel-edit-user="recieveCancelEditUser" @send-edit-user-interface="recieveEditUserInterface">
        </UserDetailEdit>
    </div>

    <!-- メッセージ表示 -->
    <div class="overMessage" v-if="messageType !== MessageConstants.VIEW_NONE">
        <MessageView :info-level="infoLevel" :message-type="messageType" :title="title" :message="message"
            @send-submit="recieveSubmit">
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
}
</style>
