import { FrameworkCapsuleDto, type FrameworkCapsuleDtoInterface } from "seijishikin-jp-normalize_common-tool";
import { PromoteAdminEntity, type PromoteAdminEntityInterface } from "../../entity/promoteAdminEntity";

interface AcceptUserAdminCapsuleDtoInterface extends FrameworkCapsuleDtoInterface {

    /** SE権限追加推薦Entity */
    promoteAdminEntity: PromoteAdminEntityInterface;

}

class AcceptUserAdminCapsuleDto extends FrameworkCapsuleDto
    implements AcceptUserAdminCapsuleDtoInterface {

    /** SE権限追加推薦Entity */
    promoteAdminEntity: PromoteAdminEntityInterface;

    constructor() {
        super();
        this.promoteAdminEntity = new PromoteAdminEntity();
    }

}

export { type AcceptUserAdminCapsuleDtoInterface, AcceptUserAdminCapsuleDto }
