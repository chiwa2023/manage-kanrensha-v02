import { FrameworkPagingDto, type FrameworkPagingDtoInterface } from "seijishikin-jp-normalize_common-tool";
import type { KanrenshaAddressBaseEntityInterface } from "../../entity/kanrenshaAddressBaseEntity";

interface SearchApprovalAddressResultDtoInterface extends FrameworkPagingDtoInterface {
    /** 住所承認作業リスト */
    listAddress: KanrenshaAddressBaseEntityInterface[];
}

class SearchApprovalAddressResultDto extends FrameworkPagingDto implements SearchApprovalAddressResultDtoInterface {

    /** 住所承認作業リスト */
    listAddress: KanrenshaAddressBaseEntityInterface[];

    constructor() {
        super();
        this.listAddress = [];
    }

}

export { type SearchApprovalAddressResultDtoInterface, SearchApprovalAddressResultDto }