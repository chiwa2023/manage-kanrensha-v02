<script setup lang="ts">
import { onMounted, ref, type Ref } from 'vue';
import ManagerInfo from '../../common/user_info/ManagerInfo.vue';
import {
    AccessTokenNotFoundError, FrameworkCapsuleDto, getAuthorizedPromiseArea, getErrorMessage, getLoginUser,
    MessageConstants, MessageView, PagingControl, TokenRefreshError, type FrameworkCapsuleDtoInterface,
    type LeastUserDtoInterface
} from 'seijishikin-jp-normalize_common-tool';
import KanrenshaKbnConstants from '../../dto/kanrensha/kanrenshaKbnConstants.ts';
import KanrenshaCombineKigyouDt from '../../common/kanrensha_edit/KanrenshaCombineKigyouDt.vue';
import KanrenshaCombineSeijitandai from '../../common/kanrensha_edit/KanrenshaCombineSeijitandai.vue';
import KanrenshaCombinePerson from '../../common/kanrensha_edit/KanrenshaCombinePerson.vue';
import { SearchKanrenshaCombineCapsuleDto, type SearchKanrenshaCombineCapsuleDtoInterface } from '../../dto/kanrensha_combine/searchKanrenshaCombineCapsuleDto.ts';
import RoutePathConstants from '../../../../routePathConstants.ts';
import { SearchKanrenshaCombineResultDto, type SearchKanrenshaCombineResultDtoInterface } from '../../dto/kanrensha_combine/searchKanrenshaCombineResultDto.ts';
import type { YearOptionResultDtoInterface } from '../../dto/year_option/yearOptionResultDto.ts';
import { SelectOptionNumberDto, type SelectOptionNumberDtoInterface } from '../../dto/select_options/selectOptionNumberDto.ts';
import KanrenshaCombineBothSet from '../../common/kanrensha_edit/KanrenshaCombineBothSet.vue';
import { WkTblKanrenshaCombineOrgEntity, type WkTblKanrenshaCombineOrgEntityInterface } from '../../entity/wkTblKanrenshaCombineOrgEntity.ts';
import type { KanrenshaCombineOrgDto } from '../../dto/kanrensha_combine/kanrenshaCombineOrgDto.ts';

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
const MESS_PAGE_NAME: string = "関連者紐づけ編集";
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

// 検索参照なし
const setKanrenshaKbn: Ref<number> = ref(KanrenshaKbnConstants.PERSON);
const actionType: Ref<number> = ref(INIT_NUMBER);

// 検索
const capsuleDto: Ref<SearchKanrenshaCombineCapsuleDtoInterface> = ref(new SearchKanrenshaCombineCapsuleDto());
capsuleDto.value.userDto = userDto.value;
capsuleDto.value.kanrenshaKbn = KanrenshaKbnConstants.KIGYOU_DT;
capsuleDto.value.allCount = INIT_NUMBER;
capsuleDto.value.limit = SEARCH_LIMIT;
capsuleDto.value.pageNumber = INIT_NUMBER;

const resultDto: Ref<SearchKanrenshaCombineResultDtoInterface> = ref(new SearchKanrenshaCombineResultDto());
const listYearSearch: Ref<SelectOptionNumberDtoInterface[]> = ref([]);

