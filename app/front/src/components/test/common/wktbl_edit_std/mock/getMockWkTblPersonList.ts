import { WkTblKanrenshaPersonMasterEntity, type WkTblKanrenshaPersonMasterEntityInterface } from "../../../entity/wkTblKanrenshaPersonMasterEntity";

export default function getMockWkTblPersonList(): WkTblKanrenshaPersonMasterEntityInterface[] {

        const list: WkTblKanrenshaPersonMasterEntityInterface[] = [];
        list.push(createEntity(1));
        list.push(createEntity(2));
        list.push(createEntity(3));
        list.push(createEntity(4));

        return list;
}

function createEntity(index: number): WkTblKanrenshaPersonMasterEntityInterface {

        const entity: WkTblKanrenshaPersonMasterEntityInterface = new WkTblKanrenshaPersonMasterEntity();
        entity.wkTblKanrenshaPersonMasterId = index;
        entity.isLatest = true;

        entity.kanrenshaName = "迂回献金　ミカエル　太郎" + index;
        entity.allAddress = "和歌山県実在市山麓町" + index;
        entity.personShokugyou = "団体役員" + index;
        entity.addressPostal = "和歌山県実在市山麓町" + index;
        entity.addressBlock = "2丁目6番地" + index;
        entity.addressBuilding = "四角ビル7F" + index;
        entity.postalcode1 = "012" + index;
        entity.postalcode2 = "3456" + index;
        entity.phon1 = "023" + index;
        entity.phon2 = "4567" + index;
        entity.phon3 = "8901" + index;
        entity.email = "taro@jakusho.net" + index;
        entity.myPortalUrl = "http://jakusho.net/taro" + index;
        entity.isForeign = false;
        entity.lastName = "迂回献金" + index;
        entity.firstName = "太郎" + index;
        entity.middleName = "ミカエル" + index;
        entity.lastNameKana = "うかいけんきん" + index;
        entity.firstNameKana = "たろう" + index;
        entity.middleNameKana = "みかえる" + index;
        entity.gyoushu = "水産業" + index;
        entity.yakushoku = "役職者" + index;
        entity.shokugyouUserWrite = "団体役員" + index;
        entity.kigyouDtNo = "1-234-5678" + index;
        entity.kigyouDtAddress = "三重県架空市湖畔町" + index;
        entity.kigyouDtName = "とこぶし収穫組合" + index;
        entity.lgCode = "098765" + index;
        entity.machiazaId = "1234" + index;
        entity.blkId = "123" + index;
        entity.rsdtId = "234" + index;
        entity.rsdt2Id = "5678" + index;
        entity.isAffected = true;
        entity.judgeReason = "未入力";

        return entity;
}