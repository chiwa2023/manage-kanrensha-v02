import { WkTblKanrenshaSeijidantaiAddMinEntity, type WkTblKanrenshaSeijidantaiAddMinEntityInterface } from "../../entity/wkTblKanrenshaSeijidantaiAddMinEntity";
import { FrameworkResultDto, type FrameworkResultDtoInterface } from "../frameworkResultDto";

interface UpdateWkTblMinSeijidantaiResultDtoInterface extends FrameworkResultDtoInterface {

    /** 編集対象Entity */
    wkTblKanrenshaSeijidantaiAddMinEntity: WkTblKanrenshaSeijidantaiAddMinEntityInterface;

}
/**
 * 政治団体一括登録マスタ最小ワークテーブル更新ResultDto
 */
class UpdateWkTblMinSeijidantaiResultDto extends FrameworkResultDto
    implements UpdateWkTblMinSeijidantaiResultDtoInterface {

    /** 編集対象Entity */
    wkTblKanrenshaSeijidantaiAddMinEntity: WkTblKanrenshaSeijidantaiAddMinEntityInterface;

    constructor() {
        super();
        this.wkTblKanrenshaSeijidantaiAddMinEntity = new WkTblKanrenshaSeijidantaiAddMinEntity();
    }
}

export { type UpdateWkTblMinSeijidantaiResultDtoInterface, UpdateWkTblMinSeijidantaiResultDto }