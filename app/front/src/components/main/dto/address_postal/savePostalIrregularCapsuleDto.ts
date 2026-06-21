import { FrameworkCapsuleDto, type FrameworkCapsuleDtoInterface } from "seijishikin-jp-normalize_common-tool";
import { AddressPostalIrregularEntity, type AddressPostalIrregularEntityInterface } from "../../entity/addressPostalIrregularEntity";

interface SavePostalIrregularCapsuleDtoInterface extends FrameworkCapsuleDtoInterface {
    /** 郵便番号不規則Entity */
    addressPostalIrregularEntity: AddressPostalIrregularEntityInterface;
}


class SavePostalIrregularCapsuleDto extends FrameworkCapsuleDto implements SavePostalIrregularCapsuleDtoInterface {

    /** 郵便番号不規則Entity */
    addressPostalIrregularEntity: AddressPostalIrregularEntityInterface;

    constructor() {
        super();
        this.addressPostalIrregularEntity = new AddressPostalIrregularEntity();
    }
}

export { type SavePostalIrregularCapsuleDtoInterface, SavePostalIrregularCapsuleDto }
