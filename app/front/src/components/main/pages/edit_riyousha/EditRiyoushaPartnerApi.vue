<script setup lang="ts">
import { onMounted, ref, type Ref } from 'vue';
import RiyoushaPartnerApiEdit from '../../common/riyousha_edit/RiyoushaPartnerApiEdit.vue';
import PartnerApiInfo from '../../common/user_info/PartnerApiInfo.vue';
import { MessageConstants, MessageView, type FrameworkMessageAndResultDtoInterface, type LeastUserDtoInterface } from 'seijishikin-jp-normalize_common-tool';
import { getLoginUser } from '../../utils/getLoginUser';
import { RiyoushaPartnerApiMasterEntity, type RiyoushaPartnerApiMasterEntityInterface } from '../../entity/riyoushaPartnerApiMasterEntity';
import getAuthorizedPromiseArea from '../../dto/login/getAuthorizedPromiseArea';
import { AccessTokenNotFoundError, TokenRefreshError } from '../../dto/login/errors';
import RoutePathConstants from '../../../../routePathConstants';
import { GetRiyoushaMasterCapsuleDto, type GetRiyoushaMasterCapsuleDtoInterface } from '../../dto/riyousha/getRiyoushaMasterCapsuleDto';
import type { GetRiyoushaMasterResultDtoInterface } from '../../dto/riyousha/getRiyoushaMasterResultDto';
import { SaveRiyoushaPartnerApiCapsuleDto, type SaveRiyoushaPartnerApiCapsuleDtoInterface } from '../../dto/riyousha/saveRiyoushaPartnerApiCapsuleDto';
import type { RiyoushaPartnerApiDtoInterface } from '../../dto/riyousha/riyoushaPartnerApiDto';

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

// ユーザ呼び出し
const userDto: Ref<LeastUserDtoInterface> = ref(getLoginUser());

const editEntity: Ref<RiyoushaPartnerApiMasterEntityInterface> = ref(new RiyoushaPartnerApiMasterEntity());

onMounted(() => {
    if (0 !== userDto.value.riyoushaCode) {
        const capsuleDto: GetRiyoushaMasterCapsuleDtoInterface = new GetRiyoushaMasterCapsuleDto();
        capsuleDto.riyoushaRole = userDto.value.riyoushaRole;
        capsuleDto.riyoushaCode = userDto.value.riyoushaCode;

        getAuthorizedPromiseArea().then(token => {
            const url = urlBack + "/riyousha/get-myself";
            const method = "POST";
            const body = JSON.stringify(capsuleDto);
            const headers = {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
                'X-AUTH-TOKEN': 'Bearer ' + token
            };
            fetch(url, { method, headers, body })
                .then(async (response) => {
                    const resultDto: GetRiyoushaMasterResultDtoInterface = await response.json();
                    message.value = resultDto.message;
                    // 処理が成功したら再登録できないようにアップロードファイル情報を初期化
                    if (resultDto.isFailure) {
                        infoLevel.value = MessageConstants.LEVEL_WARNING;
                        messageType.value = MessageConstants.VIEW_OK;
                    } else {
                        // editEntityを変更すると自動で呼び出し
                        editEntity.value = resultDto.partnerApiMasterEntity;
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

});

function recieveCancelPartnerApi() {
    history.back();

}

function recievePartnerApiInterface(editDto: RiyoushaPartnerApiDtoInterface) {

    const capsuleDto: SaveRiyoushaPartnerApiCapsuleDtoInterface = new SaveRiyoushaPartnerApiCapsuleDto();
    capsuleDto.userDto = userDto.value;
    capsuleDto.riyoushaPartnerApiDto = editDto;
    // 検索結果から呼んでいないときはidが0なら紐づけ必要
    if (0 == userDto.value.riyoushaCode) {
        capsuleDto.riyoushaPartnerApiDto.isCombineUser = true;
    }

    title.value = "関連者企業・団体編集";
    getAuthorizedPromiseArea().then(token => {
        const url = urlBack + "/riyousha/save-partner-api";
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

    <!-- 運営者編集-->
    <PartnerApiInfo :user-dto="userDto"></PartnerApiInfo>

    <h1>API接続者編集</h1><br>

    <!-- API接続者編集 -->
    <RiyoushaPartnerApiEdit :edit-entity="editEntity" @send-cancel-partner-api="recieveCancelPartnerApi"
        @send-partner-api-interface="recievePartnerApiInterface"></RiyoushaPartnerApiEdit>

    <!-- メッセージ表示 -->
    <div class="overMessage" v-if="messageType !== MessageConstants.VIEW_NONE">
        <MessageView :info-level="infoLevel" :message-type="messageType" :title="title" :message="message"
            @send-submit="recieveSubmit">
        </MessageView>
    </div>

</template>
<style scoped></style>
