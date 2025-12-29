import { WkTblKanrenshaSeijidantaiHistoryEntity, type WkTblKanrenshaSeijidantaiHistoryEntityInterface } from "../../entity/wkTblKanrenshaSeijidantaiHistoryEntity";
import { FrameworkResultDto } from "../frameworkResultDto";


interface UpdateWkTblHistorySeijidantaiResultDtoInterface {
    /** 編集対象Entity */
    wkTblKanrenshaSeijidantaiHistoryEntity: WkTblKanrenshaSeijidantaiHistoryEntityInterface;
}

/**
 * 政治団体一括登録履歴ワークテーブル更新ResultDto
 */
class UpdateWkTblHistorySeijidantaiResultDto extends FrameworkResultDto
    implements UpdateWkTblHistorySeijidantaiResultDtoInterface {

    /** 編集対象Entity */
    wkTblKanrenshaSeijidantaiHistoryEntity: WkTblKanrenshaSeijidantaiHistoryEntityInterface;

    constructor() {
        super();
        this.wkTblKanrenshaSeijidantaiHistoryEntity = new WkTblKanrenshaSeijidantaiHistoryEntity();
    }

}

export { type UpdateWkTblHistorySeijidantaiResultDtoInterface, UpdateWkTblHistorySeijidantaiResultDto }