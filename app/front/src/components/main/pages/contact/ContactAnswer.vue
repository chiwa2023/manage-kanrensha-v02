<script setup lang="ts">
import { ref, computed, type Ref, onMounted } from 'vue';
import ManagerInfo from '../../common/user_info/ManagerInfo.vue';
import { getErrorMessage, getErrorUniqueIdMessage, InputDate, MessageConstants, MessageView, PagingControl, type FrameworkMessageAndResultDtoInterface, type LeastUserDtoInterface } from 'seijishikin-jp-normalize_common-tool';
import { getLoginUser } from '../../utils/getLoginUser.ts';
import RoutePathConstants from '../../../../routePathConstants.ts';
import getAuthorizedPromiseArea from '../../dto/login/getAuthorizedPromiseArea.ts';
import { AccessTokenNotFoundError, TokenRefreshError } from '../../dto/login/errors.ts';
import { SearchContactManagerResultDto, type SearchContactManagerResultDtoInterface } from '../../dto/contact/searchContactManagerResultDto.ts';
import { SearchContactManagerCapsuleDto, type SearchContactManagerCapsuleDtoInterface } from '../../dto/contact/searchContactManagerCapsuleDto.ts';
import { ContactManagerEntity, type ContactManagerEntityInterface } from '../../entity/contactManagerEntity.ts';
import { AddContactMessageCapsuleDto, type AddContactMessageCapsuleDtoInterface } from '../../dto/contact/addContactMessageCapsuleDto.ts';

// よく使う定数
const BLANK: string = "";
const INIT_NUMBER: number = 0;
const SEARCH_LIMIT: number = 20;

// メッセージボックス表示定数
const INQUIRE_FLG: boolean = false;
const ERR_MESS_ONLY: boolean = true;
const MESS_PAGE_NAME: string = "運営者に問い合わせ(回答)";
const INIT_CALLER: string = "no branch";
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

// 検索条件と検索結果
const searchCapsuleDto: Ref<SearchContactManagerCapsuleDtoInterface> = ref(new SearchContactManagerCapsuleDto());
const searchResultDto: Ref<SearchContactManagerResultDtoInterface> = ref(new SearchContactManagerResultDto());

// 履歴
const resultDtoHistoy: Ref<SearchContactManagerResultDtoInterface> = ref(new SearchContactManagerResultDto());

// 問い合わせ登録用
const capsuleDto: Ref<AddContactMessageCapsuleDtoInterface> = ref(new AddContactMessageCapsuleDto());

// 選択中の問い合わせIDと判定
const selectedInquiryId: Ref<number | null> = ref(null);
const selectedInquiry = computed(() => {
    return 0 !== saishinEntity.value.contactManagerId;
});

