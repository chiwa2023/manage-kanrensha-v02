<script setup lang="ts">
import { MessageConstants, MessageView } from 'seijishikin-jp-normalize_common-tool';
import RoutePathConstants from '../../../routePathConstants';
import { ref, type Ref } from 'vue';
// import { LeastUserDto, type LeastUserDtoInterface } from '../dto/user/leastUserDto';
import { LoginUserCapsuleDto, type LoginUserCapsuleDtoInterface } from '../dto/login/loginUserCapsuleDto';
//import UserRoleConstants from '../dto/user/userRoleConstants';
// import LoginConstants from '../dto/user/loginConstants';
// import RoutePathConstants from './routePathConstants';
// import type UserPersonLeastInterface from './dto/user/userPersonLeastDto';
// import UserPersonLeastDto from './dto/user/userPersonLeastDto';
// import UserRoleConstants from './dto/user/userRoleConstants';

// const userDto: Ref<UserPersonLeastInterface> = ref(new UserPersonLeastDto());
// userDto.value.listRoles.push(UserRoleConstants.ROLE_ADMIN);
// userDto.value.listRoles.push(UserRoleConstants.ROLE_MANAGER);
// userDto.value.listRoles.push(UserRoleConstants.ROLE_COMRADE);
// userDto.value.listRoles.push(UserRoleConstants.ROLE_PARTNER_PERSON);
// userDto.value.listRoles.push(UserRoleConstants.ROLE_PARTNER_CORP);
// userDto.value.listRoles.push(UserRoleConstants.ROLE_PARTNER_POLI_ORG);
// userDto.value.userPersonCode=190;
// sessionStorage.setItem("userDto", JSON.stringify(userDto.value));

// よく使う定数
const BLANK: string = "";
// const INIT_NUMBER: number = 0;
// const SERVER_STATUS_OK: number = 200;
// const SERVER_STATUS_ERROR: number = 400;

// メッセージ表示定数
const infoLevel: Ref<number> = ref(MessageConstants.LEVEL_NONE);
const messageType: Ref<number> = ref(MessageConstants.VIEW_NONE);
const title: Ref<string> = ref(BLANK);
const message: Ref<string> = ref(BLANK);

// 仮インスタンスを設定?
// const userDto: Ref<LeastUserDtoInterface> = ref(new LeastUserDto());


function recieveSubmit(button: string) {
    console.log(button); // 警告除け
    // TODO ボタンタイプ別の挙動はこの中で変える

    // 非表示
    infoLevel.value = 0;
    messageType.value = 0;
}

const user: Ref<LoginUserCapsuleDtoInterface> = ref(new LoginUserCapsuleDto());
function onLogin() {
    // 入力チェック
    if (user.value.userId === BLANK || user.value.password === BLANK) {
        infoLevel.value = MessageConstants.LEVEL_ERROR;
        messageType.value = MessageConstants.VIEW_OK;
        title.value = "入力エラー";
        message.value = "メールアドレスとパスワードは必須です。";
        return;
    }

    // const url = urlBack + "/login";
    // const method = "POST";
    // const body = JSON.stringify({
    //     ...user.value,
    //     rememberMe: rememberMe.value
    // });
    // const headers = {
    //     'Accept': 'application/json',
    //     'Content-Type': 'application/json'
    // };
    // fetch(url, { method, headers, body })
    //     .then(async (response) => {
    //         const status = response.status;
    //         if (status === 200) {
    //             const resultDto: LoginUserResultInterface = await response.json();
    //             sessionStorage.setItem("userDto", JSON.stringify(resultDto.userPersonLeastDto));
    //             sessionStorage.setItem("jwtToken", JSON.stringify(resultDto.jwtTokenDto));
    //             switch (resultDto.userPersonLeastDto.listRoles[0]) {
    //                 case "ROLE_admin":
    //                     // 運営者
    //                     router.push(RoutePathConstants.PAGE_MENU_ADMIN);
    //                     break;
    //                 case "ROLE_manager":
    //                     // 運営者
    //                     router.push(RoutePathConstants.PAGE_MENU_MANAGER);
    //                     break;
    //                 case "ROLE_comrade":
    //                     // APIユーザ
    //                     router.push(RoutePathConstants.PAGE_MENU_PARTNER_API);
    //                     break;
    //                 case "ROLE_partner_person":
    //                 case "ROLE_partner_corp":
    //                 case "ROLE_partner_poli_org":
    //                     // 関連者
    //                     router.push(RoutePathConstants.PAGE_MENU_KANRENSHA);
    //                     break;
    //                 default:
    //                     alert("権限設定が登録できませんでした");
    //                     break;
    //             }
    //         }
    //         if (status === 401) {
    //             alert("status 401");
    //         }
    //     })
    //     .catch((error) => { alert(error); });
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
                    <RouterLink to="/reset_password/propose">※パスワードを忘れたので再発行</RouterLink>
                </div>
            </div>
        </div>

        <!-- メッセージ表示 -->
        <div class="overMessage" v-if="messageType !== MessageConstants.VIEW_NONE">
            <MessageView :info-level="infoLevel" :message-type="messageType" :title="title" :message="message"
                @send-submit="recieveSubmit">
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
    max-width: 400px;
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
    box-sizing: border-box; /* paddingを含めてwidth 100%に */
}

.password-wrapper {
    position: relative;
    display: flex;
    align-items: center;
}

.password-wrapper input {
    padding-right: 2.5rem; /* アイコンのスペース確保 */
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
