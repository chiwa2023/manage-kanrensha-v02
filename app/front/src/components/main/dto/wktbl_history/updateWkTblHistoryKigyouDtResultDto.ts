import { WkTblKanrenshaKigyouDtHistoryEntity, type WkTblKanrenshaKigyouDtHistoryEntityInterface } from "../../entity/wkTblKanrenshaKigyouDtHistoryEntity";
import { FrameworkResultDto, type FrameworkResultDtoInterface } from "../frameworkResultDto";

interface UpdateWkTblHistoryKigyouDtResultDtoInterface extends FrameworkResultDtoInterface {

    /** 編集対象Entity */
    wkTblKanrenshaKigyouDtHistoryEntity: WkTblKanrenshaKigyouDtHistoryEntityInterface;

}

/**
 * 企業／団体一括登録履歴ワークテーブル更新ResultDto
 */
class UpdateWkTblHistoryKigyouDtResultDto extends FrameworkResultDto
    implements UpdateWkTblHistoryKigyouDtResultDtoInterface {

    /** 編集対象Entity */
    wkTblKanrenshaKigyouDtHistoryEntity: WkTblKanrenshaKigyouDtHistoryEntityInterface;

    constructor() {
        super();
        this.wkTblKanrenshaKigyouDtHistoryEntity = new WkTblKanrenshaKigyouDtHistoryEntity();
    }
}

export { type UpdateWkTblHistoryKigyouDtResultDtoInterface, UpdateWkTblHistoryKigyouDtResultDto }