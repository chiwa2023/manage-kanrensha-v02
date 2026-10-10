<script setup lang="ts">
import { computed, onMounted, ref, type ComputedRef, type Ref } from 'vue';
import { SearchTaskPlanCapsuleDto, type SearchTaskPlanCapsuleDtoInterface } from '../../dto/task_plan/searchTaskPlanCapsuleDto.ts';
import { convertDatetimeText, DtoEntityConstants, getErrorMessage, InputDatetime, MessageConstants, MessageView, PagingControl, type FrameworkMessageAndResultDtoInterface, type LeastUserDtoInterface } from 'seijishikin-jp-normalize_common-tool';
import UserRoleConstants from '../../dto/user/userRoleConstants.ts';
import { SearchTaskPlanResultDto, type SearchTaskPlanResultDtoInterface } from '../../dto/task_plan/searchTaskPlanResultDto.ts';
import { SearchTaskHistoryResultDto, type SearchTaskHistoryResultDtoInterface } from '../../dto/task_plan/searchTaskHistoryResultDto.ts';
import getAuthorizedPromiseArea from '../../dto/login/getAuthorizedPromiseArea.ts';
import RoutePathConstants from '../../../../routePathConstants.ts';
import { AccessTokenNotFoundError, TokenRefreshError } from '../../dto/login/errors.ts';
import { SearchTaskHistoryCapsuleDto, type SearchTaskHistoryCapsuleDtoInterface } from '../../dto/task_plan/searchTaskHistoryCapsuleDto.ts';
import DownloadStackTrace from './DownloadStackTrace.vue';
import { UpdateTaskPlanSimpleCapsuleDto, type UpdateTaskPlanSimpleCapsuleDtoInterface } from '../../dto/task_plan/updateTaskPlanSimpleCapsuleDto.ts';
import { type TaskPlanBaseEntityInterface } from '../../entity/taskPlanBaseEntity.ts';
import router from '../../../../router.ts';

// props,emmits
const props = defineProps<{ isSearchCondition: boolean, userDto: LeastUserDtoInterface }>();
const emits = defineEmits(["sendCanceelShowTask"]);

// back側アクセス
const urlBack: string = RoutePathConstants.DOMAIN + RoutePathConstants.BASE_PATH;

// よく使う定数
const BLANK: string = "";
const INIT_NUMBER: number = 0;
const SEARCH_LIMIT: number = 20;
// const SERVER_STATUS_OK: number = 200;
const SERVER_STATUS_ERROR: number = 400;
const INQUIRE_FLG: boolean = false;
const ERR_MESS_ONLY: boolean = true;
const MESS_PAGE_NAME: string = "タスク計画表示";
const INIT_CALLER: string = "no branch";

// メッセージボックス表示定数
const infoLevel: Ref<number> = ref(MessageConstants.LEVEL_NONE);
const messageType: Ref<number> = ref(MessageConstants.VIEW_NONE);
const caller: Ref<string> = ref(INIT_CALLER);
const message: Ref<string> = ref(BLANK);

// Paging
const pageNumber: Ref<number> = ref(INIT_NUMBER);
const allCount: Ref<number> = ref(INIT_NUMBER);
const limit: Ref<number> = ref(SEARCH_LIMIT);

// タスク検索条件
const capsuleDto: Ref<SearchTaskPlanCapsuleDtoInterface> = ref(new SearchTaskPlanCapsuleDto());
capsuleDto.value.userDto = props.userDto;
capsuleDto.value.limit = SEARCH_LIMIT;

// StackTraceが取得できるのは管理者だけ
const isGetTrace: ComputedRef<boolean> = computed(
    () => props.userDto.listRoles.includes(UserRoleConstants.ROLE_ADMIN));

// 日付コンポーネント不正値
const LIMIT_DATE = DtoEntityConstants.INIT_DATETIME_LIMIT;

// 検索結果リスト
const resultDto: Ref<SearchTaskPlanResultDtoInterface> = ref(new SearchTaskPlanResultDto());
// 履歴用リスト
const resultHistoryDto: Ref<SearchTaskHistoryResultDtoInterface> = ref(new SearchTaskHistoryResultDto());

onMounted(() => {

    // 権限タスクを表示の際は一般タスクで行っていたタスクリスト取得がまるっと不要

    // 検索条件を作成しないときは指定条件で検索
    if (!props.isSearchCondition) {

        // 検索期間は前年初頭から今年末
        const year: number = new Date().getFullYear();
        capsuleDto.value.startDate = new Date((year - 1) + "-01-01");
        capsuleDto.value.startDate.setHours(0);
        capsuleDto.value.startDate.setMinutes(0);
        capsuleDto.value.startDate.setSeconds(0);
        capsuleDto.value.startDate.setHours(9, 0, 0, 0);

        capsuleDto.value.endDate = new Date((year) + "-12-31");
        capsuleDto.value.endDate.setHours(23, 59, 59, 0);
        capsuleDto.value.flgFinished = 0;
        capsuleDto.value.flgSuspended = 0;
        capsuleDto.value.flgStart = 2;

        onSearch();
    }
});


