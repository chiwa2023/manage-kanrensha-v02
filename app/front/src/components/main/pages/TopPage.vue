<script setup lang="ts">
import { MessageConstants, MessageView, useUserInfoStoreCommon } from 'seijishikin-jp-normalize_common-tool';
import RoutePathConstants from '../../../routePathConstants';
import { onBeforeMount, ref, type Ref } from 'vue';
import { LoginUserCapsuleDto, type LoginUserCapsuleDtoInterface } from '../dto/login/loginUserCapsuleDto';
import type { LoginUserResultDtoInterface } from '../dto/login/loginUserResultDto';
import router from '../../../router';
import { useUserInfoStore } from '../stores/storeUserInfo';
import { rememberMeStore } from '../../main/stores/remeberMe';
import UserRoleConstants from '../dto/user/userRoleConstants';
import { nextTransferPassStore } from '../stores/nextTransferPass';
import { getActivePinia } from 'pinia';

// back側アクセス
const urlBack: string = RoutePathConstants.DOMAIN + RoutePathConstants.BASE_PATH;

// よく使う定数
const BLANK: string = "";
// const INIT_NUMBER: number = 0;
const SERVER_STATUS_OK: number = 200;
// const SERVER_STATUS_ERROR: number = 400;
const SERVER_STATUS_UNAUTHORIZED: number = 401;
const MESS_PAGE_NAME: string = "ユーザログイン";
const INIT_CALLER: string = "no branch";

// メッセージ表示定数
const infoLevel: Ref<number> = ref(MessageConstants.LEVEL_NONE);
const messageType: Ref<number> = ref(MessageConstants.VIEW_NONE);
const caller: Ref<string> = ref(INIT_CALLER);
const message: Ref<string> = ref(BLANK);
// pinia
const userInfo = useUserInfoStore();
const rememberMe = rememberMeStore();
let nextPath: string | null = BLANK;
onBeforeMount(() => {
    // PiniaローカルストレージのrememberMeに値が残っていれば復元
    if (rememberMe.hasData()) {
        isPasswordVisible.value = false;
        user.value.userId = rememberMe.getMail();
        user.value.password = rememberMe.getPassowrd();
        user.value.rememberMe = true;
    }
    // 次の遷移先があれば取得して、保存先は空にする
    const passStore = nextTransferPassStore()
    nextPath = passStore.fullPath;
    passStore.fullPath = BLANK;
});

function recieveSubmit() {
    // 非表示
    infoLevel.value = 0;
    messageType.value = 0;
}

const user: Ref<LoginUserCapsuleDtoInterface> = ref(new LoginUserCapsuleDto());


