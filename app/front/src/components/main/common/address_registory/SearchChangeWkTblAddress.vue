<script setup lang="ts">
import { computed, type ComputedRef } from 'vue';
import type { WkTblAddressRsdtChangeEntityInterface } from '../../entity/wkTblAddressRsdtChangeEntity';

// props,emit
const props = defineProps<{ listEntity: WkTblAddressRsdtChangeEntityInterface[], isEdit: boolean }>();
const emits = defineEmits(["sendEdit", "sendDelete"]);

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

const listEdit: ComputedRef<WkTblAddressRsdtChangeEntityInterface[]> = computed(() => props.listEntity);

function onEdit(selectedId: number) {
    const entity: WkTblAddressRsdtChangeEntityInterface | undefined
        = listEdit.value.filter((e) => selectedId === e.wkTblAddressRsdtChangeId)[0];
    if (undefined !== entity) {
        emits("sendEdit", entity);
    }

}
function onDelete(selectedId: number) {
    const entity: WkTblAddressRsdtChangeEntityInterface | undefined
        = listEdit.value.filter((e) => selectedId === e.wkTblAddressRsdtChangeId)[0];
    if (undefined !== entity) {
        emits("sendDelete", entity);
    }
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
                <th v-if="props.isEdit">&nbsp;</th>
            </tr>
            <tr v-for="entity in listEdit">
                <td>{{ entity.lgCode }}</td>
                <td>{{ entity.postalcode1 }}{{ entity.postalcode2 }}</td>
                <td>{{ entity.addressBlock }}</td>
                <td>{{ entity.addressBuilding }}</td>
                <td v-if="props.isEdit"><button @click="onEdit(entity.wkTblAddressRsdtChangeId)"
                        :disabled="!entity.isLatest">編集</button></td>
                <td v-if="props.isEdit"><button @click="onDelete(entity.wkTblAddressRsdtChangeId)"
                        :disabled="!entity.isLatest">削除</button></td>
            </tr>
        </tbody>
    </table>
</template>
<style scoped></style>
