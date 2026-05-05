import { FrameworkMessageAndResultDto, type FrameworkMessageAndResultDtoInterface } from "seijishikin-jp-normalize_common-tool";
import { KanrenshaPersonDto, type KanrenshaPersonDtoInterface } from "./kanrenshaPersonDto";

interface GetKanrenshaPersonResultDtoInterface extends FrameworkMessageAndResultDtoInterface {

    /** 関連者個人Dto */
    kanrenshaPersonDto: KanrenshaPersonDtoInterface;
}

class GetKanrenshaPersonResultDto extends FrameworkMessageAndResultDto implements GetKanrenshaPersonResultDtoInterface {

    /** 関連者個人Dto */
    kanrenshaPersonDto: KanrenshaPersonDtoInterface;

    constructor() {
        super();

        this.kanrenshaPersonDto = new KanrenshaPersonDto();
    }

}

export { type GetKanrenshaPersonResultDtoInterface, GetKanrenshaPersonResultDto }
