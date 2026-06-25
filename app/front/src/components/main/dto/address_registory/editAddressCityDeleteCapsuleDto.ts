import { FrameworkCapsuleDto, type FrameworkCapsuleDtoInterface } from "seijishikin-jp-normalize_common-tool";
import { AddressCityDeleteEntity, type AddressCityDeleteEntityInterface } from "../../entity/addressCityDeleteEntity";

interface EditAddressCityDeleteCapsuleDtoInterface extends FrameworkCapsuleDtoInterface {

    /** 地方自治体コード削除編集Entity */
    editEntity: AddressCityDeleteEntityInterface;

    /** 移行地方自治体コード */
    moveLgCode: string;
}


class EditAddressCityDeleteCapsuleDto extends FrameworkCapsuleDto implements EditAddressCityDeleteCapsuleDtoInterface {

    /** 地方自治体コード削除編集Entity */
    editEntity: AddressCityDeleteEntityInterface;

    /** 移行地方自治体コード */
    moveLgCode: string;

    constructor() {
        super();

        this.editEntity = new AddressCityDeleteEntity();
        this.moveLgCode = "";
    }
}

export { type EditAddressCityDeleteCapsuleDtoInterface, EditAddressCityDeleteCapsuleDto }
