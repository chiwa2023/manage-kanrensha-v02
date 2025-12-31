<script setup lang="ts">
import { ref, toRaw, type Ref } from 'vue';
import type { LeastUserDtoInterface } from '../../dto/user/leastUserDto';
import { PagingControl } from 'seijishikin-jp-normalize_common-tool';
import SeijidantaiDantaiKbnConstants from '../../dto/kanrensha/seijidantaiDantaiKbnConstants';
import { SearchWkTblPagingCapsuleDto, type SearchWkTblPagingCapsuleDtoInterface } from '../../dto/add_xml/searchWkTbPagingCapsuleDto';
import { SearchWkTblMinSeijidantaiPagingResultDto, type SearchWkTblMinSeijidantaiPagingResultDtoInterface } from '../../dto/wktbl_min/searchWkTblMinSeijidantaiPagingResultDto';
import { WkTblKanrenshaSeijidantaiAddMinEntity, type WkTblKanrenshaSeijidantaiAddMinEntityInterface } from '../../entity/wkTblKanrenshaSeijidantaiAddMinEntity';
import { UpdateWkTblMinSeijidantaiCapsuleDto, type UpdateWkTblMinSeijidantaiCapsuleDtoInterface } from '../../dto/wktbl_min/updateWkTblMinSeijidantaiCapsuleDto';
import getMockWkTblPoliOrgList from './mock/getMockWkTblSeijidantaiList';

//props,emit
const props = defineProps<{ userDto: LeastUserDtoInterface }>()

// back側アクセス
//const urlBack: string = RoutePathConstants.DOMAIN_BACK + RoutePathConstants.PATH_BACK;

// Paging
const pageNumber: Ref<number> = ref(0);
const allCount: Ref<number> = ref(0);
const limit: Ref<number> = ref(20);


// 政治団体区分定数
const seijidantaiKbnNoSelect: string = SeijidantaiDantaiKbnConstants.NO_SELECT;
const seijidantaiKbnSeitou: string = SeijidantaiDantaiKbnConstants.SEITOU;
const seijidantaiKbnSeitouShibu: string = SeijidantaiDantaiKbnConstants.SEITOU_SHIBU;
const seijidantaiKbnSeijishikin: string = SeijidantaiDantaiKbnConstants.SEIJI_SHIKIN_DANTAI;
const seijidantaiKbn18Jou2KouDantai: string = SeijidantaiDantaiKbnConstants.DANTAI_18JOU_2KOU;
const seijidantaiKbnSonota: string = SeijidantaiDantaiKbnConstants.SONOTA;
const seijidantaiKbnSonotaShibu: string = SeijidantaiDantaiKbnConstants.SONOTA_SHIBU;

const seijidantaiCapsuleDto: Ref<SearchWkTblPagingCapsuleDtoInterface> = ref(new SearchWkTblPagingCapsuleDto());
seijidantaiCapsuleDto.value.userDto = props.userDto;
seijidantaiCapsuleDto.value.limit = 30;
seijidantaiCapsuleDto.value.pageNumber = 0;
seijidantaiCapsuleDto.value.hasAffectNot = true;


const seijidantaiResultDto: Ref<SearchWkTblMinSeijidantaiPagingResultDtoInterface> = ref(new SearchWkTblMinSeijidantaiPagingResultDto());


