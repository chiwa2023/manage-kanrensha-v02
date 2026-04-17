import RoutePathConstants from "../../../../routePathConstants";
import { useUserInfoStore } from "../../stores/storeUserInfo";
import { type JwtTokenDtoInterface } from "./jwtTokenDto";
import { AccessTokenNotFoundError, TokenRefreshError } from "./errors";

export default async function getAuthorizedPromiseArea(): Promise<string> {

    // back側アクセス
    const urlBack: string = RoutePathConstants.DOMAIN + RoutePathConstants.BASE_PATH;

    // よく使う定数
    const SERVER_STATUS_OK: number = 200;

    // 保存していたアクセストークンと有効期限を取得
    const userInfo = useUserInfoStore();

    // jwtを取り出し、万が一空文字未入力の場合はAPIアクセスさせない
    const jwtDto: JwtTokenDtoInterface = userInfo.jwtDto;
    const accessToken = jwtDto.accessToken;
    if (!accessToken) {
        throw new AccessTokenNotFoundError();
    }

    // 有効期限から仮値2分余裕があるならアクセス可能としてアクセストークンを使用する
    const dateExpires: Date = new Date(jwtDto.expiresAt);
    dateExpires.setMinutes(dateExpires.getMinutes() - 2);
    if (new Date() < dateExpires) {
        return accessToken;
    }

    // アクセストークンが期限切れ(直前)で利用できない場合は
    // リフレッシュトークンを使ってアクセストークンを再取得
    const url = urlBack + "/refresh-token";
    const method = "POST";
    const body = JSON.stringify(jwtDto);
    const headers = {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
    };

    // とにかくステータス200でなければアクセスさせない
    const response = await fetch(url, { method, headers, body });
    if (SERVER_STATUS_OK === response.status) {
        const jwt: JwtTokenDtoInterface = await response.json();
        userInfo.jwtDto = jwt;
        return jwt.accessToken;
    }

    // エラーの時は接続がダメなのでキャッチ先でメッセージを出して終了
    throw new TokenRefreshError();
}



