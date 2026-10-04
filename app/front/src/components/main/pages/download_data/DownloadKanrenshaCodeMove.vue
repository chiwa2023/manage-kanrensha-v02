<script setup lang="ts">
import { onMounted, ref, type Ref } from 'vue';
import { getLoginUser } from '../../utils/getLoginUser.ts';
import { convertDateText, convertDatetimeText, getErrorMessage, getErrorUniqueIdMessage, MessageConstants, MessageView, type LeastUserDtoInterface } from 'seijishikin-jp-normalize_common-tool';
import ManagerInfo from '../../common/user_info/ManagerInfo.vue';
import getAuthorizedPromiseArea from '../../dto/login/getAuthorizedPromiseArea.ts';
import RoutePathConstants from '../../../../routePathConstants.ts';
import { AccessTokenNotFoundError, TokenRefreshError } from '../../dto/login/errors.ts';
import { GetDumpRecordCapsuleDto, type GetDumpRecordCapsuleDtoInterrface } from '../../dto/z_force_dump/getDumpRecordCapsuleDto.ts';
import { DumpRecordEntity, type DumpRecordEntityInterface } from '../../entity/dumpRecordEntity.ts';
import type { GetDumpRecordResultDtoInterface } from '../../dto/z_force_dump/getDumpRecordResultDto.ts';

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

function onCancel() {
    history.back();
}

const taskCode: number = 320;
const dumpRecordEntity: Ref<DumpRecordEntityInterface> = ref(new DumpRecordEntity());

onMounted(() => {
    const capsuleDto: GetDumpRecordCapsuleDtoInterrface = new GetDumpRecordCapsuleDto();
    capsuleDto.userDto = userDto.value;
    capsuleDto.listTaskCode.push(taskCode); // 

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
                    const tmpEntity: DumpRecordEntityInterface | undefined =
                        resultDto.listEntiy.filter((e) => taskCode === e.taskInfoCode)[0];
                    if (undefined === tmpEntity) {
                        infoLevel.value = MessageConstants.LEVEL_ERROR;
                        messageType.value = MessageConstants.VIEW_OK;
                        message.value = getErrorUniqueIdMessage(taskCode);
                        return;
                    } else {
                        dumpRecordEntity.value = tmpEntity;
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

    <h1>関連者コード移動記録データダウンロード</h1>

    <h3>関連者コード移動</h3>
    <div class="one-line ">
        <div class="left-area">
            ダウンロード
        </div>
        <div class="right-area">
            <a href="/dump/dump_master/code_move.csv">関連者コード移動(code_mive.csv)</a>
        </div>
    </div>
    <div class="one-line ">
        <div class="left-area">
            収録期間
        </div>
        <div class="right-area">
            {{ convertDateText(dumpRecordEntity.endDatetime) }}まで <span class="left-space">({{
                convertDatetimeText(dumpRecordEntity.insertTimestamp) }} 実施)</span>
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
