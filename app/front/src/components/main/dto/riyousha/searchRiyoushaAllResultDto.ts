import { FrameworkPagingDto, type FrameworkPagingDtoInterface } from "seijishikin-jp-normalize_common-tool";
import type { SearchViewCombineAliveRiyoushaResultDtoInterface } from "./searchViewCombineAliveRiyoushaResultDto";

interface SearchRiyoushaAllResultDtoInterface extends FrameworkPagingDtoInterface {

    /** 全権限者リスト */
    listAllRiyousha: SearchViewCombineAliveRiyoushaResultDtoInterface[];

}

class SearchRiyoushaAllResultDto extends FrameworkPagingDto implements SearchRiyoushaAllResultDtoInterface {

    /** 全権限者リスト */
    listAllRiyousha: SearchViewCombineAliveRiyoushaResultDtoInterface[];

    constructor() {
        super();
        this.listAllRiyousha = [];
    }
}

export { type SearchRiyoushaAllResultDtoInterface, SearchRiyoushaAllResultDto }
