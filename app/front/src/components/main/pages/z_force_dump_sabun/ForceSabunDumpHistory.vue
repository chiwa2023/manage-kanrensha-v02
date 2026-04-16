<script setup lang="ts">
import { ref, watch, type Ref } from 'vue';
import type { LeastUserDtoInterface } from '../../dto/user/leastUserDto';
import { getLoginUser } from '../../utils/getLoginUser';
import { ForceDumpCapsuleDto, type ForceDumpCapsuleDtoInterface } from '../../dto/z_force_dump/forceDumpCapsuleDto';
import MockAdminInfo from '../../../test/common/user_info/MockAdminInfo.vue';

// back側アクセス
//const urlBack: string = RoutePathConstants.DOMAIN_BACK + RoutePathConstants.PATH_BACK;

// ユーザ呼び出し
const userDto: Ref<LeastUserDtoInterface> = ref(getLoginUser());

// 実行条件
const capsuleDto: Ref<ForceDumpCapsuleDtoInterface> = ref(new ForceDumpCapsuleDto());

const isExecuteAll: Ref<boolean> = ref(true);

const isDisabledKigyouDt: Ref<boolean> = ref(true);
const isDisabledPerson: Ref<boolean> = ref(true);
const isDisabledSeijidantai: Ref<boolean> = ref(true);
const now: Date = new Date();
const dateStart: Ref<string> = ref(now.getFullYear() + "-01-01");
const dateEnd: Ref<string> = ref(now.getFullYear() + "-03-31");

// 関連者選択制御
watch(isExecuteAll, () => {
    if (isExecuteAll.value) {
        capsuleDto.value.isExecuteKigyouDt = true;
        capsuleDto.value.isExecutePerson = true;
        capsuleDto.value.isExecuteSeijidantai = true;
        isDisabledKigyouDt.value = true;
        isDisabledPerson.value = true;
        isDisabledSeijidantai.value = true;
    } else {
        isDisabledKigyouDt.value = false;
        isDisabledPerson.value = false;
        isDisabledSeijidantai.value = false;
    }
});

function onCancel() {
    history.back();
}
function onSave() {
    alert("実行");
    // // 処理条件再設定なしでそのまま
    // getAuthorizedPromiseArea().then(token => {
    //     const url = urlBack + "/dump-history-sabun/execute";
    //     const method = "POST";
    //     const body = JSON.stringify(capsuleDto.value);
    //     const headers = {
    //         'Accept': 'application/json',
    //         'Content-Type': 'application/json',
    //         'X-AUTH-TOKEN': 'Bearer ' + token
    //     };
    //     fetch(url, { method, headers, body })
    //         .then(async (response) => {
    //             const resultDto: FrameworkResultInterface = await response.json();
    //             alert(resultDto.message);
    //         })
    //         .catch((error) => { alert(error); });
    // });

}
</script>
<template>
    <!-- SE権限 -->
    <MockAdminInfo :user-dto="userDto"></MockAdminInfo>
    
    <h1>関連者履歴差分強制csvダンプ</h1>


    <div class="one-line">

        <div class="left-area">
            全実施
        </div>
        <div class="right-area">
            <input type="checkbox" v-model="isExecuteAll">全実施
        </div>
    </div>

    <div class="one-line">
        <div class="left-area">
            関連者個別選択
        </div>
        <div class="right-area">
            <input type="checkbox" v-model="capsuleDto.isExecuteKigyouDt" :disabled=isDisabledKigyouDt>関連者企業・団体
            <span class="left-space"><input type="checkbox" v-model="capsuleDto.isExecutePerson"
                    :disabled=isDisabledPerson>関連者個人</span>
            <span class="left-space"><input type="checkbox" v-model="capsuleDto.isExecuteSeijidantai"
                    :disabled=isDisabledSeijidantai>関連者政治団体</span>
        </div>
    </div>

    <div class="one-line">
        <div class="left-area">
            指定期間
        </div>
        <div class="right-area">
            <input type="date" v-model="dateStart" /><span class="left-space">から</span>
            <span class="left-space"><input type="date" v-model="dateEnd" /></span><span class="left-space">まで</span>
        </div>
    </div>

    <div class="footer">
        <button @click="onCancel" class="footer-button">キャンセル</button>
        <button @click="onSave" class="footer-button left-space">送信</button>
    </div>
</template>
<style scoped></style>
