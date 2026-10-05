import { FrameworkMessageAndResultDto, type FrameworkMessageAndResultDtoInterface } from "seijishikin-jp-normalize_common-tool";
import type { TaskInfoCodeCheckOptionDtoInterface } from "../task_plan/taskInfoCodeCheckOptionDto";

interface GetTaskInfoOptionsResultDtoInterface extends FrameworkMessageAndResultDtoInterface {

    /** 選択肢リスト */
    listDto: TaskInfoCodeCheckOptionDtoInterface[];
}

class GetTaskInfoOptionsResultDto extends FrameworkMessageAndResultDto
    implements GetTaskInfoOptionsResultDtoInterface {

    /** 選択肢リスト */
    listDto: TaskInfoCodeCheckOptionDtoInterface[];

    constructor() {
        super();

        this.listDto = [];
    }
}

export { type GetTaskInfoOptionsResultDtoInterface, GetTaskInfoOptionsResultDto }
