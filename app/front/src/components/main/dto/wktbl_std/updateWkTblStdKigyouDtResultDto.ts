import { WkTblKanrenshaKigyouDtMasterEntity, type WkTblKanrenshaKigyouDtMasterEntityInterface } from "../../entity/wkTblKanrenshaKigyouDtMasterEntity";
import { FrameworkResultDto, type FrameworkResultDtoInterface } from "../frameworkResultDto";

interface UpdateWkTblStdKigyouDtResultDtoInterface extends FrameworkResultDtoInterface {

    /** 編集対象Entity */
    wkTblMasterKigyouDtEntity: WkTblKanrenshaKigyouDtMasterEntityInterface;

}

/**
 * 企業／団体一括登録マスタ標準ワークテーブル更新ResultDto
 */
class UpdateWkTblStdKigyouDtResultDto extends FrameworkResultDto
    implements UpdateWkTblStdKigyouDtResultDtoInterface {

    /** 編集対象Entity */
    wkTblMasterKigyouDtEntity: WkTblKanrenshaKigyouDtMasterEntityInterface;

    constructor() {
        super();
        this.wkTblMasterKigyouDtEntity = new WkTblKanrenshaKigyouDtMasterEntity();
    }
}

export { type UpdateWkTblStdKigyouDtResultDtoInterface, UpdateWkTblStdKigyouDtResultDto }