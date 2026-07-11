<script setup lang="ts">
import { ref, type Ref } from 'vue';
import router from '../../../../router';
import RoutePathConstants from '../../../../routePathConstants';
import { MessageConstants, MessageView, type FrameworkMessageAndResultDtoInterface } from 'seijishikin-jp-normalize_common-tool';
import { ResetPassswordCapsuleDto, type ResetPassswordCapsuleDtoInterface } from '../../dto/user/resetPassswordCapsuleDto';
import PasswordInput from '../../common/user/PasswordInput.vue';
import { useApi } from '../../utils/useApi';

// back側アクセス
const urlBack: string = RoutePathConstants.DOMAIN + RoutePathConstants.BASE_PATH;

// よく使う定数
const BLANK: string = "";
// const INIT_NUMBER: number = 0;
// const SERVER_STATUS_OK: number = 200;
// const SERVER_STATUS_ERROR: number = 400;
const MESS_PAGE_NAME: string = "パスワードリセット";
const INIT_CALLER: string = "no branch";

// ユーザログインできていない状態なのでユーザ呼び出しなし
// const userDto: Ref<LeastUserDtoInterface> = ref(getLoginUser());

// メッセージ表示定数
const infoLevel: Ref<number> = ref(MessageConstants.LEVEL_NONE);
const messageType: Ref<number> = ref(MessageConstants.VIEW_NONE);
const caller: Ref<string> = ref(INIT_CALLER);
const message: Ref<string> = ref(BLANK);

// その他入力補助 
const reInputPassword: Ref<string> = ref("");
const inputStage: Ref<number> = ref(1);

const stageClass1: Ref<string> = ref("");
const stageClass2: Ref<string> = ref("");
const stageClass3: Ref<string> = ref("");

// 入力内容Dto
const capsuleDto: Ref<ResetPassswordCapsuleDtoInterface> = ref(new ResetPassswordCapsuleDto());


async function onSendEmail() {

    const { loading: sendLoading, error: sendError, fetchData: fetchLogin } = useApi<FrameworkMessageAndResultDtoInterface>();

    const url = urlBack + "/reset-password/send-code";
    const config = {
        method: "POST",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(capsuleDto.value)
    };

    const resultDto: FrameworkMessageAndResultDtoInterface | null = await fetchLogin(url, config);
    messageType.value = MessageConstants.VIEW_OK;
    if (resultDto !== null) {
        if (resultDto.isFailure) {
            infoLevel.value = MessageConstants.LEVEL_WARNING;
        } else {
            infoLevel.value = MessageConstants.LEVEL_INFO;
            // ページ編集
            inputStage.value = 2;
            stageClass1.value = "stage-complete";
        }
        message.value = resultDto.message;
    } else {
        infoLevel.value = MessageConstants.LEVEL_ERROR;
        if (sendError.value != null) {
            message.value = "パスワードリセット(コード送信)" + sendError.value;
        } else {
            message.value = "パスワードリセット(コード送信)" + "システムエラーが発生しました";
        }
    }
}

async function onSendCode() {

    const { loading: sendLoading, error: sendError, fetchData: fetchLogin } = useApi<FrameworkMessageAndResultDtoInterface>();

    const url = urlBack + "/reset-password/check-code";
    const config = {
        method: "POST",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(capsuleDto.value)
    };

    const resultDto: FrameworkMessageAndResultDtoInterface | null = await fetchLogin(url, config);
    messageType.value = MessageConstants.VIEW_OK;
    if (resultDto !== null) {
        if (resultDto.isFailure) {
            infoLevel.value = MessageConstants.LEVEL_WARNING;
        } else {
            infoLevel.value = MessageConstants.LEVEL_INFO;
            // ページ編集
            inputStage.value = 3;
            stageClass2.value = "stage-complete";
        }
        message.value = "パスワードリセット(コード照合)：" + resultDto.message;
    } else {
        infoLevel.value = MessageConstants.LEVEL_ERROR;
        if (sendError.value != null) {
            message.value = "パスワードリセット(コード照合)：" + sendError.value;
        } else {
            message.value = "パスワードリセット(コード照合)：" + "システムエラーが発生しました";
        }
    }
}