function onSearch() {

    // 日時コンポーネントエラー検出
    if (capsuleDto.value.startDate <= LIMIT_DATE) {
        infoLevel.value = MessageConstants.LEVEL_WARNING;
        messageType.value = MessageConstants.VIEW_OK;
        message.value = "開始日時入力が不正です。入力しなおしてください";
        return;
    }
    if (capsuleDto.value.endDate <= LIMIT_DATE) {
        infoLevel.value = MessageConstants.LEVEL_WARNING;
        messageType.value = MessageConstants.VIEW_OK;
        message.value = "終了日時入力が不正です。入力しなおしてください";
        return;
    }

    // タスクコードリストを設定
    // capsuleDto.value.infoCodeList.splice(0);
    // capsuleDto.value.infoCodeList = createCodeList();

    // 検索実行
    getAuthorizedPromiseArea().then(token => {
        const url = urlBack + "/task-plan/search-role";
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

                if (response.status > SERVER_STATUS_ERROR) {
                    message.value = getErrorMessage(BLANK, INQUIRE_FLG);
                    infoLevel.value = MessageConstants.LEVEL_ERROR;
                    messageType.value = MessageConstants.VIEW_OK;
                    return;
                }

                // isFasilureなし
                if (resultDto.value.allCount > 0) {
                    allCount.value = resultDto.value.allCount;
                    pageNumber.value = resultDto.value.pageNumber;
                } else {
                    infoLevel.value = MessageConstants.LEVEL_INFO;
                    messageType.value = MessageConstants.VIEW_TOAST;
                    message.value = "検索結果が存在しませんでした。検索条件を変えて試してください";
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
                // isFailureなし
                resultHistoryDto.value = await response.json();

                if (response.status > SERVER_STATUS_ERROR) {
                    message.value = getErrorMessage(BLANK, INQUIRE_FLG);
                    infoLevel.value = MessageConstants.LEVEL_ERROR;
                    messageType.value = MessageConstants.VIEW_OK;
                    return;
                }

            })
            .catch((e) => {
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
}

function getStateText(isState: boolean, column: string): string {
    return column + (isState ? "しています" : "していません");
}

function onCancel() {
    emits("sendCanceelShowTask");
}

function recieveSubmit() {
    infoLevel.value = 0;
    messageType.value = 0;
}

function recievePagingNumber(selecteddNumber: number) {
    capsuleDto.value.pageNumber = selecteddNumber;
    onSearch();
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

function onUpdateSuccess() {
    // 最終履歴を更新
    const updateCapuleDto: Ref<UpdateTaskPlanSimpleCapsuleDtoInterface> = ref(new UpdateTaskPlanSimpleCapsuleDto());
    const dto: TaskPlanBaseEntityInterface | undefined = resultHistoryDto.value.listTaskHistory[resultHistoryDto.value.listTaskHistory.length - 1];
    if (undefined !== dto) {
        updateCapuleDto.value.taskYear = dto.tableYear;
        updateCapuleDto.value.taskPlanId = dto.taskPlanId;
        updateCapuleDto.value.userDto = props.userDto;
        if (dto.isFinished || dto.isSuspended) {
            message.value = "このタスクは終了または中断しています";
            infoLevel.value = MessageConstants.LEVEL_WARNING;
            messageType.value = MessageConstants.VIEW_OK;
            return;
        }
        if (!dto.isLatest) {
            message.value = "タスク計画データに整合性がありません。";
            infoLevel.value = MessageConstants.LEVEL_WARNING;
            messageType.value = MessageConstants.VIEW_OK;
            return;
        }

        // 正常終了で更新
        getAuthorizedPromiseArea().then(token => {
            const url = urlBack + "/task-plan/update-success";
            const method = "POST";
            const body = JSON.stringify(updateCapuleDto.value);
            const headers = {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
                'X-AUTH-TOKEN': 'Bearer ' + token
            };
            fetch(url, { method, headers, body })
                .then(async (response) => {
                    const resultDto: FrameworkMessageAndResultDtoInterface = await response.json();
                    message.value = resultDto.message;

                    if (response.status > SERVER_STATUS_ERROR) {
                        message.value = getErrorMessage(resultDto.message, ERR_MESS_ONLY);
                        infoLevel.value = MessageConstants.LEVEL_ERROR;
                        messageType.value = MessageConstants.VIEW_OK;
                        return;
                    }

                    if (resultDto.isFailure) {
                        infoLevel.value = MessageConstants.LEVEL_WARNING;
                        messageType.value = MessageConstants.VIEW_OK;
                        return;
                    } else {
                        infoLevel.value = MessageConstants.LEVEL_INFO;
                        messageType.value = MessageConstants.VIEW_TOAST;
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

        <!-- 
        <div class="one-line">
            <div class="left-area">
                タスクの名称
            </div>
            <div class="right-area">
                <input type="texr" v-model="capsuleDto.searchTaskWord" placeholder="タスク名称自由記述"></input>
            </div>
        </div>
        -->

        <!-- 
        <div class="one-line">
            <div class="left-area">
                タスクの種類
            </div>
            <div class="right-area">
                <div class="form-group-vertical">
                    <div> <button @click="onInfoCodeCheck">指定するので展開</button></div>
                    <div v-if="isTaskCodeCheck">
                        <div v-if="listCategory0.length > 0">
                            <input type="checkbox" v-model="flgAllCheck0" @change="onAllCheck0()">グループ0すべて
                            <div>
                                <span v-for="dto, index in listCategory0" class="left-space">
                                    <input type="checkbox" v-model="dto.isChecked">{{ dto.codeName }}
                                    <span v-if="index % 5 == 0"><br></span>
                                </span>
                            </div>
                        </div>
                        <div v-if="listCategory1.length > 0">
                            <input type="checkbox" v-model="flgAllCheck1" @change="onAllCheck1()">グループ1すべて
                            <div>
                                <span v-for="dto, index in listCategory1" class="left-space">
                                    <input type="checkbox" v-model="dto.isChecked">{{ dto.codeName }}
                                    <span v-if="index % 5 == 0"><br></span>
                                </span>
                            </div>
                        </div>
                        <div v-if="listCategory2.length > 0">
                            <input type="checkbox" v-model="flgAllCheck2" @change="onAllCheck2()">グループ2すべて
                            <div>
                                <span v-for="dto, index in listCategory2" class="left-space">
                                    <input type="checkbox" v-model="dto.isChecked">{{ dto.codeName }}
                                    <span v-if="index % 5 == 0"><br></span>
                                </span>
                            </div>
                        </div>

                        <div v-if="listCategory3.length > 0">
                            <input type="checkbox" v-model="flgAllCheck3" @change="onAllCheck3()">グループ3すべて
                            <div>
                                <span v-for="dto, index in listCategory3" class="left-space">
                                    <input type="checkbox" v-model="dto.isChecked">{{ dto.codeName }}
                                    <span v-if="index % 5 == 0"><br></span>
                                </span>
                            </div>
                        </div>

                        <div v-if="listCategory4.length > 0">
                            <input type="checkbox" v-model="flgAllCheck4" @change="onAllCheck4()">グループ4すべて
                            <div>
                                <span v-for="dto, index in listCategory4" class="left-space">
                                    <input type="checkbox" v-model="dto.isChecked">{{ dto.codeName }}
                                    <span v-if="index % 5 == 0"><br></span>
                                </span>
                            </div>
                        </div>

                        <div v-if="listCategory5.length > 0">
                            <input type="checkbox" v-model="flgAllCheck5" @change="onAllCheck5()">グループ5すべて
                            <div>
                                <span v-for="dto, index in listCategory5" class="left-space">
                                    <input type="checkbox" v-model="dto.isChecked">{{ dto.codeName }}
                                    <span v-if="index % 5 == 0"><br></span>
                                </span>
                            </div>
                        </div>

                        <div v-if="listCategory6.length > 0">
                            <input type="checkbox" v-model="flgAllCheck6" @change="onAllCheck6()">グループ1すべて
                            <div>
                                <span v-for="dto, index in listCategory6" class="left-space">
                                    <input type="checkbox" v-model="dto.isChecked">{{ dto.codeName }}
                                    <span v-if="index % 5 == 0"><br></span>
                                </span>
                            </div>
                        </div>

                        <div v-if="listCategory7.length > 0">
                            <input type="checkbox" v-model="flgAllCheck7" @change="onAllCheck7()">グループ7すべて
                            <div>
                                <span v-for="dto, index in listCategory7" class="left-space">
                                    <input type="checkbox" v-model="dto.isChecked">{{ dto.codeName }}
                                    <span v-if="index % 5 == 0"><br></span>
                                </span>
                            </div>
                        </div>

                        <div v-if="listCategory8.length > 0">
                            <input type="checkbox" v-model="flgAllCheck8" @change="onAllCheck8()">グループ8すべて
                            <div>
                                <span v-for="dto, index in listCategory8" class="left-space">
                                    <input type="checkbox" v-model="dto.isChecked">{{ dto.codeName }}
                                    <span v-if="index % 5 == 0"><br></span>
                                </span>
                            </div>
                        </div>
                        <div v-if="listCategory9.length > 0">
                            <input type="checkbox" v-model="flgAllCheck9" @change="onAllCheck9()">グループ9すべて
                        </div>
                        <div>
                            <span v-for="dto, index in listCategory9" class="left-space">
                                <input type="checkbox" v-model="dto.isChecked">{{ dto.codeName }}
                                <span v-if="index % 5 == 0"><br></span>
                            </span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        -->

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

    <div class="one-line" v-if="resultHistoryDto.listTaskHistory.length > 0">
        <div class="left-area">
            このタスクを正常終了
        </div>
        <div class="right-area">
            <button @click="onUpdateSuccess">終了更新</button>
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
