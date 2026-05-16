<script setup lang="ts">
import { onBeforeMount, ref, type Ref } from 'vue';
import router from '../../../../router';
import { FrameworkCapsuleDto, MessageConstants, MessageView, type FrameworkCapsuleDtoInterface, type FrameworkMessageAndResultDtoInterface, type LeastUserDtoInterface } from 'seijishikin-jp-normalize_common-tool';
import { getLoginUser } from '../../utils/getLoginUser';
import getAuthorizedPromiseArea from '../../dto/login/getAuthorizedPromiseArea';
import RoutePathConstants from '../../../../routePathConstants';
import { AccessTokenNotFoundError, TokenRefreshError } from '../../dto/login/errors';
import { GetPromoteAdminResultDto, type GetPromoteAdminResultDtoInterface } from '../../dto/user/getPromoteAdminResultDto';
import { AcceptUserAdminCapsuleDto, type AcceptUserAdminCapsuleDtoInterface } from '../../dto/user/acceptUserAdminCapsuleDto';
import { nextTransferPassStore } from '../../stores/nextTransferPass';
import ManagerInfo from '../../common/user_info/ManagerInfo.vue';
import { notCompletedTaskStore } from '../../stores/notCompletedTask';

// back側アクセス
const urlBack: string = RoutePathConstants.DOMAIN + RoutePathConstants.BASE_PATH;

// よく使う定数
const BLANK: string = "";
const INIT_NUMBER: number = 0;
//const SERVER_STATUS_OK: number = 200;
const SERVER_ACCEPTED: number = 202;
// const SERVER_STATUS_ERROR: number = 400;
// メッセージボックス表示定数
const infoLevel: Ref<number> = ref(MessageConstants.LEVEL_NONE);
const messageType: Ref<number> = ref(MessageConstants.VIEW_NONE);
const title: Ref<string> = ref(BLANK);
const message: Ref<string> = ref(BLANK);

// ユーザ呼び出し
const userDto: Ref<LeastUserDtoInterface> = ref(getLoginUser());

// pinia(タスク)
const notCompletedTaskInfo = notCompletedTaskStore();

// 表示Dto
const resultDto: Ref<GetPromoteAdminResultDtoInterface> = ref(new GetPromoteAdminResultDto());

//初期表示で該当ユーザの推薦状況を表示する
onBeforeMount(() => {
    // 直リンク(パスチェックあり)を許容ロジック
    if (INIT_NUMBER === userDto.value.userPersonId) {
        const passStore = nextTransferPassStore()
        passStore.fullPath = RoutePathConstants.PAGE_ADMIN_ACCEPT;
        router.push(RoutePathConstants.PAGE_LOGIN);
        return;
    }

    const capsuleDto: FrameworkCapsuleDtoInterface = new FrameworkCapsuleDto();
    capsuleDto.userDto = userDto.value;
    // 検索実行
    getAuthorizedPromiseArea().then(token => {
        const url = urlBack + "/user-role/get";
        const method = "POST";
        const body = JSON.stringify(capsuleDto);
        const headers = {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'X-AUTH-TOKEN': 'Bearer ' + token
        };
        fetch(url, { method, headers, body })
            .then(async (response) => {
                resultDto.value = await response.json();
                if (SERVER_ACCEPTED == response.status) {
                    infoLevel.value = MessageConstants.LEVEL_ERROR;
                    messageType.value = MessageConstants.VIEW_OK;
                    title.value = "SE権限追加推薦取得処理";
                    message.value = resultDto.value.message;
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

    const casuleDtoAccept: AcceptUserAdminCapsuleDtoInterface = new AcceptUserAdminCapsuleDto();
    casuleDtoAccept.promoteAdminEntity = resultDto.value.promoteAdminEntity;
    casuleDtoAccept.userDto = userDto.value;

    // 回答保存
    getAuthorizedPromiseArea().then(token => {
        const url = urlBack + "/user-role/accept";
        const method = "POST";
        const body = JSON.stringify(casuleDtoAccept);
        const headers = {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'X-AUTH-TOKEN': 'Bearer ' + token
        };
        fetch(url, { method, headers, body })
            .then(async (response) => {
                const resultDtoAccept: FrameworkMessageAndResultDtoInterface = await response.json();
                // 何によらずメッセージを出す
                infoLevel.value = MessageConstants.LEVEL_INFO;
                messageType.value = MessageConstants.VIEW_TOAST;
                title.value = "SE権限追加諾否登録";
                message.value = resultDtoAccept.message;
                // タスク更新につき次ページでタスクを取りなおす
                notCompletedTaskInfo.notCompleteTaskDto.isRefreshed = false;
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
    router.back();
}

function recieveSubmit(button: string) {
    console.log(button); // 警告除け
    infoLevel.value = 0;
    messageType.value = 0;
}
</script>
<template>

    <!-- ユーザメニュー兼チェック -->
    <ManagerInfo :user-dto="userDto"></ManagerInfo>

    <h1>SE権限追加承諾</h1>

    <h3>ユーザ検索条件</h3>
    <div class="one-line">
        <div class="left-area">
            SE権限追加推奨者
        </div>
        <div class="right-area">
            {{ resultDto.promoteAdminEntity.insertUserName }}({{ resultDto.promoteAdminEntity.insertUserCode }})
            <span v-if="resultDto.promoteCount > 1" class="left-space">ほか{{ resultDto.promoteCount }}件</span>
        </div>
    </div>

    <div class="one-line">
        <div class="left-area">
            推薦承諾
        </div>
        <div class="right-area">
            <input type="checkbox" v-model="resultDto.promoteAdminEntity.isAccept">SE権限追加を承諾します
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
