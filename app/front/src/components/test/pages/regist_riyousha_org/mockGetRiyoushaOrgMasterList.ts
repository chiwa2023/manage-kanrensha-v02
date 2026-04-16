import { RiyoushaOrgMasterEntity, type RiyoushaOrgMasterEntityInterface } from "../../../main/entity/riyoushaOrgMasterEntity";

export default function mockGetRiyoushaOrgMasterList(): RiyoushaOrgMasterEntityInterface[] {

    const list: RiyoushaOrgMasterEntityInterface[] = [];

    list.push(createEntity(1));
    list.push(createEntity(2));
    list.push(createEntity(3));
    list.push(createEntity(4));

    return list;
}

function createEntity(index: number): RiyoushaOrgMasterEntityInterface {

    const entity: RiyoushaOrgMasterEntityInterface = new RiyoushaOrgMasterEntity();

    entity.riyoushaOrgMasterId = 300 + index;
    entity.riyoushaOrgMasterCode = 200 + index;

    entity.allName = "利用者組織" + index;
    entity.allNameKana = "りようしゃそしき" + index;
    entity.addressAll = "宮崎県実在市湖畔町";

    return entity;
}