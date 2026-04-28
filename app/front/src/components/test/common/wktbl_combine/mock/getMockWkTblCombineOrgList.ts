import type WkTblPartnerCombineOrgInterface from "../../../../entity/wkTblPartnerCombineOrgEntity";
import WkTblPartnerCombineOrgEntity from "../../../../entity/wkTblPartnerCombineOrgEntity";

export default function getMockWkTblCombineOrgList(): WkTblPartnerCombineOrgInterface[] {

    const list: WkTblPartnerCombineOrgInterface[] = [];

    list.push(createEntity(1));
    list.push(createEntity(2));
    list.push(createEntity(3));
    list.push(createEntity(4));

    return list;

}

function createEntity(index: number): WkTblPartnerCombineOrgInterface {

    const entity: WkTblPartnerCombineOrgInterface = new WkTblPartnerCombineOrgEntity();

    entity.wkTblPartnerCombineOrgId = 100 * 4 + index;
    entity.wkTblPartnerCombineOrgCode = 100 * 3 + index;
    entity.isLatest = true;
    entity.isFinish = false;
    entity.kanrenshaKbn = 2;
    entity.personKanrenshaCode = "12-34567-8901-2345-678" + index;
    entity.personName = "迂回献金　太郎";
    entity.orgKanrenshaCode = "1-2345-67-890123-456789" + index;
    entity.orgName = "超元素製造組合";
    entity.isAffected = true;
    entity.judgeReason = "未入力";
    entity.startYear = 2022;
    entity.endYear = 2024;
    entity.yearArrayText = "2022:2023:2024";

    return entity;
}