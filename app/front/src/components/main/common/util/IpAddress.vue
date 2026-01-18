<script setup lang="ts">
import { onBeforeMount, ref, type Ref } from 'vue';

const props = defineProps<{ ipAddress: string }>();
const emits = defineEmits(["sendIpAddress"]);

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

const v4IpAdd1: Ref<number> = ref(0);
const v4IpAdd2: Ref<number> = ref(0);
const v4IpAdd3: Ref<number> = ref(0);
const v4IpAdd4: Ref<number> = ref(0);

const v6IpAdd1: Ref<string> = ref("");
const v6IpAdd2: Ref<string> = ref("");
const v6IpAdd3: Ref<string> = ref("");
const v6IpAdd4: Ref<string> = ref("");
const v6IpAdd5: Ref<string> = ref("");
const v6IpAdd6: Ref<string> = ref("");
const v6IpAdd7: Ref<string> = ref("");
const v6IpAdd8: Ref<string> = ref("");

const addressVersion: Ref<string> = ref("v4");


onBeforeMount(() => {

    if (props.ipAddress.includes(".")) {
        // propsが v4   
        const cell: string[] = props.ipAddress.split(".");
        if (cell[0] !== undefined) {
            v4IpAdd1.value = parseInt(cell[0]);
        }
        if (cell[1] !== undefined) {
            v4IpAdd2.value = parseInt(cell[1]);
        }
        if (cell[2] !== undefined) {
            v4IpAdd3.value = parseInt(cell[2]);
        }
        if (cell[3] !== undefined) {
            v4IpAdd4.value = parseInt(cell[3]);
        }

    } else {
        // propsが v6   
        const cell: string[] = props.ipAddress.split(":");
        if (cell[0] !== undefined) {
            v6IpAdd1.value = cell[0];
        }
        if (cell[1] !== undefined) {
            v6IpAdd2.value = cell[1];
        }
        if (cell[2] !== undefined) {
            v6IpAdd3.value = cell[2];
        }
        if (cell[3] !== undefined) {
            v6IpAdd4.value = cell[3];
        }
        if (cell[4] !== undefined) {
            v6IpAdd5.value = cell[4];
        }
        if (cell[5] !== undefined) {
            v6IpAdd6.value = cell[5];
        }
        if (cell[6] !== undefined) {
            v6IpAdd7.value = cell[6];
        }
        if (cell[7] !== undefined) {
            v6IpAdd8.value = cell[7];
        }
    }

});

function onAddressChangeV4() {
    const data = v4IpAdd1.value + "." + v4IpAdd2.value + "." + v4IpAdd3.value + "." + v4IpAdd4.value;
    emits("sendIpAddress", data);
}

function onAddressChangeV6() {
    const data = v6IpAdd1.value + ":" + v6IpAdd2.value + ":" + v6IpAdd3.value + ":" + v6IpAdd4.value
        + ":" + v6IpAdd5.value + ":" + v6IpAdd6.value + ":" + v6IpAdd7.value + ":" + v6IpAdd8.value;
    emits("sendIpAddress", data);
}

</script>
<template>
                    <div class="form-group-vertical">

    <div>
    <input type="radio" value="v4" v-model="addressVersion"> IPV4
    <input type="radio" value="v6" v-model="addressVersion" class="left-space"> IPV6<br>
        </div>

    <div v-if="addressVersion === 'v4'">
        <input type="number" v-model="v4IpAdd1" min="0" max="255" @change="onAddressChangeV4" class="ip-address">.
        <input type="number" v-model="v4IpAdd2" min="0" max="255" @change="onAddressChangeV4" class="ip-address">.
        <input type="number" v-model="v4IpAdd3" min="0" max="255" @change="onAddressChangeV4" class="ip-address">.
        <input type="number" v-model="v4IpAdd4" min="0" max="255" @change="onAddressChangeV4" class="ip-address">
    </div>
    <div v-if="addressVersion === 'v6'">
        <input type="text" v-model="v6IpAdd1" @change="onAddressChangeV6" class="ip-address"> ： 
        <input type="text" v-model="v6IpAdd2" @change="onAddressChangeV6" class="ip-address"> ： 
        <input type="text" v-model="v6IpAdd3" @change="onAddressChangeV6" class="ip-address"> ： 
        <input type="text" v-model="v6IpAdd4" @change="onAddressChangeV6" class="ip-address"> ： 
        <input type="text" v-model="v6IpAdd5" @change="onAddressChangeV6" class="ip-address"> ： 
        <input type="text" v-model="v6IpAdd6" @change="onAddressChangeV6" class="ip-address"> ： 
        <input type="text" v-model="v6IpAdd7" @change="onAddressChangeV6" class="ip-address"> ： 
        <input type="text" v-model="v6IpAdd8" @change="onAddressChangeV6" class="ip-address">
    </div>
    </div>
</template>
<style scoped>
    input.ip-address{
        text-align: right;
        width: 4em;
    }
</style>
