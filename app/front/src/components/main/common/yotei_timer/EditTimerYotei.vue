<script setup lang="ts">
import { InputDatetime, type LeastUserDtoInterface, InputDatetimeAndNull, MessageConstants, MessageView, type FrameworkMessageAndResultDtoInterface } from 'seijishikin-jp-normalize_common-tool';
import { TimerYoteiEntity, type TimerYoteiEntityInterface } from '../../entity/timerYoteiEntity';
import { onMounted, ref, toRaw, watch, type Ref } from 'vue';
import { EditTimerYoteiCapsuleDto, type EditTimerYoteiCapsuleDtoInterface } from '../../dto/yoyaku_timer/editTimerYoteiCapsuleDto';
import getAuthorizedPromiseArea from '../../dto/login/getAuthorizedPromiseArea';
import { AccessTokenNotFoundError, TokenRefreshError } from '../../dto/login/errors';
import RoutePathConstants from '../../../../routePathConstants';

//props,emit
const props = defineProps<{ userDto: LeastUserDtoInterface, timerYoyakuEntity: TimerYoteiEntityInterface }>()
const emits = defineEmits(["sendTimerYoyakuInterface", "sendCancelTimerYoyaku"]);

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

const editEntity: Ref<TimerYoteiEntityInterface> = ref(new TimerYoteiEntity());
const editEntityBackup: Ref<TimerYoteiEntityInterface> = ref(new TimerYoteiEntity());

onMounted(() => {
    editEntity.value = structuredClone(toRaw(props.timerYoyakuEntity));
    editEntityBackup.value = structuredClone(toRaw(props.timerYoyakuEntity));
});
watch(props, () => {
    editEntity.value = structuredClone(toRaw(props.timerYoyakuEntity));
    editEntityBackup.value = structuredClone(toRaw(props.timerYoyakuEntity));
});

function onCancel() {
    emits("sendCancelTimerYoyaku");
}


