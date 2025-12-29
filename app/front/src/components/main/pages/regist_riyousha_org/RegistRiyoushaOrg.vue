<script setup lang="ts">
import {
    InputAccessDto, InputAddressDto, InputOrgNameDto, MockViewInputAddress, ViewInputAccess, ViewInputOrgName,
    type InputAccessDtoInterface, type InputAddressDtoInterface, type InputOrgNameDtoInterface
} from 'seijishikin-jp-normalize_common-tool';
import { ref, type Ref } from 'vue';
import MockManagerInfo from '../../../test/common/user_info/MockManagerInfo.vue';
import type { LeastUserDtoInterface } from '../../dto/user/leastUserDto';
import { getLoginUser } from '../../utils/getLoginUser';

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

// ユーザ呼び出し
const userDto: Ref<LeastUserDtoInterface> = ref(getLoginUser());


const tempOrgName: Ref<InputOrgNameDtoInterface> = ref(new InputOrgNameDto());
const tempAddress: Ref<InputAddressDtoInterface> = ref(new InputAddressDto());
const tempAccess: Ref<InputAccessDtoInterface> = ref(new InputAccessDto());



function onCancel() {
    alert("キャンセル");
    history.back();

}
function onSave() {
    alert("保存");
}

</script>
<template>
    <!-- 運営者権限 -->
    <MockManagerInfo :user-dto="userDto"></MockManagerInfo>

    <h1>利用者組織登録・編集</h1><br>

    <h3 class="accent-h3">利用者組織の指定</h3><br>

    <div class="one-line">
        <div class="left-area">
            編集する利用者組織
        </div>
        <div class="right-area">
            <select>
                <option>新規</option>
            </select>
            <button class="left-space">新規登録</button>
        </div>
    </div>



    <h3 class="accent-h3">利用者組織の編集</h3>

    <!-- 団体名入力 -->
    <ViewInputOrgName :edit-dto="tempOrgName"></ViewInputOrgName>

    <!-- 住所入力 -->
    <MockViewInputAddress :edit-dto="tempAddress"></MockViewInputAddress>

    <!-- 連絡先入力 -->
    <ViewInputAccess :edit-dto="tempAccess"></ViewInputAccess>

    <div class="footer">
        <button class="footer-button" @click="onCancel">キャンセル</button>
        <button class="footer-button left-space" @click="onSave">送信</button>
    </div>

</template>
<style scoped></style>
