import { FrameworkCapsuleDto, type FrameworkCapsuleDtoInterface } from "seijishikin-jp-normalize_common-tool";
import { WkTblKanrenshaCombineOrgEntity, type WkTblKanrenshaCombineOrgEntityInterface } from "../../entity/wkTblKanrenshaCombineOrgEntity";

interface UpdateKanrenshaCombineCapsuleDtoInterface extends FrameworkCapsuleDtoInterface {

    /** 関連者紐づけワークテーブルEntity */
    combineOrgEntity: WkTblKanrenshaCombineOrgEntityInterface;

}

class UpdateKanrenshaCombineCapsuleDto extends FrameworkCapsuleDto
    implements UpdateKanrenshaCombineCapsuleDtoInterface {

    /** 関連者紐づけワークテーブルEntity */
    combineOrgEntity: WkTblKanrenshaCombineOrgEntityInterface;

    constructor() {
        super();

        this.combineOrgEntity = new WkTblKanrenshaCombineOrgEntity();
    }
}

export { type UpdateKanrenshaCombineCapsuleDtoInterface, UpdateKanrenshaCombineCapsuleDto }
