import type MasterPersonInterface from "../../../../entity/masterPersonEntity";
import MasterPersonEntity from "../../../../entity/masterPersonEntity";

export default function mockGetPersonList(): MasterPersonInterface[] {

    const list: MasterPersonInterface[] = [];

    list.push(createDto(1));
    list.push(createDto(2));
    list.push(createDto(3));
    list.push(createDto(4));
    list.push(createDto(5));

    return list;
}


function createDto(index: number): MasterPersonInterface {
    const dto: MasterPersonInterface = new MasterPersonEntity();
    dto.personKanrenshaCode = "11-333" + index;
    dto.partnerName = "迂回　献金太郎" + index;
    dto.allAddress = "山梨県架空市湖畔町" + index;
    dto.personShokugyou = "建設業従事者・職員" + index;

    return dto;
}