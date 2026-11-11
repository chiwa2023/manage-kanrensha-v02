<script setup lang="ts">
import { ref, type Ref } from 'vue';
import { getLoginUser, KanrenshaTableHistoryKigyouDtDto, KanrenshaTableHistoryPersonDto, KanrenshaTableHistorySeijidantaiDto, MessageConstants, MessageView, SearchKanrenshaKigyouDt, SearchKanrenshaPerson, SearchKanrenshaSeijidantai, type KanrenshaKigyouDtMasterEntityInterface, type KanrenshaPersonMasterEntityInterface, type KanrenshaSeijidantaiMasterEntityInterface, type KanrenshaTableHistoryKigyouDtDtoInterface, type KanrenshaTableHistoryPersonDtoInterface, type KanrenshaTableHistorySeijidantaiDtoInterface, type LeastUserDtoInterface } from 'seijishikin-jp-normalize_common-tool';
import ManagerInfo from '../../common/user_info/ManagerInfo.vue';
import AddKanrenshaHistoryKigyouDt from '../../common/kanrensha_edit/AddKanrenshaHistoryKigyouDt.vue';
import AddKanrenshaHistoryPerson from '../../common/kanrensha_edit/AddKanrenshaHistoryPerson.vue';
import AddKanrenshaHistorySeijidantai from '../../common/kanrensha_edit/AddKanrenshaHistorySeijidantai.vue';
import KanrenshaKbnConstants from '../../dto/kanrensha/kanrenshaKbnConstants.ts';

// よく使う定数
const BLANK: string = "";
//const BLANK: string = "";
const INIT_BOOLEAN: boolean = false;
// const SERVER_STATUS_OK: number = 200;
// const SERVER_STATUS_ERROR: number = 400;
// メッセージボックス表示定数
// const INQUIRE_FLG: boolean = false;
// const ERR_MESS_ONLY: boolean = true;
const MESS_PAGE_NAME: string = "関連者企業・団体／個人／政治団体履歴追加";
const INIT_CALLER: string = "no branch";
const infoLevel: Ref<number> = ref(MessageConstants.LEVEL_NONE);
const messageType: Ref<number> = ref(MessageConstants.VIEW_NONE);
const caller: Ref<string> = ref(INIT_CALLER);
const message: Ref<string> = ref(BLANK);

// ユーザ呼び出し
const userDto: Ref<LeastUserDtoInterface> = ref(getLoginUser());

// 表示
const viewStatus: Ref<number> = ref(1);

const isAddKigyouDt: Ref<boolean> = ref(INIT_BOOLEAN);
function recieveKigyouDtInterface(selectedEntity: KanrenshaKigyouDtMasterEntityInterface) {
    editDtoKigyouDt.value.kigyouDtKanrenshaCode = selectedEntity.kigyouDtKanrenshaCode;
    editDtoKigyouDt.value.allAddress = BLANK;
    editDtoKigyouDt.value.allName = BLANK;
    isAddKigyouDt.value = true;
}

const isAddPerson: Ref<boolean> = ref(INIT_BOOLEAN);
function recievePersonInterface(selectedEntity: KanrenshaPersonMasterEntityInterface) {
    editDtoPerson.value.personKanrenshaCode = selectedEntity.personKanrenshaCode;
    editDtoPerson.value.allAddress = BLANK;
    editDtoPerson.value.allName = BLANK;
    editDtoPerson.value.personShokugyou = BLANK;
    isAddPerson.value = true;
}

const isAddSeijidantai: Ref<boolean> = ref(INIT_BOOLEAN);
function recieveSeijidantaiInterface(selectedEntity: KanrenshaSeijidantaiMasterEntityInterface) {
    editDtoSeijidantai.value.seijidantaiKanrenshaCode = selectedEntity.seijidantaiKanrenshaCode;
    editDtoSeijidantai.value.allAddress = BLANK;
    editDtoSeijidantai.value.allName = BLANK;
    isAddSeijidantai.value = true;
}

function recieveSubmit() {
    infoLevel.value = 0;
    messageType.value = 0;
}
function onCancel() {
    history.back();
}

