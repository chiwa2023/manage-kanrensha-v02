interface AddressPostalIrregularEntityInterface {

    /** テーブルId */
    addressPostalIrregularId: number;

    /** 郵便番号1 */
    postal1: string;

    /** 郵便番号2 */
    postal2: string;

    /** 地方公共団体コード */
    lgCode: string;

    /** 原文書(郵便番号csv)住所 */
    addressOrg: string;

    /** 住所名 */
    addressName: string;

    /** 住所郵便番号まで */
    addressPostal: string;

    /** 郵便番号番地まで */
    addressBlock: string;

    /** 正規テーブル複写処理完了 */
    isAddPostal: boolean;

    /** 住居テーブル修正完了 */
    isRepairRsdt: boolean;

}

class AddressPostalIrregularEntity implements AddressPostalIrregularEntityInterface {

    /** テーブルId */
    addressPostalIrregularId: number;

    /** 郵便番号1 */
    postal1: string;

    /** 郵便番号2 */
    postal2: string;

    /** 地方公共団体コード */
    lgCode: string;

    /** 原文書(郵便番号csv)住所 */
    addressOrg: string;

    /** 住所名 */
    addressName: string;

    /** 住所郵便番号まで */
    addressPostal: string;

    /** 郵便番号番地まで */
    addressBlock: string;

    /** 正規テーブル複写処理完了 */
    isAddPostal: boolean;

    /** 住居テーブル修正完了 */
    isRepairRsdt: boolean;

    constructor() {
        const INIT_NUMBER: number = 0;
        const INIT_STRING: string = "";
        const INIT_BOOLEAN: boolean = false;

        this.addressPostalIrregularId = INIT_NUMBER;
        this.postal1 = INIT_STRING;
        this.postal2 = INIT_STRING;
        this.lgCode = INIT_STRING;
        this.addressOrg = INIT_STRING;
        this.addressName = INIT_STRING;
        this.addressPostal = INIT_STRING;
        this.addressBlock = INIT_STRING;
        this.isAddPostal = INIT_BOOLEAN;
        this.isRepairRsdt = INIT_BOOLEAN;

    }

}

export { type AddressPostalIrregularEntityInterface, AddressPostalIrregularEntity }