import { FrameworkCapsuleDto, type FrameworkCapsuleDtoInterface, type FrameworkPagingDtoInterface } from "seijishikin-jp-normalize_common-tool";
import type { AddressAllCityEntityInterface } from "../../entity/addressAllCityEntity";

interface SearchLgCodeResultDtoInterface extends FrameworkCapsuleDtoInterface, FrameworkPagingDtoInterface {

    /** 全件数 */
    allCount: number;

    /** 抽出件数 */
    limit: number;

    /** ページ番号 */
    pageNumber: number;

    /** 地方自治体コードリスト */
    listEntity: AddressAllCityEntityInterface[];
}

class SearchLgCodeResultDto extends FrameworkCapsuleDto implements SearchLgCodeResultDtoInterface, FrameworkPagingDtoInterface {

    /** 全件数 */
    allCount: number;

    /** 抽出件数 */
    limit: number;

    /** ページ番号 */
    pageNumber: number;

    /** 地方自治体コードリスト */
    listEntity: AddressAllCityEntityInterface[];

    constructor() {
        super();

        const INIT_NUMBER: number = 0;

        this.allCount = INIT_NUMBER;
        this.limit = INIT_NUMBER;
        this.pageNumber = INIT_NUMBER;
        this.listEntity = [];
    }
}

export { type SearchLgCodeResultDtoInterface, SearchLgCodeResultDto }
