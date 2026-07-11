<script setup lang="ts">
import { ref, type Ref } from 'vue';
import router from '../../../../router';
import { getErrorMessage, getErrorUniqueIdMessage, MessageConstants, MessageView, type FrameworkMessageAndResultDtoInterface, type LeastUserDtoInterface } from 'seijishikin-jp-normalize_common-tool';
import { getLoginUser } from '../../utils/getLoginUser';
import UserRoleConstants from '../../dto/user/userRoleConstants';
import { SearchUserCapsuleDto, type SearchUserCapsuleDtoInterface } from '../../dto/user/searchUserCapsuleDto';
import type { UserPersonEntityInterface } from '../../entity/userPersonEntity';
import { PromoteUserAdminCapsuleDto, type PromoteUserAdminCapsuleDtoInterface } from '../../dto/user/PromoteUserAdminCapsuleDto';
import getAuthorizedPromiseArea from '../../dto/login/getAuthorizedPromiseArea';
import RoutePathConstants from '../../../../routePathConstants';
import { AccessTokenNotFoundError, TokenRefreshError } from '../../dto/login/errors';
import { SearchUserEntityResultDto, type SearchUserEntityResultDtoInterface } from '../../dto/user/searchUserEntityResultDto';
import AdminInfo from '../../common/user_info/AdminInfo.vue';

// back側アクセス
const urlBack: string = RoutePathConstants.DOMAIN + RoutePathConstants.BASE_PATH;

// よく使う定数
const BLANK: string = "";
// const INIT_NUMBER: number = 0;
// const SERVER_STATUS_OK: number = 200;
// const SERVER_STATUS_ERROR: number = 400;
const INQUIRE_FLG: boolean = false;
const MESS_PAGE_NAME: string = "SE権限追加推薦処理";
const INIT_CALLER: string = "no branch";

// メッセージボックス表示定数
const infoLevel: Ref<number> = ref(MessageConstants.LEVEL_NONE);
const messageType: Ref<number> = ref(MessageConstants.VIEW_NONE);
const caller: Ref<string> = ref(INIT_CALLER);
const message: Ref<string> = ref(BLANK);

// ユーザ呼び出し
const userDto: Ref<LeastUserDtoInterface> = ref(getLoginUser());

// 検索
const capsuleDto: Ref<SearchUserCapsuleDtoInterface> = ref(new SearchUserCapsuleDto());
capsuleDto.value.limit = 30;
const resultDto: Ref<SearchUserEntityResultDtoInterface> = ref(new SearchUserEntityResultDto());

function onSearch() {
    // 運営者だけを検索
    capsuleDto.value.listRole.push(UserRoleConstants.MANAGER);

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
                resultDto.value = await response.json();
            })
            .catch((e) => {
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
    });

}


function onCancel() {
    router.back();
}

async function onAdminPromote(userId: number) {
    const userEntityPromote: UserPersonEntityInterface | undefined = resultDto.value.listPersonEntity.filter(
        (e) => userId === e.userPersonId)[0];

    const capsuleDto: PromoteUserAdminCapsuleDtoInterface = new PromoteUserAdminCapsuleDto();
    if (userEntityPromote !== undefined) {
        capsuleDto.entityUserPromote = userEntityPromote;
        capsuleDto.userDto = userDto.value;

        // 登録処理
        getAuthorizedPromiseArea().then(token => {
            const url = urlBack + "/user-role/promote";
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
                    infoLevel.value = MessageConstants.LEVEL_INFO;
                    // トークン保持ができていない場合
                    messageType.value = MessageConstants.VIEW_TOAST;
                    message.value = "SE権限追加推薦処理" + resultDto.message;
                })
                .catch((e) => {
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
        });
    } else {
        infoLevel.value = MessageConstants.LEVEL_ERROR;
        messageType.value = MessageConstants.VIEW_OK;
        message.value = getErrorUniqueIdMessage(userId);
        return;

    }
}

function recieveSubmit() {
    infoLevel.value = 0;
    messageType.value = 0;
}

</script>
<template>
    <!-- ユーザメニュー兼チェック -->
    <AdminInfo :user-dto="userDto"></AdminInfo>

    <h1>SE権限追加推薦</h1>

    <h3>運営者検索条件</h3>
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
            検索
        </div>
        <div class="right-area">
            <button @click="onSearch">検索</button>
        </div>
    </div>

    <h3>ユーザ検索結果</h3>

    <div class="one-line">
        <table>
            <tbody>
                <tr>
                    <th>コード</th>
                    <th>名前</th>
                    <th>&nbsp;</th>
                    <th>&nbsp;</th>
                </tr>
                <tr v-for="entity of resultDto.listPersonEntity" :key="entity.userPersonId">
                    <td>{{ entity.userPersonCode }}</td>
                    <td>{{ entity.userPersonName }}</td>
                    <td><button @click="onAdminPromote(entity.userPersonId)">権限追加推薦</button></td>
                </tr>
            </tbody>
        </table>
    </div>

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
