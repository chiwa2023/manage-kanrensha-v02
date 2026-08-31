import { defineStore } from 'pinia';
import { ref, type Ref } from 'vue';
import { TaskListForUserInfoResultDto, type TaskListForUserInfoResultDtoInterface } from '../dto/task_plan/taskListForUserInfoResultDto';

export const notCompletedTaskStore = defineStore('notCompletedTask', () => {
    // state（リアクティブな状態）
    const notCompleteTaskDto: Ref<TaskListForUserInfoResultDtoInterface> = ref(new TaskListForUserInfoResultDto());

    // 外部に公開する値や関数を返す
    return {
        notCompleteTaskDto,
    };
},
    {
        persist: {
            storage: sessionStorage,
        },
    },
);