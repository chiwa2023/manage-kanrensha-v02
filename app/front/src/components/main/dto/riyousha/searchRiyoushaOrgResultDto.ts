import { FrameworkPagingDto, type FrameworkPagingDtoInterface } from "seijishikin-jp-normalize_common-tool";
import type { RiyoushaOrgMasterEntityInterface } from "../../entity/riyoushaOrgMasterEntity";

interface SearchRiyoushaOrgResultDtoInterface extends FrameworkPagingDtoInterface {

    /** 利用者組織リスト */
    listRiyoushaOrg: RiyoushaOrgMasterEntityInterface[];
}

class SearchRiyoushaOrgResultDto extends FrameworkPagingDto implements SearchRiyoushaOrgResultDtoInterface {

    /** 利用者組織リスト */
    listRiyoushaOrg: RiyoushaOrgMasterEntityInterface[];

    constructor() {
        super();
        this.listRiyoushaOrg = [];
    }
}

export { type SearchRiyoushaOrgResultDtoInterface, SearchRiyoushaOrgResultDto }
