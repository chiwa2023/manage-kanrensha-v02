import { FrameworkPagingDto, type FrameworkPagingDtoInterface } from "seijishikin-jp-normalize_common-tool";

interface GetDetailPostalIllegularCapsuleDtoInterface extends FrameworkPagingDtoInterface {
    /** 住所検索語 */
    addressWords: string;
}

class GetDetailPostalIllegularCapsuleDto extends FrameworkPagingDto
    implements GetDetailPostalIllegularCapsuleDtoInterface {

    /** 住所検索語 */
    addressWords: string;

    constructor() {
        super();
        this.addressWords = "";
    }

}

export { type GetDetailPostalIllegularCapsuleDtoInterface, GetDetailPostalIllegularCapsuleDto }
