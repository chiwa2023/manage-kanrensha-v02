<script setup lang="ts">
import { onMounted, ref, type Ref } from 'vue';
import RiyoushaManagerEdit from '../../common/riyousha_edit/RiyoushaManagerEdit.vue';
import { useUserInfoStore } from '../../stores/storeUserInfo';
import { getActivePinia } from 'pinia';
import { getErrorMessage, MessageConstants, MessageView, useUserInfoStoreCommon, type FrameworkMessageAndResultDtoInterface, type LeastUserDtoInterface } from 'seijishikin-jp-normalize_common-tool';
import router from '../../../../router';
import RoutePathConstants from '../../../../routePathConstants';
import { RiyoushaManagerMasterEntity, type RiyoushaManagerMasterEntityInterface } from '../../entity/riyoushaManagerMasterEntity';
import getAuthorizedPromiseArea from '../../dto/login/getAuthorizedPromiseArea';
import type { RiyoushaManagerDtoInterface } from '../../dto/riyousha/riyoushaManagerDto';
import { SaveRiyoushaManagerCapsuleDto, type SaveRiyoushaManagerCapsuleDtoInterface } from '../../dto/riyousha/saveRiyoushaManagerCapsuleDto';
import { getLoginUser } from '../../utils/getLoginUser';
import { AccessTokenNotFoundError, TokenRefreshError } from '../../dto/login/errors';
import ManagerInfo from '../../common/user_info/ManagerInfo.vue';

// props,emit
const emits = defineEmits(["sendCancelManager", "sendManagerInterface"]);

// よく使う定数
const BLANK: string = "";
// const INIT_NUMBER: number = 0;
const INIT_BOOLEAN: boolean = false;
// const SERVER_STATUS_OK: number = 200;
// const SERVER_STATUS_ERROR: number = 400;
const INQUIRE_FLG: boolean = false;
const ERR_MESS_ONLY: boolean = true;
const MESS_PAGE_NAME: string = "利用者運営者追加";
const INIT_CALLER: string = "no branch";

// メッセージボックス表示定数
const infoLevel: Ref<number> = ref(MessageConstants.LEVEL_NONE);
const messageType: Ref<number> = ref(MessageConstants.VIEW_NONE);
const caller: Ref<string> = ref(INIT_CALLER);
const message: Ref<string> = ref(BLANK);

// back側アクセス
const urlBack: string = RoutePathConstants.DOMAIN + RoutePathConstants.BASE_PATH;

// ユーザ呼び出し
const userDto: Ref<LeastUserDtoInterface> = ref(getLoginUser());

onMounted(() => {
    // 保存していたアクセストークンと有効期限を取得
    const userInfo = useUserInfoStore();

    // common-toolにアクセス情報を渡す
    const userInfoCommon = useUserInfoStoreCommon(getActivePinia());
    userInfoCommon.jwtDto = userInfo.jwtDto;
    userInfoCommon.userDto = userInfo.userDto;
});


function recieveCancelManager() {
    // ユーザ・role登録できているのでログインできないことはないし、制御もできるはず
    router.push(RoutePathConstants.PAGE_MENU_MANAGER);
}

const editEntity: Ref<RiyoushaManagerMasterEntityInterface> = ref(new RiyoushaManagerMasterEntity());

let isSaveSuccess: boolean = INIT_BOOLEAN;
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
                // 処理が成功したら再登録できないようにアップロードファイル情報を初期化
                if (resultDto.isFailure) {
                    infoLevel.value = MessageConstants.LEVEL_WARNING;
                    messageType.value = MessageConstants.VIEW_OK;
                    return;
                } else {
                    infoLevel.value = MessageConstants.LEVEL_INFO;
                    messageType.value = MessageConstants.VIEW_TOAST;
                    // 更新成功時
                    isSaveSuccess = true;
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
    infoLevel.value = 0;
    messageType.value = 0;

    // 登録成功時のみ運営者メニューに遷移
    if (isSaveSuccess) {
        router.push(RoutePathConstants.PAGE_MENU_MANAGER);
    }
}
</script>
<template>

    <!-- 運営者 -->
    <ManagerInfo :user-dto="userDto"></ManagerInfo>

    <h1>運営者追加</h1><br>

    <!-- 運営者編集 -->
    <RiyoushaManagerEdit :user-dto="userDto" :edit-entity="editEntity" @send-cancel-manager="recieveCancelManager"
        @send-manager-interface="recieveManagerInterface">
    </RiyoushaManagerEdit>

    <!-- メッセージ表示    -->
    <div class="overMessage" v-if="messageType !== MessageConstants.VIEW_NONE">
        <MessageView :info-level="infoLevel" :message-type="messageType" :title="MESS_PAGE_NAME" :message="message"
            :caller="caller" @send-submit="recieveSubmit">
        </MessageView>
    </div>

</template>
<style scoped></style>
