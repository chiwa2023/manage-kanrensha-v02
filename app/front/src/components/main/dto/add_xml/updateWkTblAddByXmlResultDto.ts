import { WkTblMasterAllByXmlEntity, type WkTblMasterAllByXmlEntityInterface } from "../../entity/wkTblMasterAllByXmlEntity";
import { FrameworkResultDto, type FrameworkResultDtoInterface } from "../frameworkResultDto";

interface UpdateWkTblAddByXmlResultDtoInterface extends FrameworkResultDtoInterface {

    /** 編集対象Entity */
    wkTblMasterAllByXmlEntity: WkTblMasterAllByXmlEntityInterface;

}

/**
 * 企業／団体一括登録履歴ワークテーブル更新CapsuleDto
 */
class UpdateWkTblAddByXmlResultDto extends FrameworkResultDto
    implements UpdateWkTblAddByXmlResultDtoInterface {

    /** 編集対象Entity */
    wkTblMasterAllByXmlEntity: WkTblMasterAllByXmlEntityInterface;

    constructor() {
        super();
        this.wkTblMasterAllByXmlEntity = new WkTblMasterAllByXmlEntity()
    }
}

export { type UpdateWkTblAddByXmlResultDtoInterface, UpdateWkTblAddByXmlResultDto }
