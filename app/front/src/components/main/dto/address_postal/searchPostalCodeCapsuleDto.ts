import { FrameworkPagingDto, type FrameworkPagingDtoInterface } from "seijishikin-jp-normalize_common-tool";

interface SearchPostalCodeCapsuleDtoInterface extends FrameworkPagingDtoInterface {

    /** 住所検索語 */
    addressWords: string;
}


class SearchPostalCodeCapsuleDto extends FrameworkPagingDto
    implements SearchPostalCodeCapsuleDtoInterface {

    /** 住所検索語 */
    addressWords: string;

    constructor() {
        super();
        this.addressWords = "";
    }
}

export { type SearchPostalCodeCapsuleDtoInterface, SearchPostalCodeCapsuleDto }
