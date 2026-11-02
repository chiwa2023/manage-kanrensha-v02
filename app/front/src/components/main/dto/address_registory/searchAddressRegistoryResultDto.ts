import { FrameworkPagingDto, type AddressRsdtTemplateEntityInterface, type FrameworkPagingDtoInterface } from "seijishikin-jp-normalize_common-tool";

interface SearchAddressRegistoryResultDtoInterface extends FrameworkPagingDtoInterface {

    /** 郵便番号不規則リスト */
    listRsdt: AddressRsdtTemplateEntityInterface[];

}

class SearchAddressRegistoryResultDto extends FrameworkPagingDto
    implements SearchAddressRegistoryResultDtoInterface {

    /** 郵便番号不規則リスト */
    listRsdt: AddressRsdtTemplateEntityInterface[];

    constructor() {
        super();
        this.listRsdt = [];
    }
}

export { type SearchAddressRegistoryResultDtoInterface, SearchAddressRegistoryResultDto }