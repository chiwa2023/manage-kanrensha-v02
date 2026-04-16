<script setup lang="ts">
import { InputAccessDto, InputAddressDto, InputOrgNameDto, ViewInputAccess, MockViewInputAddress, ViewInputOrgName, type InputAccessDtoInterface, type InputAddressDtoInterface, type InputOrgNameDtoInterface, type LeastUserDtoInterface } from 'seijishikin-jp-normalize_common-tool';
import { onBeforeMount, ref, type Ref, watch } from 'vue';
import mockGetOrgName from '../../../test/pages/regist_riyousha_org/mockGetOrgName';
import mockGetAddress from '../../../test/pages/regist_riyousha_org/mockGetAddress';
import mockGetAccess from '../../../test/pages/regist_riyousha_org/mockGetAccess';
import RoutePathConstants from '../../../../routePathConstants';
import type { RiyoushaCombineOrgEntityInterface } from '../../entity/riyoushaCombineOrgEntity';
import RiyoushaKbnConstants from '../../dto/riyousha/riyoushaKbnConstants';
import mockGetRiyoushaCOmbinePersonList from '../../../test/common/riyousha/mockGetRiyoushaCombinePersonList';

//props,emit
const props = defineProps<{ userDto: LeastUserDtoInterface, selectedId: number }>();
const emits = defineEmits(["sendCancelRiyoushaOrg", "sendRiyoushaOrgInterface"]);

//仮
// よく使う定数
// const BLANK: string = "";
// const INIT_NUMBER: number = 0;
// const SERVER_STATUS_OK: number = 200;
// const SERVER_STATUS_ERROR: number = 400;
// const SEARCH_LIMIT: number = 20;
// メッセージボックス表示定数
//const infoLevel: Ref<number> = ref(MessageConstants.LEVEL_NONE);
//const messageType: Ref<number> = ref(MessageConstants.VIEW_NONE);
//const title: Ref<string> = ref(BLANK);
//const message: Ref<string> = ref(BLANK);

// 登録情報
const tempOrgName: Ref<InputOrgNameDtoInterface> = ref(new InputOrgNameDto());
const tempAddress: Ref<InputAddressDtoInterface> = ref(new InputAddressDto());
const tempAccess: Ref<InputAccessDtoInterface> = ref(new InputAccessDto());
const tempPersonList: Ref<RiyoushaCombineOrgEntityInterface[]> = ref([]);

onBeforeMount(() => {
    callData(props.selectedId);
});


watch(props, (newValue) => {
    callData(newValue.selectedId);
});

function callData(selectedId: number) {
    if (selectedId === 0) {
        tempOrgName.value = new InputOrgNameDto();
        tempAddress.value = new InputAddressDto();
        tempAccess.value = new InputAccessDto();
        tempPersonList.value.splice(0);
    } else {
        tempOrgName.value = mockGetOrgName();
        tempAddress.value = mockGetAddress();
        tempAccess.value = mockGetAccess();
        tempPersonList.value = mockGetRiyoushaCOmbinePersonList();
    }

}


function onCancel() {
    alert("キャンセル");
    //history.back();
    emits("sendCancelRiyoushaOrg");
}

function onSave() {
    alert("保存");
    emits("sendRiyoushaOrgInterface");
}

function onDelete(){
    alert("削除");
}
</script>
<template>
    <h3>利用者組織編集</h3>

    <!-- 団体名入力 -->
    <ViewInputOrgName :edit-dto="tempOrgName"></ViewInputOrgName>

    <!-- 住所入力 -->
    <ViewInputAddress :edit-dto="tempAddress"></ViewInputAddress>

    <!-- 連絡先入力 -->
    <ViewInputAccess :edit-dto="tempAccess"></ViewInputAccess>


    <h3>組織構成員リスト</h3>
    <div class="one-line">
        <RouterLink :to="RoutePathConstants.PAGE_REGIST_RIYOUSHA_ORG" class="menu-item">個人・組織紐づけページへ遷移</RouterLink>
    </div>
    <div class="one-line-scroll">
        <table>
            <tbody>
                <tr>
                    <th>区分</th>
                    <th>名称</th>
                    <th>&nbsp;</th>
                </tr>
            </tbody>

            <tbody>
                <tr v-for="entity of tempPersonList" :key="entity.riyoushaCombineOrgId">
                    <td>{{ RiyoushaKbnConstants.getLabel(entity.riyoushaKbn) }}</td>
                    <td>({{ entity.personRiyoushaCode }})<br>{{ entity.personName }}</td>
                    <td><button @click="onDelete">削除</button></td>
                </tr>
            </tbody>

        </table>

    </div>

    <div class="footer">
        <button class="footer-button" @click="onCancel">キャンセル</button>
        <button class="footer-button left-space" @click="onSave">送信</button>
    </div>

</template>
<style scoped></style>
