import { WkTblKanrenshaPersonMasterEntity, type WkTblKanrenshaPersonMasterEntityInterface } from "../../entity/wkTblKanrenshaPersonMasterEntity";
import { FrameworkMessageAndResultDto, type FrameworkMessageAndResultDtoInterface } from "seijishikin-jp-normalize_common-tool";

interface UpdateWkTblStdPersonResultDtoInterface extends FrameworkMessageAndResultDtoInterface {

    /** 編集対象Entity */
    wkTblMasterPersonEntity: WkTblKanrenshaPersonMasterEntityInterface;

}

/**
 * 個人一括登録マスタ標準ワークテーブル更新ResultDto
 */
class UpdateWkTblStdPersonResultDto extends FrameworkMessageAndResultDto
    implements UpdateWkTblStdPersonResultDtoInterface {


    /** 編集対象Entity */
    wkTblMasterPersonEntity: WkTblKanrenshaPersonMasterEntityInterface;

    constructor() {
        super();
        this.wkTblMasterPersonEntity = new WkTblKanrenshaPersonMasterEntity();
    }
}

export { type UpdateWkTblStdPersonResultDtoInterface, UpdateWkTblStdPersonResultDto }