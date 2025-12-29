import type { WkTblMasterAllByXmlEntityInterface } from "../../entity/wkTblMasterAllByXmlEntity";
import { FrameworkCapsuleDto, type FrameworkCapsuleDtoInterface } from "../frameworkCapsuleDto";

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