async function onLogin() {

    // ユーザIdとパスワードが未入力の場合は
    if (user.value.userId === BLANK || user.value.password === BLANK) {
        infoLevel.value = MessageConstants.LEVEL_ERROR;
        messageType.value = MessageConstants.VIEW_OK;
        message.value = "入力エラー：メールアドレスとパスワードは必須です。";
        return;
    }

    const url = urlBack + "/login";
    const config = {
        method: "POST",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(user.value)
    };

    const response = await fetch(url, config);
    if (SERVER_STATUS_OK === response.status) {
        try {
            const resultDto: LoginUserResultDtoInterface = await response.json();

            // 取得できたら保存
            userInfo.jwtDto = resultDto.jwtTokenDto;
            userInfo.userDto = resultDto.userDto;

            // common-toolにアクセス情報を渡す
            const userInfoCommon = useUserInfoStoreCommon(getActivePinia());
            userInfoCommon.jwtDto = userInfo.jwtDto;
            userInfoCommon.userDto = userInfo.userDto;

            // ログインに成功かつrememberMeを使用したいときだけPiniaに保存
            if (user.value.rememberMe) {
                rememberMe.setMail(user.value.userId);
                rememberMe.setPassword(user.value.password);
            } else {
                // チェックが外されたら初期化
                rememberMe.initialize();
            }

            // 次の行き先が保存してある場合はその遷移先に移動
            if (null !== nextPath && BLANK !== nextPath) {
                router.push(nextPath);
                return;
            }

            switch (resultDto.userDto.listRoles[0]) {
                case UserRoleConstants.ROLE_ADMIN:
                    // 運営者
                    router.push(RoutePathConstants.PAGE_MENU_ADMIN);
                    break;
                case UserRoleConstants.ROLE_MANAGER:
                    // 運営者
                    router.push(RoutePathConstants.PAGE_MENU_MANAGER);
                    break;
                case UserRoleConstants.ROLE_PARTNER_API:
                    // APIパートナー
                    router.push(RoutePathConstants.PAGE_MENU_PARTNER_API);
                    break;
                case UserRoleConstants.ROLE_KANRENSHA_PERSON:
                case UserRoleConstants.ROLE_KANRENSHA_KIGYOU_DT:
                case UserRoleConstants.ROLE_KANRENSHA_SEIJIDANTAI:
                    // 関連者
                    router.push(RoutePathConstants.PAGE_MENU_KANRENSHA);
                    break;
                default:
                    infoLevel.value = MessageConstants.LEVEL_ERROR;
                    messageType.value = MessageConstants.VIEW_OK;
                    message.value = "権限取得でエラーが発生しています";
                    break;
            }
        } catch {
            // レスポンスは正常だがSJONが合わない等→実装ミス
            infoLevel.value = MessageConstants.LEVEL_ERROR;
            messageType.value = MessageConstants.VIEW_OK;
            message.value = "システムエラーが発生しました";
        }

        return;
    }
    if (SERVER_STATUS_UNAUTHORIZED === response.status) {
        infoLevel.value = MessageConstants.LEVEL_ERROR;
        messageType.value = MessageConstants.VIEW_OK;
        message.value = "ログイン失敗しました。パスワードまたはメールアドレスに誤りがあります。再入力してください";
        return;
    }

    infoLevel.value = MessageConstants.LEVEL_ERROR;
    messageType.value = MessageConstants.VIEW_OK;
    message.value = "システムエラーが発生しました";
}

