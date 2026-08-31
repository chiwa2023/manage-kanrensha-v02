<script setup lang="ts">
function onCancel() {
    history.back();
}
</script>
<template>
    <!-- サイト利用登録者限定ページではないのでユーザチェックがない -->

    <h1>関連者マスタ最小データダウンロード</h1>

    <h3>関連者一括ダウンロード</h3>
    <div class="one-line">
        <a href="/dump/master_min_all.zip" class="left-space">一括ダウンロード(master_min_all.zip)</a><br>
    </div>

    <h3>関連者個人</h3>
    <div class="one-line">
        <a href="/dump/dump_master/master_person_min.csv" class="left-space">関連者個人マスタ最小(master_person_min.csv)</a><br>
    </div>

    <h3>関連者企業／団体</h3>
    <div class="one-line">
        <a href="/dump/dump_master/master_kigyou_dt_min.csv" class="left-space">関連者企業／団体最小(master_person_min.csv)</a><br>
    </div>

    <h3>関連者政治団体</h3>
    <div class="one-line">
        <a href="/dump/dump_master/master_poli_org_min.csv" class="left-space">関連者政治団体最小(master_person_min.csv)</a><br>
    </div>

    <div class="footer">
        <button @click="onCancel" class="footer-button">キャンセル</button>
    </div>

</template>
<style scoped></style>
