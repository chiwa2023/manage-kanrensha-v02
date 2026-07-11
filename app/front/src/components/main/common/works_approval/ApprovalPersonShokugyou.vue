<script setup lang="ts">
import { ref, type Ref } from 'vue';
import type { KanrenshaPersonPropertyEntityInterface } from '../../entity/kanrenshaPersonPropertyEntity';
import { DtoEntityConstants, getErrorMessage, getErrorUniqueIdMessage, InputCompareShokugyou, InputDate, InputShokugyouDto, MessageConstants, MessageView, PagingControl, type FrameworkMessageAndResultDtoInterface, type InputShokugyouDtoInterface, type LeastUserDtoInterface } from 'seijishikin-jp-normalize_common-tool';
import getAuthorizedPromiseArea from '../../dto/login/getAuthorizedPromiseArea';
import RoutePathConstants from '../../../../routePathConstants';
import { AccessTokenNotFoundError, TokenRefreshError } from '../../dto/login/errors';
import { SearchWorksApprovalCapsuleDto, type SearchWorksApprovalCapsuleDtoInterfce } from '../../dto/works_approval/searchWorksApprovalCapsuleDto';
import { SearchApprovalShokugyouResultDto, type SearchApprovalShokugyouResultDtoInterface } from '../../dto/works_approval/searchApprovalShokugyouResultDto';
import { SaveWorksApprovalCapsuleDto, type SaveWorksApprovalCapsuleDtoInterface } from '../../dto/works_approval/saveWorksApprovalCapsuleDto';

// よく使う定数
const BLANK: string = "";
const INIT_NUMBER: number = 0;
const INIT_BOOLEAN: boolean = false;
const SERVER_STATUS_OK: number = 200;
// const SERVER_STATUS_ERROR: number = 400;
const SEARCH_LIMIT: number = 20;
const INQUIRE_FLG: boolean = false;
const ERR_MESS_ONLY: boolean = true;
const MESS_PAGE_NAME: string = "個人職業作業承認";
const INIT_CALLER: string = "no branch";


// Paging
const pageNumber: Ref<number> = ref(INIT_NUMBER);
const allCount: Ref<number> = ref(INIT_NUMBER);
const limit: Ref<number> = ref(SEARCH_LIMIT);
// メッセージボックス表示定数
const infoLevel: Ref<number> = ref(MessageConstants.LEVEL_NONE);
const messageType: Ref<number> = ref(MessageConstants.VIEW_NONE);
const caller: Ref<string> = ref(INIT_CALLER);
const message: Ref<string> = ref(BLANK);

//props,emit
const props = defineProps<{ userDto: LeastUserDtoInterface }>()

// back側アクセス
const urlBack: string = RoutePathConstants.DOMAIN + RoutePathConstants.BASE_PATH;

// 法人番号APIキー
const houjinAppId: string = import.meta.env.VITE_HOUJIN_NO_API;

// 日付コンポーネント不正値
const LIMIT_DATE = DtoEntityConstants.INIT_DATETIME_LIMIT;

const capsuleDto: Ref<SearchWorksApprovalCapsuleDtoInterfce> = ref(new SearchWorksApprovalCapsuleDto());
const resultDto: Ref<SearchApprovalShokugyouResultDtoInterface> = ref(new SearchApprovalShokugyouResultDto());

