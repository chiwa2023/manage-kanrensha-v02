<script setup lang="ts">
import { computed, onMounted, ref, watch, type ComputedRef, type Ref } from 'vue';
import { AccessTokenNotFoundError, FrameworkCapsuleDto, getAuthorizedPromiseArea, getErrorMessage, MessageConstants, MessageView, PagingControl, SearchKanrenshaKigyouDt, SearchKanrenshaPerson, SearchKanrenshaSeijidantai, TokenRefreshError, type FrameworkCapsuleDtoInterface, type KanrenshaKigyouDtMasterEntityInterface, type KanrenshaPersonMasterEntityInterface, type KanrenshaSeijidantaiMasterEntityInterface, type LeastUserDtoInterface } from 'seijishikin-jp-normalize_common-tool';
import { WkTblKanrenshaCombineOrgEntity, type WkTblKanrenshaCombineOrgEntityInterface } from '../../entity/wkTblKanrenshaCombineOrgEntity.ts';
import { type YearOptionEntityInterface } from '../../entity/yearOptionEntity.ts';
import KanrenshaKbnConstants from '../../dto/kanrensha/kanrenshaKbnConstants.ts';
import SetYearPeriod from './SetYearPeriod.vue';
import RoutePathConstants from '../../../../routePathConstants.ts';
import type { YearOptionResultDtoInterface } from '../../dto/year_option/yearOptionResultDto.ts';
import { SelectOptionNumberDto, type SelectOptionNumberDtoInterface } from '../../dto/select_options/selectOptionNumberDto';
import { GetKanrenshaCombineLeastCapsuleDto, type GetKanrenshaCombineLeastCapsuleDtoInterface } from '../../dto/kanrensha_combine/getKanrenshaCombineLeastCapsuleDto.ts';
import type { GetKanrenshaCombineLeastResultDtoInterface } from '../../dto/kanrensha_combine/getKanrenshaCombineLeastResultDto.ts';
import { SearchKanrenshaCombineCapsuleDto, type SearchKanrenshaCombineCapsuleDtoInterface } from '../../dto/kanrensha_combine/searchKanrenshaCombineCapsuleDto.ts';
import { UpdateKanrenshaCombineCapsuleDto, type UpdateKanrenshaCombineCapsuleDtoInterface } from '../../dto/kanrensha_combine/updateKanrenshaCombineCapsuleDto.ts';
import createYearArrayText from './createYearArrayText.ts';
import { SearchKanrenshaCombineResultDto, type SearchKanrenshaCombineResultDtoInterface } from '../../dto/kanrensha_combine/searchKanrenshaCombineResultDto.ts';
import type { KanrenshaCombineOrgDto } from '../../dto/kanrensha_combine/kanrenshaCombineOrgDto.ts';

//props,emit
const props = defineProps<{ userDto: LeastUserDtoInterface, isEdit: boolean, kanrenshaCode: string }>();

// よく使う定数
const BLANK: string = "";
const INIT_NUMBER: number = 0;
const INIT_BOOLEAN: boolean = false;
// const SERVER_STATUS_OK: number = 200;
const SERVER_STATUS_ERROR: number = 400;
const SEARCH_LIMIT: number = 20;
// メッセージボックス表示定数
const INQUIRE_FLG: boolean = false;
const ERR_MESS_ONLY: boolean = true;
const MESS_PAGE_NAME: string = "関連者紐づけ編集(個人)";
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

// 編集Entity
const entityEdit: Ref<WkTblKanrenshaCombineOrgEntityInterface> = ref(new WkTblKanrenshaCombineOrgEntity());
entityEdit.value.kanrenshaKbn = KanrenshaKbnConstants.KIGYOU_DT;

// 検索有効
const enableSearch: Ref<boolean> = ref(INIT_BOOLEAN);

// 年選択リスト
const listYearCheck: Ref<YearOptionEntityInterface[]> = ref([]);
const listYearOrg: Ref<YearOptionEntityInterface[]> = ref([]);
const listYearSearch: Ref<SelectOptionNumberDtoInterface[]> = ref([]);

