import { FrameworkMessageAndResultDto, type FrameworkMessageAndResultDtoInterface } from "seijishikin-jp-normalize_common-tool";
import type { KanrenshaCodeMoveHistoryDtoInterface } from "./kanrenshaCodeMoveHistoryDto";

interface MoveKanrenshaCodeHistoryResultDtoInterface extends FrameworkMessageAndResultDtoInterface {

    /** 存続コードユーザリスト */
    listUserOrgin: KanrenshaCodeMoveHistoryDtoInterface[];

    /** 廃止コードユーザリスト */
    listUserAbolish: KanrenshaCodeMoveHistoryDtoInterface[];

    /** 存続コードコードリスト */
    listCodeOrgin: KanrenshaCodeMoveHistoryDtoInterface[];

    /** 廃止コードコードリスト */
    listCodeAbolish: KanrenshaCodeMoveHistoryDtoInterface[];
}

class MoveKanrenshaCodeHistoryResultDto extends FrameworkMessageAndResultDto
    implements MoveKanrenshaCodeHistoryResultDtoInterface {

    /** 存続コードユーザリスト */
    listUserOrgin: KanrenshaCodeMoveHistoryDtoInterface[];

    /** 廃止コードユーザリスト */
    listUserAbolish: KanrenshaCodeMoveHistoryDtoInterface[];

    /** 存続コードコードリスト */
    listCodeOrgin: KanrenshaCodeMoveHistoryDtoInterface[];

    /** 廃止コードコードリスト */
    listCodeAbolish: KanrenshaCodeMoveHistoryDtoInterface[];

    constructor() {
        super();

        this.listUserOrgin = [];
        this.listUserAbolish = [];
        this.listCodeOrgin = [];
        this.listCodeAbolish = [];
    }


}

export { type MoveKanrenshaCodeHistoryResultDtoInterface, MoveKanrenshaCodeHistoryResultDto }
