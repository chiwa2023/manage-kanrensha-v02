import { FrameworkCapsuleDto, type FrameworkCapsuleDtoInterface } from "seijishikin-jp-normalize_common-tool";
import type { KanrenshaAddressBaseEntityInterface } from "../../entity/kanrenshaAddressBaseEntity";
import type { KanrenshaPersonPropertyEntityInterface } from "../../entity/kanrenshaPersonPropertyEntity";

interface SaveWorksApprovalCapsuleDtoInterface extends FrameworkCapsuleDtoInterface {

    /** 住所承認作業リスト */
    listAddress: KanrenshaAddressBaseEntityInterface[];

    /** 承認作業用職業リスト */
    listShokugyou: KanrenshaPersonPropertyEntityInterface[];

}

class SaveWorksApprovalCapsuleDto extends FrameworkCapsuleDto implements SaveWorksApprovalCapsuleDtoInterface {

    /** 住所承認作業リスト */
    listAddress: KanrenshaAddressBaseEntityInterface[];

    /** 承認作業用職業リスト */
    listShokugyou: KanrenshaPersonPropertyEntityInterface[];

    constructor() {
        super();

        this.listAddress = [];
        this.listShokugyou = [];

    }

}

export { type SaveWorksApprovalCapsuleDtoInterface, SaveWorksApprovalCapsuleDto }