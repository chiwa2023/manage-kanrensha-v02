import { FrameworkCapsuleDto, type FrameworkCapsuleDtoInterface } from "seijishikin-jp-normalize_common-tool";
import { KanrenshaKigyouDtMasterEntity, type KanrenshaKigyouDtMasterEntityInterface } from "../../entity/kanrenshaKigyouDtMasterEntity";

interface GetKanrenshaKigyouDtCapsuleDtoInterface extends FrameworkCapsuleDtoInterface {
    /** 関連者企業団体マスタEntity */
    masterKigyouDtEntity: KanrenshaKigyouDtMasterEntityInterface;
}

class GetKanrenshaKigyouDtCapsuleDto extends FrameworkCapsuleDto implements GetKanrenshaKigyouDtCapsuleDtoInterface {

    /** 関連者企業団体マスタEntity */
    masterKigyouDtEntity: KanrenshaKigyouDtMasterEntityInterface;

    constructor() {
        super();

        this.masterKigyouDtEntity = new KanrenshaKigyouDtMasterEntity();
    }
}

export { type GetKanrenshaKigyouDtCapsuleDtoInterface, GetKanrenshaKigyouDtCapsuleDto }