onMounted(() => {

    // 年選択リストを取得
    const yearCapsuleDto: FrameworkCapsuleDtoInterface = new FrameworkCapsuleDto();
    yearCapsuleDto.userDto = userDto.value;

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
                    // 複写
                    for (const entity of resultDto.listEntity) {
                        if (capsuleDto.value.tableYear === 0) {
                            capsuleDto.value.tableYear = entity.selectedYear;
                        }
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


function onSearch() {

    getAuthorizedPromiseArea().then(token => {
        const url = urlBack + "/kanrensha-combine/search";
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


function recievePagingNumber(selecteddNumber: number) {
    pageNumber.value = selecteddNumber;
    onSearch();
}

function recieveSubmit() {

    // 非表示にして呼び出し元は初期化
    infoLevel.value = 0;
    messageType.value = 0;
}


const isEditData: Ref<boolean> = ref(INIT_BOOLEAN);
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
    }

    isEditData.value = true;
}
function recieveBothSetClose() {
    isEditData.value = false;
}


const entityEdit: Ref<WkTblKanrenshaCombineOrgEntityInterface> = ref(new WkTblKanrenshaCombineOrgEntity());

</script>
<template>

    <!-- 管理者メニュー兼チェック -->
    <ManagerInfo :user-dto="userDto"></ManagerInfo>

    <h1>関連者紐づけ(検索参照なし)</h1>

    <div class="one-line">
        <div class="left-area">
            追加／検索
        </div>
        <div class="right-area">
            <span> <input type="radio" id="action" v-model="actionType" value="0">追加</span>
            <span class="left-space"><input type="radio" id="action" v-model="actionType" value="1">検索</span>
        </div>
    </div>

    <div v-if="0 == actionType">
        <div class="one-line">
            <div class="left-area">
                紐づけ団体区分
            </div>
            <div class="right-area">
                <span> <input type="radio" id="role" v-model="setKanrenshaKbn"
                        :value=KanrenshaKbnConstants.PERSON>関連者企業・団体</span>
                <span class="left-space"> <input type="radio" id="role" v-model="setKanrenshaKbn"
                        :value=KanrenshaKbnConstants.KIGYOU_DT>関連者企業・団体</span>
                <span class="left-space"> <input type="radio" id="role" v-model="setKanrenshaKbn"
                        :value=KanrenshaKbnConstants.SEIJIDANTAI>関連者政治団体</span>
            </div>
        </div>

        <hr>

        <div v-if="KanrenshaKbnConstants.PERSON == setKanrenshaKbn">
            <KanrenshaCombinePerson :user-dto="userDto" :is-edit="true" kanrensha-code=""></KanrenshaCombinePerson>
        </div>

        <div v-if="KanrenshaKbnConstants.KIGYOU_DT == setKanrenshaKbn">
            <KanrenshaCombineKigyouDt :user-dto="userDto" :is-edit="true" kanrensha-code=""></KanrenshaCombineKigyouDt>
        </div>

        <div v-if="KanrenshaKbnConstants.SEIJIDANTAI == setKanrenshaKbn">
            <KanrenshaCombineSeijitandai :user-dto="userDto" :is-edit="true" kanrensha-code="">
            </KanrenshaCombineSeijitandai>
        </div>
    </div>

    <div v-if="1 == actionType">
        <h3>検索条件</h3><br>

        <div class="one-line">
            <div class="left-area">
                検索年
            </div>
            <div class="right-area">
                <select v-model="capsuleDto.tableYear">
                    <option v-for="dto in listYearSearch" :key="dto.value" :value="dto.value">{{ dto.text }}</option>
                </select>
            </div>
        </div>

        <div class="one-line">
            <div class="left-area">
                個人コード
            </div>
            <div class="right-area">
                <input type="text" v-model="capsuleDto.personCode">
            </div>
        </div>

        <div class="one-line">
            <div class="left-area">
                団体区分
            </div>
            <div class="right-area">
                <span> <input type="radio" id="role" v-model="capsuleDto.kanrenshaKbn"
                        :value=KanrenshaKbnConstants.KIGYOU_DT>関連者企業・団体</span>
                <span class="left-space"> <input type="radio" id="role" v-model="capsuleDto.kanrenshaKbn"
                        :value=KanrenshaKbnConstants.SEIJIDANTAI>関連者政治団体</span>
            </div>
        </div>

        <div class="one-line">
            <div class="left-area">
                団体コード
            </div>
            <div class="right-area">
                <input type="text" v-model="capsuleDto.orgCode">
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

        <div v-if="isEditData" class="overBackground"></div>
        <div class="overComponent" v-if="isEditData">
            <KanrenshaCombineBothSet :user-dto="userDto" :selected-entity="entityEdit"
                @send-both-set-close="recieveBothSetClose"></KanrenshaCombineBothSet>
        </div>

    </div>
    <!-- ページング -->
    <PagingControl :all-count="allCount" :limit="limit" :page-number="pageNumber"
        @send-paging-number="recievePagingNumber"></PagingControl>
    <div class="one-line-scroll">


        <!-- メッセージ表示    -->
        <div class="overMessage" v-if="messageType !== MessageConstants.VIEW_NONE">
            <MessageView :info-level="infoLevel" :message-type="messageType" :title="MESS_PAGE_NAME" :message="message"
                :caller="caller" @send-submit="recieveSubmit">
            </MessageView>
        </div>
    </div>


</template>
<style scoped></style>
