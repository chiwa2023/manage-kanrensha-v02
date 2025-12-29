import type { WkTblKanrenshaPersonAddMinEntityInterface } from "../../entity/wkTblKanrenshaPersonAddMinEntity";
import { PagingDto, type PagingDtoInterface } from "../paging/pagingDto";

interface SearchWkTblMinPersonPagingResultDtoInterface extends PagingDtoInterface {

    /** 個人登録候補リスト */
    listWktblPerson: WkTblKanrenshaPersonAddMinEntityInterface[];

}

class SearchWkTblMinPersonPagingResultDto extends PagingDto
    implements SearchWkTblMinPersonPagingResultDtoInterface {

    /** 個人登録候補リスト */
    listWktblPerson: WkTblKanrenshaPersonAddMinEntityInterface[];

    constructor() {
        super();
        this.listWktblPerson = [];
    }
}

export { type SearchWkTblMinPersonPagingResultDtoInterface, SearchWkTblMinPersonPagingResultDto }