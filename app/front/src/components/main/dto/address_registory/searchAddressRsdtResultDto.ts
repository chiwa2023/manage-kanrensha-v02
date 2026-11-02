import { FrameworkPagingDto, type AddressRsdtTemplateEntityInterface, type FrameworkPagingDtoInterface } from "seijishikin-jp-normalize_common-tool";

interface SearchAddressRsdtResultDtoInterface extends FrameworkPagingDtoInterface {

    /** 住居リスト */
    listEntity: AddressRsdtTemplateEntityInterface[];
}

class SearchAddressRsdtResultDto extends FrameworkPagingDto implements SearchAddressRsdtResultDtoInterface {

    /** 住居リスト */
    listEntity: AddressRsdtTemplateEntityInterface[];

    constructor() {
        super();

        /** 住居リスト */
        this.listEntity = [];
    }

}

export { type SearchAddressRsdtResultDtoInterface, SearchAddressRsdtResultDto }
