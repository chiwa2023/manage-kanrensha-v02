<script setup lang="ts">
import { onBeforeMount, ref, type Ref } from 'vue';
import UserRoleConstants from '../../dto/user/userRoleConstants';
import { FrameworkCapsuleDto, LeastUserDto, MessageConstants, MessageView, type FrameworkCapsuleDtoInterface, type LeastUserDtoInterface } from 'seijishikin-jp-normalize_common-tool';
import getAuthorizedPromiseArea from '../../dto/login/getAuthorizedPromiseArea';
import RoutePathConstants from '../../../../routePathConstants';
import { GetUserDtoCapsuleDto, type GetUserDtoCapsuleDtoInterface } from '../../dto/user/getUserDtoCapsuleDto';
import type { GetUserDtoResultDtoInterface } from '../../dto/user/getUserDtoResultDto';
import { AccessTokenNotFoundError, TokenRefreshError } from '../../dto/login/errors';

// props,emits
const props = defineProps<{ editUserId: number, userDto: LeastUserDtoInterface }>()
const emits = defineEmits(["sendCancelEditUser", "sendEditUserInterface"]);

//仮
// よく使う定数
const BLANK: string = "";
// const INIT_NUMBER: number = 0;
// const SERVER_STATUS_OK: number = 200;
// const SERVER_STATUS_ERROR: number = 400;
// メッセージボックス表示定数
const infoLevel: Ref<number> = ref(MessageConstants.LEVEL_NONE);
const messageType: Ref<number> = ref(MessageConstants.VIEW_NONE);
const title: Ref<string> = ref(BLANK);
const message: Ref<string> = ref(BLANK);

// back側アクセス
const urlBack: string = RoutePathConstants.DOMAIN + RoutePathConstants.BASE_PATH;

// 表示用変数
const editUserDto: Ref<LeastUserDtoInterface> = ref(new LeastUserDto());
const hasRoleManager: Ref<boolean> = ref(false);
const hasRolePartnerApi: Ref<boolean> = ref(false);

const kanrenshaRole: Ref<string> = ref("");
const disabledKanrensha: Ref<boolean> = ref(false);

onBeforeMount(() => {

    const capsuleDto: GetUserDtoCapsuleDtoInterface = new GetUserDtoCapsuleDto();
    capsuleDto.editUserid = props.editUserId;

    // 取得実行
    getAuthorizedPromiseArea().then(token => {
        const url = urlBack + "/edit-user/get";
        const method = "POST";
        const body = JSON.stringify(capsuleDto);
        const headers = {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'X-AUTH-TOKEN': 'Bearer ' + token
        };
        fetch(url, { method, headers, body })
            .then(async (response) => {
                const resultDto: GetUserDtoResultDtoInterface = await response.json();
                if (resultDto.isFailure) {
                    infoLevel.value = MessageConstants.LEVEL_ERROR;
                    messageType.value = MessageConstants.VIEW_OK;
                    title.value = "ユーザ取得処理";
                    message.value = resultDto.message;
                    return;
                } else {
                    editUserDto.value = resultDto.userDto;
                    // 利用者権限設定
                    if (editUserDto.value.listRoles.includes(UserRoleConstants.MANAGER)) {
                        hasRoleManager.value = true;
                    }
                    if (editUserDto.value.listRoles.includes(UserRoleConstants.PARTNER_API)) {
                        hasRolePartnerApi.value = true;
                    }
                    // 関連者者権限設定
                    if (editUserDto.value.listRoles.includes(UserRoleConstants.KANRENSHA_PERSON)) {
                        kanrenshaRole.value = UserRoleConstants.KANRENSHA_PERSON;
                    }
                    if (editUserDto.value.listRoles.includes(UserRoleConstants.KANRENSHA_KIGYOU_DT)) {
                        kanrenshaRole.value = UserRoleConstants.KANRENSHA_KIGYOU_DT;
                        disabledKanrensha.value = true;
                    }
                    if (editUserDto.value.listRoles.includes(UserRoleConstants.KANRENSHA_SEIJIDANTAI)) {
                        kanrenshaRole.value = UserRoleConstants.KANRENSHA_SEIJIDANTAI;
                        disabledKanrensha.value = true;
                    }
                }
            })
            .catch((e) => {
                if (e instanceof AccessTokenNotFoundError) {
                    infoLevel.value = MessageConstants.LEVEL_ERROR;
                    // トークン保持ができていない場合
                    messageType.value = MessageConstants.VIEW_OK;
                    title.value = "現在トークンが存在しません";
                    message.value = e.message;
                    return;
                }
                if (e instanceof TokenRefreshError) {
                    // 取得に失敗している場合
                    infoLevel.value = MessageConstants.LEVEL_ERROR;
                    messageType.value = MessageConstants.VIEW_OK;
                    title.value = "有効期限まじかのトークンを再取得できませんでした";
                    message.value = e.message;
                    return;
                }
                infoLevel.value = MessageConstants.LEVEL_ERROR;
                messageType.value = MessageConstants.VIEW_OK;
                title.value = "システムエラーが発生しました";
                message.value = "システム管理者にお問い合わせください";
            });
    });


});


