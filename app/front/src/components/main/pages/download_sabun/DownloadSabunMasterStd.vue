<script setup lang="ts">
import { ref, type Ref } from 'vue';
import MockManagerInfo from '../../../test/common/user_info/MockManagerInfo.vue';
import type { LeastUserDtoInterface } from '../../dto/user/leastUserDto';
import { getLoginUser } from '../../utils/getLoginUser';

// ユーザ呼び出し
const userDto: Ref<LeastUserDtoInterface> = ref(getLoginUser());

const now: Date = new Date();
const startDate: Date = new Date(now.getFullYear(), 1, 1);
const endDate: Date = new Date(now.getFullYear(), 3, 31);

function onCancel() {
    history.back();
}
</script>
<template>
    <!-- 管理者メニュー兼チェック -->
    <MockManagerInfo :user-dto="userDto"></MockManagerInfo>

    <h1>関連者マスタ標準データダウンロード(差分)</h1>

    <div class="one-line">
        {{ startDate.toLocaleDateString() }} から {{ endDate.toLocaleDateString() }}までを収録
    </div>

    <h3>関連者一括ダウンロード</h3>
    <div class="one-line">
        <a href="/dump/sabun_master_std_all.zip" class="left-space">一括ダウンロード(sabun_master_std_all.zip)</a><br>
    </div>

    <h3>関連者個人</h3>
    <div class="one-line">
        <a href="/dump/dump_master_sabun/sabun_master_person_std.csv"
            class="left-space">関連者個人マスタ標準(sabun_master_person_std.csv)</a><br>
    </div>

    <h3>関連者企業／団体</h3>
    <div class="one-line">
        <a href="/dump/dump_master_sabun/sabun_master_person_std.csv"
            class="left-space">関連者企業／団体標準(sabun_master_person_std.csv)</a><br>
    </div>

    <h3>関連者政治団体</h3>
    <div class="one-line">
        <a href="/dump/dump_master_sabun/sabun_master_person_std.csv"
            class="left-space">関連者政治団体標準(sabun_master_person_std.csv)</a><br>
    </div>

    <div class="footer">
        <button @click="onCancel" class="footer-button">キャンセル</button>
    </div>

</template>
<style scoped></style>
