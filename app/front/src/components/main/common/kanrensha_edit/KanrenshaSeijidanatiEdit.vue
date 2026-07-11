<script setup lang="ts">
import {
    getErrorMessage,
    MessageConstants, MessageView, ViewInputAccess, ViewInputAddress, ViewInputKanrenshaLeast, ViewInputOrgName,
    type KanrenshaSeijidantaiMasterEntityInterface, type LeastUserDtoInterface
} from 'seijishikin-jp-normalize_common-tool';
import { onMounted, ref, watch, type Ref } from 'vue';
import RoutePathConstants from '../../../../routePathConstants';
import { GetKanrenshaSeijidantaiCapsuleDto, type GetKanrenshaSeijidantaiCapsuleDtoInterface } from '../../dto/kanrensha/getKanrenshaSeijidantaiCapsuleDto';
import getAuthorizedPromiseArea from '../../dto/login/getAuthorizedPromiseArea';
import { KanrenshaSeijidantaiDto, type KanrenshaSeijidantaiDtoInterface } from '../../dto/kanrensha/kanrenshaSeijidantaiDto';
import type { GetKanrenshaSeijidantaiResultDtoInterface } from '../../dto/kanrensha/getKanrenshaSeijidantaiResultDto';
import { AccessTokenNotFoundError, TokenRefreshError } from '../../dto/login/errors';

// props,emit
const props = defineProps<{ editEntity: KanrenshaSeijidantaiMasterEntityInterface, userDto: LeastUserDtoInterface }>();
const emits = defineEmits(["sendCancelSeijidantai", "sendSeijidantaiInterface"]);


// よく使う定数
const BLANK: string = "";
// const SERVER_STATUS_OK: number = 200;
// const SERVER_STATUS_ERROR: number = 400;
const INQUIRE_FLG: boolean = false;
const ERR_MESS_ONLY: boolean = true;
const MESS_PAGE_NAME: string = "関連者政治団体編集";
const INIT_CALLER: string = "no branch";

// メッセージボックス表示定数
const infoLevel: Ref<number> = ref(MessageConstants.LEVEL_NONE);
const messageType: Ref<number> = ref(MessageConstants.VIEW_NONE);
const caller: Ref<string> = ref(INIT_CALLER);
const message: Ref<string> = ref(BLANK);

// back側アクセス
const urlBack: string = RoutePathConstants.DOMAIN + RoutePathConstants.BASE_PATH;

const editSeijidantaiDto: Ref<KanrenshaSeijidantaiDtoInterface> = ref(new KanrenshaSeijidantaiDto());

watch(props, () => {
    load();
});
onMounted(() => {
    load();
});

