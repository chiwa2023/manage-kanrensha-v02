<script setup lang="ts">
import { ref, toRaw, type Ref } from 'vue';
import { getLoginUser } from '../../utils/getLoginUser';
import { convertDatetimeText, DtoEntityConstants, getErrorMessage, getErrorUniqueIdMessage, InputDatetime, MessageConstants, MessageView, PagingControl, type LeastUserDtoInterface } from 'seijishikin-jp-normalize_common-tool';
import { SearchTimerYoteiResultDto, type SearchTimerYoteiResultDtoInterface } from '../../dto/yoyaku_timer/searchTimerYoteiResultDto';
import { SearchTimerYoteiCapsuleDto, type SearchTimerYoteiCapsuleDtoInterface } from '../../dto/yoyaku_timer/searchTimerYoteiCapsuleDto';
import { type MultiSelectOptionNumberDtoInterface } from '../../dto/select_options/multiSelectOptionsNumberDto';
import createYoteiKbnList from '../../dto/yoyaku_timer/createYoteiKbnList';
import getBooleanText from '../../utils/getBooleanText';
import { TimerYoteiEntity, type TimerYoteiEntityInterface } from '../../entity/timerYoteiEntity';
import AdminInfo from '../../common/user_info/AdminInfo.vue';
import getAuthorizedPromiseArea from '../../dto/login/getAuthorizedPromiseArea';
import RoutePathConstants from '../../../../routePathConstants';
import { AccessTokenNotFoundError, TokenRefreshError } from '../../dto/login/errors';
import EditTimerYotei from '../../common/yotei_timer/EditTimerYotei.vue';

// よく使う定数
const BLANK: string = "";
const INIT_NUMBER: number = 0;
const SEARCH_LIMIT: number = 20;
const INQUIRE_FLG: boolean = false;
const ERR_MESS_ONLY: boolean = true;
const MESS_PAGE_NAME: string = "予約実行検索";
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

// Paging
const pageNumber: Ref<number> = ref(0);
const allCount: Ref<number> = ref(0);
const limit: Ref<number> = ref(SEARCH_LIMIT);

// 日付コンポーネント不正値
const LIMIT_DATE = DtoEntityConstants.INIT_DATETIME_LIMIT;

// 検索条件と結果
const capsuleDto: Ref<SearchTimerYoteiCapsuleDtoInterface> = ref(new SearchTimerYoteiCapsuleDto());
const resultDto: Ref<SearchTimerYoteiResultDtoInterface> = ref(new SearchTimerYoteiResultDto());

// 検索条件と結果
const timerYoyakuEntity: Ref<TimerYoteiEntityInterface> = ref(new TimerYoteiEntity());
const timerYoyakuEntityBackup: Ref<TimerYoteiEntityInterface> = ref(new TimerYoteiEntity());

//予定区分(定数)
const options: Ref<MultiSelectOptionNumberDtoInterface[][]> = ref(createYoteiKbnList());

function recievePagingNumber(selecteddNumber: number) {
    pageNumber.value = selecteddNumber;
    // onSearchでページング複写
    onSearch();
}

