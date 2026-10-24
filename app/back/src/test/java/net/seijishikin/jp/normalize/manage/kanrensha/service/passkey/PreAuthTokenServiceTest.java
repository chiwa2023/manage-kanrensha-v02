package net.seijishikin.jp.normalize.manage.kanrensha.service.passkey;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.service.passkey.PreAuthTokenService.PreAuthSession;

/**
 * PreAuthTokenService単体テスト
 */
class PreAuthTokenServiceTest {

    /** テスト対象 */
    private PreAuthTokenService preAuthTokenService;

    @BeforeEach
    void setUp() {
        preAuthTokenService = new PreAuthTokenService();
    }

    @Test
    void testTokenLifecycle() {
        final Integer userPersonCode = 20001;
        String username = "test@example.com";
        Authentication auth = new UsernamePasswordAuthenticationToken(username, "password", Collections.emptyList());
        LeastUserDto userDto = new LeastUserDto();
        userDto.setUserPersonCode(userPersonCode);
        userDto.setUserPersonName("テスト太郎");

        // Token発行
        String token = preAuthTokenService.createPreAuthToken(userPersonCode, username, auth, userDto);
        assertNotNull(token);
        assertFalse(token.isBlank());

        // セッション取得
        PreAuthSession session = preAuthTokenService.getPreAuthSession(token);
        assertNotNull(session);
        assertEquals(userPersonCode, session.userPersonCode());
        assertEquals(username, session.username());
        assertEquals(auth, session.authentication());
        assertEquals("テスト太郎", session.leastUserDto().getUserPersonName());
        assertFalse(session.isExpired());

        // セッション消費・削除
        preAuthTokenService.removePreAuthSession(token);
        assertNull(preAuthTokenService.getPreAuthSession(token));
    }

    @Test
    void testNonExistentOrNullToken() {
        assertNull(preAuthTokenService.getPreAuthSession(null));
        assertNull(preAuthTokenService.getPreAuthSession(""));
        assertNull(preAuthTokenService.getPreAuthSession("non-existent-token"));
    }
}
