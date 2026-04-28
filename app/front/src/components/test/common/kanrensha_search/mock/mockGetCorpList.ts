import type MasterCorporationInterface from "../../../../entity/masterCorporationEntity";
import MasterCorporationEntity from "../../../../entity/masterCorporationEntity";

export default function mockGetCorpList(): MasterCorporationInterface[] {
    const list: MasterCorporationInterface[] = [];

    list.push(createDto(1));
    list.push(createDto(3));
    list.push(createDto(5));
    list.push(createDto(7));

    return list;
}

function createDto(index: number): MasterCorporationInterface {
    const dto: MasterCorporationInterface = new MasterCorporationEntity();

    dto.houjinNo = "1234" + index;
    dto.corpKanrenshaCode = "1234" + index + "-abcde";

    dto.partnerName = "ABCD企業" + index;
    dto.corpDelegate = "代表者　太郎" + index;

    return dto;
}