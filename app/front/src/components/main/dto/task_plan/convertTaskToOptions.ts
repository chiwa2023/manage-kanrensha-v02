import type { TaskPlanBaseEntityInterface } from "../../entity/taskPlanBaseEntity";
import { SelectOptionsTaskPlanDto, type SelectOptionsTaskPlanDtoInterface } from "../select_options/selectOptionsTaskPlanDto";


export default function convertTaskToOption(taskList: TaskPlanBaseEntityInterface[]): SelectOptionsTaskPlanDtoInterface[] {

    const list: SelectOptionsTaskPlanDtoInterface[] = [];

    // 未選択
    const initDto: SelectOptionsTaskPlanDtoInterface = new SelectOptionsTaskPlanDto();
    initDto.value = "";
    initDto.text = "(未選択)";

    list.push(initDto);

    for (const entity of taskList) {
        const dto: SelectOptionsTaskPlanDtoInterface = new SelectOptionsTaskPlanDto();
        dto.value = entity.transferPass;
        dto.text = "【" + entity.insertTimestamp + "】" + entity.taskPlanName;
        dto.taskYear = entity.tableYear;
        dto.taskPlanId = entity.taskPlanId;
        list.push(dto);
    }

    return list;
}