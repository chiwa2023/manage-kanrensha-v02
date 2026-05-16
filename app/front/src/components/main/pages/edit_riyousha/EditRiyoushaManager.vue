<script setup lang="ts">
import { onMounted, ref, type Ref } from 'vue';
import RiyoushaManagerEdit from '../../common/riyousha_edit/RiyoushaManagerEdit.vue';
import { RiyoushaManagerMasterEntity, type RiyoushaManagerMasterEntityInterface } from '../../entity/riyoushaManagerMasterEntity';
import ManagerInfo from '../../common/user_info/ManagerInfo.vue';
import { MessageConstants, MessageView, type FrameworkMessageAndResultDtoInterface, type LeastUserDtoInterface } from 'seijishikin-jp-normalize_common-tool';
import { getLoginUser } from '../../utils/getLoginUser';
import getAuthorizedPromiseArea from '../../dto/login/getAuthorizedPromiseArea';
import RoutePathConstants from '../../../../routePathConstants';
import { AccessTokenNotFoundError, TokenRefreshError } from '../../dto/login/errors';
import type { GetRiyoushaMasterResultDtoInterface } from '../../dto/riyousha/getRiyoushaMasterResultDto';
import { GetRiyoushaMasterCapsuleDto, type GetRiyoushaMasterCapsuleDtoInterface } from '../../dto/riyousha/getRiyoushaMasterCapsuleDto';
import type { RiyoushaManagerDtoInterface } from '../../dto/riyousha/riyoushaManagerDto';
import { SaveRiyoushaManagerCapsuleDto, type SaveRiyoushaManagerCapsuleDtoInterface } from '../../dto/riyousha/saveRiyoushaManagerCapsuleDto';

// よく使う定数
const BLANK: string = "";
// const INIT_NUMBER: number = 0;
// const SERVER_STATUS_OK: number = 200;
// const SERVER_STATUS_ERROR: number = 400;

//メッセージボックス表示定数
const infoLevel: Ref<number> = ref(MessageConstants.LEVEL_NONE);
const messageType: Ref<number> = ref(MessageConstants.VIEW_NONE);
const title: Ref<string> = ref(BLANK);
const message: Ref<string> = ref(BLANK);

// back側アクセス
const urlBack: string = RoutePathConstants.DOMAIN + RoutePathConstants.BASE_PATH;

// ユーザ呼び出し
const userDto: Ref<LeastUserDtoInterface> = ref(getLoginUser());

// 編集元となるEntity
const editEntity: Ref<RiyoushaManagerMasterEntityInterface> = ref(new RiyoushaManagerMasterEntity());

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
                    if (resultDto.isFailure) {
                        infoLevel.value = MessageConstants.LEVEL_WARNING;
                        messageType.value = MessageConstants.VIEW_OK;
                    } else {
                        // editEntityを変更すると自動で呼び出し
                        editEntity.value = resultDto.managerMasterEntity;
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

function recieveCancelManager() {
    history.back();
}


function recieveManagerInterface(editDto: RiyoushaManagerDtoInterface) {

    const capsuleDto: SaveRiyoushaManagerCapsuleDtoInterface = new SaveRiyoushaManagerCapsuleDto();
    capsuleDto.userDto = userDto.value;
    capsuleDto.riyoushaManagerDto = editDto;
    // 検索結果から呼んでいないときはidが0なら紐づけ必要
    if (0 == userDto.value.riyoushaCode) {
        capsuleDto.riyoushaManagerDto.isCombineUser = true;
    }

    title.value = "関連者企業・団体編集";
    getAuthorizedPromiseArea().then(token => {
        const url = urlBack + "/riyousha/save-manager";
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
    <ManagerInfo :user-dto="userDto"></ManagerInfo>

    <h1>運営者編集</h1><br>

    <!-- 運営者編集-->
    <RiyoushaManagerEdit :edit-entity="editEntity" @send-cancel-manager="recieveCancelManager"
        @send-manager-interface="recieveManagerInterface">
    </RiyoushaManagerEdit>

    <!-- メッセージ表示 -->
    <div class="overMessage" v-if="messageType !== MessageConstants.VIEW_NONE">
        <MessageView :info-level="infoLevel" :message-type="messageType" :title="title" :message="message"
            @send-submit="recieveSubmit">
        </MessageView>
    </div>

</template>
<style scoped></style>
