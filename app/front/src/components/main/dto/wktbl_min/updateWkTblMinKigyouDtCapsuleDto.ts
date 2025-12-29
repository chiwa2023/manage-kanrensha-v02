import { WkTblKanrenshaKigyouDtAddMinEntity, type WkTblKanrenshaKigyouDtAddMinEntityInterface } from "../../entity/wkTblKanrenshaKigyouDtAddMinEntity";
import { FrameworkCapsuleDto, type FrameworkCapsuleDtoInterface } from "../frameworkCapsuleDto";

interface UpdateWkTblMinKigyouDtCapsuleDtoInterface extends FrameworkCapsuleDtoInterface {

    /** 編集対象Entity */
    wkTblKanrenshaKigyouDtAddMinEntity: WkTblKanrenshaKigyouDtAddMinEntityInterface;

}


/**
 * 企業／団体一括登録マスタ最小ワークテーブル更新CapsuleDto
 */
class UpdateWkTblMinKigyouDtCapsuleDto extends FrameworkCapsuleDto
    implements UpdateWkTblMinKigyouDtCapsuleDtoInterface {

    /** 編集対象Entity */
    wkTblKanrenshaKigyouDtAddMinEntity: WkTblKanrenshaKigyouDtAddMinEntityInterface;

    constructor() {
        super();
        this.wkTblKanrenshaKigyouDtAddMinEntity = new WkTblKanrenshaKigyouDtAddMinEntity();
    }

}

export { type UpdateWkTblMinKigyouDtCapsuleDtoInterface, UpdateWkTblMinKigyouDtCapsuleDto }