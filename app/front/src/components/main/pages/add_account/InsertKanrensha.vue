<script setup lang="ts">
import { computed, onMounted, ref, type ComputedRef, type Ref } from 'vue';
import { useUserInfoStore } from '../../stores/storeUserInfo';
import { getActivePinia } from 'pinia';
import { getErrorMessage, KanrenshaKigyouDtMasterEntity, KanrenshaPersonMasterEntity, KanrenshaSeijidantaiMasterEntity, MessageConstants, MessageView, useUserInfoStoreCommon, type FrameworkMessageAndResultDtoInterface, type KanrenshaKigyouDtMasterEntityInterface, type KanrenshaPersonMasterEntityInterface, type KanrenshaSeijidantaiMasterEntityInterface, type LeastUserDtoInterface } from 'seijishikin-jp-normalize_common-tool';
import { getLoginUser } from '../../utils/getLoginUser';
import type { KanrenshaKigyouDtDtoInterface } from '../../dto/kanrensha/kanrenshaKigyouDtDto';
import getAuthorizedPromiseArea from '../../dto/login/getAuthorizedPromiseArea';
import { AccessTokenNotFoundError, TokenRefreshError } from '../../dto/login/errors';
import type { KanrenshaSeijidantaiDtoInterface } from '../../dto/kanrensha/kanrenshaSeijidantaiDto';
import type { KanrenshaPersonDtoInterface } from '../../dto/kanrensha/kanrenshaPersonDto';
import { SaveKanrenshaKigyouDtCapsuleDto, type SaveKanrenshaKigyouDtCapsuleDtoInterface } from '../../dto/kanrensha/saveKanrenshaKigyouDtCapsuleDto';
import { SaveKanrenshaPersonCapsuleDto, type SaveKanrenshaPersonCapsuleDtoInterface } from '../../dto/kanrensha/saveKanrenshaPersonCapsuleDto';
import { SaveKanrenshaSeijidantaiCapsuleDto, type SaveKanrenshaSeijidantaiCapsuleDtoInterface } from '../../dto/kanrensha/saveKanrenshaSeijidantaiCapsuleDto';
import RoutePathConstants from '../../../../routePathConstants';
import UserRoleConstants from '../../dto/user/userRoleConstants';
import KanrenshaInfo from '../../common/user_info/KanrenshaInfo.vue';
import KanrenshaKigyouDtEdit from '../../common/kanrensha_edit/KanrenshaKigyouDtEdit.vue';
import KanrenshaPersonEdit from '../../common/kanrensha_edit/KanrenshaPersonEdit.vue';
import KanrenshaSeijidanatiEdit from '../../common/kanrensha_edit/KanrenshaSeijidanatiEdit.vue';
import router from '../../../../router';

// よく使う定数
const BLANK: string = "";
// const INIT_NUMBER: number = 0;
const INIT_BOOLEAN: boolean = false;
// const SERVER_STATUS_OK: number = 200;
// const SERVER_STATUS_ERROR: number = 400;
const INQUIRE_FLG: boolean = false;
const ERR_MESS_ONLY: boolean = true;
const MESS_PAGE_NAME: string = "関連者個人・企業団体・政治団体追加";
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

onMounted(() => {
    // 保存していたアクセストークンと有効期限を取得
    const userInfo = useUserInfoStore();

    // common-toolにアクセス情報を渡す
    const userInfoCommon = useUserInfoStoreCommon(getActivePinia());
    userInfoCommon.jwtDto = userInfo.jwtDto;
    userInfoCommon.userDto = userInfo.userDto;
});

// 編集Dto
const editEntityKigyouDt: Ref<KanrenshaKigyouDtMasterEntityInterface> = ref(new KanrenshaKigyouDtMasterEntity());
const editEntityPerson: Ref<KanrenshaPersonMasterEntityInterface> = ref(new KanrenshaPersonMasterEntity());
const editEntitySeijidantai: Ref<KanrenshaSeijidantaiMasterEntityInterface> = ref(new KanrenshaSeijidantaiMasterEntity());

// 関連者は重複することがないので複数入力画面が出ることはない
const isEditKigyouDt: ComputedRef<boolean> = computed(() => {
    return userDto.value.listRoles.includes(UserRoleConstants.ROLE_KANRENSHA_KIGYOU_DT);
});
const isEditPerson: ComputedRef<boolean> = computed(() => {
    return userDto.value.listRoles.includes(UserRoleConstants.ROLE_KANRENSHA_PERSON);
});
const isEditSeijidantai: ComputedRef<boolean> = computed(() => {
    return userDto.value.listRoles.includes(UserRoleConstants.ROLE_KANRENSHA_SEIJIDANTAI);
});

function recieveCancelKigyouDtEdit() {
    history.back();
}

let isSuccessUpdate: boolean = INIT_BOOLEAN;

