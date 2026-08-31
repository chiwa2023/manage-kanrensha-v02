interface AddressPostalEntityInterface {

    /** テーブルId */
    addressPostalId: number;

    /** 郵便番号1 */
    postalcode1: string;

    /** 郵便番号2 */
    postalcode2: string;

    /** 地方自治体コード */
    lgCode: string;

    /** 原文書住所 */
    addressOrg: string;

    /** 連結住所 */
    addressName: string;

    /** 行政区検索データ */
    isGyoseikuData: boolean;

}

class AddressPostalEntity implements AddressPostalEntityInterface {

    /** テーブルId */
    addressPostalId: number;

    /** 郵便番号1 */
    postalcode1: string;

    /** 郵便番号2 */
    postalcode2: string;

    /** 地方自治体コード */
    lgCode: string;

    /** 原文書住所 */
    addressOrg: string;

    /** 連結住所 */
    addressName: string;

    /** 行政区検索データ */
    isGyoseikuData: boolean;

    constructor() {
        const INIT_STRING: string = "";

        this.addressPostalId = 0;
        this.postalcode1 = INIT_STRING;
        this.postalcode2 = INIT_STRING;
        this.lgCode = INIT_STRING;
        this.addressOrg = INIT_STRING;
        this.addressName = INIT_STRING;
        this.isGyoseikuData = false;
    }

}

export { type AddressPostalEntityInterface, AddressPostalEntity }
