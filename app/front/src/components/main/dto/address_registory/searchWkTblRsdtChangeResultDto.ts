import { FrameworkPagingDto, type FrameworkPagingDtoInterface } from "seijishikin-jp-normalize_common-tool";
import type { WkTblAddressRsdtChangeEntityInterface } from "../../entity/wkTblAddressRsdtChangeEntity";

interface SearchWkTblRsdtChangeResultDtoInterface extends FrameworkPagingDtoInterface {

    /** ワークテーブル住所差分変更リスト */
    listEntity: WkTblAddressRsdtChangeEntityInterface[];

}

class SearchWkTblRsdtChangeResultDto extends FrameworkPagingDto
    implements SearchWkTblRsdtChangeResultDtoInterface {

    /** ワークテーブル住所差分変更リスト */
    listEntity: WkTblAddressRsdtChangeEntityInterface[];

    constructor() {
        super();
        this.listEntity = [];
    }

}

export { type SearchWkTblRsdtChangeResultDtoInterface, SearchWkTblRsdtChangeResultDto }
