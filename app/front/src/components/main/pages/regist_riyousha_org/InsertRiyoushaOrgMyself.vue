<script setup lang="ts">
import { MessageConstants, MessageView, type FrameworkMessageAndResultDtoInterface, type LeastUserDtoInterface } from 'seijishikin-jp-normalize_common-tool';
import RoutePathConstants from '../../../../routePathConstants';
import ManagerInfo from '../../common/user_info/ManagerInfo.vue';
import { computed, onMounted, ref, type ComputedRef, type Ref } from 'vue';
import { getLoginUser } from '../../utils/getLoginUser';
import { RiyoushaCombineOrgEntity, type RiyoushaCombineOrgEntityInterface } from '../../entity/riyoushaCombineOrgEntity';
import UserRoleConstants from '../../dto/user/userRoleConstants';
import { GetRiyoushaMasterCapsuleDto, type GetRiyoushaMasterCapsuleDtoInterface } from '../../dto/riyousha/getRiyoushaMasterCapsuleDto';
import getAuthorizedPromiseArea from '../../dto/login/getAuthorizedPromiseArea';
import type { GetRiyoushaMasterResultDtoInterface } from '../../dto/riyousha/getRiyoushaMasterResultDto';
import { AccessTokenNotFoundError, TokenRefreshError } from '../../dto/login/errors';
import router from '../../../../router';
import { RiyoushaCombinePersonCapsuleDto, type RiyoushaCombinePersonCapsuleDtoInterface } from '../../dto/riyousha/riyoushaCombinePersonCapsuleDto';
import { useRoute } from 'vue-router';
import PartnerApiInfo from '../../common/user_info/PartnerApiInfo.vue';

// よく使う定数
const BLANK: string = "";
// const INIT_NUMBER: number = 0;
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

const editCombineEntity: Ref<RiyoushaCombineOrgEntityInterface> = ref(new RiyoushaCombineOrgEntity());
const isManagerView: ComputedRef<boolean> = computed(() => userDto.value.riyoushaRole === UserRoleConstants.MANAGER);
let disableCallMyself: boolean = INIT_BOOLEAN;
const route = useRoute();
const userRole: Ref<string> = ref(BLANK);
onMounted(() => {
    if (route.query.userRole !== null && undefined !== route.query.userRole) {
        userRole.value = route.query.userRole as string;
    }

    // 利用者設定がない場合はこのページからたたき出す(基本的にはデッドコードのはず)
    if (0 == userDto.value.riyoushaCode) {
        disableCallMyself = true;
        title.value = "利用者組織自分自身を所属させる";
        infoLevel.value = MessageConstants.LEVEL_ERROR;
        messageType.value = MessageConstants.VIEW_OK;
        message.value = "利用者の詳細登録を行ってください。ログアウトします。";
        return;
    }
    const capsuleDto: GetRiyoushaMasterCapsuleDtoInterface = new GetRiyoushaMasterCapsuleDto();
    capsuleDto.riyoushaRole = userDto.value.riyoushaRole;
    capsuleDto.riyoushaCode = userDto.value.riyoushaCode;

    // 利用者マスタの読み込み
    message.value = "利用者組織登録個人取得";
    getAuthorizedPromiseArea().then(token => {
        const url = urlBack + "/riyousha/get-myself";
        const method = "POST";
        const body = JSON.stringify(capsuleDto);
        const headers = {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'X-AUTH-TOKEN': 'Bearer ' + token
        };
        fetch(url, { method, headers, body })
            .then(async (response) => {
                const resultDto: GetRiyoushaMasterResultDtoInterface = await response.json();
                message.value = resultDto.message;
                if (resultDto.isFailure) {
                    infoLevel.value = MessageConstants.LEVEL_WARNING;
                    messageType.value = MessageConstants.VIEW_OK;
                } else {
                    if (isManagerView.value) {
                        editCombineEntity.value.personRiyoushaCode = resultDto.managerMasterEntity.riyoushaManagerMasterCode;
                        editCombineEntity.value.personRiyoushaName = resultDto.managerMasterEntity.allName;
                        editCombineEntity.value.riyoushaRole = UserRoleConstants.MANAGER;
                        editCombineEntity.value.personCode = userDto.value.userPersonCode;
                    } else {
                        editCombineEntity.value.personRiyoushaCode = resultDto.managerMasterEntity.riyoushaManagerMasterCode;
                        editCombineEntity.value.personRiyoushaName = resultDto.partnerApiMasterEntity.allName;
                        editCombineEntity.value.riyoushaRole = UserRoleConstants.PARTNER_API;
                        editCombineEntity.value.personCode = userDto.value.userPersonCode;
                    }
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


function onSave() {

    const capsuleDto: RiyoushaCombinePersonCapsuleDtoInterface = new RiyoushaCombinePersonCapsuleDto();
    capsuleDto.userDto = userDto.value;
    capsuleDto.combineEntity = editCombineEntity.value;

    title.value = "利用者組織登録";
    getAuthorizedPromiseArea().then(token => {
        const url = urlBack + "/riyousha-org/insert-combine";
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

function onCancel() {
    history.back();
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

    <h1>自分自身を組織に所属させる</h1>
    <div class="one-line">
        <div class="left-area">
            自分自身
        </div>
        <div class="right-area">
            <input type="number" v-model="editCombineEntity.personRiyoushaCode" class="code-input" disabled="true">
            <span class="left-space">名称：<input type="text" v-model="editCombineEntity.personRiyoushaName"
                    class="name-input" disabled="true"></span>
        </div>
    </div>

    <div class="one-line">
        <div class="left-area">
            参加する利用者組織コード
        </div>
        <div class="right-area">
            <input type="text" v-model="editCombineEntity.orgRiyoushaCode" class="code-input">
        </div>
    </div>

    <div class="one-line">
        <div class="left-area">
            参加する利用者組織名称
        </div>
        <div class="right-area">
            <input type="text" v-model="editCombineEntity.orgName" class="name-input">
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
