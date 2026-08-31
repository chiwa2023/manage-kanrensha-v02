import { WkTblKanrenshaSeijidantaiHistoryEntity, type WkTblKanrenshaSeijidantaiHistoryEntityInterface } from "../../entity/wkTblKanrenshaSeijidantaiHistoryEntity";
import { type FrameworkCapsuleDtoInterface ,FrameworkCapsuleDto } from "seijishikin-jp-normalize_common-tool";


interface UpdateWkTblHistorySeijidantaiCapsuleDtoInterface extends FrameworkCapsuleDtoInterface {

    /** 編集対象Entity */
    wkTblKanrenshaSeijidantaiHistoryEntity: WkTblKanrenshaSeijidantaiHistoryEntityInterface;
}

/**
 * 政治団体一括登録履歴ワークテーブル更新CapsuleDto
 */
class UpdateWkTblHistorySeijidantaiCapsuleDto extends FrameworkCapsuleDto
    implements UpdateWkTblHistorySeijidantaiCapsuleDtoInterface {

    /** 編集対象Entity */
    wkTblKanrenshaSeijidantaiHistoryEntity: WkTblKanrenshaSeijidantaiHistoryEntityInterface;

    constructor() {
        super();
        this.wkTblKanrenshaSeijidantaiHistoryEntity = new WkTblKanrenshaSeijidantaiHistoryEntity();
    }

}
export { type UpdateWkTblHistorySeijidantaiCapsuleDtoInterface, UpdateWkTblHistorySeijidantaiCapsuleDto }