// パスワード可視／不可視切り替えロジック
const isPasswordVisible: Ref<boolean> = ref(false);
const passwordInputType: Ref<string> = ref("password");
function changeVisiblePassword() {
    isPasswordVisible.value = !isPasswordVisible.value;
    if (isPasswordVisible.value) {
        passwordInputType.value = "text";
    } else {
        passwordInputType.value = "password";
    }
}
</script>
<template>
    <div class="container">
        <div class="login-page">
            <div class="form-container">
                <h1>政治資金関連者標準化サイト</h1>
                <h2>ユーザログイン</h2>

                <div class="form-group">
                    <label for="email">メールアドレス</label>
                    <input type="text" id="email" v-model="user.userId">
                </div>

                <div class="form-group">
                    <label for="password">パスワード</label>
                    <div class="password-wrapper">
                        <input :type="passwordInputType" id="password" v-model="user.password" autocomplete="off">
                        <span @click="changeVisiblePassword" class="password-toggle-icon">
                            <img v-show="!isPasswordVisible" src="../../../assets/password_hidden.png">
                            <img v-show="isPasswordVisible" src="../../../assets/password_visible.png">
                        </span>
                    </div>
                </div>

                <div class="form-options">
                    <input type="checkbox" v-model="user.rememberMe" id="rememberMe">
                    <label for="rememberMe">ログイン情報を記憶する</label>
                </div>

                <button @click="onLogin" class="login-button">ログイン</button>

                <div class="links">
                    <RouterLink :to="RoutePathConstants.PAGE_ADD_ACCOUNT">新規登録ですか?</RouterLink>
                    <RouterLink :to="RoutePathConstants.PAGE_RESET_PASSWORD">※パスワードを忘れたので再発行</RouterLink>
                </div>

                <div class="links">
                    <div style="text-align: left;">
                        下記マスタはすでにWebで公開済の公開情報となり、どなたでもご利用いただけます
                        <ul>
                        <li><RouterLink :to="RoutePathConstants.PAGE_DOWNLOAD_MASTER_MIN">関連者最小登録マスタ(公開情報)</RouterLink></li>
                        <li><RouterLink :to="RoutePathConstants.PAGE_DOWNLOAD_HISTORY">関連者履歴マスタ(公開情報)</RouterLink></li>
                        </ul>
                    </div>
                    <div style="text-align: left;">
                        <ul>
                            <li><a href="https://www.houjin-bangou.nta.go.jp/webapi/index.html">法人番号システム
                                    Web-API</a>を利用した独自サービスを提供しています。このサービスは、国税庁法人番号システムのWeb-API機能を利用して取得した情報をもとに作成していますが、サービスの内容は国税庁によって保証されたものではありません
                            </li>
                            <li><a
                                    href="https://www.digital.go.jp/policies/base_registry_address">アドレス・ベース・レジストリ</a>を加工して作成したデータに基づき独自サービスを提供しています。サービスの内容はデジタル庁によって保証されたものではありません
                            </li>
                        </ul>
                    </div>
                </div>

            </div>
        </div>

        <!-- メッセージ表示    -->
        <div class="overMessage" v-if="messageType !== MessageConstants.VIEW_NONE">
            <MessageView :info-level="infoLevel" :message-type="messageType" :title="MESS_PAGE_NAME" :message="message"
                :caller="caller" @send-submit="recieveSubmit">
            </MessageView>
        </div>
    </div>
</template>

<style scoped>
.login-page {
    display: flex;
    justify-content: center;
    align-items: center;
    min-height: 80vh;
    background-color: #f4f4f4;
}

.form-container {
    background: white;
    padding: 2rem;
    border-radius: 8px;
    box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
    width: 100%;
    max-width: 600px;
    text-align: center;
}

h1 {
    font-size: 1.5rem;
    margin-bottom: 0.5rem;
}

h2 {
    font-size: 1.2rem;
    color: #666;
    margin-bottom: 2rem;
}

.form-group {
    margin-bottom: 1.5rem;
    text-align: left;
}

.form-group label {
    display: block;
    margin-bottom: 0.5rem;
    font-weight: bold;
}

.form-group input[type="text"],
.form-group input[type="password"] {
    width: 100%;
    padding: 0.75rem;
    border: 1px solid #ccc;
    border-radius: 4px;
    box-sizing: border-box;
    /* paddingを含めてwidth 100%に */
}

.password-wrapper {
    position: relative;
    display: flex;
    align-items: center;
}

.password-wrapper input {
    padding-right: 2.5rem;
    /* アイコンのスペース確保 */
}

.password-toggle-icon {
    position: absolute;
    right: 0.75rem;
    cursor: pointer;
    height: 1.35em;
}

.password-toggle-icon img {
    height: 1.35em;
    width: auto;
}


.form-options {
    display: flex;
    align-items: center;
    margin-bottom: 1.5rem;
}

.form-options input[type="checkbox"] {
    margin-right: 0.5rem;
}

.login-button {
    width: 100%;
    padding: 0.75rem;
    border: none;
    border-radius: 4px;
    background-color: #007bff;
    color: white;
    font-size: 1rem;
    font-weight: bold;
    cursor: pointer;
    transition: background-color 0.3s;
}

.login-button:hover {
    background-color: #0056b3;
}

.links {
    margin-top: 1.5rem;
    display: flex;
    flex-direction: column;
    gap: 1rem;
}

.links a {
    color: #007bff;
    text-decoration: none;
}

.links a:hover {
    text-decoration: underline;
}
</style>
