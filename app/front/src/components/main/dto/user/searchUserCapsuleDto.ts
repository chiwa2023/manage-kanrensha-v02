interface SearchUserCapsuleDtoInterface {

    /** 名称 */
    name: string;

    /** 権限リスト */
    listRole: string[];

}


class SearchUserCapsuleDto implements SearchUserCapsuleDtoInterface {

    /** 名称 */
    name: string;

    /** 権限リスト */
    listRole: string[];

    constructor() {
        this.name = "";
        this.listRole = [];
    }
}

export { type SearchUserCapsuleDtoInterface, SearchUserCapsuleDto }