<script setup lang="ts">
import { ref, type Ref } from 'vue';
import MockAdminInfo from '../../../test/common/user_info/MockAdminInfo.vue';
// import UserRoleConstants from '../../dto/user/userRoleConstants';
// import { LeastUserDto, type LeastUserDtoInterface } from '../../dto/user/leastUserDto';
// import type RiyoushaManagerInterface from '../../entity/riyoushaManagerEntity';
// import RiyoushaManagerEntity from '../../entity/riyoushaManagerEntity';
import {  InputPersonNameDto, ViewInputPersonName, type InputPersonNameDtoInterface, } from 'seijishikin-jp-normalize_common-tool';
//import { ComponentCatalog, InputPersonNameDto, ViewInputPersonName, type InputPersonNameDtoInterface } from 'seijishikin-jp-normalize_common-tool';

// import type RiyoushaComradeInterface from '../../../entity/riyoushaComradeEntity';
// import RiyoushaComradeEntity from '../../../entity/riyoushaComradeEntity';
// import type RiyoushaManagerInterface from '../../../entity/riyoushaManagerEntity';
// import RiyoushaManagerEntity from '../../../entity/riyoushaManagerEntity';
// import RiyoushaAdminInterface from '../../../entity/riyoushaAdminEntity';
// import RiyoushaAdminEntity from '../../../entity/riyoushaAdminEntity';
// import UserPersonLeastDto from './../../../dto/user/userPersonLeastDto';
// import type UserPersonLeastInterface from './../../../dto/user/userPersonLeastDto';
// import RiyoushaComradeEdit from '../../common/riyousha_comrade_edit/RiyoushaComradeEdit.vue';
// import RiyoushaManagerEdit from '../../common/riyousha_manager_edit/RiyoushaManagerEdit.vue';
// import RiyoushaAdminEdit from '../../common/riyousha_admin_edit/RiyoushaAdminEdit.vue';
// import type SearchRiyoushaResultInterface from '../../../dto/riyousha/searchRiyoushaResultDto';
// import SearchRiyoushaResultDto from '../../../dto/riyousha/searchRiyoushaResultDto';
// import type SearchRiyoushaCapsuleInterface from '../../../dto/riyousha/searchRiyoushaCapsuleDto';
// import SearchRiyoushaCapsuleDto from '../../../dto/riyousha/searchRiyoushaCapsuleDto';
// import getAuthorizedPromiseArea from '../../../dto/login/getAuthorizedPromiseArea';
// import RoutePathConstants from '../../../routePathConstants';

// よく使う定数
// const BLANK: string = "";
// const SERVER_STATUS_OK: number = 200;
// const SERVER_STATUS_ERROR: number = 400;

// // back側アクセス
// const urlBack: string = RoutePathConstants.DOMAIN_BACK + RoutePathConstants.PATH_BACK;

// // ユーザメニューで取得したユーザを保持
// const sessionStorage = window["sessionStorage"];
// const userDtoText: string | null = sessionStorage.getItem("userDto");
// const userDto: Ref<LeastUserDtoInterface> = ref(new LeastUserDto());
// if (userDtoText !== null) {
//     userDto.value = JSON.parse(userDtoText);
// }

// // 入力用変数
// const viewStatus: Ref<string> = ref("");
// const inputComradeEntity: Ref<RiyoushaComradeInterface> = ref(new RiyoushaComradeEntity());
// const inputManagerEntity: Ref<RiyoushaManagerInterface> = ref(new RiyoushaManagerEntity());
// const inputAdminEntity: Ref<RiyoushaAdminInterface> = ref(new RiyoushaAdminEntity());

// // 検索処理変数
// const capsuleDto: Ref<SearchRiyoushaCapsuleInterface> = ref(new SearchRiyoushaCapsuleDto());
// const resultDto: Ref<SearchRiyoushaResultInterface> = ref(new SearchRiyoushaResultDto());

// function onEditComrade(id: number) {
//     alert("編集ボタン押下" + id);
//     viewStatus.value = UserRoleConstants.ROLE_COMRADE;
//     inputComradeEntity.value = resultDto.value.listComrade.filter((e) => id === e.riyoushaComradeId)[0];
//     alert("props" + inputComradeEntity.value.riyoushaComradeId);

// }

// function onEditManager(id: number) {
//     viewStatus.value = UserRoleConstants.ROLE_MANAGER;
    //inputManagerEntity.value = resultDto.value.listManager.filter((e) => id === e.riyoushaManagerId)[0];
// }

// function onEditAdmin(id: number) {
//     viewStatus.value = UserRoleConstants.ROLE_ADMIN;
//     inputAdminEntity.value = resultDto.value.listAdmin.filter((e) => id === e.riyoushaAdminId)[0];
// }

// function onSearch() {
//     getAuthorizedPromiseArea().then(token => {
//         const url = urlBack + "/user-riyousha/search";
//         const method = "POST";
//         const body = JSON.stringify(capsuleDto.value);
//         const headers = {
//             'Accept': 'application/json',
//             'Content-Type': 'application/json',
//             'X-AUTH-TOKEN': 'Bearer ' + token
//         };
//         fetch(url, { method, headers, body })
//             .then(async (response) => {
//                 resultDto.value = await response.json();
//             })
//             .catch((error) => { alert(error); });
//     });
// }

// 新規追加は許可しない
// const isNew: boolean = false;
// ユーザと関連者の紐づけはしない
// const isCombineUser: boolean = false;

// function getOrgLabel(isNotOrg: boolean) {
//     return isNotOrg ? "個人" : "組織";
// }

// Paging(Mock)
// const allCount: Ref<number> = ref(0); // Mock data
// const pageNumber: Ref<number> = ref(0); // Mock data
// const limit: Ref<number> = ref(10); // Mock data



