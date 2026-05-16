import { FrameworkPagingDto, type FrameworkPagingDtoInterface } from "seijishikin-jp-normalize_common-tool";

interface SearchRiyoushaOrgCapsuleDtoInterface extends FrameworkPagingDtoInterface {

    /** 検索語 */
    searchNaturalWords: string;

}

class SearchRiyoushaOrgCapsuleDto extends FrameworkPagingDto implements SearchRiyoushaOrgCapsuleDtoInterface {

    /** 検索語 */
    searchNaturalWords: string;

    constructor() {
        super();

        this.searchNaturalWords = "";
    }
}

export { type SearchRiyoushaOrgCapsuleDtoInterface, SearchRiyoushaOrgCapsuleDto }
