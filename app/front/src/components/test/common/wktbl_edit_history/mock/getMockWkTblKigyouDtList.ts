import { WkTblKanrenshaKigyouDtHistoryEntity, type WkTblKanrenshaKigyouDtHistoryEntityInterface } from "../../../entity/wkTblKanrenshaKigyouDtHistoryEntity";

export default function getMockWkTblKigyouDtList(): WkTblKanrenshaKigyouDtHistoryEntityInterface[] {

        const list:WkTblKanrenshaKigyouDtHistoryEntityInterface[] = [];

        list.push(createEntity(1));
        list.push(createEntity(2));
        list.push(createEntity(3));
        list.push(createEntity(4));

        return list;

}

function createEntity(index: number): WkTblKanrenshaKigyouDtHistoryEntityInterface {

        const entity: WkTblKanrenshaKigyouDtHistoryEntityInterface = new WkTblKanrenshaKigyouDtHistoryEntity();
        entity.wkKanrenshaKigyouDtHistoryId = index;
        entity.isLatest = true;

        entity.kanrenshaName = "よくばり企業" + index;
        entity.allAddress = "和歌山県実在市山麓町" + index;
        entity.kigyouDtDelegate = "代表者　太郎" + index;
        entity.kigyouDtKanrenshaCode = "1-2345-4" + index;
        entity.isAffected = true;
        entity.judgeReason = "未入力";

        return entity;
}