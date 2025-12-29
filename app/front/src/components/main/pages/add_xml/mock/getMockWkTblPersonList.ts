import type WkTblPartnerPersonAddMinInterface from "../../../../entity/wkTblPartnerPersonAddMin";
import WkTblPartnerPersonAddMinDto from "../../../../entity/wkTblPartnerPersonAddMin";

export default function getMockWkTblPersonList(): WkTblPartnerPersonAddMinInterface[] {

        const list: WkTblPartnerPersonAddMinInterface[] = [];
        list.push(createEntity(1));
        list.push(createEntity(2));
        list.push(createEntity(3));
        list.push(createEntity(4));

        return list;
}


function createEntity(index: number): WkTblPartnerPersonAddMinInterface {

        const dto: WkTblPartnerPersonAddMinInterface = new WkTblPartnerPersonAddMinDto();

        dto.partnerName = "迂回献金　太郎" + index;
        dto.allAddress = "和歌山県実在市山麓町" + index;
        dto.personShokugyou = "弁護士" + index;
        dto.isAffected = true;
        dto.judgeReason = "未入力";

        return dto;
}