// const inputPersonNameDto: Ref<InputPersonNameDtoInterface> = ref(new InputPersonNameDto());
// inputPersonNameDto.value.allNameKana = "aaa";
// inputPersonNameDto.value.allName = "bbb";

</script>
<template>
    <div class="container">
        <!-- SEページ
        <MockAdminInfo :user-dto="user"></MockAdminInfo>
 -->
        <h1>利用者検索</h1> <br>

        <h3 class="accent-h3">検索条件</h3>

        <div class="one-line">
            <div class="left-area">
                検索対象
            </div>
            <div class="right-area">
                <input type="checkbox">API接続者</input>
                <input type="checkbox">運営者</input>
                <input type="checkbox">管理者</input>
            </div>
        </div>
        <div class="one-line">
            <div class="left-area">
                検索語
            </div>
            <div class="right-area">
                <input type="text" class="max-input"></input><button class="left-space">検索</button>
            </div>
        </div>

        <h3 class="accent-h3">検索結果</h3>

        <h4>API接続者</h4>
        <!-- ページング(API接続者)
        <PagingControl :all-count="allCount" :limit="limit" :page-number="pageNumber"></PagingControl>
         -->
        <table>
            <tbody>
                <tr>
                    <th>個人／組織</th>
                    <th>姓名・名称</th>
                    <th>&nbsp;</th>
                </tr>
                <!-- 
            <tr v-for="entity of resultDto.listComrade" :key="entity.riyoushaComradeId">
                <td>{{ getOrgLabel(entity.isNotOrg) }}</td>
                <td>({{ entity.riyoushaComradeCode }}) <br> {{ entity.riyoushaComradeName }}</td>
                <td><button @click="onEditComrade(entity.riyoushaComradeId)">編集</button></td>
            </tr>
            -->
            </tbody>
        </table>
        <!-- ページング(API接続者)
        <PagingControl :all-count="allCount" :limit="limit" :page-number="pageNumber"></PagingControl>
 -->

        <hr>
        <h4>運営者</h4>
        <!-- ページング(運営者)
        <PagingControl :all-count="allCount" :limit="limit" :page-number="pageNumber"></PagingControl>
 -->
        <table>
            <tbody>
                <tr>
                    <th>個人／組織</th>
                    <th>姓名・名称</th>
                    <th>&nbsp;</th>
                </tr>
                <!-- 
            <tr v-for="entity of resultDto.listManager" :key="entity.riyoushaManagerId">
                <td>{{ getOrgLabel(entity.isNotOrg) }}</td>
                <td>({{ entity.riyoushaManagerCode }}) <br> {{ entity.riyoushaManagerName }}</td>
                <td><button @click="onEditManager(entity.riyoushaManagerId)">編集</button></td>
            </tr>
            -->
            </tbody>
        </table>
        <!-- ページング(運営者)
        <PagingControl :all-count="allCount" :limit="limit" :page-number="pageNumber"></PagingControl>
 -->
        <hr>

        <h4>管理者</h4>
        <!-- ページング(管理者)
        <PagingControl :all-count="allCount" :limit="limit" :page-number="pageNumber"></PagingControl>
         -->
        <table>
            <tbody>
                <tr>
                    <th>個人／組織</th>
                    <th>姓名・名称</th>
                    <th>&nbsp;</th>
                </tr>
                <!-- 
            <tr v-for="entity of resultDto.listAdmin" :key="entity.riyoushaAdminId">
                <td>{{ getOrgLabel(entity.isNotOrg) }}</td>
                <td>({{ entity.riyoushaAdminCode }}) <br> {{ entity.riyoushaAdminName }}</td>
                <td><button @click="onEditAdmin(entity.riyoushaAdminId)">編集</button></td>
            </tr>
            -->
            </tbody>
        </table>
        <!-- ページング(管理者) -->
        <!-- <PagingControl :all-count="allCount" :limit="limit" :page-number="pageNumber"></PagingControl> -->


        <!-- APIユーザ編集
    <div v-if="viewStatus === UserRoleConstants.ROLE_COMRADE">
        <RiyoushaComradeEdit :base-entity="inputComradeEntity" :is-edit-new="isNew" :is-combine-user="isCombineUser"
            :user-dto="userDto"></RiyoushaComradeEdit>
    </div>
 -->

        <!-- 運営者編集
                 <button @click="onEditManager(1)">仮ボタン</button>
        <div v-if="viewStatus === UserRoleConstants.ROLE_MANAGER">
            <RiyoushaManagerEdit :base-entity="inputManagerEntity" :is-edit-new="isNew" :is-combine-user="isCombineUser"
                :user-dto="userDto"></RiyoushaManagerEdit>
        </div>
 -->

        <!-- <ViewInputPersonName :edit-dto="inputPersonNameDto"></ViewInputPersonName> -->
 
        <!--
        <ComponentCatalog></ComponentCatalog>
 -->

        <!-- 管理者編集
    <div v-if="viewStatus === UserRoleConstants.ROLE_ADMIN">
        <RiyoushaAdminEdit :base-entity="inputAdminEntity" :is-edit-new="isNew" :is-combine-user="isCombineUser"
            :user-dto="userDto"></RiyoushaAdminEdit>
    </div>
     -->
        <div class="footer">
            <button class="footer-button">メニューに戻る</button>
        </div>

    </div>
</template>
<style scoped>
/**
table {
    border-style: solid;
    border-width: 1px;
}

table.std {
    border-style: solid;
    border-width: 1px;
    width: calc(200px * 26);
}

td {
    border-style: solid;
    border-width: 1px;
}

th {
    border-style: solid;
    border-width: 1px;
}
 */
</style>
