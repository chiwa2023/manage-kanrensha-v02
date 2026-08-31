import { FrameworkMessageAndResultDto, type FrameworkMessageAndResultDtoInterface } from "seijishikin-jp-normalize_common-tool";
import { RiyoushaPartnerApiMasterEntity, type RiyoushaPartnerApiMasterEntityInterface } from "../../entity/riyoushaPartnerApiMasterEntity";
import { RiyoushaManagerMasterEntity, type RiyoushaManagerMasterEntityInterface } from "../../entity/riyoushaManagerMasterEntity";

interface GetRiyoushaMasterResultDtoInterface extends FrameworkMessageAndResultDtoInterface {
    /** 運営者マスタEntity */
    managerMasterEntity: RiyoushaManagerMasterEntityInterface;

    /** APIユーザマスタEntity */
    partnerApiMasterEntity: RiyoushaPartnerApiMasterEntityInterface;

}

class GetRiyoushaMasterResultDto extends FrameworkMessageAndResultDto
    implements GetRiyoushaMasterResultDtoInterface {

    /** 運営者マスタEntity */
    managerMasterEntity: RiyoushaManagerMasterEntityInterface;

    /** APIユーザマスタEntity */
    partnerApiMasterEntity: RiyoushaPartnerApiMasterEntityInterface;

    constructor() {
        super();

        /** 運営者マスタEntity */
        this.managerMasterEntity = new RiyoushaManagerMasterEntity();

        /** APIユーザマスタEntity */
        this.partnerApiMasterEntity = new RiyoushaPartnerApiMasterEntity();

    }
}

export { type GetRiyoushaMasterResultDtoInterface, GetRiyoushaMasterResultDto }
