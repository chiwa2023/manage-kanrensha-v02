import { FrameworkCapsuleDto, type FrameworkCapsuleDtoInterface } from "seijishikin-jp-normalize_common-tool";
import { RiyoushaPartnerApiDto, type RiyoushaPartnerApiDtoInterface } from "./riyoushaPartnerApiDto";

interface SaveRiyoushaPartnerApiCapsuleDtoInterface extends FrameworkCapsuleDtoInterface {

    /** 利用者API接続Dto */
    riyoushaPartnerApiDto: RiyoushaPartnerApiDtoInterface;
}


class SaveRiyoushaPartnerApiCapsuleDto extends FrameworkCapsuleDto
    implements SaveRiyoushaPartnerApiCapsuleDtoInterface {

    /** 利用者API接続Dto */
    riyoushaPartnerApiDto: RiyoushaPartnerApiDtoInterface;

    constructor() {
        super();

        this.riyoushaPartnerApiDto = new RiyoushaPartnerApiDto();
    }
}

export { type SaveRiyoushaPartnerApiCapsuleDtoInterface, SaveRiyoushaPartnerApiCapsuleDto }
