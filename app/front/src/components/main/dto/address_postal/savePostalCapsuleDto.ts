import { FrameworkCapsuleDto, type FrameworkCapsuleDtoInterface } from "seijishikin-jp-normalize_common-tool";
import { AddressPostalEntity, type AddressPostalEntityInterface } from "../../entity/addressPostalEntity";

interface SavePostalCapsuleDtoInterface extends FrameworkCapsuleDtoInterface {

    /** 郵便番号Entity */
    addressPostalEntity: AddressPostalEntityInterface;
}


class SavePostalCapsuleDto extends FrameworkCapsuleDto implements SavePostalCapsuleDtoInterface {

    /** 郵便番号Entity */
    addressPostalEntity: AddressPostalEntityInterface;

    constructor() {
        super();
        this.addressPostalEntity = new AddressPostalEntity();
    }
}

export { type SavePostalCapsuleDtoInterface, SavePostalCapsuleDto }
