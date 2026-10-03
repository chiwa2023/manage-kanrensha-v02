import { FrameworkPagingDto, type FrameworkPagingDtoInterface } from "seijishikin-jp-normalize_common-tool";

interface MoveKanrenshaCodeSearchCapsuleDtoInterface extends FrameworkPagingDtoInterface {

    /** 検索開始日 */
    startDate: Date;

    /** 検索終了日 */
    endDate: Date;
    
    /** 申請中検索該当 */
    isPromoteSearch: boolean;

    /** 却下検索該当 */
    isRejectSearch: boolean;

    /** 承認検索該当 */
    isAcceptSearch: boolean;

    /** 追加調査検索該当 */
    isResearchSearch: boolean;
}


class MoveKanrenshaCodeSearchCapsuleDto extends FrameworkPagingDto implements MoveKanrenshaCodeSearchCapsuleDtoInterface {

    /** 申請中検索該当 */
    isPromoteSearch: boolean;

    /** 却下検索該当 */
    isRejectSearch: boolean;

    /** 承認検索該当 */
    isAcceptSearch: boolean;

    /** 追加調査検索該当 */
    isResearchSearch: boolean;

    /** 検索開始日 */
    startDate: Date;

    /** 検索終了日 */
    endDate: Date;

    constructor() {
        super();

        const INIT_BOOLEAN: boolean = false;

        this.startDate = new Date();
        this.endDate = new Date();
        this.startDate.setMonth(this.endDate.getMonth() - 1);

        this.isPromoteSearch = true;
        this.isRejectSearch = INIT_BOOLEAN;
        this.isAcceptSearch = INIT_BOOLEAN;
        this.isResearchSearch = INIT_BOOLEAN;
    }
}

export { type MoveKanrenshaCodeSearchCapsuleDtoInterface, MoveKanrenshaCodeSearchCapsuleDto }
