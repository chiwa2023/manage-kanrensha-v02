<script setup lang="ts">
import { ref, toRaw, type Ref } from 'vue';
import MockManagerInfo from '../../../test/common/user_info/MockManagerInfo.vue';
import { getLoginUser } from '../../utils/getLoginUser';
import { InputAddressDto, InputShokugyouDto, type InputAddressDtoInterface, type InputShokugyouDtoInterface, type LeastUserDtoInterface } from 'seijishikin-jp-normalize_common-tool';
import type { KanrenshaPersonPropertyEntityInterface } from '../../entity/kanrenshaPersonPropertyEntity';
import mockGetPersonApprovalAddressList from '../../../test/pages/works_approval/mock/mockGetPersonApprovalAddressList';
import mockGetPersonApprovaShokugyouList from '../../../test/pages/works_approval/mock/mockGetPersonApprovalShokugyouList';
import ApprovalPersonAddress from '../../common/works_approval/ApprovalPersonAddress.vue';
import ApprovalPersonShokugyou from '../../common/works_approval/ApprovalPersonShokugyou.vue';
import ApprovalSeijidantaiAddress from '../../common/works_approval/ApprovalSeijidantaiAddress.vue';
import ApprovalKigyouDtAddress from '../../common/works_approval/ApprovalKigyouDtAddress.vue';
import type { KanrenshaPersonAddressEntityInterface } from '../../entity/kanrenshaPersonAddressEntity';


const INIT_BOOLEAN: boolean = false;


// ユーザ呼び出し
const userDto: Ref<LeastUserDtoInterface> = ref(getLoginUser());


const viewStatus: Ref<string> = ref("1");
const isAddressInput: Ref<boolean> = ref(INIT_BOOLEAN);
const isPortalAddressInput: Ref<boolean> = ref(INIT_BOOLEAN);

//初期表示日時
const yesterday: Date = new Date();
yesterday.setDate(yesterday.getDate() - 1);
const yesterdayText: string = yesterday.toISOString().substring(0, 10);

// 検索期間
const searchStartDate: Ref<string> = ref(yesterdayText);
const searchEndDate: Ref<string> = ref(yesterdayText);

const isSearchApproval: Ref<boolean> = ref(true);

// 検索表示リスト
const listPersonAdsdress: Ref<KanrenshaPersonAddressEntityInterface[]> = ref([]);
const listPersonShokugyou: Ref<KanrenshaPersonPropertyEntityInterface[]> = ref([]);
// const listCorp: Ref<CorpNoApprovalInterface[]> = ref([]);
// const listPoliOrg: Ref<PoliOrgNoApprovalInterface[]> = ref([]);

function onSearch() {
    switch (viewStatus.value) {
        case "1":
            listPersonAdsdress.value = mockGetPersonApprovalAddressList();
            listPersonShokugyou.value = mockGetPersonApprovaShokugyouList();
            // listCorp.value = [];
            // listPoliOrg.value = [];
            break;

        case "2":
            // listCorp.value = mockGetCorpApprovalList();
            // listPerson.value = [];
            // listPoliOrg.value = [];
            break;

        case "3":
            // listPerson.value = [];
            // listPoliOrg.value = mockGetPoliOrgNoApprovalList();
            // listCorp.value = [];
            // listPerson.value = [];
            break;
        default:
            break;
    }
}

// const inputAddressDto: Ref<InputAddressDto> = ref(new InputAddressDto());
// const bakupInputAddressDto: Ref<InputAddressDto> = ref(new InputAddressDto());
let editCorpNo: string = "";
function editAddress(corpNo: string) {
    // editCorpNo = corpNo;
    // //　選択したデータ
    // const editDto: CorpNoApprovalInterface = listCorp.value.filter((e) => e.corpKanrenshaCode === editCorpNo)[0];// idのため1件
    // inputAddressDto.value = structuredClone(toRaw(editDto.inputAddressDto));
    // bakupInputAddressDto.value = structuredClone(toRaw(editDto.inputAddressDto));
    // //非表示
    // isAddressInput.value = true;
}


/**
 * 関連者検索キャンセル
 */
function recieveCancelInputAddress() {

    // //　選択したデータ
    // const editDto: CorpNoApprovalInterface = listCorp.value.filter((e) => e.corpKanrenshaCode === editCorpNo)[0];// idのため1件
    // editDto.inputAddressDto = bakupInputAddressDto.value;
    // //非表示
    // isAddressInput.value = false;
}

