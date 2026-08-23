<script setup lang="ts">
import { onBeforeMount, ref, type Ref } from 'vue';
import UserRoleConstants from '../../dto/user/userRoleConstants';
import { getErrorMessage, MessageConstants, MessageView, type LeastUserDtoInterface } from 'seijishikin-jp-normalize_common-tool';
import getAuthorizedPromiseArea from '../../dto/login/getAuthorizedPromiseArea';
import RoutePathConstants from '../../../../routePathConstants';
import { GetUserDtoCapsuleDto, type GetUserDtoCapsuleDtoInterface } from '../../dto/user/getUserDtoCapsuleDto';
import type { GetUserDtoResultDtoInterface } from '../../dto/user/getUserDtoResultDto';
import { AccessTokenNotFoundError, TokenRefreshError } from '../../dto/login/errors';
import { EditUserPersonCapsuleDto, type EditUserPersonCapsuleDtoInterface } from '../../dto/user/editUserPersonCapsuleDto';

// props,emits
const props = defineProps<{ editUserId: number, userDto: LeastUserDtoInterface }>()
const emits = defineEmits(["sendCancelEditUser", "sendEditUserInterface"]);

// よく使う定数
const BLANK: string = "";
const INIT_NUMBER: number = 0;
const SERVER_STATUS_OK: number = 200;
// const SERVER_STATUS_ERROR: number = 400;
const INQUIRE_FLG: boolean = false;
const ERR_MESS_ONLY: boolean = true;
const MESS_PAGE_NAME: string = "ユーザ詳細編集";
const INIT_CALLER: string = "no branch";

// メッセージボックス表示定数
const infoLevel: Ref<number> = ref(MessageConstants.LEVEL_NONE);
const messageType: Ref<number> = ref(MessageConstants.VIEW_NONE);
const caller: Ref<string> = ref(INIT_CALLER);
const message: Ref<string> = ref(BLANK);

// back側アクセス
const urlBack: string = RoutePathConstants.DOMAIN + RoutePathConstants.BASE_PATH;

// 表示用変数
const editUserDto: Ref<EditUserPersonCapsuleDtoInterface> = ref(new EditUserPersonCapsuleDto());
const hasRoleManager: Ref<boolean> = ref(false);
const hasRolePartnerApi: Ref<boolean> = ref(false);

const kanrenshaRole: Ref<string> = ref("");
const disabledKanrensha: Ref<boolean> = ref(false);
const disabledRiyousha: Ref<boolean> = ref(false);

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
                    message.value = resultDto.message;
                    return;
                    return;
                } else {
                    editUserDto.value.userDto = resultDto.userDto;
                    editUserDto.value.isAlertTaskStart = resultDto.isAlertTaskStart;
                    editUserDto.value.isAlertTaskEnd = resultDto.isAlertTaskEnd;
                    // 利用者権限設定
                    if (editUserDto.value.userDto.listRoles.includes(UserRoleConstants.MANAGER)) {
                        hasRoleManager.value = true;
                    }
                    if (editUserDto.value.userDto.listRoles.includes(UserRoleConstants.PARTNER_API)) {
                        hasRolePartnerApi.value = true;
                    }
                    // 関連者者権限設定
                    if (editUserDto.value.userDto.listRoles.includes(UserRoleConstants.KANRENSHA_PERSON)) {
                        kanrenshaRole.value = UserRoleConstants.KANRENSHA_PERSON;
                    }
                    if (editUserDto.value.userDto.listRoles.includes(UserRoleConstants.KANRENSHA_KIGYOU_DT)) {
                        kanrenshaRole.value = UserRoleConstants.KANRENSHA_KIGYOU_DT;
                        disabledKanrensha.value = true;
                        disabledRiyousha.value = true;
                    }
                    if (editUserDto.value.userDto.listRoles.includes(UserRoleConstants.KANRENSHA_SEIJIDANTAI)) {
                        kanrenshaRole.value = UserRoleConstants.KANRENSHA_SEIJIDANTAI;
                        disabledKanrensha.value = true;
                        disabledRiyousha.value = true;
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
});

