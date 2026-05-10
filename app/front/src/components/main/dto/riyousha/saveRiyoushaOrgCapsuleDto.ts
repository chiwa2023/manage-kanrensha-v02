import { FrameworkCapsuleDto, type FrameworkCapsuleDtoInterface } from "seijishikin-jp-normalize_common-tool";
import { RiyoushaOrgDto, type RiyoushaOrgDtoInterface } from "./riyoushaOrgDto";

interface SaveRiyoushaOrgCapsuleDtoInterface extends FrameworkCapsuleDtoInterface {
    /** 利用者運営者Dto */
    riyoushaOrgDto: RiyoushaOrgDtoInterface;

}

class SaveRiyoushaOrgCapsuleDto extends FrameworkCapsuleDto
    implements SaveRiyoushaOrgCapsuleDtoInterface {

    /** 利用者運営者Dto */
    riyoushaOrgDto: RiyoushaOrgDtoInterface;

    constructor() {
        super();

        this.riyoushaOrgDto = new RiyoushaOrgDto();
    }
}

export { type SaveRiyoushaOrgCapsuleDtoInterface, SaveRiyoushaOrgCapsuleDto }
