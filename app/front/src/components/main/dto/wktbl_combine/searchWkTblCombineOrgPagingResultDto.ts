import { FrameworkPagingDto, type FrameworkPagingDtoInterface } from "seijishikin-jp-normalize_common-tool";
import type { WkTblKanrenshaCombineOrgEntityInterface } from "../../entity/wkTblKanrenshaCombineOrgEntity";

interface SearchWkTblCombineOrgPagingResultDtoInterface extends FrameworkPagingDtoInterface {
    /** 企業／団体登録候補リスト */
    listCombineOrg: WkTblKanrenshaCombineOrgEntityInterface[];
}


class SearchWkTblCombineOrgPagingResultDto extends FrameworkPagingDto implements SearchWkTblCombineOrgPagingResultDtoInterface {

    /** 企業／団体登録候補リスト */
    listCombineOrg: WkTblKanrenshaCombineOrgEntityInterface[];

    constructor() {
        super();
        this.listCombineOrg = [];
    }
}

export { type SearchWkTblCombineOrgPagingResultDtoInterface, SearchWkTblCombineOrgPagingResultDto }