function onSearchSeijidantai() {
    seijidantaiResultDto.value.listWktblSeijidantai = getMockWkTblPoliOrgList();
    allCount.value = seijidantaiResultDto.value.listWktblSeijidantai.length;
    // getAuthorizedPromiseArea().then(token => {
    //     const url = urlBack + "/regist-bulk-master-min/search-poli-org";
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
const entityEdit: Ref<WkTblKanrenshaSeijidantaiAddMinEntityInterface> = ref(new WkTblKanrenshaSeijidantaiAddMinEntity());
const editCapsuleDto: Ref<UpdateWkTblMinSeijidantaiCapsuleDtoInterface> = ref(new UpdateWkTblMinSeijidantaiCapsuleDto());
editCapsuleDto.value.userDto = props.userDto;

function onEditData(editId: number) {
    // 指定されたデータを呼び出し(編集決定時には置き換えするので配列indexが必要)
    const findIndex: number = seijidantaiResultDto.value.listWktblSeijidantai.findIndex((e) => e.wkTblKanrenshaSeijidantaiAddMinId === editId);
    if (findIndex !== undefined && seijidantaiResultDto.value.listWktblSeijidantai[findIndex] !== undefined) {
        entityEdit.value = structuredClone(toRaw(seijidantaiResultDto.value.listWktblSeijidantai[findIndex]));
    }
    isEditData.value = true;
}
function onEditUpdate() {

    // 編集中のEntityを編集のためにBack側に受け渡し
    editCapsuleDto.value.wkTblKanrenshaSeijidantaiAddMinEntity = entityEdit.value;

    // getAuthorizedPromiseArea().then(token => {
    //     const url = urlBack + "/regist-bulk-master-min/update-poli-org";
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
    //                 const resultDto: UpdateWkTblMinSeijidantaiResultInterface = await response.json();
    //                 alert(resultDto.message);
    //                 if (response.status === 200) {
    //                     // 再表示
    //                     onSearchSeijidantai();
    //                 }
    //             }
    //         })
    //         .catch((error) => { alert(error); });
    // });

    // 指定された値に置き換え
    // seijidantaiResultDto.value.listWktblSeijidantai.splice(findIndex,1,structuredClone(toRaw(entityEdit.value)));
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

defineExpose({
    onSearchSeijidantai,
});
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
        <table>
            <tbody>
                <tr>
                    <th>反映該否</th>
                    <th>企業／団体氏名</th>
                    <th>全住所</th>
                    <th>団体代表者</th>
                    <th>団体区分</th>
                </tr>
            </tbody>
            <tbody v-for="entitySeijidantai of seijidantaiResultDto.listWktblSeijidantai"
                :key="entitySeijidantai.wkTblKanrenshaSeijidantaiAddMinId">
                <tr>
                    <td rowspan="2"><input type="checkbox" v-model="entitySeijidantai.isAffected" disabled="true">反映する
                    </td>
                    <td colspan="4">{{ entitySeijidantai.judgeReason }}</td>
                </tr>
                <tr>
                    <td><button @click="onEditData(entitySeijidantai.wkTblKanrenshaSeijidantaiAddMinId)"
                            :disabled="!entitySeijidantai.isLatest"> {{ entitySeijidantai.kanrenshaName }} </button>
                    </td>
                    <td>{{ entitySeijidantai.allAddress }}</td>
                    <td>{{ entitySeijidantai.seijidantaiDelegate }}</td>
                    <td>{{ SeijidantaiDantaiKbnConstants.getLabel(entitySeijidantai.dantaiKbn) }}</td>
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
                    団体区分
                </div>
                <div class="right-area">
                    <select v-model="entityEdit.dantaiKbn">
                        <option :value=seijidantaiKbnNoSelect> </option>
                        <option :value=seijidantaiKbnSeitou>{{
                            SeijidantaiDantaiKbnConstants.getLabel(seijidantaiKbnSeitou)
                        }}</option>
                        <option :value=seijidantaiKbnSeitouShibu>{{
                            SeijidantaiDantaiKbnConstants.getLabel(seijidantaiKbnSeitouShibu) }}</option>
                        <option :value=seijidantaiKbnSeijishikin>{{
                            SeijidantaiDantaiKbnConstants.getLabel(seijidantaiKbnSeijishikin) }}</option>
                        <option :value=seijidantaiKbn18Jou2KouDantai>{{
                            SeijidantaiDantaiKbnConstants.getLabel(seijidantaiKbn18Jou2KouDantai) }}</option>
                        <option :value=seijidantaiKbnSonota>{{
                            SeijidantaiDantaiKbnConstants.getLabel(seijidantaiKbnSonota)
                        }}</option>
                        <option :value=seijidantaiKbnSonotaShibu>{{
                            SeijidantaiDantaiKbnConstants.getLabel(seijidantaiKbnSonotaShibu) }}</option>
                    </select>
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
