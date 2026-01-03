<script setup lang="ts">
import { ref, type Ref, onMounted } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import { NewComerDto, type NewComerDtoInterface } from '../../dto/add_account/newComerDto';
import RoutePathConstants from '../../../../routePathConstants';
import MockNewComerInfo from '../../../test/common/user_info/MockNewComerInfo.vue';
import { MessageConstants, MessageView } from 'seijishikin-jp-normalize_common-tool';
import { useApi } from '../../utils/useApi';

const route = useRoute();
const router = useRouter();

const BLANK: string = "";

const sessionStorage = window["sessionStorage"];
const newComer: Ref<NewComerDtoInterface> = ref(new NewComerDto());

const pageMode: Ref<string> = ref('manual'); // 'manual': 手入力モード, 'verifying': トークン検証中, 'error': エラー発生
const errorMessage: Ref<string> = ref('');

const urlBack: string = RoutePathConstants.DOMAIN + RoutePathConstants.BASE_PATH;

const infoLevel: Ref<number> = ref(MessageConstants.LEVEL_NONE);
const messageType: Ref<number> = ref(MessageConstants.VIEW_NONE);
const title: Ref<string> = ref(BLANK);
const message: Ref<string> = ref(BLANK);

// API呼び出し用Composable
const {  loading: verifyLoading, error: verifyError, fetchData: fetchVerify } = useApi<NewComerDtoInterface>();
const {  loading: publishLoading, error: publishError, fetchData: fetchPublish } = useApi<NewComerDtoInterface>();

onMounted(async () => {
    const token = route.query.token;
    if (token && typeof token === 'string') {
        // 【トークン検証モード】
        pageMode.value = 'verifying';
        newComer.value.verifyToken = token;
        await onVerifyCodeFromUrl();
    } else {
        // 【コード手入力モード】
        pageMode.value = 'manual';
        const dtoJson: string | null = sessionStorage.getItem("new-comer");
        if (null !== dtoJson) {
            try {
                newComer.value = JSON.parse(dtoJson);
                newComer.value.registCode = "";
            } catch (e) {
                pageMode.value = 'error';
                errorMessage.value = "セッション情報が破損しています。お手数ですが、最初のメールアドレス入力からやり直してください。";
            }
        } else {
            pageMode.value = 'error';
            errorMessage.value = "セッション情報が見つかりません。お手数ですが、最初のメールアドレス入力からやり直してください。";
        }
    }
});

/**
 * URLからのトークンを検証する
 */
async function onVerifyCodeFromUrl() {
    const url = urlBack + "/add-user/verify";
    const config = {
        method: "POST",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ verifyToken: newComer.value.verifyToken })
    };

    const resultDto = await fetchVerify(url, config);

    if (resultDto) {
        if (resultDto.isFailure) {
            pageMode.value = 'error';
            errorMessage.value = resultDto.message || "認証エラーが発生しました。";
        } else {
            sessionStorage.setItem("new-comer", JSON.stringify(resultDto));
            router.push(RoutePathConstants.PAGE_SWITCH_USER_KBN);
        }
    } else if (verifyError.value) {
        pageMode.value = 'error';
        errorMessage.value = verifyError.value;
    }
}

/**
 * ユーザーが入力したコードを検証する
 */
async function onCheckSendCode() {
    if (newComer.value.registCode === BLANK) {
        infoLevel.value = MessageConstants.LEVEL_ERROR;
        messageType.value = MessageConstants.VIEW_OK;
        title.value = "入力エラー";
        message.value = "認証コードは必須です。";
        return;
    }

    const url = urlBack + "/add-user/check-code";
    const config = {
        method: "POST",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(newComer.value)
    };

    const resultDto = await fetchVerify(url, config);

    if (resultDto) {
        if (resultDto.isFailure) {
            infoLevel.value = MessageConstants.LEVEL_ERROR;
            messageType.value = MessageConstants.VIEW_OK;
            title.value = "認証エラー";
            message.value = resultDto.message || "認証コードの検証に失敗しました。";
        }
        else {
            sessionStorage.setItem("new-comer", JSON.stringify(resultDto));
            router.push(RoutePathConstants.PAGE_SWITCH_USER_KBN);
        }
    } else if (verifyError.value) {
        infoLevel.value = MessageConstants.LEVEL_ERROR;
        messageType.value = MessageConstants.VIEW_OK;
        title.value = "システムエラー";
        message.value = verifyError.value;
    }
}

