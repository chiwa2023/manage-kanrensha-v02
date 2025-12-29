import type WkTblPartnerSeijidantaiAddMinInterface from "../../../../entity/wkTblPartnerSeijidantaiAddMin";
import WkTblPartnerSeijidantaiAddMinDto from "../../../../entity/wkTblPartnerSeijidantaiAddMin";

export default function getMockWkTblSeijidantaiList(): WkTblPartnerSeijidantaiAddMinInterface[] {
    const list: WkTblPartnerSeijidantaiAddMinInterface[] = [];
    list.push(createEntity(1));
    list.push(createEntity(2));
    list.push(createEntity(3));
    list.push(createEntity(4));

    return list;
}

function createEntity(index: number): WkTblPartnerSeijidantaiAddMinInterface {

    const dto: WkTblPartnerSeijidantaiAddMinInterface = new WkTblPartnerSeijidantaiAddMinDto();

    dto.partnerName = "迂回献金　太郎" + index;
    dto.allAddress = "和歌山県実在市山麓町" + index;
    dto.seijidantaiDelegate = "代表者　太郎" + index;
    dto.dantaiKbn = "01";
    dto.isAffected = true;
    dto.judgeReason = "未入力";

    return dto;
}