function recieveKigyouDtInterfaceEdit(editDto: KanrenshaKigyouDtDtoInterface) {

    editDto.isCombineUser = true; // 初期登録時は紐づけ処理する

    const capsuleDto: SaveKanrenshaKigyouDtCapsuleDtoInterface = new SaveKanrenshaKigyouDtCapsuleDto();
    capsuleDto.userDto = userDto.value;
    capsuleDto.kanrenshaKigyouDtDto = editDto;

    getAuthorizedPromiseArea().then(token => {
        const url = urlBack + "/user-kanrensha/add-kigyou-dt";
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
                // 処理が成功したら再登録できないようにアップロードファイル情報を初期化
                if (resultDto.isFailure) {
                    infoLevel.value = MessageConstants.LEVEL_WARNING;
                    messageType.value = MessageConstants.VIEW_OK;
                    return;
                } else {
                    infoLevel.value = MessageConstants.LEVEL_INFO;
                    messageType.value = MessageConstants.VIEW_TOAST;
                    isSuccessUpdate = true;
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

function recieveCancelPersonEdit() {
    history.back();
}

function recievePersonInterfaceEdit(editDto: KanrenshaPersonDtoInterface) {

    editDto.isCombineUser = true; // 初期登録時は紐づけ処理する

    const capsuleDto: SaveKanrenshaPersonCapsuleDtoInterface = new SaveKanrenshaPersonCapsuleDto();
    capsuleDto.userDto = userDto.value;
    capsuleDto.kanrenshaPersonDto = editDto;

    getAuthorizedPromiseArea().then(token => {
        const url = urlBack + "/user-kanrensha/add-person";
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
                // 処理が成功したら再登録できないようにアップロードファイル情報を初期化
                if (resultDto.isFailure) {
                    infoLevel.value = MessageConstants.LEVEL_WARNING;
                    messageType.value = MessageConstants.VIEW_OK;
                } else {
                    infoLevel.value = MessageConstants.LEVEL_INFO;
                    messageType.value = MessageConstants.VIEW_TOAST;
                    isSuccessUpdate = true;
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

function recieveCancelSeijidantaiEdit() {
    history.back();
}

function recieveSeijidantaiInterfaceEdit(editDto: KanrenshaSeijidantaiDtoInterface) {

    editDto.isCombineUser = true; // 初期登録時は紐づけ処理する

    const capsuleDto: SaveKanrenshaSeijidantaiCapsuleDtoInterface = new SaveKanrenshaSeijidantaiCapsuleDto();
    capsuleDto.userDto = userDto.value;
    capsuleDto.kanrenshaSeijidantaiDto = editDto;

    getAuthorizedPromiseArea().then(token => {
        const url = urlBack + "/user-kanrensha/add-seijidantai";
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
                // 処理が成功したら再登録できないようにアップロードファイル情報を初期化
                if (resultDto.isFailure) {
                    infoLevel.value = MessageConstants.LEVEL_WARNING;
                    messageType.value = MessageConstants.VIEW_OK;
                    return;
                } else {
                    infoLevel.value = MessageConstants.LEVEL_INFO;
                    messageType.value = MessageConstants.VIEW_TOAST;
                    isSuccessUpdate = true;
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

function recieveSubmit() {
    infoLevel.value = 0;
    messageType.value = 0;
    if (isSuccessUpdate) {
        router.push(RoutePathConstants.PAGE_MENU_KANRENSHA);
    }
}
</script>
<template>

    <!-- 関連者 -->
    <KanrenshaInfo :user-dto="userDto"></KanrenshaInfo>

    <h1>関連者新規追加</h1>

    <!-- 企業・団体編集 -->
    <div v-if="isEditKigyouDt">
        <KanrenshaKigyouDtEdit :user-dto="userDto" :edit-entity="editEntityKigyouDt"
            @send-kigyou-dt-interface="recieveKigyouDtInterfaceEdit" @send-cancel-kigyou-dt="recieveCancelKigyouDtEdit">
        </KanrenshaKigyouDtEdit>
    </div>

    <!-- 個人編集 -->
    <div v-if="isEditPerson">
        <KanrenshaPersonEdit :user-dto="userDto" :edit-entity="editEntityPerson"
            @send-person-interface="recievePersonInterfaceEdit" @send-cancel-person="recieveCancelPersonEdit">
        </KanrenshaPersonEdit>
    </div>

    <!-- 政治団体編集 -->
    <div v-if="isEditSeijidantai">
        <KanrenshaSeijidanatiEdit :user-dto="userDto" :edit-entity="editEntitySeijidantai"
            @send-seijidantai-interface="recieveSeijidantaiInterfaceEdit"
            @send-cancel-seijidantai="recieveCancelSeijidantaiEdit"></KanrenshaSeijidanatiEdit>
    </div>

    <!-- メッセージ表示    -->
    <div class="overMessage" v-if="messageType !== MessageConstants.VIEW_NONE">
        <MessageView :info-level="infoLevel" :message-type="messageType" :title="MESS_PAGE_NAME" :message="message"
            :caller="caller" @send-submit="recieveSubmit">
        </MessageView>
    </div>

</template>
<style scoped></style>
