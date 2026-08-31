<script setup lang="ts">
import { ref, type Ref } from 'vue';
import ChangeWkTblAddress from '../../common/address_registory/ChangeWkTblAddress.vue';
import DeleteWkTblAddress from '../../common/address_registory/DeleteWkTblAddress.vue';
import ManagerInfo from '../../common/user_info/ManagerInfo.vue';
import type { LeastUserDtoInterface } from 'seijishikin-jp-normalize_common-tool';
import { getLoginUser } from '../../utils/getLoginUser.ts';


// よく使う定数
// const BLANK: string = "";
// const INIT_NUMBER: number = 0;
// const SERVER_STATUS_OK: number = 200;
// const SERVER_STATUS_ERROR: number = 400;
// const SEARCH_LIMIT: number = 20;
// メッセージボックス表示定数
//const infoLevel: Ref<number> = ref(MessageConstants.LEVEL_NONE);
//const messageType: Ref<number> = ref(MessageConstants.VIEW_NONE);
//const title: Ref<string> = ref(BLANK);
//const message: Ref<string> = ref(BLANK);

// ユーザ呼び出し
const userDto: Ref<LeastUserDtoInterface> = ref(getLoginUser());

// 表示内容制御
const editState: Ref<string> = ref("1");

</script>
<template>

    <!-- 管理者メニュー兼チェック -->
    <ManagerInfo :user-dto="userDto"></ManagerInfo>

    <h1>アドレス・ベース・レジストリ差分検索・編集</h1>

    <div class="one-line">
        <div class="left-area">
            編集対象(更新/削除)
        </div>
        <div class="right-area">
            <span><input type="radio" v-model="editState" value="1">更新</span>
            <span class="left-space"><input type="radio" v-model="editState" value="2">削除</span>
        </div>
    </div>

    <div v-if="'1' === editState">
        <!-- 更新コンポーネント -->
        <ChangeWkTblAddress :user-dto="userDto"></ChangeWkTblAddress>
    </div>

    <div v-if="'2' === editState">
        <!-- 削除コンポーネント -->
        <DeleteWkTblAddress :user-dto="userDto"></DeleteWkTblAddress>
    </div>
</template>
<style scoped></style>
