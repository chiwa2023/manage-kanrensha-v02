package net.seijishikin.jp.normalize.manage.kanrensha.service.security;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import net.seijishikin.jp.normalize.manage.kanrensha.config.RateLimitConfig;

/**
 * RateLimitServiceの単体テスト
 */
class RateLimitServiceTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    private RateLimitService rateLimitService;

    /** RateLimitConfig */
    private RateLimitConfig config;

    @BeforeEach
    void setUp() {
        rateLimitService = new RateLimitService();
        config = new RateLimitConfig();
        config.setEnabled(true);
        config.setCapacity(3);
        config.setRefillTokens(3);
        config.setRefillDurationSeconds(60);

        ReflectionTestUtils.setField(rateLimitService, "rateLimitConfig", config);
        rateLimitService.clearCache();
    }

    @Test
    void testRateLimitWithinCapacity() {
        String key = "client-1";
        assertTrue(rateLimitService.tryConsume(key));
        assertTrue(rateLimitService.tryConsume(key));
        assertTrue(rateLimitService.tryConsume(key));
        // 4回目は容量3を超過するためfalse
        assertFalse(rateLimitService.tryConsume(key));
    }

    @Test
    void testDifferentKeysHaveIndependentLimits() {

        // key1を使い切る
        String key1 = "client-a";
        assertTrue(rateLimitService.tryConsume(key1));
        assertTrue(rateLimitService.tryConsume(key1));
        assertTrue(rateLimitService.tryConsume(key1));
        assertFalse(rateLimitService.tryConsume(key1));

        // key2はまだ消費されていないため許可される
        String key2 = "client-b";
        assertTrue(rateLimitService.tryConsume(key2));
        assertTrue(rateLimitService.tryConsume(key2));
        assertTrue(rateLimitService.tryConsume(key2));
        assertFalse(rateLimitService.tryConsume(key2));
    }

    @Test
    void testRateLimitDisabled() {
        config.setEnabled(false);
        String key = "client-disabled";

        for (int i = 0; i < 10; i++) {
            assertTrue(rateLimitService.tryConsume(key));
        }
    }

    @Test
    void testNullOrBlankKey() {
        assertTrue(rateLimitService.tryConsume(null));
        assertTrue(rateLimitService.tryConsume(""));
        assertTrue(rateLimitService.tryConsume("   "));
    }
}
