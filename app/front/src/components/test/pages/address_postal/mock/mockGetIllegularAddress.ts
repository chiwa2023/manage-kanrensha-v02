import { GetDetailPostalIllegularResultDto, type GetDetailPostalIllegularResultDtoInterface } from "../../../../main/dto/address_postal/getDetailPostalIllegularResultDto";
import { AddressPostalIrregularEntity, type AddressPostalIrregularEntityInterface } from "../../../../main/entity/addressPostalIrregularEntity";

export default function mockGetIllegularAddress(address: string): GetDetailPostalIllegularResultDtoInterface {


    const dto: GetDetailPostalIllegularResultDtoInterface = new GetDetailPostalIllegularResultDto();

    const list: AddressPostalIrregularEntityInterface[] = [];

    list.push(createEntity(1, address));
    list.push(createEntity(2, address));
    list.push(createEntity(3, address));
    list.push(createEntity(4, address));
    list.push(createEntity(5, address));
    list.push(createEntity(6, address));

    dto.listIrregular = list;
    dto.allCount = 88;
    dto.limit = 30;
    dto.pageNumber = 0;

    return dto;
}


function createEntity(index: number, address: string): AddressPostalIrregularEntityInterface {

    const entity: AddressPostalIrregularEntityInterface = new AddressPostalIrregularEntity();

    const INIT_STRING: string = "";

    entity.addressPostalIrregularId = index;
    entity.postalcode1 = "12323" + index;
    entity.lgCode = INIT_STRING;
    entity.addressOrg = address + index + "階";
    entity.addressName = address;
    entity.addressPostal = INIT_STRING;
    entity.addressBlock = INIT_STRING;
    // entity.isAddPostal = INIT_BOOLEAN;
    // entity.isRepairRsdt = INIT_BOOLEAN;

    return entity;

}