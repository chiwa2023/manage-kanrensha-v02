import { FrameworkPagingDto, type FrameworkPagingDtoInterface } from "seijishikin-jp-normalize_common-tool";
import type { TaskInfoEntityInterface } from "../../entity/taskInfoEntity";

interface SearchTaskInfoResultDtoInterface  extends FrameworkPagingDtoInterface{
    /** タスク情報リスト */
    listTask: TaskInfoEntityInterface[];
}

class SearchTaskInfoResultDto extends FrameworkPagingDto implements SearchTaskInfoResultDtoInterface {

    /** タスク情報リスト */
    listTask: TaskInfoEntityInterface[];

    constructor() {
        super();

        this.listTask = [];
    }

}

export { type SearchTaskInfoResultDtoInterface, SearchTaskInfoResultDto }
