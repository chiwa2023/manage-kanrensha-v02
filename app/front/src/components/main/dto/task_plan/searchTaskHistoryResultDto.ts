import type { TaskPlanBaseEntityInterface } from "../../entity/taskPlanBaseEntity";

interface SearchTaskHistoryResultDtoInterface {

    /** タスク計画リスト */
    listTaskHistory: TaskPlanBaseEntityInterface[];
}

class SearchTaskHistoryResultDto implements SearchTaskHistoryResultDtoInterface {

    /** タスク計画リスト */
    listTaskHistory: TaskPlanBaseEntityInterface[];

    constructor() {
        this.listTaskHistory = [];
    }
}

export { type SearchTaskHistoryResultDtoInterface, SearchTaskHistoryResultDto }