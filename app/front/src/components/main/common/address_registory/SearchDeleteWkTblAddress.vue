<script setup lang="ts">
import { computed, ref, type ComputedRef, type Ref } from 'vue';
import type { WkTblAddressRsdtDeleteEntityInterface } from '../../entity/wkTblAddressRsdtDeleteEntity';
import { getErrorUniqueIdMessage, MessageConstants, MessageView } from 'seijishikin-jp-normalize_common-tool';

// props,emit
const props = defineProps<{ listEntity: WkTblAddressRsdtDeleteEntityInterface[], isEdit: boolean }>();
const emits = defineEmits(["sendEdit", "sendDelete"]);

// よく使う定数
const BLANK: string = "";
// const INIT_NUMBER: number = 0;
// const SERVER_STATUS_OK: number = 200;
// const SERVER_STATUS_ERROR: number = 400;
// const SEARCH_LIMIT: number = 20;
const MESS_PAGE_NAME: string = "関連者個人編集";
const INIT_CALLER: string = "no branch";

// メッセージボックス表示定数
const infoLevel: Ref<number> = ref(MessageConstants.LEVEL_NONE);
const messageType: Ref<number> = ref(MessageConstants.VIEW_NONE);
const caller: Ref<string> = ref(INIT_CALLER);
const message: Ref<string> = ref(BLANK);

const listEdit: ComputedRef<WkTblAddressRsdtDeleteEntityInterface[]> = computed(() => props.listEntity);

function onDelete(selectedId: number) {
    const entity: WkTblAddressRsdtDeleteEntityInterface | undefined
        = listEdit.value.filter((e) => selectedId === e.wkTblAddressRsdtDeleteId)[0];
    if (undefined !== entity) {
        emits("sendDelete", entity);
    } else {
        infoLevel.value = MessageConstants.LEVEL_ERROR;
        messageType.value = MessageConstants.VIEW_OK;
        message.value = getErrorUniqueIdMessage(selectedId);
        return;
    }
}
function recieveSubmit() {
    infoLevel.value = 0;
    messageType.value = 0;
}
</script>
<template>
    <table>
        <tbody>
            <tr>
                <th>地方自治体コード</th>
                <th>郵便番号</th>
                <th>番地まで住所</th>
                <th>建物住所</th>
                <th v-if="props.isEdit">&nbsp;</th>
            </tr>
            <tr v-for="entity in listEdit">
                <td>{{ entity.lgCode }}</td>
                <td>{{ entity.postalcode1 }}{{ entity.postalcode2 }}</td>
                <td>{{ entity.addressBlock }}</td>
                <td>{{ entity.addressBuilding }}</td>
                <td v-if="props.isEdit"><button @click="onDelete(entity.wkTblAddressRsdtDeleteId)"
                        :disabled="!entity.isLatest">削除</button></td>
            </tr>
        </tbody>
    </table>

    <!-- メッセージ表示    -->
    <div class="overMessage" v-if="messageType !== MessageConstants.VIEW_NONE">
        <MessageView :info-level="infoLevel" :message-type="messageType" :title="MESS_PAGE_NAME" :message="message"
            :caller="caller" @send-submit="recieveSubmit">
        </MessageView>
    </div>

</template>
<style scoped></style>
