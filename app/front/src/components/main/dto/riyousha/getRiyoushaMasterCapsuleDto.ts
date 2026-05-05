interface GetRiyoushaMasterCapsuleDtoInterface {

    /** 利用者コード */
    riyoushaCode: number;

    /** 利用者権限 */
    riyoushaRole: string;
}

class GetRiyoushaMasterCapsuleDto implements GetRiyoushaMasterCapsuleDtoInterface {

    /** 利用者コード */
    riyoushaCode: number;

    /** 利用者権限 */
    riyoushaRole: string;

    constructor() {
        this.riyoushaCode = 0;
        this.riyoushaRole = "";
    }

}

export { type GetRiyoushaMasterCapsuleDtoInterface, GetRiyoushaMasterCapsuleDto }
