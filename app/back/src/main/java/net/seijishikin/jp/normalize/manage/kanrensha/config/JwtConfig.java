package net.seijishikin.jp.normalize.manage.kanrensha.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * JWT有効期限設定
 */
@Component
public class JwtConfig { // NOPMD DataClass

    /** トークンの有効期限（ms） */
    @Value("${jwt.expiration.access}")
    private long expiration;

    /** リフレッシュトークンの有効期限（ms） */
    @Value("${jwt.expiration.refresh}")
    private long refreshExpiration;

    /**
     * トークンの有効期限を取得する
     *
     * @return トークンの有効期限
     */
    public long getExpiration() {
        return expiration;
    }

    /**
     * トークンの有効期限を設定する
     *
     * @param expiration トークンの有効期限
     */
    public void setExpiration(final long expiration) {
        this.expiration = expiration;
    }

    /**
     * リフレッシュトークンの有効期限を取得する
     *
     * @return リフレッシュトークンの有効期限
     */
    public long getRefreshExpiration() {
        return refreshExpiration;
    }

    /**
     * リフレッシュトークンの有効期限を設定する
     *
     * @param refreshExpiration リフレッシュトークンの有効期限
     */
    public void setRefreshExpiration(final long refreshExpiration) {
        this.refreshExpiration = refreshExpiration;
    }

}