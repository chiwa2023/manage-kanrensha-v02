interface AddressCityDeleteEntityInterface {

    /** テーブルId */
    addressCityDeleteId: number;

    /** 最新該否 */
    isLatest: boolean;

    /** 地方自治体コード */
    lgCode: string;

    /** 組織名 */
    orgName: string;

}

class AddressCityDeleteEntity implements AddressCityDeleteEntityInterface {

    /** テーブルId */
    addressCityDeleteId: number;

    /** 最新該否 */
    isLatest: boolean;

    /** 地方自治体コード */
    lgCode: string;

    /** 組織名 */
    orgName: string;

    constructor() {
        const INIT_NUMBER: number = 0;
        const INIT_STRING: string = "";
        const INIT_BOOLEAN: boolean = false;

        this.addressCityDeleteId = INIT_NUMBER;
        this.isLatest = INIT_BOOLEAN;
        this.lgCode = INIT_STRING;
        this.orgName = INIT_STRING;
    }

}

export { type AddressCityDeleteEntityInterface, AddressCityDeleteEntity }
