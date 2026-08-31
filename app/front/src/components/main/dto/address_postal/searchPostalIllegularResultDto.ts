import { FrameworkPagingDto, type FrameworkPagingDtoInterface } from "seijishikin-jp-normalize_common-tool";
import type { AddressPostalIrregularEntityInterface } from "../../entity/addressPostalIrregularEntity";

interface SearchPostalIllegularResultDtoInterface extends FrameworkPagingDtoInterface {

    /** 郵便番号不規則リスト */
    listItem: AddressPostalIrregularEntityInterface[];
}

class SearchPostalIllegularResultDto extends FrameworkPagingDto
    implements SearchPostalIllegularResultDtoInterface {

    /** 郵便番号不規則リスト */
    listItem: AddressPostalIrregularEntityInterface[];

    constructor() {
        super();
        this.listItem = [];
    }
}

export { type SearchPostalIllegularResultDtoInterface, SearchPostalIllegularResultDto }
