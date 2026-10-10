<script setup lang="ts">
import { computed, onMounted, ref, type Ref } from 'vue';
import AllUserInfo from '../../common/user_info/AllUserInfo.vue';
import { getErrorMessage, getErrorUniqueIdMessage, MessageConstants, MessageView, PagingControl, type FrameworkMessageAndResultDtoInterface, type LeastUserDtoInterface } from 'seijishikin-jp-normalize_common-tool';
import { getLoginUser } from '../../utils/getLoginUser.ts';
import RoutePathConstants from '../../../../routePathConstants.ts';
import { AddContactMessageCapsuleDto, type AddContactMessageCapsuleDtoInterface } from '../../dto/contact/addContactMessageCapsuleDto.ts';
import getAuthorizedPromiseArea from '../../dto/login/getAuthorizedPromiseArea.ts';
import { AccessTokenNotFoundError, TokenRefreshError } from '../../dto/login/errors.ts';
import { SearchContactManagerCapsuleDto, type SearchContactManagerCapsuleDtoInterface } from '../../dto/contact/searchContactManagerCapsuleDto.ts';
import { SearchContactManagerResultDto, type SearchContactManagerResultDtoInterface } from '../../dto/contact/searchContactManagerResultDto.ts';
import { ContactManagerEntity, type ContactManagerEntityInterface } from '../../entity/contactManagerEntity.ts';

// よく使う定数
const BLANK: string = "";
const INIT_NUMBER: number = 0;
// const SERVER_STATUS_OK: number = 200;
const SERVER_STATUS_ERROR: number = 400;
const SEARCH_LIMIT: number = 20;
// メッセージボックス表示定数
const INQUIRE_FLG: boolean = false;
const ERR_MESS_ONLY: boolean = true;
const MESS_PAGE_NAME: string = "運営者に問い合わせ(自分自身)";
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
const viewStatus: Ref<number> = ref(1);

const capsuleDto: Ref<AddContactMessageCapsuleDtoInterface> = ref(new AddContactMessageCapsuleDto());

onMounted(() => {
    // 過去履歴がある場合は履歴を表示(すべてクローズであっても)
    onSearch();
});

const resultDto: Ref<SearchContactManagerResultDtoInterface> = ref(new SearchContactManagerResultDto());
const resultDtoHistoy: Ref<SearchContactManagerResultDtoInterface> = ref(new SearchContactManagerResultDto());

