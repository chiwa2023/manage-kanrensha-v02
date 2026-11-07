package net.seijishikin.jp.normalize.manage.kanrensha.service.passkey;

import java.time.Instant;
import java.util.Base64;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import com.webauthn4j.data.client.challenge.Challenge;
import com.webauthn4j.data.client.challenge.DefaultChallenge;

/**
 * WebAuthn Challenge発行・検証・キャッシュ管理Service
 */
@Service
public class WebAuthnChallengeService {

    /** チャレンジの有効期限（秒）: 5分 */
    public static final long CHALLENGE_TTL_SECONDS = 300L;

    /** 登録用チャレンジキャッシュ (Key: userPersonCode) */
    private final Map<Integer, ExpiringChallenge> registrationCache = new ConcurrentHashMap<>();

    /** 認証用チャレンジキャッシュ (Key: preAuthToken) */
    private final Map<String, ExpiringChallenge> authenticationCache = new ConcurrentHashMap<>();

    /**
     * 登録用Challengeを生成して保存する
     *
     * @param userPersonCode ユーザ人物コード
     * @return Base64URL形式のChallenge文字列
     */
    public String generateRegistrationChallenge(final Integer userPersonCode) {
        cleanExpiredEntries();
        Challenge challenge = new DefaultChallenge();
        String challengeBase64Url = Base64.getUrlEncoder().withoutPadding().encodeToString(challenge.getValue());
        registrationCache.put(userPersonCode,
                new ExpiringChallenge(challengeBase64Url, Instant.now().plusSeconds(CHALLENGE_TTL_SECONDS)));
        return challengeBase64Url;
    }

    /**
     * 登録用Challengeを取得する
     *
     * @param userPersonCode ユーザ人物コード
     * @return Base64URL形式のChallenge文字列（期限切れまたは未発行時はnull）
     */
    public String getRegistrationChallenge(final Integer userPersonCode) {
        ExpiringChallenge entry = registrationCache.get(userPersonCode);
        if (entry == null || entry.isExpired()) {
            registrationCache.remove(userPersonCode);
            return null;
        }
        return entry.challengeBase64Url();
    }

    /**
     * 登録用Challengeを消費（削除）する
     *
     * @param userPersonCode ユーザ人物コード
     */
    public void removeRegistrationChallenge(final Integer userPersonCode) {
        registrationCache.remove(userPersonCode);
    }

    /**
     * 認証用Challengeを生成して保存する
     *
     * @param sessionKey preAuthToken等のセッションキー
     * @return Base64URL形式のChallenge文字列
     */
    public String generateAuthenticationChallenge(final String sessionKey) {
        cleanExpiredEntries();
        Challenge challenge = new DefaultChallenge();
        String challengeBase64Url = Base64.getUrlEncoder().withoutPadding().encodeToString(challenge.getValue());
        authenticationCache.put(sessionKey,
                new ExpiringChallenge(challengeBase64Url, Instant.now().plusSeconds(CHALLENGE_TTL_SECONDS)));
        return challengeBase64Url;
    }

    /**
     * 認証用Challengeを取得する
     *
     * @param sessionKey preAuthToken等のセッションキー
     * @return Base64URL形式のChallenge文字列（期限切れまたは未発行時はnull）
     */
    public String getAuthenticationChallenge(final String sessionKey) {
        ExpiringChallenge entry = authenticationCache.get(sessionKey);
        if (entry == null || entry.isExpired()) {
            authenticationCache.remove(sessionKey);
            return null;
        }
        return entry.challengeBase64Url();
    }

    /**
     * 認証用Challengeを消費（削除）する
     *
     * @param sessionKey preAuthToken等のセッションキー
     */
    public void removeAuthenticationChallenge(final String sessionKey) {
        authenticationCache.remove(sessionKey);
    }

    /**
     * 期限切れエントリを削除する
     */
    private void cleanExpiredEntries() {
        registrationCache.entrySet().removeIf(entry -> entry.getValue().isExpired());
        authenticationCache.entrySet().removeIf(entry -> entry.getValue().isExpired());
    }

    /**
     * キャッシュエントリ
     * 
     * @param challengeBase64Url 確認URL(Base64)
     * @param expiresAt          有効期限
     */
    private record ExpiringChallenge(String challengeBase64Url, Instant expiresAt) {

        /**
         * 現在時と有効期限を比較する
         * 
         * @return 比較結果(現在時が後の場合はtrue)
         */
        boolean isExpired() { // NOPMD DefaultAccessModifier
            return Instant.now().isAfter(expiresAt);
        }
    }

}
