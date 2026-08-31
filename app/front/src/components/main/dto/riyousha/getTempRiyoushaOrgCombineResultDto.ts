import { FrameworkMessageAndResultDto, type FrameworkMessageAndResultDtoInterface } from "seijishikin-jp-normalize_common-tool";
import { RiyoushaCombineOrgTempEntity, type RiyoushaCombineOrgTempEntityInterface } from "../../entity/riyoushaCombineOrgTempEntity";

interface GetTempRiyoushaOrgCombineResultDtoInterface extends FrameworkMessageAndResultDtoInterface {

    /** 承諾利用者所属仮登録Entity */
    combineTempEntity: RiyoushaCombineOrgTempEntityInterface;
}

class GetTempRiyoushaOrgCombineResultDto extends FrameworkMessageAndResultDto implements GetTempRiyoushaOrgCombineResultDtoInterface {

    /** 承諾利用者所属仮登録Entity */
    combineTempEntity: RiyoushaCombineOrgTempEntityInterface;

    constructor() {
        super();

       this.combineTempEntity = new RiyoushaCombineOrgTempEntity();
    }
}

export { type GetTempRiyoushaOrgCombineResultDtoInterface, GetTempRiyoushaOrgCombineResultDto }
