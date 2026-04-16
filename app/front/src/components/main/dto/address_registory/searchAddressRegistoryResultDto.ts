import { FrameworkPagingDto, type FrameworkPagingDtoInterface } from "seijishikin-jp-normalize_common-tool";
import type { AddressRsdtTemplateEntityInterface } from "../../entity/addressRsdtTemplateEntity";

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