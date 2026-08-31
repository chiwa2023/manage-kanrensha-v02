import { WkTblKanrenshaSeijidantaiHistoryEntity, type WkTblKanrenshaSeijidantaiHistoryEntityInterface } from "../../entity/wkTblKanrenshaSeijidantaiHistoryEntity";
import { FrameworkMessageAndResultDto, type FrameworkMessageAndResultDtoInterface } from "seijishikin-jp-normalize_common-tool";


interface UpdateWkTblHistorySeijidantaiResultDtoInterface extends FrameworkMessageAndResultDtoInterface {
    /** 編集対象Entity */
    wkTblKanrenshaSeijidantaiHistoryEntity: WkTblKanrenshaSeijidantaiHistoryEntityInterface;
}

/**
 * 政治団体一括登録履歴ワークテーブル更新ResultDto
 */
class UpdateWkTblHistorySeijidantaiResultDto extends FrameworkMessageAndResultDto
    implements UpdateWkTblHistorySeijidantaiResultDtoInterface {

    /** 編集対象Entity */
    wkTblKanrenshaSeijidantaiHistoryEntity: WkTblKanrenshaSeijidantaiHistoryEntityInterface;

    constructor() {
        super();
        this.wkTblKanrenshaSeijidantaiHistoryEntity = new WkTblKanrenshaSeijidantaiHistoryEntity();
    }

}

export { type UpdateWkTblHistorySeijidantaiResultDtoInterface, UpdateWkTblHistorySeijidantaiResultDto }