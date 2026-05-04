<script setup lang="ts">
import { ref, watch, type Ref } from 'vue';
import { getLoginUser } from '../../utils/getLoginUser';
import { ForceDumpCapsuleDto, type ForceDumpCapsuleDtoInterface } from '../../dto/z_force_dump/forceDumpCapsuleDto';
import { InputDate, MessageConstants, MessageView, type FrameworkMessageAndResultDtoInterface, type LeastUserDtoInterface } from 'seijishikin-jp-normalize_common-tool';
import AdminInfo from '../../common/user_info/AdminInfo.vue';
import RoutePathConstants from '../../../../routePathConstants';
import { AccessTokenNotFoundError, TokenRefreshError } from '../../dto/login/errors';
import getAuthorizedPromiseArea from '../../dto/login/getAuthorizedPromiseArea';


// よく使う定数
const BLANK: string = "";
//const INIT_NUMBER: number = 0;
// const SERVER_STATUS_OK: number = 200;
// const SERVER_STATUS_ERROR: number = 400;
//const SEARCH_LIMIT: number = 20;

// メッセージボックス表示定数
const infoLevel: Ref<number> = ref(MessageConstants.LEVEL_NONE);
const messageType: Ref<number> = ref(MessageConstants.VIEW_NONE);
const title: Ref<string> = ref(BLANK);
const message: Ref<string> = ref(BLANK);

// back側アクセス
const urlBack: string = RoutePathConstants.DOMAIN + RoutePathConstants.BASE_PATH;

// ユーザ呼び出し
const userDto: Ref<LeastUserDtoInterface> = ref(getLoginUser());

// 実行条件
const capsuleDto: Ref<ForceDumpCapsuleDtoInterface> = ref(new ForceDumpCapsuleDto());

const isExecuteAll: Ref<boolean> = ref(true);

const isDisabledKigyouDt: Ref<boolean> = ref(true);
const isDisabledPerson: Ref<boolean> = ref(true);
const isDisabledSeijidantai: Ref<boolean> = ref(true);
const now: Date = new Date();
const pre: Date = new Date(now.getFullYear(), now.getMonth(), 1);
pre.setDate(pre.getDate() - 1);
capsuleDto.value.dateEnd = pre;
capsuleDto.value.dateStart = new Date(now.getFullYear(), 0, 1);

// 関連者選択制御
watch(isExecuteAll, () => {
    if (isExecuteAll.value) {
        capsuleDto.value.isExecuteKigyouDt = true;
        capsuleDto.value.isExecutePerson = true;
        capsuleDto.value.isExecuteSeijidantai = true;
        isDisabledKigyouDt.value = true;
        isDisabledPerson.value = true;
        isDisabledSeijidantai.value = true;
    } else {
        isDisabledKigyouDt.value = false;
        isDisabledPerson.value = false;
        isDisabledSeijidantai.value = false;
    }
});

function onCancel() {
    history.back();
}
function onSave() {
    
    capsuleDto.value.userDto = userDto.value;

    title.value = "差分マスタ最小強制ダンプ処理";
    getAuthorizedPromiseArea().then(token => {
        const url = urlBack + "/dump-master-min-sabun/execute";
        const method = "POST";
        const body = JSON.stringify(capsuleDto.value);
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
                // バッチ処理なので再表示はしない
                return;
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

function recieveDateTime(date: Date, index: number) {
    if (0 == index) {
        capsuleDto.value.dateStart = date;
    }
    if (1 == index) {
        capsuleDto.value.dateEnd = date;
    }
}

function recieveSubmit(button: string) {
    console.log(button); // 警告除け
    infoLevel.value = 0;
    messageType.value = 0;
}

</script>
<template>
    <!-- SE権限 -->
    <AdminInfo :user-dto="userDto"></AdminInfo>

    <h1>関連者マスタ最小差分強制csvダンプ</h1>


    <div class="one-line">
        <div class="left-area">
            全実施
        </div>
        <div class="right-area">
            <input type="checkbox" v-model="isExecuteAll">全選択
        </div>
    </div>

    <div class="one-line">
        <div class="left-area">
            関連者個別選択
        </div>
        <div class="right-area">
            <input type="checkbox" v-model="capsuleDto.isExecuteKigyouDt" :disabled=isDisabledKigyouDt>関連者企業・団体
            <span class="left-space"><input type="checkbox" v-model="capsuleDto.isExecutePerson"
                    :disabled=isDisabledPerson>関連者個人</span>
            <span class="left-space"><input type="checkbox" v-model="capsuleDto.isExecuteSeijidantai"
                    :disabled=isDisabledSeijidantai>関連者政治団体</span>
        </div>
    </div>

    <div class="one-line">
        <div class="left-area">
            指定期間
        </div>
        <div class="right-area">
            <InputDate :date="capsuleDto.dateStart" :index="0" :is-edit="true" @send-date="recieveDateTime"></InputDate>
            <span class="left-space">から</span>
            <span class="left-space">
                <InputDate e :date="capsuleDto.dateEnd" :index="1" :is-edit="true" @send-date="recieveDateTime">
                </InputDate>
            </span><span class="left-space">まで</span>
        </div>
    </div>


    <div class="footer">
        <button @click="onCancel" class="footer-button">キャンセル</button>
        <button @click="onSave" class="footer-button left-space">送信</button>
    </div>

    <!-- メッセージ表示 -->
    <div class="overMessage" v-if="messageType !== MessageConstants.VIEW_NONE">
        <MessageView :info-level="infoLevel" :message-type="messageType" :title="title" :message="message"
            @send-submit="recieveSubmit">
        </MessageView>
    </div>

</template>
<style scoped></style>
