<script setup lang="ts">
import { MessageConstants, MessageView, type FrameworkMessageAndResultDtoInterface, type LeastUserDtoInterface } from 'seijishikin-jp-normalize_common-tool';
import { TaskInfoEntity, type TaskInfoEntityInterface } from '../../entity/taskInfoEntity';
import { onMounted, ref, toRaw, watch, type Ref } from 'vue';
import RoutePathConstants from '../../../../routePathConstants';
import { EditTaskInfoCapsuleDto, type EditTaskInfoCapsuleDtoInterfacee } from '../../dto/task_info/editTaskInfoCapsuleDto';
import getAuthorizedPromiseArea from '../../dto/login/getAuthorizedPromiseArea';
import { AccessTokenNotFoundError, TokenRefreshError } from '../../dto/login/errors';

//props,emit
const props = defineProps<{ userDto: LeastUserDtoInterface, taskInfoEntity: TaskInfoEntityInterface }>()
const emits = defineEmits(["sendCancelTaskInfo", "sendTaskInfoInterface"]);

//仮
// よく使う定数
const BLANK: string = "";
const INIT_NUMBER: number = 0;
const SERVER_STATUS_OK: number = 200;
// const SERVER_STATUS_ERROR: number = 400;
// const SEARCH_LIMIT: number = 20;
// メッセージボックス表示定数
const infoLevel: Ref<number> = ref(MessageConstants.LEVEL_NONE);
const messageType: Ref<number> = ref(MessageConstants.VIEW_NONE);
const title: Ref<string> = ref(BLANK);
const message: Ref<string> = ref(BLANK);

// back側アクセス
const urlBack: string = RoutePathConstants.DOMAIN + RoutePathConstants.BASE_PATH;

// 編集対象
const editEntity: Ref<TaskInfoEntityInterface> = ref(new TaskInfoEntity());
const editEntityBackup: Ref<TaskInfoEntityInterface> = ref(new TaskInfoEntity());

onMounted(() => {
    editEntity.value = structuredClone(toRaw(props.taskInfoEntity));
    editEntityBackup.value = structuredClone(toRaw(props.taskInfoEntity));
});
watch(props, () => {
    editEntity.value = structuredClone(toRaw(props.taskInfoEntity));
    editEntityBackup.value = structuredClone(toRaw(props.taskInfoEntity));
});


let actionState: number = INIT_NUMBER;
function onSave() {
    const capsuleDto: EditTaskInfoCapsuleDtoInterfacee = new EditTaskInfoCapsuleDto();
    capsuleDto.userDto = props.userDto;
    capsuleDto.taskInfoEntity = editEntity.value;

    title.value = "タスク情報変更";
    getAuthorizedPromiseArea().then(token => {
        const url = urlBack + "/task-info/update";
        const method = "POST";
        const body = JSON.stringify(capsuleDto);
        const headers = {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'X-AUTH-TOKEN': 'Bearer ' + token
        };
        fetch(url, { method, headers, body })
            .then(async (response) => {
                const resultDtoSave: FrameworkMessageAndResultDtoInterface = await response.json();
                message.value = resultDtoSave.message;
                if (resultDtoSave.isFailure) {
                    infoLevel.value = MessageConstants.LEVEL_WARNING;
                    messageType.value = MessageConstants.VIEW_OK;
                    return;
                } else {
                    infoLevel.value = MessageConstants.LEVEL_INFO;
                    messageType.value = MessageConstants.VIEW_TOAST;
                    actionState = SERVER_STATUS_OK;
                    return;
                }
            })
            .catch((error) => {
                infoLevel.value = MessageConstants.LEVEL_ERROR;
                messageType.value = MessageConstants.VIEW_OK;
                message.value = error;
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
        message.value = "システムエラーが発生しました。システム管理者にお問い合わせください";
        return;
    });
}

function onCancel() {
    emits("sendCancelTaskInfo");
}

function recieveSubmit(button: string) {
    console.log(button); // 警告除け

    infoLevel.value = 0;
    messageType.value = 0;

    if (SERVER_STATUS_OK === actionState) {
        emits("sendTaskInfoInterface", editEntity.value);
    }
}
</script>
<template>
    <h3>タスク情報編集</h3><br>

    <div class="one-line">
        <div class="left-area">
            タスクコード
        </div>
        <div class="right-area">
            {{ editEntity.taskInfoCode }}
        </div>
    </div>

    <div class="one-line">
        <div class="left-area">
            タスク名称
        </div>
        <div class="right-area">
            <input type="text" v-model="editEntity.taskInfoName">
        </div>
    </div>


    <div class="one-line">
        <div class="left-area">
            開始時通知テキスト
        </div>
        <div class="right-area">
            <textarea v-model="editEntity.messageStart" class="max-input"></textarea>
        </div>
    </div>

    <div class="one-line">
        <div class="left-area">
            終了時通知テキスト
        </div>
        <div class="right-area">
            <textarea v-model="editEntity.messageFinish" class="max-input"></textarea>
        </div>
    </div>

    <div class="one-line">
        <div class="left-area">
            中断時通知テキスト
        </div>
        <div class="right-area">
            <textarea v-model="editEntity.messageSuspend" class="max-input"></textarea>
        </div>
    </div>

    <div class="one-line">
        <div class="left-area">
            権限
        </div>
        <div class="right-area">
            {{ editEntity.roleList }}
        </div>
    </div>


    <div class="one-line">
        <div class="left-area">
            遷移先URL
        </div>
        <div class="right-area">
            {{ editEntity.transferPass }}
        </div>
    </div>
    <div class="one-line">
        <div class="left-area">
            遷移時パラメータ
        </div>
        <div class="right-area">
            {{ editEntity.paramQuery }}
        </div>
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
