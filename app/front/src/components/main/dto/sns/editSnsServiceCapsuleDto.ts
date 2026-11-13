import { FrameworkCapsuleDto, type FrameworkCapsuleDtoInterface } from "seijishikin-jp-normalize_common-tool";
import { SnsServiceEntity, type SnsServiceEntityInterface } from "../../entity/snsServiceEntity";

interface EditSnsServiceCapsuleDtoInterface extends FrameworkCapsuleDtoInterface {

    /** 編集対象SNSサービス */
    snsServiceEntity: SnsServiceEntityInterface;
}

class EditSnsServiceCapsuleDto extends FrameworkCapsuleDto
    implements EditSnsServiceCapsuleDtoInterface {

    /** 編集対象SNSサービス */
    snsServiceEntity: SnsServiceEntityInterface;

    constructor() {
        super();
        this.snsServiceEntity = new SnsServiceEntity();
    }
}

export { type EditSnsServiceCapsuleDtoInterface, EditSnsServiceCapsuleDto }
