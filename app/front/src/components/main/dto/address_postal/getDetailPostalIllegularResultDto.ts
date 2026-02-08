import { FrameworkPagingDto, type FrameworkPagingDtoInterface } from "seijishikin-jp-normalize_common-tool";
import type { AddressPostalIrregularEntityInterface } from "../../entity/addressPostalIrregularEntity";

interface GetDetailPostalIllegularResultDtoInterface extends FrameworkPagingDtoInterface {

    /** 郵便番号不規則リスト */
    listIrregular: AddressPostalIrregularEntityInterface[];
}

class GetDetailPostalIllegularResultDto extends FrameworkPagingDto
    implements GetDetailPostalIllegularResultDtoInterface {

    /** 郵便番号不規則リスト */
    listIrregular: AddressPostalIrregularEntityInterface[];

    constructor() {
        super();
        this.listIrregular = [];
    }
}

export { type GetDetailPostalIllegularResultDtoInterface, GetDetailPostalIllegularResultDto }
