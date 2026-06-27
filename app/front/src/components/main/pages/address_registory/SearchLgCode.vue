<script setup lang="ts">
import { convertDateText, MessageConstants, MessageView, PagingControl, type LeastUserDtoInterface } from 'seijishikin-jp-normalize_common-tool';
import { ref, type Ref } from 'vue';
import { getLoginUser } from '../../utils/getLoginUser';
import { SearchLgCodeCapsuleDto, type SearchLgCodeCapsuleDtoInterface } from '../../dto/address_registory/searchLgCodeCapsuleDto';
import { SearchLgCodeResultDto, type SearchLgCodeResultDtoInterface } from '../../dto/address_registory/searchLgCodeResultDto';
import getAuthorizedPromiseArea from '../../dto/login/getAuthorizedPromiseArea';
import RoutePathConstants from '../../../../routePathConstants';
import { AccessTokenNotFoundError, TokenRefreshError } from '../../dto/login/errors';


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

// 編集Dto    
const capsuleDto: Ref<SearchLgCodeCapsuleDtoInterface> = ref(new SearchLgCodeCapsuleDto());
const resultDto: Ref<SearchLgCodeResultDtoInterface> = ref(new SearchLgCodeResultDto());

function onSearch() {

    capsuleDto.value.allCount = allCount.value;
    capsuleDto.value.limit = limit.value;
    capsuleDto.value.pageNumber = pageNumber.value;

    getAuthorizedPromiseArea().then(token => {
        const url = urlBack + "/lgcode/search";
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



function onCancel() {
    history.back();
}

function recievePagingNumber(selecteddNumber: number) {
    pageNumber.value = selecteddNumber;
    alert("ページ情報送信");
}

function recieveSubmit(button: string) {
    console.log(button);

    // 非表示
    infoLevel.value = 0;
    messageType.value = 0;
}

</script>
<template>

    <!-- 管理者メニュー兼チェック -->
    <ManagerInfo :user-dto="userDto"></ManagerInfo>

    <h1>地方自治体コード検索</h1>

    <div class="one-line">
        <div class="left-area">
            県選択;
        </div>
        <div class="right-area">
            県選択
            <input type="text" v-model="capsuleDto.prefCode">
        </div>
    </div>

    <div class="one-line">
        <div class="left-area">
            県以外の地名
        </div>
        <div class="right-area">
            <input type="text" v-model="capsuleDto.searchWords">
        </div>
    </div>


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
                <th>発効日</th>
                <th>廃止日</th>
            </tr>
            <tr v-for="entity in resultDto.listEntity" :key="entity.addressAllCityId">
                <td>{{ entity.lgCode }}</td>
                <td>{{ entity.pref }}{{ entity.county }}{{ entity.city }}{{ entity.ward }}</td>
                <td>{{ convertDateText(entity.effectDate) }}</td>
                <td>{{ convertDateText(entity.abolishDate) }}</td>
            </tr>
        </tbody>
    </table>

    <!-- ページング  -->
    <PagingControl :all-count="allCount" :limit="limit" :page-number="pageNumber"
        @send-paging-number="recievePagingNumber"></PagingControl>

    <div class="footer">
        <button @click="onCancel" class="footer-button">キャンセル</button>
    </div>

    <!-- メッセージ表示 -->
    <div class="overMessage" v-if="messageType !== MessageConstants.VIEW_NONE">
        <MessageView :info-level="infoLevel" :message-type="messageType" :title="title" :message="message"
            @send-submit="recieveSubmit">
        </MessageView>
    </div>
</template>
<style scoped></style>
