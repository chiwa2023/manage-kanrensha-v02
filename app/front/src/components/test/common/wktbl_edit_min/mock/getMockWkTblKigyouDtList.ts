import { WkTblKanrenshaKigyouDtAddMinEntity, type WkTblKanrenshaKigyouDtAddMinEntityInterface } from "../../../entity/wkTblKanrenshaKigyouDtAddMinEntity";

export default function getMockWkTblCorpList(): WkTblKanrenshaKigyouDtAddMinEntityInterface[] {
        const list: WkTblKanrenshaKigyouDtAddMinEntityInterface[] = [];

        list.push(createEntity(1));
        list.push(createEntity(2));
        list.push(createEntity(3));
        list.push(createEntity(4));

        return list;

}

function createEntity(index: number): WkTblKanrenshaKigyouDtAddMinEntityInterface {

        const entity: WkTblKanrenshaKigyouDtAddMinEntityInterface = new WkTblKanrenshaKigyouDtAddMinEntity();

        entity.wkTblKanrenshaKigyouDtAddMinId = index;
        entity.isLatest = true;
        entity.kanrenshaName = "よくばり企業" + index;
        entity.allAddress = "和歌山県実在市山麓町" + index;
        entity.kigyouDtDelegate = "代表者　太郎" + index;
        entity.houjinNo = "1-2345" + index;
        entity.isAffected = true;
        entity.judgeReason = "未入力";

        return entity;
}