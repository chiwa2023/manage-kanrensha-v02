import { KanrenshaPersonPropertyEntity, type KanrenshaPersonPropertyEntityInterface } from "../../../../main/entity/kanrenshaPersonPropertyEntity";

export default function mockGetPersonApprovaShokugyouList(): KanrenshaPersonPropertyEntityInterface[] {

    const list: KanrenshaPersonPropertyEntityInterface[] = [];

    list.push(createDto(1));
    list.push(createDto(2));
    list.push(createDto(3));
    list.push(createDto(4));
    list.push(createDto(5));

    // list[0].inputAddressDto.isPostalEdit = true;
    // list[0].inputAddressDto.addressPostal = "和歌山県実在市架空町1丁目";
    // list[0].rsdtAddressPostl = "和歌山県実在市架空町一丁目";

    // list[1].inputAddressDto.isBlockEdit = true;
    // list[1].inputAddressDto.addressBlock = "４４４番地－1";
    // list[1].rsdtAddressBlock = "444番地1号";

    // list[2].inputAddressDto.isBuildingEdit = true;
    // list[2].inputAddressDto.addressBuilding = "四角ビル109";
    // list[2].rsdtAddressBuilding = "";

    // list[3].inputShokugyouDto.gyoushu = "林業";
    // list[3].inputShokugyouDto.yakushoku = "一般職員";
    // //list[3].allShokugyou = "林業社員・職員";
    // list[3].inputShokugyouDto.shokugyouUserWrite = "";

    return list;
}


function createDto(index: number): KanrenshaPersonPropertyEntityInterface {
    const entity: KanrenshaPersonPropertyEntityInterface = new KanrenshaPersonPropertyEntity();
    entity.personKanrenshaCode = "11-333" + index;
    entity.kanrenshaName = "迂回献金　太郎" + index;

    entity.gyoushu = "建設";
    entity.yakushoku = "一般職員";
    entity.shokugyouUserWrite = "申告職業" + index;
    entity.kigyouDtNo = "1234";
    entity.kigyouDtName = "一般職員";
    entity.kigyouDtAddress = "一般職員";

    entity.isShokyouEdit = ("" !== entity.shokugyouUserWrite);

    return entity;
}