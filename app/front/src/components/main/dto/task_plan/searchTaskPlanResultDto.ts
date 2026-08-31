import { FrameworkPagingDto, type FrameworkPagingDtoInterface } from "seijishikin-jp-normalize_common-tool";
import type { TaskPlanBaseEntityInterface } from "../../entity/taskPlanBaseEntity";

interface SearchTaskPlanResultDtoInterface extends FrameworkPagingDtoInterface {

    /** タスク計画リスト */
    listTaskPlan: TaskPlanBaseEntityInterface[];

}

class SearchTaskPlanResultDto extends FrameworkPagingDto implements SearchTaskPlanResultDtoInterface {

    /** タスク計画リスト */
    listTaskPlan: TaskPlanBaseEntityInterface[];

    constructor() {
        super();
        this.listTaskPlan = [];
    }
}

export { type SearchTaskPlanResultDtoInterface, SearchTaskPlanResultDto }