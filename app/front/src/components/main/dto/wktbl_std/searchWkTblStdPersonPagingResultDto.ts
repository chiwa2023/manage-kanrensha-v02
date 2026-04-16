import type { WkTblKanrenshaPersonMasterEntityInterface } from "../../entity/wkTblKanrenshaPersonMasterEntity";
import { FrameworkPagingDto, type FrameworkPagingDtoInterface } from "seijishikin-jp-normalize_common-tool";

interface SearchWkTblStdPersonPagingResultDtoInterface extends FrameworkPagingDtoInterface {

    /** 個人登録候補リスト */
    listWktblPerson: WkTblKanrenshaPersonMasterEntityInterface[];

}

class SearchWkTblStdPersonPagingResultDto extends FrameworkPagingDto
    implements SearchWkTblStdPersonPagingResultDtoInterface {

    /** 個人登録候補リスト */
    listWktblPerson: WkTblKanrenshaPersonMasterEntityInterface[];

    constructor() {
        super();
        this.listWktblPerson = [];
    }
}

export { type SearchWkTblStdPersonPagingResultDtoInterface, SearchWkTblStdPersonPagingResultDto }
