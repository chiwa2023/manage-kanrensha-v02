import { FrameworkCapsuleDto, type FrameworkCapsuleDtoInterface } from "seijishikin-jp-normalize_common-tool";

interface AcceptRiyoushaCombineCapsuleDtoInterface extends FrameworkCapsuleDtoInterface {
    /** タスク計画Id */
    taskPlanId: number;

    /** 発生年 */
    taskYear: number;

    /** 承諾状態 */
    isAsscept: boolean;

    /** 仮紐づけId */
    orgTempId: number;
}

class AcceptRiyoushaCombineCapsuleDto extends FrameworkCapsuleDto
    implements AcceptRiyoushaCombineCapsuleDtoInterface {

    /** タスク計画Id */
    taskPlanId: number;

    /** 発生年 */
    taskYear: number;

    /** 承諾状態 */
    isAsscept: boolean;

    /** 仮紐づけId */
    orgTempId: number;

    constructor() {
        super();
        const INIT_NUMBER: number = 0;

        this.taskPlanId = INIT_NUMBER;
        this.taskYear = INIT_NUMBER;
        this.isAsscept = false;
        this.orgTempId = INIT_NUMBER;
    }

}

export { type AcceptRiyoushaCombineCapsuleDtoInterface, AcceptRiyoushaCombineCapsuleDto }

