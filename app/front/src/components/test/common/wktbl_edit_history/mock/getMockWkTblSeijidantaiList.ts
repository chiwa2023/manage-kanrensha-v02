import { WkTblKanrenshaSeijidantaiHistoryEntity, type WkTblKanrenshaSeijidantaiHistoryEntityInterface } from "../../../entity/wkTblKanrenshaSeijidantaiHistoryEntity";

export default function getMockWkTblSeijidantaiList(): WkTblKanrenshaSeijidantaiHistoryEntityInterface[] {
    const list: WkTblKanrenshaSeijidantaiHistoryEntityInterface[] = [];
    list.push(createEntity(1));
    list.push(createEntity(2));
    list.push(createEntity(3));
    list.push(createEntity(4));

    return list;
}

function createEntity(index: number): WkTblKanrenshaSeijidantaiHistoryEntityInterface {

    const entity: WkTblKanrenshaSeijidantaiHistoryEntityInterface = new WkTblKanrenshaSeijidantaiHistoryEntity();
    entity.wkKanrenshaSeijidantaiHistoryId = index;
    entity.isLatest = true;

    entity.kanrenshaName = "いいかげん政治団体" + index;
    entity.allAddress = "和歌山県実在市山麓町" + index;
    entity.seijidantaiDelegate = "代表者　太郎" + index;
    entity.orgDelegateCode = "123-4569" + index;
    entity.isAffected = true;
    entity.judgeReason = "未入力";

    return entity;
}