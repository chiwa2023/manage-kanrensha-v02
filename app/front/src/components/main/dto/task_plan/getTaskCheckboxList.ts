import { TaskInfoCodeCheckOptionDto, type TaskInfoCodeCheckOptionDtoInterface } from "./taskInfoCodeCheckOptionDto"

function getTaskCheckboxListCategory0(): TaskInfoCodeCheckOptionDtoInterface[] {

    const list: TaskInfoCodeCheckOptionDtoInterface[] = [];

    const dto101: TaskInfoCodeCheckOptionDtoInterface = new TaskInfoCodeCheckOptionDto();
    dto101.codeValue = 101;
    dto101.codeName = "郵便番号差分CSV保存";
    list.push(dto101);

    return list;
}


function getTaskCheckboxListCategory3(): TaskInfoCodeCheckOptionDtoInterface[] {

    const list: TaskInfoCodeCheckOptionDtoInterface[] = [];

    const dto301: TaskInfoCodeCheckOptionDtoInterface = new TaskInfoCodeCheckOptionDto();
    dto301.codeValue = 301;
    dto301.codeName = "関連者XML読み取りバッチ";
    list.push(dto301);

    return list;

}

function getTaskCheckboxListCategory9(): TaskInfoCodeCheckOptionDtoInterface[] {

    const list: TaskInfoCodeCheckOptionDtoInterface[] = [];

    const dto901: TaskInfoCodeCheckOptionDtoInterface = new TaskInfoCodeCheckOptionDto();
    dto901.codeValue = 901;
    dto901.codeName = "SE権限推薦";
    list.push(dto901);

    return list;

}


export { getTaskCheckboxListCategory0, getTaskCheckboxListCategory3, getTaskCheckboxListCategory9 }