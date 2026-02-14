<script setup lang="ts">
import { ref, type Ref } from 'vue';
import RiyoushaOrgEdit from '../../common/riyousha_edit/RiyoushaOrgEdit.vue';
import { PagingControl, type LeastUserDtoInterface } from 'seijishikin-jp-normalize_common-tool';
import { getLoginUser } from '../../utils/getLoginUser';
import type { RiyoushaOrgMasterEntityInterface } from '../../entity/riyoushaOrgMasterEntity';
import mockGetRiyoushaOrgMasterList from '../../../test/pages/regist_riyousha_org/mockGetRiyoushaOrgMasterList';
import MockAdminInfo from '../../../test/common/user_info/MockAdminInfo.vue';

//仮
// よく使う定数
// const BLANK: string = "";
const INIT_NUMBER: number = 0;
const INIT_BOOLEAN: boolean = false;
const SEARCH_LIMIT: number = 20;
// const SERVER_STATUS_OK: number = 200;
// const SERVER_STATUS_ERROR: number = 400;
// メッセージボックス表示定数
//const infoLevel: Ref<number> = ref(MessageConstants.LEVEL_NONE);
//const messageType: Ref<number> = ref(MessageConstants.VIEW_NONE);
//const title: Ref<string> = ref(BLANK);
//const message: Ref<string> = ref(BLANK);
// Paging
const pageNumber: Ref<number> = ref(INIT_NUMBER);
const allCount: Ref<number> = ref(INIT_NUMBER);
const limit: Ref<number> = ref(SEARCH_LIMIT);

// ユーザ呼び出し
const userDto: Ref<LeastUserDtoInterface> = ref(getLoginUser());
const selectedOrgId: Ref<number> = ref(INIT_NUMBER);
const listRiyoushaOrg: Ref<RiyoushaOrgMasterEntityInterface[]> = ref([]);

function onSearch() {
    listRiyoushaOrg.value = mockGetRiyoushaOrgMasterList();
    allCount.value =listRiyoushaOrg.value.length;
}

const isOrgEdit: Ref<boolean> = ref(INIT_BOOLEAN);
function onEdit(selectedId:number) {
    selectedOrgId.value =selectedId;
    isOrgEdit.value = true;
}
function onDelete() {
    alert("削除");
}
function recieveCancelRiyoushaOrg() {
    isOrgEdit.value = false;
}

function recieveRiyoushaOrgInterface() {

    isOrgEdit.value = false;
}

function onCancel() {
    history.back();
}
function recievePagingNumber(selecteddNumber: number) {
    pageNumber.value = selecteddNumber;
    alert("ページ情報受信");
}

</script>
<template>
    <!-- ユーザ情報 -->
    <MockAdminInfo :user-dto="userDto"></MockAdminInfo>

    <h1>利用者組織検索</h1>

    <h3>検索条件</h3>
    <div class="one-line">
        <div class="left-area">検索語</div>
        <div class="right-area"><input type="text"></div>
    </div>
    <div class="one-line">
        <div class="left-area">検索</div>
        <div class="right-area"><button @click="onSearch">検索</button></div>
    </div>

    <h3>検索結果</h3>
    <div class="one-line-scroll">
        <table>
            <tbody>
                <tr>
                    <th>コード</th>
                    <th>名称</th>
                    <th>住所</th>
                    <th>&nbsp;</th>
                    <th>&nbsp;</th>
                </tr>
            </tbody>

            <tbody>
                <tr v-for="entity in listRiyoushaOrg" :key="entity.riyoushaOrgMasterId">
                    <td>{{ entity.riyoushaOrgMasterCode }}</td>
                    <td>({{ entity.allNameKana }})<br>{{ entity.allName }}</td>
                    <td>{{ entity.addressAll }}</td>
                    <td><button @click="onEdit(entity.riyoushaOrgMasterId)">編集</button></td>
                    <td><button @click="onDelete">削除</button></td>
                </tr>
            </tbody>
        </table>
    </div>
        <!-- ページング -->
        <PagingControl :all-count="allCount" :limit="limit" :page-number="pageNumber"
            @send-paging-number="recievePagingNumber"></PagingControl>


    <div class="footer">
        <button class="footer-button" @click="onCancel">キャンセル</button>
    </div>

    <!-- 利用者組織編集画面 -->
    <div v-if="isOrgEdit" class="overBackground"></div>
    <div v-if="isOrgEdit" class="overComponent">
        <RiyoushaOrgEdit :user-dto="userDto" :selected-id="selectedOrgId"
            @send-cancel-riyousha-org="recieveCancelRiyoushaOrg"
            @send-riyousha-org-interface="recieveRiyoushaOrgInterface"></RiyoushaOrgEdit>
    </div>

</template>
<style scoped></style>
