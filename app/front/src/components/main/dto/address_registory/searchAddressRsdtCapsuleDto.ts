import { FrameworkPagingDto, type FrameworkPagingDtoInterface } from "seijishikin-jp-normalize_common-tool";

interface SearchAddressRsdtCapsuleDtoInterface extends FrameworkPagingDtoInterface {

    /** 検索語自然検索 */
    searchNaturalWords: string;

    /** 検索条件地方自治体コード */
    searchLgCode: string;
}

class SearchAddressRsdtCapsuleDto extends FrameworkPagingDto
    implements SearchAddressRsdtCapsuleDtoInterface {

    /** 検索語自然検索 */
    searchNaturalWords: string;

    /** 検索条件地方自治体コード */
    searchLgCode: string;

    constructor() {
        super();
        const INIT_STRING: string = "";

        this.searchNaturalWords = INIT_STRING;
        this.searchLgCode = INIT_STRING;
    }
}

export { type SearchAddressRsdtCapsuleDtoInterface, SearchAddressRsdtCapsuleDto }