onMounted(() => {
    if ("" !== props.kanrenshaCode) {
        setMyself();
    }

    // 年選択リストを取得
    const yearCapsuleDto: FrameworkCapsuleDtoInterface = new FrameworkCapsuleDto();
    yearCapsuleDto.userDto = props.userDto;

    getAuthorizedPromiseArea().then(token => {
        const url = urlBack + "/year-option/get";
        const method = "POST";
        const body = JSON.stringify(yearCapsuleDto);
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
                    listYearOrg.value = resultDto.listEntity;
                    // 複写
                    for (const entity of listYearOrg.value) {
                        if (0 === capsuleDtoSearch.value.tableYear) {
                            capsuleDtoSearch.value.tableYear = entity.selectedYear;
                        }
                        listYearCheck.value.push(entity);
                        listYearSearch.value.push(createSelectDto(entity.selectedYear));
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
});

function createSelectDto(year: number): SelectOptionNumberDtoInterface {
    const dto: SelectOptionNumberDtoInterface = new SelectOptionNumberDto();
    dto.value = year;
    dto.text = String(year);
    return dto;
}

// 関連者コードの監視
watch(() => props.kanrenshaCode, (newValue, oldValue) => {
    if ("" !== newValue && newValue !== oldValue) {
        setMyself();
    }
});

function setMyself() {

    const capsuleDto: GetKanrenshaCombineLeastCapsuleDtoInterface = new GetKanrenshaCombineLeastCapsuleDto();
    capsuleDto.userDto = props.userDto;
    capsuleDto.kanrenshaCode = props.kanrenshaCode;

    getAuthorizedPromiseArea().then(token => {
        const url = urlBack + "/kanrensha-combine/get-person";
        const method = "POST";
        const body = JSON.stringify(capsuleDto);
        const headers = {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'X-AUTH-TOKEN': 'Bearer ' + token
        };
        fetch(url, { method, headers, body })
            .then(async (response) => {
                const resultDto: GetKanrenshaCombineLeastResultDtoInterface = await response.json();

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
                    entityEdit.value.personKanrenshaCode = resultDto.kanrenshaCode;
                    entityEdit.value.personName = resultDto.kanrenshaName;
                    // 検索時個人は常に限定
                    capsuleDtoSearch.value.personCode = resultDto.kanrenshaCode;
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


// 個人検索
const isSearchPerson: Ref<boolean> = ref(INIT_BOOLEAN);
function recievePersonInterface(entity: KanrenshaPersonMasterEntityInterface) {
    entityEdit.value.personKanrenshaCode = entity.personKanrenshaCode;
    entityEdit.value.personName = entity.kanrenshaName;
    // 検索時個人は常に限定
    capsuleDtoSearch.value.personCode = entity.personKanrenshaCode;
    isSearchPerson.value = false;
    checkExistYear();
}
function recieveCancelPerson() {
    isSearchPerson.value = false;
}
function onSearchPerson() {
    isSearchPerson.value = true;
}

// 企業団体検索
const isSearchKigyouDt: Ref<boolean> = ref(INIT_BOOLEAN);
function recieveKigyouDtInterface(entity: KanrenshaKigyouDtMasterEntityInterface) {
    entityEdit.value.orgKanrenshaCode = entity.kigyouDtKanrenshaCode;
    entityEdit.value.orgName = entity.kanrenshaName;
    isSearchKigyouDt.value = false;
    checkExistYear();
}
function recieveCancelKigyouDt() {
    isSearchKigyouDt.value = false;
}

// 政治団体検索
const isSearchSeijidantai: Ref<boolean> = ref(INIT_BOOLEAN);
function recieveSeijidantaiInterface(entity: KanrenshaSeijidantaiMasterEntityInterface) {
    entityEdit.value.orgKanrenshaCode = entity.seijidantaiKanrenshaCode;
    entityEdit.value.orgName = entity.kanrenshaName;
    isSearchSeijidantai.value = false;
    checkExistYear();
}
function recieveCancelSeijidantai() {
    isSearchSeijidantai.value = false;
}

function onSearchDantai() {
    if (KanrenshaKbnConstants.KIGYOU_DT === entityEdit.value.kanrenshaKbn) {
        isSearchKigyouDt.value = true;
    } else {
        isSearchSeijidantai.value = true;
    }
}


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

function recieveSubmit() {
    infoLevel.value = 0;
    messageType.value = 0;
}

function checkExistYear() {
    if (entityEdit.value.personKanrenshaCode === "" || entityEdit.value.orgKanrenshaCode === "") {
        // 個人と団体双方が揃っていないときは登録記録を取りにいかない
        return;
    }

    const capsuleDtoYear: SearchKanrenshaCombineCapsuleDtoInterface = new SearchKanrenshaCombineCapsuleDto();
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
    history.back();
}

function onSave() {

    const capsuleDtoUpdate: UpdateKanrenshaCombineCapsuleDtoInterface = new UpdateKanrenshaCombineCapsuleDto();
    capsuleDtoUpdate.userDto = props.userDto;
    capsuleDtoUpdate.combineOrgEntity = entityEdit.value;
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

// 検索機能
const disableSearch: ComputedRef<boolean> = computed(() => "" === entityEdit.value.personKanrenshaCode);
const capsuleDtoSearch: Ref<SearchKanrenshaCombineCapsuleDtoInterface> = ref(new SearchKanrenshaCombineCapsuleDto());
capsuleDtoSearch.value.userDto = props.userDto;
capsuleDtoSearch.value.allCount = allCount.value;
capsuleDtoSearch.value.limit = limit.value;
capsuleDtoSearch.value.pageNumber = pageNumber.value;
const resultDto: Ref<SearchKanrenshaCombineResultDtoInterface> = ref(new SearchKanrenshaCombineResultDto());

function onSearch() {
    getAuthorizedPromiseArea().then(token => {
        const url = urlBack + "/kanrensha-combine/search";
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

                if (response.status > SERVER_STATUS_ERROR) {
                    message.value = getErrorMessage(resultDto.value.message, ERR_MESS_ONLY);
                    infoLevel.value = MessageConstants.LEVEL_ERROR;
                    messageType.value = MessageConstants.VIEW_OK;
                    return;
                }

                if (0 === resultDto.value.allCount) {
                    infoLevel.value = MessageConstants.LEVEL_WARNING;
                    messageType.value = MessageConstants.VIEW_OK;
                    message.value = "検索結果が取得できませんでした";
                    return;
                } else {
                    allCount.value = resultDto.value.allCount;
                    pageNumber.value = resultDto.value.pageNumber;
                    limit.value = resultDto.value.limit;
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

function onEditBothSet(index: number) {

    const tempDto: KanrenshaCombineOrgDto | undefined = resultDto.value.listDto[index];

    if (undefined === tempDto) {
        infoLevel.value = MessageConstants.LEVEL_ERROR;
        messageType.value = MessageConstants.VIEW_OK;
        message.value = index + "が取得できませんでした";
        return;

    } else {
        // 選択されたデータをセット
        const tempEntity: WkTblKanrenshaCombineOrgEntityInterface = new WkTblKanrenshaCombineOrgEntity();
        tempEntity.kanrenshaKbn = tempDto.kanrenshaKbn;
        tempEntity.personKanrenshaCode = tempDto.personKanrenshaCode;
        tempEntity.personName = tempDto.personName;
        tempEntity.orgKanrenshaCode = tempDto.orgKanrenshaCode;
        tempEntity.orgName = tempDto.orgName;

        entityEdit.value = tempEntity;
        checkExistYear();
    }
}

function recievePagingNumber(selecteddNumber: number) {
    pageNumber.value = selecteddNumber;
    onSearch();
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
            <button v-if="props.isEdit" @click="onSearchPerson" class="left-space">検索</button>
        </div>
    </div>

    <div class="one-line">
        <div class="left-area">
            検索する
        </div>
        <div class="right-area">
            <input type="checkbox" v-model="enableSearch" :disabled="disableSearch">検索機能を使用
        </div>
    </div>

    <div v-if="enableSearch">
        <h3>履歴検索</h3>
        <div class="one-line">
            <div class="left-area">
                検索年
            </div>
            <div class="right-area">
                <select v-model="capsuleDtoSearch.tableYear">
                    <option v-for="dto in listYearSearch" :key="dto.value" :value="dto.value">{{ dto.text }}</option>
                </select>
            </div>
        </div>
        <div class="one-line">
            <div class="left-area">
                個人コード
            </div>
            <div class="right-area">
                <input type="text" v-model="capsuleDtoSearch.personCode" disabled="true">
            </div>
        </div>

        <div class="one-line">
            <div class="left-area">
                団体区分
            </div>
            <div class="right-area">
                <span> <input type="radio" id="role" v-model="capsuleDtoSearch.kanrenshaKbn"
                        :value=KanrenshaKbnConstants.KIGYOU_DT>関連者企業・団体</span>
                <span class="left-space"> <input type="radio" id="role" v-model="capsuleDtoSearch.kanrenshaKbn"
                        :value=KanrenshaKbnConstants.SEIJIDANTAI>関連者政治団体</span>
            </div>
        </div>

        <div class="one-line">
            <div class="left-area">
                団体コード
            </div>
            <div class="right-area">
                <input type="text" v-model="capsuleDtoSearch.orgCode">
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

        <h3>検索結果</h3><br>

        <!-- ページング -->
        <PagingControl :all-count="allCount" :limit="limit" :page-number="pageNumber"
            @send-paging-number="recievePagingNumber"></PagingControl>
        <div class="one-line-scroll">
            <table>
                <tbody>
                    <tr>
                        <th>個人</th>
                        <th>団体区分</th>
                        <th>団体</th>
                        <th>&nbsp;</th>
                    </tr>
                    <tr v-for="(dto, index) of resultDto.listDto" :key="dto.kanrenshaCombineOrgId">
                        <td>{{ dto.personKanrenshaCode }}<br>{{ dto.personName }}</td>
                        <td>{{ KanrenshaKbnConstants.getLabel(dto.kanrenshaKbn) }}</td>
                        <td>{{ dto.orgKanrenshaCode }}<br>{{ dto.orgName }}</td>
                        <td><button @click="onEditBothSet(index)">編集</button></td>
                    </tr>
                </tbody>
            </table>
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
            <span> <input type="radio" id="role" v-model="entityEdit.kanrenshaKbn"
                    :value=KanrenshaKbnConstants.KIGYOU_DT>関連者企業・団体</span>
            <span class="left-space"> <input type="radio" id="role" v-model="entityEdit.kanrenshaKbn"
                    :value=KanrenshaKbnConstants.SEIJIDANTAI>関連者政治団体</span>
        </div>
    </div>

    <div class="one-line">
        <div class="left-area">
            紐づけ団体
        </div>
        <div class="right-area">
            <input type="text" v-model="entityEdit.orgKanrenshaCode" class="code-input" disabled="true">
            <input type="text" v-model="entityEdit.orgName" class="name-input left-space" disabled="true">
            <button class="left-space" @click="onSearchDantai">検索</button>
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

    <!-- 個人検索 -->
    <div v-if="isSearchPerson" class="overBackgroundLayer2"></div>
    <div v-if="isSearchPerson" class="overComponentLayer2">
        <SearchKanrenshaPerson @send-person-interface="recievePersonInterface" @send-cancel-person="recieveCancelPerson"
            :is-raise-commponet="true">
        </SearchKanrenshaPerson>
    </div>

    <!-- 企業団体検索 -->
    <div v-if="isSearchKigyouDt" class="overBackground"></div>
    <div v-if="isSearchKigyouDt" class="overComponent">
        <SearchKanrenshaKigyouDt v-if="isSearchKigyouDt" @send-kigyou-dt-interface="recieveKigyouDtInterface"
            @send-cancel-kigyou-dt="recieveCancelKigyouDt" :is-raise-commponet="true">
        </SearchKanrenshaKigyouDt>
    </div>

    <!-- 政治団体検索 -->
    <div v-if="isSearchSeijidantai" class="overBackground"></div>
    <div v-if="isSearchSeijidantai" class="overComponent">
        <SearchKanrenshaSeijidantai @send-seijidantai-interface="recieveSeijidantaiInterface"
            @send-cancel-seijidantai="recieveCancelSeijidantai" :is-raise-commponet="true">
        </SearchKanrenshaSeijidantai>
    </div>

</template>
<style scoped></style>
