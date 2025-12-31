<script setup lang="ts">
import { ref, useTemplateRef, type Ref } from 'vue';
import MockManagerInfo from '../../../test/common/user_info/MockManagerInfo.vue';
import type { LeastUserDtoInterface } from '../../dto/user/leastUserDto';
import { getLoginUser } from '../../utils/getLoginUser';
import KanrenshaKbnConstants from '../../dto/kanrensha/kanrenshaKbnConstants';
import SeijidantaiDantaiKbnConstants from '../../dto/kanrensha/seijidantaiDantaiKbnConstants';
import { SearchWkTblPagingCapsuleDto, type SearchWkTblPagingCapsuleDtoInterface } from '../../dto/add_xml/searchWkTbPagingCapsuleDto';
import { SearchWkTblAddByXmlPagingResultDto, type SearchWkTblAddByXmlPagingResultDtoInterface } from '../../dto/add_xml/searchWkTblAddByXmlPagingResultDto';
import { RegistDataByXmlCapsuleDto, type RegistDataByXmlCapsuleDtoInterface } from '../../dto/add_xml/registDataByXmlCapsuleDto';
import type { StorageFileDtoInterface } from '../../dto/storage_file/storageFileDto';
import MockReadPublishXml from '../../../test/common/read_publish_xml/MockReadPublishXml.vue';
import EditWkTblMinPerson from '../../common/wktbl_edit_min/EditWkTblMinPerson.vue';
import EditWkTblMinKigyouDt from '../../common/wktbl_edit_min/EditWkTblMinKigyouDt.vue';
import EditWkTblMinSeijidantai from '../../common/wktbl_edit_min/EditWkTblMinSeijidantai.vue';
import getMockRegistByXmlList from './mock/getMockRegistByXmlList';
import { FrameworkCapsuleDto, PagingControl, type FrameworkCapsuleDtoInterface } from 'seijishikin-jp-normalize_common-tool';
import { UpdateWkTblAddByXmlTableListCapsuleDto, type UpdateWkTblAddByXmlTableListCapsuleDtoInterface } from '../../dto/add_xml/updateWkTblAddByXmlTableListCapsuleDto';
import { UpdateWkTblAddByXmlCapsuleDto, type UpdateWkTblAddByXmlCapsuleDtoInterface } from '../../dto/add_xml/updateWkTblAddByXmlCapsuleDto';

// ユーザ呼び出し
const userDto: Ref<LeastUserDtoInterface> = ref(getLoginUser());

// back側アクセス
// const urlBack: string = RoutePathConstants.DOMAIN_BACK + RoutePathConstants.PATH_BACK;

// 関連者区分定数
const kanrenshaKbnNoSelect: number = KanrenshaKbnConstants.NO_SELECT;
const kanrenshaKbnPerson: number = KanrenshaKbnConstants.PERSON;
const kanrenshaKbnKigyouDt: number = KanrenshaKbnConstants.KIGYOU_DT;
const kanrenshaKbnSeijidantai: number = KanrenshaKbnConstants.SEIJIDANTAI;

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

// 表示用Dto
const byXmlCapsuleDto: Ref<SearchWkTblPagingCapsuleDtoInterface> = ref(new SearchWkTblPagingCapsuleDto());
const byXmlResultDto: Ref<SearchWkTblAddByXmlPagingResultDtoInterface> = ref(new SearchWkTblAddByXmlPagingResultDto());
byXmlCapsuleDto.value.userDto = userDto.value;
byXmlCapsuleDto.value.limit = 30;
byXmlCapsuleDto.value.pageNumber = 0;
byXmlCapsuleDto.value.hasAffectNot = true;

// XML登録
const capsuleDto: Ref<RegistDataByXmlCapsuleDtoInterface> = ref(new RegistDataByXmlCapsuleDto());
capsuleDto.value.userDto = userDto.value;

const editCapsuleDto: Ref<UpdateWkTblAddByXmlCapsuleDtoInterface> = ref(new UpdateWkTblAddByXmlCapsuleDto());
editCapsuleDto.value.userDto = userDto.value;

