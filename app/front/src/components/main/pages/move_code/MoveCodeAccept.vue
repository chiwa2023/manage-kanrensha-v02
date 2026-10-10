<script setup lang="ts">
import { onMounted, ref, type Ref } from 'vue';
import ManagerInfo from '../../common/user_info/ManagerInfo.vue';
import { getErrorMessage, getErrorUniqueIdMessage, InputDate, MessageConstants, MessageView, PagingControl, type FrameworkMessageAndResultDtoInterface, type LeastUserDtoInterface } from 'seijishikin-jp-normalize_common-tool';
import { getLoginUser } from '../../utils/getLoginUser.ts';
import { MoveKanrenshaCodeAcceptCapsuleDto, type MoveKanrenshaCodeAcceptCapsuleDtoInterface } from '../../dto/kanrensha/moveKanrenshaCodeAcceptCapsuleDto.ts';
import { MoveKanrenshaCodeSearchCapsuleDto, type MoveKanrenshaCodeSearchCapsuleDtoInterface } from '../../dto/kanrensha/moveKanrenshaCodeSearchCapsuleDto.ts';
import { MoveKanrenshaCodeSearchResultDto, type MoveKanrenshaCodeSearchResultDtoInterface } from '../../dto/kanrensha/moveKanrenshaCodeSearchResultDto.ts';
import KanrenshaKbnConstants from '../../dto/kanrensha/kanrenshaKbnConstants.ts';
import { KanrenshaCodeMoveEntity, type KanrenshaCodeMoveEntityInterface } from '../../entity/kanrenshaCodeMoveEntity.ts';
import getAuthorizedPromiseArea from '../../dto/login/getAuthorizedPromiseArea.ts';
import RoutePathConstants from '../../../../routePathConstants.ts';
import { AccessTokenNotFoundError, TokenRefreshError } from '../../dto/login/errors.ts';
import { useTaskPlan } from '../../stores/storeTaskPlan.ts';
import type { OneFileBlobResultDtoInterface } from '../../dto/storage_file/oneFileBlobResultDto.ts';
import { DownloadFileCapsuleDto, type DownloadFileCapsuleDtoInterface } from '../../dto/file/downloadFileCapsuleDto.ts';
import KanrenshaCodeHistory from '../../common/kanrensha_edit/KanrenshaCodeHistory.vue';


// よく使う定数
const BLANK: string = "";
const INIT_NUMBER: number = 0;
const INIT_BOOLEAN: boolean = false;
// const SERVER_STATUS_OK: number = 200;
const SERVER_STATUS_ERROR: number = 400;
const SEARCH_LIMIT: number = 20;
// メッセージボックス表示定数
const INQUIRE_FLG: boolean = false;
const ERR_MESS_ONLY: boolean = true;
const MESS_PAGE_NAME: string = "関連者コード移行承認";
const INIT_CALLER: string = "no branch";
const infoLevel: Ref<number> = ref(MessageConstants.LEVEL_NONE);
const messageType: Ref<number> = ref(MessageConstants.VIEW_NONE);
const caller: Ref<string> = ref(INIT_CALLER);
const message: Ref<string> = ref(BLANK);

// back側アクセス
const urlBack: string = RoutePathConstants.DOMAIN + RoutePathConstants.BASE_PATH;


// Paging
const pageNumber: Ref<number> = ref(INIT_NUMBER);
const allCount: Ref<number> = ref(INIT_NUMBER);
const limit: Ref<number> = ref(SEARCH_LIMIT);

// ユーザ呼び出し
const userDto: Ref<LeastUserDtoInterface> = ref(getLoginUser());

// qyeryParam受け取り用に最初から実行条件Dtoを準備する 
const capsuleDto: Ref<MoveKanrenshaCodeAcceptCapsuleDtoInterface> = ref(new MoveKanrenshaCodeAcceptCapsuleDto());
const editEntity: Ref<KanrenshaCodeMoveEntityInterface> = ref(new KanrenshaCodeMoveEntity());


