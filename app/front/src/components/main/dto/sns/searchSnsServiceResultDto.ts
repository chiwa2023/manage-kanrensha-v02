import { FrameworkPagingResultDto, type FrameworkPagingResultDtoInterface } from "seijishikin-jp-normalize_common-tool";
import type { SnsServiceEntityInterface } from "../../entity/snsServiceEntity";

interface SearchSnsServiceResultDtoInterface extends FrameworkPagingResultDtoInterface {

    /** SNSサービスEntityリスト */
    list: SnsServiceEntityInterface[];

}

class SearchSnsServiceResultDto extends FrameworkPagingResultDto
    implements SearchSnsServiceResultDtoInterface {

    /** SNSサービスEntityリスト */
    list: SnsServiceEntityInterface[];

    constructor() {
        super();

        this.list = [];
    }
}

export { type SearchSnsServiceResultDtoInterface, SearchSnsServiceResultDto }
