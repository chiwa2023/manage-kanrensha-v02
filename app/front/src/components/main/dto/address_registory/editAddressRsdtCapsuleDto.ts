import { AddressRsdtTemplateEntity, FrameworkCapsuleDto, type AddressRsdtTemplateEntityInterface, type FrameworkCapsuleDtoInterface } from "seijishikin-jp-normalize_common-tool";

interface EditAddressRsdtCapsuleDtoInterface extends FrameworkCapsuleDtoInterface {

    /** アドレス・ベース・レジストリEntity */
    editEntity: AddressRsdtTemplateEntityInterface;
}

class EditAddressRsdtCapsuleDto extends FrameworkCapsuleDto
    implements EditAddressRsdtCapsuleDtoInterface {

    /** アドレス・ベース・レジストリEntity */
    editEntity: AddressRsdtTemplateEntityInterface;

    constructor() {
        super();

        this.editEntity = new AddressRsdtTemplateEntity();
    }
}

export { type EditAddressRsdtCapsuleDtoInterface, EditAddressRsdtCapsuleDto }
