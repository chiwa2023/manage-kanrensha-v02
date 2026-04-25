<script setup lang="ts">
import { MessageConstants, MessageView, PagingControl, type LeastUserDtoInterface } from 'seijishikin-jp-normalize_common-tool';
import { computed, ref, toRaw, type ComputedRef, type Ref } from 'vue';
import { WkTblKanrenshaKigyouDtHistoryEntity, type WkTblKanrenshaKigyouDtHistoryEntityInterface } from '../../entity/wkTblKanrenshaKigyouDtHistoryEntity';
import { SearchWkTblHistoryKigyouDtPagingResultDto, type SearchWkTblHistoryKigyouDtPagingResultDtoInterface } from '../../dto/wktbl_history/searchWkTblHistoryKigyouDtPagingResultDto';
import { SearchWkTblPagingCapsuleDto, type SearchWkTblPagingCapsuleDtoInterface } from '../../dto/add_xml/searchWkTbPagingCapsuleDto';
import { UpdateWkTblHistoryKigyouDtCapsuleDto, type UpdateWkTblHistoryKigyouDtCapsuleDtoInterface } from '../../dto/wktbl_history/updateWkTblHistoryKigyouDtCapsuleDto';
import getAuthorizedPromiseArea from '../../dto/login/getAuthorizedPromiseArea';
import RoutePathConstants from '../../../../routePathConstants';
import { AccessTokenNotFoundError, TokenRefreshError } from '../../dto/login/errors';
import type { UpdateWkTblHistoryKigyouDtResultDtoInterface } from '../../dto/wktbl_history/updateWkTblHistoryKigyouDtResultDto';


//props,emit
const props = defineProps<{ userDto: LeastUserDtoInterface }>()

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
// Paging
const pageNumber: Ref<number> = ref(INIT_NUMBER);
const allCount: Ref<number> = ref(INIT_NUMBER);
const limit: Ref<number> = ref(SEARCH_LIMIT);

// back側アクセス
const urlBack: string = RoutePathConstants.DOMAIN + RoutePathConstants.BASE_PATH;

const kigyouDtCapsuleDto: Ref<SearchWkTblPagingCapsuleDtoInterface> = ref(new SearchWkTblPagingCapsuleDto());
const kigyouDtResultDto: Ref<SearchWkTblHistoryKigyouDtPagingResultDtoInterface> = ref(new SearchWkTblHistoryKigyouDtPagingResultDto());

kigyouDtCapsuleDto.value.userDto = props.userDto;

