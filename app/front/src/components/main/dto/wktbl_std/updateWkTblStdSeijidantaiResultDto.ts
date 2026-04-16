import { WkTblKanrenshaSeijidantaiMasterEntity, type WkTblKanrenshaSeijidantaiMasterEntityInterface } from "../../entity/wkTblKanrenshaSeijidantaiMasterEntity";
import { FrameworkMessageAndResultDto, type FrameworkMessageAndResultDtoInterface } from "seijishikin-jp-normalize_common-tool";

interface UpdateWkTblStdSeijidantaiResultDtoInterface extends FrameworkMessageAndResultDtoInterface {

    /** 編集対象Entity */
    wkTblMasterSeijidantaiEntity: WkTblKanrenshaSeijidantaiMasterEntityInterface;

}
/**
 * 政治団体一括登録マスタ標準ワークテーブル更新ResultDto
 */
class UpdateWkTblStdSeijidantaiResultDto extends FrameworkMessageAndResultDto
    implements UpdateWkTblStdSeijidantaiResultDtoInterface {

    /** 編集対象Entity */
    wkTblMasterSeijidantaiEntity: WkTblKanrenshaSeijidantaiMasterEntityInterface;

    constructor() {
        super();
        this.wkTblMasterSeijidantaiEntity = new WkTblKanrenshaSeijidantaiMasterEntity();
    }
}

export { type UpdateWkTblStdSeijidantaiResultDtoInterface, UpdateWkTblStdSeijidantaiResultDto }
