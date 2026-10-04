<script setup lang="ts">
import { onMounted, ref, type Ref } from 'vue';
import { DumpRecordEntity, type DumpRecordEntityInterface } from '../../entity/dumpRecordEntity';
import { convertDateText, convertDatetimeText, getErrorUniqueIdMessage, MessageConstants, MessageView } from 'seijishikin-jp-normalize_common-tool';
import RoutePathConstants from '../../../../routePathConstants';
import { GetDumpRecordCapsuleDto, type GetDumpRecordCapsuleDtoInterrface } from '../../dto/z_force_dump/getDumpRecordCapsuleDto';
import type { GetDumpRecordResultDtoInterface } from '../../dto/z_force_dump/getDumpRecordResultDto';

const BLANK: string = "";
// const INIT_NUMBER: number = 0;
// const INIT_BOOLEAN: boolean = false;
const SERVER_STATUS_OK: number = 200;
// const INQUIRE_FLG: boolean = false;
// const ERR_MESS_ONLY: boolean = true;
const MESS_PAGE_NAME: string = "関連者コード移動csvダウンロード";
const INIT_CALLER: string = "no branch";

// メッセージボックス表示定数
const infoLevel: Ref<number> = ref(MessageConstants.LEVEL_NONE);
const messageType: Ref<number> = ref(MessageConstants.VIEW_NONE);
const caller: Ref<string> = ref(INIT_CALLER);
const message: Ref<string> = ref(BLANK);

// back側アクセス
const urlBack: string = RoutePathConstants.DOMAIN + RoutePathConstants.BASE_PATH;

const taskPersonCode: number = 324;
const dumpPersonEntity: Ref<DumpRecordEntityInterface> = ref(new DumpRecordEntity());
const taskKigyouCode: number = 325;
const dumpKigyouEntity: Ref<DumpRecordEntityInterface> = ref(new DumpRecordEntity());
const taskSeijidantaiCode: number = 326;
const dumpSeijidantaiEntity: Ref<DumpRecordEntityInterface> = ref(new DumpRecordEntity());

function onCancel() {
    history.back();
}

onMounted(async () => {
    const url = urlBack + "/dump-record/get";

    const capsuleDto: GetDumpRecordCapsuleDtoInterrface = new GetDumpRecordCapsuleDto();
    // capsuleDto.userDto = userDto.value;
    capsuleDto.listTaskCode.push(taskPersonCode); // 個人
    capsuleDto.listTaskCode.push(taskKigyouCode); // 企業団体
    capsuleDto.listTaskCode.push(taskSeijidantaiCode); // 政治団体

    const config = {
        method: "POST",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(capsuleDto)
    };

    const response = await fetch(url, config);
    if (SERVER_STATUS_OK === response.status) {
        const resultDto: GetDumpRecordResultDtoInterface = await response.json();
        message.value = resultDto.message;
        if (resultDto.isFailure) {
            infoLevel.value = MessageConstants.LEVEL_WARNING;
            messageType.value = MessageConstants.VIEW_OK;
            return;
        } else {
            // タスク情報にあった実行記録を抽出する
            const tmpPersonEntity: DumpRecordEntityInterface | undefined =
                resultDto.listEntiy.filter((e) => taskPersonCode === e.taskInfoCode)[0];
            if (undefined === tmpPersonEntity) {
                infoLevel.value = MessageConstants.LEVEL_ERROR;
                messageType.value = MessageConstants.VIEW_OK;
                message.value = getErrorUniqueIdMessage(taskPersonCode);
                return;
            } else {
                dumpPersonEntity.value = tmpPersonEntity;
            }

            const tmpKigyouEntity: DumpRecordEntityInterface | undefined =
                resultDto.listEntiy.filter((e) => taskKigyouCode === e.taskInfoCode)[0];
            if (undefined === tmpKigyouEntity) {
                infoLevel.value = MessageConstants.LEVEL_ERROR;
                messageType.value = MessageConstants.VIEW_OK;
                message.value = getErrorUniqueIdMessage(taskKigyouCode);
                return;
            } else {
                dumpKigyouEntity.value = tmpKigyouEntity;
            }

            const tmpSeijidantaiEntity: DumpRecordEntityInterface | undefined =
                resultDto.listEntiy.filter((e) => taskSeijidantaiCode === e.taskInfoCode)[0];
            if (undefined === tmpSeijidantaiEntity) {
                infoLevel.value = MessageConstants.LEVEL_ERROR;
                messageType.value = MessageConstants.VIEW_OK;
                message.value = getErrorUniqueIdMessage(taskSeijidantaiCode);
                return;
            } else {
                dumpSeijidantaiEntity.value = tmpSeijidantaiEntity;
                return;
            }
        }
    } else {
        infoLevel.value = MessageConstants.LEVEL_ERROR;
        messageType.value = MessageConstants.VIEW_OK;
        return;
    }

});


