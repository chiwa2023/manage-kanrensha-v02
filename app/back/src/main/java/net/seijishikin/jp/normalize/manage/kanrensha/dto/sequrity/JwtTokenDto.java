package net.seijishikin.jp.normalize.manage.kanrensha.dto.sequrity;

import java.io.Serializable;
import java.util.Date;

/**
 * JwtトークンDto
 */
public class JwtTokenDto implements Serializable {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** アクセストークン */
    private final String accessToken;

    /** リフレッシュトークン */
    private final String refreshToken;

    /** 有効期限 */
    private final Date expiresAt;

    /**
     * コンストラクタ
     *
     * @param accessToken  アクセストークン
     * @param refreshToken リフレッシュトークン
     * @param expiresAt    有効期限
     */
    public JwtTokenDto(final String accessToken, final String refreshToken, final Date expiresAt) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.expiresAt = expiresAt;
    }

    /**
     * アクセストークン
     *
     * @return アクセストークン
     */
    public String getAccessToken() {
        return accessToken;
    }

    /**
     * リフレッシュトークン
     *
     * @return リフレッシュトークン
     */
    public String getRefreshToken() {
        return refreshToken;
    }

    /**
     * 有効期限
     *
     * @return 有効期限
     */
    public Date getExpiresAt() {
        return expiresAt;
    }

}
