<script setup lang="ts">
import { ref, type Ref } from 'vue';
import {
    InputAccessDto, InputAddressDto, InputPersonNameDto, MockViewInputAddress, ViewInputAccess,
    ViewInputPersonName,
    type InputAccessDtoInterface, type InputAddressDtoInterface,
    type InputPersonNameDtoInterface
} from 'seijishikin-jp-normalize_common-tool';

// import type UserPersonLeastInterface from '../../../dto/user/userPersonLeastDto';
// import type RiyoushaManagerDtoInterface from '../../../dto/riyousha/riyoushaManagerDto';
// import RiyoushaManagerDto from '../../../dto/riyousha/riyoushaManagerDto';
// import type RiyoushaManagerEntityInterface from '../../../entity/riyoushaManagerEntity';
// import type InputPersonNameInterface from '../../../dto/input_person_name/inputPersonNameDto';
// import type InputOrgNameInterface from '../../../dto/input_org_name/inputOrgNameDto';
// import type InputAddressDto from '../../../dto/Input_address/inputAddressDto';
// import type SaveRiyoushaManagerCapsuleInterface from '../../../dto/riyousha/saveRiyoushaManagerCapsuleDto';
// import SaveRiyoushaManagerCapsuleDto from '../../../dto/riyousha/saveRiyoushaManagerCapsuleDto';
// import getAuthorizedPromiseArea from '../../../dto/login/getAuthorizedPromiseArea';
// import type FrameworkMessageAndResultInterface from '../../../dto/frameworkMessageAndResultDto';
// import type GetRiyoushaManagerCapsuleInterface from '../../../dto/riyousha/getRiyoushaManagerCapsuleDto';
// import GetRiyoushaManagerCapsuleDto from '../../../dto/riyousha/getRiyoushaManagerCapsuleDto';
// import type GetRiyoushaManagerResultInterface from '../../../dto/riyousha/getRiyoushaManagerResultDto';
// import ViewInputPersonName from '../input_person_name/ViewInputPersonName.vue';
// import InputOrgName from '../input_org_name/InputOrgName.vue';
// import ViewInputAddress from '../input_address/ViewInputAddress.vue';
// import InputAccess from '../input_access/InputAccess.vue';
// import router from '../../../router';
// import RoutePathConstants from '../../../routePathConstants';

// よく使う定数
// const BLANK: string = "";
// const SERVER_STATUS_OK: number = 200;
// const SERVER_STATUS_ERROR: number = 400;

// props,emmits
// const props = defineProps<{ baseEntity: RiyoushaManagerEntityInterface, isEditNew: boolean, userDto: UserPersonLeastInterface }>();

// const inputManagerDto: Ref<RiyoushaManagerDtoInterface> = ref(new RiyoushaManagerDto());
// inputManagerDto.value.isNotOrg = props.baseEntity.isNotOrg;

// watch(props, () => {
//     // リストからの変更
//     onChangeEntity();
// });

// 起動時
// onChangeEntity();

// function onChangeEntity() {
//     if (props.baseEntity.riyoushaManagerId != 0) {
//         const capsuleDto: GetRiyoushaManagerCapsuleInterface = new GetRiyoushaManagerCapsuleDto();
//         capsuleDto.riyoushaManagerEntity = props.baseEntity;
//         capsuleDto.userPersonLeastDto = props.userDto;

//         getAuthorizedPromiseArea().then(token => {
//             const url = "http://localhost:6080/user-riyousha/get-manager";
//             const method = "POST";
//             const body = JSON.stringify(capsuleDto);
//             const headers = {
//                 'Accept': 'application/json',
//                 'Content-Type': 'application/json',
//                 'X-AUTH-TOKEN': 'Bearer ' + token
//             };
//             fetch(url, { method, headers, body })
//                 .then(async (response) => {
//                     const resultDto: GetRiyoushaManagerResultInterface = await response.json();
//                     if (SERVER_STATUS_OK === response.status) {
//                         inputManagerDto.value = resultDto.riyoushaManagerDto;
//                     } else {
//                         alert(resultDto.message);
//                     }
//                 })
//                 .catch((error) => { alert(error); });
//         });
//     }
// }

// const isRecordOrg: Ref<boolean> = ref(false);

// function recieveInputPersonNameInterface(sendDto: InputPersonNameDto) {
//     //inputManagerDto.value.inputPersonNameDto = sendDto;
// }

// function recieveInputOrgNameInterface(sendDto: InputPersonNameDto) {
//     inputManagerDto.value.inputOrgNameDto = sendDto;
// }

// /**
//  *住所編集受信
//  */
// function recieveInputAddressInterface(sendDto: InputAddressDto) {
//     inputManagerDto.value.inputAddressDto = sendDto;
// }

const tempInputPersonName: Ref<InputPersonNameDtoInterface> = ref(new InputPersonNameDto());
const tempAddress: Ref<InputAddressDtoInterface> = ref(new InputAddressDto());
const tempAccess: Ref<InputAccessDtoInterface> = ref(new InputAccessDto());

</script>
<template>

    <h3 class="accent-h3">情報入力</h3><br>

    <!-- TODO 他人が編集する可能性があるので別ページに移転
    <div class="one-line">
        <div class="left-area">
            Bearer
        </div>
        <div class="right-area">
            <input type="url">
        </div>
    </div>
    -->

    <!-- 個人姓名入力 -->
    <ViewInputPersonName :edit-dto="tempInputPersonName"></ViewInputPersonName>

    <!-- 住所入力 -->
    <MockViewInputAddress :edit-dto="tempAddress"></MockViewInputAddress>

    <!-- 連絡先入力 -->
    <ViewInputAccess :edit-dto="tempAccess"></ViewInputAccess>

</template>
<style scoped></style>
