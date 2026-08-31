export default class LoginConstants {

    /** ドメインセッションキー */
    static readonly SESSION_KEY_DOMAIN: string = "normalize.jp.seijishikin.kanrensha-";

    /** ユーザセッションキー */
    static readonly SESSION_KEY_USER: string = this.SESSION_KEY_DOMAIN + "userDto";

        /** トークンセッションキー */
    static readonly SESSION_KEY_TOKEN: string = this.SESSION_KEY_DOMAIN + "jwtToken";

}
