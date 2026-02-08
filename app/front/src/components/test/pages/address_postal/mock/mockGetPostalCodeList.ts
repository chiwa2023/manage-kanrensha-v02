import { SearchPostalCodeResultDto, type SearchPostalCodeResultDtoInterface } from "../../../../main/dto/address_postal/searchPostalCodeResultDto";
import { AddressPostalEntity, type AddressPostalEntityInterface } from "../../../../main/entity/addressPostalEntity";

export default function mockGetPostalCodeList(): SearchPostalCodeResultDtoInterface {

    const dto: SearchPostalCodeResultDtoInterface = new SearchPostalCodeResultDto();

    const list: AddressPostalEntityInterface[] = [];

    list.push(createEntity(1));
    list.push(createEntity(2));
    list.push(createEntity(3));
    list.push(createEntity(4));
    list.push(createEntity(5));

    dto.listItem = list;
    dto.allCount = 146;
    dto.limit = 30;
    dto.pageNumber = 0;

    return dto;
}


function createEntity(index: number) {

    const entity: AddressPostalEntityInterface = new AddressPostalEntity();

    entity.addressPostalId = 300 + index;
    entity.addressOrg = "湖畔町" + index + "丁目";
    entity.addressName = "架空県実在町" + "湖畔町" + index + "丁目";
    entity.lgCode = "9133" + index;
    entity.postal1 = "246801" + index;
    entity.postal2 = "801" + index;

    return entity;
}