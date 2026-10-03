import { FrameworkCapsuleDto, type FrameworkCapsuleDtoInterface } from "seijishikin-jp-normalize_common-tool";

interface UpdateTaskPlanSimpleCapsuleDtoInterface extends FrameworkCapsuleDtoInterface {

    /** タスク予定Id */
    taskPlanId: number;

    /** タスク発生年 */
    taskYear: number;

}


class UpdateTaskPlanSimpleCapsuleDto extends FrameworkCapsuleDto
    implements UpdateTaskPlanSimpleCapsuleDtoInterface {

    /** タスク予定Id */
    taskPlanId: number;

    /** タスク発生年 */
    taskYear: number;

    constructor() {
        super();

        const INIT_INTEGER: number = 0;

        this.taskPlanId = INIT_INTEGER;
        this.taskYear = INIT_INTEGER;
    }
}

export { type UpdateTaskPlanSimpleCapsuleDtoInterface, UpdateTaskPlanSimpleCapsuleDto }
