import type { WkTblKanrenshaKigyouDtMasterEntity } from "../../entity/wkTblKanrenshaKigyouDtMasterEntity";
import { PagingDto, type PagingDtoInterface } from "../paging/pagingDto";


interface SearchWkTblStdKigyouDtPagingResultDtoInterface extends PagingDtoInterface {

        /** 企業／団体登録候補リスト */
    listWktblKigyouDt: WkTblKanrenshaKigyouDtMasterEntity[];

}


class SearchWkTblStdKigyouDtPagingResultDto extends PagingDto
    implements SearchWkTblStdKigyouDtPagingResultDtoInterface {

    /** 企業／団体登録候補リスト */
    listWktblKigyouDt: WkTblKanrenshaKigyouDtMasterEntity[];

    constructor() {
        super();
        this.listWktblKigyouDt = [];
    }

}

export { type SearchWkTblStdKigyouDtPagingResultDtoInterface, SearchWkTblStdKigyouDtPagingResultDto }