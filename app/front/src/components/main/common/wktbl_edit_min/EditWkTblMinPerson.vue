<script setup lang="ts">
import { computed, ref, toRaw, type ComputedRef, type Ref } from 'vue';
import { getErrorMessage, MessageConstants, MessageView, PagingControl, type LeastUserDtoInterface } from 'seijishikin-jp-normalize_common-tool';
import { SearchWkTblPagingCapsuleDto, type SearchWkTblPagingCapsuleDtoInterface } from '../../dto/add_xml/searchWkTbPagingCapsuleDto';
import { SearchWkTblMinPersonPagingResultDto, type SearchWkTblMinPersonPagingResultDtoInterface } from '../../dto/wktbl_min/searchWkTblMinPersonPagingResultDto';
import { UpdateWkTblMinPersonCapsuleDto, type UpdateWkTblMinPersonCapsuleDtoInterface } from '../../dto/wktbl_min/updateWkTblMinPersonCapsuleDto';
import { WkTblKanrenshaPersonAddMinEntity, type WkTblKanrenshaPersonAddMinEntityInterface } from '../../entity/wkTblKanrenshaPersonAddMinEntity';
import RoutePathConstants from '../../../../routePathConstants';
import getAuthorizedPromiseArea from '../../dto/login/getAuthorizedPromiseArea';
import { AccessTokenNotFoundError, TokenRefreshError } from '../../dto/login/errors';
import type { UpdateWkTblMinPersonResultDtoInterface } from '../../dto/wktbl_min/updateWkTblMinPersonResultDto';

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
const MESS_PAGE_NAME: string = "関連者個人マスタ最小ワークテーブル編集";
const INIT_CALLER: string = "no branch";

// メッセージボックス表示定数
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

// 表示必要なDto
const personCapsuleDto: Ref<SearchWkTblPagingCapsuleDtoInterface> = ref(new SearchWkTblPagingCapsuleDto());
personCapsuleDto.value.userDto = props.userDto;
personCapsuleDto.value.limit = SEARCH_LIMIT;
personCapsuleDto.value.pageNumber = INIT_NUMBER;
personCapsuleDto.value.hasAffectNot = true;

const personResultDto: Ref<SearchWkTblMinPersonPagingResultDtoInterface> = ref(new SearchWkTblMinPersonPagingResultDto());

