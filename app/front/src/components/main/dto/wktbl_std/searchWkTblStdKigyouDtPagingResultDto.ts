import type { WkTblKanrenshaKigyouDtMasterEntity } from "../../entity/wkTblKanrenshaKigyouDtMasterEntity";
import { FrameworkPagingDto, type FrameworkPagingDtoInterface } from "seijishikin-jp-normalize_common-tool";


interface SearchWkTblStdKigyouDtPagingResultDtoInterface extends FrameworkPagingDtoInterface {

    /** 企業／団体登録候補リスト */
    listWktblKigyouDt: WkTblKanrenshaKigyouDtMasterEntity[];

}


class SearchWkTblStdKigyouDtPagingResultDto extends FrameworkPagingDto
    implements SearchWkTblStdKigyouDtPagingResultDtoInterface {

    /** 企業／団体登録候補リスト */
    listWktblKigyouDt: WkTblKanrenshaKigyouDtMasterEntity[];

    constructor() {
        super();
        this.listWktblKigyouDt = [];
    }

}

export { type SearchWkTblStdKigyouDtPagingResultDtoInterface, SearchWkTblStdKigyouDtPagingResultDto }