package net.seijishikin.jp.normalize.manage.kanrensha.service.passkey;

import java.security.SecureRandom;
import java.time.Instant;
import java.util.Base64;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;

/**
 * パスキー2FA用 Pre-Auth Token (一時認証トークン) 管理Service
 */
@Service
public class PreAuthTokenService {

    /** Pre-Auth Tokenの有効期限（秒）: 5分 */
    public static final long PRE_AUTH_TTL_SECONDS = 300L;

    /** セキュア乱数生成器 */
    private final SecureRandom secureRandom = new SecureRandom();

    /** Pre-Authセッションキャッシュ (Key: preAuthToken) */
    private final Map<String, PreAuthSession> tokenCache = new ConcurrentHashMap<>();

    /**
     * Pre-Auth Tokenを発行しセッションを保持する
     *
     * @param userPersonCode ユーザ人物コード
     * @param username       ユーザ名（メールアドレス）
     * @param authentication 1段階目で成功した認証情報
     * @param leastUserDto   ユーザ情報
     * @return 発行されたPre-Auth Token
     */
    public String createPreAuthToken(final Integer userPersonCode, final String username,
            final Authentication authentication, final LeastUserDto leastUserDto) {
        cleanExpiredEntries();

        byte[] randomBytes = new byte[32]; // SUPPRESS CHECKSTYLE MagicNumber
        secureRandom.nextBytes(randomBytes);
        String token = Base64.getUrlEncoder().withoutPadding().encodeToString(randomBytes);

        PreAuthSession session = new PreAuthSession(token, userPersonCode, username, authentication, leastUserDto,
                Instant.now().plusSeconds(PRE_AUTH_TTL_SECONDS));
        tokenCache.put(token, session);
        return token;
    }

    /**
     * Pre-Authセッションを取得する
     *
     * @param token Pre-Auth Token
     * @return PreAuthSession（無効または期限切れ時はnull）
     */
    public PreAuthSession getPreAuthSession(final String token) {
        if (token == null || token.isBlank()) {
            return null;
        }
        PreAuthSession session = tokenCache.get(token);
        if (session == null || session.isExpired()) {
            tokenCache.remove(token);
            return null;
        }
        return session;
    }

    /**
     * Pre-Authセッションを消費（削除）する
     *
     * @param token Pre-Auth Token
     */
    public void removePreAuthSession(final String token) {
        if (token != null) {
            tokenCache.remove(token);
        }
    }

    /**
     * 期限切れエントリを削除する
     */
    private void cleanExpiredEntries() {
        tokenCache.entrySet().removeIf(entry -> entry.getValue().isExpired());
    }

    /**
     * Pre-Authセッションデータクラス
     * 
     * @param preAuthToken   認証前トークン
     * @param userPersonCode ユーザコード
     * @param username       ユーザ名
     * @param authentication authentication
     * @param leastUserDto   ユーザ最小限
     * @param expiresAt      有効期限
     */
    public record PreAuthSession(String preAuthToken, Integer userPersonCode, String username,
            Authentication authentication, LeastUserDto leastUserDto, Instant expiresAt) {

        /**
         * 現在時と有効期間を比較する
         * 
         * @return 比較結果(現在時が後であればtrue)
         */
        public boolean isExpired() {
            return Instant.now().isAfter(expiresAt);
        }
    }

}
