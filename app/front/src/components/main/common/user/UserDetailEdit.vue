<script setup lang="ts">
import { onMounted, ref, watch, type Ref } from 'vue';
import { LeastUserDto, type LeastUserDtoInterface } from '../../dto/user/leastUserDto';
import mockGetEditUser from '../../../test/common/user/mock/mockGetEditUser';
import UserRoleConstants from '../../dto/user/userRoleConstants';


// props,emits
const props = defineProps<{ editUserId: number, userDto: LeastUserDtoInterface }>()
const emits = defineEmits(["sendCancelEditUser", "sendEditUserInterface"]);

const editUserDto: Ref<LeastUserDtoInterface> = ref(new LeastUserDto());
const hasRoleManager: Ref<boolean> = ref(false);
const hasRolePartnerApi: Ref<boolean> = ref(false);

const kanrenshaRole: Ref<string> = ref("");
const disabledKanrensha: Ref<boolean> = ref(false);

onMounted(() => {
    editUserDto.value = mockGetEditUser(props.editUserId);
    // 利用者権限設定
    if (editUserDto.value.listRoles.includes(UserRoleConstants.MANAGER)) {
        hasRoleManager.value = true;
    }
    if (editUserDto.value.listRoles.includes(UserRoleConstants.PARTNER_API)) {
        hasRolePartnerApi.value = true;
    }
    // 関連者者権限設定
    if (editUserDto.value.listRoles.includes(UserRoleConstants.KANRENSHA_PERSON)) {
        kanrenshaRole.value = UserRoleConstants.KANRENSHA_PERSON;
    }
    if (editUserDto.value.listRoles.includes(UserRoleConstants.KANRENSHA_KIGYOU_DT)) {
        kanrenshaRole.value = UserRoleConstants.KANRENSHA_KIGYOU_DT;
        disabledKanrensha.value = true;
    }
    if (editUserDto.value.listRoles.includes(UserRoleConstants.KANRENSHA_SEIJIDANTAI)) {
        kanrenshaRole.value = UserRoleConstants.KANRENSHA_SEIJIDANTAI;
        disabledKanrensha.value = true;
    }
});

//仮
// よく使う定数
// const BLANK: string = "";
// const INIT_NUMBER: number = 0;
// const SERVER_STATUS_OK: number = 200;
// const SERVER_STATUS_ERROR: number = 400;
// メッセージボックス表示定数
//const infoLevel: Ref<number> = ref(MessageConstants.LEVEL_NONE);
//const messageType: Ref<number> = ref(MessageConstants.VIEW_NONE);
//const title: Ref<string> = ref(BLANK);
//const message: Ref<string> = ref(BLANK);

function onSave() {
    emits("sendEditUserInterface");
}

function onCancel() {
    emits("sendCancelEditUser");
}

</script>
<template>
    <h3>ユーザ情報編集</h3>

    <div class="one-line">
        <div class="left-area">
            識別コード
        </div>
        <div class="right-area">
            {{ editUserDto.userPersonCode }}
        </div>
    </div>

    <div class="one-line">
        <div class="left-area">
            ユーザ名
        </div>
        <div class="right-area">
            <input type="text" v-model="editUserDto.userPersonName">
        </div>
    </div>

    <div class="one-line">
        <div class="left-area">
            利用者権限
        </div>
        <div class="right-area">
            <input type="checkbox" v-model="hasRoleManager">運営者権限
            <input type="checkbox" v-model="hasRolePartnerApi" class="left-space">APIユーザ
        </div>
    </div>

    <div class="one-line">
        <div class="left-area">
            関連者
        </div>
        <div class="right-area">
            <input type="radio" id="role" v-model="kanrenshaRole" :value=UserRoleConstants.NONE
                :disabled="disabledKanrensha">なし
            <input type="radio" id="role" v-model="kanrenshaRole" :value=UserRoleConstants.KANRENSHA_PERSON
                class="left-space" :disabled="disabledKanrensha">個人
            <input type="radio" id="role" v-model="kanrenshaRole" :value=UserRoleConstants.KANRENSHA_KIGYOU_DT
                class="left-space" disabled="true">企業／団体
            <input type="radio" id="role" v-model="kanrenshaRole" :value=UserRoleConstants.KANRENSHA_SEIJIDANTAI
                class="left-space" disabled="true">政治団体
        </div>
    </div>






    <div class="footer">
        <button @click="onCancel" class="footer-button">キャンセル</button>
        <button @click="onSave" class="left-space footer-button">保存</button>
    </div>

</template>
<style scoped></style>
