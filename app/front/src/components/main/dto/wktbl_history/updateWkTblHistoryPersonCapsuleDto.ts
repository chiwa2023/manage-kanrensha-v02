import { WkTblKanrenshaPersonHistoryEntity, type WkTblKanrenshaPersonHistoryEntityInterface } from "../../entity/wkTblKanrenshaPersonHistoryEntity";
import { type FrameworkCapsuleDtoInterface ,FrameworkCapsuleDto } from "seijishikin-jp-normalize_common-tool";

interface UpdateWkTblHistoryPersonCapsuleDtoInterface extends FrameworkCapsuleDtoInterface {

    /** 編集対象Entity */
    wkTblKanrenshaPersonHistoryEntity: WkTblKanrenshaPersonHistoryEntityInterface;

}

/**
 * 個人一括登録履歴ワークテーブル更新CapsuleDto
 */
class UpdateWkTblHistoryPersonCapsuleDto extends FrameworkCapsuleDto
    implements UpdateWkTblHistoryPersonCapsuleDtoInterface {

    /** 編集対象Entity */
    wkTblKanrenshaPersonHistoryEntity: WkTblKanrenshaPersonHistoryEntityInterface;

    constructor() {
        super();
        this.wkTblKanrenshaPersonHistoryEntity = new WkTblKanrenshaPersonHistoryEntity();
    }

}
export{type UpdateWkTblHistoryPersonCapsuleDtoInterface,UpdateWkTblHistoryPersonCapsuleDto}