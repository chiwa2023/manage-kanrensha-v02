import { FrameworkMessageAndResultDto, type FrameworkMessageAndResultDtoInterface } from "seijishikin-jp-normalize_common-tool";

interface PartnerApiTokenResultDtoInterface extends FrameworkMessageAndResultDtoInterface {

    /**　トークン */
    token: string;
}

class PartnerApiTokenResultDto extends FrameworkMessageAndResultDto implements PartnerApiTokenResultDtoInterface {

    /**　トークン */
    token: string;

    constructor() {
        super();
        this.token = "";
    }
}

export { type PartnerApiTokenResultDtoInterface, PartnerApiTokenResultDto }