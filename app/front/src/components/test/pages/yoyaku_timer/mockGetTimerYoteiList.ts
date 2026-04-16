import { TimerYoteiEntity, type TimerYoteiEntityInterface } from "../../../main/entity/timerYoteiEntity";

export default function mockGetTimerYoteiList(): TimerYoteiEntityInterface[] {

    const list: TimerYoteiEntityInterface[] = [];
    list.push(createEntity(1));
    list.push(createEntity(2));
    list.push(createEntity(3));
    list.push(createEntity(4));
    list.push(createEntity(5));
    list.push(createEntity(6));
    list.push(createEntity(7));

    return list;
}

function createEntity(index: number): TimerYoteiEntityInterface {
    const entity: TimerYoteiEntityInterface = new TimerYoteiEntity();
    entity.timerYoteiId = 100 + index;
    entity.nextTimestamp = new Date(2025, 4, index, 2, 0, 0);
    entity.previousTimestamp = new Date(2024, 10, index, 2, 0, 0);
   entity.endTimestamp = new Date(2025, 11, index, 2, 0, 0);
    entity.sabunTimestamp = new Date(2024, 8, index, 2, 0, 0);

    entity.yoyakuTaskKbn = 101;
    entity.timerYoteiName = "ダンプ標準";

    entity.isPause = false;
    entity.isRepeat = index % 2 == 0;
    entity.isPeriod = ((index + 1) % 2) == 0;

    entity.yearPeriod = 0;
    entity.monthPeriod = 6;
    entity.dayPeriod = 0;
    entity.hourPeriod = 0;

    entity.yearPointed = 2026;
    entity.monthPointed = 9;
    entity.dayPointed = 13;
    entity.hourPointed = 23;

    return entity;
}