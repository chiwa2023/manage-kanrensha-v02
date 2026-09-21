interface KanrenshaCodeMoveHistoryDtoInterface {
    
    /** テーブルId */
    userPersonId: number;

    /** ユーザコード */
    userPersonCode: number;

    /** ユーザ名称 */
    userPersonName: string;

    /** 最新該否 */
    isLatest: number;

    /** 役割 */
    role: string;

    /** 関連者コード */
    kanrenshaCode: string;

    /** 挿入日時 */
    insertTimestamp: Date;

}

class KanrenshaCodeMoveHistoryDto implements KanrenshaCodeMoveHistoryDtoInterface {

    /** テーブルId */
    userPersonId: number;

    /** ユーザコード */
    userPersonCode: number;

    /** ユーザ名称 */
    userPersonName: string;

    /** 最新該否 */
    isLatest: number;

    /** 役割 */
    role: string;

    /** 関連者コード */
    kanrenshaCode: string;

    /** 挿入日時 */
    insertTimestamp: Date;

    constructor() {

        const INIT_INTEGER: number = 0;
        const INIT_STRING: string = "";
        const INIT_TIMESTAMP: Date = new Date(1948, 7, 28, 23, 59, 59);

        this.userPersonId = INIT_INTEGER;
        this.userPersonCode = INIT_INTEGER;
        this.userPersonName = INIT_STRING;
        this.isLatest = INIT_INTEGER;
        this.role = INIT_STRING;
        this.kanrenshaCode = INIT_STRING;
        this.insertTimestamp = INIT_TIMESTAMP;
    }

}

export { type KanrenshaCodeMoveHistoryDtoInterface, KanrenshaCodeMoveHistoryDto }
