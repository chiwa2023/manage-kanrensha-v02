import { FrameworkCapsuleDto, type FrameworkCapsuleDtoInterface } from "seijishikin-jp-normalize_common-tool";

interface PartnerApiTokenCapsuleDtoInterface extends FrameworkCapsuleDtoInterface {

    /** IPアドレス */
    ipAddress: string;

}

class PartnerApiTokenCapsuleDto extends FrameworkCapsuleDto implements PartnerApiTokenCapsuleDtoInterface {

    /** IPアドレス */
    ipAddress: string;

    constructor() {
        super();
        this.ipAddress = "";
    }
}

export { type PartnerApiTokenCapsuleDtoInterface, PartnerApiTokenCapsuleDto }