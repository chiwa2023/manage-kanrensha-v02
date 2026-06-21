import { FrameworkCapsuleDto, type FrameworkCapsuleDtoInterface } from "seijishikin-jp-normalize_common-tool";
import { WkTblPostalEditEntity, type WkTblPostalEditEntityInterface } from "../../entity/wkTblPostalEditEntity";

interface SaveWktblPostalCapsuleDtoInterface extends FrameworkCapsuleDtoInterface {

    /** 郵便番号差分ワークテーブルEntity */
    editEntity: WkTblPostalEditEntityInterface;

    /** 削除該否 */
    isDelete: boolean;
}

class SaveWktblPostalCapsuleDto extends FrameworkCapsuleDto implements SaveWktblPostalCapsuleDtoInterface {


    /** 郵便番号差分ワークテーブルEntity */
    editEntity: WkTblPostalEditEntityInterface;

    /** 削除該否 */
    isDelete: boolean;

    constructor() {
        super();

        this.editEntity = new WkTblPostalEditEntity();
        this.isDelete = false;
    }
}

export { type SaveWktblPostalCapsuleDtoInterface, SaveWktblPostalCapsuleDto }
