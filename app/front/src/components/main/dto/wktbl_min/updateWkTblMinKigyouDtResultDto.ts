import { WkTblKanrenshaKigyouDtAddMinEntity, type WkTblKanrenshaKigyouDtAddMinEntityInterface } from "../../entity/wkTblKanrenshaKigyouDtAddMinEntity";
import { FrameworkResultDto, type FrameworkResultDtoInterface } from "../frameworkResultDto";

interface UpdateWkTblMinKigyouDtResultDtoInterface extends FrameworkResultDtoInterface {

    /** 編集対象Entity */
    wkTblKanrenshaKigyouDtAddMinEntity: WkTblKanrenshaKigyouDtAddMinEntityInterface;

}


/**
 * 企業／団体一括登録マスタ最小ワークテーブル更新ResultDto
 */
class UpdateWkTblMinKigyouDtResultDto extends FrameworkResultDto
    implements UpdateWkTblMinKigyouDtResultDtoInterface {

    /** 編集対象Entity */
    wkTblKanrenshaKigyouDtAddMinEntity: WkTblKanrenshaKigyouDtAddMinEntityInterface;

    constructor() {
        super();
        this.wkTblKanrenshaKigyouDtAddMinEntity = new WkTblKanrenshaKigyouDtAddMinEntity();
    }

}

export { type UpdateWkTblMinKigyouDtResultDtoInterface, UpdateWkTblMinKigyouDtResultDto }
