import { FrameworkMessageAndResultDto, type FrameworkMessageAndResultDtoInterface } from "seijishikin-jp-normalize_common-tool";
import { KanrenshaKigyouDtDto, type KanrenshaKigyouDtDtoInterface } from "./kanrenshaKigyouDtDto";

interface GetKanrenshaKigyouDtResultDtoInterface extends FrameworkMessageAndResultDtoInterface {

    /** 関連者企業団体Dto */
    kanrenshaKigyouDtDto: KanrenshaKigyouDtDtoInterface;
}

class GetKanrenshaKigyouDtResultDto extends FrameworkMessageAndResultDto
    implements GetKanrenshaKigyouDtResultDtoInterface {

    /** 関連者企業団体Dto */
    kanrenshaKigyouDtDto: KanrenshaKigyouDtDtoInterface;

    constructor() {
        super();

        this.kanrenshaKigyouDtDto = new KanrenshaKigyouDtDto();
    }
}

export { type GetKanrenshaKigyouDtResultDtoInterface, GetKanrenshaKigyouDtResultDto }
