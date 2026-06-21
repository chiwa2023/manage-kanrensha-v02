<script setup lang="ts">
import { ref, toRaw, type Ref } from 'vue';
import SearchChangeWkTblAddress from './SearchChangeWkTblAddress.vue';
import { WkTblAddressRsdtChangeEntity, type WkTblAddressRsdtChangeEntityInterface } from '../../entity/wkTblAddressRsdtChangeEntity.ts';
import { SearchWkTblAddressRsdtCapsuleDto, type SearchWkTblAddressRsdtCapsuleDtoInterface } from '../../dto/address_registory/searchWkTblAddressRsdtCapsuleDto.ts';
import { MessageConstants, MessageView, PagingControl, type FrameworkMessageAndResultDtoInterface, type LeastUserDtoInterface } from 'seijishikin-jp-normalize_common-tool';
import getAuthorizedPromiseArea from '../../dto/login/getAuthorizedPromiseArea.ts';
import RoutePathConstants from '../../../../routePathConstants.ts';
import { AccessTokenNotFoundError, TokenRefreshError } from '../../dto/login/errors.ts';
import { SearchWkTblRsdtChangeResultDto, type SearchWkTblRsdtChangeResultDtoInterface } from '../../dto/address_registory/searchWkTblRsdtChangeResultDto.ts';
import { EditWktblRsdtChangeCapsuleDto, type EditWktblRsdtChangeCapsuleDtoInterface } from '../../dto/address_registory/editWktblRsdtChangeCapsuleDto.ts';

// props,emit
const props = defineProps<{ userDto: LeastUserDtoInterface }>();

// よく使う定数
const BLANK: string = "";
const INIT_NUMBER: number = 0;
const INIT_BOOLEAN: boolean = false;
// const SERVER_STATUS_OK: number = 200;
// const SERVER_STATUS_ERROR: number = 400;
const SEARCH_LIMIT: number = 20;

// メッセージボックス表示定数
const infoLevel: Ref<number> = ref(MessageConstants.LEVEL_NONE);
const messageType: Ref<number> = ref(MessageConstants.VIEW_NONE);
const title: Ref<string> = ref(BLANK);
const message: Ref<string> = ref(BLANK);

// back側アクセス
const urlBack: string = RoutePathConstants.DOMAIN + RoutePathConstants.BASE_PATH;

// Paging
const pageNumber: Ref<number> = ref(INIT_NUMBER);
const allCount: Ref<number> = ref(INIT_NUMBER);
const limit: Ref<number> = ref(SEARCH_LIMIT);

const capsuleDtoSearch: Ref<SearchWkTblAddressRsdtCapsuleDtoInterface> = ref(new SearchWkTblAddressRsdtCapsuleDto());
capsuleDtoSearch.value.allCount = allCount.value;
capsuleDtoSearch.value.limit = limit.value;
capsuleDtoSearch.value.pageNumber = pageNumber.value;
capsuleDtoSearch.value.userDto = props.userDto;

const resultDto: Ref<SearchWkTblRsdtChangeResultDtoInterface> = ref(new SearchWkTblRsdtChangeResultDto());

