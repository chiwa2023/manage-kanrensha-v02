<script setup lang="ts">
import { onMounted, ref, type Ref } from 'vue';
import { MoveKanrenshaCodeHistoryResultDto, type MoveKanrenshaCodeHistoryResultDtoInterface } from '../../dto/kanrensha/moveKanrenshaCodeHistoryResultDto';
import { getErrorMessage, MessageConstants, MessageView, type LeastUserDtoInterface } from 'seijishikin-jp-normalize_common-tool';
import UserRoleConstants from '../../dto/user/userRoleConstants';
import RoutePathConstants from '../../../../routePathConstants';
import { MoveKanrenshaCodeHistoryCapsuleDto, type MoveKanrenshaCodeHistoryCapsuleDtoInterface } from '../../dto/kanrensha/moveKanrenshaCodeHistoryCapsuleDto';
import getAuthorizedPromiseArea from '../../dto/login/getAuthorizedPromiseArea';
import { AccessTokenNotFoundError, TokenRefreshError } from '../../dto/login/errors';

// props,emits
const props = defineProps<{ userDto: LeastUserDtoInterface, kanrenshaKbn: number, codeOrgin: string, codeAbolish: string }>();
const emits = defineEmits(["sendCancelKanrenshaCodeHistory"]);

// よく使う定数
const BLANK: string = "";
// const INIT_NUMBER: number = 0;
// const SERVER_STATUS_OK: number = 200;
const SERVER_STATUS_ERROR: number = 400;
// const SEARCH_LIMIT: number = 20;
// メッセージボックス表示定数
const INQUIRE_FLG: boolean = false;
const ERR_MESS_ONLY: boolean = true;
const MESS_PAGE_NAME: string = "関連者政治団体編集";
const INIT_CALLER: string = "no branch";

const infoLevel: Ref<number> = ref(MessageConstants.LEVEL_NONE);
const messageType: Ref<number> = ref(MessageConstants.VIEW_NONE);
const caller: Ref<string> = ref(INIT_CALLER);
const message: Ref<string> = ref(BLANK);

// back側アクセス
const urlBack: string = RoutePathConstants.DOMAIN + RoutePathConstants.BASE_PATH;

const resultDto: Ref<MoveKanrenshaCodeHistoryResultDtoInterface> = ref(new MoveKanrenshaCodeHistoryResultDto())


