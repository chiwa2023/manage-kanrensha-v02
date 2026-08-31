interface KanrenshaSeijidantaiMasterEntityInterface {

    /** テーブルId */
    kanrenshaSeijidantaiMasterId: number;

    /** 政治団体関連者コード */
    seijidantaiKanrenshaCode: string;

    /** 政治団体番号 */
    poliOrgNo: string;

    /** 企業・団体名 */
    kanrenshaName: string;

    /** 企業・団体全住所 */
    allAddress: string;

    /** 企業・団体代表者 */
    seijidantaiDelegate: string;

    /** 政治団体区分 */
    dantaiKbn: string;

    /** 名称比較用 */
    compareNameText: string;

}

class KanrenshaSeijidantaiMasterEntity implements KanrenshaSeijidantaiMasterEntityInterface {

    /** テーブルId */
    kanrenshaSeijidantaiMasterId: number;

    /** 政治団体関連者コード */
    seijidantaiKanrenshaCode: string;

    /** 政治団体番号 */
    poliOrgNo: string;

    /** 企業・団体名 */
    kanrenshaName: string;

    /** 企業・団体全住所 */
    allAddress: string;

    /** 企業・団体代表者 */
    seijidantaiDelegate: string;

    /** 政治団体区分 */
    dantaiKbn: string;

    /** 名称比較用 */
    compareNameText: string;

    constructor() {

        const INIT_STRING: string = "";

        this.kanrenshaSeijidantaiMasterId = 0;
        this.seijidantaiKanrenshaCode = INIT_STRING;
        this.poliOrgNo = INIT_STRING;
        this.kanrenshaName = INIT_STRING;
        this.allAddress = INIT_STRING;
        this.seijidantaiDelegate = INIT_STRING;
        this.dantaiKbn = INIT_STRING;
        this.compareNameText = INIT_STRING;
    }

}

export { type KanrenshaSeijidantaiMasterEntityInterface, KanrenshaSeijidantaiMasterEntity }
