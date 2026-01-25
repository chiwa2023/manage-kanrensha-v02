<script setup lang="ts">
import { computed, onBeforeMount, ref, type ComputedRef, type Ref } from 'vue';
import { SearchTaskPlanCapsuleDto, type SearchTaskPlanCapsuleDtoInterface } from '../../dto/task_plan/searchTaskPlanCapsuleDto';
import { MessageConstants, MessageView, PagingControl, type LeastUserDtoInterface } from 'seijishikin-jp-normalize_common-tool';
import UserRoleConstants from '../../dto/user/userRoleConstants';
import { SearchTaskPlanResultDto, type SearchTaskPlanResultDtoInterface } from '../../dto/task_plan/searchTaskPlanResultDto';
import { SearchTaskHistoryResultDto, type SearchTaskHistoryResultDtoInterface } from '../../dto/task_plan/searchTaskHistoryResultDto';
import DateTime from '../util/DateTime.vue';
import getAuthorizedPromiseArea from '../../dto/login/getAuthorizedPromiseArea';
import RoutePathConstants from '../../../../routePathConstants';
import { AccessTokenNotFoundError, TokenRefreshError } from '../../dto/login/errors';
import { SearchTaskHistoryCapsuleDto, type SearchTaskHistoryCapsuleDtoInterface } from '../../dto/task_plan/searchTaskHistoryCapsuleDto';
import DownloadStackTrace from './DownloadStackTrace.vue';

// props,emmits
const props = defineProps<{ isSearchCondition: boolean, userDto: LeastUserDtoInterface }>();
const emits = defineEmits(["sendCanceelShowTask"]);

// back側アクセス
const urlBack: string = RoutePathConstants.DOMAIN + RoutePathConstants.BASE_PATH;

// よく使う定数
const BLANK: string = "";
// const INIT_NUMBER: number = 0;
const SEARCH_LIMIT: number = 20;
const SERVER_STATUS_OK: number = 200;
// const SERVER_STATUS_ERROR: number = 400;
// メッセージボックス表示定数
const infoLevel: Ref<number> = ref(MessageConstants.LEVEL_NONE);
const messageType: Ref<number> = ref(MessageConstants.VIEW_NONE);
const title: Ref<string> = ref(BLANK);
const message: Ref<string> = ref(BLANK);
// Paging
const pageNumber: Ref<number> = ref(0); // Mock data
const allCount: Ref<number> = ref(0); // Mock data
const limit: Ref<number> = ref(SEARCH_LIMIT); // Mock data


// タスク検索条件
const capsuleDto: Ref<SearchTaskPlanCapsuleDtoInterface> = ref(new SearchTaskPlanCapsuleDto());
capsuleDto.value.userDto = props.userDto;
capsuleDto.value.limit = SEARCH_LIMIT;

// StackTraceが取得できるのは管理者だけ
const isGetTrace: ComputedRef<boolean> = computed(
    () => props.userDto.listRoles.includes(UserRoleConstants.ROLE_ADMIN));


// ページング


// 検索結果リスト
const resultDto: Ref<SearchTaskPlanResultDtoInterface> = ref(new SearchTaskPlanResultDto());
// 履歴用リスト
const resultHistoryDto: Ref<SearchTaskHistoryResultDtoInterface> = ref(new SearchTaskHistoryResultDto());

