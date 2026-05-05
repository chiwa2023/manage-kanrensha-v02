<script setup lang="ts">
import { onMounted, ref, type Ref } from 'vue';
import RiyoushaPartnerApiEdit from '../../common/riyousha_edit/RiyoushaPartnerApiEdit.vue';
import { useUserInfoStore } from '../../stores/storeUserInfo';
import { MessageConstants, MessageView, useUserInfoStoreCommon, type FrameworkMessageAndResultDtoInterface, type LeastUserDtoInterface } from 'seijishikin-jp-normalize_common-tool';
import { getActivePinia } from 'pinia';
import router from '../../../../router';
import RoutePathConstants from '../../../../routePathConstants';
import { getLoginUser } from '../../utils/getLoginUser';
import type { RiyoushaPartnerApiDtoInterface } from '../../dto/riyousha/riyoushaPartnerApiDto';
import { SaveRiyoushaPartnerApiCapsuleDto, type SaveRiyoushaPartnerApiCapsuleDtoInterface } from '../../dto/riyousha/saveRiyoushaPartnerApiCapsuleDto';
import getAuthorizedPromiseArea from '../../dto/login/getAuthorizedPromiseArea';
import { AccessTokenNotFoundError, TokenRefreshError } from '../../dto/login/errors';
import { RiyoushaPartnerApiMasterEntity, type RiyoushaPartnerApiMasterEntityInterface } from '../../entity/riyoushaPartnerApiMasterEntity';
import PartnerApiInfo from '../../common/user_info/PartnerApiInfo.vue';

// よく使う定数
const BLANK: string = "";
// const INIT_NUMBER: number = 0;
const INIT_BOOLEAN: boolean = false;
// const SERVER_STATUS_OK: number = 200;
// const SERVER_STATUS_ERROR: number = 400;
// メッセージボックス表示定数
const infoLevel: Ref<number> = ref(MessageConstants.LEVEL_NONE);
const messageType: Ref<number> = ref(MessageConstants.VIEW_NONE);
const title: Ref<string> = ref(BLANK);
const message: Ref<string> = ref(BLANK);

// back側アクセス
const urlBack: string = RoutePathConstants.DOMAIN + RoutePathConstants.BASE_PATH;

// ユーザ呼び出し
const userDto: Ref<LeastUserDtoInterface> = ref(getLoginUser());

const editEntity: Ref<RiyoushaPartnerApiMasterEntityInterface> = ref(new RiyoushaPartnerApiMasterEntity());

onMounted(() => {
    // 保存していたアクセストークンと有効期限を取得
    const userInfo = useUserInfoStore();

    // common-toolにアクセス情報を渡す
    const userInfoCommon = useUserInfoStoreCommon(getActivePinia());
    userInfoCommon.jwtDto = userInfo.jwtDto;
    userInfoCommon.userDto = userInfo.userDto;
});

function recieveCancelPartnerApi() {
    // ユーザ・role登録できているのでログインできないことはないし、制御もできるはず
    router.push(RoutePathConstants.PAGE_MENU_PARTNER_API);
}

let isSaveSuccess: boolean = INIT_BOOLEAN;
function recievePartnerApiInterface(editDto: RiyoushaPartnerApiDtoInterface) {

    editDto.isCombineUser = true; // 初期登録時は紐づけ処理する

    const capsuleDto: SaveRiyoushaPartnerApiCapsuleDtoInterface = new SaveRiyoushaPartnerApiCapsuleDto();
    capsuleDto.userDto = userDto.value;
    capsuleDto.riyoushaPartnerApiDto = editDto;

    title.value = "利用者APIパートナー追加";
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
                // 処理が成功したら再登録できないようにアップロードファイル情報を初期化
                if (resultDto.isFailure) {
                    infoLevel.value = MessageConstants.LEVEL_WARNING;
                    messageType.value = MessageConstants.VIEW_OK;
                } else {
                    infoLevel.value = MessageConstants.LEVEL_INFO;
                    messageType.value = MessageConstants.VIEW_TOAST;
                    // 更新成功時
                    isSaveSuccess = true;
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

function recieveSubmit(button: string) {
    console.log(button); // 警告除け
    infoLevel.value = 0;
    messageType.value = 0;

    // 登録成功時のみ運営者メニューに遷移
    if (isSaveSuccess) {
        router.push(RoutePathConstants.PAGE_MENU_PARTNER_API);
    }
}

</script>
<template>
    
    <!-- APIパートナー -->
    <PartnerApiInfo :user-dto="userDto"></PartnerApiInfo>

    <h1>APIパートナー追加</h1><br>

    <!-- 運営者編集 -->
    <RiyoushaPartnerApiEdit :edit-entity="editEntity" @send-cancel-partner-api="recieveCancelPartnerApi"
        @send-partner-api-interface="recievePartnerApiInterface">
    </RiyoushaPartnerApiEdit>

    <!-- メッセージ表示 -->
    <div class="overMessage" v-if="messageType !== MessageConstants.VIEW_NONE">
        <MessageView :info-level="infoLevel" :message-type="messageType" :title="title" :message="message"
            @send-submit="recieveSubmit">
        </MessageView>
    </div>

</template>
<style scoped></style>
