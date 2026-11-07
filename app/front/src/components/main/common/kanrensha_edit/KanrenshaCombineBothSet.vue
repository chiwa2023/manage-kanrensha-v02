<script setup lang="ts">
import { onMounted, ref, toRaw, watch, type Ref } from 'vue';
import { AccessTokenNotFoundError, getAuthorizedPromiseArea, getErrorMessage, MessageConstants, MessageView, TokenRefreshError, type LeastUserDtoInterface } from 'seijishikin-jp-normalize_common-tool';
import { WkTblKanrenshaCombineOrgEntity, type WkTblKanrenshaCombineOrgEntityInterface } from '../../entity/wkTblKanrenshaCombineOrgEntity.ts';
import { type YearOptionEntityInterface } from '../../entity/yearOptionEntity.ts';
import KanrenshaKbnConstants from '../../dto/kanrensha/kanrenshaKbnConstants.ts';
import SetYearPeriod from './SetYearPeriod.vue';
import RoutePathConstants from '../../../../routePathConstants.ts';
import type { YearOptionResultDtoInterface } from '../../dto/year_option/yearOptionResultDto.ts';
import { SearchKanrenshaCombineCapsuleDto, type SearchKanrenshaCombineCapsuleDtoInterface } from '../../dto/kanrensha_combine/searchKanrenshaCombineCapsuleDto.ts';
import { UpdateKanrenshaCombineCapsuleDto, type UpdateKanrenshaCombineCapsuleDtoInterface } from '../../dto/kanrensha_combine/updateKanrenshaCombineCapsuleDto.ts';
import createYearArrayText from './createYearArrayText.ts';

//props,emit
const props = defineProps<{ userDto: LeastUserDtoInterface, selectedEntity: WkTblKanrenshaCombineOrgEntityInterface }>();
const emits = defineEmits(["sendBothSetClose"]);

// よく使う定数
const BLANK: string = "";
// const INIT_NUMBER: number = 0;
// const INIT_BOOLEAN: boolean = false;
// const SERVER_STATUS_OK: number = 200;
const SERVER_STATUS_ERROR: number = 400;
// const SEARCH_LIMIT: number = 20;
// メッセージボックス表示定数
const INQUIRE_FLG: boolean = false;
const ERR_MESS_ONLY: boolean = true;
const MESS_PAGE_NAME: string = "関連者紐づけ編集(個人)";
const INIT_CALLER: string = "no branch";

// メッセージボックス表示定数
const infoLevel: Ref<number> = ref(MessageConstants.LEVEL_NONE);
const messageType: Ref<number> = ref(MessageConstants.VIEW_NONE);
const caller: Ref<string> = ref(INIT_CALLER);
const message: Ref<string> = ref(BLANK);

// back側アクセス
const urlBack: string = RoutePathConstants.DOMAIN + RoutePathConstants.BASE_PATH;

// 編集Entity
const entityEdit: Ref<WkTblKanrenshaCombineOrgEntityInterface> = ref(new WkTblKanrenshaCombineOrgEntity());

watch(props.selectedEntity, () => {
    entityEdit.value = props.selectedEntity;

    // 該当する年履歴を取得しに行く
    checkExistYear();
});

onMounted(() => {
    entityEdit.value = props.selectedEntity;

    // 該当する年履歴を取得しに行く
    checkExistYear();
});

entityEdit.value.kanrenshaKbn = KanrenshaKbnConstants.KIGYOU_DT;

// 年選択リスト
const listYearCheck: Ref<YearOptionEntityInterface[]> = ref([]);

// 年指定設定受け取り
let isPeriod: boolean = true;
function recieveStartYear(year: number) {
    entityEdit.value.startYear = year;
    isPeriod = true;
}
function recieveEndYear(year: number) {
    entityEdit.value.endYear = year;
    isPeriod = true;
}
function recieveCheckList(listCheck: YearOptionEntityInterface[]) {
    listYearCheck.value = listCheck;
    isPeriod = false;
}

function recieveSubmit(button: string, callerMethod: string) {
    // 保存時のトーストの場合のみコンポーネントを閉じる
    if (button === MESS_PAGE_NAME && callerMethod === saveCall) {
        emits("sendBothSetClose");
    }
    infoLevel.value = 0;
    messageType.value = 0;
    caller.value = INIT_CALLER;
}

