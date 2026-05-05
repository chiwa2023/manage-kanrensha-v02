<script setup lang="ts">
import { ref, toRaw, type Ref } from 'vue';
import KanrenshaInfo from '../../common/user_info/KanrenshaInfo.vue';
import type { LeastUserDtoInterface } from 'seijishikin-jp-normalize_common-tool';
import { getLoginUser } from '../../utils/getLoginUser';
// import PartnerCorpEdit from '../../common/partner_corp_edit/PartnerCorpEdit.vue';
// import PartnerPersonEdit from '../../common/partner_person_edit/PartnerPersonEdit.vue';
// import PartnerPoliOrgEdit from '../../common/partner_poli_org_edit/PartnerPoliOrgEdit.vue';
// import type PersonNoInterface from '../../../dto/partner_person/personNoDto';
// import PersonNoDto from '../../../dto/partner_person/personNoDto';
// import type PoliOrgNoInterface from '../../../dto/partner_poli_org/poliOrgNoDto';
// import PoliOrgNoDto from '../../../dto/partner_poli_org/poliOrgNoDto';
// import CorpNoInterface from '../../../dto/partner_corp/corpNoDto';
// import CorpNoDto from '../../../dto/partner_corp/corpNoDto';
// import PartnerInfo from '../../common/user_info/PartnerInfo.vue';
// import UserPersonLeastDto from './../../../dto/user/userPersonLeastDto';
// import type UserPersonLeastInterface from './../../../dto/user/userPersonLeastDto';
// import UserRoleConstants from '../../../dto/user/userRoleConstants';
// import KanrenshaKbnConstants from '../../../dto/kanrensha/kanrenshaKbnConstants';
// import type GetKanrenshaAllByUserResultInterface from '../../../dto/kanrensha/getKanrenshaAllByUserResultDto';
// import GetKanrenshaAllByUserResultDto from '../../../dto/kanrensha/getKanrenshaAllByUserResultDto';
// import type FrameworkCapsuleInterface from '../../../dto/frameworkCapsuleDto';
// import FrameworkCapsuleDto from '../../../dto/frameworkCapsuleDto';
// import getAuthorizedPromiseArea from '../../../dto/login/getAuthorizedPromiseArea';
// import type SelectOptionNumberInterface from '../../../dto/selectOptionNumberDto';
// import SelectOptionNumberDto from '../../../dto/selectOptionNumberDto';
// import type MasterCorporationInterface from '../../../entity/masterCorporationEntity';
// import type MasterPoliticalOrganizationInterface from '../../../entity/masterPoliticalOrganizationEntity';
// import type GetKanrenshaCorpCapsuleInterface from '../../../dto/kanrensha/getKanrenshaCorpCapsuleDto';
// import type GetKanrenshaPoliOrgCapsuleInterface from '../../../dto/kanrensha/getKanrenshaPoliOrgCapsuleDto';
// import type GetKanrenshaPoliOrgResultInterface from '../../../dto/kanrensha/getKanrenshaPoliOrgResultDto';
// import type GetKanrenshaCorpResultInterface from '../../../dto/kanrensha/getKanrenshaCorpResultDto';
// import type GetKanrenshaPersonResultInterface from '../../../dto/kanrensha/getKanrenshaPersonResultDto';
// import GetKanrenshaCorpCapsuleDto from '../../../dto/kanrensha/getKanrenshaCorpCapsuleDto';
// import GetKanrenshaPoliOrgCapsuleDto from '../../../dto/kanrensha/getKanrenshaPoliOrgCapsuleDto';
// import GetKanrenshaPersonCapsuleDto from '../../../dto/kanrensha/getKanrenshaPersonCapsuleDto';
// import type GetKanrenshaPersonCapsuleInterface from '../../../dto/kanrensha/getKanrenshaPersonCapsuleDto';
// import type MasterPersonInterface from '../../../entity/masterPersonEntity';
// import RoutePathConstants from '../../../routePathConstants';

// // back側アクセス
// const urlBack: string = RoutePathConstants.DOMAIN_BACK + RoutePathConstants.PATH_BACK;

// // よく使う定数
// const BLANK: string = "";
// const SERVER_STATUS_OK: number = 200;
// // const SERVER_STATUS_ERROR: number = 400;

// ユーザ呼び出し
const userDto: Ref<LeastUserDtoInterface> = ref(getLoginUser());

