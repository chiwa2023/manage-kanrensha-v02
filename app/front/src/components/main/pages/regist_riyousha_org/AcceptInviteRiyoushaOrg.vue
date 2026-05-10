<script setup lang="ts">
import {
    MessageConstants, MessageView,
    type FrameworkMessageAndResultDtoInterface, type LeastUserDtoInterface
} from 'seijishikin-jp-normalize_common-tool';
import { onMounted, ref, type Ref } from 'vue';
import RoutePathConstants from '../../../../routePathConstants';
import { getLoginUser } from '../../utils/getLoginUser';
import router from '../../../../router';
import { useRoute } from 'vue-router';
import getAuthorizedPromiseArea from '../../dto/login/getAuthorizedPromiseArea';
import { AccessTokenNotFoundError, TokenRefreshError } from '../../dto/login/errors';
import { GetTempRiyoushaOrgCombineCapsuleDto, type GetTempRiyoushaOrgCombineCapsuleDtoInterface } from '../../dto/riyousha/getTempRiyoushaOrgCombineCapsuleDto';
import type { GetTempRiyoushaOrgCombineResultDtoInterface } from '../../dto/riyousha/getTempRiyoushaOrgCombineResultDto';
import { AcceptRiyoushaCombineCapsuleDto, type AcceptRiyoushaCombineCapsuleDtoInterface } from '../../dto/riyousha/acceptRiyoushaCombineCapsuleDto';
import { RiyoushaCombineOrgTempEntity, type RiyoushaCombineOrgTempEntityInterface } from '../../entity/riyoushaCombineOrgTempEntity';
import { useTaskPlan } from '../../stores/storeTaskPlan';
import ManagerInfo from '../../common/user_info/ManagerInfo.vue';
import { nextTransferPassStore } from '../../stores/nextTransferPass';
import UserRoleConstants from '../../dto/user/userRoleConstants';
import PartnerApiInfo from '../../common/user_info/PartnerApiInfo.vue';


// よく使う定数
const BLANK: string = "";
const INIT_NUMBER: number = 0;
const INIT_BOOLEAN: boolean = false;
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

// ユーザ呼び出し
const userDto: Ref<LeastUserDtoInterface> = ref(getLoginUser());
const viewEntity: Ref<RiyoushaCombineOrgTempEntityInterface> = ref(new RiyoushaCombineOrgTempEntity());

