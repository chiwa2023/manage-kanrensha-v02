<script setup lang="ts">
import { computed, ref, toRaw, type ComputedRef, type Ref } from 'vue';
import { SearchWkTblPagingCapsuleDto, type SearchWkTblPagingCapsuleDtoInterface } from '../../dto/add_xml/searchWkTbPagingCapsuleDto';
import { SearchWkTblCombineOrgPagingResultDto, type SearchWkTblCombineOrgPagingResultDtoInterface } from '../../dto/wktbl_combine/searchWkTblCombineOrgPagingResultDto';
import { MessageConstants, MessageView, PagingControl, type LeastUserDtoInterface } from 'seijishikin-jp-normalize_common-tool';
import KanrenshaKbnConstants from '../../dto/kanrensha/kanrenshaKbnConstants';
import RoutePathConstants from '../../../../routePathConstants';
import { WkTblKanrenshaCombineOrgEntity, type WkTblKanrenshaCombineOrgEntityInterface } from '../../entity/wkTblKanrenshaCombineOrgEntity';
import YearOption from '../../dto/wktbl_combine/yearOption';
import getAuthorizedPromiseArea from '../../dto/login/getAuthorizedPromiseArea';
import { AccessTokenNotFoundError, TokenRefreshError } from '../../dto/login/errors';
import { UpdateWkTblCombineOrgCapsuleDto, type UpdateWkTblCombineOrgCapsuleDtoInterface } from '../../dto/wktbl_combine/updateWkTblCombineOrgCapsuleDto';
import type { UpdateWkTblHistoryKigyouDtResultDtoInterface } from '../../dto/wktbl_history/updateWkTblHistoryKigyouDtResultDto';

//props,emit
const props = defineProps<{ orgType: string, userDto: LeastUserDtoInterface }>();

// よく使う定数
const BLANK: string = "";
const INIT_NUMBER: number = 0;
// const INIT_BOOLEAN: boolean = false;
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

const combineCapsuleDto: Ref<SearchWkTblPagingCapsuleDtoInterface> = ref(new SearchWkTblPagingCapsuleDto());
combineCapsuleDto.value.userDto = props.userDto;
combineCapsuleDto.value.hasAffectNot = true;
const combineResultDto: Ref<SearchWkTblCombineOrgPagingResultDtoInterface> = ref(new SearchWkTblCombineOrgPagingResultDto());

const systemYearStart: number = 2019;
const systemYearEnd: number = 2025;
const listYearCheck: Ref<YearOption[]> = ref([]);
for (let index = systemYearStart; index <= systemYearEnd; index++) {
    const dto: YearOption = new YearOption();
    dto.year = index;
    dto.isSelect = false;
    listYearCheck.value.push(dto);
}

