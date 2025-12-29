<script setup lang="ts">
import { ref, toRaw, type Ref } from 'vue';
import type { LeastUserDtoInterface } from '../../dto/user/leastUserDto';
import { PagingControl } from 'seijishikin-jp-normalize_common-tool';
import SeijidantaiDantaiKbnConstants from '../../dto/kanrensha/seijidantaiDantaiKbnConstants';
import { SearchWkTblPagingCapsuleDto, type SearchWkTblPagingCapsuleDtoInterface } from '../../dto/add_xml/searchWkTbPagingCapsuleDto';
import { SearchWkTblStdSeijidantaiPagingResultDto, type SearchWkTblStdSeijidantaiPagingResultDtoInterface } from '../../dto/wktbl_std/searchWkTblStdSeijidantaiPagingResultDto';
import { WkTblKanrenshaSeijidantaiMasterEntity, type WkTblKanrenshaSeijidantaiMasterEntityInterface } from '../../entity/wkTblKanrenshaSeijidantaiMasterEntity';
import { UpdateWkTblStdSeijidantaiCapsuleDto, type UpdateWkTblStdSeijidantaiCapsuleDtoInterface } from '../../dto/wktbl_std/updateWkTblStdSeijidantaiCapsuleDto';
import getMockWkTblPoliOrgList from './mock/getMockWkTblSeijidantaiList';

//props,emit
const props = defineProps<{ userDto: LeastUserDtoInterface }>()

// back側アクセス
//const urlBack: string = RoutePathConstants.DOMAIN_BACK + RoutePathConstants.PATH_BACK;

// 政治団体区分定数
const seijidantaiKbnNoSelect: string = SeijidantaiDantaiKbnConstants.NO_SELECT;
const seijidantaiKbnSeitou: string = SeijidantaiDantaiKbnConstants.SEITOU;
const seijidantaiKbnSeitouShibu: string = SeijidantaiDantaiKbnConstants.SEITOU_SHIBU;
const seijidantaiKbnSeijishikin: string = SeijidantaiDantaiKbnConstants.SEIJI_SHIKIN_DANTAI;
const seijidantaiKbn18Jou2KouDantai: string = SeijidantaiDantaiKbnConstants.DANTAI_18JOU_2KOU;
const seijidantaiKbnSonota: string = SeijidantaiDantaiKbnConstants.SONOTA;
const seijidantaiKbnSonotaShibu: string = SeijidantaiDantaiKbnConstants.SONOTA_SHIBU;

// Paging
const pageNumber: Ref<number> = ref(0);
const allCount: Ref<number> = ref(0);
const limit: Ref<number> = ref(20);

const seijidantaiCapsuleDto: Ref<SearchWkTblPagingCapsuleDtoInterface> = ref(new SearchWkTblPagingCapsuleDto());
seijidantaiCapsuleDto.value.userDto = props.userDto;
//seijidantaiCapsuleDto.value.limit = 30;
seijidantaiCapsuleDto.value.pageNumber = 0;
seijidantaiCapsuleDto.value.hasAffectNot = true;

const seijidantaiResultDto: Ref<SearchWkTblStdSeijidantaiPagingResultDtoInterface> = ref(new SearchWkTblStdSeijidantaiPagingResultDto);