function onSearch() {
    const capsuleDtoSearch: SearchContactManagerCapsuleDtoInterface = new SearchContactManagerCapsuleDto();
    capsuleDtoSearch.userDto = userDto.value;
    capsuleDtoSearch.allCount = allCount.value;
    capsuleDtoSearch.limit = limit.value;
    capsuleDtoSearch.pageNumber = pageNumber.value;

    getAuthorizedPromiseArea().then(token => {
        const url = urlBack + "/contact-manager/search-myself";
        const method = "POST";
        const body = JSON.stringify(capsuleDtoSearch);
        const headers = {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'X-AUTH-TOKEN': 'Bearer ' + token
        };
        fetch(url, { method, headers, body })
            .then(async (response) => {
                resultDto.value = await response.json();

                if (response.status > SERVER_STATUS_ERROR) {
                    message.value = getErrorMessage(resultDto.value.message, ERR_MESS_ONLY);
                    infoLevel.value = MessageConstants.LEVEL_ERROR;
                    messageType.value = MessageConstants.VIEW_OK;
                    return;
                }


                allCount.value = resultDto.value.allCount;
                limit.value = resultDto.value.limit;
                pageNumber.value = resultDto.value.pageNumber;
                message.value = resultDto.value.message;

                if (resultDto.value.isFailure) {
                    infoLevel.value = MessageConstants.LEVEL_ERROR;
                    messageType.value = MessageConstants.VIEW_OK;
                    return;
                } else {
                    if (resultDto.value.listEntity.length === 0) {
                        // 検索結果が0の時、履歴表示状態の場合は警告
                        if (viewStatus.value === 2) {
                            infoLevel.value = MessageConstants.LEVEL_WARNING;
                            messageType.value = MessageConstants.VIEW_OK;
                            message.value = "検索結果が存在しませんでした";
                            return;
                        }
                    } else {
                        // 履歴がある時は必ず1度は履歴を見せる
                        viewStatus.value = 2;
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

function recievePagingNumber(selecteddNumber: number) {
    pageNumber.value = selecteddNumber;
    // onSearchでページング複写
    onSearch();
}

function recieveSubmit() {

    infoLevel.value = 0;
    messageType.value = 0;
}

function onCancel() {
    history.back();
}

function onSave() {
    capsuleDto.value.userDto = userDto.value;
    if (1 == viewStatus.value) {
        // 新規処理
        getAuthorizedPromiseArea().then(token => {
            const url = urlBack + "/contact-manager/first-contact";
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

    } else {
        // 追加処理処理
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
}

// 選択中の問い合わせIDと判定
const selectedInquiryId: Ref<number | null> = ref(null);
const selectedInquiry = computed(() => {
    return 0 !== saishinEntity.value.contactManagerId;
});

// 履歴表示
const saishinEntity: Ref<ContactManagerEntityInterface> = ref(new ContactManagerEntity());
function showHistory(selectedId: number) {

    selectedInquiryId.value = selectedId;

    // 選択された履歴を抽出する
    const entity: ContactManagerEntityInterface | undefined
        = resultDto.value.listEntity.filter((e) => selectedId === e.contactManagerId)[0];
    if (undefined !== entity) {
        saishinEntity.value = entity;
        capsuleDto.value.isColsed = saishinEntity.value.isClosed;
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

                if (response.status > SERVER_STATUS_ERROR) {
                    message.value = getErrorMessage(resultDtoHistoy.value.message, ERR_MESS_ONLY);
                    infoLevel.value = MessageConstants.LEVEL_ERROR;
                    messageType.value = MessageConstants.VIEW_OK;
                    return;
                }

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

    <!-- ユーザメニュー兼チェック -->
    <AllUserInfo :user-dto="userDto"></AllUserInfo>

    <h1>運営者に連絡を取る(自分自身)</h1><br>

    <h3 class="accent-h3">問い合わせ状態</h3>
    <div class="one-line">
        <div class="left-area">
            問い合わせ
        </div>
        <div class="right-area">
            <span><input type="radio" id="editSelect" v-model="viewStatus" value="1">新規</span>
            <span class="left-space"><input type="radio" id="editSelect" v-model="viewStatus" value="2">履歴</span>
        </div>
    </div>

    <!-- 過去履歴 -->
    <div v-if="2 == viewStatus">

        <h3 class="accent-h3">検索結果</h3>
        <div class="one-line">
            <table>
                <tbody>
                    <tr>
                        <th>初回日時</th>
                        <th>コード</th>
                        <th>状態</th>
                        <th>タイトル</th>
                        <th>最新日時</th>
                        <th>最新メッセージ</th>
                        <th>&nbsp;</th>
                    </tr>
                    <tr v-for="entity in resultDto.listEntity" :key="entity.contactManagerId"
                        :class="{ 'selected-row': selectedInquiryId === entity.contactManagerId }">
                        <td>{{ entity.firstTimestamp }}</td>
                        <td>{{ entity.contactManagerCode }}</td>
                        <td>
                            <span :class="entity.isClosed ? 'status-closed' : 'status-open'">
                                {{ getStatus(entity.isClosed) }}
                            </span>
                        </td>
                        <td>{{ entity.inquireTitle }}</td>
                        <td>{{ entity.insertTimestamp }}</td>
                        <td>{{ convertDigest(entity.inquireContent) }}</td>
                        <td><button @click="showHistory(entity.contactManagerId)">履歴</button></td>
                    </tr>
                    <tr v-if="resultDto.listEntity.length === 0">
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
            <!-- 基本的にないけどクローズ→オープンをしたくなることがある -->
            <div class="one-line">
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
    </div>

    <div v-if="1 == viewStatus">
        <h3 class="accent-h3">新規</h3>
        <div class="one-line">
            <div class="left-area">
                タイトル
            </div>
            <div class="right-area">
                <input type="text" v-model="capsuleDto.inquireTitle" class="max-input">
            </div>
        </div>

        <div class="one-line">
            <div class="left-area">
                問い合わせ内容
            </div>
            <div class="right-area">
                <textarea class="max-input" v-model="capsuleDto.inquireContent"></textarea>
            </div>
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
