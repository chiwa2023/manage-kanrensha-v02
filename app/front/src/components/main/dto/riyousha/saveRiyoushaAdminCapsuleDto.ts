import { FrameworkCapsuleDto, type FrameworkCapsuleDtoInterface } from "seijishikin-jp-normalize_common-tool";
import { RiyoushaAdminDto, type RiyoushaAdminDtoInterface } from "./riyoushaAdminDto";

interface SaveRiyoushaAdminCapsuleDtoInterface extends FrameworkCapsuleDtoInterface {

    /** 利用者運営者Dto */
    riyoushaAdminDto: RiyoushaAdminDtoInterface;

}


class SaveRiyoushaAdminCapsuleDto extends FrameworkCapsuleDto
    implements SaveRiyoushaAdminCapsuleDtoInterface {

    /** 利用者運営者Dto */
    riyoushaAdminDto: RiyoushaAdminDtoInterface;

    constructor() {
        super();

        this.riyoushaAdminDto = new RiyoushaAdminDto();
    }
}

export { type SaveRiyoushaAdminCapsuleDtoInterface, SaveRiyoushaAdminCapsuleDto }