let disableCallMyself: boolean = INIT_BOOLEAN;
const route = useRoute();
const userRole: Ref<string> = ref(BLANK);
onMounted(() => {
    // 直リンク(パスチェックあり)を許容ロジック
    if (INIT_NUMBER === userDto.value.userPersonId) {
        const passStore = nextTransferPassStore()
        passStore.fullPath = route.fullPath;
        router.push(RoutePathConstants.PAGE_LOGIN);
        return;
    }

    const capsuleDto: GetTempRiyoushaOrgCombineCapsuleDtoInterface = new GetTempRiyoushaOrgCombineCapsuleDto();
    capsuleDto.userDto = userDto.value;
    // タスクから、メールのリンクから直接遷移された場合、クエリから固有の仮登録をとってくる
    if (route.query.orgCode !== null && undefined !== route.query.orgCode) {
        capsuleDto.orgCode = parseInt(route.query.orgCode as string);
    }
    if (route.query.personCode !== null && undefined !== route.query.personCode) {
        capsuleDto.personCode = parseInt(route.query.personCode as string);
    }
    if (route.query.userRole !== null && undefined !== route.query.userRole) {
        capsuleDto.userRole = route.query.userRole as string;
        userRole.value = capsuleDto.userRole;
    }
    // メニューから遷移してきて固有の仮情報を指定できない場合はログインユーザコードで絞って古い順(Id順)

    // 利用者設定がない場合はこのページかたたき出す(基本的にデッドコードのはず)
    if (0 == userDto.value.riyoushaCode) {
        disableCallMyself = true;
        title.value = "利用者組織自分自身を所属させる";
        infoLevel.value = MessageConstants.LEVEL_ERROR;
        messageType.value = MessageConstants.VIEW_OK;
        message.value = "利用者の詳細登録を行ってください。ログアウトします。";
        return;
    }

    title.value = "組織所属招待取得";
    getAuthorizedPromiseArea().then(token => {
        const url = urlBack + "/riyousha-org/get-temp-combine";
        const method = "POST";
        const body = JSON.stringify(capsuleDto);
        const headers = {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'X-AUTH-TOKEN': 'Bearer ' + token
        };
        fetch(url, { method, headers, body })
            .then(async (response) => {
                const resultDto: GetTempRiyoushaOrgCombineResultDtoInterface = await response.json();
                message.value = resultDto.message;
                if (resultDto.isFailure) {
                    infoLevel.value = MessageConstants.LEVEL_WARNING;
                    messageType.value = MessageConstants.VIEW_OK;
                } else {
                    viewEntity.value = resultDto.combineTempEntity;
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
});

function onCancel() {
    history.back();
}

const acceptState: Ref<string> = ref("1");

function onSave() {

    const capsuleDto: AcceptRiyoushaCombineCapsuleDtoInterface = new AcceptRiyoushaCombineCapsuleDto();
    capsuleDto.userDto = userDto.value;

    capsuleDto.isAsscept = acceptState.value === "1" ? true : false;
    capsuleDto.orgTempId = viewEntity.value.riyoushaCombineOrgTempId;
    // タスクはセッションからとってくる
    const storesTaskPlan = useTaskPlan();
    capsuleDto.taskYear = storesTaskPlan.taskYear;
    capsuleDto.taskPlanId = storesTaskPlan.taskPlanId;

    title.value = "組織所属承諾登録";
    getAuthorizedPromiseArea().then(token => {
        const url = urlBack + "/riyousha-org/accept-combine";
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
    // OKボタンのうち、利用者情報が取れないことによる場合はログアウト

    if ("OK" === button && disableCallMyself) {
        router.push(RoutePathConstants.PAGE_LOGOUT);
    }

    infoLevel.value = 0;
    messageType.value = 0;
}
</script>
<template>
    <div v-if="userRole == UserRoleConstants.MANAGER">
        <!-- 管理者メニュー兼チェック-->
        <ManagerInfo :user-dto="userDto"></ManagerInfo>
    </div>
    <div v-else>
        <!-- APIパートナーメニュー兼チェック-->
        <PartnerApiInfo :user-dto="userDto"></PartnerApiInfo>
    </div>

    <h1>組織所属招待承諾</h1>

    <div class="one-line">
        <div class="left-area">
            所属承諾
        </div>
        <div class="right-area">
            <span><input type="radio" v-model="acceptState" value="1" id="accept">承諾する</span>
            <span class="left-space"><input type="radio" v-model="acceptState" value="0" id="accept">承諾しない</span>
        </div>
    </div>


    <div class="one-line">
        <div class="left-area">
            個人(自分自身)
        </div>
        <div class="right-area">
            <span class="left-space">名称：<input type="text" v-model="viewEntity.personRiyoushaName" class="name-input"
                    disabled="true"></span>
        </div>
    </div>

    <div class="one-line">
        <div class="left-area">
            組織
        </div>
        <div class="right-area">
            <input type="number" v-model="viewEntity.orgRiyoushaCode" class="code-input" disabled="true">
            <input type="text" v-model="viewEntity.orgName" class="name-input left-space" disabled="true">
        </div>
    </div>


    <div class="footer">
        <button class="footer-button" @click="onCancel">キャンセル</button>
        <button class="footer-button left-space" @click="onSave">送信</button>
    </div>

    <!-- メッセージ表示 -->
    <div class="overMessage" v-if="messageType !== MessageConstants.VIEW_NONE">
        <MessageView :info-level="infoLevel" :message-type="messageType" :title="title" :message="message"
            @send-submit="recieveSubmit">
        </MessageView>
    </div>

</template>
<style scoped></style>
