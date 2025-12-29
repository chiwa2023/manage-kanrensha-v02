import { WkTblKanrenshaPersonHistoryEntity, type WkTblKanrenshaPersonHistoryEntityInterface } from "../../entity/wkTblKanrenshaPersonHistoryEntity";
import { FrameworkResultDto, type FrameworkResultDtoInterface } from "../frameworkResultDto";

interface UpdateWkTblHistoryPersonResultDtoInterface extends FrameworkResultDtoInterface {

    /** 編集対象Entity */
    wkTblKanrenshaPersonHistoryEntity: WkTblKanrenshaPersonHistoryEntityInterface;

}

/**
 * 個人一括登録履歴ワークテーブル更新CapsuleDto
 */
class UpdateWkTblHistoryPersonResultDto extends FrameworkResultDto
    implements UpdateWkTblHistoryPersonResultDtoInterface {

    /** 編集対象Entity */
    wkTblKanrenshaPersonHistoryEntity: WkTblKanrenshaPersonHistoryEntityInterface;

    constructor() {
        super();
        this.wkTblKanrenshaPersonHistoryEntity = new WkTblKanrenshaPersonHistoryEntity();
    }

}

export { type UpdateWkTblHistoryPersonResultDtoInterface, UpdateWkTblHistoryPersonResultDto }