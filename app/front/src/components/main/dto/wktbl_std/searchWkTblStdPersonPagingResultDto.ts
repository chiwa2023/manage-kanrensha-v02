import type { WkTblKanrenshaPersonMasterEntityInterface } from "../../entity/wkTblKanrenshaPersonMasterEntity";
import { PagingDto, type PagingDtoInterface } from "../paging/pagingDto";

interface SearchWkTblStdPersonPagingResultDtoInterface extends PagingDtoInterface {

    /** 個人登録候補リスト */
    listWktblPerson: WkTblKanrenshaPersonMasterEntityInterface[];

}

class SearchWkTblStdPersonPagingResultDto extends PagingDto
    implements SearchWkTblStdPersonPagingResultDtoInterface {

    /** 個人登録候補リスト */
    listWktblPerson: WkTblKanrenshaPersonMasterEntityInterface[];

    constructor() {
        super();
        this.listWktblPerson = [];
    }
}

export { type SearchWkTblStdPersonPagingResultDtoInterface, SearchWkTblStdPersonPagingResultDto }
