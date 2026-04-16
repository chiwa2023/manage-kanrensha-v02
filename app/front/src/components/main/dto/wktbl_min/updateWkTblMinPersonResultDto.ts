import { WkTblKanrenshaPersonAddMinEntity, type WkTblKanrenshaPersonAddMinEntityInterface } from "../../entity/wkTblKanrenshaPersonAddMinEntity";
import { FrameworkMessageAndResultDto, type FrameworkMessageAndResultDtoInterface } from "seijishikin-jp-normalize_common-tool";

interface UpdateWkTblMinPersonResultDtoInterface extends FrameworkMessageAndResultDtoInterface {

    /** 編集対象Entity */
    wkTblKanrenshaPersonAddMinEntity: WkTblKanrenshaPersonAddMinEntityInterface;

}

/**
 * 個人一括登録マスタ最小ワークテーブル更新ResultDto
 */
class UpdateWkTblMinPersonResultDto extends FrameworkMessageAndResultDto
    implements UpdateWkTblMinPersonResultDtoInterface {

    /** 編集対象Entity */
    wkTblKanrenshaPersonAddMinEntity: WkTblKanrenshaPersonAddMinEntityInterface;

    constructor() {
        super();
        this.wkTblKanrenshaPersonAddMinEntity = new WkTblKanrenshaPersonAddMinEntity();
    }
}

export { type UpdateWkTblMinPersonResultDtoInterface, UpdateWkTblMinPersonResultDto }
