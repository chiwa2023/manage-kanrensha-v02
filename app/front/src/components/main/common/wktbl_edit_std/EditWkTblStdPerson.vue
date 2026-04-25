<script setup lang="ts">
import { computed, ref, toRaw, type ComputedRef, type Ref } from 'vue';
import { MessageConstants, MessageView, PagingControl, type LeastUserDtoInterface } from 'seijishikin-jp-normalize_common-tool';
import { SearchWkTblPagingCapsuleDto, type SearchWkTblPagingCapsuleDtoInterface } from '../../dto/add_xml/searchWkTbPagingCapsuleDto';
import { SearchWkTblStdPersonPagingResultDto, type SearchWkTblStdPersonPagingResultDtoInterface } from '../../dto/wktbl_std/searchWkTblStdPersonPagingResultDto';
import { WkTblKanrenshaPersonMasterEntity, type WkTblKanrenshaPersonMasterEntityInterface } from '../../entity/wkTblKanrenshaPersonMasterEntity';
import { UpdateWkTblStdPersonCapsuleDto, type UpdateWkTblStdPersonCapsuleDtoInterface } from '../../dto/wktbl_std/updateWkTblStdPersonCapsuleDto';
import RoutePathConstants from '../../../../routePathConstants';
import getAuthorizedPromiseArea from '../../dto/login/getAuthorizedPromiseArea';
import { AccessTokenNotFoundError, TokenRefreshError } from '../../dto/login/errors';
import type { UpdateWkTblStdPersonResultDtoInterface } from '../../dto/wktbl_std/updateWkTblStdPersonResultDto';

//props,emit
const props = defineProps<{ userDto: LeastUserDtoInterface }>()

// よく使う定数
const BLANK: string = "";
const INIT_NUMBER: number = 0;
const INIT_BOOLEAN: boolean = false;
// const SERVER_STATUS_OK: number = 200;
// const SERVER_STATUS_ERROR: number = 400;
const SEARCH_LIMIT: number = 20;
// メッセージボックス表示定数
const infoLevel: Ref<number> = ref(MessageConstants.LEVEL_NONE);
const messageType: Ref<number> = ref(MessageConstants.VIEW_NONE);
const title: Ref<string> = ref(BLANK);
const message: Ref<string> = ref(BLANK);

// back側アクセス
const urlBack: string = RoutePathConstants.DOMAIN + RoutePathConstants.BASE_PATH;

// Paging
const pageNumber: Ref<number> = ref(INIT_NUMBER);
const allCount: Ref<number> = ref(INIT_NUMBER);
const limit: Ref<number> = ref(SEARCH_LIMIT);

const personCapsuleDto: Ref<SearchWkTblPagingCapsuleDtoInterface> = ref(new SearchWkTblPagingCapsuleDto());

personCapsuleDto.value.userDto = props.userDto;
personCapsuleDto.value.limit = 30;
personCapsuleDto.value.pageNumber = 0;
personCapsuleDto.value.hasAffectNot = true;

const personResultDto: Ref<SearchWkTblStdPersonPagingResultDtoInterface> = ref(new SearchWkTblStdPersonPagingResultDto());

function onSearchPerson() {
    // personResultDto.value.listWktblPerson = getMockWkTblPersonList();
    // allCount.value = personResultDto.value.listWktblPerson.length;

    personCapsuleDto.value.allCount = allCount.value;
    personCapsuleDto.value.limit = limit.value;
    personCapsuleDto.value.pageNumber = pageNumber.value;

    getAuthorizedPromiseArea().then(token => {
        const url = urlBack + "/regist-bulk-master-std/search-person";
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
                    title.value = "タスク情報検索";
                    message.value = "検索結果が0件でした";
                }
                allCount.value = personResultDto.value.allCount;
                limit.value = personResultDto.value.limit;
                pageNumber.value = personResultDto.value.pageNumber;
            })
            .catch((error) => {
                alert(error);
                infoLevel.value = MessageConstants.LEVEL_ERROR;
                messageType.value = MessageConstants.VIEW_OK;
                title.value = "タスク情報検索";
                message.value = "システムエラーが発生しました。システム管理者にお問い合わせください";
            });
    }).catch((e) => {
        infoLevel.value = MessageConstants.LEVEL_ERROR;
        messageType.value = MessageConstants.VIEW_OK;
        if (e instanceof AccessTokenNotFoundError) {
            // トークン保持ができていない場合
            title.value = "現在トークンが存在しません";
            message.value = e.message;
            return;
        }
        if (e instanceof TokenRefreshError) {
            // 取得に失敗している場合
            title.value = "有効期限まじかのトークンを再取得できませんでした";
            message.value = e.message;
            return;
        }
        title.value = "システムエラーが発生しました";
        message.value = "システム管理者にお問い合わせください";
        return;
    });
}

