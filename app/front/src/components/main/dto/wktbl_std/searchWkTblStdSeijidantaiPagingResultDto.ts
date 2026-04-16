import type { WkTblKanrenshaSeijidantaiMasterEntityInterface } from "../../entity/wkTblKanrenshaSeijidantaiMasterEntity";
import { FrameworkPagingDto, type FrameworkPagingDtoInterface } from "seijishikin-jp-normalize_common-tool";

interface SearchWkTblStdSeijidantaiPagingResultDtoInterface extends FrameworkPagingDtoInterface {

    /** 政治団体登録候補リスト */
    listWktblSeijidantai: WkTblKanrenshaSeijidantaiMasterEntityInterface[];

}

class SearchWkTblStdSeijidantaiPagingResultDto extends FrameworkPagingDto
    implements SearchWkTblStdSeijidantaiPagingResultDtoInterface {

    /** 政治団体登録候補リスト */
    listWktblSeijidantai: WkTblKanrenshaSeijidantaiMasterEntityInterface[];

    constructor() {
        super();
        this.listWktblSeijidantai = [];
    }

}
export { type SearchWkTblStdSeijidantaiPagingResultDtoInterface, SearchWkTblStdSeijidantaiPagingResultDto }
