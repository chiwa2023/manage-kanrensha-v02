<script setup lang="ts">
import { onMounted, ref, type Ref } from 'vue';
import { MoveKanrenshaCodeSearchResultDto, type MoveKanrenshaCodeSearchResultDtoInterface } from '../../dto/kanrensha/moveKanrenshaCodeSearchResultDto';
import KanrenshaKbnConstants from '../../dto/kanrensha/kanrenshaKbnConstants';
import { MoveKanrenshaCodeHistoryCapsuleDto, type MoveKanrenshaCodeHistoryCapsuleDtoInterface } from '../../dto/kanrensha/moveKanrenshaCodeHistoryCapsuleDto';
import getAuthorizedPromiseArea from '../../dto/login/getAuthorizedPromiseArea';
import RoutePathConstants from '../../../../routePathConstants';
import { getErrorMessage, MessageConstants, MessageView, type FrameworkMessageAndResultDtoInterface, type LeastUserDtoInterface } from 'seijishikin-jp-normalize_common-tool';
import { getLoginUser } from '../../utils/getLoginUser';
import { AccessTokenNotFoundError, TokenRefreshError } from '../../dto/login/errors';
import KanrenshaInfo from '../../common/user_info/KanrenshaInfo.vue';
import { useTaskPlan } from '../../stores/storeTaskPlan';
import { UpdateTaskPlanSimpleCapsuleDto, type UpdateTaskPlanSimpleCapsuleDtoInterface } from '../../dto/task_plan/updateTaskPlanSimpleCapsuleDto.ts';
import { notCompletedTaskStore } from '../../stores/notCompletedTask.ts';


// よく使う定数
const BLANK: string = "";
const INIT_NUMBER: number = 0;
// const SERVER_STATUS_OK: number = 200;
const SERVER_STATUS_ERROR: number = 400;
// const SEARCH_LIMIT: number = 20;
// メッセージボックス表示定数
const INQUIRE_FLG: boolean = false;
const ERR_MESS_ONLY: boolean = true;
const MESS_PAGE_NAME: string = "関連者コード移行履歴(自分自身)";
const INIT_CALLER: string = "no branch";
const infoLevel: Ref<number> = ref(MessageConstants.LEVEL_NONE);
const messageType: Ref<number> = ref(MessageConstants.VIEW_NONE);
const caller: Ref<string> = ref(INIT_CALLER);
const message: Ref<string> = ref(BLANK);

// back側アクセス
const urlBack: string = RoutePathConstants.DOMAIN + RoutePathConstants.BASE_PATH;

// ユーザ呼び出し
const userDto: Ref<LeastUserDtoInterface> = ref(getLoginUser());


// 検索Dto
const capsuleDtoSearch: Ref<MoveKanrenshaCodeHistoryCapsuleDtoInterface> = ref(new MoveKanrenshaCodeHistoryCapsuleDto());
const resultDtoSearch: Ref<MoveKanrenshaCodeSearchResultDtoInterface> = ref(new MoveKanrenshaCodeSearchResultDto());

const isTaskTransfer: Ref<boolean> = ref(false);

// タスク終了Dto
const capsuleDto: Ref<UpdateTaskPlanSimpleCapsuleDtoInterface> = ref(new UpdateTaskPlanSimpleCapsuleDto());



