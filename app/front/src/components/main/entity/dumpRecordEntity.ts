
interface DumpRecordEntityInterface {

    /** テーブルId */
    dumpRecordId: number;

    /** タスク情報コード */
    taskInfoCode: number;

    /** 最新該否 */
    isLatest: boolean;

    /** 開始日 */
    startDatetime: Date;

    /** 終了日 */
    endDatetime: Date;

    /** 挿入ユーザId */
    insertUserId: number;

    /** 挿入ユーザコード */
    insertUserCode: number;

    /** 挿入ユーザ名称 */
    insertUserName: string;

    /** 挿入日時 */
    insertTimestamp: Date;
}

class DumpRecordEntity implements DumpRecordEntityInterface {

    /** テーブルId */
    dumpRecordId: number;

    /** タスク情報コード */
    taskInfoCode: number;

    /** 最新該否 */
    isLatest: boolean;

    /** 開始日 */
    startDatetime: Date;

    /** 終了日 */
    endDatetime: Date;

    /** 挿入ユーザId */
    insertUserId: number;

    /** 挿入ユーザコード */
    insertUserCode: number;

    /** 挿入ユーザ名称 */
    insertUserName: string;

    /** 挿入日時 */
    insertTimestamp: Date;

    constructor() {

        const INIT_NUMBER: number = 0;
        const INIT_STRING: string = "";
        const INIT_BOOLEAN: boolean = false;
        const INIT_TIMESTAMP: Date = new Date(1948, 7, 28, 23, 59, 59);

        this.dumpRecordId = INIT_NUMBER;
        this.taskInfoCode = INIT_NUMBER;
        this.isLatest = INIT_BOOLEAN;
        this.startDatetime = INIT_TIMESTAMP;
        this.endDatetime = INIT_TIMESTAMP;
        this.insertUserId = INIT_NUMBER;
        this.insertUserCode = INIT_NUMBER;
        this.insertUserName = INIT_STRING;
        this.insertTimestamp = INIT_TIMESTAMP;
    }

}

export { type DumpRecordEntityInterface, DumpRecordEntity }
