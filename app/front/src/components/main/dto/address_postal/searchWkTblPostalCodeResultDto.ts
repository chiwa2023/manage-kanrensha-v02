import { FrameworkPagingDto, type FrameworkPagingDtoInterface } from "seijishikin-jp-normalize_common-tool";
import type { WkTblPostalEditEntityInterface } from "../../entity/wkTblPostalEditEntity";

interface SearchWkTblPostalCodeResultDtoInterface extends FrameworkPagingDtoInterface {

    /** 郵便番号差分ワークテーブルリスト */
    listEntity: WkTblPostalEditEntityInterface[];

}

class SearchWkTblPostalCodeResultDto extends FrameworkPagingDto implements SearchWkTblPostalCodeResultDtoInterface {

    /** 郵便番号差分ワークテーブルリスト */
    listEntity: WkTblPostalEditEntityInterface[];

    constructor() {
        super();
        this.listEntity = [];
    }
}

export { type SearchWkTblPostalCodeResultDtoInterface, SearchWkTblPostalCodeResultDto }
