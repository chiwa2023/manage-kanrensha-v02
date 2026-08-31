import { FrameworkPagingDto, type FrameworkPagingDtoInterface } from "seijishikin-jp-normalize_common-tool";
import type { AddressPostalEntityInterface } from "../../entity/addressPostalEntity";

interface SearchPostalCodeResultDtoInterface extends FrameworkPagingDtoInterface {

    /** 郵便番号不規則リスト */
    listItem: AddressPostalEntityInterface[];

}

class SearchPostalCodeResultDto extends FrameworkPagingDto
    implements SearchPostalCodeResultDtoInterface {

    /** 郵便番号不規則リスト */
    listItem: AddressPostalEntityInterface[];

    constructor() {
        super();
        this.listItem = [];
    }
}

export { type SearchPostalCodeResultDtoInterface, SearchPostalCodeResultDto }