function recieveSubmit() {
    infoLevel.value = 0;
    messageType.value = 0;
}
</script>
<template>
    <!-- サイト利用登録者限定ページではないのでユーザチェックがない -->

    <h1>関連者マスタ最小データダウンロード(差分)</h1>

    <h3>関連者一括ダウンロード</h3>
    <div class="one-line">
        <a href="/dump/sabun_master_min_all.zip" class="left-space">一括ダウンロード(sabun_master_min_all.zip)</a><br>
    </div>

    <h3>関連者個人</h3>
    <div class="one-line">
        <div class="left-area">
            ダウンロード
        </div>
        <div class="right-area">

            <a
                href="/dump/dump_master_sabun/sabun_master_person_min.csv">関連者個人マスタ最小(sabun_master_person_min.csv)</a><br>
        </div>
    </div>
    <div class="one-line ">
        <div class="left-area">
            収録期間
        </div>
        <div class="right-area">
            {{ convertDateText(dumpPersonEntity.startDatetime) }}から
            {{ convertDateText(dumpPersonEntity.endDatetime) }}まで <span class="left-space">({{
                convertDatetimeText(dumpPersonEntity.insertTimestamp) }} 実施)</span>
        </div>
    </div>

    <h3>関連者企業／団体</h3>
    <div class="one-line">
        <div class="left-area">
            ダウンロード
        </div>
        <div class="right-area">

            <a
                href="/dump/dump_master_sabun/sabun_master_person_min.csv">関連者企業／団体最小(sabun_master_person_min.csv)</a><br>
        </div>
    </div>
    <div class="one-line ">
        <div class="left-area">
            収録期間
        </div>
        <div class="right-area">
            {{ convertDateText(dumpKigyouEntity.startDatetime) }}から
            {{ convertDateText(dumpKigyouEntity.endDatetime) }}まで <span class="left-space">({{
                convertDatetimeText(dumpKigyouEntity.insertTimestamp) }} 実施)</span>
        </div>
    </div>

    <h3>関連者政治団体</h3>
    <div class="one-line">
        <div class="left-area">
            ダウンロード
        </div>
        <div class="right-area">

            <a href="/dump/dump_master_sabun/sabun_master_person_min.csv">関連者政治団体最小(sabun_master_person_min.csv)</a><br>
        </div>
    </div>
    <div class="one-line ">
        <div class="left-area">
            収録期間
        </div>
        <div class="right-area">
            {{ convertDateText(dumpSeijidantaiEntity.startDatetime) }}から
            {{ convertDateText(dumpSeijidantaiEntity.endDatetime) }}まで <span class="left-space">({{
                convertDatetimeText(dumpSeijidantaiEntity.insertTimestamp) }} 実施)</span>
        </div>
    </div>

    <div class="footer">
        <button @click="onCancel" class="footer-button">キャンセル</button>
    </div>

    <!-- メッセージ表示 -->
    <div class="overMessage" v-if="messageType !== MessageConstants.VIEW_NONE">
        <MessageView :info-level="infoLevel" :message-type="messageType" :title="MESS_PAGE_NAME" :message="message"
            :caller="caller" @send-submit="recieveSubmit">
        </MessageView>
    </div>

</template>
<style scoped></style>