// 関連者区分振り分け後の検索処理は各コンポーネントにお願いする
const refEditWkTblMinPerson = useTemplateRef('refEditWkTblMinPerson');
const refEditWkTblMinKigyouDt = useTemplateRef('refEditWkTblMinKigyouDt');
const refEditWkTblMinSeijidantai = useTemplateRef('refEditWkTblMinSeijidantai');

// ファイル保全情報受信
function recieveStorageFileInterface(storageFileDto: StorageFileDtoInterface) {
    capsuleDto.value.storageFileDto = storageFileDto;
}

// XMLファイルを解析しその結果をワークテーブルに保存
function onSaveWkTbl() {
    alert("動作1");
    // getAuthorizedPromiseArea().then(token => {
    //     const url = urlBack + "/analysis-xml/execute";
    //     const method = "POST";
    //     const body = JSON.stringify(capsuleDto.value);
    //     const headers = {
    //         'Accept': 'application/json',
    //         'Content-Type': 'application/json',
    //         'X-AUTH-TOKEN': 'Bearer ' + token
    //     };
    //     fetch(url, { method, headers, body })
    //         .then(async (response) => {
    //             const resultDto: FrameworkMessageAndResultInterface = await response.json();
    //             alert(resultDto.message);
    //             // 処理が成功したら再登録できないようにアップロードファイル情報を初期化
    //             if (response.status === 200) {
    //                 capsuleDto.value.storageFileDto = new StorageFileDto();
    //             }
    //         })
    //         .catch((error) => { alert(error); });
    // });
}

// 作業内容検索
function onSearchAll() {
    byXmlResultDto.value.listXmlEntity = getMockRegistByXmlList();
    allCount.value = byXmlResultDto.value.listXmlEntity.length;

    // getAuthorizedPromiseArea().then(token => {
    //     const url = urlBack + "/regist-by-xml/search";
    //     const method = "POST";
    //     const body = JSON.stringify(byXmlCapsuleDto.value);
    //     const headers = {
    //         'Accept': 'application/json',
    //         'Content-Type': 'application/json',
    //         'X-AUTH-TOKEN': 'Bearer ' + token
    //     };
    //     fetch(url, { method, headers, body })
    //         .then(async (response) => {
    //             byXmlResultDto.value = await response.json();
    //             pageOptionAll.value = getPagingOption(byXmlResultDto.value);
    //         })
    //         .catch((error) => { alert(error); });
    // });

    // 各テーブルの検索を行う
    refEditWkTblMinKigyouDt.value?.onSearchKigyouDt();
    refEditWkTblMinPerson.value?.onSearchPerson();
    refEditWkTblMinSeijidantai.value?.onSearchSeijidantai();
}

// 様式によって元データが異なるので元データ分類リスト
const bikoList: number[] = [];
bikoList.push(3);
bikoList.push(6);

const fullList: number[] = [];
fullList.push(7);
fullList.push(8);
fullList.push(11);
fullList.push(12);

const nameAddressList: number[] = [];
nameAddressList.push(5);
nameAddressList.push(14);
nameAddressList.push(15);
nameAddressList.push(16);

const partnerList: number[] = [];
nameAddressList.push(4);

