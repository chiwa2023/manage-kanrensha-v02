import { WkTblKanrenshaPersonHistoryEntity, type WkTblKanrenshaPersonHistoryEntityInterface } from "../../entity/wkTblKanrenshaPersonHistoryEntity";
import { FrameworkMessageAndResultDto, type FrameworkMessageAndResultDtoInterface } from "seijishikin-jp-normalize_common-tool";

interface UpdateWkTblHistoryPersonResultDtoInterface extends FrameworkMessageAndResultDtoInterface {

    /** 編集対象Entity */
    wkTblKanrenshaPersonHistoryEntity: WkTblKanrenshaPersonHistoryEntityInterface;

}

/**
 * 個人一括登録履歴ワークテーブル更新CapsuleDto
 */
class UpdateWkTblHistoryPersonResultDto extends FrameworkMessageAndResultDto
    implements UpdateWkTblHistoryPersonResultDtoInterface {

    /** 編集対象Entity */
    wkTblKanrenshaPersonHistoryEntity: WkTblKanrenshaPersonHistoryEntityInterface;

    constructor() {
        super();
        this.wkTblKanrenshaPersonHistoryEntity = new WkTblKanrenshaPersonHistoryEntity();
    }

}

export { type UpdateWkTblHistoryPersonResultDtoInterface, UpdateWkTblHistoryPersonResultDto }