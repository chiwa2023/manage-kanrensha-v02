import { FrameworkPagingDto, type FrameworkPagingDtoInterface } from "seijishikin-jp-normalize_common-tool";
import type { AddressAllCityEntityInterface } from "../../entity/addressAllCityEntity";

interface SearchLocalGovernmentResultDtoInterface extends FrameworkPagingDtoInterface {

    /** 郵便番号不規則リスト */
    listAllCity: AddressAllCityEntityInterface[];

}

class SearchLocalGovernmentResultDto extends FrameworkPagingDto
    implements SearchLocalGovernmentResultDtoInterface {

    /** 郵便番号不規則リスト */
    listAllCity: AddressAllCityEntityInterface[];

    constructor() {
        super();
        this.listAllCity = [];
    }
}

export { type SearchLocalGovernmentResultDtoInterface, SearchLocalGovernmentResultDto }
