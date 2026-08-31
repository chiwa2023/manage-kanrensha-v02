import { FrameworkCapsuleDto, type FrameworkCapsuleDtoInterface } from "seijishikin-jp-normalize_common-tool";
import { RiyoushaPartnerApiMasterEntity, type RiyoushaPartnerApiMasterEntityInterface } from "../../entity/riyoushaPartnerApiMasterEntity";

interface GetRiyoushaPartnerApiByEntityCapsuleDtoInterface extends FrameworkCapsuleDtoInterface {

    /** 利用者APIパートナーマスタEntity */
    masterEntity: RiyoushaPartnerApiMasterEntityInterface;
}

class GetRiyoushaPartnerApiByEntityCapsuleDto extends FrameworkCapsuleDto implements GetRiyoushaPartnerApiByEntityCapsuleDtoInterface {

    /** 利用者APIパートナーマスタEntity */
    masterEntity: RiyoushaPartnerApiMasterEntityInterface;

    constructor() {
        super();
        this.masterEntity = new RiyoushaPartnerApiMasterEntity();
    }
}

export { type GetRiyoushaPartnerApiByEntityCapsuleDtoInterface, GetRiyoushaPartnerApiByEntityCapsuleDto }
