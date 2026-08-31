import { FrameworkPagingDto, type FrameworkPagingDtoInterface } from "seijishikin-jp-normalize_common-tool";
import type { AddressCityDeleteEntityInterface } from "../../entity/addressCityDeleteEntity";

interface SearchAddressCityDeleteResultDtoInterface extends FrameworkPagingDtoInterface {

    /** 地方自治体コード削除リスト */
    listEntity: AddressCityDeleteEntityInterface[];
}


class SearchAddressCityDeleteResultDto extends FrameworkPagingDto implements SearchAddressCityDeleteResultDtoInterface {

    /** 地方自治体コード削除リスト */
    listEntity: AddressCityDeleteEntityInterface[];

    constructor() {
        super();

        this.listEntity = [];
    }
}

export { type SearchAddressCityDeleteResultDtoInterface, SearchAddressCityDeleteResultDto }
