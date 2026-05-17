<script setup lang="ts">
import {
    ViewInputAccess, ViewInputOrgName, type LeastUserDtoInterface, ViewInputAddress,
    MessageConstants,
    MessageView,
    type FrameworkMessageAndResultDtoInterface
} from 'seijishikin-jp-normalize_common-tool';
import { onBeforeMount, ref, type Ref, watch } from 'vue';
// import mockGetOrgName from '../../../test/pages/regist_riyousha_org/mockGetOrgName';
// import mockGetAddress from '../../../test/pages/regist_riyousha_org/mockGetAddress';
// import mockGetAccess from '../../../test/pages/regist_riyousha_org/mockGetAccess';
//import mockGetRiyoushaCOmbinePersonList from '../../../test/common/riyousha/mockGetRiyoushaCombinePersonList';
import RoutePathConstants from '../../../../routePathConstants';
import { RiyoushaOrgDto, type RiyoushaOrgDtoInterface } from '../../dto/riyousha/riyoushaOrgDto';
import getAuthorizedPromiseArea from '../../dto/login/getAuthorizedPromiseArea';
import { AccessTokenNotFoundError, TokenRefreshError } from '../../dto/login/errors';
import { GetRiyoushaOrgByCodeCapsuleDto, type GetRiyoushaOrgByCodeCapsuleDtoInterface } from '../../dto/riyousha/getRiyoushaOrgByCodeCapsuleDto';
import UserRoleConstants from '../../dto/user/userRoleConstants';
import type { RiyoushaCombineOrgEntityInterface } from '../../entity/riyoushaCombineOrgEntity';
import { RiyoushaCombinePersonCapsuleDto, type RiyoushaCombinePersonCapsuleDtoInterface } from '../../dto/riyousha/riyoushaCombinePersonCapsuleDto';

//props,emit
const props = defineProps<{ userDto: LeastUserDtoInterface, selectedId: number }>();
const emits = defineEmits(["sendCancelRiyoushaOrg", "sendRiyoushaOrgInterface"]);

// よく使う定数
const BLANK: string = "";
const INIT_NUMBER: number = 0;
// const SERVER_STATUS_OK: number = 200;
// const SERVER_STATUS_ERROR: number = 400;
// const SEARCH_LIMIT: number = 20;

// メッセージボックス表示定数
const infoLevel: Ref<number> = ref(MessageConstants.LEVEL_NONE);
const messageType: Ref<number> = ref(MessageConstants.VIEW_NONE);
const title: Ref<string> = ref(BLANK);
const message: Ref<string> = ref(BLANK);

/// back側アクセス
const urlBack: string = RoutePathConstants.DOMAIN + RoutePathConstants.BASE_PATH;

// 登録情報
const editDto: Ref<RiyoushaOrgDtoInterface> = ref(new RiyoushaOrgDto());

onBeforeMount(() => {
    callData(props.selectedId);
});

watch(props, (newValue) => {
    callData(newValue.selectedId);
});

function callData(selectedId: number) {
    if (selectedId === 0) {
        editDto.value = new RiyoushaOrgDto();
    } else {
        const capsuleDto: GetRiyoushaOrgByCodeCapsuleDtoInterface = new GetRiyoushaOrgByCodeCapsuleDto();
        capsuleDto.selectedCode = props.selectedId;
        title.value = "利用者組織編集対象呼び出し";
        getAuthorizedPromiseArea().then(token => {
            const url = urlBack + "/riyousha-org/get-by-code";
            const method = "POST";
            const body = JSON.stringify(capsuleDto);
            const headers = {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
                'X-AUTH-TOKEN': 'Bearer ' + token
            };
            fetch(url, { method, headers, body })
                .then(async (response) => {
                    editDto.value = await response.json();
                    message.value = editDto.value.message;
                    if (editDto.value.isFailure) {
                        infoLevel.value = MessageConstants.LEVEL_WARNING;
                        messageType.value = MessageConstants.VIEW_OK;
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
}

function onCancel() {
    emits("sendCancelRiyoushaOrg");
}

function onSave() {
    emits("sendRiyoushaOrgInterface", editDto.value);
}

let combineDeleteId: number = INIT_NUMBER;
function onDelete(selectedId: number) {
    combineDeleteId = selectedId;

    title.value = "利用者組織所属削除";
    infoLevel.value = MessageConstants.LEVEL_WARNING;
    messageType.value = MessageConstants.VIEW_YES_NO;
    message.value = "削除すると戻すことができません。よろしいですか？";
}

function doDelete() {

    const deleteEntity: RiyoushaCombineOrgEntityInterface | undefined
        = editDto.value.listPersonCombine.filter((e) => e.riyoushaCombineOrgId === combineDeleteId)[0];

    if (undefined !== deleteEntity) {

        const capsuleDtoDelete: RiyoushaCombinePersonCapsuleDtoInterface = new RiyoushaCombinePersonCapsuleDto();
        capsuleDtoDelete.userDto = props.userDto
        capsuleDtoDelete.combineEntity = deleteEntity;

        title.value = "利用者組織所属削除";
        getAuthorizedPromiseArea().then(token => {
            const url = urlBack + "/riyousha-org/delete-person";
            const method = "POST";
            const body = JSON.stringify(capsuleDtoDelete);
            const headers = {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
                'X-AUTH-TOKEN': 'Bearer ' + token
            };
            fetch(url, { method, headers, body })
                .then(async (response) => {
                    const resultDto: FrameworkMessageAndResultDtoInterface = await response.json();
                    message.value = editDto.value.message;
                    if (resultDto.isFailure) {
                        infoLevel.value = MessageConstants.LEVEL_WARNING;
                        messageType.value = MessageConstants.VIEW_OK;
                    } else {
                        message.value = editDto.value.message;
                        infoLevel.value = MessageConstants.LEVEL_INFO;
                    }
                    // 削除が終わったら初期化
                    combineDeleteId = INIT_NUMBER;
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
}

function recieveSubmit(button: string) {
    if (combineDeleteId !== INIT_NUMBER && "yes" === button) {
        doDelete();
    }
    infoLevel.value = INIT_NUMBER;
    messageType.value = INIT_NUMBER;
}
</script>
<template>
    <h3>利用者組織編集</h3>

    <!-- 団体名入力 -->
    <ViewInputOrgName :edit-dto="editDto.inputOrgNameDto"></ViewInputOrgName>

    <!-- 住所入力 -->
    <ViewInputAddress :edit-dto="editDto.inputAddressDto"></ViewInputAddress>

    <!-- 連絡先入力 -->
    <ViewInputAccess :edit-dto="editDto.inputAccessDto"></ViewInputAccess>

    <h3>組織構成員リスト</h3>
    <div class="one-line">
        <RouterLink :to="RoutePathConstants.PAGE_INVITE_ORG_PERSON" class="menu-item">他人を組織に追加</RouterLink>
    </div>
    <div class="one-line-scroll">
        <table>
            <tbody>
                <tr>
                    <th>区分</th>
                    <th>名称</th>
                    <th>&nbsp;</th>
                </tr>
            </tbody>

            <tbody>
                <tr v-for="entity of editDto.listPersonCombine" :key="entity.riyoushaCombineOrgId">
                    <td>{{ UserRoleConstants.getLabel(entity.riyoushaRole) }}</td>
                    <td>({{ entity.personRiyoushaCode }})<br>{{ entity.personRiyoushaName }}</td>
                    <td><button @click="onDelete(entity.riyoushaCombineOrgId)">削除</button></td>
                </tr>
            </tbody>
        </table>

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
