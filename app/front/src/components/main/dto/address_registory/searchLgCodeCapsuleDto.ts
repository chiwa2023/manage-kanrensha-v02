import { FrameworkPagingDto, type FrameworkPagingDtoInterface } from "seijishikin-jp-normalize_common-tool";

interface SearchLgCodeCapsuleDtoInterface extends FrameworkPagingDtoInterface {

    /** 県地方自治体コード */
    prefCode: string;

    /** 検索語 */
    searchWords: string;

}

class SearchLgCodeCapsuleDto extends FrameworkPagingDto implements SearchLgCodeCapsuleDtoInterface {

    /** 県地方自治体コード */
    prefCode: string;

    /** 検索語 */
    searchWords: string;

    constructor() {
        super();

        const INIT_STRING: string = "";

        this.prefCode = INIT_STRING;
        this.searchWords = INIT_STRING;
    }
}

export { type SearchLgCodeCapsuleDtoInterface, SearchLgCodeCapsuleDto }
