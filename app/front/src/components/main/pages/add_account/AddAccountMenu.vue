<script setup lang="ts">
import { ref, type Ref } from 'vue';
//import RoutePathConstants from '../../../../routePathConstants';
import { NewComerDto, type NewComerDtoInterface } from '../../dto/add_account/newComerDto';
import router from '../../../../router';
import RoutePathConstants from '../../../../routePathConstants';
import MockNewComerInfo from '../../../test/common/user_info/MockNewComerInfo.vue';
import { MessageConstants, MessageView } from 'seijishikin-jp-normalize_common-tool';
// import router from '../../../router';
// import type NewComerInterface from '../../../dto/user/newComerDto';
// import NewComerDto from '../../../dto/user/newComerDto';

// back側アクセス
// const urlBack: string = RoutePathConstants.DOMAIN + RoutePathConstants.BASE_PATH;

// const sessionStorage = window["sessionStorage"];

// よく使う定数
const BLANK: string = "";
// const INIT_NUMBER: number = 0;
// const SERVER_STATUS_OK: number = 200;
// const SERVER_STATUS_ERROR: number = 400;

// メッセージ表示定数
const infoLevel: Ref<number> = ref(MessageConstants.LEVEL_NONE);
const messageType: Ref<number> = ref(MessageConstants.VIEW_NONE);
const title: Ref<string> = ref(BLANK);
const message: Ref<string> = ref(BLANK);

// 入力用Dto
const newComer: Ref<NewComerDtoInterface> = ref(new NewComerDto());

function onRegistMail() {
    // 入力チェック
    if (newComer.value.mailAddress === BLANK) {
        infoLevel.value = MessageConstants.LEVEL_ERROR;
        messageType.value = MessageConstants.VIEW_OK;
        title.value = "入力エラー";
        message.value = "メールアドレスは必須です。";
        return;
    }

    //     const date: Date = new Date();
    //     date.setDate(date.getDate() + 1);
    //     newComer.value.limitDateTime = date;

    //     // メールアドレスを用いて新規登録用コードを発行
    //     const url = urlBack + "0/add-user/publish-code";
    //     const method = "POST";
    //     const body = JSON.stringify(newComer.value);
    //     const headers = {
    //         'Accept': 'application/json',
    //         'Content-Type': 'application/json'
    //     };
    //     fetch(url, { method, headers, body })
    //         .then(async (response) => {

    //             const status = response.status;
    //             if (status === 200) {
    //                 const resultDto: NewComerInterface = await response.json();
    // TODO　運用時にはセッションストレージへの保存を削除する
    newComer.value.registCode = "12345";
    sessionStorage.setItem("new-comer", JSON.stringify(newComer.value));
    router.push(RoutePathConstants.PAGE_SEND_ACCESS_CODE);
    //             } else {
    //                 alert("発行できませんでした");
    //             }
    //         })
    //         .catch((error) => { alert(error); });
}


function recieveSubmit(button: string) {
    console.log(button); // 警告除け
    // TODO ボタンタイプ別の挙動はこの中で変える

    // 非表示
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
        <button class="footer-button left-space" @click="onRegistMail">送信</button>
    </div>

    <!-- メッセージ表示 -->
    <div class="overMessage" v-if="messageType !== MessageConstants.VIEW_NONE">
        <MessageView :info-level="infoLevel" :message-type="messageType" :title="title" :message="message"
            @send-submit="recieveSubmit">
        </MessageView>
    </div>

</template>
<style scoped>
span.explain {
    font-size: 110%;
}

span.kbn {
    font-size: 120%;
    font-weight: bold;
}

table {
    border-style: solid;
    border-width: 1px;
}

td {
    border-style: solid;
    border-width: 1px;
}

th {
    border-style: solid;
    border-width: 1px;
}
</style>
