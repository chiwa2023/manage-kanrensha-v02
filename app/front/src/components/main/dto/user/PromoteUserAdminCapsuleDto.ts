import { FrameworkCapsuleDto, type FrameworkCapsuleDtoInterface } from "seijishikin-jp-normalize_common-tool";
import { UserPersonEntity, type UserPersonEntityInterface } from "../../entity/userPersonEntity";

interface PromoteUserAdminCapsuleDtoInterface extends FrameworkCapsuleDtoInterface {

    /** SE権限推薦者 */
     entityUserPromote: UserPersonEntityInterface;

}

class PromoteUserAdminCapsuleDto extends FrameworkCapsuleDto
    implements PromoteUserAdminCapsuleDtoInterface {

    /** SE権限推薦者 */
     entityUserPromote: UserPersonEntityInterface;

    constructor() {
        super();
        this.entityUserPromote = new UserPersonEntity();
    }
}

export { type PromoteUserAdminCapsuleDtoInterface, PromoteUserAdminCapsuleDto }