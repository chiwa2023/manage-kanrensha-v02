import type { WkTblKanrenshaSeijidantaiAddMinEntityInterface } from "../../entity/wkTblKanrenshaSeijidantaiAddMinEntity";
import { FrameworkPagingDto, type FrameworkPagingDtoInterface } from "seijishikin-jp-normalize_common-tool";

interface SearchWkTblMinSeijidantaiPagingResultDtoInterface extends FrameworkPagingDtoInterface {

    /** 政治団体登録候補リスト */
    listWktblSeijidantai: WkTblKanrenshaSeijidantaiAddMinEntityInterface[];

}

class SearchWkTblMinSeijidantaiPagingResultDto extends FrameworkPagingDto
    implements SearchWkTblMinSeijidantaiPagingResultDtoInterface {

    /** 政治団体登録候補リスト */
    listWktblSeijidantai: WkTblKanrenshaSeijidantaiAddMinEntityInterface[];

    constructor() {
        super();
        this.listWktblSeijidantai = [];
    }

}

export { type SearchWkTblMinSeijidantaiPagingResultDtoInterface, SearchWkTblMinSeijidantaiPagingResultDto }