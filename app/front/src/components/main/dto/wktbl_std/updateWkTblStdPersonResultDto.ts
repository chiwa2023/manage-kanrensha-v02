import { WkTblKanrenshaPersonMasterEntity, type WkTblKanrenshaPersonMasterEntityInterface } from "../../entity/wkTblKanrenshaPersonMasterEntity";
import { FrameworkResultDto, type FrameworkResultDtoInterface } from "../frameworkResultDto";

interface UpdateWkTblStdPersonResultDtoInterface extends FrameworkResultDtoInterface {

    /** 編集対象Entity */
    wkTblMasterPersonEntity: WkTblKanrenshaPersonMasterEntityInterface;

}

/**
 * 個人一括登録マスタ標準ワークテーブル更新ResultDto
 */
class UpdateWkTblStdPersonResultDto extends FrameworkResultDto
    implements UpdateWkTblStdPersonResultDtoInterface {


    /** 編集対象Entity */
    wkTblMasterPersonEntity: WkTblKanrenshaPersonMasterEntityInterface;

    constructor() {
        super();
        this.wkTblMasterPersonEntity = new WkTblKanrenshaPersonMasterEntity();
    }
}

export { type UpdateWkTblStdPersonResultDtoInterface, UpdateWkTblStdPersonResultDto }