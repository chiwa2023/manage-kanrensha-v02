<script setup lang="ts">
import { onMounted, ref, watch, type Ref } from 'vue';
import { getErrorMessage, MessageConstants, MessageView, ViewInputAccess, ViewInputAddress, ViewInputPersonName, type LeastUserDtoInterface, } from 'seijishikin-jp-normalize_common-tool';
import { RiyoushaPartnerApiDto, type RiyoushaPartnerApiDtoInterface } from '../../dto/riyousha/riyoushaPartnerApiDto';
import getAuthorizedPromiseArea from '../../dto/login/getAuthorizedPromiseArea';
import RoutePathConstants from '../../../../routePathConstants';
import { AccessTokenNotFoundError, TokenRefreshError } from '../../dto/login/errors';
import type { RiyoushaPartnerApiMasterEntityInterface } from '../../entity/riyoushaPartnerApiMasterEntity';
import { GetRiyoushaPartnerApiByEntityCapsuleDto, type GetRiyoushaPartnerApiByEntityCapsuleDtoInterface } from '../../dto/riyousha/getRiyoushaPartnerApiByEntityCapsuleDto';

// props,emmits
const props = defineProps<{ userDto: LeastUserDtoInterface, editEntity: RiyoushaPartnerApiMasterEntityInterface }>();
const emits = defineEmits(["sendCancelPartnerApi", "sendPartnerApiInterface"]);

// よく使う定数
const BLANK: string = "";
// const SERVER_STATUS_OK: number = 200;
// const SERVER_STATUS_ERROR: number = 400;
const INQUIRE_FLG: boolean = false;
const ERR_MESS_ONLY: boolean = true;
const MESS_PAGE_NAME: string = "APIパートナー編集";
const INIT_CALLER: string = "no branch";

// メッセージボックス表示定数
const infoLevel: Ref<number> = ref(MessageConstants.LEVEL_NONE);
const messageType: Ref<number> = ref(MessageConstants.VIEW_NONE);
const caller: Ref<string> = ref(INIT_CALLER);
const message: Ref<string> = ref(BLANK);

// back側アクセス
const urlBack: string = RoutePathConstants.DOMAIN + RoutePathConstants.BASE_PATH;

const inputpartnerApiDto: Ref<RiyoushaPartnerApiDtoInterface> = ref(new RiyoushaPartnerApiDto());


onMounted(() => {
    // リストからの変更
    onChangeEntity();
});

watch(props, () => {
    // リストからの変更
    onChangeEntity();
});

// データ変更
function onChangeEntity() {
    // 編集しないときはgetしない    
    if (props.editEntity.riyoushaPartnerApiMasterId !== 0) {

        const capsuleDto: GetRiyoushaPartnerApiByEntityCapsuleDtoInterface = new GetRiyoushaPartnerApiByEntityCapsuleDto();
        capsuleDto.userDto = props.userDto;
        capsuleDto.masterEntity = props.editEntity;

        getAuthorizedPromiseArea().then(token => {
            const url = urlBack + "/riyousha/get-partner-api";
            const method = "POST";
            const body = JSON.stringify(capsuleDto);
            const headers = {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
                'X-AUTH-TOKEN': 'Bearer ' + token
            };
            fetch(url, { method, headers, body })
                .then(async (response) => {
                    inputpartnerApiDto.value = await response.json();
                    message.value = inputpartnerApiDto.value.message;
                    if (inputpartnerApiDto.value.isFailure) {
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
    }
}

function onCancel() {
    emits("sendCancelPartnerApi");
}

function onSave() {
    emits("sendPartnerApiInterface", inputpartnerApiDto.value);
}

function recieveSubmit() {
    infoLevel.value = 0;
    messageType.value = 0;
}
</script>
<template>

    <h3 class="accent-h3">情報入力</h3><br>

    <!-- 長期トークン発行機能は他人がこの基礎情報を編集する可能性があるので別ページに移転 -->

    <div class="one-line">
        <div class="left-area">
            権限
        </div>
        <div class="right-area">
            <input type="text" disabled="true" value="APIパートナー">
        </div>
    </div>

    <div class="one-line">
        <div class="left-area">
            関連者コード
        </div>
        <div class="right-area">
            <input type="text" disabled="true" v-model="inputpartnerApiDto.riyoushaPartnerApiMasterCode"
                class="left-space code-input">
        </div>
    </div>

    <!-- 個人姓名入力 -->
    <ViewInputPersonName :edit-dto="inputpartnerApiDto.inputPersonNameDto"></ViewInputPersonName>

    <!-- 住所入力 -->
    <ViewInputAddress :edit-dto="inputpartnerApiDto.inputAddressDto"></ViewInputAddress>

    <!-- 連絡先入力 -->
    <ViewInputAccess :edit-dto="inputpartnerApiDto.inputAccessDto"></ViewInputAccess>

    <div class="footer">
        <button class="footer-button" @click="onCancel">キャンセル</button>
        <button class="footer-button left-space" @click="onSave">送信</button>
    </div>

    <!-- メッセージ表示    -->
    <div class="overMessage" v-if="messageType !== MessageConstants.VIEW_NONE">
        <MessageView :info-level="infoLevel" :message-type="messageType" :title="MESS_PAGE_NAME" :message="message"
            :caller="caller" @send-submit="recieveSubmit">
        </MessageView>
    </div>

</template>
<style scoped></style>
