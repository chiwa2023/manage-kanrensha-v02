import type MasterPoliticalOrganizationInterface from "../../../../entity/masterPoliticalOrganizationEntity";
import MasterPoliticalOrganizationDto from "../../../../entity/masterPoliticalOrganizationEntity";

export default function mockGetPoliOrgNoList(): MasterPoliticalOrganizationInterface[] {

    const list: MasterPoliticalOrganizationInterface[] = [];

    list.push(createDto(1));
    list.push(createDto(2));
    list.push(createDto(3));
    list.push(createDto(4));
    list.push(createDto(5));

    return list;
}

function createDto(index: number): MasterPoliticalOrganizationInterface {

    const dto: MasterPoliticalOrganizationInterface = new MasterPoliticalOrganizationDto();
    dto.poliOrgKanrenshaCode = "124-3455" + index;

    dto.partnerName = "ちゃらんぽらん政治団体" + index;
    dto.poliOrgDelegate = "代表者　太郎" + index;
    dto.allAddress = "和歌山県実在市" + index;
    dto.dantaiKbn = "05";

    return dto;
}
