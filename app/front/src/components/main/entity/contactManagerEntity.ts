interface ContactManagerEntityInterface {

    /** テーブルId */
    contactManagerId: number;

    /** 運営者問い合わせコード */
    contactManagerCode: number;

    /** 最新該非 */
    isLatest: boolean;

    /** 初回問い合わせ日時 */
    firstTimestamp: Date;

    /** 問い合わせクローズ該非 */
    isClosed: boolean;

    /** クローズ日時 */
    closeTimestamp: Date;

    /** 問い合わせユーザId */
    inquireUserId: number;

    /** 問い合わせユーザコード */
    inquireUserCode: number;

    /** 問い合わせユーザ名称 */
    inquireUserName: string;

    /** 問い合わせタイトル */
    inquireTitle: string;

    /** 問い合わせ内容 */
    inquireContent: string;

    /** 挿入ユーザId */
    insertUserId: number;

    /** 挿入ユーザコード */
    insertUserCode: number;

    /** 挿入ユーザ名称 */
    insertUserName: string;

    /** 挿入日時 */
    insertTimestamp: Date;
}


class ContactManagerEntity implements ContactManagerEntityInterface {

    /** テーブルId */
    contactManagerId: number;

    /** 運営者問い合わせコード */
    contactManagerCode: number;

    /** 最新該非 */
    isLatest: boolean;

    /** 初回問い合わせ日時 */
    firstTimestamp: Date;

    /** 問い合わせクローズ該非 */
    isClosed: boolean;

    /** クローズ日時 */
    closeTimestamp: Date;

    /** 問い合わせユーザId */
    inquireUserId: number;

    /** 問い合わせユーザコード */
    inquireUserCode: number;

    /** 問い合わせユーザ名称 */
    inquireUserName: string;

    /** 問い合わせタイトル */
    inquireTitle: string;

    /** 問い合わせ内容 */
    inquireContent: string;

    /** 挿入ユーザId */
    insertUserId: number;

    /** 挿入ユーザコード */
    insertUserCode: number;

    /** 挿入ユーザ名称 */
    insertUserName: string;

    /** 挿入日時 */
    insertTimestamp: Date;

    constructor() {
        const INIT_NUMBER: number = 0;
        const INIT_STRING: string = "";
        const INIT_BOOLEAN: boolean = false;
        const INIT_TIMESTAMP: Date = new Date(1948, 7, 28, 23, 59, 59);

        this.contactManagerId = INIT_NUMBER;
        this.contactManagerCode = INIT_NUMBER;
        this.isLatest = INIT_BOOLEAN;
        this.firstTimestamp = INIT_TIMESTAMP;
        this.isClosed = INIT_BOOLEAN;
        this.closeTimestamp = INIT_TIMESTAMP;
        this.inquireUserId = INIT_NUMBER;
        this.inquireUserCode = INIT_NUMBER;
        this.inquireUserName = INIT_STRING;
        this.inquireTitle = INIT_STRING;
        this.inquireContent = INIT_STRING;
        this.insertUserId = INIT_NUMBER;
        this.insertUserCode = INIT_NUMBER;
        this.insertUserName = INIT_STRING;
        this.insertTimestamp = INIT_TIMESTAMP;
    }

}

export { type ContactManagerEntityInterface, ContactManagerEntity }
