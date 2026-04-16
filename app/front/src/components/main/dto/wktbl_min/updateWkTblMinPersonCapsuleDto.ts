import { WkTblKanrenshaPersonAddMinEntity, type WkTblKanrenshaPersonAddMinEntityInterface } from "../../entity/wkTblKanrenshaPersonAddMinEntity";
import { type FrameworkCapsuleDtoInterface ,FrameworkCapsuleDto } from "seijishikin-jp-normalize_common-tool";

interface UpdateWkTblMinPersonCapsuleDtoInterface extends FrameworkCapsuleDtoInterface {

    /** 編集対象Entity */
    wkTblKanrenshaPersonAddMinEntity: WkTblKanrenshaPersonAddMinEntityInterface;

}

/**
 * 個人一括登録マスタ最小ワークテーブル更新CapsuleDto
 */
class UpdateWkTblMinPersonCapsuleDto extends FrameworkCapsuleDto
    implements UpdateWkTblMinPersonCapsuleDtoInterface {

    /** 編集対象Entity */
    wkTblKanrenshaPersonAddMinEntity: WkTblKanrenshaPersonAddMinEntityInterface;

    constructor() {
        super();
        this.wkTblKanrenshaPersonAddMinEntity = new WkTblKanrenshaPersonAddMinEntity();
    }
}

export { type UpdateWkTblMinPersonCapsuleDtoInterface, UpdateWkTblMinPersonCapsuleDto }