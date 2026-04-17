interface ResetPassswordCapsuleDtoInterface {

    /** メールアドレス  */
    email: string;

    /** 認証コード  */
    accessCode: string;

    /** パスワード  */
    password: string;

}

class ResetPassswordCapsuleDto implements ResetPassswordCapsuleDtoInterface {
    /** メールアドレス  */
    email: string;

    /** 認証コード  */
    accessCode: string;

    /** パスワード  */
    password: string;

    constructor() {
        const INIT_STRING: string = "";

        this.email = INIT_STRING;
        this.accessCode = INIT_STRING;
        this.password = INIT_STRING;
    }
}


export { type ResetPassswordCapsuleDtoInterface, ResetPassswordCapsuleDto }