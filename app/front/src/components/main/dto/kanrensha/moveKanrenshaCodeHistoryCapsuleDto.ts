import { FrameworkCapsuleDto, type FrameworkCapsuleDtoInterface } from "seijishikin-jp-normalize_common-tool";

interface MoveKanrenshaCodeHistoryCapsuleDtoInterface extends FrameworkCapsuleDtoInterface{

    /** 廃止コード */
    codeAbolish: string;

    /** 存続コード */
    codeOrgin: string;

    /** 関連者区分 */
    kanrenshaKbn: number;

}

class MoveKanrenshaCodeHistoryCapsuleDto extends FrameworkCapsuleDto implements MoveKanrenshaCodeHistoryCapsuleDtoInterface{

    /** 廃止コード */
    codeAbolish: string;

    /** 存続コード */
    codeOrgin: string;

    /** 関連者区分 */
    kanrenshaKbn: number;

    constructor() {
        super();

        const INIT_NUMBER: number = 0;
        const INIT_STRING: string = "";

        this.codeAbolish = INIT_STRING;
        this.codeOrgin = INIT_STRING;
        this.kanrenshaKbn = INIT_NUMBER;
    }

}

export{type MoveKanrenshaCodeHistoryCapsuleDtoInterface,MoveKanrenshaCodeHistoryCapsuleDto}
