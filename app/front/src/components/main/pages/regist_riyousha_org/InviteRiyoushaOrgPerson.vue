<script setup lang="ts">
import { computed, onMounted, ref, type ComputedRef, type Ref } from 'vue';
import RoutePathConstants from '../../../../routePathConstants';
import ManagerInfo from '../../common/user_info/ManagerInfo.vue';
import { FrameworkCapsuleDto, getErrorMessage, getErrorUniqueIdMessage, MessageConstants, MessageView, type FrameworkCapsuleDtoInterface, type LeastUserDtoInterface } from 'seijishikin-jp-normalize_common-tool';
import { getLoginUser } from '../../utils/getLoginUser';
import UserRoleConstants from '../../dto/user/userRoleConstants';
import { GetRiyoushaMasterCapsuleDto, type GetRiyoushaMasterCapsuleDtoInterface } from '../../dto/riyousha/getRiyoushaMasterCapsuleDto';
import getAuthorizedPromiseArea from '../../dto/login/getAuthorizedPromiseArea';
import type { GetRiyoushaMasterResultDtoInterface } from '../../dto/riyousha/getRiyoushaMasterResultDto';
import { AccessTokenNotFoundError, TokenRefreshError } from '../../dto/login/errors';
import type { SelectOptionNumberDtoInterface } from '../../dto/select_options/selectOptionNumberDto';
import router from '../../../../router';
import { RiyoushaCombinePersonCapsuleDto, type RiyoushaCombinePersonCapsuleDtoInterface } from '../../dto/riyousha/riyoushaCombinePersonCapsuleDto';
import { useRoute } from 'vue-router';
import PartnerApiInfo from '../../common/user_info/PartnerApiInfo.vue';

// よく使う定数
const BLANK: string = "";
const INIT_NUMBER: number = 0;
// const INIT_BOOLEAN: boolean = false;
// const SEARCH_LIMIT: number = 20;
const INQUIRE_FLG: boolean = false;
const ERR_MESS_ONLY: boolean = true;
const MESS_PAGE_NAME: string = "利用者組織招待";
const INIT_CALLER: string = "no branch";

// メッセージボックス表示定数
const infoLevel: Ref<number> = ref(MessageConstants.LEVEL_NONE);
const messageType: Ref<number> = ref(MessageConstants.VIEW_NONE);
const caller: Ref<string> = ref(INIT_CALLER);
const message: Ref<string> = ref(BLANK);

// back側アクセス
const urlBack: string = RoutePathConstants.DOMAIN + RoutePathConstants.BASE_PATH;

// ユーザ呼び出し
const userDto: Ref<LeastUserDtoInterface> = ref(getLoginUser());

const riyoshaOrgoptions: Ref<SelectOptionNumberDtoInterface[]> = ref([]);
const selectedOrgCode: Ref<number> = ref(INIT_NUMBER);
const isEnableInvite: ComputedRef<boolean> = computed(() => riyoshaOrgoptions.value.length > 0);

//編集Dto
const capsuleDtoEdit: Ref<RiyoushaCombinePersonCapsuleDtoInterface> = ref(new RiyoushaCombinePersonCapsuleDto());
const isManagerView: ComputedRef<boolean> = computed(() => userDto.value.riyoushaRole === UserRoleConstants.MANAGER);
const riyoushaCode: Ref<number> = ref(INIT_NUMBER);
const riyoushaName: Ref<string> = ref(BLANK);

