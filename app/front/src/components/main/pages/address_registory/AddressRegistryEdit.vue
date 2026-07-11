<script setup lang="ts">
import { DtoEntityConstants, getErrorMessage, getErrorUniqueIdMessage, InputDate, InputDateAndNull, InputLgcode, MessageConstants, MessageView, PagingControl, type FrameworkMessageAndResultDtoInterface, type LeastUserDtoInterface } from 'seijishikin-jp-normalize_common-tool';
import { ref, type Ref } from 'vue';
import { getLoginUser } from '../../utils/getLoginUser';
import { AddressRsdtTemplateEntity, type AddressRsdtTemplateEntityInterface } from '../../entity/addressRsdtTemplateEntity';
import ManagerInfo from '../../common/user_info/ManagerInfo.vue';
import { AccessTokenNotFoundError, TokenRefreshError } from '../../dto/login/errors.ts';
import getAuthorizedPromiseArea from '../../dto/login/getAuthorizedPromiseArea.ts';
import RoutePathConstants from '../../../../routePathConstants.ts';
import { SearchAddressRsdtCapsuleDto, type SearchAddressRsdtCapsuleDtoInterface } from '../../dto/address_registory/searchAddressRsdtCapsuleDto.ts';
import { SearchAddressRsdtResultDto, type SearchAddressRsdtResultDtoInterface } from '../../dto/address_registory/searchAddressRsdtResultDto.ts';
import { EditAddressRsdtCapsuleDto, type EditAddressRsdtCapsuleDtoInterface } from '../../dto/address_registory/editAddressRsdtCapsuleDto.ts';
import type { SelectOptionStringDtoInterface } from '../../dto/select_options/selectOptionStringDto.ts';

// よく使う定数
const BLANK: string = "";
const INIT_NUMBER: number = 0;
const INIT_BOOLEAN: boolean = false;
// const SERVER_STATUS_OK: number = 200;
// const SERVER_STATUS_ERROR: number = 400;
const SEARCH_LIMIT: number = 20;
const INQUIRE_FLG: boolean = false;
const ERR_MESS_ONLY: boolean = true;
const MESS_PAGE_NAME: string = "アドレス・ベース・レジストリ編集";
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
const pageNumber: Ref<number> = ref(INIT_NUMBER);
const allCount: Ref<number> = ref(INIT_NUMBER);
const limit: Ref<number> = ref(SEARCH_LIMIT);

const capsuleDto: Ref<SearchAddressRsdtCapsuleDtoInterface> = ref(new SearchAddressRsdtCapsuleDto());
capsuleDto.value.allCount = allCount.value;
capsuleDto.value.limit = limit.value;
capsuleDto.value.pageNumber = pageNumber.value;

// 日付コンポーネント不正値
const LIMIT_DATE = DtoEntityConstants.INIT_DATETIME_LIMIT;

const resultDto: Ref<SearchAddressRsdtResultDtoInterface> = ref(new SearchAddressRsdtResultDto());

