import { WkTblKanrenshaPersonAddMinEntity, type WkTblKanrenshaPersonAddMinEntityInterface } from "../../entity/wkTblKanrenshaPersonAddMinEntity";
import { FrameworkResultDto, type FrameworkResultDtoInterface } from "../frameworkResultDto";

interface UpdateWkTblMinPersonResultDtoInterface extends FrameworkResultDtoInterface {

    /** 編集対象Entity */
    wkTblKanrenshaPersonAddMinEntity: WkTblKanrenshaPersonAddMinEntityInterface;

}

/**
 * 個人一括登録マスタ最小ワークテーブル更新ResultDto
 */
class UpdateWkTblMinPersonResultDto extends FrameworkResultDto
    implements UpdateWkTblMinPersonResultDtoInterface {

    /** 編集対象Entity */
    wkTblKanrenshaPersonAddMinEntity: WkTblKanrenshaPersonAddMinEntityInterface;

    constructor() {
        super();
        this.wkTblKanrenshaPersonAddMinEntity = new WkTblKanrenshaPersonAddMinEntity();
    }
}

export { type UpdateWkTblMinPersonResultDtoInterface, UpdateWkTblMinPersonResultDto }
