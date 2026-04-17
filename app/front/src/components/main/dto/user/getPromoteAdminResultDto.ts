import { FrameworkMessageAndResultDto, type FrameworkMessageAndResultDtoInterface } from "seijishikin-jp-normalize_common-tool";
import { PromoteAdminEntity, type PromoteAdminEntityInterface } from "../../entity/promoteAdminEntity";

interface GetPromoteAdminResultDtoInterface extends FrameworkMessageAndResultDtoInterface {
    
    /** SE権限追加推薦Entity */
    promoteAdminEntity: PromoteAdminEntityInterface;

    /** 取得件数 */
    promoteCount: number;
}


class GetPromoteAdminResultDto extends FrameworkMessageAndResultDto
    implements GetPromoteAdminResultDtoInterface {

    /** SE権限追加推薦Entity */
    promoteAdminEntity: PromoteAdminEntityInterface;

    /** 取得件数 */
    promoteCount: number;

    constructor() {
        super();

        this.promoteAdminEntity = new PromoteAdminEntity();
        this.promoteCount = 0;
    }
}

export { type GetPromoteAdminResultDtoInterface, GetPromoteAdminResultDto }