function onSearch() {

    combineCapsuleDto.value.allCount = allCount.value;
    combineCapsuleDto.value.limit = limit.value;
    combineCapsuleDto.value.pageNumber = pageNumber.value;

    title.value = "関連者個人－企業紐づけ検索";
    getAuthorizedPromiseArea().then(token => {
        const url = urlBack + "/regist-combine/search-" + props.orgType;
        const method = "POST";
        const body = JSON.stringify(combineCapsuleDto.value);
        const headers = {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'X-AUTH-TOKEN': 'Bearer ' + token
        };
        fetch(url, { method, headers, body })
            .then(async (response) => {
                combineResultDto.value = await response.json();
                if (combineResultDto.value.allCount == 0) {
                    infoLevel.value = MessageConstants.LEVEL_INFO;
                    messageType.value = MessageConstants.VIEW_TOAST;
                    message.value = "検索結果が0件でした";

                }
                allCount.value = combineResultDto.value.allCount;
                limit.value = combineResultDto.value.limit;
                pageNumber.value = combineResultDto.value.pageNumber;
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


// 初期表示データフォーマットは最小
const period: string = "period";
const point: string = "point";
const isSetPeriod: Ref<string> = ref(period);

// 編集用
const isEditData: Ref<boolean> = ref(false);
const entityEdit: Ref<WkTblKanrenshaCombineOrgEntityInterface> = ref(new WkTblKanrenshaCombineOrgEntity());
const editCapsuleDto: Ref<UpdateWkTblCombineOrgCapsuleDtoInterface> = ref(new UpdateWkTblCombineOrgCapsuleDto());
editCapsuleDto.value.userDto = props.userDto;

function onEditData(editId: number) {
    const tempEntiy: WkTblKanrenshaCombineOrgEntityInterface | undefined = combineResultDto.value.listCombineOrg.filter((e) => e.wkTblKanrenshaCombineOrgId === editId)[0];
    if (undefined !== tempEntiy) {
        entityEdit.value = structuredClone(toRaw(tempEntiy));

        // 指定配列に基づきチェックボックスにチェックを打つ
        const listRegist = entityEdit.value.yearArrayText.split(":");
        for (const dto of listYearCheck.value) {
            if (listRegist.includes(String(dto.year))) {
                dto.isSelect = true;
            }
            isEditData.value = true;
        }
    }
}

function onEditUpdate() {

    // 編集中のEntityを編集のためにBack側に受け渡し
    let text = "";
    if (isSetPeriod.value === period) {
        // 範囲による指定
        for (let index = entityEdit.value.startYear; index <= entityEdit.value.endYear; index++) {
            text = text + index + ":";
        }
    }
    else {
        // チェックボックスによる指定
        for (const dto of listYearCheck.value) {
            if (dto.isSelect) {
                text = text + dto.year + ":";
            }
        }
        entityEdit.value.startYear = 0;
        entityEdit.value.endYear = 0;
    }
    const data = text === "" ? "" : text.substring(0, text.length - 1);
    entityEdit.value.yearArrayText = data;
    editCapsuleDto.value.wkTblKanrenshaCombineOrgEntity = entityEdit.value;

    title.value = "関連者企業団体履歴ワークテーブル更新";
    getAuthorizedPromiseArea().then(token => {
        const url = urlBack + "/regist-combine/update";
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
                    // 値を入れ替える?
                    onSearch();
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

// データ更新禁止
const listEditProhibit: string[] = [];
listEditProhibit.push("正常終了");
const isEdit: ComputedRef<string> = computed(() => {
    if (listEditProhibit.includes(entityEdit.value.judgeReason)) {
        return "footer-button-disable";
    } else {
        return "footer-button";
    }
});

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
            <input type="checkbox" v-model="combineCapsuleDto.hasAffectNot">反映なし
            <span class="left-space"><input type="checkbox" v-model="combineCapsuleDto.hasFinished">作業完了</span>
            <span class="left-space"><input type="checkbox" v-model="combineCapsuleDto.hasHistorry">処理対象外履歴</span>
        </div>
    </div>

    <div class="one-line">
        <div class="left-area">
            作業結果表示
        </div>
        <div class="right-area">
            <button @click="onSearch">表示</button>
        </div>
    </div>
    <!-- ページング -->
    <PagingControl :all-count="allCount" :limit="limit" :page-number="pageNumber"
        @send-paging-number="recievePagingNumber"></PagingControl>

    <h3>関連者企業／団体処理予定</h3>
    <div class="one-line">
        <table>
            <tbody>
                <tr>
                    <th>反映該否</th>
                    <th>紐づけ関連者区分</th>
                    <th>個人関連者コード</th>
                    <th>個人氏名</th>
                    <th>団体関連者コード</th>
                    <th>団体名称</th>
                    <th>紐づけ開始年</th>
                    <th>紐づけ終了年</th>
                    <th>登録年列挙</th>
                </tr>
            </tbody>
            <tbody v-for="entity of combineResultDto.listCombineOrg" :key="entity.wkTblKanrenshaCombineOrgId">
                <tr>
                    <td rowspan="2"><input type="checkbox" v-model="entity.isAffected" disabled="true">反映する</td>
                    <td colspan="8">{{ entity.judgeReason }}</td>
                </tr>
                <tr>
                    <td>{{ KanrenshaKbnConstants.getLabel(entity.kanrenshaKbn) }}</td>
                    <td><button @click="onEditData(entity.wkTblKanrenshaCombineOrgId)" :disabled="!entity.isLatest">{{
                        entity.personKanrenshaCode }}</button></td>
                    <td>{{ entity.personName }}</td>
                    <td>{{ entity.orgKanrenshaCode }}</td>
                    <td>{{ entity.orgName }}</td>
                    <td>{{ entity.startYear }}</td>
                    <td>{{ entity.endYear }}</td>
                    <td>{{ entity.yearArrayText }}</td>
                </tr>
            </tbody>
        </table>
    </div>

    <!-- ページング -->
    <PagingControl :all-count="allCount" :limit="limit" :page-number="pageNumber"
        @send-paging-number="recievePagingNumber"></PagingControl>

    <!-- 編集処理 -->
    <!-- TODO 個人検索画面と企業または政治団体検索コンポーネントを追加 -->
    <div v-if="isEditData" class="overBackground"></div>
    <div class="overComponent" v-if="isEditData">

        <div class="one-line">
            <div class="left-area">
                反映該否
            </div>
            <div class="right-area">
                <input type="checkbox" v-model="entityEdit.isAffected">反映あり
                <span class="left-space">※データが重複していると反映該否が動かせないことがあります</span>
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
                関連者区分
            </div>
            <div class="right-area">
                {{ KanrenshaKbnConstants.getLabel(entityEdit.kanrenshaKbn) }}
            </div>
        </div>

        <div class="one-line">
            <div class="left-area">
                個人関連者番号
            </div>
            <div class="right-area">
                <input type="text" v-model="entityEdit.personKanrenshaCode" />
            </div>
        </div>

        <div class="one-line">
            <div class="left-area">
                個人姓名
            </div>
            <div class="right-area">
                <input type="text" v-model="entityEdit.personName" />
            </div>
        </div>

        <div class="one-line">
            <div class="left-area">
                団体関連者番号
            </div>
            <div class="right-area">
                <input type="text" v-model="entityEdit.orgKanrenshaCode" />
            </div>
        </div>

        <div class="one-line">
            <div class="left-area">
                団体名称
            </div>
            <div class="right-area">
                <input type="text" v-model="entityEdit.orgName" />
            </div>
        </div>


        <div class="one-line">
            <div class="left-area">
                年度登録方法
            </div>
            <div class="right-area">
                <span><input type="radio" v-model="isSetPeriod" :value="period" id="dataFormat">期間で指定</span>
                <span class="left-space"><input type="radio" v-model="isSetPeriod" :value="point"
                        id="dataFormat">具体的に指定</span>
            </div>
        </div>

        <!-- 登録年を期間で指定  -->
        <div v-if="isSetPeriod === period">
            <div class="one-line">
                <div class="left-area">
                    開始年
                </div>
                <div class="right-area">
                    <input type="number" v-model="entityEdit.startYear" />
                </div>
            </div>

            <div class="one-line">
                <div class="left-area">
                    終了年
                </div>
                <div class="right-area">
                    <input type="number" v-model="entityEdit.endYear" />
                </div>
            </div>

        </div>
        <!-- 登録年を期間で指定 -->
        <div v-if="isSetPeriod === point">
            <div class="one-line">
                <div class="left-area">
                    登録年
                </div>
                <div class="right-area">
                    <span v-for="dto in listYearCheck" :key="dto.year">
                        <input type="checkbox" v-model="dto.isSelect" />{{ dto.year }} 年
                    </span>
                </div>
            </div>
        </div>

        <div class="footer">
            <button @click="onEditClose" class="footer-button">キャンセル</button>
            <button @click="onEditUpdate" class="left-space" :class="isEdit">送信</button>
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
</style>
