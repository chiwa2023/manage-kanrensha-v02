interface GetKanrenshaMasterCapsuleDtoInterface {

    /** 関連者コード */
    kanrenshaCode: string;

    /** 関連者権限 */
    kanrenshaRole: string;

}

class GetKanrenshaMasterCapsuleDto implements GetKanrenshaMasterCapsuleDtoInterface {

    /** 関連者コード */
    kanrenshaCode: string;

    /** 関連者権限 */
    kanrenshaRole: string;

    constructor() {
        const INIT_STRING: string = "";

        this.kanrenshaCode = INIT_STRING;
        this.kanrenshaRole = INIT_STRING;
    }
}

export { type GetKanrenshaMasterCapsuleDtoInterface, GetKanrenshaMasterCapsuleDto }
