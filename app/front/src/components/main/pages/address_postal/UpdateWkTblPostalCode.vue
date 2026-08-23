<script setup lang="ts">
import { getErrorMessage, getErrorUniqueIdMessage, MessageConstants, MessageView, PagingControl, type FrameworkMessageAndResultDtoInterface, type LeastUserDtoInterface } from 'seijishikin-jp-normalize_common-tool';
import { ref, type Ref } from 'vue';
import { getLoginUser } from '../../utils/getLoginUser';
import ManagerInfo from '../../common/user_info/ManagerInfo.vue';
import { SearchWkTblPostalCodeCapsuleDto, type SearchWkTblPostalCodeCapsuleDtoInterface } from '../../dto/address_postal/searchWkTblPostalCodeCapsuleDto.ts';
import { SearchWkTblPostalCodeResultDto, type SearchWkTblPostalCodeResultDtoInterface } from '../../dto/address_postal/searchWkTblPostalCodeResultDto.ts';
import getAuthorizedPromiseArea from '../../dto/login/getAuthorizedPromiseArea.ts';
import RoutePathConstants from '../../../../routePathConstants.ts';
import { AccessTokenNotFoundError, TokenRefreshError } from '../../dto/login/errors.ts';
import { WkTblPostalEditEntity, type WkTblPostalEditEntityInterface } from '../../entity/wkTblPostalEditEntity.ts';
import { SaveWktblPostalCapsuleDto, type SaveWktblPostalCapsuleDtoInterface } from '../../dto/address_postal/saveWktblPostalCapsuleDto.ts';
import router from '../../../../router.ts';


// よく使う定数
const BLANK: string = "";
const INIT_NUMBER: number = 0;
// const SERVER_STATUS_OK: number = 200;
// const SERVER_STATUS_ERROR: number = 400;
const SEARCH_LIMIT: number = 20;
const INQUIRE_FLG: boolean = false;
const ERR_MESS_ONLY: boolean = true;
const MESS_PAGE_NAME: string = "郵便番号差分処理結果";
const INIT_CALLER: string = "no branch";

//メッセージボックス表示定数
const infoLevel: Ref<number> = ref(MessageConstants.LEVEL_NONE);
const messageType: Ref<number> = ref(MessageConstants.VIEW_NONE);
const caller: Ref<string> = ref(INIT_CALLER);
const message: Ref<string> = ref(BLANK);

// Paging
const pageNumber: Ref<number> = ref(INIT_NUMBER);
const allCount: Ref<number> = ref(INIT_NUMBER);
const limit: Ref<number> = ref(SEARCH_LIMIT);

// back側アクセス
const urlBack: string = RoutePathConstants.DOMAIN + RoutePathConstants.BASE_PATH;

// ユーザ呼び出し
const userDto: Ref<LeastUserDtoInterface> = ref(getLoginUser());

// 編集Dto
const capsuleDto: Ref<SearchWkTblPostalCodeCapsuleDtoInterface> = ref(new SearchWkTblPostalCodeCapsuleDto());
capsuleDto.value.allCount = allCount.value;
capsuleDto.value.limit = limit.value;
capsuleDto.value.pageNumber = pageNumber.value;
capsuleDto.value.userDto = userDto.value;

const resultDto: Ref<SearchWkTblPostalCodeResultDtoInterface> = ref(new SearchWkTblPostalCodeResultDto());

const entityEdit: Ref<WkTblPostalEditEntityInterface> = ref(new WkTblPostalEditEntity());

