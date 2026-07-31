import { FrameworkCapsuleDto, type FrameworkCapsuleDtoInterface } from "seijishikin-jp-normalize_common-tool";
import { RiyoushaOrgMasterEntity, type RiyoushaOrgMasterEntityInterface } from "../../entity/riyoushaOrgMasterEntity";

interface GetRiyoushaOrgByEntityCapsuleDtoInterface extends FrameworkCapsuleDtoInterface {

    /** 利用者組織マスタEntity */
    masterEntity: RiyoushaOrgMasterEntityInterface;
}

class GetRiyoushaOrgByEntityCapsuleDto extends FrameworkCapsuleDto implements GetRiyoushaOrgByEntityCapsuleDtoInterface {

    /** 利用者組織マスタEntity */
    masterEntity: RiyoushaOrgMasterEntityInterface;

    constructor() {
        super();
        this.masterEntity = new RiyoushaOrgMasterEntity();
    }

}

export { type GetRiyoushaOrgByEntityCapsuleDtoInterface, GetRiyoushaOrgByEntityCapsuleDto }
