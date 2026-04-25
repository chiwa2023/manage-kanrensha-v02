import { WkTblKanrenshaPersonAddMinEntity, type WkTblKanrenshaPersonAddMinEntityInterface } from "../../../entity/wkTblKanrenshaPersonAddMinEntity";

export default function getMockWkTblPersonList(): WkTblKanrenshaPersonAddMinEntityInterface[] {

        const list: WkTblKanrenshaPersonAddMinEntityInterface[] = [];
        list.push(createEntity(1));
        list.push(createEntity(2));
        list.push(createEntity(3));
        list.push(createEntity(4));

        return list;
}


function createEntity(index: number): WkTblKanrenshaPersonAddMinEntityInterface {

        const entity: WkTblKanrenshaPersonAddMinEntityInterface = new WkTblKanrenshaPersonAddMinEntity();

        entity.wkTblKanrenshaPersonAddMinId = index;
        entity.isLatest = true;
        entity.kanrenshaName = "迂回献金　太郎" + index;
        entity.allAddress = "和歌山県実在市山麓町" + index;
        entity.personShokugyou = "弁護士" + index;
        entity.isAffected = true;
        entity.judgeReason = "未入力";

        return entity;
}