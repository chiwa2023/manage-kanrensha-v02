import type WkTblPartnerKigyouDtAddMinInterface from "../../../../entity/wkTblPartnerKigyouDtddMinEntity";
import WkTblPartnerKigyouDtAddMinDto from "../../../../entity/wkTblPartnerKigyouDtAddMinEntity";

export default function getMockWkTblKigyouDtList(): WkTblPartnerKigyouDtAddMinInterface[] {
        const list: WkTblPartnerKigyouDtAddMinInterface[] = [];

        list.push(createEntity(1));
        list.push(createEntity(2));
        list.push(createEntity(3));
        list.push(createEntity(4));

        return list;

}

function createEntity(index: number): WkTblPartnerKigyouDtAddMinInterface {

        const dto: WkTblPartnerKigyouDtAddMinInterface = new WkTblPartnerKigyouDtddMinDto();

        dto.partnerName = "よくばり企業" + index;
        dto.allAddress = "和歌山県実在市山麓町" + index;
        dto.KigyouDtDelegate = "代表者　太郎" + index;
        dto.houjinNo = "1-2345" + index;
        dto.isAffected = true;
        dto.judgeReason = "未入力";

        return dto;
}