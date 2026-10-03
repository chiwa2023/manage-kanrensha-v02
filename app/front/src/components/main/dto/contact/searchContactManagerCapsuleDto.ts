import { FrameworkCapsuleDto, type FrameworkCapsuleDtoInterface, type FrameworkPagingDtoInterface } from "seijishikin-jp-normalize_common-tool";

interface SearchContactManagerCapsuleDtoInterface
    extends FrameworkCapsuleDtoInterface, FrameworkPagingDtoInterface {

    /** 全件数 */
    allCount: number;

    /** 抽出件数 */
    limit: number;

    /** ページ番号 */
    pageNumber: number;

    /** 運営者問い合わせコード */
    contactManagerCode: number;

    /** 検索条件開始日付 */
    startDate: Date;

    /** 検索条件終了日付 */
    endDate: Date;

    /** 検索条件クローズ検索該当 */
    isSearchClose: boolean;
}


class SearchContactManagerCapsuleDto extends FrameworkCapsuleDto
    implements SearchContactManagerCapsuleDtoInterface, FrameworkPagingDtoInterface {


    /** 全件数 */
    allCount: number;

    /** 抽出件数 */
    limit: number;

    /** ページ番号 */
    pageNumber: number;

    /** 運営者問い合わせコード */
    contactManagerCode: number;

    /** 検索条件開始日付 */
    startDate: Date;

    /** 検索条件終了日付 */
    endDate: Date;

    /** 検索条件クローズ検索該当 */
    isSearchClose: boolean;

    constructor() {
        super();

        const INIT_NUMBER: number = 0;
        const INIT_BOOLEAN: boolean = false;
        const INIT_DATE: Date = new Date();

        this.allCount = INIT_NUMBER;
        this.limit = INIT_NUMBER;
        this.pageNumber = INIT_NUMBER;
        this.contactManagerCode = INIT_NUMBER;
        const pre = new Date();
        pre.setMonth(INIT_DATE.getMonth()-1);
        this.startDate = pre;
        this.endDate = INIT_DATE;
        this.isSearchClose = INIT_BOOLEAN;
    }

}

export { type SearchContactManagerCapsuleDtoInterface, SearchContactManagerCapsuleDto }
