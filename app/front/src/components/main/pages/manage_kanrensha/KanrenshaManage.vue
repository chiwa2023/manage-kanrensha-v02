<script setup lang="ts">
import { computed, onMounted, ref, watch, type ComputedRef, type Ref } from 'vue';
import RoutePathConstants from '../../../../routePathConstants';
import {
    KanrenshaKigyouDtMasterEntity, KanrenshaPersonMasterEntity, KanrenshaSeijidantaiMasterEntity,
    MessageConstants, MessageView, SearchKanrenshaKigyouDt, SearchKanrenshaPerson,
    SearchKanrenshaSeijidantai, useUserInfoStoreCommon, type FrameworkMessageAndResultDtoInterface,
    type KanrenshaKigyouDtMasterEntityInterface, type KanrenshaPersonMasterEntityInterface,
    type KanrenshaSeijidantaiMasterEntityInterface, type LeastUserDtoInterface
} from 'seijishikin-jp-normalize_common-tool';
import { getLoginUser } from '../../utils/getLoginUser';
import ManagerInfo from '../../common/user_info/ManagerInfo.vue';
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

// よく使う定数
const BLANK: string = "";
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

// 表示
const viewStatus: Ref<number> = ref(1);
const isNewData: Ref<number> = ref(1);

onMounted(() => {
    // 保存していたアクセストークンと有効期限を取得
    const userInfo = useUserInfoStore();

    // common-toolにアクセス情報を渡す
    const userInfoCommon = useUserInfoStoreCommon(getActivePinia());
    userInfoCommon.jwtDto = userInfo.jwtDto;
    userInfoCommon.userDto = userInfo.userDto;
});

watch(isNewData, (newValue) => {
    if (2 == newValue) {
        editEntityKigyouDt.value = new KanrenshaKigyouDtMasterEntity();
        editEntityPerson.value = new KanrenshaPersonMasterEntity();
        editEntitySeijidantai.value = new KanrenshaSeijidantaiMasterEntity();
    }
});

// コンポーネント表示制御
const isSearchKigyouDt: ComputedRef<boolean> = computed(() => {
    return 1 == isNewData.value && viewStatus.value == 2;
});

const isSearchPerson: ComputedRef<boolean> = computed(() => {
    return 1 == isNewData.value && viewStatus.value == 1;
});

const isSearchSejidantai: ComputedRef<boolean> = computed(() => {
    return 1 == isNewData.value && viewStatus.value == 3;
});

const isEditKigyouDt: ComputedRef<boolean> = computed(() => {
    return viewStatus.value == 2;
});
const isEditPerson: ComputedRef<boolean> = computed(() => {
    return viewStatus.value == 1;
});
const isEditSeijidantai: ComputedRef<boolean> = computed(() => {
    return viewStatus.value == 3;
});

const editEntityKigyouDt: Ref<KanrenshaKigyouDtMasterEntityInterface> = ref(new KanrenshaKigyouDtMasterEntity());
function recieveKigyouDtInterface(entity: KanrenshaKigyouDtMasterEntityInterface) {
    window.location.href = '#sectionEdit';
    editEntityKigyouDt.value = entity;
}

const editEntityPerson: Ref<KanrenshaPersonMasterEntityInterface> = ref(new KanrenshaPersonMasterEntity());
function recievePersonInterface(entity: KanrenshaPersonMasterEntityInterface) {
    window.location.href = "#sectionEdit";
    editEntityPerson.value = entity;
}

const editEntitySeijidantai: Ref<KanrenshaSeijidantaiMasterEntityInterface> = ref(new KanrenshaSeijidantaiMasterEntity());
function recieveSeijidantaiInterface(entity: KanrenshaSeijidantaiMasterEntityInterface) {
    window.location.href = "#sectionEdit";
    editEntitySeijidantai.value = entity;
}

function recieveCancelKigyouDtEdit() {
    history.back();
}

