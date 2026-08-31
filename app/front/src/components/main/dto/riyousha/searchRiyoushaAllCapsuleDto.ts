import { FrameworkPagingDto, type FrameworkPagingDtoInterface } from "seijishikin-jp-normalize_common-tool";

interface SearchRiyoushaAllCapsuleDtoInterface extends FrameworkPagingDtoInterface {

    /** APIユーザ検索フラグ */
    isPartnerApiSearch: boolean;

    /** 運営者検索フラグ */
    isManagerSearch: boolean;

    /** 管理者検索フラグ */
    isAdminSearch: boolean;

    /** 検索語 */
    searchNaturalWords: string;
}


class SearchRiyoushaAllCapsuleDto extends FrameworkPagingDto implements SearchRiyoushaAllCapsuleDtoInterface {

    /** APIユーザ検索フラグ */
    isPartnerApiSearch: boolean;

    /** 運営者検索フラグ */
    isManagerSearch: boolean;

    /** 管理者検索フラグ */
    isAdminSearch: boolean;

    /** 検索語 */
    searchNaturalWords: string;

    constructor() {
        super();

        // 初期データ
        const INIT_STRING: string = "";
        const INIT_BOOLEAN: boolean = true;

        this.isPartnerApiSearch = INIT_BOOLEAN;
        this.isManagerSearch = INIT_BOOLEAN;
        this.isAdminSearch = INIT_BOOLEAN;
        this.searchNaturalWords = INIT_STRING;


    }

}

export { type SearchRiyoushaAllCapsuleDtoInterface, SearchRiyoushaAllCapsuleDto }