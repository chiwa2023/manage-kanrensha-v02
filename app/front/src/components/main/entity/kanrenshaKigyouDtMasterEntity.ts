interface KanrenshaKigyouDtMasterEntityInterface {

    /** テーブルId */
    kanrenshaKigyouDtMasterId: number;

    /** 企業・団体関連者コード */
    kigyouDtKanrenshaCode: string;

    /** 法人番号 */
    houjinNo: string;

    /** 企業・団体名 */
    kanrenshaName: string;

    /** 企業・団体全住所 */
    allAddress: string;

    /** 企業・団体代表者 */
    kigyouDtDelegate: string;

    /** 名称比較用 */
    compareNameText: string;

}

class KanrenshaKigyouDtMasterEntity implements KanrenshaKigyouDtMasterEntityInterface {

    /** テーブルId */
    kanrenshaKigyouDtMasterId: number;

    /** 企業・団体関連者コード */
    kigyouDtKanrenshaCode: string;

    /** 法人番号 */
    houjinNo: string;

    /** 企業・団体名 */
    kanrenshaName: string;

    /** 企業・団体全住所 */
    allAddress: string;

    /** 企業・団体代表者 */
    kigyouDtDelegate: string;

    /** 名称比較用 */
    compareNameText: string;

    constructor() {

        const INIT_STRING: string = "";

        this.kanrenshaKigyouDtMasterId = 0;
        this.kigyouDtKanrenshaCode = INIT_STRING;
        this.houjinNo = INIT_STRING;
        this.kanrenshaName = INIT_STRING;
        this.allAddress = INIT_STRING;
        this.kigyouDtDelegate = INIT_STRING;
        this.compareNameText = INIT_STRING;
    }

}

export { type KanrenshaKigyouDtMasterEntityInterface, KanrenshaKigyouDtMasterEntity }
