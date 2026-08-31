import { FrameworkCapsuleDto, type FrameworkCapsuleDtoInterface } from "seijishikin-jp-normalize_common-tool";
import { RiyoushaOrgDto, type RiyoushaOrgDtoInterface } from "./riyoushaOrgDto";

interface SaveRiyoushaOrgCapsuleDtoInterface extends FrameworkCapsuleDtoInterface {
    /** 利用者運営者Dto */
    riyoushaOrgDto: RiyoushaOrgDtoInterface;

    /** 利用者名 */
    riyoushaName: string;

    /** 利用者コード */
    riyoushaCode: number;

    /** 利用者権限 */
    riyoushaRole: string;
}

class SaveRiyoushaOrgCapsuleDto extends FrameworkCapsuleDto
    implements SaveRiyoushaOrgCapsuleDtoInterface {

    /** 利用者運営者Dto */
    riyoushaOrgDto: RiyoushaOrgDtoInterface;

    /** 利用者名 */
    riyoushaName: string;

    /** 利用者コード */
    riyoushaCode: number;

    /** 利用者権限 */
    riyoushaRole: string;

    constructor() {
        super();

        // 初期データ
        const INIT_STRING: string = "";
        const INIT_NUMBER: number = 0;

        this.riyoushaOrgDto = new RiyoushaOrgDto();
        this.riyoushaName = INIT_STRING
        this.riyoushaCode = INIT_NUMBER;
        this.riyoushaRole = INIT_STRING;
    }
}

export { type SaveRiyoushaOrgCapsuleDtoInterface, SaveRiyoushaOrgCapsuleDto }
