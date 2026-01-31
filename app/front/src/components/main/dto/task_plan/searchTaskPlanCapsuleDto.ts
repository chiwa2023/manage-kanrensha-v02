import { FrameworkPagingDto, LeastUserDto, type FrameworkCapsuleDtoInterface, type FrameworkPagingDtoInterface, type LeastUserDtoInterface } from "seijishikin-jp-normalize_common-tool";

interface SearchTaskPlanCapsuleDtoInterface extends FrameworkPagingDtoInterface, FrameworkCapsuleDtoInterface {

    /** 検索開始日 */
    startDate: Date;

    /** 検索終了日 */
    endDate: Date;

    /** タスク検索語 */
    searchTaskWord: string;

    /** 終了検索条件 */
    flgFinished: number;

    /** 開始検索条件 */
    flgStart: number;

    /** 中断検索条件 */
    flgSuspended: number;

    /** タスクの種類検索条件 */
    infoCodeList: number[];

}

class SearchTaskPlanCapsuleDto extends FrameworkPagingDto implements SearchTaskPlanCapsuleDtoInterface {

    /** ユーザ最小限Dto */
    userDto: LeastUserDtoInterface;

    /** 検索開始日 */
    startDate: Date;

    /** 検索終了日 */
    endDate: Date;

    /** タスク検索語 */
    searchTaskWord: string;

    /** 終了検索条件 */
    flgFinished: number;

    /** 開始検索条件 */
    flgStart: number;

    /** 中断検索条件 */
    flgSuspended: number;

    /** タスクの種類検索条件 */
    infoCodeList: number[];

    constructor() {
        super();
        const now = new Date();
        now.setMonth(now.getMonth() - 1);
        const INIT_INTEGER: number = 2; // このDto特有
        this.startDate = now;
        this.endDate = new Date();
        this.searchTaskWord = ""
        this.userDto = new LeastUserDto();
        this.flgFinished = INIT_INTEGER;
        this.flgStart = INIT_INTEGER;
        this.flgSuspended = INIT_INTEGER;
        this.infoCodeList = [];
    }
}

export { SearchTaskPlanCapsuleDto, type SearchTaskPlanCapsuleDtoInterface }
