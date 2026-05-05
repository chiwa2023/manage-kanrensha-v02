import { FrameworkCapsuleDto, type FrameworkCapsuleDtoInterface } from "seijishikin-jp-normalize_common-tool";
import { RiyoushaManagerDto, type RiyoushaManagerDtoInterface } from "./riyoushaManagerDto";

interface SaveRiyoushaManagerCapsuleDtoInterface extends FrameworkCapsuleDtoInterface {

    /** 利用者運営者Dto */
    riyoushaManagerDto: RiyoushaManagerDtoInterface;
}


class SaveRiyoushaManagerCapsuleDto extends FrameworkCapsuleDto
    implements SaveRiyoushaManagerCapsuleDtoInterface {

    /** 利用者運営者Dto */
    riyoushaManagerDto: RiyoushaManagerDtoInterface;

    constructor() {
        super();

        this.riyoushaManagerDto = new RiyoushaManagerDto();
    }
}

export { type SaveRiyoushaManagerCapsuleDtoInterface, SaveRiyoushaManagerCapsuleDto }
