import { WkTblKanrenshaSeijidantaiAddMinEntity, type WkTblKanrenshaSeijidantaiAddMinEntityInterface } from "../../../entity/wkTblKanrenshaSeijidantaiAddMinEntity";

export default function getMockWkTblPoliOrgList(): WkTblKanrenshaSeijidantaiAddMinEntityInterface[] {
    const list: WkTblKanrenshaSeijidantaiAddMinEntityInterface[] = [];
    list.push(createEntity(1));
    list.push(createEntity(2));
    list.push(createEntity(3));
    list.push(createEntity(4));

    return list;
}

function createEntity(index: number): WkTblKanrenshaSeijidantaiAddMinEntityInterface {

    const entity: WkTblKanrenshaSeijidantaiAddMinEntityInterface = new WkTblKanrenshaSeijidantaiAddMinEntity();
    entity.wkTblKanrenshaSeijidantaiAddMinId = index;
    entity.isLatest = true;
    entity.kanrenshaName = "いいかげん政治団体" + index;
    entity.allAddress = "和歌山県実在市山麓町" + index;
    entity.seijidantaiDelegate = "代表者　太郎" + index;
    entity.dantaiKbn = "01";
    entity.isAffected = true;
    entity.judgeReason = "未入力";

    return entity;
}