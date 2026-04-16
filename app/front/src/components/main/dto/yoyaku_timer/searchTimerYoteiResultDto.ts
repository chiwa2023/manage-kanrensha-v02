import { FrameworkPagingDto, type FrameworkPagingDtoInterface } from "seijishikin-jp-normalize_common-tool";
import type { TimerYoteiEntityInterface } from "../../entity/timerYoteiEntity";

interface SearchTimerYoteiResultDtoInterface extends FrameworkPagingDtoInterface {
    /** 予約実行リスト */
    listEntity: TimerYoteiEntityInterface[];

}

class SearchTimerYoteiResultDto extends FrameworkPagingDto implements SearchTimerYoteiResultDtoInterface {

    /** 予約実行リスト */
    listEntity: TimerYoteiEntityInterface[];

    constructor() {
        super();
        this.listEntity = [];
    }

}

export { type SearchTimerYoteiResultDtoInterface, SearchTimerYoteiResultDto }