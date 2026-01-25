interface PromoteAdminEntityInterface {


    /** テーブルId */
    promoteAdminId: number;

    /** 紐づけコード */
    promoteAdminCode: number;

    /** 最新該否 */
    isLatest: boolean;

    /** 推薦者Id */
    promoteUserId: number;

    /** 推薦者コード */
    promoteUserCode: number;

    /** 推薦者氏名 */
    promoteUserName: string;

    /** 承諾フラグ */
    isAccept: boolean;

    /** タスク計画Id */
    taskPlanId: number;

    /** タスク発生年 */
    taskYear: number;

    /** 挿入ユーザId */
    insertUserId: number;

    /** 挿入ユーザコード */
    insertUserCode: number

    /** 挿入ユーザ名称 */
    insertUserName: string;

    /** 挿入日時 */
    insertTimestamp: Date;


}


class PromoteAdminEntity implements PromoteAdminEntityInterface {

    /** テーブルId */
    promoteAdminId: number;

    /** 紐づけコード */
    promoteAdminCode: number;

    /** 最新該否 */
    isLatest: boolean;

    /** 推薦者Id */
    promoteUserId: number;

    /** 推薦者コード */
    promoteUserCode: number;

    /** 推薦者氏名 */
    promoteUserName: string;

    /** 承諾フラグ */
    isAccept: boolean;

    /** タスク計画Id */
    taskPlanId: number;

    /** タスク発生年 */
    taskYear: number;

    /** 挿入ユーザId */
    insertUserId: number;

    /** 挿入ユーザコード */
    insertUserCode: number

    /** 挿入ユーザ名称 */
    insertUserName: string;

    /** 挿入日時 */
    insertTimestamp: Date;

    constructor() {

        const INIT_INTEGER: number = 0;
        const INIT_STRING: string = "";
        const INIT_BOOLEAN: boolean = false;
        const INIT_TIMESTAMP: Date = new Date(1948, 7, 28, 23, 59, 59);

        this.promoteAdminId = INIT_INTEGER;
        this.promoteAdminCode = INIT_INTEGER;
        this.isLatest = INIT_BOOLEAN;
        this.promoteUserId = INIT_INTEGER;
        this.promoteUserCode = INIT_INTEGER;
        this.promoteUserName = INIT_STRING;
        this.isAccept = INIT_BOOLEAN;
        this.taskPlanId = INIT_INTEGER;
        this.taskYear = INIT_INTEGER;
        this.insertUserId = INIT_INTEGER;
        this.insertUserCode = INIT_INTEGER;
        this.insertUserName = INIT_STRING;
        this.insertTimestamp = INIT_TIMESTAMP;
    }

}

export { type PromoteAdminEntityInterface, PromoteAdminEntity }
