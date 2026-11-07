interface KanrenshaCombineOrgDtoInterface {


    /** テーブルId */
    kanrenshaCombineOrgId: number;

    /** 紐づけコード */
    kanrenshaCombineOrgCode: number;

    /** 発生年 */
    tableYear: number;

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

}


class KanrenshaCombineOrgDto implements KanrenshaCombineOrgDtoInterface {

    /** テーブルId */
    kanrenshaCombineOrgId: number;

    /** 紐づけコード */
    kanrenshaCombineOrgCode: number;

    /** 発生年 */
    tableYear: number;

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

    constructor() {

        const INIT_NUMBER: number = 0;
        const INIT_STRING: string = "";

        this.kanrenshaCombineOrgId = INIT_NUMBER;
        this.kanrenshaCombineOrgCode = INIT_NUMBER;
        this.tableYear = INIT_NUMBER;
        this.kanrenshaKbn = INIT_NUMBER;
        this.personKanrenshaCode = INIT_STRING;
        this.personName = INIT_STRING;
        this.orgKanrenshaCode = INIT_STRING;
        this.orgName = INIT_STRING;


    }


}

export { type KanrenshaCombineOrgDtoInterface, KanrenshaCombineOrgDto }
