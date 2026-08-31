import { FrameworkPagingDto, type FrameworkPagingDtoInterface } from "seijishikin-jp-normalize_common-tool";

interface SearchWorksApprovalCapsuleDtoInterfce extends FrameworkPagingDtoInterface {
    /** 検索開始日 */
    startDate: Date;

    /** 検索終了日 */
    endDate: Date;

    /** 終了タスク除外フラグ */
    isExcludeFinishedTask: boolean;
}

class SearchWorksApprovalCapsuleDto extends FrameworkPagingDto implements SearchWorksApprovalCapsuleDtoInterfce {

    /** 検索開始日 */
    startDate: Date;

    /** 検索終了日 */
    endDate: Date;

    /** 終了タスク除外フラグ */
    isExcludeFinishedTask: boolean;


    constructor() {
        super();

        this.startDate = new Date;
        this.endDate = new Date();
        this.isExcludeFinishedTask = true; // 基本的に作業が必要なデータしか呼ばない
        this.startDate.setDate(this.endDate.getDate() - 28)
    }

}

export { type SearchWorksApprovalCapsuleDtoInterfce, SearchWorksApprovalCapsuleDto }