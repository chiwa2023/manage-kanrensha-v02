import { UserPersonEntity, type UserPersonEntityInterface } from "../../../../main/entity/userPersonEntity";

export default function mockGetUserList(): UserPersonEntityInterface[] {

    const list: UserPersonEntityInterface[] = [];

    list.push(createEntity(1));
    list.push(createEntity(2));
    list.push(createEntity(3));
    list.push(createEntity(4));

    return list;
}

function createEntity(index: number): UserPersonEntityInterface {

    const entity: UserPersonEntityInterface = new UserPersonEntity();

    entity.userPersonId = 200 + index;
    entity.userPersonCode = 180 + index;
    entity.userPersonName = "ユーザ　花子" + index;

    return entity;
}