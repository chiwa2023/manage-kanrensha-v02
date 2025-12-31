import { FrameworkPagingDto, type FrameworkPagingDtoInterface } from "seijishikin-jp-normalize_common-tool";
import { LeastUserDto, type LeastUserDtoInterface } from "../user/leastUserDto";

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