// /**
//  * 関連者検索選択
//  * @param sendDto 選択Dto
//  */
// function recieveInputAddressInterface(sendDto: InputAddressDto) {

//     //　選択したデータ
//     const editDto: CorpNoApprovalInterface = listCorp.value.filter((e) => e.corpKanrenshaCode === editCorpNo)[0];// idのため1件
//     editDto.inputAddressDto = sendDto;

//     //非表示
//     isAddressInput.value = false;
// }


/** 支店を外す */
function changeIsShiten(corpNo: string) {
    // const editDto: CorpNoApprovalInterface = listCorp.value.filter((e) => e.corpKanrenshaCode === corpNo)[0];// idのため1件

    // if (editDto.isShiten === false) {

    //     if (confirm("支店入力をやめて、法人番号情報に書き換えしてもよいですか?")) {
    //         // 仮の法人番号の紐づ区データに更新
    //         editDto.corpKanrenshaCode = editDto.houjinNo + "-abcde";
    //         editDto.inputOrgNameDto.orgName = "ABCD企業";
    //         editDto.inputOrgNameDto.orgNameKana = "えーびーしーでぃーきぎょう";

    //         editDto.inputAddressDto.addressPostal = "法人番号住所1";
    //         editDto.inputAddressDto.addressBlock = "法人番号住所2";
    //         editDto.inputAddressDto.addressBuilding = "法人番号住所3";
    //         editDto.inputAddressDto.rsdtAddressPostl = "法人番号住所1";
    //         editDto.inputAddressDto.rsdtAddressBlock = "法人番号住所2";
    //         editDto.inputAddressDto.rsdtAddressBuilding = "法人番号住所3";
    //     } else {
    //         editDto.isShiten = true;
    //     }
    // }
}


// 簡易住所入力(コピペ)
const inputAddressPortalDto: InputAddressDtoInterface = new InputAddressDto();
function onPortalAddressInput() {
    isPortalAddressInput.value = true;
}
function recieveCancelInputPortalAddress() {
    isPortalAddressInput.value = false;
}
function recieveInputPortalAddressInterface() {
    isPortalAddressInput.value = false;
}


// 簡易職業入力(コピペ)
const inputShokugyouDto: InputShokugyouDtoInterface = new InputShokugyouDto();
const isShokugyouInput: Ref<boolean> = ref(INIT_BOOLEAN);
function onShokugyouInput() {
    isShokugyouInput.value = true;
}


function recieveCancelInputShokugyou() {
    // isShokugyouInput.value = false;
}

// function onCancel() {
//     alert("キャンセル");
//     history.back();

// }
// function onSave() {
//     alert("保存");
// }
</script>
<template>
    <!-- 管理者メニュー兼チェック -->
    <MockManagerInfo :user-dto="userDto"></MockManagerInfo>

    <h1>登録内容承認</h1>

    <div class="one-line">
        <div class="left-area">
            関連者区分
        </div>
        <div class="right-area">
            <span><input type="radio" id="editSelect" v-model="viewStatus" value="1">1.個人住所</span>
            <span class="left-space"><input type="radio" id="editSelect" v-model="viewStatus" value="2">2.企業／団体</span>
            <span class="left-space"><input type="radio" id="editSelect" v-model="viewStatus" value="3">3.政治団体</span>
            <span class="left-space"><input type="radio" id="editSelect" v-model="viewStatus" value="4">4.個人職業</span>
        </div>
    </div>

    <!-- 個人職業承認 -->
    <div v-if="viewStatus == '1'">
        <ApprovalPersonAddress></ApprovalPersonAddress>
    </div>

    <!-- 企業団体住所承認 -->
    <div v-if="viewStatus == '2'">
        <ApprovalKigyouDtAddress></ApprovalKigyouDtAddress>
    </div>

    <!--政治団体住所承認 -->
    <div v-if="viewStatus == '3'">
        <ApprovalSeijidantaiAddress></ApprovalSeijidantaiAddress>
    </div>

    <!-- 個人職業承認 -->
    <div v-if="viewStatus == '4'">
        <ApprovalPersonShokugyou></ApprovalPersonShokugyou>
    </div>

    <!--
    



    <div class="one-line">
        <div class="left-area">
            検索期間
        </div>
        <div class="right-area">
            <span><input type="date" v-model="searchStartDate" value="1">から</span>
            <span class="left-space"><input type="date" v-model="searchEndDate" value="3">まで</span>
        </div>
    </div>
    <div class="one-line">
        <div class="left-area">
            承認必要のみ
        </div>
        <div class="right-area">
            <input type="checkbox" v-model="isSearchApproval">承認必要作業のみ表示する
        </div>
    </div>
    <div class="one-line">
        <div class="left-area">
            検索
        </div>
        <div class="right-area">
            <button @click="onSearch">検索</button>
        </div>
    </div>
