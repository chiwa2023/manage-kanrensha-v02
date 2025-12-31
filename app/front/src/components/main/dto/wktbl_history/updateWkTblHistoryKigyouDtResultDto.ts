import { WkTblKanrenshaKigyouDtHistoryEntity, type WkTblKanrenshaKigyouDtHistoryEntityInterface } from "../../entity/wkTblKanrenshaKigyouDtHistoryEntity";
import { FrameworkMessageAndResultDto, type FrameworkMessageAndResultDtoInterface } from "seijishikin-jp-normalize_common-tool";

interface UpdateWkTblHistoryKigyouDtResultDtoInterface extends FrameworkMessageAndResultDtoInterface {

    /** 編集対象Entity */
    wkTblKanrenshaKigyouDtHistoryEntity: WkTblKanrenshaKigyouDtHistoryEntityInterface;

}

/**
 * 企業／団体一括登録履歴ワークテーブル更新ResultDto
 */
class UpdateWkTblHistoryKigyouDtResultDto extends FrameworkMessageAndResultDto
    implements UpdateWkTblHistoryKigyouDtResultDtoInterface {

    /** 編集対象Entity */
    wkTblKanrenshaKigyouDtHistoryEntity: WkTblKanrenshaKigyouDtHistoryEntityInterface;

    constructor() {
        super();
        this.wkTblKanrenshaKigyouDtHistoryEntity = new WkTblKanrenshaKigyouDtHistoryEntity();
    }
}

export { type UpdateWkTblHistoryKigyouDtResultDtoInterface, UpdateWkTblHistoryKigyouDtResultDto }