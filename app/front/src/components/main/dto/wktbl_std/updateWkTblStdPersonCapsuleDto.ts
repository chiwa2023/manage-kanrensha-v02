import { WkTblKanrenshaPersonMasterEntity, type WkTblKanrenshaPersonMasterEntityInterface } from "../../entity/wkTblKanrenshaPersonMasterEntity";
import { type FrameworkCapsuleDtoInterface ,FrameworkCapsuleDto } from "seijishikin-jp-normalize_common-tool";

interface UpdateWkTblStdPersonCapsuleDtoInterface extends FrameworkCapsuleDtoInterface {

    /** 編集対象Entity */
    wkTblMasterPersonEntity: WkTblKanrenshaPersonMasterEntityInterface;

}

/**
 * 個人一括登録マスタ標準ワークテーブル更新CapsuleDto
 */
class UpdateWkTblStdPersonCapsuleDto extends FrameworkCapsuleDto
    implements UpdateWkTblStdPersonCapsuleDtoInterface {

    /** 編集対象Entity */
    wkTblMasterPersonEntity: WkTblKanrenshaPersonMasterEntityInterface;
    constructor() {
        super();
        this.wkTblMasterPersonEntity = new WkTblKanrenshaPersonMasterEntity();
    }
}

export { type UpdateWkTblStdPersonCapsuleDtoInterface, UpdateWkTblStdPersonCapsuleDto }