// 検索処理
function onSearch() {
    searchCapsuleDto.value.userDto = userDto.value;
    searchCapsuleDto.value.allCount = allCount.value;
    searchCapsuleDto.value.limit = limit.value;
    searchCapsuleDto.value.pageNumber = pageNumber.value;

    getAuthorizedPromiseArea().then(token => {
        const url = urlBack + "/contact-manager/search";
        const method = "POST";
        const body = JSON.stringify(searchCapsuleDto.value);
        const headers = {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'X-AUTH-TOKEN': 'Bearer ' + token
        };
        fetch(url, { method, headers, body })
            .then(async (response) => {
                searchResultDto.value = await response.json();
                allCount.value = searchResultDto.value.allCount;
                limit.value = searchResultDto.value.limit;
                pageNumber.value = searchResultDto.value.pageNumber;
                message.value = searchResultDto.value.message;

                if (searchResultDto.value.isFailure) {
                    infoLevel.value = MessageConstants.LEVEL_ERROR;
                    messageType.value = MessageConstants.VIEW_OK;
                    return;
                } else {
                    if (searchResultDto.value.listEntity.length === 0) {
                        infoLevel.value = MessageConstants.LEVEL_WARNING;
                        messageType.value = MessageConstants.VIEW_OK;
                        message.value = "検索結果が存在しませんでした";
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
}


onMounted(() => {
    // 初回検索実行
    onSearch();
});

function recievePagingNumber(selecteddNumber: number) {
    pageNumber.value = selecteddNumber;
}

function recieveSubmit() {
    infoLevel.value = 0;
    messageType.value = 0;
}

function onCancel() {
    history.back();
}

function onSave() {
    if (!selectedInquiry.value) {
        alert("履歴を表示する問い合わせを選択してください。");
        return;
    }
    // if (!newCommentText.value.trim()) {
    //     alert("コメント内容を入力してください。");
    //     return;
    // }


    // const now = new Date();
    // const formattedDate = now.getFullYear() + '-' + 
    //     String(now.getMonth() + 1).padStart(2, '0') + '-' + 
    //     String(now.getDate()).padStart(2, '0');
    // const formattedTime = String(now.getHours()).padStart(2, '0') + ':' + 
    //     String(now.getMinutes()).padStart(2, '0') + ':' + 
    //     String(now.getSeconds()).padStart(2, '0');
    // const fullDateTime = `${formattedDate} ${formattedTime}`;

    // // 履歴に追加
    // selectedInquiry.value.history.push({
    //     date: fullDateTime,
    //     senderName: userDto.value?.allName || '運営者(自分)',
    //     isSelf: true,
    //     comment: newCommentText.value
    // });

    // // 最新状態の更新
    // selectedInquiry.value.latestDate = formattedDate;
    // const rawComment = newCommentText.value;
    // selectedInquiry.value.latestCommentExcerpt = rawComment.slice(0, 20) + (rawComment.length > 20 ? '...' : '');

    // // クローズ処理
    // if (closeOnSave.value) {
    //     selectedInquiry.value.status = 'closed';
    // }

    // // 入力リセット
    // newCommentText.value = '';
    // closeOnSave.value = false;

    // // 検索状態を再反映
    // onSearch();

    // alert("コメントを送信しました。");

    capsuleDto.value.userDto = userDto.value;
    capsuleDto.value.contactManagerCode = saishinEntity.value.contactManagerCode;

    getAuthorizedPromiseArea().then(token => {
        const url = urlBack + "/contact-manager/add";
        const method = "POST";
        const body = JSON.stringify(capsuleDto.value);
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
                    infoLevel.value = MessageConstants.LEVEL_ERROR;
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


// コンポーネントから時刻受け取り
function recieveDate(date: Date, index: number) {
    if (0 == index) {
        searchCapsuleDto.value.startDate = date;
    }
    if (1 == index) {
        searchCapsuleDto.value.endDate = date;
    }
}



// 履歴表示
const saishinEntity: Ref<ContactManagerEntityInterface> = ref(new ContactManagerEntity());
function showHistory(selectedId: number) {

    selectedInquiryId.value = selectedId;

    // 選択された履歴を抽出する
    const entity: ContactManagerEntityInterface | undefined
        = searchResultDto.value.listEntity.filter((e) => selectedId === e.contactManagerId)[0];
    if (undefined !== entity) {
        saishinEntity.value = entity;
    } else {
        infoLevel.value = MessageConstants.LEVEL_ERROR;
        messageType.value = MessageConstants.VIEW_OK;
        message.value = getErrorUniqueIdMessage(selectedId);
        return;
    }

    // 問い合わせコードから履歴を取る
    const capsuleDtoHistory: SearchContactManagerCapsuleDtoInterface = new SearchContactManagerCapsuleDto();
    capsuleDtoHistory.userDto = userDto.value;
    capsuleDtoHistory.contactManagerCode = saishinEntity.value.contactManagerCode;

    getAuthorizedPromiseArea().then(token => {
        const url = urlBack + "/contact-manager/get-history";
        const method = "POST";
        const body = JSON.stringify(capsuleDtoHistory);
        const headers = {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'X-AUTH-TOKEN': 'Bearer ' + token
        };
        fetch(url, { method, headers, body })
            .then(async (response) => {

                // 必ず1件は取れる。とれなかったら大事件
                resultDtoHistoy.value = await response.json();
                if (resultDtoHistoy.value.isFailure) {
                    infoLevel.value = MessageConstants.LEVEL_ERROR;
                    messageType.value = MessageConstants.VIEW_OK;
                    return;
                } else {
                    if (resultDtoHistoy.value.listEntity.length === 0) {
                        infoLevel.value = MessageConstants.LEVEL_WARNING;
                        messageType.value = MessageConstants.VIEW_OK;
                        message.value = "検索結果が存在しませんでした";
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
}

function isMyself(insertUserCode: number): boolean {
    return insertUserCode === userDto.value.userPersonCode;
}
function convertDigest(content: string): string {
    const max: number = 25;
    if (content.length > max) {
        return content.substring(0, max - 2) + "……";
    } else {
        return content;
    }
}

function getStatus(isColsed: boolean): string {

    if (isColsed) {
        return "クローズ";
    } else {
        return "オープン";
    }
}
</script>
<template>

    <!-- 管理者メニュー兼チェック -->
    <ManagerInfo :user-dto="userDto"></ManagerInfo>

    <h1>運営者に連絡を取る(回答)</h1><br>

    <h3 class="accent-h3">検索条件</h3>

    <div class="one-line">
        <div class="left-area">
            検索期間
        </div>
        <div class="right-area">
            <span>
                <InputDate :date="searchCapsuleDto.startDate" :index="0" :is-edit="true" @send-date="recieveDate">
                </InputDate>
                から
            </span>
            <span class="left-space">
                <InputDate :date="searchCapsuleDto.endDate" :index="1" :is-edit="true" @send-date="recieveDate">
                </InputDate>まで
            </span>
        </div>
    </div>
    <div class="one-line">
        <div class="left-area">
            クローズ
        </div>
        <div class="right-area">
            <input type="checkbox" v-model="searchCapsuleDto.isSearchClose"> クローズされた案件も検索
        </div>
    </div>
    <div class="one-line">
        <div class="left-area">
            検索
        </div>
        <div class="right-area">
            <button @click="onSearch">条件を変えて再検索</button>
        </div>
    </div>

    <h3 class="accent-h3">検索結果</h3>
    <div class="one-line">
        <table>
            <tbody>
                <tr>
                    <th>初回日時</th>
                    <th>コード</th>
                    <th>状態</th>
                    <th>名前</th>
                    <th>タイトル</th>
                    <th>最新日時</th>
                    <th>最新メッセージ</th>
                    <th>&nbsp;</th>
                </tr>
                <tr v-for="entity in searchResultDto.listEntity" :key="entity.contactManagerId"
                    :class="{ 'selected-row': selectedInquiryId === entity.contactManagerId }">
                    <td>{{ entity.firstTimestamp }}</td>
                    <td>{{ entity.contactManagerCode }}</td>
                        <td>
                            <span :class="entity.isClosed ? 'status-closed' : 'status-open'">
                                {{ getStatus(entity.isClosed) }}
                            </span>
                        </td>
                    <td>{{ entity.inquireUserName }}</td>
                    <td>{{ entity.inquireTitle }}</td>
                    <td>{{ entity.insertTimestamp }}</td>
                    <td>{{ convertDigest(entity.inquireContent) }}</td>
                    <td><button @click="showHistory(entity.contactManagerId)">履歴</button></td>
                </tr>
                <tr v-if="searchResultDto.listEntity.length === 0">
                    <td colspan="7" style="text-align: center; color: #888;">該当する問い合わせはありません。</td>
                </tr>
            </tbody>
        </table>
    </div>
    <PagingControl :all-count="allCount" :limit="limit" :page-number="pageNumber"
        @send-paging-number="recievePagingNumber"></PagingControl>

    <h3 class="accent-h3">履歴 </h3>

    <div class="one-line" v-if="selectedInquiry">
        <span v-if="selectedInquiry" class="selected-title">(コード: {{ saishinEntity.contactManagerCode }} - {{
            saishinEntity.inquireTitle }})</span>
    </div>

    <div class="chat-container" v-if="selectedInquiry">
        <div v-for="(entity, index) in resultDtoHistoy.listEntity" :key="index"
            :class="['chat-message', isMyself(entity.insertUserCode) ? 'self' : 'other']">
            <div class="chat-meta">
                <span class="chat-sender" v-if="!isMyself(entity.insertUserCode)">{{ entity.insertUserName
                    }}</span>
                <span class="chat-time">{{ entity.insertTimestamp }}</span>
            </div>
            <div class="chat-bubble">
                {{ entity.inquireContent }}
            </div>
        </div>
        <div v-if="resultDtoHistoy.listEntity.length === 0" class="chat-empty">
            コメントはありません。
        </div>
    </div>
    <div class="chat-container" v-else>
        <div class="chat-empty">
            検索結果の「履歴」ボタンをクリックして、履歴を表示してください。
        </div>
    </div>

    <h3 class="accent-h3">追加 </h3>
    <div class="one-line" v-if="selectedInquiry">
        <span v-if="!selectedInquiry" class="warn-title">(問い合わせを選択してください)</span>
    </div>
    <div v-if="selectedInquiry">
        <div class="one-line" v-if="!saishinEntity.isClosed">
            <div class="left-area">
                クローズ
            </div>
            <div class="right-area">
                <input type="checkbox" v-model="capsuleDto.isColsed"> この問い合わせをクローズする
            </div>
        </div>
        <div class="one-line">
            <div class="left-area">
                コメント内容
            </div>
            <div class="right-area">
                <textarea class="max-input" v-model="capsuleDto.inquireContent"
                    placeholder="返信コメントを入力してください"></textarea>
            </div>
        </div>
    </div>
    <div class="one-line" v-else>
        <div style="margin-left: 1.5rem; color: #6c757d;">
            問い合わせ一覧から「履歴」ボタンを押すとコメントを入力できます。
        </div>
    </div>

    <div class="footer">
        <button @click="onCancel" class="footer-button">キャンセル</button>
        <button @click="onSave" class="footer-button left-space" :disabled="!selectedInquiry">送信</button>
    </div>

    <!-- メッセージ表示 -->
    <div class="overMessage" v-if="messageType !== MessageConstants.VIEW_NONE">
        <MessageView :info-level="infoLevel" :message-type="messageType" :title="MESS_PAGE_NAME" :message="message"
            :caller="caller" @send-submit="recieveSubmit">
        </MessageView>
    </div>

</template>
<style scoped>
@import "./chat.css";

.selected-row {
    background-color: var(--accent-color, #fffacd);
}

.status-closed {
    color: #6c757d;
    font-weight: bold;
    background-color: #e9ecef;
    padding: 0.2rem 0.5rem;
    border-radius: 4px;
}

.status-open {
    color: #155724;
    font-weight: bold;
    background-color: #d4edda;
    padding: 0.2rem 0.5rem;
    border-radius: 4px;
}

.selected-title {
    font-size: 0.95rem;
    font-weight: normal;
    color: #495057;
    margin-left: 1rem;
}

.warn-title {
    font-size: 0.95rem;
    font-weight: normal;
    color: #dc3545;
    margin-left: 1rem;
}
</style>
