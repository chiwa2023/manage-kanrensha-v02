
interface TimerYoteiEntityInterface {


    /** テーブルId */
    timerYoteiId: number;

    /** 予約実行コード */
    timerYoteiCode: number;

    /** 予約実行名称 */
    timerYoteiName: string;

    /** 最新該否 */
    isLatest: boolean;

    /** 予約作業区分 */
    yoyakuTaskKbn: number;

    /** 次回実行日時 */
    nextTimestamp: Date | null;

    /** 前回実行日時 */
    previousTimestamp: Date;

    /** 繰り返し有無 */
    isRepeat: boolean;

    /** 繰り返し中断 */
    isPause: boolean;

    /** タスク条件終了日時 */
    endTimestamp: Date | null;

    /** タスク条件差分日時 */
    sabunTimestamp: Date | null;

    /** 間隔指定該否 */
    isPeriod: boolean;

    /** 間隔指定年条件 */
    yearPeriod: number;

    /** 間隔指定月条件 */
    monthPeriod: number;

    /** 間隔指定日条件 */
    dayPeriod: number;

    /** 間隔指定時間条件 */
    hourPeriod: number;

    /** 直接指定年条件 */
    yearPointed: number;

    /** 直接指定月条件 */
    monthPointed: number;

    /** 直接指定日条件 */
    dayPointed: number;

    /** 直接指定時間条件 */
    hourPointed: number;
}

class TimerYoteiEntity implements TimerYoteiEntityInterface {

    /** テーブルId */
    timerYoteiId: number;

    /** 予約実行コード */
    timerYoteiCode: number;

    /** 予約実行名称 */
    timerYoteiName: string;

    /** 最新該否 */
    isLatest: boolean;

    /** 予約作業区分 */
    yoyakuTaskKbn: number;

    /** 次回実行日時 */
    nextTimestamp: Date;

    /** 前回実行日時 */
    previousTimestamp: Date;

    /** 繰り返し有無 */
    isRepeat: boolean;

    /** 繰り返し中断 */
    isPause: boolean;

    /** タスク条件終了日時 */
    endTimestamp: Date;

    /** タスク条件差分日時 */
    sabunTimestamp: Date;

    /** 間隔指定該否 */
    isPeriod: boolean;

    /** 間隔指定年条件 */
    yearPeriod: number;

    /** 間隔指定月条件 */
    monthPeriod: number;

    /** 間隔指定日条件 */
    dayPeriod: number;

    /** 間隔指定時間条件 */
    hourPeriod: number;

    /** 直接指定年条件 */
    yearPointed: number;

    /** 直接指定月条件 */
    monthPointed: number;

    /** 直接指定日条件 */
    dayPointed: number;

    /** 直接指定時間条件 */
    hourPointed: number;

    constructor() {

        const INIT_NUMBER: number = 0;
        const INIT_STRING: string = "";
        const INIT_BOOLEAN: boolean = false;
        const INIT_TIMESTAMP: Date = new Date();

        this.timerYoteiId = INIT_NUMBER;
        this.timerYoteiCode = INIT_NUMBER;
        this.timerYoteiName = INIT_STRING;
        this.isLatest = INIT_BOOLEAN;
        this.yoyakuTaskKbn = INIT_NUMBER;
        this.nextTimestamp = INIT_TIMESTAMP;
        this.previousTimestamp = INIT_TIMESTAMP;
        this.isRepeat = INIT_BOOLEAN;
        this.isPause = INIT_BOOLEAN;
        this.endTimestamp = INIT_TIMESTAMP;
        this.sabunTimestamp = INIT_TIMESTAMP;
        this.isPeriod = INIT_BOOLEAN;
        this.yearPeriod = INIT_NUMBER;
        this.monthPeriod = INIT_NUMBER;
        this.dayPeriod = INIT_NUMBER;
        this.hourPeriod = INIT_NUMBER;
        this.yearPointed = INIT_NUMBER;
        this.monthPointed = INIT_NUMBER;
        this.dayPointed = INIT_NUMBER;
        this.hourPointed = INIT_NUMBER;
    }

}

export { type TimerYoteiEntityInterface, TimerYoteiEntity }