import type { TaskPlanBaseEntityInterface } from "../../entity/taskPlanBaseEntity";
import { SelectOptionStringDto, type SelectOptionStringDtoInterface } from "../select_options/selectOptionStringDto";


export default function convertTaskToOption(taskList: TaskPlanBaseEntityInterface[]): SelectOptionStringDtoInterface[] {

    const list: SelectOptionStringDtoInterface[] = [];

    // 未選択
    const initDto: SelectOptionStringDtoInterface = new SelectOptionStringDto();
    initDto.value = "";
    initDto.text = "(未選択)";

    list.push(initDto);

    for (const entity of taskList) {
        const dto: SelectOptionStringDtoInterface = new SelectOptionStringDto();
        dto.value = entity.transferPass;
        dto.text = "【" + entity.insertTimestamp + "】" + entity.taskPlanName;
        list.push(dto);
    }

    return list;
}