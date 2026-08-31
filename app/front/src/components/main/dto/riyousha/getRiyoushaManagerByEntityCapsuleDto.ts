import { FrameworkCapsuleDto, type FrameworkCapsuleDtoInterface } from "seijishikin-jp-normalize_common-tool";
import { RiyoushaManagerMasterEntity, type RiyoushaManagerMasterEntityInterface } from "../../entity/riyoushaManagerMasterEntity";

interface GetRiyoushaManagerByEntityCapsuleDtoInterface extends FrameworkCapsuleDtoInterface {

    /** 利用者運営者マスタEntity */
    masterEntity: RiyoushaManagerMasterEntityInterface;
}

class GetRiyoushaManagerByEntityCapsuleDto extends FrameworkCapsuleDto implements GetRiyoushaManagerByEntityCapsuleDtoInterface {

    /** 利用者運営者マスタEntity */
    masterEntity: RiyoushaManagerMasterEntityInterface;

    constructor() {
        super();

        this.masterEntity = new RiyoushaManagerMasterEntity();
    }
}

export { type GetRiyoushaManagerByEntityCapsuleDtoInterface, GetRiyoushaManagerByEntityCapsuleDto }
