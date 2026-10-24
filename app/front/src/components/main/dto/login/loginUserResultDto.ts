import { LeastUserDto } from 'seijishikin-jp-normalize_common-tool';
import { JwtTokenDto, type JwtTokenDtoInterface } from "./jwtTokenDto";

interface LoginUserResultDtoInterface {

    /** トークンDto  */
    jwtTokenDto: JwtTokenDtoInterface;

    /** ユーザ最低限Dto */
    userDto: LeastUserDto;

    /** パスキー2段階認証要求フラグ */
    requiresPasskey?: boolean;

    /** Pre-Auth Token (2段階目認証用一時トークン) */
    preAuthToken?: string;

    /** WebAuthn Challenge (Base64URL) */
    challenge?: string;

    /** 許可クレデンシャルIDリスト */
    allowCredentials?: string[];

}

class LoginUserResultDto implements LoginUserResultDtoInterface {

    /** トークンDto  */
    jwtTokenDto: JwtTokenDtoInterface;

    /** ユーザ最低限Dto */
    userDto: LeastUserDto;

    /** パスキー2段階認証要求フラグ */
    requiresPasskey: boolean;

    /** Pre-Auth Token (2段階目認証用一時トークン) */
    preAuthToken: string;

    /** WebAuthn Challenge (Base64URL) */
    challenge: string;

    /** 許可クレデンシャルIDリスト */
    allowCredentials: string[];

    constructor() {
        this.jwtTokenDto = new JwtTokenDto();
        this.userDto = new LeastUserDto();
        this.requiresPasskey = false;
        this.preAuthToken = "";
        this.challenge = "";
        this.allowCredentials = [];
    }

}

export { type LoginUserResultDtoInterface, LoginUserResultDto }