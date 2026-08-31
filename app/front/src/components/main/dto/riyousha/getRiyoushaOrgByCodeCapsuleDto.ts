
interface GetRiyoushaOrgByCodeCapsuleDtoInterface {

    /** 選択中コード */
    selectedCode: number;
}

class GetRiyoushaOrgByCodeCapsuleDto implements GetRiyoushaOrgByCodeCapsuleDtoInterface {

    /** 選択中コード */
    selectedCode: number;

    constructor() {
        this.selectedCode = 0;
    }

}

export { type GetRiyoushaOrgByCodeCapsuleDtoInterface, GetRiyoushaOrgByCodeCapsuleDto }
