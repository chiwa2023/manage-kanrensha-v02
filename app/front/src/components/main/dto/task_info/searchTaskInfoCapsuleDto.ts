import { FrameworkPagingDto, type FrameworkPagingDtoInterface } from "seijishikin-jp-normalize_common-tool";

interface SearchTaskInfoCapsuleDtoInterface extends FrameworkPagingDtoInterface {

    /** 検索語自然検索 */
    searchNaturalWords: string;

    /** タスク種類 */
    taskType: string;
}

class SearchTaskInfoCapsuleDto extends FrameworkPagingDto implements SearchTaskInfoCapsuleDtoInterface {

    /** 検索語自然検索 */
    searchNaturalWords: string;

    /** タスク種類 */
    taskType: string;

    constructor() {
        super();

        const INIT_STRING: string = "";

        this.searchNaturalWords = INIT_STRING;
        this.taskType = INIT_STRING;

    }
}

export { type SearchTaskInfoCapsuleDtoInterface, SearchTaskInfoCapsuleDto }