function onCancel() {
    history.back();
}

async function onResendCode() {
    if (newComer.value.mailAddress === BLANK) {
        infoLevel.value = MessageConstants.LEVEL_ERROR;
        messageType.value = MessageConstants.VIEW_OK;
        title.value = "入力エラー";
        message.value = "メールアドレスは必須です。";
        return;
    }

    const url = urlBack + "/add-user/publish-code";
    const config = {
        method: "POST",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(newComer.value)
    };

    const resultDto = await fetchPublish(url, config);

    if (resultDto) {
        sessionStorage.setItem("new-comer", JSON.stringify(resultDto));
        infoLevel.value = MessageConstants.LEVEL_INFO;
        messageType.value = MessageConstants.VIEW_TOAST;
        title.value = "コード発行完了";
        message.value = "認証コードを発行しました。指定のアドレスにメールを送信しましたのでご確認ください";
    } else if (publishError.value) {
        infoLevel.value = MessageConstants.LEVEL_ERROR;
        messageType.value = MessageConstants.VIEW_OK;
        title.value = "コード発行エラー";
        message.value = publishError.value;
    }
}

function recieveSubmit(button: string) {
    console.log(button);
    infoLevel.value = 0;
    messageType.value = 0;
}
</script>
<template>

    <MockNewComerInfo :current-step="2"></MockNewComerInfo>

    <!-- 1. トークン検証中の表示 -->
    <div v-if="pageMode === 'verifying'">
        <h1>本人確認</h1>
        <p>メールアドレスの所有権を確認しています...</p>
    </div>

    <!-- エラー表示 -->
    <div v-else-if="pageMode === 'error'">
        <h1>認証エラー</h1>
        <p class="error-message">{{ errorMessage }}</p>
        <RouterLink :to="RoutePathConstants.PAGE_ADD_ACCOUNT">新規登録をやり直す</RouterLink>
    </div>

    <!-- 3. コード手入力フォーム (従来通り) -->
    <div v-else-if="pageMode === 'manual'">
        <h1>新規登録(アカウント疎通確認)</h1>

        <div class="one-line">
            メールアドレスに登録コードを送信しました。<br>
            メールアドレスで送付されたコードを入力して登録してください<br>
        </div>

        <div class="one-line">
            <div class="left-area">
                送信されたコード
            </div>
            <div class="right-area">
                <input type="email" v-model="newComer.registCode" class="name-input">
            </div>
        </div>

        <div class="one-line">
            <div class="left-area">
                メールアドレス(アカウント)
            </div>
            <div class="right-area">
                <input type="email" v-model="newComer.mailAddress" class="name-input"
                    :disabled="'' === newComer.mailAddress">
            </div>
        </div>

        <div class="one-line">
            <div class="left-area">
                認証コード再送
            </div>
            <div class="right-area">
                <div class="form-group-vertical">
                    <div>
                        コード認証期限(1日)が過ぎてしまった、メールが届いていないなどの場合のコード再送信をします
                    </div>
                    <div>
                        <button @click="onResendCode" :disabled="publishLoading">認証コード再送信</button>
                    </div>
                </div>
            </div>
        </div>

        <div class="footer">
            <button class="footer-button" @click="onCancel">前に戻る</button>
            <button class="footer-button left-space" @click="onCheckSendCode" :disabled="verifyLoading">送信</button>
        </div>
    </div>

    <!-- メッセージ表示 -->
    <div class="overMessage" v-if="messageType !== MessageConstants.VIEW_NONE">
        <MessageView :info-level="infoLevel" :message-type="messageType" :title="title" :message="message"
            @send-submit="recieveSubmit">
        </MessageView>
    </div>
</template>
<style scoped>
.error-message {
    color: red;
    font-weight: bold;
}
</style>