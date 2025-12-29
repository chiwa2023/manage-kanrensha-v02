import type { WkTblKanrenshaSeijidantaiAddMinEntityInterface } from "../../entity/wkTblKanrenshaSeijidantaiAddMinEntity";
import { PagingDto, type PagingDtoInterface } from "../paging/pagingDto";

interface SearchWkTblMinSeijidantaiPagingResultDtoInterface extends PagingDtoInterface {

    /** 政治団体登録候補リスト */
    listWktblSeijidantai: WkTblKanrenshaSeijidantaiAddMinEntityInterface[];

}

class SearchWkTblMinSeijidantaiPagingResultDto extends PagingDto
    implements SearchWkTblMinSeijidantaiPagingResultDtoInterface {

    /** 政治団体登録候補リスト */
    listWktblSeijidantai: WkTblKanrenshaSeijidantaiAddMinEntityInterface[];

    constructor() {
        super();
        this.listWktblSeijidantai = [];
    }

}

export { type SearchWkTblMinSeijidantaiPagingResultDtoInterface, SearchWkTblMinSeijidantaiPagingResultDto }