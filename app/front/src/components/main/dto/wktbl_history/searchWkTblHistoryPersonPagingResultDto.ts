import type { WkTblKanrenshaPersonHistoryEntityInterface } from "../../entity/wkTblKanrenshaPersonHistoryEntity";
import { PagingDto, type PagingDtoInterface } from "../paging/pagingDto";

interface SearchWkTblHistoryPersonPagingResultDtoInterface extends PagingDtoInterface {

    /** 個人登録候補リスト */
    listWktblPerson: WkTblKanrenshaPersonHistoryEntityInterface[];

}

class SearchWkTblHistoryPersonPagingResultDto extends PagingDto implements SearchWkTblHistoryPersonPagingResultDtoInterface {

    /** 個人登録候補リスト */
    listWktblPerson: WkTblKanrenshaPersonHistoryEntityInterface[];

    constructor() {
        super();
        this.listWktblPerson = [];
    }
}

export { type SearchWkTblHistoryPersonPagingResultDtoInterface, SearchWkTblHistoryPersonPagingResultDto }