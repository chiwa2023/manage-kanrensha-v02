import { FrameworkCapsuleDto, type FrameworkCapsuleDtoInterface } from "seijishikin-jp-normalize_common-tool";
import { WkTblKanrenshaCombineOrgEntity, type WkTblKanrenshaCombineOrgEntityInterface } from "../../entity/wkTblKanrenshaCombineOrgEntity";

interface UpdateWkTblCombineOrgCapsuleDtoInterface extends FrameworkCapsuleDtoInterface {
    /** 編集対象Entity */
    wkTblKanrenshaCombineOrgEntity: WkTblKanrenshaCombineOrgEntityInterface;

}

class UpdateWkTblCombineOrgCapsuleDto extends FrameworkCapsuleDto implements UpdateWkTblCombineOrgCapsuleDtoInterface {

    /** 編集対象Entity */
    wkTblKanrenshaCombineOrgEntity: WkTblKanrenshaCombineOrgEntityInterface;

    constructor() {
        super();
        this.wkTblKanrenshaCombineOrgEntity = new WkTblKanrenshaCombineOrgEntity();
    }

}

export { type UpdateWkTblCombineOrgCapsuleDtoInterface, UpdateWkTblCombineOrgCapsuleDto }
