import { FrameworkMessageAndResultDto, type FrameworkMessageAndResultDtoInterface, type FrameworkPagingDtoInterface } from "seijishikin-jp-normalize_common-tool";
import type { ContactManagerEntityInterface } from "../../entity/contactManagerEntity";

interface SearchContactManagerResultDtoInterface extends FrameworkMessageAndResultDtoInterface, FrameworkPagingDtoInterface {

    /** 全件数 */
    allCount: number;

    /** 抽出件数 */
    limit: number;

    /** ページ番号 */
    pageNumber: number;

    /** 検索結果リスト */
    listEntity: ContactManagerEntityInterface[];

}

class SearchContactManagerResultDto extends FrameworkMessageAndResultDto
    implements SearchContactManagerResultDtoInterface, FrameworkPagingDtoInterface {

    /** 全件数 */
    allCount: number;

    /** 抽出件数 */
    limit: number;

    /** ページ番号 */
    pageNumber: number;

    /** 検索結果リスト */
    listEntity: ContactManagerEntityInterface[];

    constructor() {
        super();

        const INIT_NUMBER: number = 0;

        this.allCount = INIT_NUMBER;
        this.limit = INIT_NUMBER;
        this.pageNumber = INIT_NUMBER;
        this.listEntity = [];
    }

}

export { type SearchContactManagerResultDtoInterface, SearchContactManagerResultDto }
