import { FrameworkPagingDto, type FrameworkPagingDtoInterface } from "seijishikin-jp-normalize_common-tool";
import type { KanrenshaPersonPropertyEntityInterface } from "../../entity/kanrenshaPersonPropertyEntity";

interface SearchApprovalShokugyouResultDtoInterface extends FrameworkPagingDtoInterface {
    /** 承認作業用職業リスト */
    listShokugyou: KanrenshaPersonPropertyEntityInterface[];
}

class SearchApprovalShokugyouResultDto extends FrameworkPagingDto implements SearchApprovalShokugyouResultDtoInterface {

    /** 承認作業用職業リスト */
    listShokugyou: KanrenshaPersonPropertyEntityInterface[];

    constructor() {
        super();
        this.listShokugyou = [];
    }
}

export { type SearchApprovalShokugyouResultDtoInterface, SearchApprovalShokugyouResultDto }