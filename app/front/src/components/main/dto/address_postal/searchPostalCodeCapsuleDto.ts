import { FrameworkPagingDto, type FrameworkPagingDtoInterface } from "seijishikin-jp-normalize_common-tool";

interface SearchPostalCodeCapsuleDtoInterface extends FrameworkPagingDtoInterface {

    /** 検索条件郵便番号1 */
    searchPostalcode1: string;

    /** 検索条件郵便番号2 */
    searchPostalcode2: string;

    /** 検索条件住所 */
    searchAddressName: string;
}


class SearchPostalCodeCapsuleDto extends FrameworkPagingDto
    implements SearchPostalCodeCapsuleDtoInterface {

    /** 検索条件郵便番号1 */
    searchPostalcode1: string;

    /** 検索条件郵便番号2 */
    searchPostalcode2: string;

    /** 検索条件住所 */
    searchAddressName: string;

    constructor() {
        super();
        const INIT_STRING: string = "";

        /** 検索条件郵便番号1 */
        this.searchPostalcode1 = INIT_STRING;

        /** 検索条件郵便番号2 */
        this.searchPostalcode2 = INIT_STRING;

        /** 検索条件住所 */
        this.searchAddressName = INIT_STRING;
    }
}

export { type SearchPostalCodeCapsuleDtoInterface, SearchPostalCodeCapsuleDto }
