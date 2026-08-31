import { AddressRsdtTemplateEntity, type AddressRsdtTemplateEntityInterface } from "./addressRsdtTemplateEntity";

interface WkTblAddressRsdtDeleteEntityInterface extends AddressRsdtTemplateEntityInterface {
    /** テーブルId */
    wkTblAddressRsdtDeleteId: number;

}

class WkTblAddressRsdtDeleteEntity extends AddressRsdtTemplateEntity implements WkTblAddressRsdtDeleteEntityInterface {

    /** テーブルId */
    wkTblAddressRsdtDeleteId: number;

    constructor() {
        super();
        this.wkTblAddressRsdtDeleteId = 0;
    }

}

export { type WkTblAddressRsdtDeleteEntityInterface, WkTblAddressRsdtDeleteEntity }
