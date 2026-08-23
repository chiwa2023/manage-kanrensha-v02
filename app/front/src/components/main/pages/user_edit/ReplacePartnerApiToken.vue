<script setup lang="ts">
import { onBeforeMount, ref, type Ref } from 'vue';
import { FrameworkCapsuleDto, getErrorMessage, InputDatetime, type FrameworkCapsuleDtoInterface, type LeastUserDtoInterface } from 'seijishikin-jp-normalize_common-tool';
import { getLoginUser } from '../../utils/getLoginUser';
import router from '../../../../router';
import { PartnerAccessTokenStateDto, type PartnerAccessTokenStateDtoInterface } from '../../dto/user/partnerAccessTokenStateDto';
import IpAddress from '../../common/util/IpAddress.vue';
import RoutePathConstants from '../../../../routePathConstants';
import { PartnerApiTokenCapsuleDto, type PartnerApiTokenCapsuleDtoInterface } from '../../dto/user/partnerApiTokenCapsuleDto';
import type { PartnerApiTokenResultDtoInterface } from '../../dto/user/partnerApiTokenResultDto';
import { MessageConstants, MessageView } from 'seijishikin-jp-normalize_common-tool';
import getAuthorizedPromiseArea from '../../dto/login/getAuthorizedPromiseArea';
import { AccessTokenNotFoundError, TokenRefreshError } from '../../dto/login/errors';
import { logout } from '../../utils/logout';
import PartnerApiInfo from '../../common/user_info/PartnerApiInfo.vue';

// back側アクセス
const urlBack: string = RoutePathConstants.DOMAIN + RoutePathConstants.BASE_PATH;

// よく使う定数
const BLANK: string = "";
// const INIT_NUMBER: number = 0;
const INQUIRE_FLG: boolean = false;
const ERR_MESS_ONLY: boolean = true;
const MESS_PAGE_NAME: string = "長期トークン作成";
const INIT_CALLER: string = "no branch";

// メッセージボックス表示定数
const infoLevel: Ref<number> = ref(MessageConstants.LEVEL_NONE);
const messageType: Ref<number> = ref(MessageConstants.VIEW_NONE);
const caller: Ref<string> = ref(INIT_CALLER);
const message: Ref<string> = ref(BLANK);

// ユーザ呼び出し
const userDto: Ref<LeastUserDtoInterface> = ref(getLoginUser());

