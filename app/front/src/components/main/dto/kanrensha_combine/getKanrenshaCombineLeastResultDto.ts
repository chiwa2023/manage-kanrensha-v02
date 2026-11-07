import { FrameworkMessageAndResultDto, type FrameworkMessageAndResultDtoInterface } from "seijishikin-jp-normalize_common-tool";

interface GetKanrenshaCombineLeastResultDtoInterface extends FrameworkMessageAndResultDtoInterface {

    /** 関連者コード */
    kanrenshaCode: string;

    /** 関連者権限 */
    kanrenshaName: string;
}

class GetKanrenshaCombineLeastResultDto extends FrameworkMessageAndResultDto
    implements GetKanrenshaCombineLeastResultDtoInterface {

    /** 関連者コード */
    kanrenshaCode: string;

    /** 関連者権限 */
    kanrenshaName: string;

    constructor() {
        super();

        const INIT_STRING: string = "";

        this.kanrenshaCode = INIT_STRING;
        this.kanrenshaName = INIT_STRING;
    }
}

export { type GetKanrenshaCombineLeastResultDtoInterface, GetKanrenshaCombineLeastResultDto }
