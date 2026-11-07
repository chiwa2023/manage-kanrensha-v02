<script setup lang="ts">
import { computed, ref, watch, type ComputedRef, type Ref } from 'vue';
import type { YearOptionEntityInterface } from '../../entity/yearOptionEntity';

// props,emit
const props = defineProps<{ listYear: YearOptionEntityInterface[] }>();
const emits = defineEmits(["sendStartYear", "sendEndYear", "sendCheckList"]);

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

const startYear: Ref<number> = ref(0);
const endYear: Ref<number> = ref(0);

watch(props.listYear, () => {
    setYear();
});

const listYearCheck: ComputedRef<YearOptionEntityInterface[]> = computed(() => props.listYear);

const CHUNK_SIZE: number = 6;
const chunkedYearList: ComputedRef<YearOptionEntityInterface[][]> = computed(() => {
    const chunks: YearOptionEntityInterface[][] = [];
    const list = listYearCheck.value;
    if (!list) {
        return chunks;
    }
    for (let i = 0; i < list.length; i += CHUNK_SIZE) {
        chunks.push(list.slice(i, i + CHUNK_SIZE));
    }
    return chunks;
});

function setYear() {
    const start: number | undefined = props.listYear[0]?.selectedYear;
    const end: number | undefined = props.listYear[props.listYear.length - 1]?.selectedYear;
    if (undefined !== start) {
        startYear.value = start;
    }
    if (undefined !== end) {
        endYear.value = end;
    }
}

const period: string = "period";
const point: string = "point";
const isSetPeriod: Ref<string> = ref(period);

function onStartYear() {
    emits("sendStartYear", startYear.value);
}
function onEndYear() {
    emits("sendEndYear", endYear.value);
}
function onYearList() {
    emits("sendCheckList", listYearCheck.value);
}
</script>
<template>

    <div class="one-line">
        <div class="left-area">
            年度登録方法
        </div>
        <div class="right-area">
            <span><input type="radio" v-model="isSetPeriod" :value="period" id="dataFormat">期間で指定</span>
            <span class="left-space"><input type="radio" v-model="isSetPeriod" :value="point"
                    id="dataFormat">具体的に指定</span>
        </div>
    </div>

    <!-- 登録年を期間で指定  -->
    <div v-if="isSetPeriod === period">
        <div class="one-line">
            <div class="left-area">
                開始年
            </div>
            <div class="right-area">
                <input type="number" v-model="startYear" @input="onStartYear" />
            </div>
        </div>

        <div class="one-line">
            <div class="left-area">
                終了年
            </div>
            <div class="right-area">
                <input type="number" v-model="endYear" @input="onEndYear" />
            </div>
        </div>

    </div>
    <!-- 登録年を期間で指定 -->
    <div v-if="isSetPeriod === point">
        <div class="one-line">
            <div class="left-area">
                登録年
            </div>
            <div class="right-area">
                <div class="form-group-vertical">
                    <div v-for="(chunk, cIdx) in chunkedYearList" :key="cIdx" class="year-row">
                        <span v-for="dto in chunk" :key="dto.selectedYear">
                            <input type="checkbox" v-model="dto.isSelected" @change="onYearList" />{{ dto.selectedYear }} 年
                        </span>
                    </div>
                </div>
            </div>
        </div>
    </div>

</template>
<style scoped>
.year-row {
    display: flex;
    flex-wrap: wrap;
    gap: 0.5rem;
}
</style>
