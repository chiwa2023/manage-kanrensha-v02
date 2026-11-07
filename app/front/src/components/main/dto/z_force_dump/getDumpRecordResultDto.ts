import { FrameworkMessageAndResultDto, type FrameworkMessageAndResultDtoInterface } from "seijishikin-jp-normalize_common-tool";
import type { DumpRecordEntityInterface } from "../../entity/dumpRecordEntity";

interface GetDumpRecordResultDtoInterface extends FrameworkMessageAndResultDtoInterface {

    /** ダンプ記録リスト */
    listEntiy: DumpRecordEntityInterface[];
}

class GetDumpRecordResultDto extends FrameworkMessageAndResultDto
    implements GetDumpRecordResultDtoInterface {

    /** ダンプ記録リスト */
    listEntiy: DumpRecordEntityInterface[];

    constructor() {
        super();
        this.listEntiy = [];
    }
}

export { type GetDumpRecordResultDtoInterface, GetDumpRecordResultDto }
