<script setup lang="ts">
import { computed, ref, toRaw, type ComputedRef, type Ref } from 'vue';
import { getErrorMessage, MessageConstants, MessageView, PagingControl, type LeastUserDtoInterface } from 'seijishikin-jp-normalize_common-tool';
import { SearchWkTblPagingCapsuleDto, type SearchWkTblPagingCapsuleDtoInterface } from '../../dto/add_xml/searchWkTbPagingCapsuleDto';
import { SearchWkTblHistorySeijidantaiPagingResultDto, type SearchWkTblHistorySeijidantaiPagingResultDtoInterface } from '../../dto/wktbl_history/searchWkTblHistorySeijidantaiPagingResultDto';
import { WkTblKanrenshaSeijidantaiHistoryEntity, type WkTblKanrenshaSeijidantaiHistoryEntityInterface } from '../../entity/wkTblKanrenshaSeijidantaiHistoryEntity';
import { UpdateWkTblHistorySeijidantaiCapsuleDto, type UpdateWkTblHistorySeijidantaiCapsuleDtoInterface } from '../../dto/wktbl_history/updateWkTblHistorySeijidantaiCapsuleDto';
import RoutePathConstants from '../../../../routePathConstants';
import getAuthorizedPromiseArea from '../../dto/login/getAuthorizedPromiseArea';
import { AccessTokenNotFoundError, TokenRefreshError } from '../../dto/login/errors';
import type { UpdateWkTblHistorySeijidantaiResultDtoInterface } from '../../dto/wktbl_history/updateWkTblHistorySeijidantaiResultDto';

//props,emit
const props = defineProps<{ userDto: LeastUserDtoInterface }>()

// よく使う定数
const BLANK: string = "";
const INIT_NUMBER: number = 0;
const INIT_BOOLEAN: boolean = false;
// const SERVER_STATUS_OK: number = 200;
// const SERVER_STATUS_ERROR: number = 400;
const SEARCH_LIMIT: number = 20;
const INQUIRE_FLG: boolean = false;
const ERR_MESS_ONLY: boolean = true;
const MESS_PAGE_NAME: string = "関連者企業団体履歴ワークテーブル編集";
const INIT_CALLER: string = "no branch";

// メッセージボックス表示定数
const infoLevel: Ref<number> = ref(MessageConstants.LEVEL_NONE);
const messageType: Ref<number> = ref(MessageConstants.VIEW_NONE);
const caller: Ref<string> = ref(INIT_CALLER);
const message: Ref<string> = ref(BLANK);

// back側アクセス
const urlBack: string = RoutePathConstants.DOMAIN + RoutePathConstants.BASE_PATH;

const seijidantaiCapsuleDto: Ref<SearchWkTblPagingCapsuleDtoInterface> = ref(new SearchWkTblPagingCapsuleDto());
seijidantaiCapsuleDto.value.userDto = props.userDto;
seijidantaiCapsuleDto.value.limit = SEARCH_LIMIT;
seijidantaiCapsuleDto.value.pageNumber = INIT_NUMBER;
seijidantaiCapsuleDto.value.hasAffectNot = true;

// Paging
const pageNumber: Ref<number> = ref(INIT_NUMBER);
const allCount: Ref<number> = ref(INIT_NUMBER);
const limit: Ref<number> = ref(SEARCH_LIMIT);


const seijidantaiResultDto: Ref<SearchWkTblHistorySeijidantaiPagingResultDtoInterface> = ref(new SearchWkTblHistorySeijidantaiPagingResultDto());