// 編集受け渡しDto
const editDtoKigyouDt: Ref<KanrenshaTableHistoryKigyouDtDtoInterface> = ref(new KanrenshaTableHistoryKigyouDtDto());
const editDtoPerson: Ref<KanrenshaTableHistoryPersonDtoInterface> = ref(new KanrenshaTableHistoryPersonDto());
const editDtoSeijidantai: Ref<KanrenshaTableHistorySeijidantaiDtoInterface> = ref(new KanrenshaTableHistorySeijidantaiDto());

function recieveKigyouDtClose() {
    isAddKigyouDt.value = false;
}
function recievePersonClose() {
    isAddPerson.value = false;
}
function recieveSeijidantaiClose() {
    isAddSeijidantai.value = false;
}


</script>
<template>

    <!-- 管理者メニュー兼チェック -->
    <ManagerInfo :user-dto="userDto"></ManagerInfo>

    <h1>関連者履歴追加</h1><br>

    <div class="one-line">
        ※関連者履歴の編集・削除は概念として存在しません
    </div>

    <div class="one-line">
        <div class="left-area">
            関連者区分
        </div>
        <div class="right-area">
            <span><input type="radio" id="editSelect" v-model="viewStatus" value=1>1.個人</span>
            <span class="left-space"><input type="radio" id="editSelect" v-model="viewStatus" value=2>2.企業／団体</span>
            <span class="left-space"><input type="radio" id="editSelect" v-model="viewStatus" value=3>3.政治団体</span>
        </div>
    </div>

    <!-- 個人追加 -->
    <div v-if="KanrenshaKbnConstants.PERSON == viewStatus">

        <!-- 個人検索 -->
        <SearchKanrenshaPerson @send-person-interface="recievePersonInterface" :is-raise-commponet="false">
        </SearchKanrenshaPerson>

        <!-- 個人履歴追加 -->
        <div v-if="isAddPerson" class="overBackground"></div>
        <div v-if="isAddPerson" class="overComponent">
            <AddKanrenshaHistoryPerson :user-dto="userDto" :edit-dto="editDtoPerson"
                @send-person-close="recievePersonClose"></AddKanrenshaHistoryPerson>
        </div>

    </div>

    <!-- 企業団体追加 -->
    <div v-if="KanrenshaKbnConstants.KIGYOU_DT == viewStatus">

        <!-- 企業団体検索 -->
        <SearchKanrenshaKigyouDt @send-kigyou-dt-interface="recieveKigyouDtInterface" :is-raise-commponet="false">
        </SearchKanrenshaKigyouDt>

        <!-- 企業団体履歴追加 -->
        <div v-if="isAddKigyouDt" class="overBackground"></div>
        <div v-if="isAddKigyouDt" class="overComponent">
            <AddKanrenshaHistoryKigyouDt :user-dto="userDto" :edit-dto="editDtoKigyouDt"
                @send-kigyou-dt-close="recieveKigyouDtClose"></AddKanrenshaHistoryKigyouDt>
        </div>
    </div>

    <!-- 政治団体追加 -->
    <div v-if="KanrenshaKbnConstants.SEIJIDANTAI == viewStatus">

        <!-- 政治団体検索 -->
        <SearchKanrenshaSeijidantai @send-seijidantai-interface="recieveSeijidantaiInterface"
            :is-raise-commponet="false">
        </SearchKanrenshaSeijidantai>

        <!-- 政治団体履歴追加 -->
        <div v-if="isAddSeijidantai" class="overBackground"></div>
        <div v-if="isAddSeijidantai" class="overComponent">
            <AddKanrenshaHistorySeijidantai :user-dto="userDto" :edit-dto="editDtoSeijidantai"
                @send-seijidantai-close="recieveSeijidantaiClose"></AddKanrenshaHistorySeijidantai>
        </div>


    </div>

    <div class="footer">
        <button class="footer-button" @click="onCancel">キャンセル</button>
    </div>

    <!-- メッセージ表示    -->
    <div class="overMessage" v-if="messageType !== MessageConstants.VIEW_NONE">
        <MessageView :info-level="infoLevel" :message-type="messageType" :title="MESS_PAGE_NAME" :message="message"
            :caller="caller" @send-submit="recieveSubmit">
        </MessageView>
    </div>

</template>
<style scoped></style>
