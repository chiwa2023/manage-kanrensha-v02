import type { WkTblKanrenshaKigyouDtAddMinEntityInterface } from "../../entity/wkTblKanrenshaKigyouDtAddMinEntity";
import { FrameworkPagingDto, type FrameworkPagingDtoInterface } from "seijishikin-jp-normalize_common-tool";

interface SearchWkTblMinKigyouDtPagingResultDtoInterface extends FrameworkPagingDtoInterface {

    /** 企業／団体登録候補リスト */
    listWktblKigyouDt: WkTblKanrenshaKigyouDtAddMinEntityInterface[];

}


class SearchWkTblMinKigyouDtPagingResultDto extends FrameworkPagingDto
    implements SearchWkTblMinKigyouDtPagingResultDtoInterface {

    /** 企業／団体登録候補リスト */
    listWktblKigyouDt: WkTblKanrenshaKigyouDtAddMinEntityInterface[];

    constructor() {
        super();
        this.listWktblKigyouDt = [];
    }

}

export { type SearchWkTblMinKigyouDtPagingResultDtoInterface, SearchWkTblMinKigyouDtPagingResultDto }