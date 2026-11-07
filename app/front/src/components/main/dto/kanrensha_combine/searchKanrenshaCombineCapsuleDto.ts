import { FrameworkCapsuleDto, type FrameworkCapsuleDtoInterface, type FrameworkPagingDtoInterface } from "seijishikin-jp-normalize_common-tool";

interface SearchKanrenshaCombineCapsuleDtoInterface extends FrameworkCapsuleDtoInterface, FrameworkPagingDtoInterface {


    /** 全件数 */
    allCount: number;

    /** 抽出件数 */
    limit: number;

    /** ページ番号 */
    pageNumber: number;

    /** 登録年 */
    tableYear: number;

    /** 関連者個人コード */
    personCode: string;

    /** 関連者組織コード */
    orgCode: string;

    /** 紐づけ関連者区分 */
    kanrenshaKbn: number;
}

class SearchKanrenshaCombineCapsuleDto extends FrameworkCapsuleDto
    implements SearchKanrenshaCombineCapsuleDtoInterface {

    /** 全件数 */
    allCount: number;

    /** 抽出件数 */
    limit: number;

    /** ページ番号 */
    pageNumber: number;

    /** 登録年 */
    tableYear: number;

    /** 関連者個人コード */
    personCode: string;

    /** 関連者組織コード */
    orgCode: string;

    /** 紐づけ関連者区分 */
    kanrenshaKbn: number;

    constructor() {
        super();

        const INIT_NUMBER: number = 0;
        const INIT_STRING: string = "";

        this.allCount = INIT_NUMBER;
        this.limit = INIT_NUMBER;
        this.pageNumber = INIT_NUMBER;
        this.tableYear = INIT_NUMBER;
        this.personCode = INIT_STRING;
        this.orgCode = INIT_STRING;
        this.kanrenshaKbn = INIT_NUMBER;
    }

}

export { type SearchKanrenshaCombineCapsuleDtoInterface, SearchKanrenshaCombineCapsuleDto }