// 分類編集内容保存
const notUseText: string = "使用しないに変更;";
function onSaveBunrui(editId: number) {
    alert("動作4");
    const findIndex: number = byXmlResultDto.value.listXmlEntity.findIndex((e) => e.wkTblMasterAllByXmlId === editId);
    if (findIndex !== undefined && byXmlResultDto.value.listXmlEntity[findIndex] !== undefined) {
        editCapsuleDto.value.wkTblMasterAllByXmlEntity = byXmlResultDto.value.listXmlEntity[findIndex];
        // 使用しないにチェックが入っていたら必要な編集をする
        if (editCapsuleDto.value.wkTblMasterAllByXmlEntity.isNotUse) {
            editCapsuleDto.value.wkTblMasterAllByXmlEntity.judgeReason = notUseText;
            editCapsuleDto.value.wkTblMasterAllByXmlEntity.isAffected = false;
            editCapsuleDto.value.wkTblMasterAllByXmlEntity.isFinish = true;
        }
    }


    // getAuthorizedPromiseArea().then(token => {
    //     const url = urlBack + "/regist-by-xml/update";
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
    //                 const resultDto: UpdateWkTblAddByXmlResultInterface = await response.json();
    //                 if (response.status === 200) {
    //                     // 正常に更新できた時だけ既存のリストと入れ替え
    //                     // byXmlResultDto.value.listXmlEntity.splice(findIndex, 1, resultDto.wkTblMasterAllByXmlEntity);
    //                     alert(resultDto.message);
    //                     // 再表示
    //                     onSearchAll();
    //                 }
    //             }
    //         })
    //         .catch((error) => { alert(error); });
    // });

}

// 表示中データ全更新
function onSaveTableList() {
    alert("動作5");

    // 編集条件を作成
    const editListCapsuleDto: UpdateWkTblAddByXmlTableListCapsuleDtoInterface = new UpdateWkTblAddByXmlTableListCapsuleDto();
    editListCapsuleDto.userDto = userDto.value;
    // リスト全件について、データを使用しないにチェックが入っていたら必要な編集をする
    for (const entity of byXmlResultDto.value.listXmlEntity) {
        if (entity.isNotUse) {
            entity.judgeReason = notUseText;
            entity.isAffected = false;
            entity.isFinish = true;
        }
    }
    editListCapsuleDto.listWkTblByXml = byXmlResultDto.value.listXmlEntity;

    // getAuthorizedPromiseArea().then(token => {
    //     const url = urlBack + "/regist-by-xml/update-list";
    //     const method = "POST";
    //     const body = JSON.stringify(editListCapsuleDto);
    //     const headers = {
    //         'Accept': 'application/json',
    //         'Content-Type': 'application/json',
    //         'X-AUTH-TOKEN': 'Bearer ' + token
    //     };
    //     fetch(url, { method, headers, body })
    //         .then(async (response) => {
    //             if (response.status < 400) {
    //                 const resultDto: FrameworkMessageAndResultInterface = await response.json();
    //                 alert(resultDto.message);
    //                 // 再表示
    //                 onSearchAll();
    //             }
    //         })
    //         .catch((error) => { alert(error); });
    // });
}

function onCancel() {
    history.back();
}


// 再処理起動条件(ユーザ)
const retryCapsuleDto: Ref<FrameworkCapsuleDtoInterface> = ref(new FrameworkCapsuleDto());
retryCapsuleDto.value.userDto = userDto.value;

// 個人・企業団体・政治団体一括最小マスタ登録処理
function onSave() {
    alert("動作3");
    // getAuthorizedPromiseArea().then(token => {
    //     const url = urlBack + "/regist-by-xml/retry";
    //     const method = "POST";
    //     const body = JSON.stringify(retryCapsuleDto.value);
    //     const headers = {
    //         'Accept': 'application/json',
    //         'Content-Type': 'application/json',
    //         'X-AUTH-TOKEN': 'Bearer ' + token
    //     };
    //     fetch(url, { method, headers, body })
    //         .then(async (response) => {
    //             const resultDto: FrameworkMessageAndResultInterface = await response.json();
    //             alert(resultDto.message);
    //             // 再表示
    //             onSearchAll();
    //         })
    //         .catch((error) => { alert(error); });
    // });
    // // 再表示
    // onSearchAll();
}