const route = useRoute();
const userRole: Ref<string> = ref(BLANK);
const logoutText = "logout";
onMounted(() => {

    if (route.query.userRole !== null && undefined !== route.query.userRole) {
        userRole.value = route.query.userRole as string;
    }


    // 利用者設定がない場合はこのページかたたき出す(基本的にデッドコードのはず)
    if (0 == userDto.value.riyoushaCode) {
        infoLevel.value = MessageConstants.LEVEL_ERROR;
        messageType.value = MessageConstants.VIEW_OK;
        message.value = "利用者の詳細登録を行ってください。ログアウトします。";
        caller.value = logoutText;
        return;
    }
    const capsuleDtoMyself: GetRiyoushaMasterCapsuleDtoInterface = new GetRiyoushaMasterCapsuleDto();
    capsuleDtoMyself.riyoushaRole = userDto.value.riyoushaRole;
    capsuleDtoMyself.riyoushaCode = userDto.value.riyoushaCode;

    // 利用者自身を読み込み
    getAuthorizedPromiseArea().then(token => {
        const url = urlBack + "/riyousha/get-myself";
        const method = "POST";
        const body = JSON.stringify(capsuleDtoMyself);
        const headers = {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'X-AUTH-TOKEN': 'Bearer ' + token
        };
        fetch(url, { method, headers, body })
            .then(async (response) => {
                const resultDto: GetRiyoushaMasterResultDtoInterface = await response.json();
                if (resultDto.isFailure) {
                    message.value = resultDto.message;
                    infoLevel.value = MessageConstants.LEVEL_WARNING;
                    messageType.value = MessageConstants.VIEW_OK;
                    return;
                } else {
                    if (isManagerView.value) {
                        riyoushaCode.value = resultDto.managerMasterEntity.riyoushaManagerMasterCode;
                        riyoushaName.value = resultDto.managerMasterEntity.allName;
                    } else {
                        riyoushaCode.value = resultDto.partnerApiMasterEntity.riyoushaPartnerApiMasterCode;
                        riyoushaName.value = resultDto.partnerApiMasterEntity.allName;
                    }
                }
            })
            .catch((error) => {
                message.value = getErrorMessage(error, ERR_MESS_ONLY);
                infoLevel.value = MessageConstants.LEVEL_ERROR;
                messageType.value = MessageConstants.VIEW_OK;
                return;
            });
    }).catch((e) => {
        infoLevel.value = MessageConstants.LEVEL_ERROR;
        messageType.value = MessageConstants.VIEW_OK;

        // トークン保持または取得に失敗している場合
        if (e instanceof AccessTokenNotFoundError || e instanceof TokenRefreshError) {
            message.value = e.message;
            return;
        }

        message.value = getErrorMessage(e, INQUIRE_FLG);
        return;
    });

    // 利用者自身が所属する組織選択項目を読み込み
    const capsuleDtoOption: FrameworkCapsuleDtoInterface = new FrameworkCapsuleDto();
    capsuleDtoOption.userDto = userDto.value;

    getAuthorizedPromiseArea().then(token => {
        const url = urlBack + "/riyousha-org/get-org-options";
        const method = "POST";
        const body = JSON.stringify(capsuleDtoOption);
        const headers = {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'X-AUTH-TOKEN': 'Bearer ' + token
        };
        fetch(url, { method, headers, body })
            .then(async (response) => {
                riyoshaOrgoptions.value = await response.json();
                // 編集時には必要な新規登録選択肢は不要
                riyoshaOrgoptions.value.shift();
                if (riyoshaOrgoptions.value.length === 0) {
                    message.value = "組織に所属していないため、組織に招待ができません";
                    infoLevel.value = MessageConstants.LEVEL_WARNING;
                    messageType.value = MessageConstants.VIEW_OK;
                }
            })
            .catch((error) => {
                message.value = getErrorMessage(error, ERR_MESS_ONLY);
                infoLevel.value = MessageConstants.LEVEL_ERROR;
                messageType.value = MessageConstants.VIEW_OK;
                return;
            });
    }).catch((e) => {
        infoLevel.value = MessageConstants.LEVEL_ERROR;
        messageType.value = MessageConstants.VIEW_OK;

        // トークン保持または取得に失敗している場合
        if (e instanceof AccessTokenNotFoundError || e instanceof TokenRefreshError) {
            message.value = e.message;
            return;
        }

        message.value = getErrorMessage(e, INQUIRE_FLG);
        return;
    });
});