function onSearchSeijidantai() {
    seijidantaiResultDto.value.listWktblSeijidantai = getMockWkTblPoliOrgList();
    allCount.value = seijidantaiResultDto.value.listWktblSeijidantai.length;

    // getAuthorizedPromiseArea().then(token => {
    //     const url = urlBack + "/regist-bulk-master-std/search-poli-org";
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
const entityEdit: Ref<WkTblKanrenshaSeijidantaiMasterEntityInterface> = ref(new WkTblKanrenshaSeijidantaiMasterEntity());
const editCapsuleDto: Ref<UpdateWkTblStdSeijidantaiCapsuleDtoInterface> = ref(new UpdateWkTblStdSeijidantaiCapsuleDto());
editCapsuleDto.value.userDto = props.userDto;

function onEditData(editId: number) {
    // 指定されたデータを呼び出し(編集決定時には置き換えするので配列indexが必要)
    const findIndex: number = seijidantaiResultDto.value.listWktblSeijidantai.findIndex(
        (e) => e.wkTblKanrenshaSeijidantaiMasterId === editId);
    if (findIndex !== undefined && seijidantaiResultDto.value.listWktblSeijidantai[findIndex] !== undefined) {
        entityEdit.value = structuredClone(toRaw(seijidantaiResultDto.value.listWktblSeijidantai[findIndex]));
    }
    isEditData.value = true;
}
function onEditUpdate() {

    // 編集中のEntityを編集のためにBack側に受け渡し
    editCapsuleDto.value.wkTblMasterSeijidantaiEntity = entityEdit.value;

    // getAuthorizedPromiseArea().then(token => {
    //     const url = urlBack + "/regist-bulk-master-std/update-poli-org";
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
    //                 const resultDto: UpdateWkTblStdSeijidantaiResultInterface = await response.json();
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
    // seijidantaiResultDto.value.listWktblSeijidantai.splice(findIndex, 1, structuredClone(toRaw(entityEdit.value)));
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
    <h3>関連者政治検索条件</h3>

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
        <table class="std">
            <tbody>
                <tr>
                    <th>反映該否</th>
                    <th>政治団体名称</th>
                    <th>全住所</th>
                    <th>団体代表者名</th>
                    <th>政治団体区分</th>
                    <th>住所郵便番号まで</th>
                    <th>住所番地まで</th>
                    <th>住所建物まで</th>
                    <th>郵便番号1</th>
                    <th>郵便番号2</th>
                    <th>電話番号市外局番</th>
                    <th>電話番号局番</th>
                    <th>電話番号番号</th>
                    <th>メールアドレス</th>
                    <th>自分の公式サイト</th>
                    <th>SNS名称</th>
                    <th>SNSアカウント</th>
                    <th>関連者団体名称かな</th>
                    <th>団体代表者関連者コード</th>
                    <th>会計責任者関連者個人コード</th>
                    <th>会計責任者関連者個人氏名</th>
                    <th>地方公共団体コード</th>
                    <th>町字Id</th>
                    <th>街区Id</th>
                    <th>住居Id</th>
                    <th>住居2Id</th>
                </tr>
            </tbody>
            <tbody v-for="entity of seijidantaiResultDto.listWktblSeijidantai"
                :key="entity.wkTblKanrenshaSeijidantaiMasterId">
                <tr>
                    <td rowspan="2"><input type="checkbox" v-model="entity.isAffected" disabled="true">反映する</td>
                    <td colspan="25">{{ entity.judgeReason }}</td>
                </tr>
                <tr>
                    <td><button @click="onEditData(entity.wkTblKanrenshaSeijidantaiMasterId)"
                            :disabled="!entity.isLatest">{{
                                entity.kanrenshaName }}</button></td>
                    <td>{{ entity.allAddress }}</td>
                    <td>{{ entity.seijidantaiDelegate }}</td>
                    <td>{{ entity.dantaiKbn }}</td>
                    <td>{{ entity.addressPostal }}</td>
                    <td>{{ entity.addressBlock }}</td>
                    <td>{{ entity.addressBuilding }}</td>
                    <td>{{ entity.postalcode1 }}</td>
                    <td>{{ entity.postalcode2 }}</td>
                    <td>{{ entity.phon1 }}</td>
                    <td>{{ entity.phon2 }}</td>
                    <td>{{ entity.phon3 }}</td>
                    <td>{{ entity.email }}</td>
                    <td>{{ entity.myPortalUrl }}</td>
                    <td>{{ entity.snsServiceName }}</td>
                    <td>{{ entity.snsAccount }}</td>
                    <td>{{ entity.orgNameKana }}</td>
                    <td>{{ entity.orgDelegateCode }}</td>
                    <td>{{ entity.accountMgrCode }}</td>
                    <td>{{ entity.accountMgrName }}</td>
                    <td>{{ entity.lgCode }}</td>
                    <td>{{ entity.machiazaId }}1</td>
                    <td>{{ entity.blkId }}</td>
                    <td>{{ entity.rsdtId }}</td>
                    <td>{{ entity.rsdt2Id }}</td>
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
                    住所郵便番号まで
                </div>
                <div class="right-area">
                    <input type="text" v-model="entityEdit.addressPostal" />
                </div>
            </div>

            <div class="one-line">
                <div class="left-area">
                    住所番地まで
                </div>
                <div class="right-area">
                    <input type="text" v-model="entityEdit.addressBlock" />
                </div>
            </div>

            <div class="one-line">
                <div class="left-area">
                    住所建物まで
                </div>
                <div class="right-area">
                    <input type="text" v-model="entityEdit.addressBuilding" />
                </div>
            </div>

            <div class="one-line">
                <div class="left-area">
                    郵便番号1
                </div>
                <div class="right-area">
                    <input type="text" v-model="entityEdit.postalcode1" />
                </div>
            </div>

            <div class="one-line">
                <div class="left-area">
                    郵便番号2
                </div>
                <div class="right-area">
                    <input type="text" v-model="entityEdit.postalcode2" />
                </div>
            </div>

            <div class="one-line">
                <div class="left-area">
                    電話番号市外局番
                </div>
                <div class="right-area">
                    <input type="text" v-model="entityEdit.phon1" />
                </div>
            </div>

            <div class="one-line">
                <div class="left-area">
                    電話番号局番
                </div>
                <div class="right-area">
                    <input type="text" v-model="entityEdit.phon2" />
                </div>
            </div>

            <div class="one-line">
                <div class="left-area">
                    電話番号番号
                </div>
                <div class="right-area">
                    <input type="text" v-model="entityEdit.phon3" />
                </div>
            </div>

            <div class="one-line">
                <div class="left-area">
                    メールアドレス
                </div>
                <div class="right-area">
                    <input type="text" v-model="entityEdit.email" />
                </div>
            </div>

            <div class="one-line">
                <div class="left-area">
                    自分の公式サイト
                </div>
                <div class="right-area">
                    <input type="text" v-model="entityEdit.myPortalUrl" />
                </div>
            </div>

            <div class="one-line">
                <div class="left-area">
                    SNS名称
                </div>
                <div class="right-area">
                    <input type="text" v-model="entityEdit.snsServiceName" />
                </div>
            </div>

            <div class="one-line">
                <div class="left-area">
                    SNSアカウント
                </div>
                <div class="right-area">
                    <input type="text" v-model="entityEdit.snsAccount" />
                </div>
            </div>

            <div class="one-line">
                <div class="left-area">
                    関連者団体名称かな
                </div>
                <div class="right-area">
                    <input type="text" v-model="entityEdit.orgNameKana" />
                </div>
            </div>

            <div class="one-line">
                <div class="left-area">
                    団体代表者関連者コード
                </div>
                <div class="right-area">
                    <input type="text" v-model="entityEdit.orgDelegateCode" />
                </div>
            </div>

            <div class="one-line">
                <div class="left-area">
                    会計責任者関連者個人コード
                </div>
                <div class="right-area">
                    <input type="text" v-model="entityEdit.accountMgrCode" />
                </div>
            </div>

            <div class="one-line">
                <div class="left-area">
                    会計責任者関連者個人氏名
                </div>
                <div class="right-area">
                    <input type="text" v-model="entityEdit.accountMgrName" />
                </div>
            </div>

            <div class="one-line">
                <div class="left-area">
                    地方公共団体コード
                </div>
                <div class="right-area">
                    <input type="text" v-model="entityEdit.lgCode" />
                </div>
            </div>

            <div class="one-line">
                <div class="left-area">
                    町字Id
                </div>
                <div class="right-area">
                    <input type="text" v-model="entityEdit.machiazaId" />
                </div>
            </div>

            <div class="one-line">
                <div class="left-area">
                    街区Id
                </div>
                <div class="right-area">
                    <input type="text" v-model="entityEdit.blkId" />
                </div>
            </div>

            <div class="one-line">
                <div class="left-area">
                    住居Id
                </div>
                <div class="right-area">
                    <input type="text" v-model="entityEdit.rsdtId" />
                </div>
            </div>

            <div class="one-line">
                <div class="left-area">
                    住居2Id
                </div>
                <div class="right-area">
                    <input type="text" v-model="entityEdit.rsdt2Id" />
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
:root {
    --cell_width: 200 px;
}

table {
    border-style: solid;
    border-width: 1px;
}

table.std {
    border-style: solid;
    border-width: 1px;
    width: calc(200px * 25);
}

td {
    border-style: solid;
    border-width: 1px;
}

th {
    border-style: solid;
    border-width: 1px;
}

th.hojo {
    background-color: lightgray;
    width: --cell_width px;
}

th.explain {
    background-color: lightcyan;
    width: --cell_width px;
}

tbody:after {
    content: "";
    height: 2px;
    width: 100%;
    padding: 2px 0;
    display: block;
}
</style>
