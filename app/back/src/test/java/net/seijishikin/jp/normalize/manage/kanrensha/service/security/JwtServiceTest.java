package net.seijishikin.jp.normalize.manage.kanrensha.service.security;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import net.seijishikin.jp.normalize.manage.kanrensha.dto.sequrity.JwtTokenDto;

/**
 * JwtService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class JwtServiceTest {
    /** テスト対象 */
    @Autowired
    private JwtService jwtService;

    /** JwtDecoder */
    @Autowired
    private JwtDecoder jwtDecoder;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        final String role = "manager";
        List<String> list = new ArrayList<>();
        list.add(role);
        final String user = "test_user";
        JwtTokenDto tokenDto = jwtService.generateToken(user, list);

        Jwt jwtAccess = jwtDecoder.decode(tokenDto.getAccessToken());

        final String roleKey = "roles";
        // アクセストークンはロールを含む
        assertEquals(user, jwtAccess.getSubject());
        List<String> listAccessRole = jwtAccess.getClaimAsStringList(roleKey);
        assertEquals(1, listAccessRole.size());
        assertEquals(role, listAccessRole.get(0));
        assertTrue(jwtAccess.getExpiresAt().isAfter(Instant.now()));

        // リフレッシュトークンはロールを含まない
        Jwt jwtReflesh = jwtDecoder.decode(tokenDto.getRefreshToken());
        assertNull(jwtReflesh.getClaimAsStringList(roleKey));
        assertEquals(user, jwtReflesh.getSubject());
        assertTrue(jwtReflesh.getIssuedAt().isBefore(Instant.now()));
    }

}
