<script setup lang="ts">
import { computed, onBeforeMount, ref, type ComputedRef, type Ref } from 'vue';
import { FrameworkCapsuleDto, type FrameworkCapsuleDtoInterface, type LeastUserDtoInterface } from 'seijishikin-jp-normalize_common-tool';
import { MessageConstants, MessageView } from 'seijishikin-jp-normalize_common-tool';
import router from '../../../../router';
import RoutePathConstants from '../../../../routePathConstants';
import UserRoleConstants from '../../dto/user/userRoleConstants';
import AllMenu from '../../../main/common/menu/AllMenu.vue';
import type { SelectOptionStringDtoInterface } from '../../dto/select_options/selectOptionStringDto';
import { createListRoleOptions } from '../menu/createListRoleOptions';
import PersonMenu from '../../../main/common/menu/PersonMenu.vue';
import { TaskListForUserInfoResultDto, type TaskListForUserInfoResultDtoInterface } from '../../dto/task_plan/taskListForUserInfoResultDto';
import { notCompletedTaskStore } from '../../stores/notCompletedTask';
import getAuthorizedPromiseArea from '../../dto/login/getAuthorizedPromiseArea';
import convertTaskToOption from '../../dto/task_plan/convertTaskToOptions';
import { AccessTokenNotFoundError, TokenRefreshError } from '../../dto/login/errors';
import ShowTask from '../show_task/ShowTask.vue';

// props,emmits
const props = defineProps<{ userDto: LeastUserDtoInterface }>();

// よく使う定数
const BLANK: string = "";
const INIT_NUMBER: number = 0;
const SERVER_STATUS_OK: number = 200;
// const SERVER_STATUS_ERROR: number = 400;
// メッセージ表示定数
const infoLevel: Ref<number> = ref(MessageConstants.LEVEL_NONE);
const messageType: Ref<number> = ref(MessageConstants.VIEW_NONE);
const title: Ref<string> = ref(BLANK);
const message: Ref<string> = ref(BLANK);

// back側アクセス
const urlBack: string = RoutePathConstants.DOMAIN + RoutePathConstants.BASE_PATH;

// pinia
const notCompletedTaskInfo = notCompletedTaskStore();

const listMenuRoleOptions: Ref<SelectOptionStringDtoInterface[]> = ref(createListRoleOptions(props.userDto.listRoles));

const viewMenuRole: Ref<string> = ref(BLANK);
const isVewAllMenu: Ref<boolean> = ref(false);
const isVewPersonMenu: Ref<boolean> = ref(false);

function viewAllMenu() {
    isVewPersonMenu.value = false;
    if (BLANK === viewMenuRole.value) {
        // 非表示
        isVewAllMenu.value = false;
    } else {
        // 表示
        isVewAllMenu.value = true;
    }
}

function recieveCanceelAllMenu() {
    // 非表示
    viewMenuRole.value = BLANK;
    isVewAllMenu.value = false;
}

function recieveCanceelPersonMenu() {
    // 非表示
    isVewPersonMenu.value = false;
}

function viewPersonMenu() {
    // 個人メニュー作成
    isVewPersonMenu.value = true;
    isVewAllMenu.value = false;
}


