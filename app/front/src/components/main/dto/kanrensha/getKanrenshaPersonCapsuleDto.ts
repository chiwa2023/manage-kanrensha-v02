import { FrameworkCapsuleDto, type FrameworkCapsuleDtoInterface } from "seijishikin-jp-normalize_common-tool";
import { KanrenshaPersonMasterEntity, type KanrenshaPersonMasterEntityInterface } from "../../entity/kanrenshaPersonMasterEntity";

interface GetKanrenshaPersonCapsuleDtoInterface extends FrameworkCapsuleDtoInterface {
    /** 関連者個人マスタEntity */
    masterPersonEntity: KanrenshaPersonMasterEntityInterface;
}

class GetKanrenshaPersonCapsuleDto extends FrameworkCapsuleDto implements GetKanrenshaPersonCapsuleDtoInterface {

    /** 関連者個人マスタEntity */
    masterPersonEntity: KanrenshaPersonMasterEntityInterface;

    constructor() {
        super();

        this.masterPersonEntity = new KanrenshaPersonMasterEntity();
    }

}
export { type GetKanrenshaPersonCapsuleDtoInterface, GetKanrenshaPersonCapsuleDto }
