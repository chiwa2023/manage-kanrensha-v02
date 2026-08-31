import { FrameworkCapsuleDto, type FrameworkCapsuleDtoInterface } from "seijishikin-jp-normalize_common-tool";

interface GetKanrenshaMasterCapsuleDtoInterface extends FrameworkCapsuleDtoInterface {

    /** 関連者コード */
    kanrenshaCode: string;

    /** 関連者権限 */
    kanrenshaRole: string;

}

class GetKanrenshaMasterCapsuleDto extends FrameworkCapsuleDto
    implements GetKanrenshaMasterCapsuleDtoInterface {

    /** 関連者コード */
    kanrenshaCode: string;

    /** 関連者権限 */
    kanrenshaRole: string;

    constructor() {
        super();

        const INIT_STRING: string = "";

        this.kanrenshaCode = INIT_STRING;
        this.kanrenshaRole = INIT_STRING;
    }
}

export { type GetKanrenshaMasterCapsuleDtoInterface, GetKanrenshaMasterCapsuleDto }
