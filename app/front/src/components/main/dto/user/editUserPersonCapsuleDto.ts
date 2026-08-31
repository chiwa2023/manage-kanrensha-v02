import { FrameworkCapsuleDto, type FrameworkCapsuleDtoInterface } from "seijishikin-jp-normalize_common-tool";

interface EditUserPersonCapsuleDtoInterface extends FrameworkCapsuleDtoInterface {


    /** タスク開始通知有無 */
    isAlertTaskStart: boolean;

    /** タスク終了通知有無 */
    isAlertTaskEnd: boolean;
}
class EditUserPersonCapsuleDto extends FrameworkCapsuleDto implements EditUserPersonCapsuleDtoInterface {


    /** タスク開始通知有無 */
    isAlertTaskStart: boolean;

    /** タスク終了通知有無 */
    isAlertTaskEnd: boolean;

    constructor() {
        super();

        const INIT_BOOLEAN: boolean = false;

        this.isAlertTaskStart = INIT_BOOLEAN;
        this.isAlertTaskEnd = INIT_BOOLEAN;
    }
}

export { type EditUserPersonCapsuleDtoInterface, EditUserPersonCapsuleDto }