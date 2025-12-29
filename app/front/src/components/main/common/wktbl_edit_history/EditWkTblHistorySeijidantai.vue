<script setup lang="ts">
import { ref, toRaw, type Ref } from 'vue';
import type { LeastUserDtoInterface } from '../../dto/user/leastUserDto';
import { PagingControl } from 'seijishikin-jp-normalize_common-tool';
import { SearchWkTblPagingCapsuleDto, type SearchWkTblPagingCapsuleDtoInterface } from '../../dto/add_xml/searchWkTbPagingCapsuleDto';
import { SearchWkTblHistorySeijidantaiPagingResultDto, type SearchWkTblHistorySeijidantaiPagingResultDtoInterface } from '../../dto/wktbl_history/searchWkTblHistorySeijidantaiPagingResultDto';
import { WkTblKanrenshaSeijidantaiHistoryEntity, type WkTblKanrenshaSeijidantaiHistoryEntityInterface } from '../../entity/wkTblKanrenshaSeijidantaiHistoryEntity';
import { UpdateWkTblHistorySeijidantaiCapsuleDto, type UpdateWkTblHistorySeijidantaiCapsuleDtoInterface } from '../../dto/wktbl_history/updateWkTblHistorySeijidantaiCapsuleDto';
import getMockWkTblSeijidantaiList from './mock/getMockWkTblSeijidantaiList';

// back側アクセス
// const urlBack: string = RoutePathConstants.DOMAIN_BACK + RoutePathConstants.PATH_BACK;

//props,emit
const props = defineProps<{ userDto: LeastUserDtoInterface }>()




const seijidantaiCapsuleDto: Ref<SearchWkTblPagingCapsuleDtoInterface> = ref(new SearchWkTblPagingCapsuleDto());
seijidantaiCapsuleDto.value.userDto = props.userDto;
//seijidantaiCapsuleDto.value.limit = 30;
seijidantaiCapsuleDto.value.pageNumber = 0;
seijidantaiCapsuleDto.value.hasAffectNot = true;

// Paging
const pageNumber: Ref<number> = ref(0);
const allCount: Ref<number> = ref(0);
const limit: Ref<number> = ref(20);


const seijidantaiResultDto: Ref<SearchWkTblHistorySeijidantaiPagingResultDtoInterface> = ref(new SearchWkTblHistorySeijidantaiPagingResultDto());

function onSearchSeijidantai() {

    seijidantaiResultDto.value.listWktblSeijidantai = getMockWkTblSeijidantaiList();
    allCount.value = seijidantaiResultDto.value.listWktblSeijidantai.length;
    // getAuthorizedPromiseArea().then(token => {
    //     const url = urlBack + "/regist-bulk-history/search-poli-org";
    //     const method = "POST";
    //     const body = JSON.stringify(seijidantaiCapsuleDto.value);
    //     const headers = {
    //         'Accept': 'application/json',
    //         'Content-Type': 'application/json',
    //         'X-AUTH-TOKEN': 'Bearer ' + token
    //     };
    //     fetch(url, { method, headers, body })
    //         .then(async (response) => {
    //             seijidantaiResultDto.value = await response.json();
    //             pageOptionSeijidantai.value = getPagingOption(seijidantaiResultDto.value);
    //         })
    //         .catch((error) => { alert(error); });
    // });

}

// 編集用
const isEditData: Ref<boolean> = ref(false);
const entityEdit: Ref<WkTblKanrenshaSeijidantaiHistoryEntityInterface> = ref(new WkTblKanrenshaSeijidantaiHistoryEntity());
const editCapsuleDto: Ref<UpdateWkTblHistorySeijidantaiCapsuleDtoInterface> = ref(new UpdateWkTblHistorySeijidantaiCapsuleDto());
editCapsuleDto.value.userDto = props.userDto;

function onEditData(editId: number) {
    // 指定されたデータを呼び出し(編集決定時には置き換えするので配列indexが必要)
    const findIndex: number = seijidantaiResultDto.value.listWktblSeijidantai.findIndex((e) => e.wkKanrenshaSeijidantaiHistoryId === editId);
    if (findIndex !== undefined && seijidantaiResultDto.value.listWktblSeijidantai[findIndex] !== undefined) {
        entityEdit.value = structuredClone(toRaw(seijidantaiResultDto.value.listWktblSeijidantai[findIndex]));
    }
    isEditData.value = true;
}
function onEditUpdate() {
    // 編集中のEntityを編集のためにBack側に受け渡し
    editCapsuleDto.value.wkTblKanrenshaSeijidantaiHistoryEntity = entityEdit.value;

    // getAuthorizedPromiseArea().then(token => {
    //     const url = urlBack + "/regist-bulk-history/update-poli-org";
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
    //                 const resultDto: UpdateWkTblHistorySeijidantaiResultInterface = await response.json();
    //                 alert(resultDto.message);
    //                 if (response.status === 200) {
    //                     // 再表示
    //                     onSearchSeijidantai();
    //                 }
    //             }
    //         })
    //         .catch((error) => { alert(error); });
    // });

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
