import { FrameworkCapsuleDto, type FrameworkCapsuleDtoInterface } from "seijishikin-jp-normalize_common-tool";
import { RiyoushaCombineOrgEntity, type RiyoushaCombineOrgEntityInterface } from "../../entity/riyoushaCombineOrgEntity";

interface RiyoushaCombinePersonCapsuleDtoInterface extends FrameworkCapsuleDtoInterface {

    /** 利用者組織マスタEntity */
    combineEntity: RiyoushaCombineOrgEntityInterface;

    /** 個人登録メールアドレス */
    email: string;
}

class RiyoushaCombinePersonCapsuleDto extends FrameworkCapsuleDto
    implements RiyoushaCombinePersonCapsuleDtoInterface {

    /** 利用者組織マスタEntity */
    combineEntity: RiyoushaCombineOrgEntityInterface;

    /** 個人登録メールアドレス */
    email: string;

    constructor() {
        super();

        this.combineEntity = new RiyoushaCombineOrgEntity();
        this.email = "";
    }

}

export { type RiyoushaCombinePersonCapsuleDtoInterface, RiyoushaCombinePersonCapsuleDto }
