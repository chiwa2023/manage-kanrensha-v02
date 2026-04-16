import { FrameworkPagingDto, type FrameworkPagingDtoInterface } from "seijishikin-jp-normalize_common-tool";

interface SearchLocalGovernmentCapsuleDtoInterface extends FrameworkPagingDtoInterface {

    /** 住所検索語 */
    addressWords: string;
}


class SearchLocalGovernmentCapsuleDto extends FrameworkPagingDto
    implements SearchLocalGovernmentCapsuleDtoInterface {

    /** 住所検索語 */
    addressWords: string;

    constructor() {
        super();
        this.addressWords = "";
    }
}

export { type SearchLocalGovernmentCapsuleDtoInterface, SearchLocalGovernmentCapsuleDto }
