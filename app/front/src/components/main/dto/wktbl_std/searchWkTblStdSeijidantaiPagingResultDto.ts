import type { WkTblKanrenshaSeijidantaiMasterEntityInterface } from "../../entity/wkTblKanrenshaSeijidantaiMasterEntity";
import { PagingDto, type PagingDtoInterface } from "../paging/pagingDto";

interface SearchWkTblStdSeijidantaiPagingResultDtoInterface extends PagingDtoInterface {

    /** 政治団体登録候補リスト */
    listWktblSeijidantai: WkTblKanrenshaSeijidantaiMasterEntityInterface[];

}

class SearchWkTblStdSeijidantaiPagingResultDto extends PagingDto
    implements SearchWkTblStdSeijidantaiPagingResultDtoInterface {

    /** 政治団体登録候補リスト */
    listWktblSeijidantai: WkTblKanrenshaSeijidantaiMasterEntityInterface[];

    constructor() {
        super();
        this.listWktblSeijidantai = [];
    }

}
export { type SearchWkTblStdSeijidantaiPagingResultDtoInterface, SearchWkTblStdSeijidantaiPagingResultDto }