// 編集用
const isEditData: Ref<boolean> = ref(INIT_BOOLEAN);
const entityEdit: Ref<WkTblKanrenshaPersonMasterEntityInterface> = ref(new WkTblKanrenshaPersonMasterEntity());
const editCapsuleDto: Ref<UpdateWkTblStdPersonCapsuleDtoInterface> = ref(new UpdateWkTblStdPersonCapsuleDto());
editCapsuleDto.value.userDto = props.userDto;

const findIndex: Ref<number> = ref(INIT_NUMBER);
function onEditData(editId: number) {
    // 指定されたデータを呼び出し(編集決定時には置き換えするので配列indexが必要)
    const tempIndex: number = personResultDto.value.listWktblPerson.findIndex(
        (e) => e.wkTblKanrenshaPersonMasterId === editId);
    if (tempIndex !== undefined && personResultDto.value.listWktblPerson[tempIndex] !== undefined) {
        entityEdit.value = structuredClone(toRaw(personResultDto.value.listWktblPerson[tempIndex]));
        findIndex.value = tempIndex;
    }
    isEditData.value = true;
}
function onEditUpdate() {

    // 編集中のEntityを編集のためにBack側に受け渡し
    editCapsuleDto.value.wkTblKanrenshaPersonMasterEntity = entityEdit.value;

    title.value = "関連者個人マスタ標準ワークテーブル更新";
    getAuthorizedPromiseArea().then(token => {
        const url = urlBack + "/regist-bulk-master-std/update-person";
        const method = "POST";
        const body = JSON.stringify(editCapsuleDto.value);
        const headers = {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'X-AUTH-TOKEN': 'Bearer ' + token
        };
        fetch(url, { method, headers, body })
            .then(async (response) => {
                const resultDto: UpdateWkTblStdPersonResultDtoInterface = await response.json();
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
                alert(error);
                infoLevel.value = MessageConstants.LEVEL_ERROR;
                messageType.value = MessageConstants.VIEW_OK;
                message.value = "システムエラーが発生しました。システム管理者にお問い合わせください";
            });
    }).catch((e) => {
        infoLevel.value = MessageConstants.LEVEL_ERROR;
        messageType.value = MessageConstants.VIEW_OK;

        if (e instanceof AccessTokenNotFoundError) {
            // トークン保持ができていない場合
            title.value = "現在トークンが存在しません";
            message.value = e.message;
            return;
        }
        if (e instanceof TokenRefreshError) {
            // 取得に失敗している場合
            title.value = "有効期限まじかのトークンを再取得できませんでした";
            message.value = e.message;
            return;
        }
        title.value = "システムエラーが発生しました";
        message.value = "システム管理者にお問い合わせください";
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
    alert("ページ情報受信");
}

function recieveSubmit(button: string) {
    console.log(button); // 警告除け
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
                    <input type="text" v-model="entityEdit.kanrenshaName" class="name-input" />
                </div>
            </div>

            <div class="one-line">
                <div class="left-area">
                    全住所
                </div>
                <div class="right-area">
                    <input type="text" v-model="entityEdit.allAddress" class="name-input" />
                </div>
            </div>

            <div class="one-line">
                <div class="left-area">
                    個人職業
                </div>
                <div class="right-area">
                    <input type="text" v-model="entityEdit.personShokugyou" class="name-input" />
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
                    地番Id
                </div>
                <div class="right-area">
                    <input type="text" v-model="entityEdit.prcId" />
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

            <div class="footer">
                <button @click="onEditClose" class="footer-button">キャンセル</button>
                <button @click="onEditUpdate" class="left-space" :class="onSaveClassName">送信</button>
            </div>

        </div>
    </div>

    <!-- メッセージ表示 -->
    <div class="overMessage" v-if="messageType !== MessageConstants.VIEW_NONE">
        <MessageView :info-level="infoLevel" :message-type="messageType" :title="title" :message="message"
            @send-submit="recieveSubmit">
        </MessageView>
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
