import { WkTblKanrenshaSeijidantaiMasterEntity, type WkTblKanrenshaSeijidantaiMasterEntityInterface } from "../../entity/wkTblKanrenshaSeijidantaiMasterEntity";
import { type FrameworkCapsuleDtoInterface ,FrameworkCapsuleDto } from "seijishikin-jp-normalize_common-tool";

interface UpdateWkTblStdSeijidantaiCapsuleDtoInterface extends FrameworkCapsuleDtoInterface {

    /** 編集対象Entity */
    wkTblKanrenshaSeijidantaiMasterEntity: WkTblKanrenshaSeijidantaiMasterEntityInterface;

}
/**
 * 政治団体一括登録マスタ標準ワークテーブル更新CapsuleDto
 */
class UpdateWkTblStdSeijidantaiCapsuleDto extends FrameworkCapsuleDto
    implements UpdateWkTblStdSeijidantaiCapsuleDtoInterface {


    /** 編集対象Entity */
    wkTblKanrenshaSeijidantaiMasterEntity: WkTblKanrenshaSeijidantaiMasterEntityInterface;

    constructor() {
        super();
        this.wkTblKanrenshaSeijidantaiMasterEntity = new WkTblKanrenshaSeijidantaiMasterEntity();
    }
}

export { type UpdateWkTblStdSeijidantaiCapsuleDtoInterface, UpdateWkTblStdSeijidantaiCapsuleDto }