// const inputPersonDto: Ref<PersonNoInterface> = ref(new PersonNoDto());
// const inputPoliOrgDto: Ref<PoliOrgNoInterface> = ref(new PoliOrgNoDto());
// const inputCorpNoDto: Ref<CorpNoInterface> = ref(new CorpNoDto());

// const resultDto: Ref<GetKanrenshaAllByUserResultInterface> = ref(new GetKanrenshaAllByUserResultDto());
// const viewStatus: Ref<number> = ref(0);

// const listCorpEntity: Ref<SelectOptionNumberInterface[]> = ref([]);
// const listPoliOrgEntity: Ref<SelectOptionNumberInterface[]> = ref([]);

// const corpSelectDisiable: Ref<boolean> = ref(false);
// const poliOrgSelectDisiable: Ref<boolean> = ref(false);


// const personRadioDisiable: Ref<boolean> = ref(false);
// const corpRadioDisiable: Ref<boolean> = ref(false);
// const poliOrgRadioDisiable: Ref<boolean> = ref(false);


// const viewCorp: Ref<number> = ref(0);
// const viewPoliOrg: Ref<number> = ref(0);

// getAuthorizedPromiseArea().then(token => {
//     const capsuleDto: Ref<FrameworkCapsuleInterface> = ref(new FrameworkCapsuleDto());
//     capsuleDto.value.userPersonLeastDto = userDto.value;
//     if (token !== BLANK) {
//         // 取得処理
//         const method = "POST";
//         const url: string = urlBack + "/user-kanrensha/get-by-user";
//         const body = JSON.stringify(capsuleDto.value);
//         const headers = {
//             'Accept': 'application/json',
//             'Content-Type': 'application/json',
//             'X-AUTH-TOKEN': 'Bearer ' + token
//         };
//         fetch(url, { method, headers, body })
//             .then(async (response) => {
//                 // 結果を受け取ってメッセージ表示
//                 resultDto.value = await response.json();
//                 if (SERVER_STATUS_OK === response.status) {

//                     // 関連者個人である場合は最優先で表示
//                     if (resultDto.value.masterPersonEntity.masterPersonId !== 0) {
//                         if (userDto.value.listRoles.includes(UserRoleConstants.ROLE_PARTNER_PERSON)) {
//                             viewStatus.value = KanrenshaKbnConstants.PERSON;
//                             // 個人取得処理
//                             recievePersonNoInterface(resultDto.value.masterPersonEntity);

//                         }

//                     } else {
//                         // 個人マスタが取得されていない場合は選択不可
//                         //personRadioDisiable.value = true;
//                     }

//                     // 企業団体で1項目でも取得できていたらリスト作成
//                     let size = resultDto.value.listCorpEntity.length;
//                     if (size > 0) {
//                         createCorpSelect(resultDto.value.listCorpEntity);
//                         if (size === 1 && viewStatus.value === 0) {
//                             viewStatus.value = KanrenshaKbnConstants.CORP;
//                             // 企業団体取得処理
//                             viewCorp.value = listCorpEntity.value[0].value;
//                             recieveCorpCoInterface(resultDto.value.listCorpEntity[0]);
//                             poliOrgSelectDisiable.value = true;
//                         }
//                     } else {
//                         // 企業団体が取得できていない場合は選択不可
//                         corpSelectDisiable.value = true;
//                         //corpRadioDisiable.value = true;
//                     }

//                     // 政治団体で1項目でも取得できていたらリスト作成
//                     size = resultDto.value.listPoliOrgEntity.length;
//                     if (size > 0) {
//                         createPoliOrgSelect(resultDto.value.listPoliOrgEntity);
//                         if (size === 1 && viewStatus.value === 0) {
//                             viewStatus.value = KanrenshaKbnConstants.POLI_ORG;
//                             // 政治団体取得処理
//                             viewPoliOrg.value = listPoliOrgEntity.value[0].value;
//                             recievePoliOrgNoInterface(resultDto.value.listPoliOrgEntity[0]);
//                             poliOrgSelectDisiable.value = true;
//                         }
//                     } else {
//                         // 政治団体が取得できていない場合は取得不可
//                         poliOrgSelectDisiable.value = true;
//                         poliOrgRadioDisiable.value = true;
//                     }
//                 } else {
//                     alert(resultDto.value.message);
//                 }
//             })
//             .catch((e) => { alert(e); });
//     } else {
//         alert("エラーのつもり");
//     }
// });


