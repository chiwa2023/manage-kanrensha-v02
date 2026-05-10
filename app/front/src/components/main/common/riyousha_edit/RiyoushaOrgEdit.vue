<script setup lang="ts">
import {
    ViewInputAccess, ViewInputOrgName, type LeastUserDtoInterface, ViewInputAddress,
    MessageConstants,
    MessageView
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

//props,emit
const props = defineProps<{ userDto: LeastUserDtoInterface, selectedId: number }>();
const emits = defineEmits(["sendCancelRiyoushaOrg", "sendRiyoushaOrgInterface"]);

//仮
// よく使う定数
const BLANK: string = "";
// const INIT_NUMBER: number = 0;
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
                    // 処理が成功したら再登録できないようにアップロードファイル情報を初期化
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
    alert("保存");
    emits("sendRiyoushaOrgInterface", editDto.value);
}

function onDelete() {
    alert("削除");
}

function recieveSubmit(button: string) {
    console.log(button); // 警告除け
    infoLevel.value = 0;
    messageType.value = 0;
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
                    <td>({{ entity.personCode }})<br>{{ entity.personRiyoushaName }}</td>
                    <td><button @click="onDelete">削除</button></td>
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
