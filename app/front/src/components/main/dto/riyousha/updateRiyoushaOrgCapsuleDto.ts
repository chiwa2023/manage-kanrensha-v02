import { FrameworkCapsuleDto, type FrameworkCapsuleDtoInterface } from "seijishikin-jp-normalize_common-tool";
import { RiyoushaOrgMasterEntity, type RiyoushaOrgMasterEntityInterface } from "../../entity/riyoushaOrgMasterEntity";

interface UpdateRiyoushaOrgCapsuleDtoInterface extends FrameworkCapsuleDtoInterface {
    /** 利用者組織マスタEntity */
    masterEntity: RiyoushaOrgMasterEntityInterface;
}


class UpdateRiyoushaOrgCapsuleDto extends FrameworkCapsuleDto
    implements UpdateRiyoushaOrgCapsuleDtoInterface {

    /** 利用者組織マスタEntity */
    masterEntity: RiyoushaOrgMasterEntityInterface;

    constructor() {
        super();
        this.masterEntity = new RiyoushaOrgMasterEntity();
    }

}

export { type UpdateRiyoushaOrgCapsuleDtoInterface, UpdateRiyoushaOrgCapsuleDto }
