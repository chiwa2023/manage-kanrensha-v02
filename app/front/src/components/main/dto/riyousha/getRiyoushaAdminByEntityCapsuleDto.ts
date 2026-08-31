import { FrameworkCapsuleDto, type FrameworkCapsuleDtoInterface } from "seijishikin-jp-normalize_common-tool";
import { RiyoushaAdminMasterEntity, type RiyoushaAdminMasterEntityInterface } from "../../entity/riyoushaAdminMasterEntity";

interface GetRiyoushaAdminByEntityCapsuleDtoInterface extends FrameworkCapsuleDtoInterface {

    /** 利用者SE権限マスタEntity */
    masterEntity: RiyoushaAdminMasterEntityInterface;

}

class GetRiyoushaAdminByEntityCapsuleDto extends FrameworkCapsuleDto implements GetRiyoushaAdminByEntityCapsuleDtoInterface {

    /** 利用者SE権限マスタEntity */
    masterEntity: RiyoushaAdminMasterEntityInterface;

    constructor() {
        super();

        this.masterEntity = new RiyoushaAdminMasterEntity();
    }
}

export { type GetRiyoushaAdminByEntityCapsuleDtoInterface, GetRiyoushaAdminByEntityCapsuleDto }