function checkExistYear() {
    if (entityEdit.value.personKanrenshaCode === "" || entityEdit.value.orgKanrenshaCode === "") {
        // 個人と団体双方が揃っていないときは登録記録を取りにいかない
        return;
    }

    const capsuleDtoYear: SearchKanrenshaCombineCapsuleDtoInterface = new SearchKanrenshaCombineCapsuleDto();
    // capsuleDtoYear.tableYear;
    capsuleDtoYear.userDto = props.userDto;
    capsuleDtoYear.personCode = entityEdit.value.personKanrenshaCode;
    capsuleDtoYear.orgCode = entityEdit.value.orgKanrenshaCode;
    capsuleDtoYear.kanrenshaKbn = entityEdit.value.kanrenshaKbn;

    getAuthorizedPromiseArea().then(token => {
        const url = urlBack + "/kanrensha-combine/check-exist";
        const method = "POST";
        const body = JSON.stringify(capsuleDtoYear);
        const headers = {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'X-AUTH-TOKEN': 'Bearer ' + token
        };
        fetch(url, { method, headers, body })
            .then(async (response) => {
                const resultDto: YearOptionResultDtoInterface = await response.json();

                if (response.status > SERVER_STATUS_ERROR) {
                    message.value = getErrorMessage(resultDto.message, ERR_MESS_ONLY);
                    infoLevel.value = MessageConstants.LEVEL_ERROR;
                    messageType.value = MessageConstants.VIEW_OK;
                    return;
                }

                message.value = resultDto.message;
                if (resultDto.isFailure) {
                    infoLevel.value = MessageConstants.LEVEL_WARNING;
                    messageType.value = MessageConstants.VIEW_OK;
                    return;
                } else {
                    listYearCheck.value = resultDto.listEntity;
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
}

function onCancel() {
    emits("sendBothSetClose");
}

const saveCall: string = "保存";
function onSave() {

    const capsuleDtoUpdate: UpdateKanrenshaCombineCapsuleDtoInterface = new UpdateKanrenshaCombineCapsuleDto();
    capsuleDtoUpdate.userDto = props.userDto;
    capsuleDtoUpdate.combineOrgEntity = structuredClone(toRaw(entityEdit.value));
    capsuleDtoUpdate.combineOrgEntity.yearArrayText = createYearArrayText(entityEdit.value, listYearCheck.value, isPeriod);

    getAuthorizedPromiseArea().then(token => {
        const url = urlBack + "/kanrensha-combine/update";
        const method = "POST";
        const body = JSON.stringify(capsuleDtoUpdate);
        const headers = {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'X-AUTH-TOKEN': 'Bearer ' + token
        };
        fetch(url, { method, headers, body })
            .then(async (response) => {
                const resultDto: YearOptionResultDtoInterface = await response.json();

                if (response.status > SERVER_STATUS_ERROR) {
                    message.value = getErrorMessage(resultDto.message, ERR_MESS_ONLY);
                    infoLevel.value = MessageConstants.LEVEL_ERROR;
                    messageType.value = MessageConstants.VIEW_OK;
                    return;
                }

                message.value = resultDto.message;
                if (resultDto.isFailure) {
                    infoLevel.value = MessageConstants.LEVEL_WARNING;
                    messageType.value = MessageConstants.VIEW_OK;
                    return;
                } else {
                    infoLevel.value = MessageConstants.LEVEL_WARNING;
                    messageType.value = MessageConstants.VIEW_TOAST;
                    caller.value = saveCall;
                    return;
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
}

</script>
<template>
    <h3>個人</h3><br>
    <div class="one-line">
        <div class="left-area">
            個人指定
        </div>
        <div class="right-area">
            <input type="text" v-model="entityEdit.personKanrenshaCode" class="code-input" disabled="true">
            <input type="text" v-model="entityEdit.personName" class="name-input left-space" disabled="true">
        </div>
    </div>

    <h3>紐づけ追加</h3>
    <div class="one-line">
        ※すでに同内容のデータが存在する場合は追加しません。
    </div>

    <div class="one-line">
        <div class="left-area">
            紐づけ団体区分
        </div>
        <div class="right-area">
            {{ KanrenshaKbnConstants.getLabel(entityEdit.kanrenshaKbn) }}
        </div>
    </div>

    <div class="one-line">
        <div class="left-area">
            紐づけ団体
        </div>
        <div class="right-area">
            <input type="text" v-model="entityEdit.orgKanrenshaCode" class="code-input" disabled="true">
            <input type="text" v-model="entityEdit.orgName" class="name-input left-space" disabled="true">
        </div>
    </div>

    <!-- 紐づけ年指定 -->
    <SetYearPeriod :list-year="listYearCheck" @send-end-year="recieveEndYear" @send-start-year="recieveStartYear"
        @send-check-list="recieveCheckList">
    </SetYearPeriod>

    <div class="footer">
        <button @click="onCancel" class="footer-button">キャンセル</button>
        <button @click="onSave" class="footer-button left-space">送信</button>
    </div>

    <!-- メッセージ表示 -->
    <div class="overMessage" v-if="messageType !== MessageConstants.VIEW_NONE">
        <MessageView :info-level="infoLevel" :message-type="messageType" :title="MESS_PAGE_NAME" :message="message"
            :caller="caller" @send-submit="recieveSubmit">
        </MessageView>
    </div>

</template>
<style scoped></style>
