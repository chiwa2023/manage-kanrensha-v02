<script setup lang="ts">
import { computed, onMounted, ref, type ComputedRef, type Ref } from 'vue';
import RoutePathConstants from '../../../../routePathConstants';
import {
    getErrorMessage,
    KanrenshaKigyouDtMasterEntity, KanrenshaPersonMasterEntity, KanrenshaSeijidantaiMasterEntity,
    MessageConstants, MessageView, useUserInfoStoreCommon, type FrameworkMessageAndResultDtoInterface,
    type KanrenshaKigyouDtMasterEntityInterface, type KanrenshaPersonMasterEntityInterface,
    type KanrenshaSeijidantaiMasterEntityInterface, type LeastUserDtoInterface
} from 'seijishikin-jp-normalize_common-tool';
import { getLoginUser } from '../../utils/getLoginUser';
import KanrenshaKigyouDtEdit from '../../common/kanrensha_edit/KanrenshaKigyouDtEdit.vue';
import KanrenshaPersonEdit from '../../common/kanrensha_edit/KanrenshaPersonEdit.vue';
import KanrenshaSeijidanatiEdit from '../../common/kanrensha_edit/KanrenshaSeijidanatiEdit.vue';
import getAuthorizedPromiseArea from '../../dto/login/getAuthorizedPromiseArea';
import { AccessTokenNotFoundError, TokenRefreshError } from '../../dto/login/errors';
import { SaveKanrenshaPersonCapsuleDto, type SaveKanrenshaPersonCapsuleDtoInterface } from '../../dto/kanrensha/saveKanrenshaPersonCapsuleDto';
import { type KanrenshaKigyouDtDtoInterface } from '../../dto/kanrensha/kanrenshaKigyouDtDto';
import { SaveKanrenshaKigyouDtCapsuleDto, type SaveKanrenshaKigyouDtCapsuleDtoInterface } from '../../dto/kanrensha/saveKanrenshaKigyouDtCapsuleDto';
import type { KanrenshaPersonDtoInterface } from '../../dto/kanrensha/kanrenshaPersonDto';
import type { KanrenshaSeijidantaiDtoInterface } from '../../dto/kanrensha/kanrenshaSeijidantaiDto';
import { SaveKanrenshaSeijidantaiCapsuleDto, type SaveKanrenshaSeijidantaiCapsuleDtoInterface } from '../../dto/kanrensha/saveKanrenshaSeijidantaiCapsuleDto';
import { getActivePinia } from 'pinia';
import { useUserInfoStore } from '../../stores/storeUserInfo';
import KanrenshaInfo from '../../common/user_info/KanrenshaInfo.vue';
import UserRoleConstants from '../../dto/user/userRoleConstants';
import { GetKanrenshaMasterCapsuleDto, type GetKanrenshaMasterCapsuleDtoInterface } from '../../dto/kanrensha/getKanrenshaMasterCapsuleDto';
import type { GetKanrenshaMasterResultDtoInterface } from '../../dto/kanrensha/getKanrenshaMasterResultDto';

// よく使う定数
const BLANK: string = "";
// const SERVER_STATUS_OK: number = 200;
// const SERVER_STATUS_ERROR: number = 400;
const INQUIRE_FLG: boolean = false;
const ERR_MESS_ONLY: boolean = true;
const MESS_PAGE_NAME: string = "関連者更新";
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


