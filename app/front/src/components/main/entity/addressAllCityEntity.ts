interface AddressAllCityEntityInterface {

    /** テーブルId */
    addressAllCityId: number;

    /** 地方自治体コード */
    lgCode: string;

    /** 最新該否 */
    isLatest: boolean;

    /** 住所名かな */
    addressNameKana: string;

    /** 県名称 */
    pref: string;

    /** 郡名称 */
    county: string;

    /** 市名称 */
    city: string;

    /** 特別区名称 */
    ward: string;

    /** 適用開始日 */
    effectDate: Date;

    /** 廃止日 */
    abolishDate: Date;

}

class AddressAllCityEntity implements AddressAllCityEntityInterface {

    /** テーブルId */
    addressAllCityId: number;

    /** 地方自治体コード */
    lgCode: string;

    /** 最新該否 */
    isLatest: boolean;

    /** 住所名かな */
    addressNameKana: string;

    /** 県名称 */
    pref: string;

    /** 郡名称 */
    county: string;

    /** 市名称 */
    city: string;

    /** 特別区名称 */
    ward: string;

    /** 適用開始日 */
    effectDate: Date;

    /** 廃止日 */
    abolishDate: Date;



    constructor() {

        const INIT_NUMBER: number = 0;
        const INIT_STRING: string = "";
        const INIT_BOOLEAN: boolean = false;
        const INIT_DATE: Date = new Date();

        this.addressAllCityId = INIT_NUMBER;
        this.lgCode = INIT_STRING;
        this.isLatest = INIT_BOOLEAN;
        this.addressNameKana = INIT_STRING;
        this.pref = INIT_STRING;
        this.county = INIT_STRING;
        this.city = INIT_STRING;
        this.ward = INIT_STRING;
        this.effectDate = INIT_DATE;
        this.abolishDate = INIT_DATE;
    }

}

export { type AddressAllCityEntityInterface, AddressAllCityEntity }
