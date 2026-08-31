interface UserPersonEntityInterface {
    /** テーブルId */
    userPersonId: number;

    /** ユーザコード */
    userPersonCode: number;

    /** ユーザ名称 */
    userPersonName: string;

    /** 最新該否 */
    isLatest: boolean;

    /** メールアドレス */
    email: string;

}

class UserPersonEntity implements UserPersonEntityInterface {

    /** テーブルId */
    userPersonId: number;

    /** ユーザコード */
    userPersonCode: number;

    /** ユーザ名称 */
    userPersonName: string;

    /** 最新該否 */
    isLatest: boolean;

    /** メールアドレス */
    email: string;

    constructor() {
        // 初期データ
        const INIT_STRING = "";
        const INIT_INREGER = 0;
        const INIT_BOOLEAN = false;

        this.userPersonId = INIT_INREGER;
        this.userPersonCode = INIT_INREGER;
        this.userPersonName = INIT_STRING;
        this.isLatest = INIT_BOOLEAN;
        this.email = INIT_STRING;
    }

}

export { type UserPersonEntityInterface, UserPersonEntity }