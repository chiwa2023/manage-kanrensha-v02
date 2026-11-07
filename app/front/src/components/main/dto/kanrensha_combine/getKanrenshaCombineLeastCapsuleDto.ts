import { FrameworkCapsuleDto, type FrameworkCapsuleDtoInterface } from "seijishikin-jp-normalize_common-tool";

interface GetKanrenshaCombineLeastCapsuleDtoInterface extends FrameworkCapsuleDtoInterface {

    /** 関連者コード */
    kanrenshaCode: string;

}

class GetKanrenshaCombineLeastCapsuleDto extends FrameworkCapsuleDto
    implements GetKanrenshaCombineLeastCapsuleDtoInterface {

    /** 関連者コード */
    kanrenshaCode: string;

    constructor() {
        super();

        this.kanrenshaCode = "";
    }
}

export { type GetKanrenshaCombineLeastCapsuleDtoInterface, GetKanrenshaCombineLeastCapsuleDto }