// 検索処理
function onSearchPerson() {

    // personResultDto.value.listWktblPerson = getMockWkTblPersonList();
    // allCount.value = personResultDto.value.listWktblPerson.length;

    personCapsuleDto.value.allCount = allCount.value;
    personCapsuleDto.value.limit = limit.value;
    personCapsuleDto.value.pageNumber = pageNumber.value;

    getAuthorizedPromiseArea().then(token => {
        const url = urlBack + "/regist-bulk-master-min/search-person";
        const method = "POST";
        const body = JSON.stringify(personCapsuleDto.value);
        const headers = {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'X-AUTH-TOKEN': 'Bearer ' + token
        };
        fetch(url, { method, headers, body })
            .then(async (response) => {
                personResultDto.value = await response.json();
                if (0 == personResultDto.value.listWktblPerson.length) {
                    infoLevel.value = MessageConstants.LEVEL_INFO;
                    messageType.value = MessageConstants.VIEW_TOAST;
                    message.value = "検索結果が0件でした";
                }
                allCount.value = personResultDto.value.allCount;
                limit.value = personResultDto.value.limit;
                pageNumber.value = personResultDto.value.pageNumber;
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
const entityEdit: Ref<WkTblKanrenshaPersonAddMinEntityInterface> = ref(new WkTblKanrenshaPersonAddMinEntity());
const editCapsuleDto: Ref<UpdateWkTblMinPersonCapsuleDtoInterface> = ref(new UpdateWkTblMinPersonCapsuleDto());
editCapsuleDto.value.userDto = props.userDto;

const findIndex: Ref<number> = ref(INIT_NUMBER);
function onEditData(editId: number) {
    // 指定されたデータを呼び出し(編集決定時には置き換えするので配列indexが必要)
    const tempIndex: number = personResultDto.value.listWktblPerson.findIndex((e) => e.wkTblKanrenshaPersonAddMinId === editId);
    if (tempIndex !== undefined && personResultDto.value.listWktblPerson[tempIndex] !== undefined) {
        entityEdit.value = structuredClone(toRaw(personResultDto.value.listWktblPerson[tempIndex]));
        findIndex.value = tempIndex;
    }

    isEditData.value = true;
}

function onEditUpdate() {

    // 編集中のEntityを編集のためにBack側に受け渡し
    editCapsuleDto.value.wkTblKanrenshaPersonAddMinEntity = entityEdit.value;

    getAuthorizedPromiseArea().then(token => {
        const url = urlBack + "/regist-bulk-master-min/update-person";
        const method = "POST";
        const body = JSON.stringify(editCapsuleDto.value);
        const headers = {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'X-AUTH-TOKEN': 'Bearer ' + token
        };
        fetch(url, { method, headers, body })
            .then(async (response) => {
                const resultDto: UpdateWkTblMinPersonResultDtoInterface = await response.json();
                message.value = resultDto.message;
                if (resultDto.isFailure) {
                    infoLevel.value = MessageConstants.LEVEL_WARNING;
                    messageType.value = MessageConstants.VIEW_OK;
                    return;
                } else {
                    infoLevel.value = MessageConstants.LEVEL_INFO;
                    messageType.value = MessageConstants.VIEW_TOAST;
                    isEditData.value = false;
                    onSearchPerson();
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
const onHideDisabled: ComputedRef<boolean> = computed(() => listEditProhibit.includes(entityEdit.value.judgeReason));
function onHideData() {
    entityEdit.value.judgeReason = notUseText;
    entityEdit.value.isAffected = false;
    entityEdit.value.isFinish = true;
    onEditUpdate();
}

function recievePagingNumber(selecteddNumber: number) {
    pageNumber.value = selecteddNumber;
    // onSearchでページング複写
    onSearchPerson();
}

defineExpose({
    onSearchPerson,
});

function recieveSubmit() {
    infoLevel.value = 0;
    messageType.value = 0;
}
</script>
<template>
    <h3>関連者個人検索条件</h3>

    <div class="one-line">
        <div class="left-area">
            検索項目
        </div>
        <div class="right-area">
            <input type="checkbox" v-model="personCapsuleDto.hasAffectNot">反映なし
            <span class="left-space"><input type="checkbox" v-model="personCapsuleDto.hasFinished">作業完了</span>
            <span class="left-space"><input type="checkbox" v-model="personCapsuleDto.hasHistorry">処理対象外履歴</span>
        </div>
    </div>

    <div class="one-line">
        <div class="left-area">
            作業結果表示
        </div>
        <div class="right-area">
            <button @click="onSearchPerson">表示</button>
        </div>
    </div>

    <h3>関連者個人処理予定</h3>
    <!-- ページング -->
    <PagingControl :all-count="allCount" :limit="limit" :page-number="pageNumber"
        @send-paging-number="recievePagingNumber"></PagingControl>
    <div class="one-line-scroll">
        <table>
            <tbody>
                <tr>
                    <th>反映該否</th>
                    <th>個人氏名</th>
                    <th>全住所</th>
                    <th>職業</th>
                </tr>
            </tbody>
            <tbody v-for="entityPerson of personResultDto.listWktblPerson"
                :key="entityPerson.wkTblKanrenshaPersonAddMinId">
                <tr>
                    <td rowspan="2"><input type="checkbox" v-model="entityPerson.isAffected" disabled="true">反映する</td>
                    <td colspan="3">{{ entityPerson.judgeReason }}</td>
                </tr>
                <tr>
                    <td><button @click="onEditData(entityPerson.wkTblKanrenshaPersonAddMinId)"
                            :disabled="!entityPerson.isLatest">{{ entityPerson.kanrenshaName }}</button></td>
                    <td>{{ entityPerson.allAddress }}</td>
                    <td>{{ entityPerson.personShokugyou }}</td>
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
                        class="left-space" :disabled="onHideDisabled">このデータを使用しない</button>
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
                    個人姓名
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
                    個人職業
                </div>
                <div class="right-area">
                    <input type="text" v-model="entityEdit.personShokugyou" />
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