function onSearch() {
    getAuthorizedPromiseArea().then(token => {
        const url = urlBack + "/postal-wktbl/search";
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
                allCount.value = resultDto.value.allCount;
                limit.value = resultDto.value.limit;
                pageNumber.value = resultDto.value.pageNumber;

                if (resultDto.value.allCount === 0) {
                    infoLevel.value = MessageConstants.LEVEL_WARNING;
                    messageType.value = MessageConstants.VIEW_OK;
                    message.value = "検索結果が0件でした";
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
    history.back();

}

const deleteText: string = "delete";
function onDelete(editId: number) {

    const tempEntity: WkTblPostalEditEntityInterface | undefined =
        resultDto.value.listEntity.filter((e) => editId === e.wkTblPostalEditId)[0];

    if (undefined !== tempEntity) {
        entityEdit.value;
        infoLevel.value = MessageConstants.LEVEL_WARNING;
        messageType.value = MessageConstants.VIEW_YES_NO;
        message.value = "削除すると戻すことができません。よろしいですか？";
        caller.value = deleteText;
    } else {
        infoLevel.value = MessageConstants.LEVEL_ERROR;
        messageType.value = MessageConstants.VIEW_OK;
        message.value = getErrorUniqueIdMessage(editId);
        return;
    }
}

function onEditSelect(editId: number) {
    const tempEntity: WkTblPostalEditEntityInterface | undefined =
        resultDto.value.listEntity.filter((e) => editId === e.wkTblPostalEditId)[0];
    if (undefined !== tempEntity) {
        entityEdit.value = tempEntity;
    } else {
        infoLevel.value = MessageConstants.LEVEL_ERROR;
        messageType.value = MessageConstants.VIEW_OK;
        message.value = getErrorUniqueIdMessage(editId);
        return;
    }
}


function onUpdate(capsuleDto: SaveWktblPostalCapsuleDtoInterface) {

    // 編集された郵便番号差分を保存
    getAuthorizedPromiseArea().then(token => {
        const url = urlBack + "/postal-wktbl/update";
        const method = "POST";
        const body = JSON.stringify(capsuleDto);
        const headers = {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'X-AUTH-TOKEN': 'Bearer ' + token
        };
        fetch(url, { method, headers, body })
            .then(async (response) => {
                const resultDto: FrameworkMessageAndResultDtoInterface = await response.json();
                message.value = resultDto.message;
                if (resultDto.isFailure) {
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


function onSave() {

    const capsuleDto: SaveWktblPostalCapsuleDtoInterface = new SaveWktblPostalCapsuleDto();
    capsuleDto.userDto = userDto.value;
    capsuleDto.editEntity = entityEdit.value;
    capsuleDto.isDelete = false;

    onUpdate(capsuleDto);
}

function recieveSubmit(button: string, callerMethod: string) {

    if (MessageConstants.BUTTON_YES === button && callerMethod === deleteText) {
        const capsuleDto: SaveWktblPostalCapsuleDtoInterface = new SaveWktblPostalCapsuleDto();
        capsuleDto.userDto = userDto.value;
        capsuleDto.editEntity = entityEdit.value;
        capsuleDto.isDelete = true; // 削除の時はフラグを立てる
        onUpdate(capsuleDto);
    }
    // 非表示
    infoLevel.value = 0;
    messageType.value = 0;
    caller.value = INIT_CALLER;
}

function recievePagingNumber(selecteddNumber: number) {
    pageNumber.value = selecteddNumber;
    capsuleDto.value.pageNumber = pageNumber.value;
    onSearch();
}

function convretRireki(isLatest: boolean) {

    if (isLatest) {
        return "最新";
    } else {
        return "履歴";
    }
}
function convretRepair(isLatest: boolean) {

    if (isLatest) {
        return "修復可能";
    } else {
        return "修理不可";
    }
}

//  郵便番号移動ページに遷移
function onMovePostal(entity: WkTblPostalEditEntityInterface) {

    const postalPre = entity.worksText.substring(5, 12);
    const postalPro = entity.worksText.substring(14, 21);

    router.push(RoutePathConstants.PAGE_POSTAL_MOVE
        + "?postalpre=" + postalPre + "&postalpro=" + postalPro + "&lgcode=" + entity.lgCode)
}
</script>
<template>
    <!-- 管理者メニュー兼チェック -->
    <ManagerInfo :user-dto="userDto"></ManagerInfo>

    <h1>郵便番号差分処理編集</h1>

    <h3>検索条件</h3>
    <div class="one-line">
        <div class="left-area">
            履歴データ
        </div>
        <div class="right-area">
            <input type="checkbox" v-model="capsuleDto.isSearchHistory">履歴データを検索する
        </div>
    </div>

    <div class="one-line">
        <div class="left-area">
            修正方法
        </div>
        <div class="right-area">
            <input type="checkbox" v-model="capsuleDto.isSearchRepair">対処方法未定データも検索する
        </div>
    </div>

    <div class="one-line">
        <div class="left-area">
            地名(県・市町村なし)
        </div>
        <div class="right-area">
            <input type="text" v-model="capsuleDto.orgName">
        </div>
    </div>

    <div class="one-line">
        <div class="left-area">
            変更内容
        </div>
        <div class="right-area">
            <input type="text" v-model="capsuleDto.worksText">
        </div>
    </div>

    <div class="one-line">
        <div class="left-area">
            &nbsp;
        </div>
        <div class="right-area">
            <button @click="onSearch">検索</button>
        </div>
    </div>


    <h3>検索結果</h3>
    <table>
        <tbody>
            <tr>
                <th>最新</th>
                <th>修正可能</th>
                <th>地方自治体コード</th>
                <th>郵便番号7桁</th>
                <th>地名</th>
                <th>変更理由</th>
                <th>&nbsp;</th>
                <th>&nbsp;</th>
            </tr>

            <tr v-for="entity in resultDto.listEntity">
                <td>{{ convretRireki(entity.isLatest) }}</td>
                <td>{{ convretRepair(entity.isRepair) }}</td>
                <td>{{ entity.lgCode }}</td>
                <td>{{ entity.postalcode7 }}</td>
                <td>{{ entity.prefName }}{{ entity.cityName }}{{ entity.orgName }}</td>
                <td>
                    {{ entity.worksText }}
                    <span v-if="entity.worksText.startsWith('郵便番号')">
                        <button @click="onMovePostal(entity)">移動</button>
                    </span>
                </td>
                <td><button @click="onEditSelect(entity.wkTblPostalEditId)" :disabled="!entity.isLatest">編集</button>
                </td>
                <td><button @click="onDelete(entity.wkTblPostalEditId)" :disabled="!entity.isLatest">削除</button> </td>
            </tr>
        </tbody>
    </table>

    <!-- ページング  -->
    <PagingControl :all-count="allCount" :limit="limit" :page-number="pageNumber"
        @send-paging-number="recievePagingNumber"></PagingControl>


    <h3>編集</h3>
    <div class="one-line">
        <div class="left-area">
            修正可能
        </div>
        <div class="right-area">
            <input type="checkbox" v-model="entityEdit.isRepair">修正可能とする
        </div>
    </div>


    <div class="one-line">
        <div class="left-area">
            作業内容の修正
        </div>
        <div class="right-area">
            <input type="text" v-model="entityEdit.worksText" class="max-input">
        </div>
    </div>

    <div class="one-line">
        <div class="left-area">
            地方自治体コード
        </div>
        <div class="right-area">
            {{ entityEdit.lgCode }}
        </div>
    </div>

    <div class="one-line">
        <div class="left-area">
            郵便番号7桁
        </div>
        <div class="right-area">
            {{ entityEdit.postalcode7 }}
        </div>
    </div>

    <div class="one-line">
        <div class="left-area">
            地名
        </div>
        <div class="right-area">
            {{ entityEdit.prefName }}{{ entityEdit.cityName }}{{ entityEdit.orgName }}
        </div>
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
