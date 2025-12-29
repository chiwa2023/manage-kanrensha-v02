<script setup lang="ts">
import { ref, toRaw, type Ref } from 'vue';
import type { LeastUserDtoInterface } from '../../dto/user/leastUserDto';
import { PagingControl } from 'seijishikin-jp-normalize_common-tool';
import { SearchWkTblPagingCapsuleDto, type SearchWkTblPagingCapsuleDtoInterface } from '../../dto/add_xml/searchWkTbPagingCapsuleDto';
import { SearchWkTblStdPersonPagingResultDto, type SearchWkTblStdPersonPagingResultDtoInterface } from '../../dto/wktbl_std/searchWkTblStdPersonPagingResultDto';
import { WkTblKanrenshaPersonMasterEntity, type WkTblKanrenshaPersonMasterEntityInterface } from '../../entity/wkTblKanrenshaPersonMasterEntity';
import { UpdateWkTblStdPersonCapsuleDto, type UpdateWkTblStdPersonCapsuleDtoInterface } from '../../dto/wktbl_std/updateWkTblStdPersonCapsuleDto';
import getMockWkTblPersonList from './mock/getMockWkTblPersonList';

//props,emit
const props = defineProps<{ userDto: LeastUserDtoInterface }>()

// back側アクセス
// const urlBack: string = RoutePathConstants.DOMAIN_BACK + RoutePathConstants.PATH_BACK;

// Paging
const pageNumber: Ref<number> = ref(0);
const allCount: Ref<number> = ref(0);
const limit: Ref<number> = ref(20);

const personCapsuleDto: Ref<SearchWkTblPagingCapsuleDtoInterface> = ref(new SearchWkTblPagingCapsuleDto());

personCapsuleDto.value.userDto = props.userDto;
//personCapsuleDto.value.limit = 30;
personCapsuleDto.value.pageNumber = 0;
personCapsuleDto.value.hasAffectNot = true;

const personResultDto: Ref<SearchWkTblStdPersonPagingResultDtoInterface> = ref(new SearchWkTblStdPersonPagingResultDto());

