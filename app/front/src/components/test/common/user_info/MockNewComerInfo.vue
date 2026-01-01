<script setup lang="ts">
import { ref, type Ref } from 'vue';
import { MessageConstants, MessageView } from 'seijishikin-jp-normalize_common-tool';
import router from '../../../../router';
import RoutePathConstants from '../../../../routePathConstants';

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

// propsの定義
const props = defineProps<{ 
    currentStep: number;
    // 他のpropsもあればここに追加
}>();

// 登録フローのステップ定義
const steps = [
    { id: 1, name: 'メールアドレス入力' },
    { id: 2, name: '認証コード確認' },
    { id: 3, name: 'パスワード・役割決定' },
    { id: 4, name: '詳細情報入力' },
];

// ログインと権限チェック（既存のコメントアウトされたコードはそのままに）
// ...

// メッセージからの反応受け取り
function recieveSubmit(button: string) {
    console.log(button); // 警告除け
    // TODO ボタンタイプ別の挙動はこの中で変える

    // 非表示
    infoLevel.value = 0;
    messageType.value = 0;
    router.push(RoutePathConstants.PAGE_LOGOUT);
}
</script>

<template>
    <!-- ユーザrole別制御コンポーネント -->
    <div class="user-role-container-partner">
        <div class="user-role-content">
            <div class="user-role-title">
                新規ユーザ登録作業中・・・
            </div>
            <!-- ステップバーの追加 -->
            <nav class="progress-steps">
                <ol>
                    <li v-for="step in steps" :key="step.id" :class="{
                        'is-active': step.id === props.currentStep,
                        'is-complete': step.id < props.currentStep
                    }">
                        <span>{{ step.name }}</span>
                    </li>
                </ol>
            </nav>
        </div>
    </div>

    <!-- メッセージ表示 -->
    <div class="overMessage" v-if="messageType !== MessageConstants.VIEW_NONE">
        <MessageView :info-level="infoLevel" :message-type="messageType" :title="title" :message="message"
            @send-submit="recieveSubmit">
        </MessageView>
    </div>

</template>
<style scoped>
.progress-steps {
    margin: 20px 0;
    padding: 0;
    list-style: none;
    display: flex;
    justify-content: space-around;
    align-items: center;
    width: 100%;
}

.progress-steps ol {
    display: flex;
    width: 100%;
    padding: 0;
    margin: 0;
    list-style: none;
}

.progress-steps li {
    flex: 1;
    text-align: center;
    position: relative;
    padding: 10px 0;
    color: #ccc;
    font-weight: bold;
    font-size: 0.9em;
}

.progress-steps li:not(:last-child)::before {
    content: '';
    position: absolute;
    top: 50%;
    left: 50%;
    width: 100%;
    height: 2px;
    background-color: #ccc;
    transform: translateY(-50%) translateX(50%);
    z-index: -1;
}

.progress-steps li span {
    display: inline-block;
    background-color: #fff;
    padding: 5px 10px;
    border-radius: 20px;
    border: 2px solid #ccc;
    box-sizing: border-box;
}

.progress-steps li.is-active span {
    color: #007bff;
    border-color: #007bff;
    background-color: #e6f2ff;
}

.progress-steps li.is-complete span {
    color: #28a745;
    border-color: #28a745;
    background-color: #e6ffe6;
}

.progress-steps li.is-complete:not(:last-child)::before {
    background-color: #28a745;
}
</style>