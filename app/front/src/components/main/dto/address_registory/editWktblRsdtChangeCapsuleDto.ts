import { FrameworkCapsuleDto, type FrameworkCapsuleDtoInterface } from "seijishikin-jp-normalize_common-tool";
import { WkTblAddressRsdtChangeEntity, type WkTblAddressRsdtChangeEntityInterface } from "../../entity/wkTblAddressRsdtChangeEntity";

interface EditWktblRsdtChangeCapsuleDtoInterface extends FrameworkCapsuleDtoInterface {

    /** 住所差分ワークテーブル変更Entity */
    editEntity: WkTblAddressRsdtChangeEntityInterface;

}

class EditWktblRsdtChangeCapsuleDto extends FrameworkCapsuleDto
    implements EditWktblRsdtChangeCapsuleDtoInterface {

    /** 住所差分ワークテーブル変更Entity */
    editEntity: WkTblAddressRsdtChangeEntityInterface;

    constructor() {
        super();

        this.editEntity = new WkTblAddressRsdtChangeEntity();
    }
}

export { type EditWktblRsdtChangeCapsuleDtoInterface, EditWktblRsdtChangeCapsuleDto }
