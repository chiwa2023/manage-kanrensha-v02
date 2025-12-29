import { WkTblMasterAllByXmlEntity, type WkTblMasterAllByXmlEntityInterface } from "../../entity/wkTblMasterAllByXmlEntity";
import { FrameworkCapsuleDto, type FrameworkCapsuleDtoInterface } from "../frameworkCapsuleDto";

interface UpdateWkTblAddByXmlCapsuleDtoInterface extends FrameworkCapsuleDtoInterface {

    /** 編集対象Entity */
    wkTblMasterAllByXmlEntity: WkTblMasterAllByXmlEntityInterface;

}

/**
 * 企業／団体一括登録履歴ワークテーブル更新CapsuleDto
 */
class UpdateWkTblAddByXmlCapsuleDto extends FrameworkCapsuleDto
    implements UpdateWkTblAddByXmlCapsuleDtoInterface {

    /** 編集対象Entity */
    wkTblMasterAllByXmlEntity: WkTblMasterAllByXmlEntityInterface;

    constructor() {
        super();
        this.wkTblMasterAllByXmlEntity = new WkTblMasterAllByXmlEntity()
    }
}


export { type UpdateWkTblAddByXmlCapsuleDtoInterface, UpdateWkTblAddByXmlCapsuleDto }
