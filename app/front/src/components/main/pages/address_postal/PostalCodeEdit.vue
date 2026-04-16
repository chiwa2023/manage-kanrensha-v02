<script setup lang="ts">
import { MessageConstants, MessageView, PagingControl, type LeastUserDtoInterface } from 'seijishikin-jp-normalize_common-tool';
import { ref, toRaw, type Ref } from 'vue';
import { getLoginUser } from '../../utils/getLoginUser';
import MockManagerInfo from '../../../test/common/user_info/MockManagerInfo.vue';
import { SearchPostalCodeCapsuleDto, type SearchPostalCodeCapsuleDtoInterface } from '../../dto/address_postal/searchPostalCodeCapsuleDto';
import { SearchPostalCodeResultDto, type SearchPostalCodeResultDtoInterface } from '../../dto/address_postal/searchPostalCodeResultDto';
import { AddressPostalEntity, type AddressPostalEntityInterface } from '../../entity/addressPostalEntity';
import mockGetPostalCodeList from '../../../test/pages/address_postal/mock/mockGetPostalCodeList';

// よく使う定数
const BLANK: string = "";
const INIT_NUMBER: number = 0;
// const SERVER_STATUS_OK: number = 200;
// const SERVER_STATUS_ERROR: number = 400;
const SEARCH_LIMIT: number = 20;
// メッセージ表示定数
const infoLevel: Ref<number> = ref(MessageConstants.LEVEL_NONE);
const messageType: Ref<number> = ref(MessageConstants.VIEW_NONE);
const title: Ref<string> = ref(BLANK);
const message: Ref<string> = ref(BLANK);

// ユーザ呼び出し
const userDto: Ref<LeastUserDtoInterface> = ref(getLoginUser());

// Paging
const pageNumber: Ref<number> = ref(INIT_NUMBER);
const allCount: Ref<number> = ref(INIT_NUMBER);
const limit: Ref<number> = ref(SEARCH_LIMIT);


// 検索条件と検索結果Dt0
const capsuleDto: Ref<SearchPostalCodeCapsuleDtoInterface> = ref(new SearchPostalCodeCapsuleDto());
const resultDto: Ref<SearchPostalCodeResultDtoInterface> = ref(new SearchPostalCodeResultDto());

// 編集対象
const entityEdit: Ref<AddressPostalEntityInterface> = ref(new AddressPostalEntity());
const entityPre: Ref<AddressPostalEntityInterface> = ref(new AddressPostalEntity());
function onSearch() {
    resultDto.value = mockGetPostalCodeList();
    allCount.value = resultDto.value.listItem.length;
    pageNumber.value = resultDto.value.pageNumber;

    // 入力された検索語で郵便番号検索をする
    // getAuthorizedPromiseArea().then(token => {
    //     if (token !== "") {
    //         // const conditionDto: SaveAddressRegistoryCapsuleInterface = new SaveAddressRegistoryCapsuleEntity();
    //         // conditionDto.addressRsdtTemplateEntity = entityEdit.value;
    //
    //         const url = urlBack + "/postal-code/search";
    //         const method = "POST";
    //         const body = JSON.stringify(null);
    //         const headers = {
    //             'Accept': 'application/json',
    //             'Content-Type': 'application/json',
    //             'X-AUTH-TOKEN': 'Bearer ' + token
    //         };
    //         fetch(url, { method, headers, body })
    //             .then(async (response) => {
    //                 // const resultDto: FrameworkMessageAndResultInterface = await response.json();
    //
    //                 // alert(resultDto.message);
    //             })
    //             .catch((e) => { alert(e); });
    //     } else {
    //         alert("エラーのつもり");
    //     }
    // });

    // pageOptionPostal.value = getPagingOption(resultDto.value);
}

function onAddEntity() {
    // TODO 編集があれば保存を促す
    if (isDifferEntity()) {
        selectedId.value = INIT_NUMBER;
        infoLevel.value = MessageConstants.LEVEL_WARNING;
        title.value = "未保存データが存在";
        message.value = "住所入力がされています。保存せず新たな編集対象を表示してよいですか?";
        messageType.value = MessageConstants.VIEW_YES_NO;
        return;
    } else {
        onShowDetail(INIT_NUMBER);
    }
}



const selectedId: Ref<number> = ref(INIT_NUMBER);
function onChangeEdit(id: number) {

    // 編集があれば保存を促す
    // TODO 表示項目に合わせて比較も修正する
    if (isDifferEntity()) {
        selectedId.value = id;
        infoLevel.value = MessageConstants.LEVEL_WARNING;
        title.value = "未保存データが存在";
        message.value = "住所入力がされています。保存せず新たな編集対象を表示してよいですか?";
        messageType.value = MessageConstants.VIEW_YES_NO;
        return;
    }
    else {
        onShowDetail(id);
    }
}