function onSearchPerson() {
    personResultDto.value.listWktblPerson = getMockWkTblPersonList();
    allCount.value = personResultDto.value.listWktblPerson.length;
    // getAuthorizedPromiseArea().then(token => {
    //     const url = urlBack + "/regist-bulk-master-std/search-person";
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
const entityEdit: Ref<WkTblKanrenshaPersonMasterEntityInterface> = ref(new WkTblKanrenshaPersonMasterEntity());
const editCapsuleDto: Ref<UpdateWkTblStdPersonCapsuleDtoInterface> = ref(new UpdateWkTblStdPersonCapsuleDto());
editCapsuleDto.value.userDto = props.userDto;

function onEditData(editId: number) {
    // 指定されたデータを呼び出し(編集決定時には置き換えするので配列indexが必要)
    const findIndex: number = personResultDto.value.listWktblPerson.findIndex(
        (e) => e.wkTblKanrenshaPersonMasterId === editId);
    if (findIndex !== undefined && personResultDto.value.listWktblPerson[findIndex] !== undefined) {
        entityEdit.value = structuredClone(toRaw(personResultDto.value.listWktblPerson[findIndex]));
    }
    isEditData.value = true;
}
function onEditUpdate() {

    // 編集中のEntityを編集のためにBack側に受け渡し
    editCapsuleDto.value.wkTblMasterPersonEntity = entityEdit.value;

    // getAuthorizedPromiseArea().then(token => {
    //     const url = urlBack + "/regist-bulk-master-std/update-person";
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
    //                 const resultDto: UpdateWkTblStdPersonResultInterface = await response.json();
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
    // personResultDto.value.listWktblPerson.splice(findIndex, 1, structuredClone(toRaw(entityEdit.value)));
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
    <div class="left-area">
        検索項目
    </div>

    <div class="one-line">
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
        <table class="std">
            <tbody>
                <tr>
                    <th>反映該否</th>
                    <th>個人の姓名</th>
                    <th>全住所</th>
                    <th>個人職業</th>
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
                    <th>姓名の姓</th>
                    <th>姓名の名</th>
                    <th>姓名のミドルネーム</th>
                    <th>姓名の姓のかな</th>
                    <th>姓名の名のかな</th>
                    <th>姓名のミドルネームのかな</th>
                    <th>職業の業種</th>
                    <th>職業の役職</th>
                    <th>職業のユーザ記載</th>
                    <th>職業法人番号</th>
                    <th>職業法人住所</th>
                    <th>職業法人名</th>
                    <th>SNS名称</th>
                    <th>SNSアカウント</th>
                    <th>地方公共団体コード</th>
                    <th>町字Id</th>
                    <th>街区Id</th>
                    <th>住居Id</th>
                    <th>住居2Id</th>
                </tr>
            </tbody>
            <tbody v-for="entity of personResultDto.listWktblPerson" :key="entity.wkTblKanrenshaPersonMasterId">
                <tr>
                    <td rowspan="2"><input type="checkbox" v-model="entity.isAffected" disabled="true">反映する</td>
                    <td colspan="31">{{ entity.judgeReason }}</td>
                </tr>
                <tr>
                    <td><button @click="onEditData(entity.wkTblKanrenshaPersonMasterId)" :disabled="!entity.isLatest">{{
                        entity.kanrenshaName }}</button></td>
                    <td>{{ entity.allAddress }}</td>
                    <td>{{ entity.personShokugyou }}</td>
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
                    <td>{{ entity.lastName }}</td>
                    <td>{{ entity.firstName }}</td>
                    <td>{{ entity.middleName }}</td>
                    <td>{{ entity.lastNameKana }}</td>
                    <td>{{ entity.firstNameKana }}</td>
                    <td>{{ entity.middleNameKana }}</td>
                    <td>{{ entity.gyoushu }}</td>
                    <td>{{ entity.yakushoku }}</td>
                    <td>{{ entity.shokugyouUserWrite }}</td>
                    <td>{{ entity.kigyouDtNo }}</td>
                    <td>{{ entity.kigyouDtAddress }}</td>
                    <td>{{ entity.kigyouDtName }}</td>
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
                    姓名の姓
                </div>
                <div class="right-area">
                    <input type="text" v-model="entityEdit.lastName" />
                </div>
            </div>

            <div class="one-line">
                <div class="left-area">
                    姓名の名
                </div>
                <div class="right-area">
                    <input type="text" v-model="entityEdit.firstName" />
                </div>
            </div>

            <div class="one-line">
                <div class="left-area">
                    姓名のミドルネーム
                </div>
                <div class="right-area">
                    <input type="text" v-model="entityEdit.middleName" />
                </div>
            </div>

            <div class="one-line">
                <div class="left-area">
                    姓名の姓のかな
                </div>
                <div class="right-area">
                    <input type="text" v-model="entityEdit.lastNameKana" />
                </div>
            </div>

            <div class="one-line">
                <div class="left-area">
                    姓名の名のかな
                </div>
                <div class="right-area">
                    <input type="text" v-model="entityEdit.firstNameKana" />
                </div>
            </div>

            <div class="one-line">
                <div class="left-area">
                    姓名のミドルネームのかな
                </div>
                <div class="right-area">
                    <input type="text" v-model="entityEdit.middleNameKana" />
                </div>
            </div>

            <div class="one-line">
                <div class="left-area">
                    職業の業種
                </div>
                <div class="right-area">
                    <input type="text" v-model="entityEdit.gyoushu" />
                </div>
            </div>

            <div class="one-line">
                <div class="left-area">
                    職業の役職
                </div>
                <div class="right-area">
                    <input type="text" v-model="entityEdit.yakushoku" />
                </div>
            </div>

            <div class="one-line">
                <div class="left-area">
                    職業のユーザ記載
                </div>
                <div class="right-area">
                    <input type="text" v-model="entityEdit.shokugyouUserWrite" />
                </div>
            </div>

            <div class="one-line">
                <div class="left-area">
                    職業法人番号
                </div>
                <div class="right-area">
                    <input type="text" v-model="entityEdit.kigyouDtNo" />
                </div>
            </div>

            <div class="one-line">
                <div class="left-area">
                    職業法人住所
                </div>
                <div class="right-area">
                    <input type="text" v-model="entityEdit.kigyouDtAddress" />
                </div>
            </div>

            <div class="one-line">
                <div class="left-area">
                    職業法人名
                </div>
                <div class="right-area">
                    <input type="text" v-model="entityEdit.kigyouDtName" />
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
    width: calc(200px * 34);
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
