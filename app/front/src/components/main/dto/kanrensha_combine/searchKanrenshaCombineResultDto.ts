import { FrameworkMessageAndResultDto, type FrameworkMessageAndResultDtoInterface, type FrameworkPagingDtoInterface } from "seijishikin-jp-normalize_common-tool";
import type { KanrenshaCombineOrgDtoInterface } from "./kanrenshaCombineOrgDto";

interface SearchKanrenshaCombineResultDtoInterface extends FrameworkMessageAndResultDtoInterface, FrameworkPagingDtoInterface {

    /** 全件数 */
    allCount: number;

    /** 抽出件数 */
    limit: number;

    /** ページ番号 */
    pageNumber: number;

    /** 関連者紐づけリスト */
    listDto: KanrenshaCombineOrgDtoInterface[];

}

class SearchKanrenshaCombineResultDto extends FrameworkMessageAndResultDto
    implements SearchKanrenshaCombineResultDtoInterface {

    /** 全件数 */
    allCount: number;

    /** 抽出件数 */
    limit: number;

    /** ページ番号 */
    pageNumber: number;

    /** 関連者紐づけリスト */
    listDto: KanrenshaCombineOrgDtoInterface[];

    constructor() {
        super();

        const INIT_NUMBER: number = 0;

        this.allCount = INIT_NUMBER;
        this.limit = INIT_NUMBER;
        this.pageNumber = INIT_NUMBER;
        this.listDto = [];

    }
}

export { type SearchKanrenshaCombineResultDtoInterface, SearchKanrenshaCombineResultDto }
