import { WkTblKanrenshaKigyouDtHistoryEntity, type WkTblKanrenshaKigyouDtHistoryEntityInterface } from "../../entity/wkTblKanrenshaKigyouDtHistoryEntity";
import { FrameworkCapsuleDto, type FrameworkCapsuleDtoInterface } from "../frameworkCapsuleDto";

interface UpdateWkTblHistoryKigyouDtCapsuleDtoInterface extends FrameworkCapsuleDtoInterface {

    /** 編集対象Entity */
    wkTblKanrenshaKigyouDtHistoryEntity: WkTblKanrenshaKigyouDtHistoryEntityInterface;

}

/**
 * 企業／団体一括登録履歴ワークテーブル更新CapsuleDto
 */
class UpdateWkTblHistoryKigyouDtCapsuleDto extends FrameworkCapsuleDto
    implements UpdateWkTblHistoryKigyouDtCapsuleDtoInterface {

    /** 編集対象Entity */
    wkTblKanrenshaKigyouDtHistoryEntity: WkTblKanrenshaKigyouDtHistoryEntityInterface;

    constructor() {
        super();
        this.wkTblKanrenshaKigyouDtHistoryEntity = new WkTblKanrenshaKigyouDtHistoryEntity();
    }
}

export { type UpdateWkTblHistoryKigyouDtCapsuleDtoInterface, UpdateWkTblHistoryKigyouDtCapsuleDto }