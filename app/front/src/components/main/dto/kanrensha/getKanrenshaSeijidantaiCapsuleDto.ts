import { FrameworkCapsuleDto, type FrameworkCapsuleDtoInterface } from "seijishikin-jp-normalize_common-tool";
import { KanrenshaSeijidantaiMasterEntity, type KanrenshaSeijidantaiMasterEntityInterface } from "../../entity/kanrenshaSeijidantaiMasterEntity";

interface GetKanrenshaSeijidantaiCapsuleDtoInterface extends FrameworkCapsuleDtoInterface {

    /** 関連者政治団体マスタEntity */
    masterSeijidantaiEntity: KanrenshaSeijidantaiMasterEntityInterface;
}

class GetKanrenshaSeijidantaiCapsuleDto extends FrameworkCapsuleDto implements GetKanrenshaSeijidantaiCapsuleDtoInterface {

    /** 関連者政治団体マスタEntity */
    masterSeijidantaiEntity: KanrenshaSeijidantaiMasterEntityInterface;

    constructor() {
        super();

        this.masterSeijidantaiEntity = new KanrenshaSeijidantaiMasterEntity();
    }

}

export { type GetKanrenshaSeijidantaiCapsuleDtoInterface, GetKanrenshaSeijidantaiCapsuleDto }
