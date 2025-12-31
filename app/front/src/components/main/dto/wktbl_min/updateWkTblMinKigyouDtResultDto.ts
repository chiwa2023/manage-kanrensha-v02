import { WkTblKanrenshaKigyouDtAddMinEntity, type WkTblKanrenshaKigyouDtAddMinEntityInterface } from "../../entity/wkTblKanrenshaKigyouDtAddMinEntity";
import { FrameworkMessageAndResultDto, type FrameworkMessageAndResultDtoInterface } from "seijishikin-jp-normalize_common-tool";

interface UpdateWkTblMinKigyouDtResultDtoInterface extends FrameworkMessageAndResultDtoInterface {

    /** 編集対象Entity */
    wkTblKanrenshaKigyouDtAddMinEntity: WkTblKanrenshaKigyouDtAddMinEntityInterface;

}


/**
 * 企業／団体一括登録マスタ最小ワークテーブル更新ResultDto
 */
class UpdateWkTblMinKigyouDtResultDto extends FrameworkMessageAndResultDto
    implements UpdateWkTblMinKigyouDtResultDtoInterface {

    /** 編集対象Entity */
    wkTblKanrenshaKigyouDtAddMinEntity: WkTblKanrenshaKigyouDtAddMinEntityInterface;

    constructor() {
        super();
        this.wkTblKanrenshaKigyouDtAddMinEntity = new WkTblKanrenshaKigyouDtAddMinEntity();
    }

}

export { type UpdateWkTblMinKigyouDtResultDtoInterface, UpdateWkTblMinKigyouDtResultDto }
