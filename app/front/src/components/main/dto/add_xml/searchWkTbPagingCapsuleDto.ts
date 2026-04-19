import { FrameworkPagingDto, LeastUserDto, type FrameworkPagingDtoInterface, type LeastUserDtoInterface } from "seijishikin-jp-normalize_common-tool";

interface SearchWkTblPagingCapsuleDtoInterface extends FrameworkPagingDtoInterface {


    /** 履歴データ検索結果保持 */
    hasHistorry: boolean;

    /** 終了データ検索結果保持 */
    hasFinished: boolean;

    /** 無影響検索結果保持 */
    hasAffectNot: boolean;

    /** ユーザ最小限Dto */
    userDto: LeastUserDtoInterface;
}


class SearchWkTblPagingCapsuleDto extends FrameworkPagingDto
    implements SearchWkTblPagingCapsuleDtoInterface {

    /** 履歴データ検索結果保持 */
    hasHistorry: boolean;

    /** 終了データ検索結果保持 */
    hasFinished: boolean;

    /** 無影響検索結果保持 */
    hasAffectNot: boolean;

    /** ユーザ最小限Dto */
    userDto: LeastUserDtoInterface;

    constructor() {

        super();
        const INIT_BOOLEAN: boolean = false;

        this.hasFinished = INIT_BOOLEAN;
        this.hasAffectNot = INIT_BOOLEAN
        this.hasHistorry = INIT_BOOLEAN
        this.userDto = new LeastUserDto();
    }

}

export { type SearchWkTblPagingCapsuleDtoInterface, SearchWkTblPagingCapsuleDto }
