<script setup lang="ts">
const now: Date = new Date();
// TODO 差分作成日時を取得する
const startDate: Date = new Date(now.getFullYear(), 1, 1);
const endDate: Date = new Date(now.getFullYear(), 3, 31);

function onCancel() {
    history.back();
}
</script>
<template>
    <!-- サイト利用登録者限定ページではないのでユーザチェックがない -->

    <h1>関連者マスタ最小データダウンロード(差分)</h1>

    <div class="one-line">
        {{ startDate.toLocaleDateString() }} から {{ endDate.toLocaleDateString() }}までを収録
    </div>

    <h3>関連者一括ダウンロード</h3>
    <div class="one-line">
        <a href="/dump/sabun_master_min_all.zip" class="left-space">一括ダウンロード(sabun_master_min_all.zip)</a><br>
    </div>

    <h3>関連者個人</h3>
    <div class="one-line">
        <a href="/dump/dump_master_sabun/sabun_master_person_min.csv"
            class="left-space">関連者個人マスタ最小(sabun_master_person_min.csv)</a><br>
    </div>

    <h3>関連者企業／団体</h3>
    <div class="one-line">
        <a href="/dump/dump_master_sabun/sabun_master_person_min.csv"
            class="left-space">関連者企業／団体最小(sabun_master_person_min.csv)</a><br>
    </div>

    <h3>関連者政治団体</h3>
    <div class="one-line">
        <a href="/dump/dump_master_sabun/sabun_master_person_min.csv"
            class="left-space">関連者政治団体最小(sabun_master_person_min.csv)</a><br>
    </div>

    <div class="footer">
        <button @click="onCancel" class="footer-button">キャンセル</button>
    </div>

</template>
<style scoped></style>
