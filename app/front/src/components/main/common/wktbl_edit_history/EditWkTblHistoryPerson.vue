<script setup lang="ts">
import { ref, toRaw, type Ref } from 'vue';
import type { LeastUserDtoInterface } from '../../dto/user/leastUserDto';
import { SearchWkTblPagingCapsuleDto, type SearchWkTblPagingCapsuleDtoInterface } from '../../dto/add_xml/searchWkTbPagingCapsuleDto';
import { SearchWkTblHistoryPersonPagingResultDto, type SearchWkTblHistoryPersonPagingResultDtoInterface } from '../../dto/wktbl_history/searchWkTblHistoryPersonPagingResultDto';
import { WkTblKanrenshaPersonHistoryEntity, type WkTblKanrenshaPersonHistoryEntityInterface } from '../../entity/wkTblKanrenshaPersonHistoryEntity';
import { UpdateWkTblHistoryPersonCapsuleDto, type UpdateWkTblHistoryPersonCapsuleDtoInterface } from '../../dto/wktbl_history/updateWkTblHistoryPersonCapsuleDto';
import getMockWkTblPersonList from './mock/getMockWkTblPersonList';
import { PagingControl } from 'seijishikin-jp-normalize_common-tool';

//props,emit
const props = defineProps<{ userDto: LeastUserDtoInterface }>()

// back側アクセス
//const urlBack: string = RoutePathConstants.DOMAIN_BACK + RoutePathConstants.PATH_BACK;

// Paging
const pageNumber: Ref<number> = ref(0);
const allCount: Ref<number> = ref(0);
const limit: Ref<number> = ref(20);


const personCapsuleDto: Ref<SearchWkTblPagingCapsuleDtoInterface> = ref(new SearchWkTblPagingCapsuleDto());

personCapsuleDto.value.userDto = props.userDto;
//personCapsuleDto.value.limit = 30;
personCapsuleDto.value.pageNumber = 0;
personCapsuleDto.value.hasAffectNot = true;

const personResultDto: Ref<SearchWkTblHistoryPersonPagingResultDtoInterface> = ref(new SearchWkTblHistoryPersonPagingResultDto());

function onSearchPerson() {
    personResultDto.value.listWktblPerson = getMockWkTblPersonList();
    allCount.value = personResultDto.value.listWktblPerson.length;

    // getAuthorizedPromiseArea().then(token => {
    //     const url = urlBack + "/regist-bulk-history/search-person";
    //     const method = "POST";
    //     const body = JSON.stringify(personCapsuleDto.value);
    //     const headers = {
    //         'Accept': 'application/json',
    //         'Content-Type': 'application/json',
    //         'X-AUTH-TOKEN': 'Bearer ' + token
    //     };
    //     fetch(url, { method, headers, body })
    //         .then(async (response) => {

    //             personResultDto.value = await response.json();
    //             pageOptionPerson.value = getPagingOption(personResultDto.value);
    //         })
    //         .catch((error) => { alert(error); });
    // });
}

// 編集用
const isEditData: Ref<boolean> = ref(false);
const entityEdit: Ref<WkTblKanrenshaPersonHistoryEntityInterface> = ref(new WkTblKanrenshaPersonHistoryEntity());
const editCapsuleDto: Ref<UpdateWkTblHistoryPersonCapsuleDtoInterface> = ref(new UpdateWkTblHistoryPersonCapsuleDto());
editCapsuleDto.value.userDto = props.userDto;

function onEditData(editId: number) {
    // 指定されたデータを呼び出し(編集決定時には置き換えするので配列indexが必要)
    const findIndex: number = personResultDto.value.listWktblPerson.findIndex((e) => e.wkKanrenshaPersonHistoryId === editId);
    if (findIndex !== undefined && personResultDto.value.listWktblPerson[findIndex] !== undefined) {
        entityEdit.value = structuredClone(toRaw(personResultDto.value.listWktblPerson[findIndex]));
    }

    isEditData.value = true;
}
function onEditUpdate() {

    // 編集中のEntityを編集のためにBack側に受け渡し
    editCapsuleDto.value.wkTblKanrenshaPersonHistoryEntity = entityEdit.value;

    // getAuthorizedPromiseArea().then(token => {
    //     const url = urlBack + "/regist-bulk-history/update-person";
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
    //                 const resultDto: UpdateWkTblHistoryPersonResultInterface = await response.json();
    //                 alert(resultDto.message);
    //                 if (response.status === 200) {
    //                     // 再表示
    //                     onSearchPerson();
    //                 }
    //             }
    //         })
    //         .catch((error) => { alert(error); });
    // });


    // 指定された値に置き換え
    // personResultDto.value.listWktblPerson.splice(findIndex,1,structuredClone(toRaw(entityEdit.value)));
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
    <div class="one-line">
        <table>
            <tbody>
                <tr>
                    <th>反映該否</th>
                    <th>個人氏名</th>
                    <th>全住所</th>
                    <th>職業</th>
                    <th>関連者コード</th>
                </tr>
            </tbody>
            <tbody v-for="entity of personResultDto.listWktblPerson" :key="entity.wkKanrenshaPersonHistoryId">
                <tr>
                    <td rowspan="2"><input type="checkbox" v-model="entity.isAffected" disabled="true">反映する</td>
                    <td colspan="4">{{ entity.judgeReason }}</td>
                </tr>
                <tr>
                    <td><button @click="onEditData(entity.wkKanrenshaPersonHistoryId)" :disabled="!entity.isLatest">{{
                        entity.kanrenshaName }}</button></td>
                    <td>{{ entity.allAddress }}</td>
                    <td>{{ entity.personShokugyou }}</td>
                    <td>{{ entity.personKanrenshaCode }}</td>
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
            <div class="one-line">


                <div class="left-area">
                    個人関連者コード
                </div>
                <div class="right-area">
                    <input type="text" v-model="entityEdit.personKanrenshaCode" />
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
<style scoped></style>
