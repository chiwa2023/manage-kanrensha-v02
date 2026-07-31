import { FrameworkCapsuleDto, type FrameworkCapsuleDtoInterface } from "seijishikin-jp-normalize_common-tool";

interface GetRiyoushaMasterCapsuleDtoInterface extends FrameworkCapsuleDtoInterface {

    /** 利用者コード */
    riyoushaCode: number;

    /** 利用者権限 */
    riyoushaRole: string;
}

class GetRiyoushaMasterCapsuleDto extends FrameworkCapsuleDto
    implements GetRiyoushaMasterCapsuleDtoInterface {

    /** 利用者コード */
    riyoushaCode: number;

    /** 利用者権限 */
    riyoushaRole: string;

    constructor() {
        super();

        this.riyoushaCode = 0;
        this.riyoushaRole = "";
    }

}

export { type GetRiyoushaMasterCapsuleDtoInterface, GetRiyoushaMasterCapsuleDto }
