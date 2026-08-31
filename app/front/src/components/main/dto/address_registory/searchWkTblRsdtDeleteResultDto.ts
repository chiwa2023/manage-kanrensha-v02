import { FrameworkPagingDto, type FrameworkPagingDtoInterface } from "seijishikin-jp-normalize_common-tool";
import type { WkTblAddressRsdtDeleteEntityInterface } from "../../entity/wkTblAddressRsdtDeleteEntity";

interface SearchWkTblRsdtDeleteResultDtoInterface extends FrameworkPagingDtoInterface {

    /** ワークテーブル住所差分削除リスト */
    listEntity: WkTblAddressRsdtDeleteEntityInterface[];
}

class SearchWkTblRsdtDeleteResultDto extends FrameworkPagingDto implements SearchWkTblRsdtDeleteResultDtoInterface {


    /** ワークテーブル住所差分削除リスト */
    listEntity: WkTblAddressRsdtDeleteEntityInterface[];

    constructor() {
        super();
        this.listEntity = [];
    }
}

export { type SearchWkTblRsdtDeleteResultDtoInterface, SearchWkTblRsdtDeleteResultDto }
