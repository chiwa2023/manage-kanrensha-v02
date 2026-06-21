import { AddressRsdtTemplateEntity, type AddressRsdtTemplateEntityInterface } from "./addressRsdtTemplateEntity"

interface WkTblAddressRsdtChangeEntityInterface extends AddressRsdtTemplateEntityInterface {
    /** テーブルId */
    wkTblAddressRsdtChangeId: number;
}

class WkTblAddressRsdtChangeEntity extends AddressRsdtTemplateEntity implements WkTblAddressRsdtChangeEntityInterface {

    /** テーブルId */
    wkTblAddressRsdtChangeId: number;

    constructor() {
        super();
        this.wkTblAddressRsdtChangeId = 0;
    }
}

export { type WkTblAddressRsdtChangeEntityInterface, WkTblAddressRsdtChangeEntity }