-->


    <!-- 
    <div v-if="viewStatus == '1'">
        <h3>検索結果(住所)</h3>
        <!-- 個人住所 
        <div class="one-line-scroll">
            <table>
                <tbody>
                    <tr>
                        <th>関連者番号</th>
                        <th>名前</th>
                        <th>住所郵便番号まで</th>
                        <th>住所番地</th>
                        <th>住所建物</th>
                        <th>承認</th>
                        <th>&nbsp;</th>
                    </tr>
                </tbody>
                <tbody v-for="entity in listPersonAdsdress" :key="entity.kanrenshaPersonAddressId">
                    <tr>
                        <td>{{ entity.personKanrenshaCode }} </td>
                        <td>{{ entity.kanrenshaName }} </td>
                        <td><input type="text" v-model="entity.addressPostal" :disabled="!entity.isPostalEdit"><br>
                            {{ entity.addressPostal }} &nbsp;
                        </td>
                        <td><input type="text" v-model="entity.addressBlock" :disabled="!entity.isBlockEdit"><br>{{
                            entity.addressBlock }}&nbsp;
                        </td>
                        <td><input type="text" v-model="entity.addressBuilding"
                                :disabled="!entity.isBuildingEdit"><br>{{
                                    entity.addressBlock
                                }}&nbsp;
                        </td>
                        <td><input type="checkbox" v-model="entity.isPostalAccept">登録内容を承認</td>
                        <td><button @click="onPortalAddressInput">編集</button></td>
                    </tr>
                </tbody>
            </table>
        </div>
        

        <!-- 個人職業
        <h3>検索結果(職業)</h3>
        <div class="one-line-scroll">
            <table>
                <tbody>
                    <tr>
                        <th>関連者番号</th>
                        <th>名前</th>
                        <th>業種</th>
                        <th>役職</th>
                        <th>自己申告職業</th>
                        <th>法人番号</th>
                        <th>法人名称</th>
                        <th>法人住所</th>
                        <th>承認</th>
                        <th>&nbsp;</th>
                    </tr>
                </tbody>
                <tbody v-for="entity in listPersonShokugyou" :key="entity.kanrenshaPersonPropertyId">
                    <tr>
                        <td>{{ entity.personKanrenshaCode }} </td>
                        <td>{{ entity.kanrenshaName }} </td>
                        <td>{{ entity.gyoushu }} </td>
                        <td>{{ entity.yakushoku }} </td>
                        <td>{{ entity.shokugyouUserWrite }} </td>
                        <td>{{ entity.kigyouDtNo }} </td>
                        <td>{{ entity.kigyouDtName }} </td>
                        <td>{{ entity.kigyouDtAddress }} </td>
                        <td><input type="checkbox" v-model="entity.isShokyouAccept">登録内容を承認</td>
                        <td><button @click="onShokugyouInput">編集</button></td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>

    <!-- 
    <div v-if="viewStatus == '2'">
        <div class="one-line">
            <h3>検索結果</h3>
            <table style="scroll">
                <tbody>
                    <tr>
                        <th rowspan="2">関連者番号</th>
                        <th rowspan="2">法人番号</th>
                        <th rowspan="2">企業・団体名称</th>
                        <th colspan="5">住所</th>
                        <th rowspan="2">承認</th>
                    </tr>
                    <tr>
                        <th>支店</th>
                        <th>住所郵便局まで</th>
                        <th>住所番地</th>
                        <th>住所建物</th>
                        <th>編集</th>
                    </tr>
                </tbody>
                <tbody v-for="dto in listCorp" :key="dto.corpKanrenshaCode">
                    <tr>
                        <td>{{ dto.corpKanrenshaCode }} </td>
                        <td>{{ dto.houjinNo }} </td>
                        <td><input type="text" v-model="dto.inputOrgNameDto.orgNameKana" :disabled="!dto.isShiten"><br><input
                                type="text" v-model="dto.inputOrgNameDto.orgName" :disabled="!dto.isShiten"></td>
                        <td><input type="checkbox" v-model="dto.isShiten" @change="changeIsShiten(dto.corpKanrenshaCode)">支店</td>
                        <td><input type="text" v-model="dto.inputAddressDto.addressPostal"
                                :disabled="!(dto.inputAddressDto.isPostalEdit || dto.isShiten)"><br>{{
                                    dto.inputAddressDto.rsdtAddressPostl }}&nbsp;
                        </td>
                        <td><input type="text" v-model="dto.inputAddressDto.addressBlock"
                                :disabled="!(dto.inputAddressDto.isPostalEdit || dto.isShiten)"><br>{{
                                    dto.inputAddressDto.rsdtAddressBlock }}&nbsp;
                        </td>
                        <td><input type="text" v-model="dto.inputAddressDto.addressBuilding"
                                :disabled="!(dto.inputAddressDto.isPostalEdit || dto.isShiten)"><br>{{
                                    dto.inputAddressDto.rsdtAddressBuilding
                                }}&nbsp;
                        </td>
                        <td><button @click="editAddress(dto.corpKanrenshaCode)">住所編集</button></td>

                        <td><input type="checkbox">登録内容を承認</td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>

    <div v-if="viewStatus == '3'">
        <div class="one-line">
            <h3>検索結果</h3>
            <button @click="onPortalAddressInput">住所入力(簡易)</button><br><br>
            <table style="scroll">
                <tbody>
                    <tr>
                        <th rowspan="2">関連者番号</th>
                        <th rowspan="2">政治団体名称</th>
                        <th colspan="3">住所</th>
                        <th rowspan="2">承認</th>
                    </tr>
                    <tr>
                        <th>住所郵便局まで</th>
                        <th>住所番地</th>
                        <th>住所建物</th>
                    </tr>
                </tbody>
                <tbody v-for="dto in listPoliOrg" :key="dto.poliOrgKanrenshaCode">
                    <tr>
                        <td>{{ dto.poliOrgKanrenshaCode }} </td>
                        <td>{{ dto.inputOrgNameDto.orgNameKana }}<br>{{ dto.inputOrgNameDto.orgName }}</td>
                        <td><input type="text" v-model="dto.inputAddressDto.addressPostal"
                                :disabled="!dto.inputAddressDto.isPostalEdit"><br>{{
                                    dto.inputAddressDto.rsdtAddressPostl }}&nbsp;
                        </td>
                        <td><input type="text" v-model="dto.inputAddressDto.addressBlock"
                                :disabled="!dto.inputAddressDto.isBlockEdit"><br>{{
                                    dto.inputAddressDto.rsdtAddressBlock }}&nbsp;
                        </td>
                        <td><input type="text" v-model="dto.inputAddressDto.addressBuilding"
                                :disabled="!dto.inputAddressDto.isBuildingEdit"><br>{{
                                    dto.inputAddressDto.rsdtAddressBuilding
                                }}&nbsp;
                        </td>
                        <td><input type="checkbox">登録内容を承認</td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>

    <div class="clear-both"><br></div>

    <div class="footer">
        <button @click="onCancel" class="footer-button">キャンセル</button>
        <button @click="onSave" class="footer-button left-space">送信</button>
    </div>

    <!-- 住所入力
    <div v-if="isAddressInput" class="overBackground"></div>
    <div v-if="isAddressInput">
        <div class="overComponent">
            <InputAddress v-if="isAddressInput" :edit-dto="inputAddressDto"
                @send-cancel-input-address="recieveCancelInputAddress"
                @send-input-address-interface="recieveInputAddressInterface">
                ></InputAddress>
        </div>
    </div>

    <!-- 住所入力(紐づけなし)
    <div v-if="isPortalAddressInput" class="overBackground"></div>
    <div v-if="isPortalAddressInput">
        <div class="overComponent">
            <InputAddress v-if="isPortalAddressInput" :edit-dto="inputAddressPortalDto"
                @send-cancel-input-address="recieveCancelInputPortalAddress"
                @send-input-address-interface="recieveInputPortalAddressInterface">

            </InputAddress>
        </div>
    </div>

    <!-- 職業入力(紐づけなし)
    <div v-if="isShokugyouInput" class="overBackground"></div>
    <div v-if="isShokugyouInput">
        <div class="overComponent">
            <InputShokugyou v-if="isShokugyouInput" :edit-dto="inputShokugyouDto" :isfooter="true"
                @send-cancel-input-shokugyou="recieveCancelInputShokugyou">
            </InputShokugyou>
        </div>
    </div>

-->
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

th {
    border-style: solid;
    border-width: 1px;
    text-align: center;
}
</style>
