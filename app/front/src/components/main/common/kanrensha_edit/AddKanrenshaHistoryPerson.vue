<script setup lang="ts">
import { computed, ref, type ComputedRef, type Ref } from 'vue';
import { AccessTokenNotFoundError, getErrorMessage, MessageConstants, MessageView, TokenRefreshError, type FrameworkMessageAndResultDtoInterface, type KanrenshaTableHistoryPersonDtoInterface, type LeastUserDtoInterface } from 'seijishikin-jp-normalize_common-tool';
import getAuthorizedPromiseArea from '../../dto/login/getAuthorizedPromiseArea';
import { AddHistoryKanrenshaPersonCapsuleDto, type AddHistoryKanrenshaPersonCapsuleDtoInterface } from '../../dto/kanrensha/addHistoryKanrenshaPersonCapsuleDto';
import RoutePathConstants from '../../../../routePathConstants';

//props,emit
const props = defineProps<{ userDto: LeastUserDtoInterface, editDto: KanrenshaTableHistoryPersonDtoInterface }>();
const emits = defineEmits(["sendPersonClose"]);

// よく使う定数
const BLANK: string = "";
// const INIT_NUMBER: number = 0;
// const SERVER_STATUS_OK: number = 200;
const SERVER_STATUS_ERROR: number = 400;
// const SEARCH_LIMIT: number = 20;
// メッセージボックス表示定数
const INQUIRE_FLG: boolean = false;
const ERR_MESS_ONLY: boolean = true;
const MESS_PAGE_NAME: string = "関連者企業・団体／個人／政治団体編集";
const INIT_CALLER: string = "no branch";

const infoLevel: Ref<number> = ref(MessageConstants.LEVEL_NONE);
const messageType: Ref<number> = ref(MessageConstants.VIEW_NONE);
const caller: Ref<string> = ref(INIT_CALLER);
const message: Ref<string> = ref(BLANK);

    
/// back側アクセス
const urlBack: string = RoutePathConstants.DOMAIN + RoutePathConstants.BASE_PATH;

// 編集対象
const editDtoPerson: ComputedRef<KanrenshaTableHistoryPersonDtoInterface> = computed(() => props.editDto);


function recieveSubmit(button: string, callerMethod: string) {

    if (callerMethod === "saved" && MESS_PAGE_NAME === button) {
        emits("sendPersonClose");
    }

    infoLevel.value = 0;
    messageType.value = 0;
    caller.value = INIT_CALLER;

}

function onCancel() {
    emits("sendPersonClose");
}

function onSave() {
    const capsuleDto: AddHistoryKanrenshaPersonCapsuleDtoInterface = new AddHistoryKanrenshaPersonCapsuleDto();
    capsuleDto.userDto = props.userDto;
    capsuleDto.editDto = editDtoPerson.value;

    getAuthorizedPromiseArea().then(token => {
        const url = urlBack + "/kanrensha-history/add-person";
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
                    message.value = resultDto.message;
                    infoLevel.value = MessageConstants.LEVEL_INFO;
                    messageType.value = MessageConstants.VIEW_TOAST;
                    caller.value = "saved";
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

</script>
<template>
    <h1>関連者企業・団体履歴追加</h1><br>

    <h3>関連者コード</h3><br>
    <div class="one-line">
        <div class="left-area">
            関連者コード
        </div>
        <div class="right-area">
            <input type="text" v-model="editDtoPerson.personKanrenshaCode" class="code-input" disabled="true">
        </div>
    </div>

    <h3>名称</h3><br>
    <div class="one-line">
        <div class="left-area">
            名称
        </div>
        <div class="right-area">
            <input type="text"" v-model="editDtoPerson.allName" placeholder="すでに報告書などに存在する値をそのまま貼る" class="max-input">
        </div>
    </div>

    <h3>住所</h3><br>
    <div class="one-line">
        <div class="left-area">
            住所
        </div>
        <div class="right-area">
            <input type="text"" v-model="editDtoPerson.allAddress" placeholder="すでに報告書などに存在する値をそのまま貼る"
                class="max-input">
        </div>
    </div>


    <h3>職業</h3><br>
    <div class="one-line">
        <div class="left-area">
            職業
        </div>
        <div class="right-area">
            <input type="text"" v-model="editDtoPerson.personShokugyou" placeholder="すでに報告書などに存在する値をそのまま貼る"
                class="max-input">
        </div>
    </div>



    <div class="footer">
        <button class="footer-button" @click="onCancel">キャンセル</button>
        <button class="footer-button left-space" @click="onSave">送信</button>
    </div>

    <!-- メッセージ表示    -->
    <div class="overMessage" v-if="messageType !== MessageConstants.VIEW_NONE">
        <MessageView :info-level="infoLevel" :message-type="messageType" :title="MESS_PAGE_NAME" :message="message"
            :caller="caller" @send-submit="recieveSubmit">
        </MessageView>
    </div>

</template>
<style scoped></style>