function onSearch() {
    capsuleDtoSearch.value.allCount = allCount.value;
    capsuleDtoSearch.value.pageNumber = pageNumber.value;
    capsuleDtoSearch.value.limit = limit.value;
    capsuleDtoSearch.value.userDto = props.userDto;

    getAuthorizedPromiseArea().then(token => {
        const url = urlBack + "/wktbl-address-rsdt/change-search";
        const method = "POST";
        const body = JSON.stringify(capsuleDtoSearch.value);
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

function onDelete() {

    const capsuleDto: EditWktblRsdtChangeCapsuleDtoInterface = new EditWktblRsdtChangeCapsuleDto();
    capsuleDto.userDto = props.userDto;
    capsuleDto.editEntity = entityEdit.value;

    // 編集された郵便番号を削除
    getAuthorizedPromiseArea().then(token => {
        const url = urlBack + "/wktbl-address-rsdt/delete-change";
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
                title.value = "アドレス・ベース・レジストリデータ削除";
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

function recievePagingNumber(selecteddNumber: number) {
    pageNumber.value = selecteddNumber;
    alert("ページ情報受信");
}


const entityEdit: Ref<WkTblAddressRsdtChangeEntityInterface> = ref(new WkTblAddressRsdtChangeEntity())

function recieveEdit(entity: WkTblAddressRsdtChangeEntityInterface) {
    entityEdit.value = structuredClone(toRaw(entity));
}

const isDelete: Ref<boolean> = ref(INIT_BOOLEAN);
function recieveDelete(entity: WkTblAddressRsdtChangeEntityInterface) {
    entityEdit.value = entity;
    isDelete.value = true;
    infoLevel.value = MessageConstants.LEVEL_WARNING;
    title.value = "データ削除";
    message.value = "このデータを削除してよいですか？";
    messageType.value = MessageConstants.VIEW_YES_NO;
}

function recieveSubmit(button: string) {

    if (isDelete.value) {
        if ("yes" === button) {
            onDelete();
        }
    }

    // 非表示
    infoLevel.value = 0;
    messageType.value = 0;
}

function onCancel() {
    history.back();
}
function onSave() {

    const capsuleDto: EditWktblRsdtChangeCapsuleDtoInterface = new EditWktblRsdtChangeCapsuleDto();
    capsuleDto.userDto = props.userDto;
    capsuleDto.editEntity = entityEdit.value;

    getAuthorizedPromiseArea().then(token => {
        const url = urlBack + "/wktbl-address-rsdt/edit-change";
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
                    title.value = "アドレス・ベース・レジストリ編集";
                    infoLevel.value = MessageConstants.LEVEL_WARNING;
                    messageType.value = MessageConstants.VIEW_OK;
                } else {
                    title.value = "アドレス・ベース・レジストリ編集";
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
</script>
<template>
    <h3>アドレス・ベース・レジストリ差分更新対象</h3>
    <h3>検索条件の指定</h3>

    <!-- 検索条件 -->
    <div class="one-line">
        <div class="left-area">
            地方自治体コード
        </div>
        <div class="right-area">
            <input type="checkbox" v-model="capsuleDtoSearch.isSearchHistory">履歴も検索する
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

    <!-- 検索コンポーネント -->
    <SearchChangeWkTblAddress :list-entity="resultDto.listEntity" :is-edit="true" @send-delete="recieveDelete"
        @send-edit="recieveEdit"></SearchChangeWkTblAddress>

    <!-- ページング -->
    <PagingControl :all-count="allCount" :limit="limit" :page-number="pageNumber"
        @send-paging-number="recievePagingNumber"></PagingControl>

    <h3>編集内容</h3>

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
            郵便番号
        </div>
        <div class="right-area">
            <input v-model="entityEdit.postalcode1" type="text" class="short-input">&nbsp;-&nbsp;
            <input v-model="entityEdit.postalcode2" type="text" class="short-input">
        </div>
    </div>

    <div class="one-line">
        <div class="left-area">
            住所番地
        </div>
        <div class="right-area">
            <textarea v-model="entityEdit.addressBlock" class="max-input"></textarea>
        </div>
    </div>

    <div class="one-line">
        <div class="left-area">
            住所建物
        </div>
        <div class="right-area">
            <textarea v-model="entityEdit.addressBuilding" class="max-input"></textarea>
        </div>
    </div>

    <div class="one-line">
        <div class="left-area">
            住所コード
        </div>
        <div class="right-area">
            <div class="form-group-vertical">
                <div>
                    <span>町字Id</span><input type="text" v-model="entityEdit.machiazaId" class="short-input left-space">
                </div>
                <div>
                    <span>地番Id</span><input type="text" v-model="entityEdit.prcId" class="short-input left-space">
                </div>
                <div>
                    <span>街区Id</span><input type="text" v-model="entityEdit.blkId" class="short-input left-space">
                </div>
                <div>
                    <span>住居Id</span><input type="text" v-model="entityEdit.rsdtId" class="short-input left-space">
                </div>
                <div>
                    <span>住居2Id</span><input type="text" v-model="entityEdit.rsdt2Id" class="short-input left-space">
                </div>
            </div>
        </div>
    </div>

    <div class="footer">
        <button @click="onCancel" class="footer-button">キャンセル</button>
        <button @click="onSave" class="footer-button left-space">送信</button>
    </div>

    <!-- メッセージ表示    -->
    <div class="overMessage" v-if="messageType !== MessageConstants.VIEW_NONE">
        <MessageView :info-level="infoLevel" :message-type="messageType" :title="title" :message="message"
            @send-submit="recieveSubmit">
        </MessageView>
    </div>

</template>
<style scoped></style>