function onSearchKigyouDt() {
    // kigyouDtResultDto.value.listWktblKigyouDt = getMockWkTblKigyouDtList();
    // allCount.value = kigyouDtResultDto.value.listWktblKigyouDt.length;

    kigyouDtCapsuleDto.value.allCount = allCount.value;
    kigyouDtCapsuleDto.value.limit = limit.value;
    kigyouDtCapsuleDto.value.pageNumber = pageNumber.value;

    getAuthorizedPromiseArea().then(token => {
        const url = urlBack + "/regist-bulk-history/search-kigyou-dt";
        const method = "POST";
        const body = JSON.stringify(kigyouDtCapsuleDto.value);
        const headers = {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'X-AUTH-TOKEN': 'Bearer ' + token
        };
        fetch(url, { method, headers, body })
            .then(async (response) => {
                kigyouDtResultDto.value = await response.json();
                if (0 == kigyouDtResultDto.value.listWktblKigyouDt.length) {
                    infoLevel.value = MessageConstants.LEVEL_INFO;
                    messageType.value = MessageConstants.VIEW_TOAST;
                    title.value = "タスク情報検索";
                    message.value = "検索結果が0件でした";
                }
                allCount.value = kigyouDtResultDto.value.allCount;
                limit.value = kigyouDtResultDto.value.limit;
                pageNumber.value = kigyouDtResultDto.value.pageNumber;
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


// 編集用
const isEditData: Ref<boolean> = ref(INIT_BOOLEAN);
const entityEdit: Ref<WkTblKanrenshaKigyouDtHistoryEntityInterface> = ref(new WkTblKanrenshaKigyouDtHistoryEntity());
const editCapsuleDto: Ref<UpdateWkTblHistoryKigyouDtCapsuleDtoInterface> = ref(new UpdateWkTblHistoryKigyouDtCapsuleDto());
editCapsuleDto.value.userDto = props.userDto;

const findIndex: Ref<number> = ref(INIT_NUMBER);
function onEditData(editId: number) {
    // 指定されたデータを呼び出し(編集決定時には置き換えするので配列indexが必要)
    const tempIndex: number = kigyouDtResultDto.value.listWktblKigyouDt.findIndex(
        (e: WkTblKanrenshaKigyouDtHistoryEntity) => e.wkKanrenshaKigyouDtHistoryId === editId);
    if (kigyouDtResultDto.value.listWktblKigyouDt[tempIndex] !== undefined && tempIndex !== undefined) {
        entityEdit.value = structuredClone(toRaw(kigyouDtResultDto.value.listWktblKigyouDt[tempIndex]));
        findIndex.value = tempIndex;
    }

    isEditData.value = true;
}
function onEditUpdate() {

    // 編集中のEntityを編集のためにBack側に受け渡し
    editCapsuleDto.value.wkTblKanrenshaKigyouDtHistoryEntity = entityEdit.value;

    title.value = "関連者企業団体履歴ワークテーブル更新";
    getAuthorizedPromiseArea().then(token => {
        const url = urlBack + "/regist-bulk-history/update-kigyou-dt";
        const method = "POST";
        const body = JSON.stringify(editCapsuleDto.value);
        const headers = {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'X-AUTH-TOKEN': 'Bearer ' + token
        };
        fetch(url, { method, headers, body })
            .then(async (response) => {
                const resultDto: UpdateWkTblHistoryKigyouDtResultDtoInterface = await response.json();
                message.value = resultDto.message;
                if (resultDto.isFailure) {
                    infoLevel.value = MessageConstants.LEVEL_WARNING;
                    messageType.value = MessageConstants.VIEW_OK;
                    return;
                } else {
                    infoLevel.value = MessageConstants.LEVEL_INFO;
                    messageType.value = MessageConstants.VIEW_TOAST;
                    isEditData.value = false;
                    onSearchKigyouDt();
                }
            })
            .catch((error) => {
                alert(error);
                infoLevel.value = MessageConstants.LEVEL_ERROR;
                messageType.value = MessageConstants.VIEW_OK;
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

    // 編集コンポーネントを閉じる
    isEditData.value = false;
}
function onEditClose() {
    // 編集コンポーネントを閉じる
    isEditData.value = false;
}

// 編集画面データ更新禁止
const listEditProhibit: string[] = [];
listEditProhibit.push("正常終了");
const onSaveClassName: ComputedRef<string> = computed(() => {
    if (listEditProhibit.includes(entityEdit.value.judgeReason)) {
        return "footer-button-disable";
    } else {
        return "footer-button";
    }
});

const notUseText: string = "使用しないに変更;";
function onHideData() {
    entityEdit.value.judgeReason = notUseText;
    entityEdit.value.isAffected = false;
    entityEdit.value.isFinish = true;
    onEditUpdate();
}

function recievePagingNumber(selecteddNumber: number) {
    pageNumber.value = selecteddNumber;
    alert("ページ情報受信");
}

function recieveSubmit(button: string) {
    console.log(button); // 警告除け
    infoLevel.value = 0;
    messageType.value = 0;
}
</script>
<template>
    <h3>関連者企業／団体検索条件</h3>

    <div class="one-line">
        <div class="left-area">
            検索項目
        </div>
        <div class="right-area">
            <input type="checkbox" v-model="kigyouDtCapsuleDto.hasAffectNot">反映なし
            <span class="left-space"><input type="checkbox" v-model="kigyouDtCapsuleDto.hasFinished">作業完了</span>
            <span class="left-space"><input type="checkbox" v-model="kigyouDtCapsuleDto.hasHistorry">処理対象外履歴</span>
        </div>
    </div>

    <div class="one-line">
        <div class="left-area">
            作業結果表示
        </div>
        <div class="right-area">
            <button @click="onSearchKigyouDt">表示</button>
        </div>
    </div>

    <h3>関連者企業／団体処理予定</h3>

    <!-- ページング -->
    <PagingControl :all-count="allCount" :limit="limit" :page-number="pageNumber"
        @send-paging-number="recievePagingNumber"></PagingControl>
    <div class="one-line">
        <table>
            <tbody>
                <tr>
                    <th>反映該否</th>
                    <th>団体名称</th>
                    <th>団体住所</th>
                    <th>団体代表者氏名</th>
                    <th>関連者コード</th>
                </tr>
            </tbody>
            <tbody v-for="entity of kigyouDtResultDto.listWktblKigyouDt" :key="entity.wkKanrenshaKigyouDtHistoryId">
                <tr>
                    <td rowspan="2"><input type="checkbox" v-model="entity.isAffected" disabled="true">反映する</td>
                    <td colspan="4">{{ entity.judgeReason }}</td>
                </tr>
                <tr>
                    <td><button @click="onEditData(entity.wkKanrenshaKigyouDtHistoryId)" :disabled="!entity.isLatest">{{
                        entity.kanrenshaName }}</button></td>
                    <td>{{ entity.allAddress }}</td>
                    <td>{{ entity.kigyouDtDelegate }}</td>
                    <td>{{ entity.kigyouDtKanrenshaCode }}</td>
                </tr>
            </tbody>
        </table>
    </div>
    <!-- ページング -->
    <PagingControl :all-count="allCount" :limit="limit" :page-number="pageNumber"
        @send-paging-number="recievePagingNumber"></PagingControl>

    <!-- 編集処理 -->
    <div v-if="isEditData" class="overBackground"></div>
    <div v-if="isEditData">
        <div class="overComponent">

            <div class="one-line">

                <div class="left-area">
                    反映該否
                </div>
                <div class="right-area">
                    <input type="checkbox" v-model="entityEdit.isAffected">反映あり<button @click="onHideData"
                        class="left-space">このデータを使用しない</button>
                    <br>※データが重複していると反映該否が動かせないことがあります
                </div>
            </div>

            <div class="one-line">
                <div class="left-area">
                    判定理由
                </div>
                <div class="right-area">
                    {{ entityEdit.judgeReason }}
                </div>
            </div>

            <div class="one-line">
                <div class="left-area">
                    企業／団体名
                </div>
                <div class="right-area">
                    <input type="text" v-model="entityEdit.kanrenshaName" />
                </div>
            </div>

            <div class="one-line">

                <div class="left-area">
                    全住所
                </div>
                <div class="right-area">
                    <input type="text" v-model="entityEdit.allAddress" />
                </div>
            </div>

            <div class="one-line">
                <div class="left-area">
                    企業／団体代表者名
                </div>
                <div class="right-area">
                    <input type="text" v-model="entityEdit.kigyouDtDelegate" />
                </div>
            </div>

            <div class="one-line">
                <div class="left-area">
                    企業／団体関連者コード
                </div>
                <div class="right-area">
                    <input type="text" v-model="entityEdit.kigyouDtKanrenshaCode" />
                </div>
            </div>

            <div class="footer">
                <button @click="onEditClose" class="footer-button">キャンセル</button>
                <button @click="onEditUpdate" class="left-space"  :class="onSaveClassName">送信</button>
            </div>

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
/*
table {
    border-style: solid;
    border-width: 1px;
}

td {
    border-style: solid;
    border-width: 1px;
}

td.rowNum {
    border-style: solid;
    border-top-width: 2px;
    border-left-width: 2px;
    border-bottom-width: 2px;
    border-right-width: 1px;
}

th {
    border-style: solid;
    border-width: 1px;
}

tbody:after {
    content: "";
    height: 2px;
    width: 100%;
    padding: 2px 0;
    display: block;
}
*/
</style>