function onSearch() {

    // 日時コンポーネントエラー検出
    if (capsuleDto.value.startDate <= LIMIT_DATE) {
        infoLevel.value = MessageConstants.LEVEL_WARNING;
        messageType.value = MessageConstants.VIEW_OK;
        message.value = "開始日時入力が不正です。入力しなおしてください";
        return;
    }
    if (capsuleDto.value.endDate <= LIMIT_DATE) {
        infoLevel.value = MessageConstants.LEVEL_WARNING;
        messageType.value = MessageConstants.VIEW_OK;
        message.value = "終了日時入力が不正です。入力しなおしてください";
        return;
    }


    capsuleDto.value.limit = limit.value;
    capsuleDto.value.allCount = allCount.value;
    capsuleDto.value.pageNumber = pageNumber.value;
    getAuthorizedPromiseArea().then(token => {
        const url = urlBack + "/works-approval/search-shokugyou";
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
                if (SERVER_STATUS_OK === response.status) {
                    //ページング
                    allCount.value = resultDto.value.allCount;
                    pageNumber.value = resultDto.value.pageNumber;
                    limit.value = resultDto.value.limit;
                } else {
                    infoLevel.value = MessageConstants.LEVEL_WARNING;
                    messageType.value = MessageConstants.VIEW_OK;
                    message.value = "検索結果を取得できませんでした";
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

const inputShokugyouDto: Ref<InputShokugyouDtoInterface> = ref(new InputShokugyouDto());
const isShokugyouInput: Ref<boolean> = ref(INIT_BOOLEAN);
const storedId: Ref<number> = ref(INIT_NUMBER);
function onShokugyouInput(selectedId: number) {

    const tempEntity: KanrenshaPersonPropertyEntityInterface | undefined
        = resultDto.value.listShokugyou.filter((e) => selectedId === e.kanrenshaPersonPropertyId)[0];

    if (tempEntity !== undefined) {
        storedId.value = selectedId;
        // dtoとentityで型が異なるで1件ずつつなぎ合わせる
        inputShokugyouDto.value.gyoushu = tempEntity.gyoushu;
        inputShokugyouDto.value.yakushoku = tempEntity.yakushoku;
        inputShokugyouDto.value.shokugyouUserWrite = tempEntity.shokugyouUserWrite;
        inputShokugyouDto.value.houjinNo = tempEntity.kigyouDtNo;
        inputShokugyouDto.value.houjinName = tempEntity.kigyouDtName;
        inputShokugyouDto.value.houjinAddress = tempEntity.kigyouDtAddress;

        isShokugyouInput.value = true;
    } else {
        infoLevel.value = MessageConstants.LEVEL_ERROR;
        messageType.value = MessageConstants.VIEW_OK;
        message.value = getErrorUniqueIdMessage(selectedId);
        return;
    }

}

function recieveCancelInputShokugyou() {
    isShokugyouInput.value = false;
}

function recieveInputShokugyouInterface(dataDto: InputShokugyouDtoInterface) {

    const tempEntity: KanrenshaPersonPropertyEntityInterface | undefined
        = resultDto.value.listShokugyou.filter((e) => storedId.value === e.kanrenshaPersonPropertyId)[0];

    // dtoとentityで型が異なるで1件ずつつなぎ合わせる
    if (tempEntity !== undefined) {
        //tempEntity.allShokugyou = dataDto.allShokugyou;
        tempEntity.gyoushu = dataDto.gyoushu;
        tempEntity.yakushoku = dataDto.yakushoku;
        tempEntity.shokugyouUserWrite = dataDto.shokugyouUserWrite;
        tempEntity.kigyouDtNo = dataDto.houjinNo;
        tempEntity.kigyouDtName = dataDto.houjinName;
        tempEntity.kigyouDtAddress = dataDto.houjinAddress;
        tempEntity.isShokyouEdit = (BLANK !== dataDto.shokugyouUserWrite);
    } else {
        infoLevel.value = MessageConstants.LEVEL_ERROR;
        messageType.value = MessageConstants.VIEW_OK;
        message.value = getErrorUniqueIdMessage(storedId.value);
        return;
    }

    isShokugyouInput.value = false;
}

function onCancel() {
    history.back();

}
function onSave() {

    const capsuleDtoSave: SaveWorksApprovalCapsuleDtoInterface = new SaveWorksApprovalCapsuleDto();
    capsuleDtoSave.userDto = props.userDto;
    capsuleDtoSave.listShokugyou = resultDto.value.listShokugyou;

    getAuthorizedPromiseArea().then(token => {
        const url = urlBack + "/works-approval/save-shokugyou";
        const method = "POST";
        const body = JSON.stringify(capsuleDtoSave);
        const headers = {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'X-AUTH-TOKEN': 'Bearer ' + token
        };
        fetch(url, { method, headers, body })
            .then(async (response) => {

                const resultDtoSave: FrameworkMessageAndResultDtoInterface = await response.json();
                message.value = resultDtoSave.message;
                if (resultDtoSave.isFailure) {
                    infoLevel.value = MessageConstants.LEVEL_WARNING;
                    messageType.value = MessageConstants.VIEW_OK;
                    return;
                } else {
                    infoLevel.value = MessageConstants.LEVEL_INFO;
                    messageType.value = MessageConstants.VIEW_TOAST;
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

function recieveSubmit() {
    infoLevel.value = 0;
    messageType.value = 0;
}

// コンポーネントから時刻受け取り
function recieveDate(date: Date, index: number) {
    if (0 == index) {
        capsuleDto.value.startDate = date;
    }
    if (1 == index) {
        capsuleDto.value.endDate = date;
    }
}

function recievePagingNumber(selecteddNumber: number) {
    pageNumber.value = selecteddNumber;
    // onSearchでページング複写
    onSearch();
}
</script>
<template>
    <h1>個人職業</h1>

    <div class="one-line">
        <div class="left-area">
            検索期間
        </div>
        <div class="right-area">
            <span>
                <InputDate :date="capsuleDto.startDate" :index="0" :is-edit="true" @send-date="recieveDate"></InputDate>
                から
            </span>
            <span class="left-space">
                <InputDate :date="capsuleDto.endDate" :index="1" :is-edit="true" @send-date="recieveDate"></InputDate>まで
            </span>
        </div>
    </div>
    <div class="one-line">
        <div class="left-area">
            承認必要のみ
        </div>
        <div class="right-area">
            <input type="checkbox" v-model="capsuleDto.isExcludeFinishedTask">承認必要作業のみ表示する
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


    <h3>検索結果(職業)</h3>
    <div class="one-line-scroll">
        <table>
            <tbody>
                <tr>
                    <th>関連者番号</th>
                    <th>名前</th>
                    <th>業種</th>
                    <th>役職</th>
                    <th>自己申告職業</th>
                    <th>法人番号</th>
                    <th>法人名称</th>
                    <th>法人住所</th>
                    <th>編集</th>
                    <th>承認</th>
                    <th>&nbsp;</th>
                </tr>
            </tbody>
            <tbody v-for="entity in resultDto.listShokugyou" :key="entity.kanrenshaPersonPropertyId">
                <tr>
                    <td>{{ entity.personKanrenshaCode }} </td>
                    <td>{{ entity.kanrenshaName }} </td>
                    <td>{{ entity.gyoushu }} </td>
                    <td>{{ entity.yakushoku }} </td>
                    <td>{{ entity.shokugyouUserWrite }} </td>
                    <td>{{ entity.kigyouDtNo }} </td>
                    <td>{{ entity.kigyouDtName }} </td>
                    <td>{{ entity.kigyouDtAddress }} </td>
                    <td><input type="checkbox" v-model="entity.isShokyouEdit" disabled="true">編集あり</td>
                    <td><input type="checkbox" v-model="entity.isShokyouAccept">登録内容を承認</td>
                    <td><button @click="onShokugyouInput(entity.kanrenshaPersonPropertyId)">編集</button></td>
                </tr>
            </tbody>
        </table>
    </div>
    <!-- ページング -->
    <PagingControl :all-count="allCount" :limit="limit" :page-number="pageNumber"
        @send-paging-number="recievePagingNumber"></PagingControl>

    <!-- 職業入力(紐づけなし) -->
    <div v-if="isShokugyouInput" class="overBackground"></div>
    <div v-if="isShokugyouInput" class="overComponent">
        <InputCompareShokugyou :edit-dto="inputShokugyouDto" :isfooter="true" :houjin-api-key="houjinAppId"
            @send-cancel-input-shokugyou="recieveCancelInputShokugyou"
            @send-input-shokugyou-interface="recieveInputShokugyouInterface">
        </InputCompareShokugyou>
    </div>

    <div class="footer">
        <button @click="onCancel" class="footer-button">キャンセル</button>
        <button @click="onSave" class="footer-button left-space">送信</button>
    </div>

    <!-- メッセージ表示    -->
    <div class="overMessage" v-if="messageType !== MessageConstants.VIEW_NONE">
        <MessageView :info-level="infoLevel" :message-type="messageType" :title="MESS_PAGE_NAME" :message="message"
            :caller="caller" @send-submit="recieveSubmit">
        </MessageView>
    </div>

</template>
<style scoped></style>
