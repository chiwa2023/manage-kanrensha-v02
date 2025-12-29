import type { WkTblMasterAllByXmlEntityInterface } from "../../entity/wkTblMasterAllByXmlEntity";
import { PagingDto, type PagingDtoInterface } from "../paging/pagingDto";


interface SearchWkTblAddByXmlPagingResultDtoInterface extends PagingDtoInterface {

    /** 全XML登録リスト */
    listXmlEntity: WkTblMasterAllByXmlEntityInterface[];

}


class SearchWkTblAddByXmlPagingResultDto extends PagingDto implements SearchWkTblAddByXmlPagingResultDtoInterface {

    /** 全XML登録リスト */
    listXmlEntity: WkTblMasterAllByXmlEntityInterface[];

    constructor() {
        super();
        this.listXmlEntity = [];
    }
}

export { type SearchWkTblAddByXmlPagingResultDtoInterface, SearchWkTblAddByXmlPagingResultDto }
