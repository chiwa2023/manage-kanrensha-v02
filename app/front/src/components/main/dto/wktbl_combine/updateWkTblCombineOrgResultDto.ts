import { FrameworkMessageAndResultDto, type FrameworkMessageAndResultDtoInterface } from "seijishikin-jp-normalize_common-tool";
import { WkTblKanrenshaCombineOrgEntity, type WkTblKanrenshaCombineOrgEntityInterface } from "../../entity/wkTblKanrenshaCombineOrgEntity";

interface UpdateWkTblCombineOrgResultDtoInterface extends FrameworkMessageAndResultDtoInterface {
    /** 編集対象Entity */
    wkTblKanrenshaCombineOrgEntity: WkTblKanrenshaCombineOrgEntityInterface;
}

class UpdateWkTblCombineOrgResultDto extends FrameworkMessageAndResultDto implements UpdateWkTblCombineOrgResultDtoInterface {

    /** 編集対象Entity */
    wkTblKanrenshaCombineOrgEntity: WkTblKanrenshaCombineOrgEntityInterface;

    constructor() {
        super();
        this.wkTblKanrenshaCombineOrgEntity = new WkTblKanrenshaCombineOrgEntity();
    }

}

export { type UpdateWkTblCombineOrgResultDtoInterface, UpdateWkTblCombineOrgResultDto }
