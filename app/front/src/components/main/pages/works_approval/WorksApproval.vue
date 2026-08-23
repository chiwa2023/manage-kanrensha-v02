<script setup lang="ts">
import { ref, type Ref } from 'vue';
import { getLoginUser } from '../../utils/getLoginUser';
import { type LeastUserDtoInterface } from 'seijishikin-jp-normalize_common-tool';
import ApprovalPersonAddress from '../../common/works_approval/ApprovalPersonAddress.vue';
import ApprovalPersonShokugyou from '../../common/works_approval/ApprovalPersonShokugyou.vue';
import ApprovalSeijidantaiAddress from '../../common/works_approval/ApprovalSeijidantaiAddress.vue';
import ApprovalKigyouDtAddress from '../../common/works_approval/ApprovalKigyouDtAddress.vue';
import ManagerInfo from '../../common/user_info/ManagerInfo.vue';

// ユーザ呼び出し
const userDto: Ref<LeastUserDtoInterface> = ref(getLoginUser());

const viewStatus: Ref<string> = ref("1");

//初期表示日時
const yesterday: Date = new Date();
yesterday.setDate(yesterday.getDate() - 28);

</script>
<template>
    <!-- 管理者メニュー兼チェック -->
    <ManagerInfo :user-dto="userDto"></ManagerInfo>

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

    <!-- 個人住所承認 -->
    <div v-if="viewStatus == '1'">
        <ApprovalPersonAddress :user-dto="userDto"></ApprovalPersonAddress>
    </div>

    <!-- 企業団体住所承認 -->
    <div v-if="viewStatus == '2'">
        <ApprovalKigyouDtAddress :user-dto="userDto"></ApprovalKigyouDtAddress>
    </div>

    <!--政治団体住所承認 -->
    <div v-if="viewStatus == '3'">
        <ApprovalSeijidantaiAddress :user-dto="userDto"></ApprovalSeijidantaiAddress>
    </div>

    <!-- 個人職業承認 -->
    <div v-if="viewStatus == '4'">
        <ApprovalPersonShokugyou :user-dto="userDto"></ApprovalPersonShokugyou>
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
