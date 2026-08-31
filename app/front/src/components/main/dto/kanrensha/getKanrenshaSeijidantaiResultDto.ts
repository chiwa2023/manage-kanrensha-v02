import { FrameworkMessageAndResultDto, type FrameworkMessageAndResultDtoInterface } from "seijishikin-jp-normalize_common-tool";
import { KanrenshaSeijidantaiDto, type KanrenshaSeijidantaiDtoInterface } from "./kanrenshaSeijidantaiDto";

interface GetKanrenshaSeijidantaiResultDtoInterface extends FrameworkMessageAndResultDtoInterface {

    /** 関連者政治団体Dto */
    kanrenshaSeijidantaiDto: KanrenshaSeijidantaiDtoInterface;
}


class GetKanrenshaSeijidantaiResultDto extends FrameworkMessageAndResultDto implements GetKanrenshaSeijidantaiResultDtoInterface {

    /** 関連者政治団体Dto */
    kanrenshaSeijidantaiDto: KanrenshaSeijidantaiDtoInterface;

    constructor() {
        super();

        this.kanrenshaSeijidantaiDto = new KanrenshaSeijidantaiDto();
    }
}

export { type GetKanrenshaSeijidantaiResultDtoInterface, GetKanrenshaSeijidantaiResultDto }
