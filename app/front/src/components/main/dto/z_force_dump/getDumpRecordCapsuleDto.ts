import { FrameworkCapsuleDto, type FrameworkCapsuleDtoInterface } from "seijishikin-jp-normalize_common-tool";

interface GetDumpRecordCapsuleDtoInterrface extends FrameworkCapsuleDtoInterface {
    /** ダンプ記録リスト */
    listTaskCode: number[];

}

class GetDumpRecordCapsuleDto extends FrameworkCapsuleDto
    implements GetDumpRecordCapsuleDtoInterrface {

    /** ダンプ記録リスト */
    listTaskCode: number[];

    constructor() {
        super();
        this.listTaskCode = [];
    }

}

export { type GetDumpRecordCapsuleDtoInterrface, GetDumpRecordCapsuleDto }
