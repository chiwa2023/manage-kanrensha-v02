interface WkTblKanrenshaPersonHistoryEntityInterface {

    /** テーブルId */
    wkKanrenshaPersonHistoryId: number;

    /** 関連者企業・団体コード */
    wkKanrenshaPersonHistoryCode: number;

    /** 最新フラグ */
    isLatest: boolean;

    /** 処理完了該否 */
    isFinish: boolean;

    /** 企業・団体名 */
    kanrenshaName: string;

    /** 企業・団体全住所 */
    allAddress: string;

    /** 企業・団体代表者 */
    personShokugyou: string;

    /** 企業・団体関連者コード */
    personKanrenshaCode: string;

    /** 反映有無 */
    isAffected: boolean;

    /** 判定理由 */
    judgeReason: string;

}



class WkTblKanrenshaPersonHistoryEntity implements WkTblKanrenshaPersonHistoryEntityInterface {

    /** テーブルId */
    wkKanrenshaPersonHistoryId: number;

    /** 関連者企業・団体コード */
    wkKanrenshaPersonHistoryCode: number;

    /** 最新フラグ */
    isLatest: boolean;

    /** 処理完了該否 */
    isFinish: boolean;

    /** 企業・団体名 */
    kanrenshaName: string;

    /** 企業・団体全住所 */
    allAddress: string;

    /** 企業・団体代表者 */
    personShokugyou: string;

    /** 企業・団体関連者コード */
    personKanrenshaCode: string;

    /** 反映有無 */
    isAffected: boolean;

    /** 判定理由 */
    judgeReason: string;


    constructor() {
        const INIT_NUMBER: number = 0;
        const INIT_STRING: string = "";
        const INIT_BOOLEAN: boolean = false;

        this.wkKanrenshaPersonHistoryId = INIT_NUMBER;
        this.wkKanrenshaPersonHistoryCode = INIT_NUMBER;
        this.isLatest = INIT_BOOLEAN;
        this.isFinish = INIT_BOOLEAN;
        this.kanrenshaName = INIT_STRING;
        this.allAddress = INIT_STRING;
        this.personShokugyou = INIT_STRING;
        this.personKanrenshaCode = INIT_STRING;
        this.isAffected = INIT_BOOLEAN;
        this.judgeReason = INIT_STRING;
    }

}

export { type WkTblKanrenshaPersonHistoryEntityInterface, WkTblKanrenshaPersonHistoryEntity }
