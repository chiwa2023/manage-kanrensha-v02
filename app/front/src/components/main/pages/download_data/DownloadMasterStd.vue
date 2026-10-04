<script setup lang="ts">
import { onMounted, ref, type Ref } from 'vue';
import { getLoginUser } from '../../utils/getLoginUser';
import { convertDateText, convertDatetimeText, getErrorMessage, getErrorUniqueIdMessage, MessageConstants, MessageView, type LeastUserDtoInterface } from 'seijishikin-jp-normalize_common-tool';
import ManagerInfo from '../../common/user_info/ManagerInfo.vue';
import { DumpRecordEntity, type DumpRecordEntityInterface } from '../../entity/dumpRecordEntity.ts';
import RoutePathConstants from '../../../../routePathConstants.ts';
import { GetDumpRecordCapsuleDto, type GetDumpRecordCapsuleDtoInterrface } from '../../dto/z_force_dump/getDumpRecordCapsuleDto.ts';
import getAuthorizedPromiseArea from '../../dto/login/getAuthorizedPromiseArea.ts';
import type { GetDumpRecordResultDtoInterface } from '../../dto/z_force_dump/getDumpRecordResultDto.ts';
import { AccessTokenNotFoundError, TokenRefreshError } from '../../dto/login/errors.ts';

const BLANK: string = "";
// const INIT_NUMBER: number = 0;
// const INIT_BOOLEAN: boolean = false;
const INQUIRE_FLG: boolean = false;
const ERR_MESS_ONLY: boolean = true;
const MESS_PAGE_NAME: string = "関連者コード移動csvダウンロード";
const INIT_CALLER: string = "no branch";

// メッセージボックス表示定数
const infoLevel: Ref<number> = ref(MessageConstants.LEVEL_NONE);
const messageType: Ref<number> = ref(MessageConstants.VIEW_NONE);
const caller: Ref<string> = ref(INIT_CALLER);
const message: Ref<string> = ref(BLANK);


// back側アクセス
const urlBack: string = RoutePathConstants.DOMAIN + RoutePathConstants.BASE_PATH;

// ユーザ呼び出し
const userDto: Ref<LeastUserDtoInterface> = ref(getLoginUser());

const taskPersonCode: number = 311;
const dumpPersonEntity: Ref<DumpRecordEntityInterface> = ref(new DumpRecordEntity());
const taskKigyouCode: number = 312;
const dumpKigyouEntity: Ref<DumpRecordEntityInterface> = ref(new DumpRecordEntity());
const taskSeijidantaiCode: number = 313;
const dumpSeijidantaiEntity: Ref<DumpRecordEntityInterface> = ref(new DumpRecordEntity());

function onCancel() {
    history.back();
}


onMounted(() => {
    const capsuleDto: GetDumpRecordCapsuleDtoInterrface = new GetDumpRecordCapsuleDto();
    capsuleDto.userDto = userDto.value;
    capsuleDto.listTaskCode.push(taskPersonCode); // 個人
    capsuleDto.listTaskCode.push(taskKigyouCode); // 企業団体
    capsuleDto.listTaskCode.push(taskSeijidantaiCode); // 政治団体

    getAuthorizedPromiseArea().then(token => {
        const url = urlBack + "/dump-record/get";
        const method = "POST";
        const body = JSON.stringify(capsuleDto);
        const headers = {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'X-AUTH-TOKEN': 'Bearer ' + token
        };
        fetch(url, { method, headers, body })
            .then(async (response) => {

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
            })
            .catch((error) => {
                message.value = getErrorMessage(error, ERR_MESS_ONLY);
                infoLevel.value = MessageConstants.LEVEL_ERROR;
                messageType.value = MessageConstants.VIEW_OK;
                return;
            });
    }).catch((e) => {
        infoLevel.value = MessageConstants.LEVEL_ERROR;
        messageType.value = MessageConstants.VIEW_OK;

        // トークン保持または取得に失敗している場合
        if (e instanceof AccessTokenNotFoundError || e instanceof TokenRefreshError) {
            message.value = e.message;
            return;
        }

        message.value = getErrorMessage(e, INQUIRE_FLG);
        return;
    });


});


function recieveSubmit() {
    infoLevel.value = 0;
    messageType.value = 0;
}
</script>
<template>
    <!-- 管理者メニュー兼チェック -->
    <ManagerInfo :user-dto="userDto"></ManagerInfo>

    <h1>関連者マスタ標準データダウンロード</h1>

    <h3>関連者一括ダウンロード</h3>
    <div class="one-line">
        <a href="/dump/master_std_all.zip" class="left-space">一括ダウンロード(master_std_all.zip)</a><br>
    </div>

    <h3>関連者個人</h3>
    <div class="one-line ">
        <div class="left-area">
            ダウンロード
        </div>
        <div class="right-area">
            <a href="/dump/dump_master/master_person_std.csv">関連者個人マスタ標準(master_person_std.csv)</a>
        </div>
    </div>
    <div class="one-line ">
        <div class="left-area">
            収録期間
        </div>
        <div class="right-area">
            {{ convertDateText(dumpPersonEntity.endDatetime) }}まで <span class="left-space">({{
                convertDatetimeText(dumpPersonEntity.insertTimestamp) }} 実施)</span>
        </div>
    </div>

    <h3>関連者企業／団体</h3>
    <div class="one-line ">
        <div class="left-area">
            ダウンロード
        </div>
        <div class="right-area">
            <a href="/dump/dump_master/master_kigyou_dt_std.csv">関連者企業／団体標準(master_person_std.csv)</a><br>
        </div>
    </div>
    <div class="one-line ">
        <div class="left-area">
            収録期間
        </div>
        <div class="right-area">
            {{ convertDateText(dumpKigyouEntity.endDatetime) }}まで <span class="left-space">({{
                convertDatetimeText(dumpKigyouEntity.insertTimestamp) }} 実施)</span>
        </div>
    </div>



    <h3>関連者政治団体</h3>
    <div class="one-line ">
        <div class="left-area">
            ダウンロード
        </div>
        <div class="right-area">
            <a href="/dump/dump_master/master_poli_org_std.csv">関連者政治団体標準(master_person_std.csv)</a><br>
        </div>

        <div class="one-line ">
            <div class="left-area">
                収録期間
            </div>
            <div class="right-area">
                {{ convertDateText(dumpSeijidantaiEntity.endDatetime) }}まで <span class="left-space">({{
                    convertDatetimeText(dumpSeijidantaiEntity.insertTimestamp) }} 実施)</span>
            </div>
        </div>

        <!-- メッセージ表示 -->
        <div class="overMessage" v-if="messageType !== MessageConstants.VIEW_NONE">
            <MessageView :info-level="infoLevel" :message-type="messageType" :title="MESS_PAGE_NAME" :message="message"
                :caller="caller" @send-submit="recieveSubmit">
            </MessageView>
        </div>


    </div>
    <div class="footer">
        <button @click="onCancel" class="footer-button">キャンセル</button>
    </div>

</template>
<style scoped></style>