function isDifferEntity(): boolean {
    return entityEdit.value.postal1 !== entityPre.value.postal1
        || entityEdit.value.addressOrg !== entityPre.value.addressOrg
}


function onCancel() {
    history.back();

}
function onSave() {
    alert("保存");

    // 編集された郵便番号
    // getAuthorizedPromiseArea().then(token => {
    //     if (token !== "") {
    //         // const conditionDto: SaveAddressRegistoryCapsuleInterface = new SaveAddressRegistoryCapsuleEntity();
    //         // conditionDto.addressRsdtTemplateEntity = entityEdit.value;
    //
    //         const url = urlBack + "/postal-code/save";
    //         const method = "POST";
    //         const body = JSON.stringify(null);
    //         const headers = {
    //             'Accept': 'application/json',
    //             'Content-Type': 'application/json',
    //             'X-AUTH-TOKEN': 'Bearer ' + token
    //         };
    //         fetch(url, { method, headers, body })
    //             .then(async (response) => {
    //                 // const resultDto: FrameworkMessageAndResultInterface = await response.json();
    //
    //                 // alert(resultDto.message);
    //             })
    //             .catch((e) => { alert(e); });
    //     } else {
    //         alert("エラーのつもり");
    //     }
    // });

}

function recievePagingNumber(selecteddNumber: number) {
    pageNumber.value = selecteddNumber;
    alert("ページ情報受信");
}

function recieveSubmit(button: string) {
    if ("yes" === button) {
        onShowDetail(selectedId.value);
    }

    // 非表示
    infoLevel.value = 0;
    messageType.value = 0;
}

function onShowDetail(id: number) {

    if (INIT_NUMBER == - id) {
        entityEdit.value = new AddressPostalEntity();
        entityPre.value = new AddressPostalEntity();

    } else {
        const tmpEntity: AddressPostalEntity | undefined
            = resultDto.value.listItem.filter(e => e.addressPostalId === id)[0];
        if (tmpEntity !== undefined) {
            entityEdit.value = structuredClone(toRaw(tmpEntity));
            entityPre.value = structuredClone(toRaw(tmpEntity));
        }
    }
}
</script>
<template>
    <!-- 管理者メニュー兼チェック -->
    <MockManagerInfo :user-dto="userDto"></MockManagerInfo>

    <h1>郵便番号編集</h1>

    <h3>編集郵便番号の検索条件</h3>
    <div class="one-line">
        <div class="left-area">
            検索条件(部分一致)
        </div>
        <div class="right-area">
            <input type="text" v-model="capsuleDto.addressWords">
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

    <h3>検索結果</h3>

    <div class="one-line-scroll">
        <table>
            <tbody>
                <tr>
                    <th>郵便番号</th>
                    <th>原文書住所</th>
                    <th>表示住所</th>
                    <th>&nbsp</th>
                </tr>
                <tr v-for="entity of resultDto.listItem" :key="entity.addressPostalId">
                    <td>{{ entity.postal1 }}</td>
                    <td>{{ entity.addressOrg }}</td>
                    <td>{{ entity.addressName }}</td>
                    <td><button @click="onChangeEdit(entity.addressPostalId)">編集</button></td>
                </tr>
            </tbody>
        </table>
    </div>
    <!-- ページング  -->
    <PagingControl :all-count="allCount" :limit="limit" :page-number="pageNumber"
        @send-paging-number="recievePagingNumber"></PagingControl>

    <h3>編集</h3>
    <div class="one-line">
        <div class="left-area">
            追加
        </div>
        <div class="right-area">
            <button @click="onAddEntity">追加</button>
        </div>
    </div>

    <div class="one-line">
        <div class="left-area">
            郵便番号
        </div>
        <div class="right-area">
            <input type="text" v-model="entityEdit.postal1" class="code-input">
        </div>
    </div>

    <div class="one-line">
        <div class="left-area">
            原文書住所
        </div>
        <div class="right-area">
            <input type="text" v-model="entityEdit.addressOrg" class="max-input">
        </div>
    </div>

    <div class="one-line">
        <div class="left-area">
            表示住所
        </div>
        <div class="right-area">
            <input type="text" v-model="entityEdit.addressName" class="max-input">
        </div>
    </div>

    <div class="footer">
        <button @click="onCancel" class="footer-button">キャンセル</button>
        <button @click="onSave" class="footer-button left-space">送信</button>
    </div>

    <!-- メッセージ表示 -->
    <div class="overMessage" v-if="messageType !== MessageConstants.VIEW_NONE">
        <MessageView :info-level="infoLevel" :message-type="messageType" :title="title" :message="message"
            @send-submit="recieveSubmit">
        </MessageView>
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

th {
    border-style: solid;
    border-width: 1px;
    text-align: center;
}
</style>