// function createCorpSelect(listEntity: MasterCorporationInterface[]) {
//     listCorpEntity.value.splice(0);

//     for (const entity of listEntity) {
//         const dto: SelectOptionNumberInterface = new SelectOptionNumberDto();
//         dto.value = entity.masterCorporationId;
//         dto.text = entity.partnerName;
//         listCorpEntity.value.push(dto);
//     }

// }

// function createPoliOrgSelect(listEntity: MasterPoliticalOrganizationInterface[]) {
//     listPoliOrgEntity.value.splice(0);

//     for (const entity of listEntity) {
//         const dto: SelectOptionNumberInterface = new SelectOptionNumberDto();
//         dto.value = entity.masterPoliticalOrganizationId;
//         dto.text = entity.partnerName;
//         listPoliOrgEntity.value.push(dto);
//     }

// }


// /** 選択された関連者個人を受信する */
// function recievePersonNoInterface(sendDto: MasterPersonInterface) {

//     getAuthorizedPromiseArea().then(token => {
//         const capsuleDto: Ref<GetKanrenshaPersonCapsuleInterface> = ref(new GetKanrenshaPersonCapsuleDto());
//         capsuleDto.value.masterPersonEntity = sendDto;
//         if (token !== BLANK) {
//             // 保存処理
//             const method = "POST";
//             const url: string = urlBack + "/user-kanrensha/get-person";
//             const body = JSON.stringify(capsuleDto.value);
//             const headers = {
//                 'Accept': 'application/json',
//                 'Content-Type': 'application/json',
//                 'X-AUTH-TOKEN': 'Bearer ' + token
//             };
//             fetch(url, { method, headers, body })
//                 .then(async (response) => {
//                     // 結果を受け取ってメッセージ表示
//                     const resultDto: GetKanrenshaPersonResultInterface = await response.json();
//                     if (SERVER_STATUS_OK === response.status) {
//                         inputPersonDto.value = structuredClone(toRaw(resultDto.kanrenshaPersonDto));
//                         // isEditNew.value = false;
//                     } else {
//                         alert(resultDto.message);
//                     }
//                 })
//                 .catch((e) => { alert(e); });
//         } else {
//             alert("エラーのつもり");
//         }
//     });
// }

// /** 選択された関連者政治団体を受信する */
// function recievePoliOrgNoInterface(sendDto: MasterPoliticalOrganizationInterface) {
//     //inputPoliOrgDto.value = structuredClone(toRaw(sendDto));
//     getAuthorizedPromiseArea().then(token => {
//         const capsuleDto: Ref<GetKanrenshaPoliOrgCapsuleInterface> = ref(new GetKanrenshaPoliOrgCapsuleDto());
//         capsuleDto.value.masterPoliticalOrganizationEntity = sendDto;
//         if (token !== BLANK) {
//             // 保存処理
//             const method = "POST";
//             const url: string = urlBack + "/user-kanrensha/get-poli-org";
//             const body = JSON.stringify(capsuleDto.value);
//             const headers = {
//                 'Accept': 'application/json',
//                 'Content-Type': 'application/json',
//                 'X-AUTH-TOKEN': 'Bearer ' + token
//             };
//             fetch(url, { method, headers, body })
//                 .then(async (response) => {
//                     // 結果を受け取ってメッセージ表示
//                     const resultDto: GetKanrenshaPoliOrgResultInterface = await response.json();
//                     if (SERVER_STATUS_OK === response.status) {
//                         inputPoliOrgDto.value = structuredClone(toRaw(resultDto.kanrenshaPoliOrgDto));
//                         // isEditNew.value = false;
//                     } else {
//                         alert(resultDto.message);
//                     }
//                 })
//                 .catch((e) => { alert(e); });
//         } else {
//             alert("エラーのつもり");
//         }
//     });
// }

// /** 選択された関連者企業／団体を受信する */
// function recieveCorpCoInterface(sendDto: MasterCorporationInterface) {
//     // TODO Backに接続しEntityからDtoを取得する
//     //inputCorpNoDto.value = structuredClone(toRaw(sendDto));