// 地方自治体検索
function onSearchLocalGov() {

    if (capsuleDto.value.searchLgCode === null || capsuleDto.value.searchLgCode === undefined || capsuleDto.value.searchLgCode === BLANK) {
        infoLevel.value = MessageConstants.LEVEL_ERROR;
        messageType.value = MessageConstants.VIEW_OK;
        message.value = "地方自治体コードを指定してください";
        return;
    }

    capsuleDto.value.allCount = allCount.value;
    capsuleDto.value.pageNumber = pageNumber.value;
    capsuleDto.value.limit = limit.value;

    // 入力された検索語で郵便番号検索をする
    getAuthorizedPromiseArea().then(token => {
        const url = urlBack + "/address-rsdt/search";
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
function onSave() {

    // 日時コンポーネントエラー検出
    if (editEntity.value.effectDate <= LIMIT_DATE) {
        message.value = "発効日が不正です。入力しなおしてください";
        infoLevel.value = MessageConstants.LEVEL_ERROR;
        messageType.value = MessageConstants.VIEW_OK;
        return;
    }
    // null許容の日付コンポーネントでは途中かけの不正っぽい入力もnullとなる
    // if (null !== editEntity.value.abolishDate) {
    //     if (editEntity.value.abolishDate <= LIMIT_DATE) {
    //         infoLevel.value = MessageConstants.LEVEL_WARNING;
    //         messageType.value = MessageConstants.VIEW_OK;
    //         message.value = "廃止日が不正です。入力しなおしてください";
    //         return;
    //     }
    // }

    // アドレス・ベース・レジストリ住居　保存処理
    const capsuleDto: EditAddressRsdtCapsuleDtoInterface = new EditAddressRsdtCapsuleDto();
    capsuleDto.userDto = userDto.value;
    capsuleDto.editEntity = editEntity.value;

    // 編集された郵便番号を保存
    getAuthorizedPromiseArea().then(token => {
        const url = urlBack + "/address-rsdt/edit";
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


const isAddressEdit: Ref<boolean> = ref(INIT_BOOLEAN);
const editEntity: Ref<AddressRsdtTemplateEntityInterface> = ref(new AddressRsdtTemplateEntity());
function onEdit(selectedId: number) {
    const tempEntity: AddressRsdtTemplateEntityInterface | undefined =
        resultDto.value.listEntity.filter((e) => selectedId === e.addressRsdtId)[0];

    if (tempEntity !== undefined) {
        storedId.value = selectedId;

        editEntity.value = {
            addressRsdtId: tempEntity.addressRsdtId,
            isLatest: tempEntity.isLatest,
            addressAll: tempEntity.addressAll,
            orginAddressAll: tempEntity.orginAddressAll,
            postalcode1: tempEntity.postalcode1,
            postalcode2: tempEntity.postalcode2,
            addressPostal: tempEntity.addressPostal,
            addressBlock: tempEntity.addressBlock,
            addressBuilding: tempEntity.addressBuilding,

            lgCode: tempEntity.lgCode,
            machiazaId: tempEntity.machiazaId,
            blkId: tempEntity.blkId,
            prcId: tempEntity.prcId,
            rsdtId: tempEntity.rsdtId,
            rsdt2Id: tempEntity.rsdt2Id,

            effectDate: tempEntity.effectDate ? new Date(tempEntity.effectDate) : new Date(),
            abolishDate: tempEntity.abolishDate ? new Date(tempEntity.abolishDate) : null
        };

        isAddressEdit.value = true;
    } else {
        infoLevel.value = MessageConstants.LEVEL_ERROR;
        messageType.value = MessageConstants.VIEW_OK;
        message.value = getErrorUniqueIdMessage(selectedId);
        return;
    }
}

const storedId: Ref<number> = ref(INIT_NUMBER);
const deleteText: string = "delete";
function onDeleteEdit(selectedId: number) {
    const tempEntity: AddressRsdtTemplateEntityInterface | undefined =
        resultDto.value.listEntity.filter((e) => selectedId === e.addressRsdtId)[0];

    if (tempEntity !== undefined) {
        storedId.value = selectedId;
        infoLevel.value = MessageConstants.LEVEL_WARNING;
        message.value = "このデータを削除してよいですか？";
        messageType.value = MessageConstants.VIEW_YES_NO;
        caller.value = deleteText;
    } else {
        infoLevel.value = MessageConstants.LEVEL_ERROR;
        messageType.value = MessageConstants.VIEW_OK;
        message.value = getErrorUniqueIdMessage(selectedId);
        return;
    }
}

function onDelete(selectedId: number) {
    const capsuleDto: EditAddressRsdtCapsuleDtoInterface = new EditAddressRsdtCapsuleDto();
    capsuleDto.userDto = userDto.value;

    const tempEntity: AddressRsdtTemplateEntityInterface | undefined =
        resultDto.value.listEntity.filter((e) => selectedId === e.addressRsdtId)[0];
    if (tempEntity !== undefined) {
        capsuleDto.editEntity = editEntity.value;
    } else {
        infoLevel.value = MessageConstants.LEVEL_ERROR;
        messageType.value = MessageConstants.VIEW_OK;
        message.value = getErrorUniqueIdMessage(selectedId);
        return;
    }

    // 編集された郵便番号を削除
    getAuthorizedPromiseArea().then(token => {
        const url = urlBack + "/address-rsdt/delete";
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
    onSearchLocalGov();
}

// 地方自治体コードを受信
function recieveLgCode(dto: SelectOptionStringDtoInterface) {
    capsuleDto.value.searchLgCode = dto.value;
}

function recieveSubmit(button: string, callerMethod: string) {
    if (MessageConstants.BUTTON_YES === button && callerMethod === deleteText) {
        onDelete(storedId.value);
    }

    // 非表示
    infoLevel.value = MessageConstants.LEVEL_NONE;
    messageType.value = MessageConstants.VIEW_NONE;
    caller.value = INIT_CALLER;
}

function recieveDateAndNull(data: Date | null) {
    editEntity.value.abolishDate = data;
}
function recieveDate(data: Date) {
    editEntity.value.effectDate = data;
}

function onChangeAdd() {
    editEntity.value.addressRsdtId = 0;
}

</script>
<template>
    <!-- 管理者メニュー兼チェック -->
    <ManagerInfo :user-dto="userDto"></ManagerInfo>

    <h1>アドレスベースレジストリ編集</h1>

    <h3>検索条件の指定</h3>

    <!-- 検索条件 -->
    <div class="one-line">
        <div class="left-area">
            地方自治体コード
        </div>
        <div class="right-area">
            <InputLgcode :is-digit5="false" :lg-code="capsuleDto.searchLgCode" @send-lg-code="recieveLgCode">
            </InputLgcode>
        </div>
    </div>

    <div class="one-line">
        <div class="left-area">
            検索条件(部分一致)
        </div>
        <div class="right-area">
            <input type="text" v-model="capsuleDto.searchNaturalWords">
        </div>
    </div>

    <div class="one-line">
        <div class="left-area">
            検索
        </div>
        <div class="right-area">
            <button @click="onSearchLocalGov">検索</button>
        </div>
    </div>

    <h3>地方自治体詳細検索結果</h3>

    <div class="one-line-scroll">
        <table>
            <tbody>
                <tr>
                    <th>地方公共団体コード</th>
                    <th>郵便番号</th>
                    <th>町字コード</th>
                    <th>街区コード</th>
                    <th>地番コード</th>
                    <th>住所番地まで</th>
                    <th>住所建物</th>
                    <th>&nbsp;</th>
                    <th>&nbsp;</th>
                </tr>
                <tr v-for="entity of resultDto.listEntity" :key="entity.addressRsdtId">
                    <td>{{ entity.lgCode }}</td>
                    <td>{{ entity.postalcode1 }} - {{ entity.postalcode2 }}</td>
                    <td>{{ entity.machiazaId }}</td>
                    <td>{{ entity.blkId }}</td>
                    <td>{{ entity.prcId }}</td>
                    <td>{{ entity.addressBlock }}</td>
                    <td>{{ entity.addressBuilding }}</td>
                    <td><button @click="onEdit(entity.addressRsdtId)">編集</button></td>
                    <td><button @click="onDeleteEdit(entity.addressRsdtId)">削除</button></td>
                </tr>
            </tbody>
        </table>
    </div>
    <!-- ページング -->
    <PagingControl :all-count="allCount" :limit="limit" :page-number="pageNumber"
        @send-paging-number="recievePagingNumber"></PagingControl>

    <!-- 編集 -->
    <div class="one-line">
        <div class="left-area">
            <button @click="onChangeAdd">新規</button>
        </div>
        <div class="right-area">
            id:{{ editEntity.addressRsdtId }}(0の時は追加)
        </div>
    </div>
    <div class="one-line">
        <div class="left-area">
            地方自治体コード
        </div>
        <div class="right-area">
            {{ editEntity.lgCode }}
        </div>
    </div>

    <div class="one-line">
        <div class="left-area">
            郵便番号
        </div>
        <div class="right-area">
            <input v-model="editEntity.postalcode1" type="text" class="short-input">&nbsp;-&nbsp;
            <input v-model="editEntity.postalcode2" type="text" class="short-input">
        </div>
    </div>

    <div class="one-line">
        <div class="left-area">
            住所番地
        </div>
        <div class="right-area">
            <textarea v-model="editEntity.addressBlock" class="max-input"></textarea>
        </div>
    </div>

    <div class="one-line">
        <div class="left-area">
            住所建物
        </div>
        <div class="right-area">
            <textarea v-model="editEntity.addressBuilding" class="max-input"></textarea>
        </div>
    </div>

    <div class="one-line">
        <div class="left-area">
            住所コード
        </div>
        <div class="right-area">
            <div class="form-group-vertical">
                <div>
                    <span>町字Id</span><input type="text" v-model="editEntity.machiazaId" class="short-input left-space">
                </div>
                <div>
                    <span>地番Id</span><input type="text" v-model="editEntity.prcId" class="short-input left-space">
                </div>
                <div>
                    <span>街区Id</span><input type="text" v-model="editEntity.blkId" class="short-input left-space">
                </div>
                <div>
                    <span>住居Id</span><input type="text" v-model="editEntity.rsdtId" class="short-input left-space">
                </div>
                <div>
                    <span>住居2Id</span><input type="text" v-model="editEntity.rsdt2Id" class="short-input left-space">
                </div>
            </div>
        </div>
    </div>

    <div class="one-line">
        <div class="left-area">
            効力発生日
        </div>
        <div class="right-area">
            <InputDate :date="editEntity.effectDate" :index="1" :is-edit="true" @send-date="recieveDate">
            </InputDate>
        </div>
    </div>
    <div class="one-line">
        <div class="left-area">
            廃止日
        </div>
        <div class="right-area">
            <InputDateAndNull :date="editEntity.abolishDate" :index="2" :is-edit="true" @send-date="recieveDateAndNull">
            </InputDateAndNull>
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
table {
    border-style: solid;
    border-width: 1px;
}

td {
    border-style: solid;
    border-width: 1px;
}

th {
    border-style: solid;
    border-width: 1px;
    text-align: center;
}
</style>
