interface WkTblKanrenshaSeijidantaiAddMinEntityInterface {

    /** テーブルId */
    wkTblKanrenshaSeijidantaiAddMinId: number;

    /** 関連者政治団体コード */
    wkTblKanrenshaSeijidantaiAddMinCode: number;

    /** 最新フラグ */
    isLatest: boolean;

    /** 処理完了該否 */
    isFinish: boolean;

    /** 政治団体名 */
    kanrenshaName: string;

    /** 政治団体全住所 */
    allAddress: string;

    /** 政治団体代表者 */
    seijidantaiDelegate: string;

    /** 政治団体区分 */
    dantaiKbn: string;

    /** 政治団体番号 */
    poliOrgNo: string;

    /** 反映有無 */
    isAffected: boolean;

    /** 判定理由 */
    judgeReason: string;

}



class WkTblKanrenshaSeijidantaiAddMinEntity implements WkTblKanrenshaSeijidantaiAddMinEntityInterface {

    /** テーブルId */
    wkTblKanrenshaSeijidantaiAddMinId: number;

    /** 関連者政治団体コード */
    wkTblKanrenshaSeijidantaiAddMinCode: number;

    /** 最新フラグ */
    isLatest: boolean;

    /** 処理完了該否 */
    isFinish: boolean;

    /** 政治団体名 */
    kanrenshaName: string;

    /** 政治団体全住所 */
    allAddress: string;

    /** 政治団体代表者 */
    seijidantaiDelegate: string;

    /** 政治団体区分 */
    dantaiKbn: string;

    /** 政治団体番号 */
    poliOrgNo: string;

    /** 反映有無 */
    isAffected: boolean;

    /** 判定理由 */
    judgeReason: string;


    constructor() {
        const INIT_NUMBER: number = 0;
        const INIT_STRING: string = "";
        const INIT_BOOLEAN: boolean = false;

        this.wkTblKanrenshaSeijidantaiAddMinId = INIT_NUMBER;
        this.wkTblKanrenshaSeijidantaiAddMinCode = INIT_NUMBER;
        this.isLatest = INIT_BOOLEAN;
        this.isFinish = INIT_BOOLEAN;
        this.kanrenshaName = INIT_STRING;
        this.allAddress = INIT_STRING;
        this.seijidantaiDelegate = INIT_STRING;
        this.dantaiKbn = INIT_STRING;
        this.poliOrgNo = INIT_STRING;
        this.isAffected = INIT_BOOLEAN;
        this.judgeReason = INIT_STRING;
    }

}

export { type WkTblKanrenshaSeijidantaiAddMinEntityInterface, WkTblKanrenshaSeijidantaiAddMinEntity }
