<script setup lang="ts">
import { ref, type Ref } from 'vue';
import { SearchAddressCityDeleteResultDto, type SearchAddressCityDeleteResultDtoInterface } from '../../dto/address_registory/searchAddressCityDeleteResultDto';
import { FrameworkPagingDto, InputLgcode, MessageConstants, MessageView, PagingControl, type FrameworkMessageAndResultDtoInterface, type FrameworkPagingDtoInterface, type LeastUserDtoInterface } from 'seijishikin-jp-normalize_common-tool';
import { AddressCityDeleteEntity, type AddressCityDeleteEntityInterface } from '../../entity/addressCityDeleteEntity';
import { EditAddressCityDeleteCapsuleDto, type EditAddressCityDeleteCapsuleDtoInterface } from '../../dto/address_registory/editAddressCityDeleteCapsuleDto';
import RoutePathConstants from '../../../../routePathConstants';
import { getLoginUser } from '../../utils/getLoginUser';
import getAuthorizedPromiseArea from '../../dto/login/getAuthorizedPromiseArea';
import { AccessTokenNotFoundError, TokenRefreshError } from '../../dto/login/errors';
import ManagerInfo from '../../common/user_info/ManagerInfo.vue';


// よく使う定数
const BLANK: string = "";
const INIT_NUMBER: number = 0;
// const SERVER_STATUS_OK: number = 200;
// const SERVER_STATUS_ERROR: number = 400;
const SEARCH_LIMIT: number = 20;
// メッセージボックス表示定数
const infoLevel: Ref<number> = ref(MessageConstants.LEVEL_NONE);
const messageType: Ref<number> = ref(MessageConstants.VIEW_NONE);
const title: Ref<string> = ref(BLANK);
const message: Ref<string> = ref(BLANK);

// Paging
const pageNumber: Ref<number> = ref(INIT_NUMBER);
const allCount: Ref<number> = ref(INIT_NUMBER);
const limit: Ref<number> = ref(SEARCH_LIMIT);

// back側アクセス
const urlBack: string = RoutePathConstants.DOMAIN + RoutePathConstants.BASE_PATH;

// ユーザ呼び出し
const userDto: Ref<LeastUserDtoInterface> = ref(getLoginUser());

// 編集用Dto
const resultDto: Ref<SearchAddressCityDeleteResultDtoInterface> = ref(new SearchAddressCityDeleteResultDto());
const editEntity: Ref<AddressCityDeleteEntityInterface> = ref(new AddressCityDeleteEntity());
const editCapsuleDto: Ref<EditAddressCityDeleteCapsuleDtoInterface> = ref(new EditAddressCityDeleteCapsuleDto());
editCapsuleDto.value.userDto = userDto.value;

function onSearch() {
    const capsuleDto: FrameworkPagingDtoInterface = new FrameworkPagingDto();
    capsuleDto.allCount = allCount.value;
    capsuleDto.limit = limit.value;
    capsuleDto.pageNumber = pageNumber.value;

    getAuthorizedPromiseArea().then(token => {
        const url = urlBack + "/lgcode-delete/search";
        const method = "POST";
        const body = JSON.stringify(capsuleDto);
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
                alert(error);
                infoLevel.value = MessageConstants.LEVEL_ERROR;
                messageType.value = MessageConstants.VIEW_OK;
                message.value = "システム管理者にお問い合わせください";
                return;
            });
    }).catch((e) => {
        infoLevel.value = MessageConstants.LEVEL_ERROR;
        messageType.value = MessageConstants.VIEW_OK;

        if (e instanceof AccessTokenNotFoundError) {
            // トークン保持ができていない場合
            title.value = "現在トークンが存在しません";
            message.value = e.message;
            return;
        }
        if (e instanceof TokenRefreshError) {
            // 取得に失敗している場合
            title.value = "有効期限まじかのトークンを再取得できませんでした";
            message.value = e.message;
            return;
        }
        title.value = "システムエラーが発生しました";
        message.value = "システム管理者にお問い合わせください";
        return;
    });
}

function recievePagingNumber(selecteddNumber: number) {
    pageNumber.value = selecteddNumber;
    alert("ページ情報送信");
}

function recieveSubmit(button: string) {

    if ("yes" === button) {
        deleteData();
    }
    // 非表示
    infoLevel.value = 0;
    messageType.value = 0;
}


function deleteData() {

    const capsuleDto: EditAddressCityDeleteCapsuleDtoInterface = new EditAddressCityDeleteCapsuleDto();
    capsuleDto.userDto
    capsuleDto.editEntity = editEntity.value;
    // 異動先の指定の必要はない

    // 削除処理
    title.value = "地方自治体コード差分削除";
    getAuthorizedPromiseArea().then(token => {
        const url = urlBack + "/lgcode-delete/delete";
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
                } else {
                    infoLevel.value = MessageConstants.LEVEL_INFO;
                    messageType.value = MessageConstants.VIEW_TOAST;
                }
            })
            .catch((error) => {
                alert(error);
                infoLevel.value = MessageConstants.LEVEL_ERROR;
                messageType.value = MessageConstants.VIEW_OK;
                message.value = "システム管理者にお問い合わせください";
                return;
            });
    }).catch((e) => {
        infoLevel.value = MessageConstants.LEVEL_ERROR;
        messageType.value = MessageConstants.VIEW_OK;

        if (e instanceof AccessTokenNotFoundError) {
            // トークン保持ができていない場合
            title.value = "現在トークンが存在しません";
            message.value = e.message;
            return;
        }
        if (e instanceof TokenRefreshError) {
            // 取得に失敗している場合
            title.value = "有効期限まじかのトークンを再取得できませんでした";
            message.value = e.message;
            return;
        }
        title.value = "システムエラーが発生しました";
        message.value = "システム管理者にお問い合わせください";
        return;
    });


}


