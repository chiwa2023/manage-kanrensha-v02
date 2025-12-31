import type { WkTblKanrenshaKigyouDtHistoryEntityInterface } from "../../entity/wkTblKanrenshaKigyouDtHistoryEntity";
import { FrameworkPagingDto, type FrameworkPagingDtoInterface } from "seijishikin-jp-normalize_common-tool";


interface SearchWkTblHistoryKigyouDtPagingResultDtoInterface extends FrameworkPagingDtoInterface {

    /** 企業／団体登録候補リスト */
    listWktblKigyouDt: WkTblKanrenshaKigyouDtHistoryEntityInterface[];

}


class SearchWkTblHistoryKigyouDtPagingResultDto extends FrameworkPagingDto
    implements SearchWkTblHistoryKigyouDtPagingResultDtoInterface {

    /** 企業／団体登録候補リスト */
    listWktblKigyouDt: WkTblKanrenshaKigyouDtHistoryEntityInterface[];

    constructor() {
        super();
        this.listWktblKigyouDt = [];
    }

}

export { type SearchWkTblHistoryKigyouDtPagingResultDtoInterface, SearchWkTblHistoryKigyouDtPagingResultDto }