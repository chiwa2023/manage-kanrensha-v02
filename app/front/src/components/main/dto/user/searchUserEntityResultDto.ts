import { FrameworkPagingDto, type FrameworkPagingDtoInterface } from "seijishikin-jp-normalize_common-tool";
import type { UserPersonEntityInterface } from "../../entity/userPersonEntity";

interface SearchUserEntityResultDtoInterface extends FrameworkPagingDtoInterface {

    /** 検索結果リスト */
    listPersonEntity: UserPersonEntityInterface[];
}


class SearchUserEntityResultDto extends FrameworkPagingDto implements SearchUserEntityResultDtoInterface {

    /** 検索結果リスト */
    listPersonEntity: UserPersonEntityInterface[];

    constructor() {
        super();
        this.listPersonEntity = [];
    }
}


export { type SearchUserEntityResultDtoInterface, SearchUserEntityResultDto }