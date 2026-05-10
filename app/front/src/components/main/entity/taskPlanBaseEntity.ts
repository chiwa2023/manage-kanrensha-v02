
interface TaskPlanBaseEntityInterface {
    /** タスク計画コード */
    taskPlanId: number;

    /** タスク計画コード */
    taskPlanCode: number;

    /** タスク計画名称 */
    taskPlanName: string;

    /** 発生年 */
    tableYear: number;

    /** 開始フラグ */
    isStart: boolean;

    /** 終了フラグ */
    isFinished: boolean;

    /** 中断フラグ */
    isSuspended: boolean;

    /** 開始日時 */
    startDatetime: Date;

    /** 終了日時 */
    endDatetime: Date;
    
    /** 遷移パス */
    transferPass: string;

    /** 作成日時 */
    insertTimestamp: Date;
}

class TaskPlanBaseEntity implements TaskPlanBaseEntityInterface {
    /** タスク計画コード */
    taskPlanId: number;

    /** タスク計画コード */
    taskPlanCode: number;

    /** タスク計画名称 */
    taskPlanName: string;

    /** 発生年 */
    tableYear: number;

    /** 開始フラグ */
    isStart: boolean;

    /** 終了フラグ */
    isFinished: boolean;

    /** 中断フラグ */
    isSuspended: boolean;

    /** 開始日時 */
    startDatetime: Date;

    /** 終了日時 */
    endDatetime: Date;

    /** 遷移パス */
    transferPass: string;

    /** 作成日時 */
    insertTimestamp: Date;

    constructor() {

        // 初期データ
        const INIT_STRING: string = "";
        const INIT_NUMBER: number = 0;
        const INIT_BOOLEAN: boolean = false;
        const INIT_DATE: Date = new Date(1948, 7, 29, 0, 0, 0);

        this.taskPlanId = INIT_NUMBER;
        this.taskPlanCode = INIT_NUMBER;
        this.tableYear = INIT_NUMBER;
        this.isStart = INIT_BOOLEAN;
        this.isFinished = INIT_BOOLEAN;
        this.isSuspended = INIT_BOOLEAN;
        this.taskPlanName = INIT_STRING;
        this.transferPass = INIT_STRING;

        this.startDatetime = INIT_DATE;
        this.endDatetime = INIT_DATE;
        this.insertTimestamp = INIT_DATE;
    }

}

export { type TaskPlanBaseEntityInterface, TaskPlanBaseEntity }