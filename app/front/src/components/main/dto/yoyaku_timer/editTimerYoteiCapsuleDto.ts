import { FrameworkCapsuleDto, type FrameworkCapsuleDtoInterface } from "seijishikin-jp-normalize_common-tool";
import { TimerYoteiEntity, type TimerYoteiEntityInterface } from "../../entity/timerYoteiEntity";

interface EditTimerYoteiCapsuleDtoInterface extends FrameworkCapsuleDtoInterface {

    /** 定期実行Entity */
    timerYoteiEntity: TimerYoteiEntityInterface;
}

class EditTimerYoteiCapsuleDto extends FrameworkCapsuleDto implements EditTimerYoteiCapsuleDtoInterface {

    /** 定期実行Entity */
    timerYoteiEntity: TimerYoteiEntityInterface;

    constructor() {
        super();

        this.timerYoteiEntity = new TimerYoteiEntity();
    }
}

export { type EditTimerYoteiCapsuleDtoInterface, EditTimerYoteiCapsuleDto }