import type { WkTblKanrenshaSeijidantaiHistoryEntityInterface } from "../../entity/wkTblKanrenshaSeijidantaiHistoryEntity";
import { PagingDto, type PagingDtoInterface } from "../paging/pagingDto";

interface SearchWkTblHistorySeijidantaiPagingResultDtoInterface extends PagingDtoInterface {

    /** 政治団体登録候補リスト */
    listWktblSeijidantai: WkTblKanrenshaSeijidantaiHistoryEntityInterface[];
}

class SearchWkTblHistorySeijidantaiPagingResultDto extends PagingDto implements SearchWkTblHistorySeijidantaiPagingResultDtoInterface {

    /** 政治団体登録候補リスト */
    listWktblSeijidantai: WkTblKanrenshaSeijidantaiHistoryEntityInterface[];

    constructor() {
        super();
        this.listWktblSeijidantai = [];
    }

}

export { type SearchWkTblHistorySeijidantaiPagingResultDtoInterface, SearchWkTblHistorySeijidantaiPagingResultDto }