function onSearchSeijidantai() {

    // seijidantaiResultDto.value.listWktblSeijidantai = getMockWkTblSeijidantaiList();
    // allCount.value = seijidantaiResultDto.value.listWktblSeijidantai.length;

    seijidantaiCapsuleDto.value.allCount = allCount.value;
    seijidantaiCapsuleDto.value.limit = limit.value;
    seijidantaiCapsuleDto.value.pageNumber = pageNumber.value;

    getAuthorizedPromiseArea().then(token => {
        const url = urlBack + "/regist-bulk-history/search-seijidantai";
        const method = "POST";
        const body = JSON.stringify(seijidantaiCapsuleDto.value);
        const headers = {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'X-AUTH-TOKEN': 'Bearer ' + token
        };
        fetch(url, { method, headers, body })
            .then(async (response) => {
                seijidantaiResultDto.value = await response.json();
                if (0 == seijidantaiResultDto.value.listWktblSeijidantai.length) {
                    infoLevel.value = MessageConstants.LEVEL_INFO;
                    messageType.value = MessageConstants.VIEW_TOAST;
                    message.value = "検索結果が0件でした";
                }
                allCount.value = seijidantaiResultDto.value.allCount;
                limit.value = seijidantaiResultDto.value.limit;
                pageNumber.value = seijidantaiResultDto.value.pageNumber;
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

// 編集用
const isEditData: Ref<boolean> = ref(INIT_BOOLEAN);
const entityEdit: Ref<WkTblKanrenshaSeijidantaiHistoryEntityInterface> = ref(new WkTblKanrenshaSeijidantaiHistoryEntity());
const editCapsuleDto: Ref<UpdateWkTblHistorySeijidantaiCapsuleDtoInterface> = ref(new UpdateWkTblHistorySeijidantaiCapsuleDto());
editCapsuleDto.value.userDto = props.userDto;

const findIndex: Ref<number> = ref(INIT_NUMBER);
function onEditData(editId: number) {
    // 指定されたデータを呼び出し(編集決定時には置き換えするので配列indexが必要)
    const tempIndex: number = seijidantaiResultDto.value.listWktblSeijidantai.findIndex((e) => e.wkKanrenshaSeijidantaiHistoryId === editId);
    if (tempIndex !== undefined && seijidantaiResultDto.value.listWktblSeijidantai[tempIndex] !== undefined) {
        entityEdit.value = structuredClone(toRaw(seijidantaiResultDto.value.listWktblSeijidantai[tempIndex]));
        findIndex.value = tempIndex;
    }
    isEditData.value = true;
}
function onEditUpdate() {
    // 編集中のEntityを編集のためにBack側に受け渡し
    editCapsuleDto.value.wkTblKanrenshaSeijidantaiHistoryEntity = entityEdit.value;

    getAuthorizedPromiseArea().then(token => {
        const url = urlBack + "/regist-bulk-history/update-seijidantai";
        const method = "POST";
        const body = JSON.stringify(editCapsuleDto.value);
        const headers = {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'X-AUTH-TOKEN': 'Bearer ' + token
        };
        fetch(url, { method, headers, body })
            .then(async (response) => {
                const resultDto: UpdateWkTblHistorySeijidantaiResultDtoInterface = await response.json();
                message.value = resultDto.message;
                if (resultDto.isFailure) {
                    infoLevel.value = MessageConstants.LEVEL_WARNING;
                    messageType.value = MessageConstants.VIEW_OK;
                    return;
                } else {
                    infoLevel.value = MessageConstants.LEVEL_INFO;
                    messageType.value = MessageConstants.VIEW_TOAST;
                    isEditData.value = false;
                    // 更新したので検索
                    onSearchSeijidantai();
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
    // onSearchでページング複写
    onSearchSeijidantai();
}

function recieveSubmit() {
    infoLevel.value = 0;
    messageType.value = 0;
}
</script>
<template>
    <h3>関連者政治団体検索条件</h3>
    <div class="one-line">
        <div class="left-area">
            検索項目
        </div>
        <div class="right-area">
            <input type="checkbox" v-model="seijidantaiCapsuleDto.hasAffectNot">反映なし
            <span class="left-space"><input type="checkbox" v-model="seijidantaiCapsuleDto.hasFinished">作業完了</span>
            <span class="left-space"><input type="checkbox" v-model="seijidantaiCapsuleDto.hasHistorry">処理対象外履歴</span>
        </div>
    </div>

    <div class="one-line">
        <div class="left-area">
            作業結果表示
        </div>
        <div class="right-area">
            <button @click="onSearchSeijidantai">表示</button>
        </div>
    </div>


    <h3>関連者政治団体処理予定</h3>
    <!-- ページング -->
    <PagingControl :all-count="allCount" :limit="limit" :page-number="pageNumber"
        @send-paging-number="recievePagingNumber"></PagingControl>

    <div class="one-line">
        <!-- ページング -->
        <table>
            <tbody>
                <tr>
                    <th>反映該否</th>
                    <th>政治団体名称</th>
                    <th>政治団体住所</th>
                    <th>政治団体代表者</th>
                    <th>関連者コード</th>
                </tr>
            </tbody>
            <tbody v-for="entity of seijidantaiResultDto.listWktblSeijidantai"
                :key="entity.wkKanrenshaSeijidantaiHistoryId">
                <tr>
                    <td rowspan="2"><input type="checkbox" v-model="entity.isAffected" disabled="true">反映する</td>
                    <td colspan="4">{{ entity.judgeReason }}</td>
                </tr>
                <tr>
                    <td><button @click="onEditData(entity.wkKanrenshaSeijidantaiHistoryId)"
                            :disabled="!entity.isLatest">
                            {{
                                entity.kanrenshaName }}</button></td>
                    <td>{{ entity.allAddress }}</td>
                    <td>{{ entity.seijidantaiDelegate }}</td>
                    <td>{{ entity.orgDelegateCode }}</td>
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
                    政治団体名
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
                    政治団体代表者名
                </div>
                <div class="right-area">
                    <input type="text" v-model="entityEdit.seijidantaiDelegate" />
                </div>
            </div>

            <div class="one-line">
                <div class="left-area">
                    政治団体関連者コード
                </div>
                <div class="right-area">
                    <input type="text" v-model="entityEdit.orgDelegateCode" />
                </div>
            </div>

            <div class="footer">
                <button @click="onEditClose" class="footer-button">キャンセル</button>
                <button @click="onEditUpdate" class="left-space" :class="onSaveClassName">送信</button>
            </div>

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
