import { FrameworkCapsuleDto, type FrameworkCapsuleDtoInterface } from "seijishikin-jp-normalize_common-tool";
import { KanrenshaCodeMoveEntity, type KanrenshaCodeMoveEntityInterface } from "../../entity/kanrenshaCodeMoveEntity";

interface MoveKanrenshaCodeAcceptCapsuleDtoInterface extends FrameworkCapsuleDtoInterface {
    /** 関連者コード移動申請Entity */
    kanrenshaCodeMoveEntity: KanrenshaCodeMoveEntityInterface;
    
    /** タスク計画Id */
    taskPlanId: number;

    /** タスク計画発生年 */
    taskYear: number;

}

class MoveKanrenshaCodeAcceptCapsuleDto extends FrameworkCapsuleDto
    implements MoveKanrenshaCodeAcceptCapsuleDtoInterface {

    /** 関連者コード移動申請Entity */
    kanrenshaCodeMoveEntity: KanrenshaCodeMoveEntityInterface;

    /** タスク計画Id */
    taskPlanId: number;

    /** タスク計画発生年 */
    taskYear: number;

    constructor() {
        super();
        const INIT_NUMBER: number = 0;

        this.kanrenshaCodeMoveEntity = new KanrenshaCodeMoveEntity();
        this.taskPlanId = INIT_NUMBER;
        this.taskYear = INIT_NUMBER;
    }
}

export { type MoveKanrenshaCodeAcceptCapsuleDtoInterface, MoveKanrenshaCodeAcceptCapsuleDto }