function onSave() {

    const capsuleDto: FrameworkCapsuleDtoInterface = new FrameworkCapsuleDto();
    capsuleDto.userDto = editUserDto.value;

    // 更新実行
    getAuthorizedPromiseArea().then(token => {
        const url = urlBack + "/edit-user/change";
        const method = "POST";
        const body = JSON.stringify(capsuleDto);
        const headers = {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'X-AUTH-TOKEN': 'Bearer ' + token
        };
        fetch(url, { method, headers, body })
            .then(async (response) => {
                const resultDto: GetUserDtoResultDtoInterface = await response.json();
                if (resultDto.isFailure) {
                    title.value = "更新処理失敗";
                    infoLevel.value = MessageConstants.LEVEL_ERROR;
                    messageType.value = MessageConstants.VIEW_OK;
                    message.value = resultDto.message;
                    return;
                } else {
                    title.value = "ユーザ更新処理";
                    infoLevel.value = MessageConstants.LEVEL_INFO;
                    messageType.value = MessageConstants.VIEW_TOAST;
                    message.value = "ユーザ更新処理が正常にできました";
                }
            })
            .catch((e) => {
                if (e instanceof AccessTokenNotFoundError) {
                    infoLevel.value = MessageConstants.LEVEL_ERROR;
                    // トークン保持ができていない場合
                    messageType.value = MessageConstants.VIEW_OK;
                    title.value = "現在トークンが存在しません";
                    message.value = e.message;
                    return;
                }
                if (e instanceof TokenRefreshError) {
                    // 取得に失敗している場合
                    infoLevel.value = MessageConstants.LEVEL_ERROR;
                    messageType.value = MessageConstants.VIEW_OK;
                    title.value = "有効期限まじかのトークンを再取得できませんでした";
                    message.value = e.message;
                    return;
                }
                infoLevel.value = MessageConstants.LEVEL_ERROR;
                messageType.value = MessageConstants.VIEW_OK;
                title.value = "システムエラーが発生しました";
                message.value = "システム管理者にお問い合わせください";
            });
    });
}

function onCancel() {
    emits("sendCancelEditUser");
}

function recieveSubmit(button: string) {

    if (button === "ユーザ更新処理") {
        emits("sendEditUserInterface");
    }
    infoLevel.value = 0;
    messageType.value = 0;
}
</script>
<template>
    <h3>ユーザ情報編集</h3>

    <div class="one-line">
        <div class="left-area">
            識別コード
        </div>
        <div class="right-area">
            {{ editUserDto.userPersonCode }}
        </div>
    </div>

    <div class="one-line">
        <div class="left-area">
            ユーザ名
        </div>
        <div class="right-area">
            <input type="text" v-model="editUserDto.userPersonName">
        </div>
    </div>

    <div class="one-line">
        <div class="left-area">
            利用者権限
        </div>
        <div class="right-area">
            <input type="checkbox" v-model="hasRoleManager">運営者権限
            <input type="checkbox" v-model="hasRolePartnerApi" class="left-space">APIパートナー
        </div>
    </div>

    <div class="one-line">
        <div class="left-area">
            関連者
        </div>
        <div class="right-area">
            <input type="radio" id="role" v-model="kanrenshaRole" :value=UserRoleConstants.NONE
                :disabled="disabledKanrensha">なし
            <input type="radio" id="role" v-model="kanrenshaRole" :value=UserRoleConstants.KANRENSHA_PERSON
                class="left-space" :disabled="disabledKanrensha">個人
            <input type="radio" id="role" v-model="kanrenshaRole" :value=UserRoleConstants.KANRENSHA_KIGYOU_DT
                class="left-space" disabled="true">企業／団体
            <input type="radio" id="role" v-model="kanrenshaRole" :value=UserRoleConstants.KANRENSHA_SEIJIDANTAI
                class="left-space" disabled="true">政治団体
        </div>
    </div>

    <div class="footer">
        <button @click="onCancel" class="footer-button">キャンセル</button>
        <button @click="onSave" class="left-space footer-button">保存</button>
    </div>

    <!-- メッセージ表示 -->
    <div class="overMessage" v-if="messageType !== MessageConstants.VIEW_NONE">
        <MessageView :info-level="infoLevel" :message-type="messageType" :title="title" :message="message"
            @send-submit="recieveSubmit">
        </MessageView>
    </div>

</template>
<style scoped></style>
