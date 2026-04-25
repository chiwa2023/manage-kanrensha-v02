import { WkTblKanrenshaKigyouDtMasterEntity, type WkTblKanrenshaKigyouDtMasterEntityInterface } from "../../entity/wkTblKanrenshaKigyouDtMasterEntity";
import { type FrameworkCapsuleDtoInterface, FrameworkCapsuleDto } from "seijishikin-jp-normalize_common-tool";

interface UpdateWkTblStdKigyouDtCapsuleDtoInterface extends FrameworkCapsuleDtoInterface {

    /** 編集対象Entity */
    wkTblKanrenshaKigyouDtMasterEntity: WkTblKanrenshaKigyouDtMasterEntityInterface;

}

/**
 * 企業／団体一括登録マスタ標準ワークテーブル更新CapsuleDto
 */
class UpdateWkTblStdKigyouDtCapsuleDto extends FrameworkCapsuleDto
    implements UpdateWkTblStdKigyouDtCapsuleDtoInterface {

    /** 編集対象Entity */
    wkTblKanrenshaKigyouDtMasterEntity: WkTblKanrenshaKigyouDtMasterEntityInterface;

    constructor() {
        super();
        this.wkTblKanrenshaKigyouDtMasterEntity = new WkTblKanrenshaKigyouDtMasterEntity();
    }
}

export { type UpdateWkTblStdKigyouDtCapsuleDtoInterface, UpdateWkTblStdKigyouDtCapsuleDto }
