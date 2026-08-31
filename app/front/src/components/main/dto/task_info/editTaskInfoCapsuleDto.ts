import { FrameworkCapsuleDto, type FrameworkCapsuleDtoInterface } from "seijishikin-jp-normalize_common-tool";
import { TaskInfoEntity, type TaskInfoEntityInterface } from "../../entity/taskInfoEntity";

interface EditTaskInfoCapsuleDtoInterfacee extends FrameworkCapsuleDtoInterface {

    /** 編集Entity */
    taskInfoEntity: TaskInfoEntityInterface;
}

class EditTaskInfoCapsuleDto extends FrameworkCapsuleDto implements EditTaskInfoCapsuleDtoInterfacee {


    /** 編集Entity */
    taskInfoEntity: TaskInfoEntityInterface;

    constructor() {
        super();

        this.taskInfoEntity = new TaskInfoEntity();
    }

}

export { type EditTaskInfoCapsuleDtoInterfacee, EditTaskInfoCapsuleDto }
