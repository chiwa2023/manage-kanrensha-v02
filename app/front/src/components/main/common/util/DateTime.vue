<script setup lang="ts">
import { onBeforeMount, ref, type Ref } from 'vue';


// props,emmits
const props = defineProps<{ datetime: Date, index: number, isEdit: boolean }>();
const emits = defineEmits(["sendDateTime"]);

//仮
// よく使う定数
// const BLANK: string = "";
// const INIT_NUMBER: number = 0;
// const SERVER_STATUS_OK: number = 200;
// const SERVER_STATUS_ERROR: number = 400;
// メッセージボックス表示定数
//const infoLevel: Ref<number> = ref(MessageConstants.LEVEL_NONE);
//const messageType: Ref<number> = ref(MessageConstants.VIEW_NONE);
//const title: Ref<string> = ref(BLANK);
//const message: Ref<string> = ref(BLANK);

const dateString: Ref<string> = ref("");
const timeString: Ref<string> = ref("");
onBeforeMount(() => {
    if (props.datetime !== null) {
        dateString.value = props.datetime.toLocaleDateString('sv-SE');
        const time = props.datetime.toLocaleDateString('ja-JP', { hour: '2-digit', minute: '2-digit', second: '2-digit', hour12: false });
        timeString.value = time.substring(time.length - 8, time.length);
    } else {
        dateString.value = "";
        timeString.value = "";
    }
});

function onDataChange() {
    const dateCell: string[] = dateString.value.split("-");
    const timeCell: string[] = timeString.value.split(":");
    if (dateCell[0] !== undefined && dateCell[1] !== undefined && dateCell[2] !== undefined
        && timeCell[0] !== undefined && timeCell[1] !== undefined && timeCell[2] !== undefined) {
        const sendData: Date = new Date(parseInt(dateCell[0]), parseInt(dateCell[1]), parseInt(dateCell[2]),
            parseInt(timeCell[0]), parseInt(timeCell[1]), parseInt(timeCell[2]));
        emits("sendDateTime", sendData, props.index);
    }
}

</script>
<template>
    <input type="date" v-model="dateString" @blur="onDataChange" :disabled="!isEdit">
    <input type="time" v-model="timeString" @blur="onDataChange" :disabled="!isEdit">
</template>
<style scoped></style>
