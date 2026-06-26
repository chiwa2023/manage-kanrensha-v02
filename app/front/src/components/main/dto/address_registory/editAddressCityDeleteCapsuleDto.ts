import { FrameworkCapsuleDto, type FrameworkCapsuleDtoInterface } from "seijishikin-jp-normalize_common-tool";
import { AddressCityDeleteEntity, type AddressCityDeleteEntityInterface } from "../../entity/addressCityDeleteEntity";

interface EditAddressCityDeleteCapsuleDtoInterface extends FrameworkCapsuleDtoInterface {

    /** 地方自治体コード削除編集Entity */
    editEntity: AddressCityDeleteEntityInterface;

    /** 編集地方自治体名(置換対象) */
    srcLgName: string;

    /** 移行地方自治体コード */
    moveLgCode: string;

    /** 移行地方自治体名(置換対象) */
    moveLgName: string;

}


class EditAddressCityDeleteCapsuleDto extends FrameworkCapsuleDto implements EditAddressCityDeleteCapsuleDtoInterface {

    /** 地方自治体コード削除編集Entity */
    editEntity: AddressCityDeleteEntityInterface;

    /** 編集地方自治体名(置換対象) */
    srcLgName: string;

    /** 移行地方自治体コード */
    moveLgCode: string;

    /** 移行地方自治体名(置換対象) */
    moveLgName: string;

    constructor() {
        super();
        const INIT_STRING: string = "";

        this.editEntity = new AddressCityDeleteEntity();
        this.moveLgCode = INIT_STRING;
        this.srcLgName = INIT_STRING;
        this.moveLgName = INIT_STRING;
    }
}

export { type EditAddressCityDeleteCapsuleDtoInterface, EditAddressCityDeleteCapsuleDto }
