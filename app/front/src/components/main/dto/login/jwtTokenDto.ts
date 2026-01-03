interface JwtTokenDtoInterface {

    /** アクセストークン  */
    accessToken: string;

    /** リフレッシュトークン */
    refleshToken: string;

    /** 有効期限 */
    expiresAt: Date;

}

/** Jwt格納Dto */
class JwtTokenDto implements JwtTokenDtoInterface {

    /** アクセストークン  */
    accessToken: string;

    /** リフレッシュトークン */
    refleshToken: string;

    /** 有効期限 */
    expiresAt: Date;

    constructor() {
        const INIT_STRING: string = "";

        this.accessToken = INIT_STRING;
        this.refleshToken = INIT_STRING;
        this.expiresAt = new Date(1948, 7, 29);
    }
}

export { type JwtTokenDtoInterface, JwtTokenDto }