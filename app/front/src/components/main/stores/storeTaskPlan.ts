import { defineStore } from 'pinia';
import { ref, type Ref } from 'vue';

export const useTaskPlan = defineStore('taskPlan', () => {
    // state（リアクティブな状態）
    const taskYear: Ref<number> = ref(0);
    const taskPlanId: Ref<number> = ref(0);

    const initialize = () => {
        taskYear.value = 0;
        taskPlanId.value = 0;
    }

    // 外部に公開する値や関数を返す
    return {
        initialize,
        taskYear,
        taskPlanId,
    };
},
    {
        persist: {
            storage: sessionStorage,
        },
    },
);