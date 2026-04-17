import { FrameworkMessageAndResultDto, type FrameworkMessageAndResultDtoInterface } from "seijishikin-jp-normalize_common-tool";

interface NewComerDtoInterface extends FrameworkMessageAndResultDtoInterface {

    /** メールアドレス */
    mailAddress: string;

    /** ニックネーム */
    nickName: string;

    /** パスワード */
    password: string;

    /** 登録用コード */
    registCode: string;

    /** 有効期限 */
    limitDateTime: Date;

    /** ユーザ区分(ロール・権限) */
    role: string;

    /** URLダイレクトアクセス用認証トークン */
    verifyToken:string;
}

class NewComerDto extends FrameworkMessageAndResultDto
    implements NewComerDtoInterface {

    /** メールアドレス */
    mailAddress: string;

    /** ニックネーム */
    nickName: string;

    /** パスワード */
    password: string;

    /** 登録用コード */
    registCode: string;

    /** 有効期限 */
    limitDateTime: Date;

    /** ユーザ区分(ロール・権限) */
    role: string;

    /** URLダイレクトアクセス用認証トークン */
    verifyToken:string;

    constructor() {
        super();
        
        const INIT_STRING: string = "";
        this.mailAddress = INIT_STRING;
        this.nickName = INIT_STRING;
        this.password = INIT_STRING;
        this.registCode = INIT_STRING;
        this.limitDateTime = new Date(1948, 7, 28);
        this.role = INIT_STRING;
        this.verifyToken = INIT_STRING;
    }
}

export { type NewComerDtoInterface, NewComerDto }