let actionStatus: number = INIT_NUMBER;
function onSave() {
    // すべての権限を外すのは許可しない
    if (!hasRoleManager.value && !hasRolePartnerApi.value && kanrenshaRole.value === UserRoleConstants.NONE) {
        infoLevel.value = MessageConstants.LEVEL_ERROR;
        messageType.value = MessageConstants.VIEW_OK;
        message.value = "すべての権限をなくす場合は、アイコンをクリックして個人メニューを出し、退会処理をしてください";
        return;
    }

    // // 更新実行
    // getAuthorizedPromiseArea().then(token => {
    //     const url = urlBack + "/edit-user/change";
    //     const method = "POST";
    //     const body = JSON.stringify(editUserDto.value);
    //     const headers = {
    //         'Accept': 'application/json',
    //         'Content-Type': 'application/json',
    //         'X-AUTH-TOKEN': 'Bearer ' + token
    //     };
    //     fetch(url, { method, headers, body })
    //         .then(async (response) => {
    //             const resultDto: GetUserDtoResultDtoInterface = await response.json();
    //             if (resultDto.isFailure) {
    //                 title.value = "更新処理失敗";
    //                 infoLevel.value = MessageConstants.LEVEL_ERROR;
    //                 messageType.value = MessageConstants.VIEW_OK;
    //                 message.value = resultDto.message;
    //                 return;
    //             } else {
    //                 title.value = "ユーザ更新処理";
    //                 infoLevel.value = MessageConstants.LEVEL_INFO;
    //                 messageType.value = MessageConstants.VIEW_TOAST;
    //                 message.value = "ユーザ更新処理が正常にできました";
    //                 actionStatus = SERVER_STATUS_OK;
    //                 return;
    //             }
    //         })
    //         .catch((e) => {
    //             if (e instanceof AccessTokenNotFoundError) {
    //                 infoLevel.value = MessageConstants.LEVEL_ERROR;
    //                 // トークン保持ができていない場合
    //                 messageType.value = MessageConstants.VIEW_OK;
    //                 title.value = "現在トークンが存在しません";
    //                 message.value = e.message;
    //                 return;
    //             }
    //             if (e instanceof TokenRefreshError) {
    //                 // 取得に失敗している場合
    //                 infoLevel.value = MessageConstants.LEVEL_ERROR;
    //                 messageType.value = MessageConstants.VIEW_OK;
    //                 title.value = "有効期限まじかのトークンを再取得できませんでした";
    //                 message.value = e.message;
    //                 return;
    //             }
    //             infoLevel.value = MessageConstants.LEVEL_ERROR;
    //             messageType.value = MessageConstants.VIEW_OK;
    //             title.value = "システムエラーが発生しました";
    //             message.value = "システム管理者にお問い合わせください";
    //             return;
    //         });
    // });
}

function onCancel() {
    emits("sendCancelEditUser");
}

function recieveSubmit() {
    if (SERVER_STATUS_OK === actionStatus) {
        emits("sendEditUserInterface");
    }
    infoLevel.value = 0;
    messageType.value = 0;
    actionStatus = INIT_NUMBER;
}
</script>
<template>
    <h3>ユーザ情報編集</h3>

    <div class="one-line">
        <div class="left-area">
            識別コード
        </div>
        <div class="right-area">
            {{ editUserDto.userDto.userPersonCode }}
        </div>
    </div>

    <div class="one-line">
        <div class="left-area">
            ユーザ名
        </div>
        <div class="right-area">
            <input type="text" v-model="editUserDto.userDto.userPersonName">
        </div>
    </div>
    <div class="one-line" v-if="disabledRiyousha">
        <div class="left-area">
            権限変更制限
        </div>
        <div class="right-area">
            関連者企業団体と関連者政治団体は権限の変更ができません。<br>
            利用者資格を追加したい場合は、別途個人で必要なアカウントを作成してください。
        </div>
    </div>
    <div class="one-line">
        <div class="left-area">
            利用者権限
        </div>
        <div class="right-area">
            <input type="checkbox" v-model="hasRoleManager" :disabled="disabledRiyousha">運営者権限
            <input type="checkbox" v-model="hasRolePartnerApi" :disabled="disabledRiyousha" class="left-space">APIパートナー
        </div>
    </div>

    <div class="one-line">
        <div class="left-area">
            関連者権限
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

    <div class="one-line">
        <div class="left-area">
            連続処理通知
        </div>
        <div class="right-area">
            <div class="form-group-vertical">
                <div><input type="checkbox" v-model="editUserDto.isAlertTaskStart">タスク開始通知を送信する</input></div>
                <div><input type="checkbox" v-model="editUserDto.isAlertTaskEnd">タスク終了通知を送信する</input></div>
            </div>
        </div>
    </div>

    <div class="footer">
        <button @click="onCancel" class="footer-button">キャンセル</button>
        <button @click="onSave" class="left-space footer-button">保存</button>
    </div>

    <!-- メッセージ表示    -->
    <div class="overMessage" v-if="messageType !== MessageConstants.VIEW_NONE">
        <MessageView :info-level="infoLevel" :message-type="messageType" :title="MESS_PAGE_NAME" :message="message"
            :caller="caller" @send-submit="recieveSubmit">
        </MessageView>
    </div>

</template>
<style scoped></style>
