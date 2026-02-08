import { FrameworkPagingDto, type FrameworkPagingDtoInterface } from "seijishikin-jp-normalize_common-tool";
import type { PostalIrregularItemDtoInterface } from "./postalIrregularItemDto";

interface SearchPostalIllegularResultDtoInterface extends FrameworkPagingDtoInterface {

    /** 郵便番号不規則リスト */
    listItem: PostalIrregularItemDtoInterface[];
}

class SearchPostalIllegularResultDto extends FrameworkPagingDto
    implements SearchPostalIllegularResultDtoInterface {

    /** 郵便番号不規則リスト */
    listItem: PostalIrregularItemDtoInterface[];

    constructor() {
        super();
        this.listItem = [];
    }
}

export { type SearchPostalIllegularResultDtoInterface, SearchPostalIllegularResultDto }