// 検索Dto
const capsuleDtoSearch: Ref<MoveKanrenshaCodeSearchCapsuleDtoInterface> = ref(new MoveKanrenshaCodeSearchCapsuleDto());
const resultDtoSearch: Ref<MoveKanrenshaCodeSearchResultDtoInterface> = ref(new MoveKanrenshaCodeSearchResultDto());

onMounted(() => {
    // 権限タスクから遷移されてきたときだけqueryParamを受け取る
    const storesTaskPlan = useTaskPlan();

    if (storesTaskPlan.taskPlanId !== null && undefined !== storesTaskPlan.taskPlanId && 0 != storesTaskPlan.taskPlanId) {
        capsuleDto.value.taskPlanId = storesTaskPlan.taskPlanId;
    }
    if (storesTaskPlan.taskYear !== null && undefined !== storesTaskPlan.taskYear && 0 != storesTaskPlan.taskYear) {
        capsuleDto.value.taskYear = storesTaskPlan.taskYear;
    }

    // 初期設定1か月内申請中だけを抽出
    onSearch();
});


function onSearch() {
    capsuleDtoSearch.value.allCount = allCount.value;
    capsuleDtoSearch.value.limit = limit.value;
    capsuleDtoSearch.value.pageNumber = pageNumber.value;

    getAuthorizedPromiseArea().then(token => {
        const url = urlBack + "/kanrensha-code-move/search";
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

                allCount.value = resultDtoSearch.value.allCount;
                limit.value = resultDtoSearch.value.limit;
                pageNumber.value = resultDtoSearch.value.pageNumber;

                if (resultDtoSearch.value.allCount == 0) {
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


}

function onCancel() {
    history.back();
}

function onSave() {

    capsuleDto.value.userDto = userDto.value;
    capsuleDto.value.kanrenshaCodeMoveEntity = editEntity.value;

    getAuthorizedPromiseArea().then(token => {
        const url = urlBack + "/kanrensha-code-move/accept";
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

function recieveSubmit() {
    infoLevel.value = 0;
    messageType.value = 0;
}
function recievePagingNumber(selecteddNumber: number) {
    pageNumber.value = selecteddNumber;
    // onSearchでページング複写
    onSearch();
}

// 編集対象を決定
function onEdit(selectedId: number) {
    const entity: KanrenshaCodeMoveEntityInterface | undefined
        = resultDtoSearch.value.listEntity[selectedId];
    if (undefined !== entity) {
        editEntity.value = entity;
    } else {
        infoLevel.value = MessageConstants.LEVEL_ERROR;
        messageType.value = MessageConstants.VIEW_OK;
        message.value = getErrorUniqueIdMessage(selectedId);
        return;
    }
}

// コンポーネントから時刻受け取り
function recieveDate(date: Date, index: number) {
    if (0 == index) {
        capsuleDtoSearch.value.startDate = date;
    }
    if (1 == index) {
        capsuleDtoSearch.value.endDate = date;
    }
}

function onDownloadFile() {

    const downloadCapsuleDto: DownloadFileCapsuleDtoInterface = new DownloadFileCapsuleDto();
    downloadCapsuleDto.userDto = userDto.value;
    downloadCapsuleDto.storageYear = editEntity.value.taskYear;
    downloadCapsuleDto.storageId = editEntity.value.saveFileStorageId;
    downloadCapsuleDto.kanrenshaKbn = editEntity.value.kanrenshaKbn;
    downloadCapsuleDto.kanrenshaCode = editEntity.value.abolishKanrenshaCode;

    getAuthorizedPromiseArea().then(token => {
        const url = urlBack + "/file/download";
        const method = "POST";
        const body = JSON.stringify(downloadCapsuleDto);
        const headers = {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'X-AUTH-TOKEN': 'Bearer ' + token
        };
        fetch(url, { method, headers, body })
            .then(async (response) => {

                const resultDto: OneFileBlobResultDtoInterface = await response.json();

                if (response.status > SERVER_STATUS_ERROR) {
                    message.value = getErrorMessage(resultDto.message, ERR_MESS_ONLY);
                    infoLevel.value = MessageConstants.LEVEL_ERROR;
                    messageType.value = MessageConstants.VIEW_OK;
                    return;
                }

                if (resultDto.isFailure) {
                    // メッセージ
                    infoLevel.value = MessageConstants.LEVEL_WARNING;
                    messageType.value = MessageConstants.VIEW_OK;
                    message.value = getErrorMessage(resultDto.message, ERR_MESS_ONLY);
                } else {
                    //Base64文字列からMIMEType不明(=application/octet-stream)Blobに変換
                    const bin: string = window.atob(resultDto.fileContentBase64);
                    const buffer = new Uint8Array(bin.length);
                    for (var i = 0; i < bin.length; i++) {
                        buffer[i] = bin.charCodeAt(i);
                    }
                    const blob: Blob = new Blob([buffer.buffer], { "type": "application/octet-stream", });

                    //リンクを作成して強制発火
                    let anchorElement = document.createElement('a');
                    anchorElement.href = URL.createObjectURL(blob);
                    anchorElement.download = resultDto.fileName;
                    document.body.appendChild(anchorElement);
                    anchorElement.click();
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


const isShowHistory: Ref<boolean> = ref(INIT_BOOLEAN);
const kanrenshaKbn: Ref<number> = ref(INIT_NUMBER);
const codeOrgin: Ref<string> = ref(BLANK);
const codeAbolish: Ref<string> = ref(BLANK);

function recieveCancelKanrenshaCodeHistory() {
    // 履歴コンポーネントを閉じる
    isShowHistory.value = false;
}

function onHistory(index: number) {

    const entity: KanrenshaCodeMoveEntityInterface | undefined
        = resultDtoSearch.value.listEntity[index];
    if (undefined !== entity) {
        // 選択された値をpropsで引き渡す
        kanrenshaKbn.value = entity.kanrenshaKbn;
        codeAbolish.value = entity.abolishKanrenshaCode;
        codeOrgin.value = entity.originKanrenshaCode;

        // コンポーネントを開く
        isShowHistory.value = true;
    } else {
        infoLevel.value = MessageConstants.LEVEL_ERROR;
        messageType.value = MessageConstants.VIEW_OK;
        message.value = getErrorUniqueIdMessage(index);
        return;
    }

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

</script>
<template>

    <!-- 管理者メニュー兼チェック -->
    <ManagerInfo :user-dto="userDto"></ManagerInfo>

    <h1>コード変更承認</h1><br>

    <h3 class="accent-h3">検索条件</h3>

    <div class="one-line">
        <div class="left-area">
            申請状況
        </div>
        <div class="right-area">
            <input type="checkbox" v-model="capsuleDtoSearch.isPromoteSearch">申請中
            <input type="checkbox" v-model="capsuleDtoSearch.isRejectSearch">却下
            <input type="checkbox" v-model="capsuleDtoSearch.isAcceptSearch">承認
            <input type="checkbox" v-model="capsuleDtoSearch.isResearchSearch">追加調査
        </div>
    </div>

    <div class="one-line">
        <div class="left-area">
            期間指定
        </div>
        <div class="right-area">
            <span>
                <InputDate :date="capsuleDtoSearch.startDate" :index="0" :is-edit="true" @send-date="recieveDate">
                </InputDate>
                から
            </span>
            <span class="left-space">
                <InputDate :date="capsuleDtoSearch.endDate" :index="1" :is-edit="true" @send-date="recieveDate">
                </InputDate>まで
            </span>
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

    <h3 class="accent-h3">検索結果</h3>

    <div class="one-line">
        <table>
            <tbody>
                <tr>
                    <th>関連者区分</th>
                    <th>状態</th>
                    <th>廃止コード(申請者)</th>
                    <th>併合コード</th>
                    <th>&nbsp;</th>
                    <th>&nbsp;</th>
                </tr>
                <tr v-for="(entity, index) of resultDtoSearch.listEntity" :key="index">
                    <td>{{ KanrenshaKbnConstants.getLabel(entity.kanrenshaKbn) }}</td>
                    <td>{{ getStatus(entity.moveStatus) }}</td>
                    <td>{{ entity.abolishKanrenshaCode }} <br> {{ entity.abolishKanrenshaName }}</td>
                    <td>{{ entity.originKanrenshaCode }} <br> {{ entity.originName }} </td>
                    <td><button @click="onEdit(index)">編集</button></td>
                    <td><button @click="onHistory(index)">履歴</button></td>
                </tr>
            </tbody>
        </table>
    </div>
    <PagingControl :all-count="allCount" :limit="limit" :page-number="pageNumber"
        @send-paging-number="recievePagingNumber"></PagingControl>

    <h3 class="accent-h3">申請詳細</h3>

    <div class="one-line">
        <div class="left-area">
            関連者区分
        </div>
        <div class="right-area">
            <span><input type="radio" v-model="editEntity.kanrenshaKbn" id="editSelect" value="1"
                    disabled="true">1.個人</span>
            <span class="left-space"><input type="radio" v-model="editEntity.kanrenshaKbn" id="editSelect" value="2"
                    disabled="true">2.企業／団体</span>
            <span class="left-space"><input type="radio" v-model="editEntity.kanrenshaKbn" id="editSelect" value="3"
                    disabled="true">3.政治団体</span>
        </div>
    </div>

    <div class="one-line">
        <div class="left-area">
            廃止コード
        </div>
        <div class="right-area">
            <input type="text" v-model="editEntity.abolishKanrenshaCode" class="code-input" disabled="true">
            <span class="left-space">名称：<input type="text" v-model="editEntity.abolishKanrenshaName" class="name-input"
                    disabled="true"></span>
        </div>
    </div>

    <div class="one-line">
        <div class="left-area">
            併合先
        </div>
        <div class="right-area">
            <input type="text" v-model="editEntity.originKanrenshaCode" class="code-input" disabled="true">
            <span class="left-space">名称：<input type="text" v-model="editEntity.originName" class="name-input"
                    disabled="true"></span>
        </div>
    </div>

    <div class="one-line">
        <div class="left-area">
            最新履歴
        </div>
        <div class="right-area">
            <input type="checkbox" v-model="editEntity.isAbolishLast">申請者の登録を最新にする
        </div>
    </div>

    <div class="one-line">
        <div class="left-area">
            書証
        </div>
        <div class="right-area">
            <button @click="onDownloadFile">書証ダウンロード</button>
        </div>
    </div>

    <div class="one-line">
        <div class="left-area">
            承認判定
        </div>
        <div class="right-area">
            <select v-model="editEntity.moveStatus">
                <option value=0>未定</option>
                <option value=1>申請中</option>
                <option value=2>却下</option>
                <option value=3>承認</option>
                <option value=4>追加調査</option>
            </select>
        </div>
    </div>

    <div class="footer">
        <button @click="onCancel" class="footer-button">キャンセル</button>
        <button @click="onSave" class="footer-button left-space">送信</button>
    </div>

    <!-- 関連者コード履歴 -->
    <div v-if="isShowHistory" class="overBackground"></div>
    <div v-if="isShowHistory">
        <div class="overComponent">
            <KanrenshaCodeHistory :user-dto="userDto" :kanrensha-kbn="kanrenshaKbn" :code-orgin="codeOrgin"
                :code-abolish="codeAbolish" @send-cancel-kanrensha-code-history="recieveCancelKanrenshaCodeHistory">
            </KanrenshaCodeHistory>
        </div>
    </div>

    <!-- メッセージ表示    -->
    <div class="overMessage" v-if="messageType !== MessageConstants.VIEW_NONE">
        <MessageView :info-level="infoLevel" :message-type="messageType" :title="MESS_PAGE_NAME" :message="message"
            :caller="caller" @send-submit="recieveSubmit">
        </MessageView>
    </div>

</template>
<style scoped></style>