//     getAuthorizedPromiseArea().then(token => {
//         const capsuleDto: Ref<GetKanrenshaCorpCapsuleInterface> = ref(new GetKanrenshaCorpCapsuleDto());
//         capsuleDto.value.masterCorporationEntity = sendDto;
//         if (token !== BLANK) {
//             // 保存処理
//             const method = "POST";
//             const url: string = urlBack + "/user-kanrensha/get-corp";
//             const body = JSON.stringify(capsuleDto.value);
//             const headers = {
//                 'Accept': 'application/json',
//                 'Content-Type': 'application/json',
//                 'X-AUTH-TOKEN': 'Bearer ' + token
//             };
//             fetch(url, { method, headers, body })
//                 .then(async (response) => {
//                     // 結果を受け取ってメッセージ表示
//                     const resultDto: GetKanrenshaCorpResultInterface = await response.json();
//                     if (SERVER_STATUS_OK === response.status) {
//                         inputCorpNoDto.value = structuredClone(toRaw(resultDto.kanrenshaCorpDto));
//                         // isEditNew.value = false;
//                     } else {
//                         alert(resultDto.message);
//                     }
//                 })
//                 .catch((e) => { alert(e); });
//         } else {
//             alert("エラーのつもり");
//         }
//     });

// }

// function onChangenCorpView() {
//     // 政治団体取得処理
//     const list = resultDto.value.listCorpEntity.filter((e) => viewPoliOrg.value === e.masterCorporationId);

//     if (list.length === 0) {
//         // リスト作成に失敗していない限り起きない例外
//     } else {
//         // 企業団体取得処理
//         viewStatus.value = KanrenshaKbnConstants.CORP;
//         recieveCorpCoInterface(list[0]);
//     }



// }

// function onChangenPoliOrgView() {
//     const list = resultDto.value.listPoliOrgEntity.filter((e) => viewPoliOrg.value === e.masterPoliticalOrganizationId);
//     if (list.length === 0) {
//         // リスト作成に失敗していない限り起きない例外
//     } else {
//         // 政治団体取得処理
//         viewStatus.value = KanrenshaKbnConstants.POLI_ORG;
//         recievePoliOrgNoInterface(list[0]);
//     }
// }


// 新規追加は許可しない
const isNew: boolean = false;
// ユーザと関連者の紐づけはしない
const isCombineUser: boolean = false;

</script>
<template>
    <!-- 関連者メニュー兼チェック -->
    <KanrenshaInfo :user-dto="userDto"></KanrenshaInfo>
    
    <h1>関連者編集</h1>

    <div class="left-area">
        関連者区分
    </div>
    <div class="right-area">
        <!-- 
        <span><input type="radio" id="editSelect" v-model="viewStatus" value="1"
                :disabled="personRadioDisiable">1.個人</span>
        <span class="left-space"><input type="radio" id="editSelect" v-model="viewStatus" value="2"
                :disabled="corpRadioDisiable">2.企業／団体</span>
        <select class="left-space" v-model="viewCorp" :disabled="corpSelectDisiable" @change="onChangenCorpView">
            <option v-for="dto of listCorpEntity" :value="dto.value">{{ dto.text }}</option>
        </select>
        <span class="left-space"><input type="radio" id="editSelect" v-model="viewStatus" value="3"
                :disabled="poliOrgRadioDisiable">3.政治団体</span><select class="left-space" v-model="viewPoliOrg"
            :disabled="poliOrgSelectDisiable" @change="onChangenPoliOrgView">
            <option v-for="dto of listPoliOrgEntity" :value="dto.value">{{ dto.text }}</option>
        </select>
        -->
    </div>
    <div class="clear-both"></div>

    <!-- 編集対象が法人／個人
    <div v-if="viewStatus == KanrenshaKbnConstants.PERSON">
        <PartnerPersonEdit :edit-dto="inputPersonDto" :is-edit-new="isNew" :is-combine-user="isCombineUser"
            :user-dto="userLeastDto"></PartnerPersonEdit>
    </div>
     -->
    
    <!-- 編集対象が法人／団体
    <div v-if="viewStatus == KanrenshaKbnConstants.CORP">
        <PartnerCorpEdit :edit-dto="inputCorpNoDto" :is-edit-new="isNew" :is-combine-user="isCombineUser"
            :user-dto="userLeastDto"></PartnerCorpEdit>
    </div>
-->
    <!-- 編集対象が政治団体 
         <div v-if="viewStatus == KanrenshaKbnConstants.POLI_ORG">
        <PartnerPoliOrgEdit :edit-dto="inputPoliOrgDto" :is-edit-new="isNew" :is-combine-user="isCombineUser"
            :user-dto="userLeastDto">
        </PartnerPoliOrgEdit>
    </div>
 -->
</template>
<style scoped></style>
