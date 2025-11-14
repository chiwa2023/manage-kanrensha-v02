<script setup lang="ts">
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

function onCancel() {
    // TODO 管理者など上位権限で下位権限コンポーネントを使用している場合の処理
    alert("キャンセル");
    //router.push(RoutePathConstants.PAGE_MENU_MANAGER);
}

function onSave() {

    alert("保存");

    //     const capsuleDto: SaveRiyoushaManagerCapsuleInterface = new SaveRiyoushaManagerCapsuleDto();
    //     capsuleDto.riyoushaManagerDto = inputManagerDto.value;
    //     capsuleDto.userPersonLeastDto = props.userDto;

    //     getAuthorizedPromiseArea().then(token => {
    //         const url = "http://localhost:6080/user-riyousha/save-manager";
    //         const method = "POST";
    //         const body = JSON.stringify(capsuleDto);
    //         const headers = {
    //             'Accept': 'application/json',
    //             'Content-Type': 'application/json',
    //             'X-AUTH-TOKEN': 'Bearer ' + token
    //         };
    //         fetch(url, { method, headers, body })
    //             .then(async (response) => {
    //                 const resultDto: FrameworkMessageAndResultInterface = await response.json();
    //                 alert(resultDto.message);
    //                 if (isRecordOrg.value) {
    //                     // 組織登録 
    //                     router.push(RoutePathConstants.PAGE_COMBINE_MANAGER);
    //                 } else {
    //                     // 引き続き組織を登録しない場合はメニューに戻る
    //                     router.push(RoutePathConstants.PAGE_MENU_MANAGER);
    //                 }
    //             })
    //             .catch((error) => { alert(error); });
    //     });
}

</script>
<template>
    <h1>利用者運営者編集</h1>

    <!-- 
    <div class="left-area">
        個人／団体
    </div>
    <div class="right-area">
        <span><input type="radio" v-model="inputManagerDto.isNotOrg" :value="true" :disabled="true">個人</span>
        <span class="left-space"><input type="radio" v-model="inputManagerDto.isNotOrg" :value="false" :disabled="true">
            団体</span>
        <div v-if="props.isEditNew">
            <br>
            <input v-model="isRecordOrg" type="checkbox">次のページで自分の所属団体を別に登録したい場合はチェックを入れる
        </div>
    </div>
    <div class="clear-both"><br></div>
    -->

    <!-- 名称
    <div v-if="inputManagerDto.isNotOrg">
    </div>
    <div v-else>
         団体名称入力
        <InputOrgName :edit-dto="inputManagerDto.inputOrgNameDto" :is-raise-edit-view="true"
            @send-input-person-name-interface="recieveInputOrgNameInterface"></InputOrgName>
    </div>
    <div class="clear-both"><br></div>
 -->

    <hr>

    <div class="footer">
        <button @click="onCancel" class="footer-button">キャンセル</button>
        <button @click="onSave" class="footer-button left-space">送信</button>
    </div>

</template>
<style scoped></style>
