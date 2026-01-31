<script setup lang="ts">
import { computed, onBeforeMount, ref, type ComputedRef, type Ref } from 'vue';
import { SearchTaskPlanCapsuleDto, type SearchTaskPlanCapsuleDtoInterface } from '../../dto/task_plan/searchTaskPlanCapsuleDto';
import { convertDatetimeText, InputDatetime, MessageConstants, MessageView, PagingControl, type LeastUserDtoInterface } from 'seijishikin-jp-normalize_common-tool';
import UserRoleConstants from '../../dto/user/userRoleConstants';
import { SearchTaskPlanResultDto, type SearchTaskPlanResultDtoInterface } from '../../dto/task_plan/searchTaskPlanResultDto';
import { SearchTaskHistoryResultDto, type SearchTaskHistoryResultDtoInterface } from '../../dto/task_plan/searchTaskHistoryResultDto';
import getAuthorizedPromiseArea from '../../dto/login/getAuthorizedPromiseArea';
import RoutePathConstants from '../../../../routePathConstants';
import { AccessTokenNotFoundError, TokenRefreshError } from '../../dto/login/errors';
import { SearchTaskHistoryCapsuleDto, type SearchTaskHistoryCapsuleDtoInterface } from '../../dto/task_plan/searchTaskHistoryCapsuleDto';
import DownloadStackTrace from './DownloadStackTrace.vue';
import type { TaskInfoCodeCheckOptionDtoInterface } from '../../dto/task_plan/taskInfoCodeCheckOptionDto';
import { getTaskCheckboxListCategory0, getTaskCheckboxListCategory3, getTaskCheckboxListCategory9 } from '../../dto/task_plan/getTaskCheckboxList';
import router from '../../../../router';

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

    // タスクコードリストを設定
    capsuleDto.value.infoCodeList.splice(0);
    capsuleDto.value.infoCodeList = createCodeList();

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
                } else {
                    infoLevel.value = MessageConstants.LEVEL_INFO;
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

const listCategory0: Ref<TaskInfoCodeCheckOptionDtoInterface[]> = ref(getTaskCheckboxListCategory0());
const listCategory3: Ref<TaskInfoCodeCheckOptionDtoInterface[]> = ref(getTaskCheckboxListCategory3());
const listCategory9: Ref<TaskInfoCodeCheckOptionDtoInterface[]> = ref(getTaskCheckboxListCategory9());

// 検索条件を入力しないときは2年間の未処理タスク
onBeforeMount(() => {
    // タスクコードリストを設定
    capsuleDto.value.infoCodeList.splice(0);
    capsuleDto.value.infoCodeList = createCodeList();

    if (!props.isSearchCondition) {

        // 検索期間は前年初頭から今年末
        const year: number = new Date().getFullYear();
        capsuleDto.value.startDate = new Date((year - 1) + "-01-01");
        capsuleDto.value.startDate.setHours(0);
        capsuleDto.value.startDate.setMinutes(0);
        capsuleDto.value.startDate.setSeconds(0);

        capsuleDto.value.startDate.setHours(9, 0, 0, 0);
        capsuleDto.value.endDate = new Date((year) + "-12-31");
        capsuleDto.value.endDate.setHours(32, 59, 59, 0);

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


function createCodeList(): number[] {
    const list: number[] = [];
    for (const dto of listCategory0.value) {
        if (dto.isChecked) {
            list.push(dto.codeValue);
        }
    }
    for (const dto of listCategory3.value) {
        if (dto.isChecked) {
            list.push(dto.codeValue);
        }
    }
    for (const dto of listCategory9.value) {
        if (dto.isChecked) {
            list.push(dto.codeValue);
        }
    }
    return list;
}

// タスクコードチェック編集
const isTaskCodeCheck: Ref<boolean> = ref(false); //初期状態非表示
function onInfoCodeCheck() {
    isTaskCodeCheck.value = !isTaskCodeCheck.value;
}

const flgAllCheck1: Ref<boolean> = ref(true);
function onAllCheck0() {
    const ans = flgAllCheck1.value;
    for (const dto of listCategory0.value) {
        dto.isChecked = ans
    }
}

const flgAllCheck3: Ref<boolean> = ref(true);
function onAllCheck3() {
    const ans = flgAllCheck3.value;
    for (const dto of listCategory3.value) {
        dto.isChecked = ans
    }
}

const flgAllCheck9: Ref<boolean> = ref(true);
function onAllCheck9() {
    const ans = flgAllCheck9.value;
    for (const dto of listCategory9.value) {
        dto.isChecked = ans
    }
}

// コンポーネントから時刻受け取り
function recieveDatetime(date: Date, index: number) {
    if (1 == index) {
        capsuleDto.value.startDate = date;
    }
    if (2 == index) {
        capsuleDto.value.endDate = date;
    }
}

function onTransfer(path: string) {
    router.push(RoutePathConstants.BASE_PATH + path);
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
                <InputDatetime :datetime="capsuleDto.startDate" :index="1" :is-edit="true"
                    @send-date-time="recieveDatetime"></InputDatetime>
                <span>&nbsp;から&nbsp;</span>
                <InputDatetime :datetime="capsuleDto.endDate" :index="2" :is-edit="true"
                    @send-date-time="recieveDatetime"></InputDatetime>
                <span>&nbsp;まで</span>
            </div>
        </div>

        <div class=" one-line">
            <div class="left-area">
                タスク着手
            </div>
            <div class="right-area">
                <div class="form-group-vertical">
                    <div>
                        <span>終了条件：</span>
                        <input type="radio" v-model="capsuleDto.flgFinished" value="2" class="left-space">指定なし
                        <input type="radio" v-model="capsuleDto.flgFinished" value="1" class="left-space">終了した
                        のみ
                        <input type="radio" v-model="capsuleDto.flgFinished" value="0" class="left-space">終了していない のみ
                    </div>
                    <div>
                        <span>開始条件：</span>
                        <input type="radio" v-model="capsuleDto.flgStart" value="2" class="left-space">指定なし
                        <input type="radio" v-model="capsuleDto.flgStart" value="1" class="left-space">開始した のみ
                        <input type="radio" v-model="capsuleDto.flgStart" value="0" class="left-space">開始していない
                        のみ
                    </div>
                    <div>
                        <span>中断条件：</span>
                        <input type="radio" v-model="capsuleDto.flgSuspended" value="2" class="left-space">指定なし
                        <input type="radio" v-model="capsuleDto.flgSuspended" value="1" class="left-space">中断した
                        のみ
                        <input type="radio" v-model="capsuleDto.flgSuspended" value="0" class="left-space">中断していない のみ
                    </div>
                </div>
            </div>
        </div>

        <div class="one-line">
            <div class="left-area">
                タスクの名称
            </div>
            <div class="right-area">
                <input type="texr" v-model="capsuleDto.searchTaskWord" placeholder="タスク名称自由記述"></input>
            </div>
        </div>

        <!-- TODO タスクの種類はさらに種類が確定するまで調整 -->
        <div class="one-line">
            <div class="left-area">
                タスクの種類
            </div>
            <div class="right-area">
                <div class="form-group-vertical">
                    <div> <button @click="onInfoCodeCheck">指定するので展開</button></div>
                    <div v-if="isTaskCodeCheck">
                        <div>
                            <input type="checkbox" v-model="flgAllCheck1" @change="onAllCheck0()">グループ1すべて
                            <div>
                                <span v-for="dto in listCategory0" class="left-space">
                                    <input type="checkbox" v-model="dto.isChecked">{{ dto.codeName }}
                                </span>
                            </div>
                        </div>
                        <div> <input type="checkbox" v-model="flgAllCheck3" @change="onAllCheck3()">グループ3すべて
                            <div>
                                <span v-for="dto in listCategory3" class="left-space">
                                    <input type="checkbox" v-model="dto.isChecked">{{ dto.codeName }}
                                </span>
                            </div>
                        </div>
                        <div> <input type="checkbox" v-model="flgAllCheck9" @change="onAllCheck9()">グループ9すべて
                        </div>
                        <div>
                            <span v-for="dto in listCategory9" class="left-space">
                                <input type="checkbox" v-model="dto.isChecked">{{ dto.codeName }}
                            </span>
                        </div>
                    </div>
                </div>
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
                    <th>遷移</th>
                    <th v-if="isGetTrace">&nbsp;</th>
                </tr>
                <tr v-for="entity of resultDto.listTaskPlan">
                    <td>{{ entity.tableYear }}</td>
                    <td><button class="left-space" @click="onShowHistory(entity.taskPlanCode, entity.tableYear)">履歴({{
                        entity.taskPlanCode
                            }})</button> </td>
                    <td>{{ entity.taskPlanName }}</td>
                    <td>{{ convertDatetimeText(entity.insertTimestamp) }}</td>
                    <td>{{ getStateText(entity.isStart, "開始") }}<br><span v-if="entity.isStart"> {{
                        convertDatetimeText(entity.startDatetime) }}</span></td>
                    <td>{{ getStateText(entity.isFinished, "終了") }}<br><span v-if="entity.isFinished"> {{
                        convertDatetimeText(entity.endDatetime) }}</span>
                    </td>
                    <td>{{ getStateText(entity.isSuspended, "中断") }}<br><span v-if="entity.isSuspended">{{
                        convertDatetimeText(entity.endDatetime) }}</span>
                    </td>
                    <td>
                        <button @click="onTransfer(entity.transferPass)"
                            :disabled="entity.transferPass == ''">遷移</button>
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
                    <td>{{ convertDatetimeText(entity.insertTimestamp) }}</td>
                    <td>{{ getStateText(entity.isStart, "開始") }}<br><span v-if="entity.isStart">{{
                        convertDatetimeText(entity.startDatetime)
                            }}</span></td>
                    <td>{{ getStateText(entity.isFinished, "終了") }}<br><span v-if="entity.isFinished"> {{
                        convertDatetimeText(entity.endDatetime) }}</span>
                    </td>
                    <td>{{ getStateText(entity.isSuspended, "中断") }}<br><span v-if="entity.isSuspended">{{
                        convertDatetimeText(entity.endDatetime) }}</span>
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
