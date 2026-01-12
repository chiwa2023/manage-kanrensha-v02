<script setup lang="ts">
import { ref, type Ref } from 'vue';
import RoutePathConstants from '../../../../routePathConstants';
import router from '../../../../router';
import MockAdminInfo from '../../../test/common/user_info/MockAdminInfo.vue';
import type { LeastUserDtoInterface } from 'seijishikin-jp-normalize_common-tool/dist/components/main/dto/user/leastUserDto';
import { getLoginUser } from '../../utils/getLoginUser';
import type { UserPersonEntityInterface } from '../../entity/userPersonEntity';
import mockGetUserList from '../../../test/pages/user/mock/mockGetUserList';
import UserDetailEdit from '../../common/user/UserDetailEdit.vue';
import { SearchUserCapsuleDto, type SearchUserCapsuleDtoInterface } from '../../dto/user/searchUserCapsuleDto';
import UserRoleConstants from '../../dto/user/userRoleConstants';

// back側アクセス
const urlBack: string = RoutePathConstants.DOMAIN + RoutePathConstants.BASE_PATH;

// ユーザ呼び出し
const userDto: Ref<LeastUserDtoInterface> = ref(getLoginUser());

// 検索
const listEntity: Ref<UserPersonEntityInterface[]> = ref([]);
const capsuleDto: Ref<SearchUserCapsuleDtoInterface> = ref(new SearchUserCapsuleDto());
const hasConditionManager: Ref<boolean> = ref(true);
const hasConditionPartnerApi: Ref<boolean> = ref(true);
const hasConditionPerson: Ref<boolean> = ref(true);
const hasConditionKigyouDt: Ref<boolean> = ref(true);
const hasConditionSeijidantai: Ref<boolean> = ref(true);
function onSearch() {

    // 検索条件の設定
    capsuleDto.value.listRole.splice(0);
    if (hasConditionManager) {
        capsuleDto.value.listRole.push(UserRoleConstants.MANAGER);
    }
    if (hasConditionPartnerApi) {
        capsuleDto.value.listRole.push(UserRoleConstants.PARTNER_API);
    }
    if (hasConditionPerson) {
        capsuleDto.value.listRole.push(UserRoleConstants.KANRENSHA_PERSON);
    }
    if (hasConditionKigyouDt) {
        capsuleDto.value.listRole.push(UserRoleConstants.KANRENSHA_KIGYOU_DT);
    }
    if (hasConditionSeijidantai) {
        capsuleDto.value.listRole.push(UserRoleConstants.KANRENSHA_SEIJIDANTAI);
    }

    // TODO 検索条件を確定したら検索



    listEntity.value = mockGetUserList();
}








const isViewEdit: Ref<boolean> = ref(false);
const isViewDelete: Ref<boolean> = ref(false);

function onEdit(selectedId: number) {
    selectedUserId.value = selectedId;
    isViewEdit.value = true;
}

function onDelete(selectedId: number) {

    alert("削除" + selectedId);

    // TODO アラート

    // getAuthorizedPromiseArea().then(token => {
    //     if (token !== "") {
    //         // TODO 選択されたUserEntityを最小限ユーザに変換して削除対象、操作者はメニューから取得する
    //         const capsuleDto: Ref<FrameworkCapsuleInterface> = ref(new FrameworkCapsuleDto());
    //         capsuleDto.value.userPersonLeastDto = userLeastDto.value;

    //         const url = urlBack + "/";
    //         const method = "POST";
    //         const body = JSON.stringify(null);
    //         const headers = {
    //             'Accept': 'application/json',
    //             'Content-Type': 'application/json',
    //             'X-AUTH-TOKEN': 'Bearer ' + token
    //         };
    //         fetch(url, { method, headers, body })
    //             .then(async (response) => {
    //                 // 結果を受け取ってメッセージ表示
    //                 const resultDto: FrameworkResultInterface = await response.json();
    //                 alert(resultDto.message);

    //             })
    //             .catch((e) => { alert(e); });
    //     } else {
    //         alert("エラーのつもり");
    //     }
    // });

}

// ユーザ検索と選択
const selectedUserId: Ref<number> = ref(0);


function recieveCancelEditUser() {
    isViewEdit.value = false;
}

function recieveEditUserInterface() {
    isViewEdit.value = false;
}




function onCancel() {
    router.push(RoutePathConstants.PAGE_LOGIN);
}
</script>
<template>

    <!-- ユーザメニュー兼チェック -->
    <MockAdminInfo :user-dto="userDto"></MockAdminInfo>
    <hr>

    <h1>ユーザ変更</h1>

    <h3>ユーザ検索条件</h3>
    <div class="one-line">
        <div class="left-area">
            ユーザ名
        </div>
        <div class="right-area">
            <input type="text">
        </div>
    </div>

    <div class="one-line">
        <div class="left-area">
            ユーザ権限
        </div>
        <div class="right-area">
            <div class="form-group-vertical">
                <span>
                    <input type="checkbox" v-model="hasConditionManager">運営者
                    <input type="checkbox" v-model="hasConditionPartnerApi" class="left-space">APIユーザ
                </span>
                <span>

                    <input type="checkbox" v-model="hasConditionPerson">関連者個人
                    <input type="checkbox" v-model="hasConditionKigyouDt" class="left-space">関連者企業／団体
                    <input type="checkbox" v-model="hasConditionSeijidantai" class="left-space">政治団体
                </span>
            </div>
        </div>
    </div>

    <div class="one-line">
        <div class="left-area">
            検索
        </div>
        <div class="right-area">
            <button @click="onSearch">検索</button>
        </div>
    </div>

    <h3>ユーザ検索結果</h3>
    <!-- 選択されたユーザを編集 -->
    <div class="one-line">
        <table>
            <tbody>
                <tr>
                    <th>コード</th>
                    <th>名前</th>
                    <th>&nbsp;</th>
                    <th>&nbsp;</th>
                </tr>
                <tr v-for="entity of listEntity" :key="entity.userPersonId">
                    <td>{{ entity.userPersonCode }}</td>
                    <td>{{ entity.userPersonName }}</td>
                    <td><button @click="onEdit(entity.userPersonId)">編集</button></td>
                    <td><button @click="onDelete(entity.userPersonId)">削除</button></td>
                </tr>
            </tbody>
        </table>
    </div>
    <div class="footer">
        <button @click="onCancel" class="footer-button">キャンセル</button>
    </div>


    <!-- メニュー表示 -->
    <div v-if="isViewEdit" class="overComponent">
        <UserDetailEdit :user-dto="userDto" :edit-user-id="selectedUserId"
            @send-cancel-edit-user="recieveCancelEditUser" @send-edit-user-interface="recieveEditUserInterface">
        </UserDetailEdit>
    </div>



</template>
<style scoped>
table {
    border-style: solid;
    border-width: 1px;
}

td {
    border-style: solid;
    border-width: 1px;
}

th {
    border-style: solid;
    border-width: 1px;
}
</style>
