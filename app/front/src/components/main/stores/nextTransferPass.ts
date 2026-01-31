import { defineStore } from "pinia";
import { ref, type Ref } from 'vue';



export const nextTransferPassStore = defineStore('nextTransferPass', () => {
    const fullPath: Ref<string> = ref("");
    
    // 外部に公開する値や関数を返す
    return {
        fullPath,
    };
},
    {
        persist: {
            storage: sessionStorage,
        },
    },
);