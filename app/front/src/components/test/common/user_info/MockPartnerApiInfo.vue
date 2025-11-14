<script setup lang="ts">
import { ref, type Ref } from 'vue';
import type { LeastUserDtoInterface } from '../../../main/dto/user/leastUserDto';
import { MessageConstants, MessageView } from 'seijishikin-jp-normalize_common-tool';
import router from '../../../../router';
import RoutePathConstants from '../../../../routePathConstants';
import UserRoleConstants from '../../../main/dto/user/userRoleConstants';
import AllMenu from '../../../main/common/menu/AllMenu.vue';
import type { SelectOptionStringDtoInterface } from '../../../main/dto/select_options/selectOptionStringDto';
import { createListRoleOptions } from '../../../main/common/menu/createListRoleOptions';
import PersonMenu from '../../../main/common/menu/PersonMenu.vue';

// よく使う定数
const BLANK: string = "";
const INIT_NUMBER: number = 0;
// const SERVER_STATUS_OK: number = 200;
// const SERVER_STATUS_ERROR: number = 400;
// メッセージ表示定数
const infoLevel: Ref<number> = ref(MessageConstants.LEVEL_NONE);
const messageType: Ref<number> = ref(MessageConstants.VIEW_NONE);
const title: Ref<string> = ref(BLANK);
const message: Ref<string> = ref(BLANK);


// props,emmits
const props = defineProps<{ userDto: LeastUserDtoInterface }>();

const listMenuRoleOptions: Ref<SelectOptionStringDtoInterface[]> = ref(createListRoleOptions(props.userDto.listRoles));

// ログインと権限チェック
if (INIT_NUMBER === props.userDto.userPersonId || !props.userDto.listRoles.includes(UserRoleConstants.ROLE_PARTNER_API)) {
    //alert("必要な権限が存在しません");
    infoLevel.value = MessageConstants.LEVEL_ERROR;
    messageType.value = MessageConstants.VIEW_OK;
    title.value = "ログイン状態またはAPIユーザ権限が確認できませんでした";
    message.value = "ログアウト処理をします。再度ログイン処理をするかシステム担当者にお問い合わせください";
}

const viewMenuRole: Ref<string> = ref(BLANK);
const isVewAllMenu: Ref<boolean> = ref(false);
const isVewPersonMenu: Ref<boolean> = ref(false);

function viewAllMenu() {
    isVewPersonMenu.value = false;
    if (BLANK === viewMenuRole.value) {
        // 非表示
        isVewAllMenu.value = false;
    } else {
        // 表示
        isVewAllMenu.value = true;
    }
}

function recieveCanceelAllMenu() {
    // 非表示
    viewMenuRole.value = BLANK;
    isVewAllMenu.value = false;
}

function recieveCanceelPersonMenu() {
    // 非表示
    isVewPersonMenu.value = false;
}

function viewPersonMenu() {
    // 個人メニュー作成
    isVewPersonMenu.value = true;
    isVewAllMenu.value = false;
}


// メッセージからの反応受け取り
function recieveSubmit(button: string) {
    console.log(button); // 警告除け
    // TODO ボタンタイプ別の挙動はこの中で変える

    // 非表示
    infoLevel.value = 0;
    messageType.value = 0;
    router.push(RoutePathConstants.PAGE_LOGOUT);
}
</script>
<template>
    <!-- ユーザrole別制御コンポーネント -->
    <div class="user-role-container-partner">
        <div class="user-role-content">
            <div class="user-role-title">
                APIユーザ
            </div>
            <!-- 遷移メニュー -->
            <div class="user-role-menu-wrapper">
                <div class="left-space" style="vertical-align: center;">
                    遷移メニュー <select class="left-space" v-model="viewMenuRole" @change="viewAllMenu">
                        <option v-for="dto of listMenuRoleOptions" :key="dto.value" :value="dto.value">{{ dto.text }}
                        </option>
                    </select>&nbsp;
                </div>
            </div>
            <!-- 必要アイコンはここに追加 -->
            <div class="left-space user-role-icon-container" @click="viewPersonMenu">
                <img src="#" class="user-role-icon">
            </div>
        </div>
    </div>

    <!-- メッセージ表示 -->
    <div class="overMessage" v-if="messageType !== MessageConstants.VIEW_NONE">
        <MessageView :info-level="infoLevel" :message-type="messageType" :title="title" :message="message"
            @send-submit="recieveSubmit">
        </MessageView>
    </div>

    <!-- メニュー表示 -->
    <div class="overComponentLayer2" v-if="isVewAllMenu">
        <AllMenu :view-role="viewMenuRole" @send-canceel-menu="recieveCanceelAllMenu"></AllMenu>
    </div>

    <!-- 個人メニュー表示 -->
    <div class="personMenuLayer" v-if="isVewPersonMenu">
        <PersonMenu :view-role="UserRoleConstants.ROLE_ADMIN" @send-canceel-menu="recieveCanceelPersonMenu">
        </PersonMenu>
    </div>

</template>
<style scoped></style>
