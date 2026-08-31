import { FrameworkCapsuleDto, type FrameworkCapsuleDtoInterface, type FrameworkPagingDtoInterface } from "seijishikin-jp-normalize_common-tool";

interface SearchWkTblPostalCodeCapsuleDtoInterface
    extends FrameworkPagingDtoInterface, FrameworkCapsuleDtoInterface {

    /** 履歴検索該否 */
    isSearchHistory: boolean;

    /** 自動修復検索該否 */
    isSearchRepair: boolean;

    /** 原文書名 */
    orgName: string;

    /** 編集内容説明 */
    worksText: string;
}

class SearchWkTblPostalCodeCapsuleDto extends FrameworkCapsuleDto
    implements SearchWkTblPostalCodeCapsuleDtoInterface {

    allCount: number;
    limit: number;
    pageNumber: number;

    /** 履歴検索該否 */
    isSearchHistory: boolean;

    /** 自動修復検索該否 */
    isSearchRepair: boolean;

    /** 原文書名 */
    orgName: string;

    /** 編集内容説明 */
    worksText: string;

    constructor() {
        super();
        const INIT_NUMBER: number = 0;
        const INIT_STRING: string = "";
        const INIT_BOOLEAN: boolean = false;

        this.isSearchHistory = INIT_BOOLEAN;
        this.isSearchRepair = INIT_BOOLEAN;
        this.allCount = INIT_NUMBER;
        this.limit = INIT_NUMBER;
        this.pageNumber = INIT_NUMBER;
        this.orgName = INIT_STRING;
        this.worksText = INIT_STRING;
    }
}

export { type SearchWkTblPostalCodeCapsuleDtoInterface, SearchWkTblPostalCodeCapsuleDto }
