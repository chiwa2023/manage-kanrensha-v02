import { describe, it, expect } from 'vitest';
import convertTaskToOption from '../../../main/dto/task_plan/convertTaskToOptions';
import type { SelectOptionStringDtoInterface } from '../../../main/dto/select_options/selectOptionStringDto';
import { TaskPlanBaseEntity, type TaskPlanBaseEntityInterface } from '../../../main/entity/taskPlanBaseEntity';

describe('convertTaskToOptions', () => {

    it('空リストの場合は未選択のみ作成', () => {
        const listAns: SelectOptionStringDtoInterface[] = convertTaskToOption([]);
        expect(listAns.length).toBe(1);
        expect(listAns[0]?.text).toBe("(未選択)");
        expect(listAns[0]?.value).toBe("");

    });


    it('指定リストプラス未選択を作成', () => {

        const taskList: TaskPlanBaseEntityInterface[] = [];
        const entity1:TaskPlanBaseEntityInterface = new TaskPlanBaseEntity();
        entity1.insertTimestamp = new Date(2000,1,2,3,4,5);
        entity1.taskPlanName = "タスク名称1";
        entity1.transferPass = "";

        const entity2:TaskPlanBaseEntityInterface = new TaskPlanBaseEntity();
        entity2.insertTimestamp = new Date(2001,2,3,4,5,6);
        entity2.taskPlanName = "タスク名称2";
        entity2.transferPass = "/aaa-bbb/";

        const entity3:TaskPlanBaseEntityInterface = new TaskPlanBaseEntity();
        entity3.insertTimestamp = new Date(2002,3,4,5,6,7);
        entity3.taskPlanName = "タスク名称3";
        entity3.transferPass = "/ccc-ddd/";
        taskList.push(entity1);
        taskList.push(entity2);
        taskList.push(entity3);

        const listAns: SelectOptionStringDtoInterface[] = convertTaskToOption(taskList);
        expect(listAns.length).toBe(4);

        expect(listAns[0]?.text).toBe("(未選択)");
        expect(listAns[0]?.value).toBe("");

        expect(listAns[1]?.text).toBe("【2000-02-02 03:04:05】タスク名称1");
        expect(listAns[1]?.value).toBe("");

        expect(listAns[2]?.text).toBe("【2001-03-03 04:05:06】タスク名称2");
        expect(listAns[2]?.value).toBe("/aaa-bbb/");

        expect(listAns[3]?.text).toBe("【2002-04-04 05:06:07】タスク名称3");
        expect(listAns[3]?.value).toBe("/ccc-ddd/");
    });

});
