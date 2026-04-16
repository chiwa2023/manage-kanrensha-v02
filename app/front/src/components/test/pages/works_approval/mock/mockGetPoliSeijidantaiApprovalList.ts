import { KanrenshaSeijidantaiAddressEntity, type KanrenshaSeijidantaiAddressEntityInterface } from "../../../../main/entity/kanrenshaSeijidantaiAddressEntity";

export default function mockGetSeijidantaiNoApprovalList(): KanrenshaSeijidantaiAddressEntityInterface[] {

    const list: KanrenshaSeijidantaiAddressEntityInterface[] = [];

    list.push(createDto(1));
    list.push(createDto(2));
    list.push(createDto(3));
    list.push(createDto(4));
    list.push(createDto(5));

    return list;
}


function createDto(index: number): KanrenshaSeijidantaiAddressEntityInterface {
    const entity: KanrenshaSeijidantaiAddressEntityInterface = new KanrenshaSeijidantaiAddressEntity();
    entity.seijidantaiKanrenshaCode = "11-333" + index;
    entity.kanrenshaName = "ちゃらんぽらん政治団体" + index;
    //dto.juushoAll = "山梨県架空市湖畔町" + index;
    //dto.shokugyou = "建設業従事者・職員" + index;

    entity.addressPostal = "和歌山県実在市" + index;
    entity.addressBlock = "山麓町" + index;
    entity.addressBuilding = "四角ビル" + index;
    //entity.addressAll = entity.addressPostal + entity.addressBlock + entity.addressBuilding;

    entity.postalcode1 = "56" + index;
    entity.postalcode2 = "789" + index;

    entity.lgCode = "1" + index;
    entity.machiazaId = "2" + index;
    entity.blkId = "3" + index;
    entity.prcId = "4" + index;
    entity.rsdtId = "5" + index;
    entity.rsdt2Id = "6" + index;

    entity.isPostalEdit = index % 2 == 0;
    entity.isBlockEdit = ((index + 1) % 2) == 0;
    entity.isBuildingEdit = index % 2 == 0;

    return entity;
}