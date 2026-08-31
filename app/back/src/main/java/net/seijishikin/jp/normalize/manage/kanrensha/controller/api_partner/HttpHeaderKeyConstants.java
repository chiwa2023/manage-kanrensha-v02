package net.seijishikin.jp.normalize.manage.kanrensha.controller.api_partner;

/**
 * Controllerヘッダ取得キー定数
 */
public final class HttpHeaderKeyConstants { // NOPMD DataClass

    /** リクエストヘッダキー(user agent) */
    public static final String HEADER_KEY_AGENT = "User-Agent";

    /** リクエストヘッダキー(authorization) */
    public static final String HEADER_KEY_AUTH = "X-AUTH-TOKEN";

    /** リクエストヘッダキー(Bearer) */
    public static final String HEADER_KEY_BEARE = "Bearer ";

    /** リクエストヘッダキー(IPアドレス) */
    public static final String HEADER_KEY_IP_ADDRESS = "X-Forwarded-For";

}
