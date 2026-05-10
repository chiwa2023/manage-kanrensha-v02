import { FrameworkCapsuleDto, type FrameworkCapsuleDtoInterface } from "seijishikin-jp-normalize_common-tool";

interface GetTempRiyoushaOrgCombineCapsuleDtoInterface extends FrameworkCapsuleDtoInterface {

    /** 個人コード */
    personCode: number;

    /** 組織コード */
    orgCode: number;

    /** 個人権限 */
    userRole: string;
}


class GetTempRiyoushaOrgCombineCapsuleDto extends FrameworkCapsuleDto
    implements GetTempRiyoushaOrgCombineCapsuleDtoInterface {

    /** 個人コード */
    personCode: number;

    /** 組織コード */
    orgCode: number;

    /** 個人権限 */
    userRole: string;

    constructor() {
        super();

        const INIT_NUMBER: number = 0;

        this.personCode = INIT_NUMBER;
        this.orgCode = INIT_NUMBER;
        this.userRole = "";
    }

}

export { type GetTempRiyoushaOrgCombineCapsuleDtoInterface, GetTempRiyoushaOrgCombineCapsuleDto }

