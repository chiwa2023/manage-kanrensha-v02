package net.seijishikin.jp.normalize.manage.kanrensha.service.passkey;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * WebAuthnChallengeService単体テスト
 */
class WebAuthnChallengeServiceTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    private WebAuthnChallengeService challengeService;

    @BeforeEach
    void setUp() {
        challengeService = new WebAuthnChallengeService();
    }

    @Test
    void testRegistrationChallengeLifecycle() {
        final Integer userPersonCode = 12345;

        // Challenge生成
        String challenge = challengeService.generateRegistrationChallenge(userPersonCode);
        assertNotNull(challenge);
        assertFalse(challenge.isBlank());

        // 取得
        String retrieved = challengeService.getRegistrationChallenge(userPersonCode);
        assertEquals(challenge, retrieved);

        // 消費・削除
        challengeService.removeRegistrationChallenge(userPersonCode);
        assertNull(challengeService.getRegistrationChallenge(userPersonCode));
    }

    @Test
    void testAuthenticationChallengeLifecycle() {
        String sessionKey = "pre-auth-token-xyz";

        // Challenge生成
        String challenge = challengeService.generateAuthenticationChallenge(sessionKey);
        assertNotNull(challenge);
        assertFalse(challenge.isBlank());

        // 取得
        String retrieved = challengeService.getAuthenticationChallenge(sessionKey);
        assertEquals(challenge, retrieved);

        // 消費・削除
        challengeService.removeAuthenticationChallenge(sessionKey);
        assertNull(challengeService.getAuthenticationChallenge(sessionKey));
    }

    @Test
    void testNonExistentChallenge() {
        assertNull(challengeService.getRegistrationChallenge(99999));
        assertNull(challengeService.getAuthenticationChallenge("unknown-token"));
    }
}
