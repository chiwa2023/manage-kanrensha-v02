import { FrameworkMessageAndResultDto, type FrameworkMessageAndResultDtoInterface } from "seijishikin-jp-normalize_common-tool";
import type { YearOptionEntityInterface } from "../../entity/yearOptionEntity";

interface YearOptionResultDtoInterface extends FrameworkMessageAndResultDtoInterface {

    /** 年切り替えリスト */
    listEntity: YearOptionEntityInterface[];

}

class YearOptionResultDto extends FrameworkMessageAndResultDto
    implements YearOptionResultDtoInterface {

    /** 年切り替えリスト */
    listEntity: YearOptionEntityInterface[];

    constructor() {
        super();

        /** 年切り替えリスト */
        this.listEntity = [];
    }
}

export { type YearOptionResultDtoInterface, YearOptionResultDto }