onMounted(() => {

    // メニューから遷移されてきたときは保存
    const storesTaskPlan = useTaskPlan();
    if (storesTaskPlan.taskPlanId !== null && undefined !== storesTaskPlan.taskPlanId && 0 != storesTaskPlan.taskPlanId) {
        isTaskTransfer.value = true;
        capsuleDto.value.taskPlanId = storesTaskPlan.taskPlanId;
    }
    if (storesTaskPlan.taskYear !== null && undefined !== storesTaskPlan.taskYear && 0 != storesTaskPlan.taskYear) {
        capsuleDto.value.taskYear = storesTaskPlan.taskYear;
    }

    capsuleDtoSearch.value.userDto = userDto.value;
    getAuthorizedPromiseArea().then(token => {
        const url = urlBack + "/kanrensha-code-move/search-myself";
        const method = "POST";
        const body = JSON.stringify(capsuleDtoSearch.value);
        const headers = {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'X-AUTH-TOKEN': 'Bearer ' + token
        };
        fetch(url, { method, headers, body })
            .then(async (response) => {
                resultDtoSearch.value = await response.json();

                if (response.status > SERVER_STATUS_ERROR) {
                    message.value = getErrorMessage(BLANK, INQUIRE_FLG);
                    infoLevel.value = MessageConstants.LEVEL_ERROR;
                    messageType.value = MessageConstants.VIEW_OK;
                    return;
                }

                if (resultDtoSearch.value.listEntity.length == 0) {
                    infoLevel.value = MessageConstants.LEVEL_WARNING;
                    messageType.value = MessageConstants.VIEW_OK;
                    message.value = "検索結果がありませんでした";
                    return;
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


});


function recieveSubmit() {
    infoLevel.value = 0;
    messageType.value = 0;
}


function getStatus(status: number): string {
    switch (status) {
        //  未定 
        case 0:
            return "未定";

        // 申請中 
        case 1:
            return "申請中";

        // 却下 
        case 2:
            return "却下";

        // 承認 
        case 3:
            return "承認";

        // 追加調査 
        case 4:
            return "追加調査";
        default:
            return "";
    }
}

function onCancel() {
    history.back();
}

function onSave() {
    capsuleDto.value.userDto = userDto.value;

    if (capsuleDto.value.taskPlanId === 0) {
        infoLevel.value = MessageConstants.LEVEL_WARNING;
        messageType.value = MessageConstants.VIEW_OK;
        message.value = "紐づくタスク計画が存在しません(もしくは完了済です)";
        return;
    }

    getAuthorizedPromiseArea().then(token => {
        const url = urlBack + "/task-plan/update-success";
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

                if (response.status > SERVER_STATUS_ERROR) {
                    message.value = getErrorMessage(resultDto.message, ERR_MESS_ONLY);
                    infoLevel.value = MessageConstants.LEVEL_ERROR;
                    messageType.value = MessageConstants.VIEW_OK;
                    return;
                }

                if (resultDto.isFailure) {
                    infoLevel.value = MessageConstants.LEVEL_WARNING;
                    messageType.value = MessageConstants.VIEW_OK;
                    message.value = resultDto.message;
                } else {
                    infoLevel.value = MessageConstants.LEVEL_INFO;
                    messageType.value = MessageConstants.VIEW_TOAST;
                    message.value = resultDto.message;
                    // 現在の遷移情報を消去
                    const storesTaskPlan = useTaskPlan();
                    storesTaskPlan.taskPlanId = INIT_NUMBER;
                    storesTaskPlan.taskYear = INIT_NUMBER;
                    capsuleDto.value.taskPlanId = INIT_NUMBER;
                    capsuleDto.value.taskYear = INIT_NUMBER;
                    // 次回メニュー呼び出し時には更新
                    const notCompletedTaskInfo = notCompletedTaskStore();
                    notCompletedTaskInfo.notCompleteTaskDto.isRefreshed = false;
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
</script>
<template>
    <!-- 関連者メニュー兼チェック -->
    <KanrenshaInfo :user-dto="userDto"></KanrenshaInfo>

    <h1>関連者コード移動申請履歴(自分自身)</h1><br>

    <h3 class="accent-h3">申請履歴</h3><br>

    <div class="one-line">
        <table>
            <tbody>
                <tr>
                    <th>関連者区分</th>
                    <th>状態</th>
                    <th>廃止コード(申請者)</th>
                    <th>併合コード</th>
                </tr>
                <tr v-for="(entity, index) of resultDtoSearch.listEntity" :key="index">
                    <td>{{ KanrenshaKbnConstants.getLabel(entity.kanrenshaKbn) }}</td>
                    <td>{{ getStatus(entity.moveStatus) }}</td>
                    <td>{{ entity.abolishKanrenshaCode }} <br> {{ entity.abolishKanrenshaName }}</td>
                    <td>{{ entity.originKanrenshaCode }} <br> {{ entity.originName }} </td>
                </tr>
            </tbody>
        </table>
    </div>

    <div class="one-line" v-if="isTaskTransfer">
        <div class="left-area">
            内容を確認したので<br>予定を終了
        </div>
        <div class="right-area">
            <button @click="onSave">終了にする</button>
        </div>
    </div>

    <div class="footer">
        <button @click="onCancel" class="footer-button">キャンセル</button>
    </div>

    <!-- メッセージ表示    -->
    <div class="overMessage" v-if="messageType !== MessageConstants.VIEW_NONE">
        <MessageView :info-level="infoLevel" :message-type="messageType" :title="MESS_PAGE_NAME" :message="message"
            :caller="caller" @send-submit="recieveSubmit">
        </MessageView>
    </div>

</template>
<style scoped></style>
