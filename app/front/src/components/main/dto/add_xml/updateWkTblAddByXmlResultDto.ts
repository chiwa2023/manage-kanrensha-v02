import { FrameworkMessageAndResultDto, type FrameworkMessageAndResultDtoInterface } from "seijishikin-jp-normalize_common-tool";
import { WkTblMasterAllByXmlEntity, type WkTblMasterAllByXmlEntityInterface } from "../../entity/wkTblMasterAllByXmlEntity";

interface UpdateWkTblAddByXmlResultDtoInterface extends FrameworkMessageAndResultDtoInterface {

    /** 編集対象Entity */
    wkTblMasterAllByXmlEntity: WkTblMasterAllByXmlEntityInterface;

}

/**
 * 企業／団体一括登録履歴ワークテーブル更新CapsuleDto
 */
class UpdateWkTblAddByXmlResultDto extends FrameworkMessageAndResultDto
    implements UpdateWkTblAddByXmlResultDtoInterface {

    /** 編集対象Entity */
    wkTblMasterAllByXmlEntity: WkTblMasterAllByXmlEntityInterface;

    constructor() {
        super();
        this.wkTblMasterAllByXmlEntity = new WkTblMasterAllByXmlEntity()
    }
}

export { type UpdateWkTblAddByXmlResultDtoInterface, UpdateWkTblAddByXmlResultDto }
