import type { WkTblKanrenshaKigyouDtHistoryEntityInterface } from "../../entity/wkTblKanrenshaKigyouDtHistoryEntity";
import { PagingDto } from "../paging/pagingDto";


interface SearchWkTblHistoryKigyouDtPagingResultDtoInterface {

    /** 企業／団体登録候補リスト */
    listWktblKigyouDt: WkTblKanrenshaKigyouDtHistoryEntityInterface[];

}


class SearchWkTblHistoryKigyouDtPagingResultDto extends PagingDto
    implements SearchWkTblHistoryKigyouDtPagingResultDtoInterface {

    /** 企業／団体登録候補リスト */
    listWktblKigyouDt: WkTblKanrenshaKigyouDtHistoryEntityInterface[];

    constructor() {
        super();
        this.listWktblKigyouDt = [];
    }

}

export { type SearchWkTblHistoryKigyouDtPagingResultDtoInterface, SearchWkTblHistoryKigyouDtPagingResultDto }