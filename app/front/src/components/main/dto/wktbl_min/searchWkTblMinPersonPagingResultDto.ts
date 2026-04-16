import type { WkTblKanrenshaPersonAddMinEntityInterface } from "../../entity/wkTblKanrenshaPersonAddMinEntity";
import { FrameworkPagingDto, type FrameworkPagingDtoInterface } from "seijishikin-jp-normalize_common-tool";

interface SearchWkTblMinPersonPagingResultDtoInterface extends FrameworkPagingDtoInterface {

    /** 個人登録候補リスト */
    listWktblPerson: WkTblKanrenshaPersonAddMinEntityInterface[];

}

class SearchWkTblMinPersonPagingResultDto extends FrameworkPagingDto
    implements SearchWkTblMinPersonPagingResultDtoInterface {

    /** 個人登録候補リスト */
    listWktblPerson: WkTblKanrenshaPersonAddMinEntityInterface[];

    constructor() {
        super();
        this.listWktblPerson = [];
    }
}

export { type SearchWkTblMinPersonPagingResultDtoInterface, SearchWkTblMinPersonPagingResultDto }