function recieveKigyouDtInterfaceEdit(editDto: KanrenshaKigyouDtDtoInterface) {

    // 新規と編集で渡す先だけ変える(渡すものは同じ)
    let url = BLANK;
    if (1 == isNewData.value) {
        url = urlBack + "/user-kanrensha/edit-kigyou-dt";
    } else {
        url = urlBack + "/user-kanrensha/add-kigyou-dt";
        editDto.isCombineUser = false; // 紐づけ処理なし
    }

    const capsuleDto: SaveKanrenshaKigyouDtCapsuleDtoInterface = new SaveKanrenshaKigyouDtCapsuleDto();
    capsuleDto.userDto = userDto.value;
    capsuleDto.kanrenshaKigyouDtDto = editDto;

    title.value = "関連者企業・団体編集";
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

function recieveCancelPersonEdit() {
    history.back();
}

function recievePersonInterfaceEdit(editDto: KanrenshaPersonDtoInterface) {

    // 新規と編集で渡す先だけ変える(渡すものは同じ)
    let url = BLANK;
    if (1 == isNewData.value) {
        url = urlBack + "/user-kanrensha/edit-person";
    } else {
        url = urlBack + "/user-kanrensha/add-person";
        editDto.isCombineUser = false; // 紐づけ処理なし
    }

    const capsuleDto: SaveKanrenshaPersonCapsuleDtoInterface = new SaveKanrenshaPersonCapsuleDto();
    capsuleDto.userDto = userDto.value;
    capsuleDto.kanrenshaPersonDto = editDto;

    title.value = "関連者個人編集";
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

function recieveCancelSeijidantaiEdit() {
    history.back();
}

function recieveSeijidantaiInterfaceEdit(editDto: KanrenshaSeijidantaiDtoInterface) {

    // 新規と編集で渡す先だけ変える(渡すものは同じ)
    let url = BLANK;
    if (1 == isNewData.value) {
        url = urlBack + "/user-kanrensha/edit-seijidantai";
    } else {
        url = urlBack + "/user-kanrensha/add-seijidantai";
        editDto.isCombineUser = false; // 紐づけ処理なし
    }

    const capsuleDto: SaveKanrenshaSeijidantaiCapsuleDtoInterface = new SaveKanrenshaSeijidantaiCapsuleDto();
    capsuleDto.userDto = userDto.value;
    capsuleDto.kanrenshaSeijidantaiDto = editDto;

    title.value = "関連者政治団体編集";
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

    <!-- 管理者メニュー兼チェック -->
    <ManagerInfo :user-dto="userDto"></ManagerInfo>

    <h1>関連者管理</h1>

    <div class="one-line">
        <div class="left-area">
            関連者区分
        </div>
        <div class="right-area">
            <span><input type="radio" id="editSelect" v-model="viewStatus" value="1">1.個人</span>
            <span class="left-space"><input type="radio" id="editSelect" v-model="viewStatus" value="2">2.企業／団体</span>
            <span class="left-space"><input type="radio" id="editSelect" v-model="viewStatus" value="3">3.政治団体</span>
        </div>
    </div>

    <div class="one-line">
        <div class="left-area">
            追加／編集
        </div>
        <div class="right-area">
            <span><input type="radio" id="isNew" v-model="isNewData" value="1">編集</span>
            <span class="left-space"><input type="radio" id="isNew" v-model="isNewData" value="2">新規</span>
        </div>
    </div>

    <hr>

    <!-- 個人検索 -->
    <SearchKanrenshaPerson v-if="isSearchPerson" @send-person-interface="recievePersonInterface"
        :is-raise-commponet="false">
    </SearchKanrenshaPerson>

    <!-- 企業団体検索 -->
    <SearchKanrenshaKigyouDt v-if="isSearchKigyouDt" @send-kigyou-dt-interface="recieveKigyouDtInterface"
        :is-raise-commponet="false">
    </SearchKanrenshaKigyouDt>

    <!-- 政治団体検索 -->
    <SearchKanrenshaSeijidantai v-if="isSearchSejidantai" @send-seijidantai-interface="recieveSeijidantaiInterface"
        :is-raise-commponet="false">
    </SearchKanrenshaSeijidantai>

    <h3 id="sectionEdit">編集</h3>
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

    <!-- メッセージ表示 -->
    <div class="overMessage" v-if="messageType !== MessageConstants.VIEW_NONE">
        <MessageView :info-level="infoLevel" :message-type="messageType" :title="title" :message="message"
            @send-submit="recieveSubmit">
        </MessageView>
    </div>

</template>
<style scoped></style>
