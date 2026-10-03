import { FrameworkPagingDto, type FrameworkPagingDtoInterface } from "seijishikin-jp-normalize_common-tool"
import type { KanrenshaCodeMoveEntityInterface } from "../../entity/kanrenshaCodeMoveEntity";

interface MoveKanrenshaCodeSearchResultDtoInterface extends FrameworkPagingDtoInterface {

    /** 関連者コード移動申請リスト */
    listEntity: KanrenshaCodeMoveEntityInterface[];
}


class MoveKanrenshaCodeSearchResultDto extends FrameworkPagingDto implements MoveKanrenshaCodeSearchResultDtoInterface {

    /** 関連者コード移動申請リスト */
    listEntity: KanrenshaCodeMoveEntityInterface[];

    constructor() {
        super();
        this.listEntity = [];
    }
}

export { type MoveKanrenshaCodeSearchResultDtoInterface, MoveKanrenshaCodeSearchResultDto }