function onDelete(editId: number) {
    const tempEntity: AddressCityDeleteEntityInterface | undefined =
        resultDto.value.listEntity.filter((e) => editId === e.addressCityDeleteId)[0];

    if (undefined !== tempEntity) {
        editEntity.value = tempEntity;
        infoLevel.value = MessageConstants.LEVEL_WARNING;
        messageType.value = MessageConstants.VIEW_YES_NO;
        message.value = "削除すると戻すことができません。よろしいですか？";
    }
}

function onMove(editId: number) {
    const tempEntity: AddressCityDeleteEntityInterface | undefined =
        resultDto.value.listEntity.filter((e) => editId === e.addressCityDeleteId)[0];
    if (undefined !== tempEntity) {
        editCapsuleDto.value.editEntity = tempEntity;
    }
}


function onCancel() {
    history.back();
}

function onSave() {
    alert("保存");

    // 地方自治体コード移行処理
    title.value = "地方自治体コード移動";
    getAuthorizedPromiseArea().then(token => {
        const url = urlBack + "/lgcode-delete/move";
        const method = "POST";
        const body = JSON.stringify(editCapsuleDto.value);
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
                } else {
                    infoLevel.value = MessageConstants.LEVEL_INFO;
                    messageType.value = MessageConstants.VIEW_TOAST;
                }
            })
            .catch((error) => {
                alert(error);
                infoLevel.value = MessageConstants.LEVEL_ERROR;
                messageType.value = MessageConstants.VIEW_OK;
                message.value = "システム管理者にお問い合わせください";
                return;
            });
    }).catch((e) => {
        infoLevel.value = MessageConstants.LEVEL_ERROR;
        messageType.value = MessageConstants.VIEW_OK;

        if (e instanceof AccessTokenNotFoundError) {
            // トークン保持ができていない場合
            title.value = "現在トークンが存在しません";
            message.value = e.message;
            return;
        }
        if (e instanceof TokenRefreshError) {
            // 取得に失敗している場合
            title.value = "有効期限まじかのトークンを再取得できませんでした";
            message.value = e.message;
            return;
        }
        title.value = "システムエラーが発生しました";
        message.value = "システム管理者にお問い合わせください";
        return;
    });
}

// 地方自治体コードを受信
function recieveLgCode(data: string) {
    editCapsuleDto.value.moveLgCode = data;
}
</script>
<template>
    <!-- 管理者メニュー兼チェック -->
    <ManagerInfo :user-dto="userDto"></ManagerInfo>

    <h1>地方自治体コード差分編集</h1>

    <div class="one-line">
        <div class="left-area">
            &nbsp;
        </div>
        <div class="right-area">
            <button @click="onSearch">検索する</button>
        </div>
    </div>

    <h3>削除予定リスト</h3>
    <table>
        <tbody>
            <tr>
                <th>地方自治体コード</th>
                <th>自治体名称</th>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
            </tr>
            <tr v-for="entity in resultDto.listEntity" :key="entity.addressCityDeleteId">
                <td>{{ entity.lgCode }}</td>
                <td>{{ entity.orgName }}</td>
                <td><button @click="onDelete(entity.addressCityDeleteId)">削除</button> </td>
                <td><button @click="onMove(entity.addressCityDeleteId)">移行</button></td>
            </tr>
        </tbody>
    </table>

    <!-- ページング  -->
    <PagingControl :all-count="allCount" :limit="limit" :page-number="pageNumber"
        @send-paging-number="recievePagingNumber"></PagingControl>

    <h3>住居データ一括移行</h3>
    <div class="one-line">
        <div class="left-area">
            移動元地方自治体コード
        </div>
        <div class="right-area">
            {{ editCapsuleDto.editEntity.lgCode }} ： {{ editCapsuleDto.editEntity.orgName }}
        </div>
    </div>
    <div class="one-line">
        <div class="left-area">
            移動先地方自治体コード
        </div>
        <div class="right-area">
            <InputLgcode :is-digit5="false" :lg-code="editCapsuleDto.moveLgCode" @send-lg-code="recieveLgCode"></InputLgcode>
        </div>
    </div>


    <div class="footer">
        <button @click="onCancel" class="footer-button">キャンセル</button>
        <button @click="onSave" class="footer-button left-space">送信</button>
    </div>

    <!-- メッセージ表示 -->
    <div class="overMessage" v-if="messageType !== MessageConstants.VIEW_NONE">
        <MessageView :info-level="infoLevel" :message-type="messageType" :title="title" :message="message"
            @send-submit="recieveSubmit">
        </MessageView>
    </div>

</template>
<style scoped></style>
