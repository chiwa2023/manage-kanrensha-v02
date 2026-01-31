<script setup lang="ts">
import { onBeforeMount, ref, type Ref } from 'vue';
import { InputDatetime, type LeastUserDtoInterface } from 'seijishikin-jp-normalize_common-tool';
import { getLoginUser } from '../../utils/getLoginUser';
import router from '../../../../router';
import { PartnerAccessTokenStateDto, type PartnerAccessTokenStateDtoInterface } from '../../dto/user/partnerAccessTokenStateDto';
import mockGetPartnerApiForNewToken from '../../../test/common/user/mock/mockGetPartnerApiForNewToken';
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

//仮
// よく使う定数
const BLANK: string = "";
const INIT_NUMBER: number = 0;
// const SERVER_STATUS_OK: number = 200;
// const SERVER_STATUS_ERROR: number = 400;
// メッセージボックス表示定数
//const infoLevel: Ref<number> = ref(MessageConstants.LEVEL_NONE);
//const messageType: Ref<number> = ref(MessageConstants.VIEW_NONE);
//const title: Ref<string> = ref(BLANK);
//const message: Ref<string> = ref(BLANK);

// メッセージ表示定数
const infoLevel: Ref<number> = ref(MessageConstants.LEVEL_NONE);
const messageType: Ref<number> = ref(MessageConstants.VIEW_NONE);
const title: Ref<string> = ref(BLANK);
const message: Ref<string> = ref(BLANK);
const messageIndex: Ref<number> = ref(INIT_NUMBER);

// ユーザ呼び出し
const userDto: Ref<LeastUserDtoInterface> = ref(getLoginUser());

// 表示内容
const stateDto: Ref<PartnerAccessTokenStateDtoInterface> = ref(new PartnerAccessTokenStateDto());
const newToken: Ref<string> = ref("");

onBeforeMount(() => {
    stateDto.value = mockGetPartnerApiForNewToken();
});

// function recievDateTime(datetime: Date, index: number) {
//     switch (index) {
//         case 1:
//             stateDto.value.createdAt = datetime;
//             break;
//         case 2:
//             stateDto.value.expiresAt = datetime;
//             break;
//         case 3:
//             stateDto.value.lastUsedAt = datetime;
//             break;
//         case 4:
//             stateDto.value.revokedAt = datetime;
//             break;
//         default:
//             break;
//     }
// }

function recieveIpAddress(ipAddress: string) {
    stateDto.value.ipAddress = ipAddress;
}

function onCancel() {
    router.back();
}

async function onSave() {
    //newToken.value = mockGetNewToken();

    getAuthorizedPromiseArea().then(token => {
        // TODO 選択されたUserEntityを最小限ユーザに変換して削除対象、操作者はメニューから取得する
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
                    title.value = "長期トークン作成失敗";
                    message.value = "長期トークンが作成できませんでした。もう一度やり直してください" + resultDto.message;
                } else {
                    // 取得に成功している場合
                    infoLevel.value = MessageConstants.LEVEL_INFO;
                    messageType.value = MessageConstants.VIEW_TOAST;
                    title.value = "長期トークンの作成に成功しました";
                    message.value = resultDto.message;
                    newToken.value = resultDto.token;
                }
            })
            .catch((e) => {
                if (e instanceof AccessTokenNotFoundError) {
                    infoLevel.value = MessageConstants.LEVEL_ERROR;
                    // トークン保持ができていない場合
                    messageType.value = MessageConstants.VIEW_OK;
                    title.value = "現在トークンが存在しません";
                    messageIndex.value = 1;
                    message.value = e.message;
                }
                if (e instanceof TokenRefreshError) {
                    // 取得に失敗している場合
                    infoLevel.value = MessageConstants.LEVEL_ERROR;
                    messageType.value = MessageConstants.VIEW_OK;
                    title.value = "有効期限まじかのトークンを再取得できませんでした";
                    message.value = e.message;
                }
            });
    });

}

// async function getAccessToken() {
//     alert("アクセストークンを取得");

//     const url = urlBack + "/api-for-partner/convert";
//     const config = {
//         method: "POST",
//         headers: {
//             'Accept': 'application/json',
//             'Content-Type': 'application/json',
//             'X-AUTH-TOKEN': 'Bearer ' + newToken.value
//         },
//         //body: JSON.stringify(user.value)
//     };

//     const { loading: loginLoading, error: loginError, fetchData: fetchRefresh } = useApi<JwtTokenResultDtoInterface>();
//     const resultDto: JwtTokenResultDtoInterface | null = await fetchRefresh(url, config);

//     const userInfo = useUserInfoStore();
//     if (resultDto !== null) {
//         // jwtTokenを新たばTokenに入れ替え
//         userInfo.jwtDto = resultDto.jwtTokenDto;
//         alert("token取得" + resultDto.jwtTokenDto.accessToken);
//     }else{
//         alert("token初期化");
//         userInfo.jwtDto = new JwtTokenDto();
//     }
// }