function onSearch() {
    // TODO タスクの種類を決定したらチェックボックスを設定し
    // タスクの種類のnumber配列に変換

    // 検索実行
    getAuthorizedPromiseArea().then(token => {
        const url = urlBack + "/task-plan/search";
        const method = "POST";
        const body = JSON.stringify(capsuleDto.value);
        const headers = {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'X-AUTH-TOKEN': 'Bearer ' + token
        };
        fetch(url, { method, headers, body })
            .then(async (response) => {
                resultDto.value = await response.json();
                if (SERVER_STATUS_OK === response.status) {
                    allCount.value = resultDto.value.allCount;
                    pageNumber.value = resultDto.value.pageNumber;
                }else{
                    infoLevel.value = MessageConstants.LEVEL_INFO;
                    // トークン保持ができていない場合
                    messageType.value = MessageConstants.VIEW_TOAST;
                    title.value = "検索結果が存在しませんでした";
                    message.value = "検索条件を変えて試してください";
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
                alert(e);
            });
    });
}

function onShowHistory(selectedCode: number, taskYear: number) {

    const capsuleDtoHistory: SearchTaskHistoryCapsuleDtoInterface = new SearchTaskHistoryCapsuleDto();
    capsuleDtoHistory.taskYear = taskYear;
    capsuleDtoHistory.taskPlanCode = selectedCode;

    // 履歴検索実行
    getAuthorizedPromiseArea().then(token => {
        const url = urlBack + "/task-plan/search-history";
        const method = "POST";
        const body = JSON.stringify(capsuleDtoHistory);
        const headers = {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'X-AUTH-TOKEN': 'Bearer ' + token
        };
        fetch(url, { method, headers, body })
            .then(async (response) => {
                resultHistoryDto.value = await response.json();
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
                alert(e);
            });
    });


}

function getStateText(isState: boolean, column: string): string {
    return column + (isState ? "しています" : "していません");
}

// 検索条件を入力しないときは無条件で本日から1か月に実行した最新タスク
onBeforeMount(() => {
    if (!props.isSearchCondition) {
        onSearch();
    }
});

function onCancel() {
    emits("sendCanceelShowTask");
}

function recieveSubmit(button: string) {
    console.log(button); // 警告除け
    infoLevel.value = 0;
    messageType.value = 0;
}

function recievePagingNumber(selecteddNumber: number) {
    capsuleDto.value.pageNumber = selecteddNumber;
    onSearch();
}

</script>
<template>
    <div v-if="!isSearchCondition">
        <h3 class="accent-h3">タスク表示</h3><br>
    </div>

    <div v-if="isSearchCondition">
        <h3>検索条件入力</h3>

        <div class="one-line">
            <div class="left-area">
                検索期間
            </div>
            <div class="right-area">
                <DateTime :datetime="capsuleDto.startDate" :index="1" :is-edit="true"></DateTime>
                <span>&nbsp;から&nbsp;</span>
                <DateTime :datetime="capsuleDto.endDate" :index="2" :is-edit="true"></DateTime>
                <span>&nbsp;まで</span>
            </div>
        </div>

        <div class="one-line">
            <div class="left-area">
                タスク
            </div>
            <div class="right-area">
                タスクの種類複数選択
                <br>
                <input type="texr" v-model="capsuleDto.searchTaskWord" placeholder="タスク名称自由記述"></input>
            </div>
        </div>

        <div class="one-line">
            <div class="left-area">
                検索
            </div>
            <div class="right-area">
                <button @click="onSearch">検索</button>
            </div>
        </div>
    </div>

    <h3>検索結果表示</h3>
    <div class="one-line-scroll">
        <!-- ページング -->
        <table>
            <tbody>
                <tr>
                    <th>実行年</th>
                    <th>タスクコード</th>
                    <th>タスク名称</th>
                    <th>更新</th>
                    <th>開始</th>
                    <th>終了</th>
                    <th>途中停止</th>
                    <th v-if="isGetTrace">&nbsp;</th>
                </tr>
                <tr v-for="entity of resultDto.listTaskPlan">
                    <td>{{ entity.tableYear }}</td>
                    <td><button class="left-space" @click="onShowHistory(entity.taskPlanCode, entity.tableYear)">履歴({{
                        entity.taskPlanCode
                            }})</button> </td>
                    <td>{{ entity.taskPlanName }}</td>
                    <td>{{ entity.insertTimestamp }}</td>
                    <td>{{ getStateText(entity.isStart, "開始") }}<br><span v-if="entity.isStart"> {{
                        entity.startDatetime }}</span></td>
                    <td>{{ getStateText(entity.isFinished, "終了") }}<br><span v-if="entity.isFinished"> {{
                        entity.endDatetime }}</span>
                    </td>
                    <td>{{ getStateText(entity.isSuspended, "中断") }}<br><span v-if="entity.isSuspended">{{
                        entity.endDatetime }}</span>
                    </td>
                    <td v-if="isGetTrace">
                        <DownloadStackTrace :task-plan-code="entity.taskPlanCode" :task-year="entity.tableYear"
                            :user-dto="props.userDto"></DownloadStackTrace>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
    <!-- ページング -->
    <PagingControl :all-count="allCount" :limit="limit" :page-number="pageNumber"
        @send-paging-number="recievePagingNumber"></PagingControl>

    <h3>履歴</h3>
    <div class="one-line">
        <table>
            <tbody>
                <tr>
                    <th>実行年</th>
                    <th>タスクコード</th>
                    <th>タスク名称</th>
                    <th>作成</th>
                    <th>開始</th>
                    <th>終了</th>
                    <th>途中停止</th>
                </tr>
                <tr v-for="entity of resultHistoryDto.listTaskHistory">
                    <td>{{ entity.tableYear }}</td>
                    <td>{{ entity.taskPlanCode }}</td>
                    <td>{{ entity.taskPlanName }}</td>
                    <td>{{ entity.insertTimestamp }}</td>
                    <td>{{ getStateText(entity.isStart, "開始") }}<br><span v-if="entity.isStart">{{ entity.startDatetime
                    }}</span></td>
                    <td>{{ getStateText(entity.isFinished, "終了") }}<br><span v-if="entity.isFinished"> {{
                        entity.endDatetime }}</span>
                    </td>
                    <td>{{ getStateText(entity.isSuspended, "中断") }}<br><span v-if="entity.isSuspended">{{
                        entity.endDatetime }}</span>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>

    <div class="footer">
        <button @click="onCancel" class="footer-button">キャンセル</button>
    </div>

    <!-- メッセージ表示 -->
    <div class="overMessage" v-if="messageType !== MessageConstants.VIEW_NONE">
        <MessageView :info-level="infoLevel" :message-type="messageType" :title="title" :message="message"
            @send-submit="recieveSubmit">
        </MessageView>
    </div>

</template>
<style scoped></style>