function load() {
    const capsuleDto: GetKanrenshaSeijidantaiCapsuleDtoInterface = new GetKanrenshaSeijidantaiCapsuleDto();
    capsuleDto.userDto = props.userDto;
    capsuleDto.masterSeijidantaiEntity = props.editEntity;

    getAuthorizedPromiseArea().then(token => {
        const url = urlBack + "/user-kanrensha/get-seijidantai";
        const method = "POST";
        const body = JSON.stringify(capsuleDto);
        const headers = {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'X-AUTH-TOKEN': 'Bearer ' + token
        };
        fetch(url, { method, headers, body })
            .then(async (response) => {
                const resultDto: GetKanrenshaSeijidantaiResultDtoInterface = await response.json();
                message.value = resultDto.message;
                // 処理が成功したら再登録できないようにアップロードファイル情報を初期化
                if (resultDto.isFailure) {
                    infoLevel.value = MessageConstants.LEVEL_WARNING;
                    messageType.value = MessageConstants.VIEW_OK;
                } else {
                    editSeijidantaiDto.value = resultDto.kanrenshaSeijidantaiDto;
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

// // 関連者個人検索
// const isPersonSearch: Ref<boolean> = ref(false);
// let searchPersonType: string = BLANK;
// const delegateId: string = "delegate";
// const accountMgrId: string = "accountMgr";
// /**
//  * 関連者個人検索を表示する
//  */
// function onPersonSearch(searchId: string) {

//     searchPersonType = searchId;
//     isPersonSearch.value = true;
// }

// /**
//  * 検索キャンセルを受信する
//  */
// function recieveCancelPersonNo() {

//     isPersonSearch.value = false;
// }

// /**
//  * 選択された関連者個人を受信を表示する
//  */
// function recievePersonNoInterface(sendDto: MasterPersonInterface) {

//     // 会計責任者から呼び出した場合は選択結果は会計責任者に設定
//     if (accountMgrId === searchPersonType) {
//         editPoliOrgDto.value.accounrMgrLeastDto.personKanrenshaCode = sendDto.personKanrenshaCode;
//         editPoliOrgDto.value.accounrMgrLeastDto.personName = sendDto.partnerName;
//     }

//     // 代表者から呼び出した場合は選択結果は代表者に設定
//     if (delegateId === searchPersonType) {
//         editPoliOrgDto.value.orgDelegateLeastDto.personKanrenshaCode = sendDto.personKanrenshaCode;
//         editPoliOrgDto.value.orgDelegateLeastDto.personName = sendDto.partnerName;
//     }

//     isPersonSearch.value = false;
// }

// function resetData() {
//     // 関連者コード初期化
//     //editPoliOrgDto.value = new PoliOrgNoDto();
// }

// /**
//  *住所編集受信
//  */
// function recieveInputAddressInterface(sendDto: InputAddressDto) {
//     editPoliOrgDto.value.inputAddressDto = sendDto;
//     editPoliOrgDto.value.inputAddressDto.addressAll = sendDto.addressPostal;
// }

// /**
//  * すでに同じ法人番号で登録されているかチェック
//  */
// function onCheckAlreadyRegist() {
//     if (editPoliOrgDto.value.poliOrgKanrenshaCode !== BLANK) {
//         alert("現在既存または新規と確定したデータを編集中です");
//     } else {
//         // 仮で時効の秒数基準で既存だったり新規だったり動作を変更する
//         // TOD Back側で同一判定処理ができたら連結する
//         const date: Date = new Date();
//         if (date.getSeconds() % 2 == 0) {
//             alert("新規データでした");
//             editPoliOrgDto.value.poliOrgKanrenshaCode = "新規";
//         } else {
//             alert("既存データが存在します。変更が必要な場合はデータ検索からやり直してください");
//             editPoliOrgDto.value.poliOrgKanrenshaCode = "1234-tyeer";
//         }
//     }
// }

function onCancel() {
    emits("sendCancelSeijidantai");
}

function onSave() {

    emits("sendSeijidantaiInterface", editSeijidantaiDto.value);
}

function recieveSubmit() {
    infoLevel.value = 0;
    messageType.value = 0;
}
</script>
<template>
    <h3>収支報告書公開情報</h3>

    <div class="one-line">
        <div class="left-area">
            関連者コード
        </div>
        <div class="right-area">
            <input type="text" disabled="true" v-model="editSeijidantaiDto.seijidantaiKanrenshaCode" class="max-input">
        </div>
    </div>

    <div class="one-line">
        <div class="left-area">
            政治団体名称
        </div>
        <div class="right-area">
            <input type="text" v-model="editSeijidantaiDto.inputOrgNameDto.orgName" disabled="true" class="max-input">
        </div>
    </div>

    <div class="one-line">
        <div class="left-area">
            住所
        </div>
        <div class="right-area">
            <input type="text" v-model="editSeijidantaiDto.inputAddressDto.addressAll" disabled="true"
                class="max-input">
        </div>
    </div>

    <div class="one-line">
        <div class="left-area">
            代表者
        </div>
        <div class="right-area">
            <input type="text" v-model="editSeijidantaiDto.orgDelegateLeastDto.personName" disabled="true"
                class="max-input">
        </div>
    </div>

    <hr>

    <h3>基礎情報</h3>

    <!-- 団体名称入力  -->
    <ViewInputOrgName :edit-dto="editSeijidantaiDto.inputOrgNameDto"></ViewInputOrgName>

    <!-- 団体情報  -->
    <div class="one-line">
        <div class="left-area">
            団体区分
        </div>
        <div class="right-area">
            <span><input type="radio" v-model="editSeijidantaiDto.dantaiKbn" :value="'01'"> 政党要件を満たす政党</span>
            <span class="left-space"><input type="radio" v-model="editSeijidantaiDto.dantaiKbn" :value="'02'">
                政党の支部</span>
            <span class="left-space"><input type="radio" v-model="editSeijidantaiDto.dantaiKbn" :value="'03'">
                政治資金団体</span>
            <br>
            <span><input type="radio" v-model="editSeijidantaiDto.dantaiKbn" :value="'04'">
                政治資金規正法第18条の2第1項の規定による政治団体</span>
            <span class="left-space"><input type="radio" v-model="editSeijidantaiDto.dantaiKbn" :value="'05'">
                その他の政治団体</span>
            <span class="left-space"><input type="radio" v-model="editSeijidantaiDto.dantaiKbn" :value="'06'">
                その他の政治団体の支部</span>
        </div>
    </div>
    <div class="one-line">
        <div class="left-area">
            政治団体番号
        </div>
        <div class="right-area">
            <input type="text" v-model="editSeijidantaiDto.poliOrgNo" class="code-input">
        </div>
    </div>

    <!-- 住所入力 -->
    <ViewInputAddress :edit-dto="editSeijidantaiDto.inputAddressDto"></ViewInputAddress>

    <!-- 連絡先入力 -->
    <ViewInputAccess :edit-dto="editSeijidantaiDto.inputAccessDto"></ViewInputAccess>

    <!-- 団体代表者 -->
    <ViewInputKanrenshaLeast :edit-dto="editSeijidantaiDto.orgDelegateLeastDto" title="団体代表者"></ViewInputKanrenshaLeast>

    <!-- 会計責任者 -->
    <ViewInputKanrenshaLeast :edit-dto="editSeijidantaiDto.accounrMgrLeastDto" title="会計責任者"></ViewInputKanrenshaLeast>

    <!-- 
    <hr>
    <h3>変更履歴</h3>

    <div class="left-area">
        履歴表示
    </div>
    <div class="right-area">
        <button>展開</button>
    </div>
    <div class="clear-both"></div>
    -->

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
<style scoped></style>
