import { WkTblKanrenshaKigyouDtMasterEntity, type WkTblKanrenshaKigyouDtMasterEntityInterface } from "../../entity/wkTblKanrenshaKigyouDtMasterEntity";
import { FrameworkMessageAndResultDto, type FrameworkMessageAndResultDtoInterface } from "seijishikin-jp-normalize_common-tool";

interface UpdateWkTblStdKigyouDtResultDtoInterface extends FrameworkMessageAndResultDtoInterface {

    /** 編集対象Entity */
    wkTblMasterKigyouDtEntity: WkTblKanrenshaKigyouDtMasterEntityInterface;

}

/**
 * 企業／団体一括登録マスタ標準ワークテーブル更新ResultDto
 */
class UpdateWkTblStdKigyouDtResultDto extends FrameworkMessageAndResultDto
    implements UpdateWkTblStdKigyouDtResultDtoInterface {

    /** 編集対象Entity */
    wkTblMasterKigyouDtEntity: WkTblKanrenshaKigyouDtMasterEntityInterface;

    constructor() {
        super();
        this.wkTblMasterKigyouDtEntity = new WkTblKanrenshaKigyouDtMasterEntity();
    }
}

export { type UpdateWkTblStdKigyouDtResultDtoInterface, UpdateWkTblStdKigyouDtResultDto }