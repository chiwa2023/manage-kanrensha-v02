<script setup lang="ts">
import { ref, type Ref } from 'vue';
import MockManagerInfo from '../../../test/common/user_info/MockManagerInfo.vue';
import type { LeastUserDtoInterface } from '../../dto/user/leastUserDto';
import { getLoginUser } from '../../utils/getLoginUser';

// ユーザ呼び出し
const userDto: Ref<LeastUserDtoInterface> = ref(getLoginUser());

function onCancel() {
    history.back();
}
</script>
<template>

    <!-- 管理者メニュー兼チェック -->
    <MockManagerInfo :user-dto="userDto"></MockManagerInfo>

    <h1>関連者マスタ標準データダウンロード</h1>

    <h3>関連者一括ダウンロード</h3>
    <div class="one-line">
        <a href="/dump/master_std_all.zip" class="left-space">一括ダウンロード(master_std_all.zip)</a><br>
    </div>

    <h3>関連者個人</h3>
    <div class="one-line">
        <a href="/dump/dump_master/master_person_std.csv" class="left-space">関連者個人マスタ標準(master_person_std.csv)</a><br>
    </div>

    <h3>関連者企業／団体</h3>
    <div class="one-line">
        <a href="/dump/dump_master/master_kigyou_dt_std.csv" class="left-space">関連者企業／団体標準(master_person_std.csv)</a><br>
    </div>

    <h3>関連者政治団体</h3>
    <div class="one-line">
        <a href="/dump/dump_master/master_poli_org_std.csv" class="left-space">関連者政治団体標準(master_person_std.csv)</a><br>
    </div>

    <div class="footer">
        <button @click="onCancel" class="footer-button">キャンセル</button>
    </div>

</template>
<style scoped></style>
