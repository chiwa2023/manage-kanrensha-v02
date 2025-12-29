import { WkTblKanrenshaSeijidantaiMasterEntity, type WkTblKanrenshaSeijidantaiMasterEntityInterface } from "../../entity/wkTblKanrenshaSeijidantaiMasterEntity";
import { FrameworkResultDto, type FrameworkResultDtoInterface } from "../frameworkResultDto";

interface UpdateWkTblStdSeijidantaiResultDtoInterface extends FrameworkResultDtoInterface {

    /** 編集対象Entity */
    wkTblMasterSeijidantaiEntity: WkTblKanrenshaSeijidantaiMasterEntityInterface;

}
/**
 * 政治団体一括登録マスタ標準ワークテーブル更新ResultDto
 */
class UpdateWkTblStdSeijidantaiResultDto extends FrameworkResultDto
    implements UpdateWkTblStdSeijidantaiResultDtoInterface {

    /** 編集対象Entity */
    wkTblMasterSeijidantaiEntity: WkTblKanrenshaSeijidantaiMasterEntityInterface;

    constructor() {
        super();
        this.wkTblMasterSeijidantaiEntity = new WkTblKanrenshaSeijidantaiMasterEntity();
    }
}

export { type UpdateWkTblStdSeijidantaiResultDtoInterface, UpdateWkTblStdSeijidantaiResultDto }
