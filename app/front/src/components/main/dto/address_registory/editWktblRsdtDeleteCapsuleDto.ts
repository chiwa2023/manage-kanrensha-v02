import { FrameworkCapsuleDto, type FrameworkCapsuleDtoInterface } from "seijishikin-jp-normalize_common-tool";
import { WkTblAddressRsdtDeleteEntity, type WkTblAddressRsdtDeleteEntityInterface } from "../../entity/wkTblAddressRsdtDeleteEntity";

interface EditWktblRsdtDeleteCapsuleDtoInterface extends FrameworkCapsuleDtoInterface {

    /** 住所差分ワークテーブル削除Entity */
    editEntity: WkTblAddressRsdtDeleteEntityInterface;
}

class EditWktblRsdtDeleteCapsuleDto extends FrameworkCapsuleDto implements EditWktblRsdtDeleteCapsuleDtoInterface {

    /** 住所差分ワークテーブル削除Entity */
    editEntity: WkTblAddressRsdtDeleteEntityInterface;

    constructor() {
        super();

        this.editEntity = new WkTblAddressRsdtDeleteEntity();
    }
}

export { type EditWktblRsdtDeleteCapsuleDtoInterface, EditWktblRsdtDeleteCapsuleDto }