function onSearch() {

    // 日時コンポーネントエラー検出
    if (capsuleDto.value.startDateTime <= LIMIT_DATE) {
        infoLevel.value = MessageConstants.LEVEL_WARNING;
        messageType.value = MessageConstants.VIEW_OK;
        message.value = "開始日時入力が不正です。入力しなおしてください";
        return;
    }
    if (capsuleDto.value.endDateTime <= LIMIT_DATE) {
        infoLevel.value = MessageConstants.LEVEL_WARNING;
        messageType.value = MessageConstants.VIEW_OK;
        message.value = "終了日時入力が不正です。入力しなおしてください";
        return;
    }


    // 初期化して選択された予定区分定数のみをリストに複写
    capsuleDto.value.listYoteiKbn.splice(0);
    for (const row of options.value) {
        for (const dto of row) {
            if (dto.isSelected) {
                capsuleDto.value.listYoteiKbn.push(dto.value);
            }
        }
    }

    capsuleDto.value.allCount = allCount.value;
    capsuleDto.value.limit = limit.value;
    capsuleDto.value.pageNumber = pageNumber.value;

    getAuthorizedPromiseArea().then(token => {
        const url = urlBack + "/timer-yotei/search";
        const method = "POST";
        const body = JSON.stringify(capsuleDto.value);
        const headers = {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'X-AUTH-TOKEN': 'Bearer ' + token
        };
        fetch(url, { method, headers, body })
            .then(async (response) => {
                resultDto.value = await response.json();
                if (0 == resultDto.value.listEntity.length) {
                    infoLevel.value = MessageConstants.LEVEL_INFO;
                    messageType.value = MessageConstants.VIEW_TOAST;
                    message.value = "検索結果が0件でした";
                }
                allCount.value = resultDto.value.allCount;
                limit.value = resultDto.value.limit;
                pageNumber.value = resultDto.value.pageNumber;
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

const isYoyakuEdit: Ref<Boolean> = ref(false);
const editYoyakId: Ref<number> = ref(INIT_NUMBER);

function onEdit(editId: number) {

    const tempEntity: TimerYoteiEntityInterface | undefined = resultDto.value.listEntity.filter(
        (e) => editId === e.timerYoteiId)[0];

    if (undefined !== tempEntity) {

        timerYoyakuEntity.value = {
            timerYoteiId: tempEntity.timerYoteiId,
            timerYoteiCode: tempEntity.timerYoteiCode,
            timerYoteiName: tempEntity.timerYoteiName,
            isLatest: tempEntity.isLatest,
            yoyakuTaskKbn: tempEntity.yoyakuTaskKbn,
            nextTimestamp: tempEntity.nextTimestamp ? new Date(tempEntity.nextTimestamp) : new Date(),
            previousTimestamp: tempEntity.previousTimestamp ? new Date(tempEntity.previousTimestamp) : new Date(),
            isRepeat: tempEntity.isRepeat,
            isPause: tempEntity.isPause,
            endTimestamp: tempEntity.endTimestamp ? new Date(tempEntity.endTimestamp) : new Date(),
            sabunTimestamp: tempEntity.sabunTimestamp ? new Date(tempEntity.sabunTimestamp) : new Date(),
            isPeriod: tempEntity.isPeriod,
            yearPeriod: tempEntity.yearPeriod,
            monthPeriod: tempEntity.monthPeriod,
            dayPeriod: tempEntity.dayPeriod,
            hourPeriod: tempEntity.hourPeriod,
            yearPointed: tempEntity.yearPointed,
            monthPointed: tempEntity.monthPointed,
            dayPointed: tempEntity.dayPointed,
            hourPointed: tempEntity.hourPointed,
        };
        editYoyakId.value = editId;
        timerYoyakuEntityBackup.value = structuredClone(toRaw(timerYoyakuEntity.value));
        isYoyakuEdit.value = true;
    } else {
        infoLevel.value = MessageConstants.LEVEL_ERROR;
        messageType.value = MessageConstants.VIEW_OK;
        message.value = getErrorUniqueIdMessage(editYoyakId.value);
        return;
    }
}

function recieveCancelTimerYoyaku() {
    isYoyakuEdit.value = false;
}

function recieveTimerYoyakuInterface(entity: TimerYoteiEntityInterface) {

    let tempEntity: TimerYoteiEntityInterface | undefined = resultDto.value.listEntity.filter(
        (e) => editYoyakId.value === e.timerYoteiId)[0];

    if (undefined === tempEntity) {
        infoLevel.value = MessageConstants.LEVEL_ERROR;
        messageType.value = MessageConstants.VIEW_OK;
        message.value = getErrorUniqueIdMessage(editYoyakId.value);
        return;
    } else {
        tempEntity.previousTimestamp = entity.previousTimestamp;
        tempEntity.nextTimestamp = entity.nextTimestamp;
        tempEntity.endTimestamp = entity.endTimestamp;
        tempEntity.sabunTimestamp = entity.sabunTimestamp;
        tempEntity.isRepeat = entity.isRepeat;
        tempEntity.isPause = entity.isPause;
        tempEntity.isPeriod = entity.isPeriod;
        tempEntity.yearPeriod = entity.yearPeriod;
        tempEntity.monthPeriod = entity.monthPeriod;
        tempEntity.dayPeriod = entity.dayPeriod;
        tempEntity.hourPeriod = entity.hourPeriod;
        tempEntity.yearPointed = entity.yearPointed;
        tempEntity.monthPointed = entity.monthPointed;
        tempEntity.dayPointed = entity.dayPointed;
        tempEntity.hourPointed = entity.hourPointed;
    }
    isYoyakuEdit.value = false;
}

function recieveSubmit() {
    infoLevel.value = 0;
    messageType.value = 0;
}


function recieveDateTime(date: Date, index: number) {
    if (0 == index) {
        capsuleDto.value.startDateTime = date;
    }
    if (1 == index) {
        capsuleDto.value.endDateTime = date;
    }
}
</script>
<template>
    <!-- SE権限 -->
    <AdminInfo :user-dto="userDto"></AdminInfo>

    <h1>予約実行</h1><br>

    <h3 class="accent-h3">検索条件</h3>

    <div class="one-line">
        <div class="left-area">
            予定区分
        </div>
        <div class="right-area">
            <table class="no-boarder">
                <tbody>
                    <tr v-for="(row, rowIndex) in options" :key="rowIndex">
                        <td v-for="(item, colIndex) in row" :key="colIndex" class="no-boarder">
                            <input type="checkbox" v-model="item.isSelected"> {{ item.text }}</input>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
    <div class="one-line">
        <div class="left-area">
            検索期間
        </div>
        <div class="right-area">
            <InputDatetime :datetime="capsuleDto.startDateTime" :index="0" :isEdit="true"
                @send-date-time="recieveDateTime"></InputDatetime>から
            <InputDatetime :datetime="capsuleDto.endDateTime" :index="1" :isEdit="true"
                @send-date-time="recieveDateTime"></InputDatetime>まで
        </div>
    </div>

    <div class="one-line">
        <div class="left-area">
            検索
        </div>
        <div class="right-area">
            <button @click="onSearch">検索</button>
        </div>
    </div>

    <h3 class="accent-h3">検索結果</h3>

    <!-- ページング -->
    <PagingControl :all-count="allCount" :limit="limit" :page-number="pageNumber"
        @send-paging-number="recievePagingNumber"></PagingControl>
    <div class="one-line">
        <table class="left-space">
            <tbody>
                <tr>
                    <th>予約区分</th>
                    <th>前回実行時間</th>
                    <th>次回実行時間</th>
                    <th>繰り返し</th>
                    <th>中断</th>
                    <th>&nbsp;</th>
                </tr>
                <tr v-for="entity of resultDto.listEntity" :key="entity.timerYoteiId">
                    <td>{{ entity.yoyakuTaskKbn }}<br>
                        {{ entity.timerYoteiName }}
                    </td>
                    <td>{{ convertDatetimeText(entity.previousTimestamp) }}</td>
                    <td>{{ convertDatetimeText(entity.nextTimestamp) }}</td>
                    <td>{{ getBooleanText(entity.isRepeat) }}</td>
                    <td>{{ getBooleanText(entity.isPause) }}</td>
                    <td><button @click="onEdit(entity.timerYoteiId)">編集</button></td>
                </tr>
            </tbody>
        </table>
    </div>
    <!-- ページング -->
    <PagingControl :all-count="allCount" :limit="limit" :page-number="pageNumber"
        @send-paging-number="recievePagingNumber"></PagingControl>

    <div class="footer">
        <button class="footer-button">メニューに戻る</button>
    </div>

    <div v-if="isYoyakuEdit" class="overBackground"></div>
    <div class="overComponent" v-if="isYoyakuEdit">
        <EditTimerYotei :user-dto="userDto" :timer-yoyaku-entity="timerYoyakuEntity"
            @send-cancel-timer-yoyaku="recieveCancelTimerYoyaku"
            @send-timer-yoyaku-interface="recieveTimerYoyakuInterface"></EditTimerYotei>
    </div>

    <!-- メッセージ表示    -->
    <div class="overMessage" v-if="messageType !== MessageConstants.VIEW_NONE">
        <MessageView :info-level="infoLevel" :message-type="messageType" :title="MESS_PAGE_NAME" :message="message"
            :caller="caller" @send-submit="recieveSubmit">
        </MessageView>
    </div>

</template>
<style scoped>
td.no-boarder {
    border-style: none;
}
</style>
