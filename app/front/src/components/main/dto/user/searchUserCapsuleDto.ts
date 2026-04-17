import { FrameworkPagingDto, type FrameworkPagingDtoInterface } from "seijishikin-jp-normalize_common-tool";

interface SearchUserCapsuleDtoInterface extends FrameworkPagingDtoInterface{

    /** 名称 */
    name: string;

    /** 権限リスト */
    listRole: string[];

}


class SearchUserCapsuleDto extends FrameworkPagingDto implements SearchUserCapsuleDtoInterface {

    /** 名称 */
    name: string;

    /** 権限リスト */
    listRole: string[];

    constructor() {
        super();
        this.name = "";
        this.listRole = [];
    }
}

export { type SearchUserCapsuleDtoInterface, SearchUserCapsuleDto }