interface SearchTaskHistoryCapsuleDtoInterface {

    /** タスク計画年 */
    taskYear: number;

    /** タスク計画コード */
    taskPlanCode: number;

}



class SearchTaskHistoryCapsuleDto implements SearchTaskHistoryCapsuleDtoInterface {

    /** タスク計画年 */
    taskYear: number;

    /** タスク計画コード */
    taskPlanCode: number;

    constructor() {

        const INIT_INTEGER: number = 0;

        this.taskYear = INIT_INTEGER;
        this.taskPlanCode = INIT_INTEGER;

    }

}

export { type SearchTaskHistoryCapsuleDtoInterface, SearchTaskHistoryCapsuleDto }