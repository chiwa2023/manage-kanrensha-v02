<script setup lang="ts">
import { ref, type Ref } from 'vue';
import RoutePathConstants from '../../../../routePathConstants';
import { NewComerDto, type NewComerDtoInterface } from '../../dto/add_account/newComerDto';
import MockNewComerInfo from '../../../test/common/user_info/MockNewComerInfo.vue';
import { useApi } from '../../utils/useApi';
import type { LoginUserResultDtoInterface } from '../../dto/login/loginUserResultDto';
import { MessageConstants, MessageView } from 'seijishikin-jp-normalize_common-tool';
import router from '../../../../router';
import RoleConstants from '../../dto/login/RoleConstants';

// back側アクセス
const urlBack: string = RoutePathConstants.DOMAIN + RoutePathConstants.BASE_PATH;

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

// 入力用Dto
const sessionStorage = window["sessionStorage"];
const newComer: Ref<NewComerDtoInterface> = ref(new NewComerDto());
const dtoJson: string | null = sessionStorage.getItem("new-comer");
if (null !== dtoJson) {
    newComer.value = JSON.parse(dtoJson);
}

newComer.value.role = "manager";

// 入力されたコードをチェックして正常ならパスワード入力と
// 権限を選択してもらう
// API呼び出し用Composable
const { loading: addUserLoading, error: addUserError, fetchData: fetchAddUser } = useApi<LoginUserResultDtoInterface>();
async function onRegistUser() {
    // パスワード、権限、ニックネームを登録

    const url = urlBack + "/add-user/user";
    const config = {
        method: "POST",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(newComer.value)
    };

    const resultDto: LoginUserResultDtoInterface | null = await fetchAddUser(url, config);

    if (resultDto !== null) {
        sessionStorage.setItem("userDto", JSON.stringify(resultDto.userDto));
        sessionStorage.setItem("jwtToken", JSON.stringify(resultDto.jwtTokenDto));

        switch (newComer.value.role) {
            case RoleConstants.MANAGER:
                // 運営者
                router.push(RoutePathConstants.PAGE_INSERT_MANAGER);
                break;
            case RoleConstants.PARTNER_API:
                // APIユーザ
                router.push(RoutePathConstants.PAGE_INSERT_PARTNER_API);
                break;
            case RoleConstants.KANRENSHA_PERSON:
            case RoleConstants.KANRENSHA_KIGYOU_DT:
            case RoleConstants.KANRENSHA_SEIJIDANTAI:
                // 関連者
                router.push(RoutePathConstants.PAGE_INSERT_KANRENSHA);
                break;
            default:
                infoLevel.value = MessageConstants.LEVEL_ERROR;
                messageType.value = MessageConstants.VIEW_OK;
                title.value = "権限者未登録";
                if (addUserError.value !== null) {
                    message.value = addUserError.value;
                }
                break;
        }
    } else {
        infoLevel.value = MessageConstants.LEVEL_ERROR;
        messageType.value = MessageConstants.VIEW_OK;
        title.value = "システムエラー";
        if (addUserError.value !== null) {
            message.value = addUserError.value;
        }
    }
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

function onCancel() {
    history.back();
}

function recieveSubmit(button: string) {
    console.log(button);
    infoLevel.value = 0;
    messageType.value = 0;
}
</script>
<template>
    <!-- 新規登録コードチェック -->
    <MockNewComerInfo :current-step="3" :regist-code="newComer.registCode"></MockNewComerInfo>

    <h1>ユーザ登録</h1>

    <div class="one-line">
        <div class="left-area">
            送信されたコード
        </div>
        <div class="right-area">
            <input type="email" v-model="newComer.registCode" class="name-input" disabled="true">
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
            このサイトだけで<br>使用する名前
        </div>
        <div class="right-area">
            <input type="text" v-model="newComer.nickName" class="name-input">
        </div>
    </div>


    <div class="one-line">
        <div class="left-area">
            パスワード &nbsp;
        </div>
        <div class="right-area">
            <input :type="passwordInputType" v-model="newComer.password" class="name-input">
            <span @click="changeVisiblePassword"><img v-show="!isPasswordVisible"
                    src="../../../../assets/password_hidden.png" style="height:1.35em"><img v-show="isPasswordVisible"
                    src="../../../../assets/password_visible.png" style="height:1.35em"></span>
        </div>
    </div>

    <div class="one-line">
        <div class="left-area">
            ユーザ区分
        </div>
        <div class="right-area">
            <div class="form-group-vertical">
                <div>
                    <input type="radio" id="role" v-model="newComer.role" :value=RoleConstants.MANAGER>このサイトで<span
                        class="explain">大量・一括関連者データ編集</span>を行いたい<span class="kbn">運営者</span>
                </div>
                <div>
                    <input type="radio" id="role" v-model="newComer.role"
                        :value=RoleConstants.PARTNER_API>自作ソフトウェアに<span class="explain">このサイトの関連者情報を取り込みたい</span><span
                        class="kbn">APIユーザ</span>
                </div>
                <div>
                    <input type="radio" id="role" v-model="newComer.role" :value=RoleConstants.KANRENSHA_PERSON><span
                        class="explain">政治団体と資金・物品の取引</span>をする<span class="kbn">関連者個人</span>
                </div>
                <div>
                    <input type="radio" id="role" v-model="newComer.role" :value=RoleConstants.KANRENSHA_KIGYOU_DT><span
                        class="explain">政治団体と資金・物品の取引</span>をする<span class="kbn">関連者企業・団体</span>
                </div>
                <div>
                    <input type="radio" id="role" v-model="newComer.role"
                        :value=RoleConstants.KANRENSHA_SEIJIDANTAI><span class="explain">政治団体と資金・物品の取引</span>をする<span
                        class="kbn">関連者政治団体</span>
                </div>
            </div>
        </div>
    </div>

    <div class="one-line">
        <div class="left-area">
            【参考】ユーザ区分の<br>データ編集範囲一覧
        </div>
        <div class="right-area">
            <table>
                <tbody>

                    <tr>
                        <th>ユーザ区分</th>
                        <th>このサイトでのデータ管理</th>
                        <th>大量・一括編集</th>
                        <th>一人編集</th>
                    </tr>
                    <tr>
                        <td>運営者</td>
                        <td>○</td>
                        <td>○</td>
                        <td>○</td>
                    </tr>
                    <tr>
                        <td>APIユーザ</td>
                        <td>△：本人のみ</td>
                        <td>×：申請制を予定</td>
                        <td>○</td>
                    </tr>
                    <tr>
                        <td>関連者</td>
                        <td>△：本人のみ</td>
                        <td>×</td>
                        <td>△：本人のみ</td>
                    </tr>
                </tbody>
            </table>

        </div>
    </div>

    <div class="footer">
        <button class="footer-button" @click="onCancel">前に戻る</button>
        <button class="footer-button left-space" @click="onRegistUser" :disabled="addUserLoading">送信</button>
    </div>



    <!-- メッセージ表示 -->
    <div class="overMessage" v-if="messageType !== MessageConstants.VIEW_NONE">
        <MessageView :info-level="infoLevel" :message-type="messageType" :title="title" :message="message"
            @send-submit="recieveSubmit">
        </MessageView>
    </div>


</template>
<style scoped>
span.kbn {
    font-weight: bold;
}

span.explain {
    font-size: 110%;
}

table {
    border-style: solid;
    border-width: 1px;
}

td {
    border-style: solid;
    border-width: 1px;
}

th {
    border-style: solid;
    border-width: 1px;
}
</style>
