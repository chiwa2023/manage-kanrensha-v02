
interface TaskInfoEntityInterface {


    /** タスク情報Id */
    taskInfoId: number;

    /** タスク情報コード */
    taskInfoCode: number;

    /** タスク情報名称 */
    taskInfoName: string;

    /** 最新該否 */
    isLatest: boolean;

    /** 権限区分 */
    roleList: string;

    /** 開始メッセージ */
    messageStart: string;

    /** 終了メッセージ  */
    messageFinish: string;

    /** 中断メッセージ */
    messageSuspend: string;

    /** 遷移パス(URL) */
    transferPass: string;

    /** 遷移時引数 */
    paramQuery: string;

}


class TaskInfoEntity implements TaskInfoEntityInterface {

    /** タスク情報Id */
    taskInfoId: number;

    /** タスク情報コード */
    taskInfoCode: number;

    /** タスク情報名称 */
    taskInfoName: string;

    /** 最新該否 */
    isLatest: boolean;

    /** 権限区分 */
    roleList: string;

    /** 開始メッセージ */
    messageStart: string;

    /** 終了メッセージ  */
    messageFinish: string;

    /** 中断メッセージ */
    messageSuspend: string;

    /** 遷移パス(URL) */
    transferPass: string;

    /** 遷移時引数 */
    paramQuery: string;

    constructor() {

        const INIT_INTEGER: number = 0;
        const INIT_STRING: string = "";
        const INIT_BOOLEAN: boolean = false;

        this.taskInfoId = INIT_INTEGER;
        this.taskInfoCode = INIT_INTEGER;
        this.taskInfoName = INIT_STRING;
        this.isLatest = INIT_BOOLEAN;
        this.roleList = INIT_STRING;
        this.messageStart = INIT_STRING;
        this.messageFinish = INIT_STRING;
        this.messageSuspend = INIT_STRING;
        this.transferPass = INIT_STRING;
        this.paramQuery = INIT_STRING;
    }

}

export { type TaskInfoEntityInterface, TaskInfoEntity }