onMounted(() => {

    const capsuleDto: MoveKanrenshaCodeHistoryCapsuleDtoInterface = new MoveKanrenshaCodeHistoryCapsuleDto();
    capsuleDto.userDto = props.userDto;
    capsuleDto.kanrenshaKbn = props.kanrenshaKbn;
    capsuleDto.codeAbolish = props.codeAbolish;
    capsuleDto.codeOrgin = props.codeOrgin;

    getAuthorizedPromiseArea().then(token => {
        const url = urlBack + "/kanrensha-code-move/history";
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

                if (response.status > SERVER_STATUS_ERROR) {
                    message.value = getErrorMessage(resultDto.value.message, ERR_MESS_ONLY);
                    infoLevel.value = MessageConstants.LEVEL_ERROR;
                    messageType.value = MessageConstants.VIEW_OK;
                    return;
                }

                if (resultDto.value.isFailure) {
                    message.value = resultDto.value.message;
                    infoLevel.value = MessageConstants.LEVEL_WARNING;
                    messageType.value = MessageConstants.VIEW_OK;
                    return;
                } else {
                    // すべてに該当がなければ通知
                    if (resultDto.value.listCodeAbolish.length === 0 && resultDto.value.listCodeOrgin.length === 0
                        && resultDto.value.listUserAbolish.length === 0 && resultDto.value.listUserOrgin.length === 0) {
                        message.value = "該当する履歴がありませんでした";
                        infoLevel.value = MessageConstants.LEVEL_WARNING;
                        messageType.value = MessageConstants.VIEW_OK;
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
});

function onCancel() {
    emits("sendCancelKanrenshaCodeHistory");
}

function convertIsLatest(isLatest: number): string {
    if (1 === isLatest) {
        return "最新";
    } else {
        return "";
    }
}
function recieveSubmit() {
    infoLevel.value = 0;
    messageType.value = 0;
}
</script>
<template>

    <h3>存続コード履歴</h3>

    <div class="one-line">
        <table>
            <tbody>
                <tr>
                    <th>関連者区分</th>
                    <th>ユーザ</th>
                    <th>最新</th>
                    <th>関連者コード</th>
                    <th>更新時間</th>
                </tr>
                <tr v-for="(dto, index) of resultDto.listCodeOrgin" :key="index">
                    <td> ({{ dto.userPersonCode }}) <br> {{ dto.userPersonName }}</td>
                    <td>{{ convertIsLatest(dto.isLatest) }}</td>
                    <td>{{ UserRoleConstants.getLabel(dto.role) }}</td>
                    <td>{{ dto.kanrenshaCode }} </td>
                    <td>{{ dto.insertTimestamp }} </td>
                </tr>
            </tbody>
        </table>
    </div>


    <h3>廃止コード履歴</h3>
    <div class="one-line">
        <table>
            <tbody>
                <tr>
                    <th>関連者区分</th>
                    <th>ユーザ</th>
                    <th>最新</th>
                    <th>関連者コード</th>
                    <th>更新時間</th>
                </tr>
                <tr v-for="(dto, index) of resultDto.listCodeAbolish" :key="index">
                    <td> ({{ dto.userPersonCode }}) <br> {{ dto.userPersonName }}</td>
                    <td>{{ convertIsLatest(dto.isLatest) }}</td>
                    <td>{{ UserRoleConstants.getLabel(dto.role) }}</td>
                    <td>{{ dto.kanrenshaCode }} </td>
                    <td>{{ dto.insertTimestamp }} </td>
                </tr>
            </tbody>
        </table>
    </div>

    <h3>存続コードユーザ履歴</h3>
    <div class="one-line">
        <table>
            <tbody>
                <tr>
                    <th>関連者区分</th>
                    <th>ユーザ</th>
                    <th>最新</th>
                    <th>関連者コード</th>
                    <th>更新時間</th>
                </tr>
                <tr v-for="(dto, index) of resultDto.listUserOrgin" :key="index">
                    <td> ({{ dto.userPersonCode }}) <br> {{ dto.userPersonName }}</td>
                    <td>{{ convertIsLatest(dto.isLatest) }}</td>
                    <td>{{ UserRoleConstants.getLabel(dto.role) }}</td>
                    <td>{{ dto.kanrenshaCode }} </td>
                    <td>{{ dto.insertTimestamp }} </td>
                </tr>
            </tbody>
        </table>
    </div>

    <h3>廃止コードユーザ履歴</h3>
    <div class="one-line">
        <table>
            <tbody>
                <tr>
                    <th>関連者区分</th>
                    <th>ユーザ</th>
                    <th>最新</th>
                    <th>関連者コード</th>
                    <th>更新時間</th>
                </tr>
                <tr v-for="(dto, index) of resultDto.listUserAbolish" :key="index">
                    <td> ({{ dto.userPersonCode }}) <br> {{ dto.userPersonName }}</td>
                    <td>{{ convertIsLatest(dto.isLatest) }}</td>
                    <td>{{ UserRoleConstants.getLabel(dto.role) }}</td>
                    <td>{{ dto.kanrenshaCode }} </td>
                    <td>{{ dto.insertTimestamp }} </td>
                </tr>
            </tbody>
        </table>
    </div>

    <div class="footer">
        <button @click="onCancel" class="footer-button">キャンセル</button>
    </div>

    <!-- メッセージ表示    -->
    <div class="overMessage" v-if="messageType !== MessageConstants.VIEW_NONE">
        <MessageView :info-level="infoLevel" :message-type="messageType" :title="MESS_PAGE_NAME" :message="message"
            :caller="caller" @send-submit="recieveSubmit">
        </MessageView>
    </div>

</template>
<style scoped></style>
