<script setup lang="ts">
import { ref, toRaw, type Ref } from 'vue';
import type { LeastUserDtoInterface } from '../../dto/user/leastUserDto';
import { PagingControl } from 'seijishikin-jp-normalize_common-tool';
import { SearchWkTblPagingCapsuleDto, type SearchWkTblPagingCapsuleDtoInterface } from '../../dto/add_xml/searchWkTbPagingCapsuleDto';
import { SearchWkTblStdKigyouDtPagingResultDto, type SearchWkTblStdKigyouDtPagingResultDtoInterface } from '../../dto/wktbl_std/searchWkTblStdKigyouDtPagingResultDto';
import { WkTblKanrenshaKigyouDtMasterEntity, type WkTblKanrenshaKigyouDtMasterEntityInterface } from '../../entity/wkTblKanrenshaKigyouDtMasterEntity';
import { UpdateWkTblStdKigyouDtCapsuleDto, type UpdateWkTblStdKigyouDtCapsuleDtoInterface } from '../../dto/wktbl_std/updateWkTblStdKigyouDtKigyouDtCapsuleDto';
import getMockWkTblKigyouDtList from './mock/getMockWkTblKigyouDtList';

//props,emit
const props = defineProps<{ userDto: LeastUserDtoInterface }>()

// Paging
const pageNumber: Ref<number> = ref(0);
const allCount: Ref<number> = ref(0);
const limit: Ref<number> = ref(20);

// back側アクセス
// const urlBack: string = RoutePathConstants.DOMAIN_BACK + RoutePathConstants.PATH_BACK;

const kigyouDtCapsuleDto: Ref<SearchWkTblPagingCapsuleDtoInterface> = ref(new SearchWkTblPagingCapsuleDto());
kigyouDtCapsuleDto.value.userDto = props.userDto;
//kigyouDtCapsuleDto.value.limit = 30;
kigyouDtCapsuleDto.value.pageNumber = 0;
kigyouDtCapsuleDto.value.hasAffectNot = true;

const kigyouDtResultDto: Ref<SearchWkTblStdKigyouDtPagingResultDtoInterface> = ref(new SearchWkTblStdKigyouDtPagingResultDto());

function onSearchKigyouDt() {
    kigyouDtResultDto.value.listWktblKigyouDt = getMockWkTblKigyouDtList();
    allCount.value = kigyouDtResultDto.value.listWktblKigyouDt.length;

    // getAuthorizedPromiseArea().then(token => {
    //     const url = urlBack + "/regist-bulk-master-std/search-kigyouDt";
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
const entityEdit: Ref<WkTblKanrenshaKigyouDtMasterEntityInterface> = ref(new WkTblKanrenshaKigyouDtMasterEntity());
const editCapsuleDto: Ref<UpdateWkTblStdKigyouDtCapsuleDtoInterface> = ref(new UpdateWkTblStdKigyouDtCapsuleDto());
editCapsuleDto.value.userDto = props.userDto;

function onEditData(editId: number) {
    // 指定されたデータを呼び出し(編集決定時には置き換えするので配列indexが必要)
    const findIndex: number = kigyouDtResultDto.value.listWktblKigyouDt.findIndex(
        (e) => e.wkTblKanrenshaKigyouDtMasterId === editId);
    if (findIndex !== undefined && kigyouDtResultDto.value.listWktblKigyouDt[findIndex] !== undefined) {
        entityEdit.value = structuredClone(toRaw(kigyouDtResultDto.value.listWktblKigyouDt[findIndex]));
    }
    isEditData.value = true;
}
function onEditUpdate() {

    // 編集中のEntityを編集のためにBack側に受け渡し
    editCapsuleDto.value.wkTblMasterKigyouDtEntity = entityEdit.value;

    // getAuthorizedPromiseArea().then(token => {
    //     const url = urlBack + "/regist-bulk-master-std/update-kigyouDt";
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
    //                 const resultDto: UpdateWkTblStdKigyouDtResultInterface = await response.json();
    //                 alert(resultDto.message);
    //                 // 表示更新
    //                 onSearchKigyouDt();
    //             }
    //         })
    //         .catch((error) => { alert(error); });
    // });

    // 指定された値に置き換え
    // kigyouDtResultDto.value.listWktblKigyouDt.splice(findIndex, 1, structuredClone(toRaw(entityEdit.value)));
    // 編集コンポーネントを閉じる
    isEditData.value = false;

}

function onEditClose() {
    // 編集コンポーネントを閉じる
    isEditData.value = false;
}

const notUseText: string = "使用しないに変更;";
function onHideData() {
    entityEdit.value.judgeReason = notUseText;
    entityEdit.value.isAffected = false;
    entityEdit.value.isFinish = true;
    onEditUpdate();
}

// 編集画面データ更新禁止
const listEditProhibit: string[] = [];
listEditProhibit.push("正常終了");
function isEdit(): boolean {
    return listEditProhibit.includes(entityEdit.value.judgeReason);
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
        <table class="std">
            <tbody>
                <tr>
                    <th>反映該否</th>
                    <th>団体名称</th>
                    <th>全住所</th>
                    <th>団体代表者</th>
                    <th>法人番号</th>
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
                    <th>外国籍該否</th>
                    <th>法人種別</th>
                    <th>関連者団体名称かな</th>
                    <th>支店該当</th>
                    <th>団体代表者関連者コード</th>
                    <th>SNS名称</th>
                    <th>SNSアカウント</th>
                    <th>地方公共団体コード</th>
                    <th>町字Id</th>
                    <th>街区Id</th>
                    <th>住居Id</th>
                    <th>住居2Id</th>
                </tr>
            </tbody>
            <tbody v-for="entity of kigyouDtResultDto.listWktblKigyouDt" :key="entity.wkTblKanrenshaKigyouDtMasterId">
                <tr>
                    <td rowspan="2"><input type="checkbox" v-model="entity.isAffected" disabled="true">反映する</td>
                    <td colspan="26">{{ entity.judgeReason }}</td>
                </tr>
                <tr>
                    <td><button @click="onEditData(entity.wkTblKanrenshaKigyouDtMasterId)"
                            :disabled="!entity.isLatest">{{
                                entity.kanrenshaName }}</button></td>
                    <td>{{ entity.allAddress }}</td>
                    <td>{{ entity.orgDelegateCode }}</td>
                    <td>{{ entity.houjinNo }}</td>
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
                    <td>{{ entity.isForeign }}</td>
                    <td>{{ entity.houjinSbts }}</td>
                    <td>{{ entity.orgNameKana }}</td>
                    <td>{{ entity.isShiten }}</td>
                    <td>{{ entity.orgDelegateCode }}</td>
                    <td>{{ entity.snsServiceName }}</td>
                    <td>{{ entity.snsAccount }}</td>
                    <td>{{ entity.lgCode }}</td>
                    <td>{{ entity.machiazaId }}</td>
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
                    企業／団体名称
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
                    法人番号
                </div>
                <div class="right-area">
                    <input type="text" v-model="entityEdit.houjinNo" />
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
                    外国籍該否
                </div>
                <div class="right-area">
                    <input type="checkbox" v-model="entityEdit.isForeign" />外国籍
                </div>
            </div>

            <div class="one-line">
                <div class="left-area">
                    法人種別
                </div>
                <div class="right-area">
                    <input type="text" v-model="entityEdit.houjinSbts" />
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
                    支店該否
                </div>
                <div class="right-area">
                    <input type="checkbox" v-model="entityEdit.isForeign" />支店
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
    width: calc(200px * 27);
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
