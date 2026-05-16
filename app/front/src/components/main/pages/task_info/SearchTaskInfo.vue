<script setup lang="ts">
import { MessageConstants, MessageView, PagingControl, type LeastUserDtoInterface } from 'seijishikin-jp-normalize_common-tool';
import { ref, toRaw, type Ref } from 'vue';
import RoutePathConstants from '../../../../routePathConstants';
import EditTaskInfo from '../../common/task_info/EditTaskInfo.vue';
import { getLoginUser } from '../../utils/getLoginUser';
import AdminInfo from '../../common/user_info/AdminInfo.vue';
import { SearchTaskInfoCapsuleDto, type SearchTaskInfoCapsuleDtoInterface } from '../../dto/task_info/searchTaskInfoCapsuleDto';
import { SearchTaskInfoResultDto, type SearchTaskInfoResultDtoInterface } from '../../dto/task_info/searchTaskInfoResultDto';
import getAuthorizedPromiseArea from '../../dto/login/getAuthorizedPromiseArea';
import { AccessTokenNotFoundError, TokenRefreshError } from '../../dto/login/errors';
import { TaskInfoEntity, type TaskInfoEntityInterface } from '../../entity/taskInfoEntity';

// よく使う定数
const BLANK: string = "";
const INIT_NUMBER: number = 0;
const INIT_BOOLEAN: boolean = false;
// const SERVER_STATUS_OK: number = 200;
// const SERVER_STATUS_ERROR: number = 400;
const SEARCH_LIMIT: number = 20;

// メッセージボックス表示定数
const infoLevel: Ref<number> = ref(MessageConstants.LEVEL_NONE);
const messageType: Ref<number> = ref(MessageConstants.VIEW_NONE);
const title: Ref<string> = ref(BLANK);
const message: Ref<string> = ref(BLANK);

// ユーザ呼び出し
const userDto: Ref<LeastUserDtoInterface> = ref(getLoginUser());

// Paging
const pageNumber: Ref<number> = ref(INIT_NUMBER);
const allCount: Ref<number> = ref(INIT_NUMBER);
const limit: Ref<number> = ref(SEARCH_LIMIT);

// back側アクセス
const urlBack: string = RoutePathConstants.DOMAIN + RoutePathConstants.BASE_PATH;
const isTaskEdit: Ref<boolean> = ref(INIT_BOOLEAN);

const capsuleDto: Ref<SearchTaskInfoCapsuleDtoInterface> = ref(new SearchTaskInfoCapsuleDto());
const resultDto: Ref<SearchTaskInfoResultDtoInterface> = ref(new SearchTaskInfoResultDto());

function onSearch() {
    capsuleDto.value.allCount = allCount.value;
    capsuleDto.value.limit = limit.value;
    capsuleDto.value.pageNumber = pageNumber.value;

    getAuthorizedPromiseArea().then(token => {
        const url = urlBack + "/task-info/search";
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
                if (0 == resultDto.value.listTask.length) {
                    infoLevel.value = MessageConstants.LEVEL_INFO;
                    messageType.value = MessageConstants.VIEW_TOAST;
                    title.value = "タスク情報検索";
                    message.value = "検索結果が0件でした";
                }
                allCount.value = resultDto.value.allCount;
                limit.value = resultDto.value.limit;
                pageNumber.value = resultDto.value.pageNumber;
            })
            .catch((error) => {
                alert(error);
                infoLevel.value = MessageConstants.LEVEL_ERROR;
                messageType.value = MessageConstants.VIEW_OK;
                title.value = "タスク情報検索";
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

function recievePagingNumber(selecteddNumber: number) {
    //capsuleDto.value.pageNumber = selecteddNumber;
    //onSearch();
    alert("ページング");
}

function onCancel() {
    history.back();
}

// 検索条件と結果
const taskInfoEntity: Ref<TaskInfoEntityInterface> = ref(new TaskInfoEntity());
const taskInfoEntityBackup: Ref<TaskInfoEntityInterface> = ref(new TaskInfoEntity());
const editInfoId: Ref<number> = ref(INIT_NUMBER);

function onEdit(editId: number) {
    if (undefined !== resultDto.value.listTask.filter((e) => editId === e.taskInfoId)[0]) {
        taskInfoEntity.value = resultDto.value.listTask.filter((e) => editId === e.taskInfoId)[0] as TaskInfoEntityInterface;
        editInfoId.value = editId;
        taskInfoEntityBackup.value = structuredClone(toRaw(taskInfoEntity.value));
        isTaskEdit.value = true;
    } else {
        alert("取得できませんでした");
    }
}

function recieveCancelTaskInfo() {

    isTaskEdit.value = false;
}

function recieveTaskInfoInterface() {

    isTaskEdit.value = false;
}

function recieveSubmit(button: string) {
    console.log(button); // 警告除け
    infoLevel.value = 0;
    messageType.value = 0;
}
</script>
<template>
    <!-- SE権限 -->
    <AdminInfo :user-dto="userDto"></AdminInfo>

    <h1>タスク情報検索・編集</h1>

    <h3>検索条件</h3>
    <div class="one-line">
        <div class="left-area">検索語</div>
        <div class="right-area"><input type="text" v-model="capsuleDto.searchNaturalWords"></div>
    </div>
    <div class="one-line">
        <div class="left-area">タスクの種類</div>
        <div class="right-area"><select v-model="capsuleDto.taskType">
                <option value="1">住所タスク</option>
                <option value="3">関連者タスク</option>
                <option value="9">権限タスク</option>
            </select>
        </div>
    </div>
    <div class="one-line">
        <div class="left-area">検索</div>
        <div class="right-area"><button @click="onSearch">検索</button></div>
    </div>


    <h3>検索結果表示</h3>
    <!-- ページング -->
    <PagingControl :all-count="allCount" :limit="limit" :page-number="pageNumber"
        @send-paging-number="recievePagingNumber"></PagingControl>
        
    <div class="one-line-scroll">
        <!-- ページング -->
        <table>
            <tbody>
                <tr>
                    <th>タスクコード</th>
                    <th>タスク名称</th>
                    <th>&nbsp;</th>
                </tr>
                <tr v-for="entity in resultDto.listTask">
                    <td>{{ entity.taskInfoCode }}</td>
                    <td>{{ entity.taskInfoName }}</td>
                    <td> <button @click="onEdit(entity.taskInfoId)">編集</button> </td>
                </tr>
            </tbody>
        </table>
    </div>
    <!-- ページング -->
    <PagingControl :all-count="allCount" :limit="limit" :page-number="pageNumber"
        @send-paging-number="recievePagingNumber"></PagingControl>


    <div class="footer">
        <button class="footer-button" @click="onCancel">キャンセル</button>
    </div>

    <!-- タスク情報編集画面-->
    <div v-if="isTaskEdit" class="overBackground"></div>
    <div v-if="isTaskEdit" class="overComponent">
        <EditTaskInfo :user-dto="userDto" :task-info-entity="taskInfoEntity"
            @send-cancel-task-info="recieveCancelTaskInfo" @send-task-info-interface="recieveTaskInfoInterface">
        </EditTaskInfo>
    </div>

    <!-- メッセージ表示 -->
    <div class="overMessage" v-if="messageType !== MessageConstants.VIEW_NONE">
        <MessageView :info-level="infoLevel" :message-type="messageType" :title="title" :message="message"
            @send-submit="recieveSubmit">
        </MessageView>
    </div>

</template>
<style scoped></style>
