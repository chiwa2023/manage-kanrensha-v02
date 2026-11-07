<script setup lang="ts">
import { ref, type Ref } from 'vue';
import { getLoginUser, UserRoleConstants, type LeastUserDtoInterface } from 'seijishikin-jp-normalize_common-tool';
import KanrenshaInfo from '../../common/user_info/KanrenshaInfo.vue';
import KanrenshaCombinePerson from '../../common/kanrensha_edit/KanrenshaCombinePerson.vue';
import KanrenshaCombineKigyouDt from '../../common/kanrensha_edit/KanrenshaCombineKigyouDt.vue';
import KanrenshaCombineSeijitandai from '../../common/kanrensha_edit/KanrenshaCombineSeijitandai.vue';

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

// ユーザ呼び出し
const userDto: Ref<LeastUserDtoInterface> = ref(getLoginUser());

// 仮
// const masterEntityKigyouDt: Ref<KanrenshaKigyouDtMasterEntityInterface> = ref(new KanrenshaKigyouDtMasterEntity());
// const masterEntityPerson: Ref<KanrenshaPersonMasterEntityInterface> = ref(new KanrenshaPersonMasterEntity());
// const masterEntitySeijidantai: Ref<KanrenshaSeijidantaiMasterEntityInterface> = ref(new KanrenshaSeijidantaiMasterEntity());

</script>
<template>

    <!-- 管理者メニュー兼チェック -->
    <kanrensha-info :user-dto="userDto"></kanrensha-info>

    <h1>関連者紐づけ(自分自身)</h1><br>

    <div v-if="UserRoleConstants.KANRENSHA_PERSON == userDto.kanrenshaRole">
        <KanrenshaCombinePerson :user-dto="userDto" :is-edit="false" :kanrensha-code="userDto.kanrenshaCode"></KanrenshaCombinePerson>
    </div>

    <div v-if="UserRoleConstants.KANRENSHA_KIGYOU_DT == userDto.kanrenshaRole">
        <KanrenshaCombineKigyouDt :user-dto="userDto" :is-edit="false" :kanrensha-code="userDto.kanrenshaCode"></KanrenshaCombineKigyouDt>
    </div>

    <div v-if="UserRoleConstants.KANRENSHA_SEIJIDANTAI == userDto.kanrenshaRole">
        <KanrenshaCombineSeijitandai :user-dto="userDto" :is-edit="false" :kanrensha-code="userDto.kanrenshaCode"></KanrenshaCombineSeijitandai>
    </div>

</template>
<style scoped></style>
