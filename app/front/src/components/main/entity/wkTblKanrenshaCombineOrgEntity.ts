interface WkTblKanrenshaCombineOrgEntityInterface {

    /** テーブルId */
    wkTblKanrenshaCombineOrgId: number;

    /** 紐づけコード */
    wkTblKanrenshaCombineOrgCode: number;

    /** 最新該否 */
    isLatest: boolean;

    /** 処理完了該否 */
    isFinish: boolean;

    /** 紐づけ関連者区分 */
    kanrenshaKbn: number;

    /** 個人関連者コード */
    personKanrenshaCode: string;

    /** 個人氏名 */
    personName: string;

    /** 団体関連者コード */
    orgKanrenshaCode: string;

    /** 団体代表者名称 */
    orgName: string;

    /** 登録開始年 */
    startYear: number;

    /** 登録終了年 */
    endYear: number;

    /** 登録年配列 */
    yearArrayText: string;

    /** 反映有無 */
    isAffected: boolean;

    /** 判定理由 */
    judgeReason: string;

}

class WkTblKanrenshaCombineOrgEntity implements WkTblKanrenshaCombineOrgEntityInterface {

    /** テーブルId */
    wkTblKanrenshaCombineOrgId: number;

    /** 紐づけコード */
    wkTblKanrenshaCombineOrgCode: number;

    /** 最新該否 */
    isLatest: boolean;

    /** 処理完了該否 */
    isFinish: boolean;

    /** 紐づけ関連者区分 */
    kanrenshaKbn: number;

    /** 個人関連者コード */
    personKanrenshaCode: string;

    /** 個人氏名 */
    personName: string;

    /** 団体関連者コード */
    orgKanrenshaCode: string;

    /** 団体代表者名称 */
    orgName: string;

    /** 登録開始年 */
    startYear: number;

    /** 登録終了年 */
    endYear: number;

    /** 登録年配列 */
    yearArrayText: string;

    /** 反映有無 */
    isAffected: boolean;

    /** 判定理由 */
    judgeReason: string;


    constructor() {

        // 初期データ
        const INIT_STRING: string = "";
        const INIT_NUMBER: number = 0;
        const INIT_BOOLEAN: boolean = false;

        this.wkTblKanrenshaCombineOrgId = INIT_NUMBER;
        this.wkTblKanrenshaCombineOrgCode = INIT_NUMBER;
        this.isLatest = INIT_BOOLEAN;
        this.isFinish = INIT_BOOLEAN;
        this.kanrenshaKbn = INIT_NUMBER;
        this.personKanrenshaCode = INIT_STRING;
        this.personName = INIT_STRING;
        this.orgKanrenshaCode = INIT_STRING;
        this.orgName = INIT_STRING;
        this.startYear = INIT_NUMBER;
        this.endYear = INIT_NUMBER;
        this.yearArrayText = INIT_STRING;
        this.isAffected = INIT_BOOLEAN;
        this.judgeReason = INIT_STRING;
    }

}

export { type WkTblKanrenshaCombineOrgEntityInterface, WkTblKanrenshaCombineOrgEntity }
