<script setup lang="ts">
// import router from '../../../router';
// import type SelectOptionStringInterface from '../../../dto/selectOptionStringDto';
// import mockGetRoleMenuOpions from '../../../dto/user/mock/mockGetRoleMenuOpions';

// 常にどのメニューにも移行できるMock
// const listRouter: Ref<SelectOptionStringInterface[]> = ref(mockGetRoleMenuOpions());

// function onInfo() {
//     alert("押した");
//     // TODO 簡易ユーザ情報を表示する
// }

// // 役割異動メニュー
// const moveRole: Ref<string> = ref("");
// function onMoveRole() {
//     const route: string = listRouter.value.filter((e) => { return e.text === moveRole.value })[0].value;
//     router.push(route);
// }
// const isMoveMenu:ComputedRef<boolean> = computed(() => listRouter.value.length>0);

</script>
<template>
        <!-- ユーザrole別制御コンポーネント -->
        <div class="user-role-container">
            <div class="user-role-content">
                <div class="user-role-title">
                    SE権限
                </div>
                <!-- 遷移メニュー -->
                <div class="user-role-menu-wrapper">
                    <div class="left-space">
                        遷移メニュー
                    </div>
                </div>
                <div class="user-role-menu-wrapper">
                    <!-- 必要アイコンはここに追加 -->
                    <div class="left-space user-role-icon-container">
                        <img src="#" class="user-role-icon">
                    </div>
                </div>
            </div>
        </div>
</template>
<style scoped></style>
