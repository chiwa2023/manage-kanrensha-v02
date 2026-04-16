import { WkTblKanrenshaSeijidantaiAddMinEntity, type WkTblKanrenshaSeijidantaiAddMinEntityInterface } from "../../entity/wkTblKanrenshaSeijidantaiAddMinEntity";
import { FrameworkMessageAndResultDto, type FrameworkMessageAndResultDtoInterface } from "seijishikin-jp-normalize_common-tool";

interface UpdateWkTblMinSeijidantaiResultDtoInterface extends FrameworkMessageAndResultDtoInterface {

    /** 編集対象Entity */
    wkTblKanrenshaSeijidantaiAddMinEntity: WkTblKanrenshaSeijidantaiAddMinEntityInterface;

}
/**
 * 政治団体一括登録マスタ最小ワークテーブル更新ResultDto
 */
class UpdateWkTblMinSeijidantaiResultDto extends FrameworkMessageAndResultDto
    implements UpdateWkTblMinSeijidantaiResultDtoInterface {

    /** 編集対象Entity */
    wkTblKanrenshaSeijidantaiAddMinEntity: WkTblKanrenshaSeijidantaiAddMinEntityInterface;

    constructor() {
        super();
        this.wkTblKanrenshaSeijidantaiAddMinEntity = new WkTblKanrenshaSeijidantaiAddMinEntity();
    }
}

export { type UpdateWkTblMinSeijidantaiResultDtoInterface, UpdateWkTblMinSeijidantaiResultDto }