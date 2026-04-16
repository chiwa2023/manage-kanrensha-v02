import type { WkTblMasterAllByXmlEntityInterface } from "../../entity/wkTblMasterAllByXmlEntity";
import { type FrameworkCapsuleDtoInterface ,FrameworkCapsuleDto } from "seijishikin-jp-normalize_common-tool";

interface UpdateWkTblAddByXmlTableListCapsuleDtoInterface extends FrameworkCapsuleDtoInterface {

    /** 編集対象リスト */
    listWkTblByXml: WkTblMasterAllByXmlEntityInterface[];

}

class UpdateWkTblAddByXmlTableListCapsuleDto extends FrameworkCapsuleDto
    implements UpdateWkTblAddByXmlTableListCapsuleDtoInterface {

    /** 編集対象リスト */
    listWkTblByXml: WkTblMasterAllByXmlEntityInterface[];

    constructor() {
        super();
        this.listWkTblByXml = [];
    }
}

export { type UpdateWkTblAddByXmlTableListCapsuleDtoInterface, UpdateWkTblAddByXmlTableListCapsuleDto }