// function doAnything() {
//     alert("アクセストークンで何か実行");

//     getAuthorizedPromiseArea().then(token => {
//         alert("token取得" + token);
//         // TODO 選択されたUserEntityを最小限ユーザに変換して削除対象、操作者はメニューから取得する
//         const capsuleDto: GetUserDtoCapsuleDtoInterface = new GetUserDtoCapsuleDto();
//         capsuleDto.editUserid = 1;

//         const url = urlBack + "/edit-user/get";
//         const method = "POST";
//         const body = JSON.stringify(capsuleDto);
//         const headers = {
//             'Accept': 'application/json',
//             'Content-Type': 'application/json',
//             'X-AUTH-TOKEN': 'Bearer ' + token
//         };
//         fetch(url, { method, headers, body })
//             .then(async (response) => {
//                 alert(response.status);
//                 const resultDto: GetUserDtoResultDtoInterface = await response.json();
//                 if (resultDto.isFailure) {
//                     // 取得に失敗している場合
//                     infoLevel.value = MessageConstants.LEVEL_ERROR;
//                     messageType.value = MessageConstants.VIEW_OK;
//                     title.value = "ユーザ作成失敗";
//                     message.value = "ユーザが取得できませんでした。" + resultDto.message;
//                 } else {
//                     // 取得に成功している場合
//                     // infoLevel.value = MessageConstants.LEVEL_INFO;
//                     // messageType.value = MessageConstants.VIEW_TOAST;
//                     // title.value = "長期トークン作成成功";
//                     // message.value = resultDto.message;
//                     alert("権限リスト" + resultDto.userDto.listRoles);
//                 }
//             })
//             .catch((e) => {
//                 if (e instanceof AccessTokenNotFoundError) {
//                     infoLevel.value = MessageConstants.LEVEL_ERROR;
//                     // トークン保持ができていない場合
//                     messageType.value = MessageConstants.VIEW_OK;
//                     title.value = "現在トークンが存在しません";
//                     messageIndex.value = 1;
//                     message.value = e.message;
//                 }
//                 if (e instanceof TokenRefreshError) {
//                     // 取得に失敗している場合
//                     infoLevel.value = MessageConstants.LEVEL_ERROR;
//                     messageType.value = MessageConstants.VIEW_OK;
//                     title.value = "有効期限まじかの現在トークンを再取得できませんでした";
//                     message.value = e.message;
//                 }
//             });
//     });

// }

function recieveSubmit(button: string) {
    // 非表示
    infoLevel.value = 0;
    messageType.value = 0;

    // 現在トークンを所持していない場合はログアウト
    if ("ok" === button && 1 === messageIndex.value) {
        logout();
        router.push(RoutePathConstants.PAGE_LOGOUT);
    }
}
</script>
<template>

    <PartnerApiInfo :user-dto="userDto"></PartnerApiInfo>

    <h1>APIパートナー長期トークン(再)発行</h1>

    <div class="one-line">
        {{ stateDto.userName }} のトークンを再発行
    </div>

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
            <InputDatetime :datetime="stateDto.createdAt" :index="2" :is-edit="false"></InputDatetime>
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
            <InputDatetime :datetime="stateDto.lastUsedAt" :index="4" :is-edit="false"></InputDatetime>
        </div>
    </div>

    <div class="one-line">
        <div class="left-area">
            長期トークン
        </div>
        <div class="right-area">
            <div class="form-group-vertical">
                <span>全く同じトークンは再発行できません。安全な場所に保管してください</span>
                <textarea v-model="newToken" :disabled="true" class="max-input" placeholder="このトークンは再発行できません。"></textarea>
            </div>
        </div>
    </div>
    <!-- 
    <div class="one-line">
        <div class="left-area">
            トークン取得
        </div>
        <div class="right-area">
            <button @click="getAccessToken">トークン取得</button>
        </div>
    </div>

    <div class="one-line">
        <div class="left-area">
            長期トークン→アクセストークン→機能
        </div>
        <div class="right-area">
            <button @click="doAnything">何か実行</button>
        </div>
    </div>
    -->

    <div class="footer">
        <button @click="onCancel" class="footer-button">キャンセル</button>
        <button @click="onSave" class="footer-button left-space">長期トークン発行</button>
    </div>

    <!-- メッセージ表示 -->
    <div class="overMessage" v-if="messageType !== MessageConstants.VIEW_NONE">
        <MessageView :info-level="infoLevel" :message-type="messageType" :title="title" :message="message"
            @send-submit="recieveSubmit">
        </MessageView>
    </div>

</template>
<style scoped></style>