// 表示内容
const stateDto: Ref<PartnerAccessTokenStateDtoInterface> = ref(new PartnerAccessTokenStateDto());
const newToken: Ref<string> = ref("");
const hasNotToken: string = "hasNotToken";
onBeforeMount(() => {
    // 前回トークン状態(トークン自身は除く)を取得
    getAuthorizedPromiseArea().then(token => {
        const capsuleDto: FrameworkCapsuleDtoInterface = new FrameworkCapsuleDto();
        capsuleDto.userDto = userDto.value;

        const url = urlBack + "/partner-api/get-state";
        const method = "POST";
        const body = JSON.stringify(capsuleDto);
        const headers = {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'X-AUTH-TOKEN': 'Bearer ' + token
        };
        fetch(url, { method, headers, body })
            .then(async (response) => {
                // 取得できないときはステータス500
                stateDto.value = await response.json();
            })
            .catch((error) => {
                message.value = getErrorMessage(error, ERR_MESS_ONLY);
                infoLevel.value = MessageConstants.LEVEL_ERROR;
                messageType.value = MessageConstants.VIEW_OK;
                caller.value = hasNotToken;
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
});

function recieveIpAddress(ipAddress: string) {
    stateDto.value.ipAddress = ipAddress;
}

function onCancel() {
    router.back();
}

async function onSave() {
    getAuthorizedPromiseArea().then(token => {
        const capsuleDto: PartnerApiTokenCapsuleDtoInterface = new PartnerApiTokenCapsuleDto();
        capsuleDto.userDto = userDto.value;
        capsuleDto.ipAddress = stateDto.value.ipAddress;

        const url = urlBack + "/partner-api/replace-token";
        const method = "POST";
        const body = JSON.stringify(capsuleDto);
        const headers = {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'X-AUTH-TOKEN': 'Bearer ' + token
        };
        fetch(url, { method, headers, body })
            .then(async (response) => {
                const resultDto: PartnerApiTokenResultDtoInterface = await response.json();
                if (resultDto.isFailure) {
                    // 取得に失敗している場合
                    infoLevel.value = MessageConstants.LEVEL_ERROR;
                    messageType.value = MessageConstants.VIEW_OK;
                    message.value = "長期トークンが作成できませんでした。もう一度やり直してください" + resultDto.message;
                    return;
                } else {
                    // 取得に成功している場合
                    infoLevel.value = MessageConstants.LEVEL_INFO;
                    messageType.value = MessageConstants.VIEW_TOAST;
                    message.value = "長期トークンの作成に成功しました：" + resultDto.message;
                    newToken.value = resultDto.token;
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

function recieveSubmit(button: string, callerMethod: string) {

    // 現在トークンを所持していない場合はログアウト
    if (MessageConstants.BUTTON_OK === button && callerMethod === hasNotToken) {
        logout();
        router.push(RoutePathConstants.PAGE_LOGOUT);
    }

    // 非表示
    infoLevel.value = 0;
    messageType.value = 0;
    caller.value = INIT_CALLER;
}
</script>
<template>

    <PartnerApiInfo :user-dto="userDto"></PartnerApiInfo>

    <h1>APIパートナー長期トークン(再)発行</h1>

    <div class="one-line">
        <div class="left-area">
            ユーザ名
        </div>
        <div class="right-area">
            {{ stateDto.userName }}({{ stateDto.userCode }})
        </div>
    </div>

    <div class="one-line">
        <div class="left-area">
            IPアドレス
        </div>
        <div class="right-area">
            <IpAddress :ip-address="stateDto.ipAddress" @send-ip-address="recieveIpAddress"></IpAddress>
        </div>
    </div>

    <div class="one-line">
        <div class="left-area">
            発行時間
        </div>
        <div class="right-area">
            <InputDatetime :datetime="stateDto.createdAt" :index="1" :is-edit="false"></InputDatetime>
        </div>
    </div>

    <div class="one-line">
        <div class="left-area">
            有効期限
        </div>
        <div class="right-area">
            <InputDatetime :datetime="stateDto.expiresAt" :index="2" :is-edit="false"></InputDatetime>
        </div>
    </div>

    <div class="one-line">
        <div class="left-area">
            最新アクセス
        </div>
        <div class="right-area">
            <InputDatetime :datetime="stateDto.lastUsedAt" :index="3" :is-edit="false"></InputDatetime>
        </div>
    </div>

    <div class="one-line">
        <div class="left-area">
            失効時間
        </div>
        <div class="right-area">
            <InputDatetime :datetime="stateDto.revokedAt" :index="4" :is-edit="false"></InputDatetime>
        </div>
    </div>

    <div class="one-line">
        <div class="left-area">
            長期トークン
        </div>
        <div class="right-area">
            <div class="form-group-vertical">
                <span>全く同じトークンは再発行できません。安全な場所に保管してください</span>
                <textarea v-model="newToken" :disabled="true" class="max-input"
                    placeholder="このトークンは再発行できません。"></textarea>
            </div>
        </div>
    </div>

    <div class="footer">
        <button @click="onCancel" class="footer-button">キャンセル</button>
        <button @click="onSave" class="footer-button left-space">長期トークン発行</button>
    </div>

    <!-- メッセージ表示    -->
    <div class="overMessage" v-if="messageType !== MessageConstants.VIEW_NONE">
        <MessageView :info-level="infoLevel" :message-type="messageType" :title="MESS_PAGE_NAME" :message="message"
            :caller="caller" @send-submit="recieveSubmit">
        </MessageView>
    </div>

</template>
<style scoped></style>
