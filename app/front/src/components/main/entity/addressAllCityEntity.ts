interface AddressAllCityEntityInterface {

    /** 地方自治体テーブルId */
    addressAllCityId: number;

    /** 地方自治体コード */
    lgCode: string;

    /** 住所 */
    addressName: string;

    /** 住所かな */
    addressNameKana: string;

    /** 影響日 */
    effectDate: Date;

}

class AddressAllCityEntity implements AddressAllCityEntityInterface {

    /** 地方自治体テーブルId */
    addressAllCityId: number;

    /** 地方自治体コード */
    lgCode: string;

    /** 住所 */
    addressName: string;

    /** 住所かな */
    addressNameKana: string;

    /** 影響日 */
    effectDate: Date;

    constructor() {

        const INIT_STRING: string = "";

        this.addressAllCityId = 0;
        this.lgCode = INIT_STRING;
        this.addressName = INIT_STRING;
        this.addressNameKana = INIT_STRING;
        this.effectDate = new Date(1948, 7, 28);
    }

}

export { type AddressAllCityEntityInterface, AddressAllCityEntity }
