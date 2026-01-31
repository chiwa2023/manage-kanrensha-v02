import type { TaskPlanBaseEntityInterface } from "../../entity/taskPlanBaseEntity";

interface TaskListForUserInfoResultDtoInterface {

    /** 本年度未処理タスク計画リスト */
    listThisYear: TaskPlanBaseEntityInterface[];

    /** 前年度未処理タスク計画リスト */
    listLastYear: TaskPlanBaseEntityInterface[];

    /** リスト更新済状態 */
    isRefreshed: boolean;

}

class TaskListForUserInfoResultDto implements TaskListForUserInfoResultDtoInterface {

    /** 本年度未処理タスク計画リスト */
    listThisYear: TaskPlanBaseEntityInterface[];

    /** 前年度未処理タスク計画リスト */
    listLastYear: TaskPlanBaseEntityInterface[];

    /** リスト更新済状態 */
    isRefreshed: boolean;

    constructor() {

        this.listThisYear = [];
        this.listLastYear = [];
        this.isRefreshed = false;
    }

}

export { type TaskListForUserInfoResultDtoInterface, TaskListForUserInfoResultDto }