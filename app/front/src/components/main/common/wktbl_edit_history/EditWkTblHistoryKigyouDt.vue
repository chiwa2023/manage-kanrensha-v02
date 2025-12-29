<script setup lang="ts">
import { PagingControl } from 'seijishikin-jp-normalize_common-tool';
import { ref, toRaw, type Ref } from 'vue';
import { WkTblKanrenshaKigyouDtHistoryEntity, type WkTblKanrenshaKigyouDtHistoryEntityInterface } from '../../entity/wkTblKanrenshaKigyouDtHistoryEntity';
import { SearchWkTblHistoryKigyouDtPagingResultDto, type SearchWkTblHistoryKigyouDtPagingResultDtoInterface } from '../../dto/wktbl_history/searchWkTblHistoryKigyouDtPagingResultDto';
import { SearchWkTblPagingCapsuleDto, type SearchWkTblPagingCapsuleDtoInterface } from '../../dto/add_xml/searchWkTbPagingCapsuleDto';
import type { LeastUserDtoInterface } from '../../dto/user/leastUserDto';
import { UpdateWkTblHistoryKigyouDtCapsuleDto, type UpdateWkTblHistoryKigyouDtCapsuleDtoInterface } from '../../dto/wktbl_history/updateWkTblHistoryKigyouDtCapsuleDto';
import getMockWkTblKigyouDtList from './mock/getMockWkTblKigyouDtList';


//props,emit
const props = defineProps<{ userDto: LeastUserDtoInterface }>()

// back側アクセス
//const urlBack: string = RoutePathConstants.DOMAIN_BACK + RoutePathConstants.PATH_BACK;

const kigyouDtCapsuleDto: Ref<SearchWkTblPagingCapsuleDtoInterface> = ref(new SearchWkTblPagingCapsuleDto());
const kigyouDtResultDto: Ref<SearchWkTblHistoryKigyouDtPagingResultDtoInterface> = ref(new SearchWkTblHistoryKigyouDtPagingResultDto());

kigyouDtCapsuleDto.value.userDto = props.userDto;
//kigyouDtCapsuleDto.value.limit = 30;
kigyouDtCapsuleDto.value.pageNumber = 0;
kigyouDtCapsuleDto.value.hasAffectNot = true;

// Paging
const pageNumber: Ref<number> = ref(0);
const allCount: Ref<number> = ref(0);
const limit: Ref<number> = ref(20);



function onSearchKigyouDt() {
    kigyouDtResultDto.value.listWktblKigyouDt = getMockWkTblKigyouDtList();
    allCount.value = kigyouDtResultDto.value.listWktblKigyouDt.length;

    // getAuthorizedPromiseArea().then(token => {
    //     const url = urlBack + "/regist-bulk-history/search-kigyouDt";
    //     const method = "POST";
    //     const body = JSON.stringify(kigyouDtCapsuleDto.value);
    //     const headers = {
    //         'Accept': 'application/json',
    //         'Content-Type': 'application/json',
    //         'X-AUTH-TOKEN': 'Bearer ' + token
    //     };
    //     fetch(url, { method, headers, body })
    //         .then(async (response) => {
    //             kigyouDtResultDto.value = await response.json();
    //             pageOptionKigyouDt.value = getPagingOption(kigyouDtResultDto.value);
    //         })
    //         .catch((error) => { alert(error); });
    // });
}


// 編集用
const isEditData: Ref<boolean> = ref(false);
const entityEdit: Ref<WkTblKanrenshaKigyouDtHistoryEntityInterface> = ref(new WkTblKanrenshaKigyouDtHistoryEntity());
const editCapsuleDto: Ref<UpdateWkTblHistoryKigyouDtCapsuleDtoInterface> = ref(new UpdateWkTblHistoryKigyouDtCapsuleDto());
editCapsuleDto.value.userDto = props.userDto;

function onEditData(editId: number) {
    // 指定されたデータを呼び出し(編集決定時には置き換えするので配列indexが必要)
    const findIndex:number = kigyouDtResultDto.value.listWktblKigyouDt.findIndex(
        (e: WkTblKanrenshaKigyouDtHistoryEntity) => e.wkKanrenshaKigyouDtHistoryId === editId);
        if(kigyouDtResultDto.value.listWktblKigyouDt[findIndex] !== undefined && findIndex !== undefined){
            entityEdit.value = structuredClone(toRaw(kigyouDtResultDto.value.listWktblKigyouDt[findIndex]));
        }

    isEditData.value = true;
}
function onEditUpdate() {

    // 編集中のEntityを編集のためにBack側に受け渡し
    editCapsuleDto.value.wkTblKanrenshaKigyouDtHistoryEntity = entityEdit.value;

    // getAuthorizedPromiseArea().then(token => {
    //     const url = urlBack + "/regist-bulk-history/update-kigyouDt";
    //     const method = "POST";
    //     const body = JSON.stringify(editCapsuleDto.value);
    //     const headers = {
    //         'Accept': 'application/json',
    //         'Content-Type': 'application/json',
    //         'X-AUTH-TOKEN': 'Bearer ' + token
    //     };
    //     fetch(url, { method, headers, body })
    //         .then(async (response) => {
    //             if (response.status < 400) {
    //                 // TODO 処理内容
    //                 const resultDto: UpdateWkTblHistoryKigyouDtResultInterface = await response.json();
    //                 alert(resultDto.message);
    //                 if (response.status === 200) {
    //                     // 再表示
    //                     onSearchKigyouDt();
    //                 }
    //             }
    //         })
    //         .catch((error) => { alert(error); });
    // });

    // 指定された値に置き換え
    // kigyouDtResultDto.value.listWktblKigyouDt.splice(findIndex,1,structuredClone(toRaw(entityEdit.value)));
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
function isEdit(): boolean {
    return listEditProhibit.includes(entityEdit.value.judgeReason);
}

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


            <div class="one-line">
                <div class="left-area">
                    &nbsp;
                </div>
                <div class="right-area">
                    <button @click="onEditClose">閉じる</button><button class="left-space" @click="onEditUpdate()"
                        :disabled="isEdit()">更新</button>
                </div>
            </div>

        </div>
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