function recievePagingNumber(selecteddNumber: number) {
    pageNumber.value = selecteddNumber;
    alert("ページ情報受信");
}
</script>
<template>

    <!-- 管理者メニュー兼チェック -->
    <MockManagerInfo :user-dto="userDto"></MockManagerInfo>

    <h1>政治資金収支報告書XMLより関連者登録</h1>

    <!-- XMLファイルアップロード -->
    <MockReadPublishXml @send-storage-file-interface="recieveStorageFileInterface" :user-dto="userDto">
    </MockReadPublishXml>

    <div class="one-line">
        <div class="left-area">
            解析処理条件
        </div>
        <div class="right-area">
            <input type="checkbox" v-model="capsuleDto.isNotBiko">1項目(備考)を使用して登録する関連者は除外する(様式区分3,4,6)<br>
            <input type="checkbox" v-model="capsuleDto.isNotNameAddress">2項目(名前・住所)を使用して登録する関連者は除外する(様式区分5,14,15,16)<br>
        </div>
    </div>

    <div class="one-line">
        <div class="left-area">
            XML解析処理
        </div>
        <div class="right-area">
            <button @click="onSaveWkTbl">解析開始</button>
        </div>
    </div>

    <div class="one-line">
        <div class="left-area">
            検索項目
        </div>
        <div class="right-area">
            <input type="checkbox" v-model="byXmlCapsuleDto.hasAffectNot">反映なし
            <span class="left-space"><input type="checkbox" v-model="byXmlCapsuleDto.hasFinished">作業完了</span>
            <span class="left-space"><input type="checkbox" v-model="byXmlCapsuleDto.hasHistorry">処理対象外履歴</span>
        </div>
    </div>

    <div class="one-line">
        <div class="left-area">
            作業結果表示
        </div>
        <div class="right-area">
            <button @click="onSearchAll">表示</button>
        </div>
    </div>

    <div class="one-line">
        関連者未区分
    </div>
    <!-- ページング -->
    <PagingControl :all-count="allCount" :limit="limit" :page-number="pageNumber"
        @send-paging-number="recievePagingNumber"></PagingControl>

    <div class="one-line">
        <table>
            <tbody>
                <tr>
                    <th>行番号</th>
                    <th>反映該否</th>
                    <th>個人氏名</th>
                    <th>全住所</th>
                    <th>入力項目1</th>
                    <th>入力項目2</th>
                </tr>
            </tbody>
            <tbody v-for="entity of byXmlResultDto.listXmlEntity" style="margin-bottom: 1%;">

                <tr>
                    <td rowspan="3"> <button @click="onSaveBunrui(entity.wkTblMasterAllByXmlId)"
                            :disabled="entity.isDisabled">編集</button></td>
                    <td rowspan="2"><input type="checkbox" v-model="entity.isAffected"
                            :disabled="entity.isDisabled">編集有効</td>
                    <td colspan="4">処理判定：{{ entity.judgeReason }} <input type="checkbox" v-model="entity.isNotUse"
                            class="left-space">このデータを使用しない</td>
                </tr>

                <!-- 手掛かりが備考欄のみ(様式3,6)  -->
                <tr v-if="bikoList.includes(entity.youshikiKbn)">
                    <td colspan="4"><input type="text" v-model="entity.bikou" :disabled="true"></td>
                </tr>

                <!-- 手掛かりが全部そろっている(様式7,8,11,12)  -->
                <tr v-if="fullList.includes(entity.youshikiKbn)">
                    <td><input type="text" v-model="entity.inputSrcName" :disabled="true"></td>
                    <td><input type="text" v-model="entity.inputSrcAddress" :disabled="true"></td>
                    <td><input type="text" v-model="entity.inputSrcKey" :disabled="true"></td>
                    <td colspan="2">&nbsp;</td>
                </tr>

                <!-- 手掛かりが名前と住所(様式5,14,15)   -->
                <tr v-if="nameAddressList.includes(entity.youshikiKbn)">
                    <td><input type="text" v-model="entity.inputSrcName" :disabled="true"></td>
                    <td><input type="text" v-model="entity.inputSrcAddress" :disabled="true"></td>
                    <td colspan="3">&nbsp;</td>
                </tr>

                <!-- 手掛かりが名前のみ(様式4)   -->
                <tr v-if="partnerList.includes(entity.youshikiKbn)">
                    <td colspan="4"><input type="text" v-model="entity.inputSrcName" :disabled="true"></td>
                </tr>

                <tr>
                    <td>
                        様式 {{ entity.youshikiKbn }} 様式枝区分 {{ entity.youshikiEdaKbn }}<br>
                        <select v-model="entity.kanrenshaKbn"
                            :disabled="!entity.isAffected || fullList.includes(entity.youshikiKbn) || entity.isDisabled">
                            <option :value=kanrenshaKbnNoSelect> </option>
                            <option :value=kanrenshaKbnPerson>個人</option>
                            <option :value=kanrenshaKbnKigyouDt>企業／団体</option>
                            <option :value=kanrenshaKbnSeijidantai>政治団体</option>
                        </select>
                    </td>
                    <td>
                        名称<br>
                        <input type="text" v-model="entity.kanrenshaName"
                            :disabled="!entity.isAffected || entity.isDisabled" />
                    </td>
                    <td>
                        全住所<br>
                        <input type="text" v-model="entity.allAddress"
                            :disabled="!entity.isAffected || entity.isDisabled" />
                    </td>
                    <td>
                        <div v-if="entity.kanrenshaKbn === kanrenshaKbnNoSelect">
                            &nbsp;
                        </div>
                        <div v-if="entity.kanrenshaKbn === kanrenshaKbnPerson">
                            職業<br>
                            <input type="text" v-model="entity.personShokugyou"
                                :disabled="!entity.isAffected || entity.isDisabled" />
                        </div>
                        <div v-if="entity.kanrenshaKbn === kanrenshaKbnKigyouDt">
                            団体代表者<br>
                            <input type="text" v-model="entity.orgDelegate"
                                :disabled="!entity.isAffected || entity.isDisabled" />
                        </div>
                        <div v-if="entity.kanrenshaKbn === kanrenshaKbnSeijidantai">
                            団体代表者<br>
                            <input type="text" v-model="entity.orgDelegate"
                                :disabled="!entity.isAffected || entity.isDisabled" />
                        </div>
                    </td>

                    <td>
                        <div v-if="entity.kanrenshaKbn === kanrenshaKbnNoSelect">
                            &nbsp;
                        </div>
                        <div v-if="entity.kanrenshaKbn === kanrenshaKbnPerson">
                            &nbsp;
                        </div>
                        <div v-if="entity.kanrenshaKbn === kanrenshaKbnKigyouDt">
                            法人番号<br>
                            <input type="text" v-model="entity.houjinNo"
                                :disabled="!entity.isAffected || entity.isDisabled" />
                        </div>
                        <div v-if="entity.kanrenshaKbn === kanrenshaKbnSeijidantai">
                            政治団体区分<br>
                            <select v-model="entity.dantaiKbn" :disabled="!entity.isAffected || entity.isDisabled">
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
                    </td>

                </tr>
            </tbody>
        </table>
    </div>
    <div class="one-line">
        <button @click="onSaveTableList">表示中の {{ limit }}件すべて更新</button>
    </div>


    <!-- ワークテーブル編集マスタ最小個人 -->
    <EditWkTblMinPerson ref="refEditWkTblMinPerson" :user-dto="userDto"></EditWkTblMinPerson>
    <hr>

    <!-- ワークテーブル編集マスタ最小企業／団体 -->
    <EditWkTblMinKigyouDt ref="refEditWkTblMinKigyouDt" :user-dto="userDto"></EditWkTblMinKigyouDt>
    <hr>

    <!-- ワークテーブル編集マスタ最小政治団体 -->
    <EditWkTblMinSeijidantai ref="refEditWkTblMinSeijidantai" :user-dto="userDto"></EditWkTblMinSeijidantai>
    <hr>

    <div class="footer">
        <button @click="onCancel" class="footer-button">キャンセル</button>
        <button @click="onSave" class="footer-button left-space">送信</button>
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

td.top {
    border-style: solid;
    border-top-width: 2px;
    border-left-width: 1px;
    border-bottom-width: 1px;
    border-right-width: 1px;
}

td.topCorner {
    border-style: solid;
    border-top-width: 2px;
    border-left-width: 1px;
    border-bottom-width: 1px;
    border-right-width: 2px;
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
