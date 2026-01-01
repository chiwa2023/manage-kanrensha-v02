<script setup lang="ts">
import { ref, type Ref, onMounted } from 'vue';
import { useRoute, useRouter } from 'vue-router'; // useRoute と useRouter をインポート
import { NewComerDto, type NewComerDtoInterface } from '../../dto/add_account/newComerDto';
import RoutePathConstants from '../../../../routePathConstants';
import MockNewComerInfo from '../../../test/common/user_info/MockNewComerInfo.vue';
import { MessageConstants, MessageView } from 'seijishikin-jp-normalize_common-tool';

const route = useRoute(); // 現在のルート情報にアクセス
const router = useRouter(); // routerのインスタンスを取得

// よく使う定数
const BLANK: string = "";
// const INIT_NUMBER: number = 0;
// const SERVER_STATUS_OK: number = 200;
// const SERVER_STATUS_ERROR: number = 400;

const sessionStorage = window["sessionStorage"];
const newComer: Ref<NewComerDtoInterface> = ref(new NewComerDto());
const regiCode: Ref<string> = ref(""); // モック用のコード表示

// UIの状態を管理するための変数
// 'manual': 手入力モード, 'verifying': トークン検証中, 'error': エラー発生
const pageMode: Ref<string> = ref('manual');
const errorMessage: Ref<string> = ref('');

// back側アクセス
const urlBack: string = RoutePathConstants.DOMAIN + RoutePathConstants.BASE_PATH;

// メッセージ表示定数
const infoLevel: Ref<number> = ref(MessageConstants.LEVEL_NONE);
const messageType: Ref<number> = ref(MessageConstants.VIEW_NONE);
const title: Ref<string> = ref(BLANK);
const message: Ref<string> = ref(BLANK);

// --- ライフサイクルフック ---
onMounted(() => {
    const token = route.query.token;
    if (token && typeof token === 'string') {
        // 【トークン検証モード】
        pageMode.value = 'verifying';
        verifyToken(token);
    } else {
        // 【コード手入力モード】
        pageMode.value = 'manual';
        const dtoJson: string | null = sessionStorage.getItem("new-comer");
        if (null !== dtoJson) {
            newComer.value = JSON.parse(dtoJson);
            regiCode.value = newComer.value.registCode; // モック用
            newComer.value.registCode = "";
        } else {
            // sessionStorageに情報がない場合、不正なアクセスかタイムアウトの可能性がある
            pageMode.value = 'error';
            errorMessage.value = "セッション情報が見つかりません。お手数ですが、最初のメールアドレス入力からやり直してください。";
        }
    }
});


// --- メソッド ---

/**
 * トークンをバックエンドに送信して検証する (トークン検証モード)
 */
function verifyToken(token: string) {
    // TODO: バックエンドにトークン検証APIを実装し、呼び出す
    console.log("バックエンドにトークンを送信して検証:", token);
    // const url = urlBack + "/add-user/verify-token";
    // const method = "POST";
    // const body = JSON.stringify({ token: token });
    // const headers = { /* ... */ };

    // fetch(url, { method, headers, body }).then(async (response) => {
    //     if (response.status === 200) {
    //         const resultDto = await response.json(); // メールアドレス等を含むDTOを返してもらう
    //         sessionStorage.setItem("new-comer", JSON.stringify(resultDto)); // 次のページ用に情報を保存
    //         router.push(RoutePathConstants.PAGE_SWITCH_USER_KBN);
    //     } else {
    //         // トークンが無効・期限切れの場合
    //         pageMode.value = 'error';
    //         errorMessage.value = "この認証リンクは無効か、有効期限が切れています。お手数ですが、再度メールアドレスの入力からお試しください。";
    //     }
    // }).catch(error => {
    //     pageMode.value = 'error';
    //     errorMessage.value = "認証中にエラーが発生しました。";
    // });

    // --- Mock実装 ---
    setTimeout(() => {
        if (token === "valid-token") {
            // 検証成功。次のページに必要な情報をsessionStorageに保存
            const mockDto = new NewComerDto();
            mockDto.mailAddress = "user@example.com"; // 本来はバックエンドから受け取る
            sessionStorage.setItem("new-comer", JSON.stringify(mockDto));
            router.push(RoutePathConstants.PAGE_SWITCH_USER_KBN);
        } else {
            pageMode.value = 'error';
            errorMessage.value = "この認証リンクは無効か、有効期限が切れています。";
        }
    }, 1500);
}


/**
 * ユーザーが入力したコードを検証する (コード手入力モード)
 */
function onCheckSendCode() {
    router.push(RoutePathConstants.PAGE_SWITCH_USER_KBN);
    // TODO: 既存のコードチェックロジックをここに実装
}

function onCancel() {
    history.back();
}

function onResendCode() {
    alert("コード再送リクエストを送信しました。"+ newComer.value.mailAddress);
    // TODO: 既存の再送ロジックをここに実装
}


function recieveSubmit(button: string) {
    console.log(button); // 警告除け
    // TODO ボタンタイプ別の挙動はこの中で変える

    // 非表示
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
        <!-- ここにスピナーなどを表示するとより親切です -->
    </div>

    <!-- 2. エラー表示 -->
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
            メールアドレス：{{ newComer.mailAddress }}<br>
            登録コード：{{ regiCode }} <!-- モック用 -->
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
                <input type="email" v-model="newComer.mailAddress" class="name-input" disabled="true">
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
                        <button @click="onResendCode">認証コード再送信</button>
                    </div>
                </div>
            </div>
        </div>

        <div class="footer">
            <button class="footer-button" @click="onCancel">前に戻る</button>
            <button class="footer-button left-space" @click="onCheckSendCode">送信</button>
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