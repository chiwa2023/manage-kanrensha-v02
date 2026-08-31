import type { WkTblKanrenshaSeijidantaiHistoryEntityInterface } from "../../entity/wkTblKanrenshaSeijidantaiHistoryEntity";
import { FrameworkPagingDto, type FrameworkPagingDtoInterface } from "seijishikin-jp-normalize_common-tool";

interface SearchWkTblHistorySeijidantaiPagingResultDtoInterface extends FrameworkPagingDtoInterface {

    /** 政治団体登録候補リスト */
    listWktblSeijidantai: WkTblKanrenshaSeijidantaiHistoryEntityInterface[];
}

class SearchWkTblHistorySeijidantaiPagingResultDto extends FrameworkPagingDto
    implements SearchWkTblHistorySeijidantaiPagingResultDtoInterface {

    /** 政治団体登録候補リスト */
    listWktblSeijidantai: WkTblKanrenshaSeijidantaiHistoryEntityInterface[];

    constructor() {
        super();
        this.listWktblSeijidantai = [];
    }

}

export { type SearchWkTblHistorySeijidantaiPagingResultDtoInterface, SearchWkTblHistorySeijidantaiPagingResultDto }