// 未処理タスク表示
const resultDtoTask: Ref<TaskListForUserInfoResultDtoInterface> = ref(new TaskListForUserInfoResultDto());
const optionsThisYear: Ref<SelectOptionStringDtoInterface[]> = ref([]);
const optionsLastYear: Ref<SelectOptionStringDtoInterface[]> = ref([]);
const optionsView: ComputedRef<SelectOptionStringDtoInterface[]> = computed(() => {
    if ("1" === switchYear.value) {
        return optionsThisYear.value;
    } else {
        return optionsLastYear.value;
    }
});
const selectedTask: Ref<string> = ref("");
const switchYear: Ref<string> = ref("");
const tansferDisabled: ComputedRef<boolean> = computed(() => BLANK === selectedTask.value);
let actionStatus = INIT_NUMBER;
onBeforeMount(async () => {
    // ログインと権限チェック
    if (INIT_NUMBER === props.userDto.userPersonId || !props.userDto.listRoles.includes(UserRoleConstants.ROLE_ADMIN)) {
        infoLevel.value = MessageConstants.LEVEL_ERROR;
        messageType.value = MessageConstants.VIEW_OK;
        title.value = "ログイン状態またはAPIパートナー権限が確認できませんでした";
        message.value = "ログアウト処理をします。再度ログイン処理をするかシステム担当者にお問い合わせください";
    }

    // 未処理タスクが最新でなければ更新
    if (notCompletedTaskInfo !== null) {
        if (!notCompletedTaskInfo.notCompleteTaskDto.isRefreshed) {
            // 更新処理
            const capsuleDto: FrameworkCapsuleDtoInterface = new FrameworkCapsuleDto();
            capsuleDto.userDto = props.userDto;
            // 検索実行
            getAuthorizedPromiseArea().then(token => {
                const url = urlBack + "/task-plan/get-not-finished";
                const method = "POST";
                const body = JSON.stringify(capsuleDto);
                const headers = {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json',
                    'X-AUTH-TOKEN': 'Bearer ' + token
                };
                fetch(url, { method, headers, body })
                    .then(async (response) => {

                        resultDtoTask.value = await response.json();
                        if (resultDtoTask.value.listThisYear.length === 0 && resultDtoTask.value.listLastYear.length === 0) {
                            infoLevel.value = MessageConstants.LEVEL_INFO;
                            messageType.value = MessageConstants.VIEW_TOAST;
                            title.value = "未処理タスク確認";
                            message.value = "未処理タスクは存在しませんでした";
                            notCompletedTaskInfo.notCompleteTaskDto.isRefreshed = true; // 毎回更新しにいかないように
                            actionStatus = SERVER_STATUS_OK;
                        } else {
                            notCompletedTaskInfo.notCompleteTaskDto = resultDtoTask.value;
                            optionsThisYear.value = convertTaskToOption(resultDtoTask.value.listThisYear);
                            optionsLastYear.value = convertTaskToOption(resultDtoTask.value.listLastYear);
                            switchYear.value = "1";
                        }
                    })
                    .catch((e) => {
                        infoLevel.value = MessageConstants.LEVEL_ERROR;
                        messageType.value = MessageConstants.VIEW_OK;
                        title.value = "システムエラーが発生しました";
                        message.value = e.message;
                    });
            }).catch((e) => {
                if (e instanceof AccessTokenNotFoundError) {
                    // トークン保持ができていない場合
                    infoLevel.value = MessageConstants.LEVEL_ERROR;
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
        } else {
            optionsThisYear.value = convertTaskToOption(notCompletedTaskInfo.notCompleteTaskDto.listThisYear);
            optionsLastYear.value = convertTaskToOption(notCompletedTaskInfo.notCompleteTaskDto.listLastYear);
            notCompletedTaskInfo.notCompleteTaskDto.isRefreshed = true;
            switchYear.value = "1";
        }
    }

});

// メッセージからの反応受け取り
function recieveSubmit(button: string) {
    console.log(button); // 警告除け
    // TODO ボタンタイプ別の挙動はこの中で変える

    // 非表示
    infoLevel.value = 0;
    messageType.value = 0;

    // 正常アクセスができないときはログアウトする
    if (SERVER_STATUS_OK !== actionStatus) {
        router.push(RoutePathConstants.PAGE_LOGOUT);
    }
    actionStatus = INIT_NUMBER;
}

// タスク表示
const isShowTask: Ref<Boolean> = ref(false);
function onTaskView() {
    isShowTask.value = true;
}
function recieveCancelShowTask() {
    isShowTask.value = false;
}

function onTransfer() {
    // ページ遷移
    router.push(RoutePathConstants.BASE_PATH + selectedTask.value);
}

</script>
<template>
    <!-- ユーザrole別制御コンポーネント -->
    <div class="user-role-container-admin">
        <div class="user-role-content">
            <div class="user-role-title">
                <span class="user-role-text">SE権限</span><br>
                {{ props.userDto.userPersonName }}さん
            </div>
            <div class="user-role-task left-space">
                <input type="radio" v-model="switchYear" value="1" id="test">本年{{ optionsThisYear.length - 1 }}件
                <input type="radio" v-model="switchYear" value="2" id="test">前年{{ optionsLastYear.length - 1 }}件
                <select v-model="selectedTask" class="left-space">
                    <option v-for="option in optionsView" :value="option.value">{{ option.text }}</option>
                </select>
                <button @click="onTransfer" :disabled="tansferDisabled"
                    class="left-space-narrow user-role-transfer-button">遷移</button><br>
                <button @click="onTaskView"
                    class="user-role-transfer-button user-role-transfer-button-margin-top">未処理タスクをもっと見る</button>
            </div>
            <!-- 遷移メニュー -->
            <div class="user-role-menu-wrapper">
                <div class="left-space">
                    遷移メニュー <select class="left-space" v-model="viewMenuRole" @change="viewAllMenu">
                        <option v-for="dto of listMenuRoleOptions" :key="dto.value" :value="dto.value">{{ dto.text }}
                        </option>
                    </select>&nbsp;
                </div>
            </div>
            <div class="left-space user-role-icon-container" @click="viewPersonMenu">
                <img src="#" class="user-role-icon">
            </div>
        </div>
    </div>

    <!-- メッセージ表示 -->
    <div class="overMessage" v-if="messageType !== MessageConstants.VIEW_NONE">
        <MessageView :info-level="infoLevel" :message-type="messageType" :title="title" :message="message"
            @send-submit="recieveSubmit">
        </MessageView>
    </div>

    <!-- メニュー表示 -->
    <div class="overComponentLayer2" v-if="isVewAllMenu">
        <AllMenu :view-role="viewMenuRole" @send-canceel-menu="recieveCanceelAllMenu"></AllMenu>
    </div>

    <!-- 個人メニュー表示 -->
    <div class="personMenuLayer" v-if="isVewPersonMenu">
        <PersonMenu :view-role="UserRoleConstants.ROLE_ADMIN" @send-canceel-menu="recieveCanceelPersonMenu">
        </PersonMenu>
    </div>

    <!-- タスク表示 -->
    <div v-if="isShowTask" class="overComponent">
        <ShowTask :is-search-condition="false" :user-dto="userDto" @send-canceel-show-task="recieveCancelShowTask">
        </ShowTask>
    </div>

</template>
<style scoped></style>
