import { InputAddressDto, type InputAddressDtoInterface } from "seijishikin-jp-normalize_common-tool";

interface PostalIrregularItemDtoInterface {

    /** テーブルId */
    addressPostalIrregularId: number;

    /** 郵便番号1 */
    postal1: string;

    /** 地方公共団体コード */
    lgCode: string;

    /** 原文書(郵便番号csv)住所 */
    addressOrg: string;

    /** 住所名 */
    addressName: string;

    inputAddress: InputAddressDtoInterface;

}

class PostalIrregularItemDto implements PostalIrregularItemDtoInterface {

    /** テーブルId */
    addressPostalIrregularId: number;

    /** 郵便番号1 */
    postal1: string;

    /** 地方公共団体コード */
    lgCode: string;

    /** 原文書(郵便番号csv)住所 */
    addressOrg: string;

    /** 住所名 */
    addressName: string;

    inputAddress: InputAddressDtoInterface;

    constructor() {

        const INIT_NUMBER: number = 0;
        const INIT_STRING: string = "";

        this.addressPostalIrregularId = INIT_NUMBER;
        this.postal1 = INIT_STRING;
        this.lgCode = INIT_STRING;
        this.addressOrg = INIT_STRING;
        this.addressName = INIT_STRING;
        this.inputAddress = new InputAddressDto();
    }

}

export { type PostalIrregularItemDtoInterface, PostalIrregularItemDto }