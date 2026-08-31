import { WkTblKanrenshaSeijidantaiAddMinEntity, type WkTblKanrenshaSeijidantaiAddMinEntityInterface } from "../../entity/wkTblKanrenshaSeijidantaiAddMinEntity";
import { type FrameworkCapsuleDtoInterface ,FrameworkCapsuleDto } from "seijishikin-jp-normalize_common-tool";

interface UpdateWkTblMinSeijidantaiCapsuleDtoInterface extends FrameworkCapsuleDtoInterface {

    /** 編集対象Entity */
    wkTblKanrenshaSeijidantaiAddMinEntity: WkTblKanrenshaSeijidantaiAddMinEntityInterface;

}
/**
 * 政治団体一括登録マスタ最小ワークテーブル更新CapsuleDto
 */
class UpdateWkTblMinSeijidantaiCapsuleDto extends FrameworkCapsuleDto
    implements UpdateWkTblMinSeijidantaiCapsuleDtoInterface {

    /** 編集対象Entity */
    wkTblKanrenshaSeijidantaiAddMinEntity: WkTblKanrenshaSeijidantaiAddMinEntityInterface;

    constructor() {
        super();
        this.wkTblKanrenshaSeijidantaiAddMinEntity = new WkTblKanrenshaSeijidantaiAddMinEntity();
    }
}

export { type UpdateWkTblMinSeijidantaiCapsuleDtoInterface, UpdateWkTblMinSeijidantaiCapsuleDto }