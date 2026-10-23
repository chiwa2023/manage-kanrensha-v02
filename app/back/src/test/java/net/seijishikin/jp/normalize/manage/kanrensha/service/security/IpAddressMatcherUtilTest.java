package net.seijishikin.jp.normalize.manage.kanrensha.service.security;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * IpAddressMatcherUtilの単体テスト
 */
class IpAddressMatcherUtilTest {

    /** テスト対象 */
    private IpAddressMatcherUtil util;

    @BeforeEach
    void setUp() {
        util = new IpAddressMatcherUtil();
    }

    /** IPアドレス(100) */
    private static final String IP_100 = "192.168.1.100"; // NOPMD
    /** IPアドレス(101) */
    private static final String IP_101 = "192.168.1.101"; // NOPMD

    /** IPアドレス(101) */
    private static final String IP_0_24 = "192.168.1.0/24";

    /** IPアドレス(localnost) */
    private static final String IP_127_1 = "127.0.0.1"; // NOPMD

    @Test
    void testExactMatch() {
        assertTrue(util.matches(IP_100, IP_100));
        assertFalse(util.matches(IP_100, IP_101));
    }

    @Test
    void testCidrMatch() {
        // 192.168.1.0/24 の範囲内
        assertTrue(util.matches(IP_0_24, "192.168.1.1")); // NOPMD
        assertTrue(util.matches(IP_0_24, "192.168.1.254")); // NOPMD
        // 範囲外
        assertFalse(util.matches(IP_0_24, "192.168.2.1")); // NOPMD
    }

    @Test
    void testMultipleExpressions() {
        String allowed = "10.0.0.1, 192.168.1.0/24, 172.16.0.0/16";
        // 1つ目にマッチ
        assertTrue(util.matches(allowed, "10.0.0.1")); // NOPMD
        // 2つ目にマッチ
        assertTrue(util.matches(allowed, "192.168.1.50")); // NOPMD
        // 3つ目にマッチ
        assertTrue(util.matches(allowed, "172.16.10.20")); // NOPMD
        // いずれにも不一致
        assertFalse(util.matches(allowed, "10.0.0.2")); // NOPMD
        assertFalse(util.matches(allowed, "192.168.0.1")); // NOPMD
    }

    @Test
    void testLoopbackIpv4AndIpv6() {
        assertTrue(util.matches(IP_127_1, IP_127_1));
        assertTrue(util.matches("::1", "::1")); // NOPMD
        assertTrue(util.matches("::1/128", "::1")); // NOPMD
    }

    @Test
    void testNullAndEmpty() {
        assertFalse(util.matches(null, IP_127_1));
        assertFalse(util.matches("", IP_127_1));
        assertFalse(util.matches("   ", IP_127_1));
        assertFalse(util.matches(IP_127_1, null));
        assertFalse(util.matches(IP_127_1, ""));
        assertFalse(util.matches(IP_127_1, "   "));
    }

    @Test
    void testInvalidExpressionGracefullyHandled() {
        // 不正なフォーマットが含まれていても、他の有効な設定で判定できる
        String allowed = "invalid-ip, 192.168.1.1";
        assertTrue(util.matches(allowed, "192.168.1.1")); // NOPMD
        assertFalse(util.matches("invalid-ip", "192.168.1.1")); // NOPMD
    }
}
