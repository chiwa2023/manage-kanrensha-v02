import { FrameworkCapsuleDto, type FrameworkCapsuleDtoInterface } from "seijishikin-jp-normalize_common-tool";

interface NotifyPartnerApiLimitCapsuleDtoInterface extends FrameworkCapsuleDtoInterface {

    /** 通知確認日付 */
    checkDate: Date;

}


class NotifyPartnerApiLimitCapsuleDto extends FrameworkCapsuleDto
    implements NotifyPartnerApiLimitCapsuleDtoInterface {

    /** 通知確認日付 */
    checkDate: Date;

    constructor() {
        super();

        this.checkDate = new Date();
    }
}

export { type NotifyPartnerApiLimitCapsuleDtoInterface, NotifyPartnerApiLimitCapsuleDto }
