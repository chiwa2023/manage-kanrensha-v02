import type { WkTblKanrenshaKigyouDtAddMinEntityInterface } from "../../entity/wkTblKanrenshaKigyouDtAddMinEntity";
import { PagingDto, type PagingDtoInterface } from "../paging/pagingDto";

interface SearchWkTblMinKigyouDtPagingResultDtoInterface extends PagingDtoInterface {

    /** 企業／団体登録候補リスト */
    listWktblKigyouDt: WkTblKanrenshaKigyouDtAddMinEntityInterface[];

}


class SearchWkTblMinKigyouDtPagingResultDto extends PagingDto
    implements SearchWkTblMinKigyouDtPagingResultDtoInterface {

    /** 企業／団体登録候補リスト */
    listWktblKigyouDt: WkTblKanrenshaKigyouDtAddMinEntityInterface[];

    constructor() {
        super();
        this.listWktblKigyouDt = [];
    }

}

export { type SearchWkTblMinKigyouDtPagingResultDtoInterface, SearchWkTblMinKigyouDtPagingResultDto }