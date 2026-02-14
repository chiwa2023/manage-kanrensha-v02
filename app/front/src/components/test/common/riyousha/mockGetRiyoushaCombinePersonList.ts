import { RiyoushaCombineOrgEntity, type RiyoushaCombineOrgEntityInterface } from "../../../main/entity/riyoushaCombineOrgEntity";

export default function mockGetRiyoushaCOmbinePersonList(): RiyoushaCombineOrgEntityInterface[] {

    const list: RiyoushaCombineOrgEntityInterface[] = [];

    list.push(createEntity(1));
    list.push(createEntity(2));
    list.push(createEntity(3));
    list.push(createEntity(4));

    return list;
}

function createEntity(index: number): RiyoushaCombineOrgEntityInterface {

    const entity: RiyoushaCombineOrgEntityInterface = new RiyoushaCombineOrgEntity();
    entity.riyoushaCombineOrgId = 300 + index;
    entity.riyoushaCombineOrgCode = 200 + index;
    entity.personRiyoushaCode = 1000 + index;
    entity.personName = "利用者　花子" + index;
    entity.riyoushaKbn = (index % 3) + 1;

    return entity;
}