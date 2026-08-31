import type { WkTblKanrenshaPersonHistoryEntityInterface } from "../../entity/wkTblKanrenshaPersonHistoryEntity";
import { FrameworkPagingDto, type FrameworkPagingDtoInterface } from "seijishikin-jp-normalize_common-tool";

interface SearchWkTblHistoryPersonPagingResultDtoInterface extends FrameworkPagingDtoInterface {

    /** 個人登録候補リスト */
    listWktblPerson: WkTblKanrenshaPersonHistoryEntityInterface[];

}

class SearchWkTblHistoryPersonPagingResultDto extends FrameworkPagingDto
    implements SearchWkTblHistoryPersonPagingResultDtoInterface {

    /** 個人登録候補リスト */
    listWktblPerson: WkTblKanrenshaPersonHistoryEntityInterface[];

    constructor() {
        super();
        this.listWktblPerson = [];
    }
}

export { type SearchWkTblHistoryPersonPagingResultDtoInterface, SearchWkTblHistoryPersonPagingResultDto }