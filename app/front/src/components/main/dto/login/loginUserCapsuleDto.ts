interface LoginUserCapsuleDtoInterface {

    /** ユーザId  */
    userId: string;

    /** パスワード */
    password: string;

    /** ログイン情報記憶 */
    rememberMe: boolean;
}

class LoginUserCapsuleDto implements LoginUserCapsuleDtoInterface {

    /** ユーザId  */
    userId: string;

    /** パスワード */
    password: string;

    /** ログイン情報記憶 */
    rememberMe: boolean;

    constructor() {
        const INIT_STRING: string = "";
        const INIT_BOOLEAN: boolean = false;

        this.userId = INIT_STRING;
        this.password = INIT_STRING;
        this.rememberMe = INIT_BOOLEAN;
    }
}

export { type LoginUserCapsuleDtoInterface, LoginUserCapsuleDto }
