interface SearchTimerYoteiCapsuleDtoInterface {
    /** 予定区分リスト */
    listYoteiKbn: number[];

    /** 検索開始日時 */
    startDateTime: Date;

    /** 検索終了日時 */
    endDateTime: Date;
}

class SearchTimerYoteiCapsuleDto implements SearchTimerYoteiCapsuleDtoInterface {
    /** 予定区分リスト */
    listYoteiKbn: number[];

    /** 検索開始日時 */
    startDateTime: Date;

    /** 検索終了日時 */
    endDateTime: Date;

    constructor() {
        this.listYoteiKbn = [];
        this.startDateTime = new Date();
        this.endDateTime = new Date();
        this.startDateTime.setMonth(this.startDateTime.getMonth() - 3);
    }

}

export { type SearchTimerYoteiCapsuleDtoInterface, SearchTimerYoteiCapsuleDto }