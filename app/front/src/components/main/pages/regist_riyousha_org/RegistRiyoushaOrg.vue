<script setup lang="ts">
import { onBeforeMount, ref, type Ref } from 'vue';
import { getLoginUser } from '../../utils/getLoginUser';
import RiyoushaOrgEdit from '../../common/riyousha_edit/RiyoushaOrgEdit.vue';
import { type SelectOptionNumberDtoInterface } from '../../dto/select_options/selectOptionNumberDto';
import { FrameworkCapsuleDto, MessageConstants, MessageView, type FrameworkCapsuleDtoInterface, type FrameworkMessageAndResultDtoInterface, type LeastUserDtoInterface } from 'seijishikin-jp-normalize_common-tool';
import ManagerInfo from '../../common/user_info/ManagerInfo.vue';
import type { RiyoushaOrgDtoInterface } from '../../dto/riyousha/riyoushaOrgDto';
import getAuthorizedPromiseArea from '../../dto/login/getAuthorizedPromiseArea';
import RoutePathConstants from '../../../../routePathConstants';
import { AccessTokenNotFoundError, TokenRefreshError } from '../../dto/login/errors';
import { SaveRiyoushaOrgCapsuleDto, type SaveRiyoushaOrgCapsuleDtoInterface } from '../../dto/riyousha/saveRiyoushaOrgCapsuleDto';
import { useRoute } from 'vue-router';
import UserRoleConstants from '../../dto/user/userRoleConstants';
import PartnerApiInfo from '../../common/user_info/PartnerApiInfo.vue';

// よく使う定数
const BLANK: string = "";
const INIT_NUMBER: number = 0;
// const SERVER_STATUS_OK: number = 200;
// const SERVER_STATUS_ERROR: number = 400;
// メッセージボックス表示定数
const infoLevel: Ref<number> = ref(MessageConstants.LEVEL_NONE);
const messageType: Ref<number> = ref(MessageConstants.VIEW_NONE);
const title: Ref<string> = ref(BLANK);
const message: Ref<string> = ref(BLANK);

// back側アクセス
const urlBack: string = RoutePathConstants.DOMAIN + RoutePathConstants.BASE_PATH;

// ユーザ呼び出し
const userDto: Ref<LeastUserDtoInterface> = ref(getLoginUser());

const riyoshaOrgoptions: Ref<SelectOptionNumberDtoInterface[]> = ref([]);
const selectedOrgCode: Ref<number> = ref(INIT_NUMBER);

const route = useRoute();
const userRole: Ref<string> = ref(BLANK);
onBeforeMount(() => {

    if (route.query.userRole !== null && undefined !== route.query.userRole) {
        userRole.value = route.query.userRole as string;
    }

    const capsuleDto: FrameworkCapsuleDtoInterface = new FrameworkCapsuleDto();
    capsuleDto.userDto = userDto.value;

    title.value = "所属組織項目取得";
    getAuthorizedPromiseArea().then(token => {
        const url = urlBack + "/riyousha-org/get-org-options";
        const method = "POST";
        const body = JSON.stringify(capsuleDto);
        const headers = {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'X-AUTH-TOKEN': 'Bearer ' + token
        };
        fetch(url, { method, headers, body })
            .then(async (response) => {
                riyoshaOrgoptions.value = await response.json();
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

function recieveCancelRiyoushaOrg() {
    history.back();
}

function recieveRiyoushaOrgInterface(editDto: RiyoushaOrgDtoInterface) {
    // 保存処理
    const capsuleDto: SaveRiyoushaOrgCapsuleDtoInterface = new SaveRiyoushaOrgCapsuleDto();
    capsuleDto.userDto = userDto.value;
    capsuleDto.riyoushaOrgDto = editDto;

    title.value = "利用者組織更新";
    getAuthorizedPromiseArea().then(token => {
        const url = urlBack + "/riyousha-org/update";
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
    console.log(button); // 警告除け
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

    <h1>利用者組織登録・編集</h1><br>

    <h3 class="accent-h3">利用者組織の指定</h3><br>

    <div class="one-line">
        <div class="left-area">
            編集する利用者組織
        </div>
        <div class="right-area">
            <select v-model="selectedOrgCode">
                <option v-for="dto of riyoshaOrgoptions" :value="dto.value">{{ dto.text }}</option>
            </select>
        </div>
    </div>

    <!-- 利用者編集 -->
    <RiyoushaOrgEdit :user-dto="userDto" :selected-id="selectedOrgCode"
        @send-cancel-riyousha-org="recieveCancelRiyoushaOrg" @send-riyousha-org-interface="recieveRiyoushaOrgInterface">
    </RiyoushaOrgEdit>

    <!-- メッセージ表示 -->
    <div class="overMessage" v-if="messageType !== MessageConstants.VIEW_NONE">
        <MessageView :info-level="infoLevel" :message-type="messageType" :title="title" :message="message"
            @send-submit="recieveSubmit">
        </MessageView>
    </div>

</template>
<style scoped></style>
