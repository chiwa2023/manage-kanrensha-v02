import { FrameworkPagingDto, LeastUserDto, type FrameworkPagingDtoInterface, type LeastUserDtoInterface } from "seijishikin-jp-normalize_common-tool";

interface SearchWkTblAddressRsdtCapsuleDtoInterface extends FrameworkPagingDtoInterface {

    /** 履歴検索該否 */
    isSearchHistory: boolean;

    /** ワークテーブル編集者 */
    userDto: LeastUserDtoInterface;
}

class SearchWkTblAddressRsdtCapsuleDto extends FrameworkPagingDto
    implements SearchWkTblAddressRsdtCapsuleDtoInterface {

    /** 履歴検索該否 */
    isSearchHistory: boolean;

    /** ワークテーブル編集者 */
    userDto: LeastUserDtoInterface;

    constructor() {
        super();
        this.isSearchHistory = false;
        this.userDto = new LeastUserDto();
    }
}

export { type SearchWkTblAddressRsdtCapsuleDtoInterface, SearchWkTblAddressRsdtCapsuleDto }
