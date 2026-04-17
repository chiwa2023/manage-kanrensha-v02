import { FrameworkCapsuleDto, type FrameworkCapsuleDtoInterface } from "seijishikin-jp-normalize_common-tool";

interface DeleteUserCapsuleDtoInterface extends FrameworkCapsuleDtoInterface {

    /** 退会理由 */
    withdrawReason: string;

}

class DeleteUserCapsuleDto extends FrameworkCapsuleDto implements DeleteUserCapsuleDtoInterface {

    /** 退会理由 */
    withdrawReason: string;

    constructor() {
        super();
        this.withdrawReason = "";
    }
}


export { type DeleteUserCapsuleDtoInterface, DeleteUserCapsuleDto }