import { WkTblKanrenshaPersonHistoryEntity, type WkTblKanrenshaPersonHistoryEntityInterface } from "../../../entity/wkTblKanrenshaPersonHistoryEntity";

export default function getMockWkTblPersonList(): WkTblKanrenshaPersonHistoryEntityInterface[] {

        const list: WkTblKanrenshaPersonHistoryEntityInterface[] = [];
        list.push(createEntity(1));
        list.push(createEntity(2));
        list.push(createEntity(3));
        list.push(createEntity(4));

        return list;
}


function createEntity(index: number): WkTblKanrenshaPersonHistoryEntityInterface {

        const entity: WkTblKanrenshaPersonHistoryEntityInterface = new WkTblKanrenshaPersonHistoryEntity();
        entity.wkKanrenshaPersonHistoryId = index;
        entity.isLatest = true;

        entity.kanrenshaName = "迂回献金　太郎" + index;
        entity.allAddress = "和歌山県実在市山麓町" + index;
        entity.personShokugyou = "弁護士" + index;
        entity.personKanrenshaCode = "12-3456" + index;
        entity.isAffected = true;
        entity.judgeReason = "未入力";

        return entity;
}