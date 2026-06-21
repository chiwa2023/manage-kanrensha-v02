import { FrameworkPagingDto, type FrameworkPagingDtoInterface } from "seijishikin-jp-normalize_common-tool";
import type { AddressRsdtTemplateEntityInterface } from "../../entity/addressRsdtTemplateEntity";

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