let actionState: number = INIT_NUMBER;
function onSave() {
    const capsuleDto: EditTimerYoteiCapsuleDtoInterface = new EditTimerYoteiCapsuleDto();
    capsuleDto.userDto = props.userDto;
    capsuleDto.timerYoteiEntity = editEntity.value;

    getAuthorizedPromiseArea().then(token => {
        const url = urlBack + "/timer-yotei/update";
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
                title.value = "予約実行変更";
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
                alert(error);
                infoLevel.value = MessageConstants.LEVEL_ERROR;
                messageType.value = MessageConstants.VIEW_OK;
                title.value = "作業承認登録";
                message.value = "システムエラーが発生しました。システム管理者にお問い合わせください";
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

function onSetNext() {
    // 次回予定が決まる
    if (editEntity.value.isRepeat && !editEntity.value.isPause) {
        // 初期化したら元のデータに戻す
        if (INIT_NUMBER === editEntity.value.yearPointed) {
            editEntity.value.yearPointed = editEntityBackup.value.yearPointed;
            editEntity.value.monthPointed = editEntityBackup.value.monthPointed;
            editEntity.value.dayPointed = editEntityBackup.value.dayPointed;
            editEntity.value.hourPointed = editEntityBackup.value.hourPointed;
        }

        editEntity.value.endTimestamp = addPeriod(editEntityBackup.value.endTimestamp, editEntity.value);
        editEntity.value.sabunTimestamp = addPeriod(editEntityBackup.value.sabunTimestamp, editEntity.value);

        let nextTime: Date | null;
        if (editEntity.value.isPeriod) {
            // 実施日から算出(次回までの間隔優先、起動の差分日時、終了日時を見ないパターン)
            nextTime = addPeriod(editEntity.value.previousTimestamp, editEntity.value);
        } else {
            // 次回指定日(常に設定した基準の日付に戻ろうとする。起動の差分日時、終了日時を見るパターン)
            nextTime = addPeriod(new Date(editEntity.value.yearPointed, editEntity.value.monthPointed - 1,
                editEntity.value.dayPointed, editEntity.value.hourPointed, 0, 0), editEntity.value);
        }

        // 設定した次回時刻と一致させる
        editEntity.value.nextTimestamp = nextTime;
        if (null === nextTime) {
            editEntity.value.yearPointed = INIT_NUMBER;
            editEntity.value.monthPointed = INIT_NUMBER;
            editEntity.value.dayPointed = INIT_NUMBER;
            editEntity.value.hourPointed = INIT_NUMBER;
        } else {
            editEntity.value.yearPointed = nextTime.getFullYear();
            editEntity.value.monthPointed = nextTime.getMonth() + 1;
            editEntity.value.dayPointed = nextTime.getDate();
            editEntity.value.hourPointed = nextTime.getHours();
        }
    } else {
        // 次回予定を外す
        editEntity.value.nextTimestamp = null; // 次回実行しないためにnullでなければならない
        editEntity.value.yearPointed = INIT_NUMBER;
        editEntity.value.monthPointed = INIT_NUMBER;
        editEntity.value.dayPointed = INIT_NUMBER;
        editEntity.value.hourPointed = INIT_NUMBER;
        editEntity.value.sabunTimestamp = editEntityBackup.value.endTimestamp;
        editEntity.value.endTimestamp = null// 起動条件は次回起動日が決まる必要がある
    }
}
function onChageDate(date: Date | null, index: number) {
    switch (index) {
        case 1:
            editEntity.value.nextTimestamp = date;
            break;
        case 2:
            editEntity.value.endTimestamp = date;
            break;
        case 3:
            editEntity.value.sabunTimestamp = date;
            break;
    }
}
function addPeriod(src: Date | null, entity: TimerYoteiEntityInterface): Date | null {

    if (null === src) {
        return src;
    }

    src.setFullYear(src.getFullYear() + entity.yearPeriod);
    src.setMonth(src.getMonth() + entity.monthPeriod);
    src.setDate(src.getDate() + entity.dayPeriod);
    src.setHours(src.getHours() + entity.yearPeriod);
    src.setMinutes(0);
    src.setSeconds(0);

    return src;
}
function recieveSubmit(button: string) {
    console.log(button); // 警告除け

    infoLevel.value = 0;
    messageType.value = 0;

    if (SERVER_STATUS_OK === actionState) {
        emits("sendTimerYoyakuInterface", editEntity.value);
    }
}
</script>
<template>
    <h3>予約実行編集</h3><br>
    <div class="one-line">
        <div class="left-area">
            予約実行名称
        </div>
        <div class="right-area">
            {{ editEntity.timerYoteiName }} <span class="left-space"> ({{ editEntity.yoyakuTaskKbn }})</span>
        </div>
    </div>

    <div class="one-line">
        <div class="left-area">
            前回実行時間
        </div>
        <div class="right-area">
            <InputDatetime :datetime="editEntity.previousTimestamp" :index="0" :isEdit="false"></InputDatetime>
        </div>
    </div>

    <div class="one-line">
        <div class="left-area">
            次回実行時間
        </div>
        <div class="right-area">
            <InputDatetimeAndNull :datetime="editEntity.nextTimestamp" :index="1" :isEdit="true"
                @sendDateTime="onChageDate">
            </InputDatetimeAndNull>
        </div>
    </div>
    <div class="one-line">
        <div class="left-area">
            起動(終了日時)
        </div>
        <div class="right-area">
            終了： <InputDatetimeAndNull :datetime="editEntity.endTimestamp" :index="2" :isEdit="true"
                @sendDateTime="onChageDate">
            </InputDatetimeAndNull>
        </div>
    </div>

    <div class="one-line">
        <div class="left-area">
            起動(開始・差分日時)
        </div>
        <div class="right-area">
            開始(差分)： <InputDatetimeAndNull :datetime="editEntity.sabunTimestamp" :index="3" :isEdit="true"
                @sendDateTime="onChageDate">
            </InputDatetimeAndNull>
        </div>
    </div>

    <div class="one-line">
        <div class="left-area">
            繰り返し
        </div>
        <div class="right-area">
            <input type="checkbox" v-model="editEntity.isRepeat" @change="onSetNext">
        </div>
    </div>

    <div class="one-line">
        <div class="left-area">
            一時停止
        </div>
        <div class="right-area">
            <input type="checkbox" v-model="editEntity.isPause" @change="onSetNext">
        </div>
    </div>

    <div class="one-line">
        <div class="left-area">
            実施間隔
        </div>
        <div class="right-area">
            年<input type="number" v-model="editEntity.yearPeriod" class="short-input">
            月<input type="number" v-model="editEntity.monthPeriod" class="short-input">
            日<input type="number" v-model="editEntity.dayPeriod" class="short-input">
            時<input type="number" v-model="editEntity.hourPeriod" class="short-input">
        </div>
    </div>

    <div class="one-line">
        <div class="left-area">
            次回実行
        </div>
        <div class="right-area">
            <div class="form-group-vertical">
                <div>
                    <input type="checkbox" v-model="editEntity.isPeriod">前回起動日から算出</input>
                </div>
                <div>
                    年<input type="number" v-model="editEntity.yearPointed" class="short-input"
                        :disabled="editEntity.isPeriod">
                    月<input type="number" v-model="editEntity.monthPointed" class="short-input"
                        :disabled="editEntity.isPeriod">
                    日<input type="number" v-model="editEntity.dayPointed" class="short-input"
                        :disabled="editEntity.isPeriod">
                    時<input type="number" v-model="editEntity.hourPointed" class="short-input"
                        :disabled="editEntity.isPeriod">
                </div>
            </div>
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