const nowRoleStatus: Ref<string> = ref(BLANK);
onMounted(() => {
    // 保存していたアクセストークンと有効期限を取得
    const userInfo = useUserInfoStore();

    // common-toolにアクセス情報を渡す
    const userInfoCommon = useUserInfoStoreCommon(getActivePinia());
    userInfoCommon.jwtDto = userInfo.jwtDto;
    userInfoCommon.userDto = userInfo.userDto;

    // 編集するroleを確定
    nowRoleStatus.value = pickupRole();
    switch (nowRoleStatus.value) {
        case UserRoleConstants.KANRENSHA_PERSON:
            break;
        case UserRoleConstants.KANRENSHA_KIGYOU_DT:
            break;
        case UserRoleConstants.KANRENSHA_SEIJIDANTAI:
            break;
    }


    if (BLANK !== userDto.value.kanrenshaCode) {
        const capsuleDto: GetKanrenshaMasterCapsuleDtoInterface = new GetKanrenshaMasterCapsuleDto();
        capsuleDto.kanrenshaRole = userDto.value.kanrenshaRole;
        capsuleDto.kanrenshaCode = userDto.value.kanrenshaCode;

        getAuthorizedPromiseArea().then(token => {
            const url = urlBack + "/user-kanrensha/get-myself";
            const method = "POST";
            const body = JSON.stringify(capsuleDto);
            const headers = {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
                'X-AUTH-TOKEN': 'Bearer ' + token
            };
            fetch(url, { method, headers, body })
                .then(async (response) => {
                    const resultDto: GetKanrenshaMasterResultDtoInterface = await response.json();
                    message.value = resultDto.message;
                    // 処理が成功したら再登録できないようにアップロードファイル情報を初期化
                    if (resultDto.isFailure) {
                        infoLevel.value = MessageConstants.LEVEL_WARNING;
                        messageType.value = MessageConstants.VIEW_OK;
                    } else {
                        // editEntityを変更すると自動で呼び出し
                        if (UserRoleConstants.KANRENSHA_PERSON == userDto.value.kanrenshaRole) {
                            editEntityPerson.value = resultDto.masterPersonEntity;
                        }
                        if (UserRoleConstants.KANRENSHA_KIGYOU_DT == userDto.value.kanrenshaRole) {
                            editEntityKigyouDt.value = resultDto.masterKigyouDtEntity;
                        }
                        if (UserRoleConstants.KANRENSHA_SEIJIDANTAI == userDto.value.kanrenshaRole) {
                            editEntitySeijidantai.value = resultDto.masterSeijidantaiEntity;
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
    }

});

// コンポーネント表示制御
const isEditKigyouDt: ComputedRef<boolean> = computed(() => {
    return UserRoleConstants.ROLE_KANRENSHA_KIGYOU_DT == nowRoleStatus.value;
});
const isEditPerson: ComputedRef<boolean> = computed(() => {
    return UserRoleConstants.ROLE_KANRENSHA_PERSON == nowRoleStatus.value;
});
const isEditSeijidantai: ComputedRef<boolean> = computed(() => {
    return UserRoleConstants.ROLE_KANRENSHA_SEIJIDANTAI == nowRoleStatus.value;
});

// 
const editEntityKigyouDt: Ref<KanrenshaKigyouDtMasterEntityInterface> = ref(new KanrenshaKigyouDtMasterEntity());
const editEntityPerson: Ref<KanrenshaPersonMasterEntityInterface> = ref(new KanrenshaPersonMasterEntity());
const editEntitySeijidantai: Ref<KanrenshaSeijidantaiMasterEntityInterface> = ref(new KanrenshaSeijidantaiMasterEntity());

function recieveCancelKigyouDtEdit() {
    history.back();
}

const isNewData: ComputedRef<boolean> = computed(() => {
    if (UserRoleConstants.KANRENSHA_PERSON == userDto.value.kanrenshaRole) {
        return 0 == editEntityPerson.value.kanrenshaPersonMasterId;
    }
    if (UserRoleConstants.KANRENSHA_KIGYOU_DT == userDto.value.kanrenshaRole) {
        return 0 == editEntityKigyouDt.value.kanrenshaKigyouDtMasterId;
    }
    if (UserRoleConstants.KANRENSHA_SEIJIDANTAI == userDto.value.kanrenshaRole) {
        return 0 == editEntitySeijidantai.value.kanrenshaSeijidantaiMasterId;
    }
    return false;
});


function recieveKigyouDtInterfaceEdit(editDto: KanrenshaKigyouDtDtoInterface) {

    const capsuleDto: SaveKanrenshaKigyouDtCapsuleDtoInterface = new SaveKanrenshaKigyouDtCapsuleDto();
    capsuleDto.userDto = userDto.value;
    capsuleDto.kanrenshaKigyouDtDto = editDto;

    // 新規と編集で渡す先だけ変える(渡すものは同じ)
    let url = BLANK;
    if (isNewData.value) {
        url = urlBack + "/user-kanrensha/add-kigyou-dt";
        editDto.isCombineUser = true; // 紐づけ処理あり
    } else {
        url = urlBack + "/user-kanrensha/edit-kigyou-dt";
    }

    getAuthorizedPromiseArea().then(token => {
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

    const capsuleDto: SaveKanrenshaPersonCapsuleDtoInterface = new SaveKanrenshaPersonCapsuleDto();
    capsuleDto.userDto = userDto.value;
    capsuleDto.kanrenshaPersonDto = editDto;

    // 新規と編集で渡す先だけ変える(渡すものは同じ)
    let url = BLANK;
    if (isNewData.value) {
        url = urlBack + "/user-kanrensha/add-person";
        editDto.isCombineUser = true; // 紐づけ処理あり
    } else {
        url = urlBack + "/user-kanrensha/add-person";
    }

    getAuthorizedPromiseArea().then(token => {
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

    const capsuleDto: SaveKanrenshaSeijidantaiCapsuleDtoInterface = new SaveKanrenshaSeijidantaiCapsuleDto();
    capsuleDto.userDto = userDto.value;
    capsuleDto.kanrenshaSeijidantaiDto = editDto;

    // 新規と編集で渡す先だけ変える(渡すものは同じ)
    let url = BLANK;
    if (isNewData.value) {
        url = urlBack + "/user-kanrensha/add-seijidantai";
        editDto.isCombineUser = true; // 紐づけ処理あり
    } else {
        url = urlBack + "/user-kanrensha/edit-seijidantai";
    }

    getAuthorizedPromiseArea().then(token => {
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

function pickupRole(): string {
    // 関連者は一人1資格なのでこの実装でOK
    // (個人が出したいのに政治団体が出てしまう、ということはない)
    for (const roleString of userDto.value.listRoles) {
        if (roleString.startsWith("ROLE_kanrensha_")) {
            return roleString;
        }
    }

    return BLANK; // 非ログイン状態でないとここには来ない
}

function recieveSubmit() {
    infoLevel.value = 0;
    messageType.value = 0;
}
</script>
<template>

    <!-- 関連者 -->
    <KanrenshaInfo :user-dto="userDto"></KanrenshaInfo>

    <h1>関連者自分自身編集({{ UserRoleConstants.getLabel(nowRoleStatus) }})</h1>

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
