<script setup lang="ts">
import { onMounted, ref, type Ref } from 'vue';
import ManagerInfo from '../../common/user_info/ManagerInfo.vue';
import { InputLgcode, MessageConstants, MessageView, type FrameworkMessageAndResultDtoInterface, type LeastUserDtoInterface } from 'seijishikin-jp-normalize_common-tool';
import { getLoginUser } from '../../utils/getLoginUser.ts';
import { MovePostalCodeCapsuleDto, type MovePostalCodeCapsuleDtoInterface } from '../../dto/address_postal/movePostalCodeCapsuleDto.ts';
import { AccessTokenNotFoundError, TokenRefreshError } from '../../dto/login/errors.ts';
import getAuthorizedPromiseArea from '../../dto/login/getAuthorizedPromiseArea.ts';
import RoutePathConstants from '../../../../routePathConstants.ts';
import { useRoute } from 'vue-router';


// よく使う定数
const BLANK: string = "";
// const INIT_NUMBER: number = 0;
// const SERVER_STATUS_OK: number = 200;
// const SERVER_STATUS_ERROR: number = 400;
// const SEARCH_LIMIT: number = 20;
// メッセージボックス表示定数
const infoLevel: Ref<number> = ref(MessageConstants.LEVEL_NONE);
const messageType: Ref<number> = ref(MessageConstants.VIEW_NONE);
const title: Ref<string> = ref(BLANK);
const message: Ref<string> = ref(BLANK);

// back側アクセス
const urlBack: string = RoutePathConstants.DOMAIN + RoutePathConstants.BASE_PATH;

const route = useRoute();

// ユーザ呼び出し
const userDto: Ref<LeastUserDtoInterface> = ref(getLoginUser());

// 編集Dto
const capsuleDto: Ref<MovePostalCodeCapsuleDtoInterface> = ref(new MovePostalCodeCapsuleDto());

const lg: Ref<string> = ref(BLANK);
onMounted(() => {
    // queryparamがあればセット
    const lgCode = route.query.lgcode;
    alert(lgCode?.toString());
    if (undefined !== lgCode?.toString()) {
        capsuleDto.value.lgCode = lgCode?.toString();
        lg.value = capsuleDto.value.lgCode;
    }
    const postalPre = route.query.postalpre;
    if (undefined !== postalPre?.toString()) {
        const postal: string = postalPre?.toString();
        capsuleDto.value.postalOld1 = postal.substring(0, 3);
        capsuleDto.value.postalOld2 = postal.substring(3, 7);
    }

    const postalPro = route.query.postalpro;
    if (undefined !== postalPro?.toString()) {
        const postal: string = postalPro?.toString();
        capsuleDto.value.postalNew1 = postal.substring(0, 3);
        capsuleDto.value.postalNew2 = postal.substring(3, 7);
    }
});





function onCancel() {
    history.back();

}

function onSave() {
    title.value = "郵便番号移動";

    // 編集された郵便番号差分を保存
    getAuthorizedPromiseArea().then(token => {
        const url = urlBack + "/postal-code/move";
        const method = "POST";
        const body = JSON.stringify(capsuleDto.value);
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

    // 非表示
    infoLevel.value = 0;
    messageType.value = 0;
}

// 地方自治体コードを受信
function recieveLgCode(data: string) {
    capsuleDto.value.lgCode = data;
}
</script>
<template>
    <!-- 管理者メニュー兼チェック -->
    <ManagerInfo :user-dto="userDto"></ManagerInfo>

    <h1>郵便番号移動</h1>

    <h3>移動条件</h3>
    <div class="one-line">
        <div class="left-area">
            地方自治体コード
        </div>
        <div class="right-area">
            <InputLgcode :is-digit5="false" :lg-code="lg" @send-lg-code="recieveLgCode"></InputLgcode>
        </div>
    </div>

    <div class="one-line">
        <div class="left-area">
            旧郵便番号
        </div>
        <div class="right-area">
            <input type="text" v-model="capsuleDto.postalOld1" class="code-input">
            <input type="text" v-model="capsuleDto.postalOld2" class="code-input left-space">
        </div>
    </div>

    <div class="one-line">
        <div class="left-area">
            新郵便番号
        </div>
        <div class="right-area">
            <input type="text" v-model="capsuleDto.postalNew1" class="code-input">
            <input type="text" v-model="capsuleDto.postalNew2" class="code-input left-space">
        </div>
    </div>

    <div class="footer">
        <button @click="onCancel" class="footer-button">キャンセル</button>
        <button @click="onSave" class="footer-button left-space">送信</button>
    </div>

    <!-- メッセージ表示    -->
    <div class="overMessage" v-if="messageType !== MessageConstants.VIEW_NONE">
        <MessageView :info-level="infoLevel" :message-type="messageType" :title="title" :message="message"
            @send-submit="recieveSubmit">
        </MessageView>
    </div>

</template>
<style scoped></style>
