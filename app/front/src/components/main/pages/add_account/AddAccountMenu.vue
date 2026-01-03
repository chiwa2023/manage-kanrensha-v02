<script setup lang="ts">
import { ref, type Ref } from 'vue';
import { NewComerDto, type NewComerDtoInterface } from '../../dto/add_account/newComerDto';
import router from '../../../../router';
import RoutePathConstants from '../../../../routePathConstants';
import MockNewComerInfo from '../../../test/common/user_info/MockNewComerInfo.vue';
import { MessageConstants, MessageView } from 'seijishikin-jp-normalize_common-tool';
import { useApi } from '../../utils/useApi';

// back側アクセス
const urlBack: string = RoutePathConstants.DOMAIN + RoutePathConstants.BASE_PATH;

// よく使う定数
const BLANK: string = "";

// メッセージ表示定数
const infoLevel: Ref<number> = ref(MessageConstants.LEVEL_NONE);
const messageType: Ref<number> = ref(MessageConstants.VIEW_NONE);
const title: Ref<string> = ref(BLANK);
const message: Ref<string> = ref(BLANK);

// 入力用Dto
const newComer: Ref<NewComerDtoInterface> = ref(new NewComerDto());

// API呼び出し用Composable
const { loading: publishLoading, error: publishError, fetchData: fetchPublish } = useApi<NewComerDtoInterface>();

async function onRegistMail() {
    // 入力チェック
    if (newComer.value.mailAddress === BLANK) {
        infoLevel.value = MessageConstants.LEVEL_ERROR;
        messageType.value = MessageConstants.VIEW_OK;
        title.value = "入力エラー";
        message.value = "メールアドレスは必須です。";
        return;
    }

    // メールアドレスを用いて新規登録用コードを発行
    const url = urlBack + "/add-user/publish-code";
    const config = {
        method: "POST",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(newComer.value)
    };

    const resultDto = await fetchPublish(url, config);

    if (resultDto) {
        sessionStorage.setItem("new-comer", JSON.stringify(resultDto));
        router.push(RoutePathConstants.PAGE_SEND_ACCESS_CODE);
    } else if (publishError.value) {
        infoLevel.value = MessageConstants.LEVEL_ERROR;
        messageType.value = MessageConstants.VIEW_OK;
        title.value = "コード発行エラー";
        message.value = publishError.value;
    }
}

function recieveSubmit(button: string) {
    console.log(button);
    infoLevel.value = 0;
    messageType.value = 0;
}

function onCancel() {
    history.back();
}
</script>
<template>
    <MockNewComerInfo :current-step="1"></MockNewComerInfo>
    <h1>新規登録(アカウント入力)</h1>

    <div class="one-line">
        連絡用メールアドレスの疎通確認をします。
    </div>

    <div class="one-line">
        <div class="left-area">
            メールアドレス(アカウント)
        </div>
        <div class="right-area">
            <input type="email" v-model="newComer.mailAddress" class="name-input">
        </div>
    </div>

    <div class="footer">
        <button class="footer-button" @click="onCancel">前に戻る</button>
        <button class="footer-button left-space" @click="onRegistMail" :disabled="publishLoading">送信</button>
    </div>

    <!-- メッセージ表示 -->
    <div class="overMessage" v-if="messageType !== MessageConstants.VIEW_NONE">
        <MessageView :info-level="infoLevel" :message-type="messageType" :title="title" :message="message"
            @send-submit="recieveSubmit">
        </MessageView>
    </div>

</template>
<style scoped></style>
