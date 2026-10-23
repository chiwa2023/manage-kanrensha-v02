package net.seijishikin.jp.normalize.manage.kanrensha.service.security;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.util.ReflectionTestUtils;

import jakarta.servlet.ServletException;
import net.seijishikin.jp.normalize.manage.kanrensha.config.RateLimitConfig;

/**
 * RateLimitFilterの単体テスト
 */
class RateLimitFilterTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    private RateLimitFilter rateLimitFilter;
    
    /** RateLimitConfig */
    private RateLimitConfig rateLimitConfig;

    @BeforeEach
    void setUp() {
        rateLimitConfig = new RateLimitConfig();
        rateLimitConfig.setEnabled(true);
        rateLimitConfig.setCapacity(2);
        rateLimitConfig.setRefillTokens(2);
        rateLimitConfig.setRefillDurationSeconds(60);

        RateLimitService rateLimitService = new RateLimitService();
        ReflectionTestUtils.setField(rateLimitService, "rateLimitConfig", rateLimitConfig);
        rateLimitService.clearCache();

        rateLimitFilter = new RateLimitFilter(rateLimitService, rateLimitConfig);
    }

    @Test
    void testRateLimitExceededOnPartnerApi() throws ServletException, IOException {
        String path = "/api/api-for-partner/convert";


        final String methodPost = "POST";

        // 1回目: 許可
        MockHttpServletRequest request1 = this.getMockHttpServletRequest(methodPost, path);
        request1.setRemoteAddr("192.168.1.10"); // NOPMD
        MockHttpServletResponse response1 = new MockHttpServletResponse();
        MockFilterChain chain1 = new MockFilterChain();
        rateLimitFilter.doFilter(request1, response1, chain1);
        assertEquals(200, response1.getStatus());

        // 2回目: 許可
        MockHttpServletRequest request2 = this.getMockHttpServletRequest(methodPost, path);
        request2.setRemoteAddr("192.168.1.10"); // NOPMD
        MockHttpServletResponse response2 = new MockHttpServletResponse();
        MockFilterChain chain2 = new MockFilterChain();
        rateLimitFilter.doFilter(request2, response2, chain2);
        assertEquals(200, response2.getStatus());

        // 3回目: 容量2を超過して 429
        MockHttpServletRequest request3 = this.getMockHttpServletRequest(methodPost, path);
        request3.setRemoteAddr("192.168.1.10"); // NOPMD
        MockHttpServletResponse response3 = new MockHttpServletResponse();
        MockFilterChain chain3 = new MockFilterChain();
        rateLimitFilter.doFilter(request3, response3, chain3);
        assertEquals(429, response3.getStatus());
        assertEquals("60", response3.getHeader("Retry-After"));
        assertTrue(response3.getContentAsString().contains("Too Many Requests"));
    }

    @Test
    void testNonTargetUrlBypassesRateLimit() throws ServletException, IOException {
        String path = "/api/some-unrestricted-path";

        final String methodGet = "Get";

        for (int i = 0; i < 5; i++) {
            MockHttpServletRequest request = this.getMockHttpServletRequest(methodGet, path);
            request.setRemoteAddr("192.168.1.20"); // NOPMD
            MockHttpServletResponse response = new MockHttpServletResponse(); // NOPMD
            MockFilterChain chain = new MockFilterChain(); // NOPMD
            rateLimitFilter.doFilter(request, response, chain);
            assertEquals(200, response.getStatus());
        }
    }

    @Test
    void testDisabledConfigBypassesRateLimit() throws ServletException, IOException {
        rateLimitConfig.setEnabled(false);
        String path = "/api/login";
        
        final String methodPost = "POST";

        for (int i = 0; i < 5; i++) {
            MockHttpServletRequest request = this.getMockHttpServletRequest(methodPost, path);
            request.setRemoteAddr("192.168.1.30"); // NOPMD
            MockHttpServletResponse response = new MockHttpServletResponse(); // NOPMD
            MockFilterChain chain = new MockFilterChain(); // NOPMD
            rateLimitFilter.doFilter(request, response, chain);
            assertEquals(200, response.getStatus());
        }
    }

    @Test
    void testClientKeyByTokenHeader() throws ServletException, IOException {
        String path = "/api/api-for-partner/convert";

        final String methodPost = "POST";
        
        // token-A で 2 回アクセスして枯渇
        for (int i = 0; i < 2; i++) {
            MockHttpServletRequest request = this.getMockHttpServletRequest(methodPost, path);
            request.addHeader("X-AUTH-TOKEN", "token-A");
            MockHttpServletResponse response = new MockHttpServletResponse(); // NOPMD
            rateLimitFilter.doFilter(request, response, new MockFilterChain()); // NOPMD
            assertEquals(200, response.getStatus());
        }

        // token-A の 3 回目は 429
        MockHttpServletRequest reqA3 = this.getMockHttpServletRequest(methodPost, path);
        reqA3.addHeader("X-AUTH-TOKEN", "token-A");
        MockHttpServletResponse resA3 = new MockHttpServletResponse();
        rateLimitFilter.doFilter(reqA3, resA3, new MockFilterChain());
        assertEquals(429, resA3.getStatus());

        // token-B は別キーなので 200
        MockHttpServletRequest reqB1 = this.getMockHttpServletRequest(methodPost, path);
        reqB1.addHeader("X-AUTH-TOKEN", "token-B");
        MockHttpServletResponse resB1 = new MockHttpServletResponse();
        rateLimitFilter.doFilter(reqB1, resB1, new MockFilterChain());
        assertEquals(200, resB1.getStatus());
    }


    private MockHttpServletRequest getMockHttpServletRequest(final String method,final String path) {
        return new MockHttpServletRequest(method, path);
    }
}
