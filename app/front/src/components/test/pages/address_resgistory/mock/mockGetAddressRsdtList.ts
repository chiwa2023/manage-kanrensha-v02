import { SearchAddressRegistoryResultDto, type SearchAddressRegistoryResultDtoInterface } from "../../../../main/dto/address_registory/searchAddressRegistoryResultDto";
import { AddressRsdtTemplateEntity, type AddressRsdtTemplateEntityInterface } from "../../../../main/entity/addressRsdtTemplateEntity";

export default function mockGetAddressRsdtList(llgCode: string): SearchAddressRegistoryResultDtoInterface {

    const pageNumber: number = 2;

    const dto: SearchAddressRegistoryResultDtoInterface = new SearchAddressRegistoryResultDto();

    const list: AddressRsdtTemplateEntityInterface[] = [];
    const data: number = pageNumber + 1;
    list.push(createEntity(1 * data, llgCode));
    list.push(createEntity(2 * data, llgCode));
    list.push(createEntity(3 * data, llgCode));
    list.push(createEntity(4 * data, llgCode));
    list.push(createEntity(5 * data, llgCode));

    dto.listRsdt = list;
    dto.allCount = 366;
    dto.limit = 30;
    dto.pageNumber = pageNumber;

    return dto;
}


function createEntity(index: number, llgCode: string): AddressRsdtTemplateEntityInterface {

    const entity: AddressRsdtTemplateEntityInterface = new AddressRsdtTemplateEntity();

    entity.addressRsdtId = 1000 + index;
    entity.addressBlock = "和歌山県架空市山麓町2丁目" + index;
    entity.addressBuilding = "三角ビル" + index + "号室";
    entity.lgCode = llgCode;
    entity.postalcode1 = "989";
    entity.postalcode2 = "8986";
    entity.machiazaId = index + "100";
    entity.blkId = index + "30";
    entity.prcId = index + "40";
    entity.rsdtId = index + "50";
    entity.rsdt2Id = index + "60";

    entity.effectDate = new Date("1946-01-01");
    entity.abolishDate = new Date("1946-01-01");

    return entity;
}