function onSave() {

    // 保存処理
    capsuleDtoEdit.value.combineEntity.orgRiyoushaCode = selectedOrgCode.value;
    const name: undefined | string = riyoshaOrgoptions.value.filter
        ((e) => selectedOrgCode.value === e.value)[0]?.text;
    if (undefined !== name) {
        capsuleDtoEdit.value.combineEntity.orgName = name;
    } else {
        infoLevel.value = MessageConstants.LEVEL_ERROR;
        messageType.value = MessageConstants.VIEW_OK;
        message.value = getErrorUniqueIdMessage(selectedOrgCode.value);
        return;
    }
    
    capsuleDtoEdit.value.userDto = userDto.value;

    message.value = "自分が所属する組織に利用者を招待";
    getAuthorizedPromiseArea().then(token => {
        const url = urlBack + "/riyousha-org/invite-person";
        const method = "POST";
        const body = JSON.stringify(capsuleDtoEdit.value);
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
                    infoLevel.value = MessageConstants.LEVEL_INFO;
                    messageType.value = MessageConstants.VIEW_TOAST;
                }
            })
            .catch((error) => {
                message.value = getErrorMessage(error, ERR_MESS_ONLY);
                infoLevel.value = MessageConstants.LEVEL_ERROR;
                messageType.value = MessageConstants.VIEW_OK;
                return;
            });
    }).catch((e) => {
        infoLevel.value = MessageConstants.LEVEL_ERROR;
        messageType.value = MessageConstants.VIEW_OK;

        // トークン保持または取得に失敗している場合
        if (e instanceof AccessTokenNotFoundError || e instanceof TokenRefreshError) {
            message.value = e.message;
            return;
        }

        message.value = getErrorMessage(e, INQUIRE_FLG);
        return;
    });
}

function onCancel() {
    history.back();
}

function recieveSubmit(button: string, callerMethod: string) {
    // OKボタンのうち、利用者情報が取れないことによる場合はログアウト
    if (MessageConstants.BUTTON_OK === button && callerMethod === logoutText) {
        router.push(RoutePathConstants.PAGE_LOGOUT);
    }

    infoLevel.value = 0;
    messageType.value = 0;
    caller.value = INIT_CALLER;
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

    <h1>利用者を組織に招待</h1>

    <div class="one-line">
        <div class="left-area">
            自分自身
        </div>
        <div class="right-area">
            <input type="number" v-model="riyoushaCode" class="code-input" disabled="true">
            <span class="left-space">名称：<input type="text" v-model="riyoushaName" class="name-input"
                    disabled="true"></span>
        </div>
    </div>

    <div class="one-line">
        <div class="left-area">
            利用者組織
        </div>
        <div class="right-area">
            <select v-model="selectedOrgCode" :disabled="!isEnableInvite">
                <option v-for="dto of riyoshaOrgoptions" :value="dto.value">{{ dto.text }}</option>
            </select>
        </div>
    </div>

    <div class="one-line">
        <div class="left-area">
            招待する個人コード
        </div>
        <div class="right-area">
            <input type="number" v-model="capsuleDtoEdit.combineEntity.personRiyoushaCode" class="code-input">
        </div>
    </div>

    <div class="one-line">
        <div class="left-area">
            招待する個人名称
        </div>
        <div class="right-area">
            <input type="text" v-model="capsuleDtoEdit.combineEntity.personRiyoushaName" class="name-input">
        </div>
    </div>

    <div class="one-line">
        <div class="left-area">
            個人メールアドレス(確認用)
        </div>
        <div class="right-area">
            <input type="email" v-model="capsuleDtoEdit.email" class="name-input">
        </div>
    </div>

    <div class="footer">
        <button class="footer-button" @click="onCancel">キャンセル</button>
        <button class="footer-button left-space" @click="onSave" :disabled="!isEnableInvite">送信</button>
    </div>

    <!-- メッセージ表示    -->
    <div class="overMessage" v-if="messageType !== MessageConstants.VIEW_NONE">
        <MessageView :info-level="infoLevel" :message-type="messageType" :title="MESS_PAGE_NAME" :message="message"
            :caller="caller" @send-submit="recieveSubmit">
        </MessageView>
    </div>

</template>
<style scoped></style>
