import { FrameworkPagingDto, LeastUserDto, type FrameworkCapsuleDtoInterface, type FrameworkPagingDtoInterface, type LeastUserDtoInterface } from "seijishikin-jp-normalize_common-tool";

interface SearchTaskPlanCapsuleDtoInterface extends FrameworkPagingDtoInterface, FrameworkCapsuleDtoInterface {

    /** 検索開始日 */
    startDate: Date;

    /** 検索終了日 */
    endDate: Date;

    /** タスク検索語 */
    searchTaskWord: string;

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

    constructor() {
        super();
        const now = new Date();
        now.setMonth(now.getMonth()-1);
        this.startDate = now;
        this.endDate = new Date();
        this.searchTaskWord = ""
        this.userDto = new LeastUserDto();
    }
}

export { SearchTaskPlanCapsuleDto, type SearchTaskPlanCapsuleDtoInterface }
