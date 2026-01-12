<script setup lang="ts">
import { ref, type Ref } from 'vue';
import RoutePathConstants from '../../../../routePathConstants';
import type { LeastUserDtoInterface } from '../../dto/user/leastUserDto';
import { getLoginUser } from '../../utils/getLoginUser';
import router from '../../../../router';
import MockAllUserInfo from '../../../test/common/user_info/MockAllUserInfo.vue';
import UserDetailEdit from '../../common/user/UserDetailEdit.vue';

// back側アクセス
const urlBack: string = RoutePathConstants.DOMAIN + RoutePathConstants.BASE_PATH;

// ユーザ呼び出し
const userDto: Ref<LeastUserDtoInterface> = ref(getLoginUser());

function onCancel() {
    router.push(RoutePathConstants.PAGE_LOGIN);
}
function onSave() {
    // getAuthorizedPromiseArea().then(token => {
    //     const capsuleDto: Ref<FrameworkCapsuleInterface> = ref(new FrameworkCapsuleDto());
    //     capsuleDto.value.userPersonLeastDto = userLeastDto.value;
    //     if (token !== "") {
    //         // パスワード更新
    //         const url = urlBack + "/";
    //         const method = "POST";
    //         const body = JSON.stringify(capsuleDto.value);
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

</script>
<template>

    <!-- ユーザメニュー兼チェック -->
    <MockAllUserInfo :user-dto="userDto"></MockAllUserInfo>

    <h1>ユーザ編集</h1>

    <UserDetailEdit :user-dto="userDto" :edit-user-id="userDto.userPersonId"></UserDetailEdit>

    <!-- 
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
            利用者権限
        </div>
        <div class="right-area">
            <input type="checkbox">運営者権限
            <input type="checkbox" class="left-space">APIユーザ
        </div>
    </div>

    <div class="one-line">
        <div class="left-area">
            関連者
        </div>
        <div class="right-area">
            <input type="radio">なし
            <input type="radio" class="left-space">個人
            <input type="radio" class="left-space">企業／団体
            <input type="radio" class="left-space">政治団体
        </div>
    </div>

    <div class="footer">
        <button @click="onCancel" class="footer-button">キャンセル</button>
        <button @click="onSave" class="footer-button left-space">保存</button>
    </div>
    -->

</template>
<style scoped></style>
