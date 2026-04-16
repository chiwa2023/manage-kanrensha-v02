import { FrameworkPagingDto, type FrameworkPagingDtoInterface } from "seijishikin-jp-normalize_common-tool";
import type { WkTblMasterAllByXmlEntityInterface } from "../../entity/wkTblMasterAllByXmlEntity";


interface SearchWkTblAddByXmlPagingResultDtoInterface extends FrameworkPagingDtoInterface {

    /** 全XML登録リスト */
    listXmlEntity: WkTblMasterAllByXmlEntityInterface[];
}


class SearchWkTblAddByXmlPagingResultDto extends FrameworkPagingDto implements SearchWkTblAddByXmlPagingResultDtoInterface {

    /** 全XML登録リスト */
    listXmlEntity: WkTblMasterAllByXmlEntityInterface[];

    constructor() {
        super();
        this.listXmlEntity = [];
    }
}

export { type SearchWkTblAddByXmlPagingResultDtoInterface, SearchWkTblAddByXmlPagingResultDto }