const completKey: string = "stage-complete";
async function onSavePassword() {
    if (reInputPassword.value !== capsuleDto.value.password) {
        // パスワードの再入力が異なる場合はメッセージを出して離脱
        infoLevel.value = MessageConstants.LEVEL_ERROR;
        message.value = "新しいパスワードが再入力と異なります。入力をやり直してください";
        // 表示
        messageType.value = MessageConstants.VIEW_OK;
        return;
    }


    const { loading: sendLoading, error: sendError, fetchData: fetchLogin } = useApi<FrameworkMessageAndResultDtoInterface>();

    const url = urlBack + "/reset-password/save";
    const config = {
        method: "POST",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(capsuleDto.value)
    };

    const resultDto: FrameworkMessageAndResultDtoInterface | null = await fetchLogin(url, config);
    messageType.value = MessageConstants.VIEW_OK;
    if (resultDto !== null) {
        if (resultDto.isFailure) {
            infoLevel.value = MessageConstants.LEVEL_WARNING;
        } else {
            infoLevel.value = MessageConstants.LEVEL_INFO;
            // ページ編集
            stageClass3.value = completKey;
            caller.value = completKey;
        }
        message.value = "パスワードリセット(パスワード設定)" + resultDto.message;
    } else {
        infoLevel.value = MessageConstants.LEVEL_ERROR;
        if (sendError.value != null) {
            message.value = "パスワードリセット(パスワード設定)" + sendError.value;
        } else {
            message.value = "パスワードリセット(パスワード設定)" + "システムエラーが発生しました";
        }
    }

}

function onCancel() {
    router.back();
}

function recieveSubmit(button: string, callerMethod: string) {

    if (callerMethod == completKey && button === MessageConstants.BUTTON_YES) {
        // パスワード設定成功時にはログインページに遷移
        router.push(RoutePathConstants.PAGE_LOGIN);
    }

    infoLevel.value = 0;
    messageType.value = 0;
    caller.value = INIT_CALLER;
}

function recievePassword1(password: string) {
    capsuleDto.value.password = password;
}
function recievePassword2(password: string) {
    reInputPassword.value = password;
}
</script>
<template>

    <h1>パスワードリセット</h1>
    <div>
        【注意】作業が終わるまでのこのページを閉じたり、別のページへ移動したりしないでください
    </div>


    <div :class="stageClass1">
        <h3> (1)連絡用メールアドレスに認証コードを送信します</h3>
        <div class="one-line">
            <div class="left-area">
                メールアドレス(アカウント)
            </div>
            <div class="right-area">
                <input type="email" v-model="capsuleDto.email" class="name-input" :disabled="1 !== inputStage">
            </div>
        </div>
        <div class="one-line">
            <div class="left-area">
                送信
            </div>
            <div class="right-area">
                <button @click="onSendEmail" :disabled="1 !== inputStage">メールアドレス送信</button>
            </div>
        </div>

    </div>


    <div v-if="1 < inputStage" :class="stageClass2">
        <h3> (2)認証コードで本人確認をします</h3>
        <div class="one-line">
            <div class="left-area">
                送信された認証コード
            </div>
            <div class="right-area">
                <input type="email" v-model="capsuleDto.accessCode" class="name-input" :disabled="2 !== inputStage">
            </div>
        </div>
        <div class="one-line">
            <div class="left-area">
                送信
            </div>
            <div class="right-area">
                <button @click="onSendCode" :disabled="2 !== inputStage">認証コードの送信</button>
            </div>
        </div>
    </div>

    <!-- 新しいパスワード入力 -->

    <div v-if="2 < inputStage" :class="stageClass3">
        <h3> (3)新しいパスワードの入力をします</h3>
        <div class="one-line">
            <div class="left-area">
                新しいパスワード
            </div>
            <div class="right-area">
                <PasswordInput :password="capsuleDto.password" @send-password="recievePassword1"></PasswordInput>
            </div>
        </div>

        <div class="one-line">
            <div class="left-area">
                パスワードの再入力
            </div>
            <div class="right-area">
                <PasswordInput :password="reInputPassword" @send-password="recievePassword2"></PasswordInput>
            </div>
        </div>

        <div class="one-line">
            <div class="left-area">
                パスワード変更
            </div>
            <div class="right-area">
                <button @click="onSavePassword" :disabled="3 !== inputStage">パスワード送信</button>
            </div>
        </div>
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
<style scoped>
div.stage-complete {
    background-color: #e5e5e5;
}
</style>
