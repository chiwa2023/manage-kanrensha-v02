package net.seijishikin.jp.normalize.manage.kanrensha.config;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

/**
 * SecurityConfig単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class SecurityConfigTest {

    /** テスト対象 */
    @Autowired
    private SecurityConfig securityConfig;
    
    
    /** テスト呼び出しタグ */
    private static final String TAG = "TableTruncate";

    @Test
    @Tag(TAG)
    void testSecurityFilterChain() throws Exception {
        HttpSecurity security = Mockito.mock(HttpSecurity.class);
        assertEquals(DefaultSecurityFilterChain.class, securityConfig.securityFilterChain(security).getClass());
    }

    @Test
    @Tag(TAG)
    void testJwtAuthenticationConverter() {
        assertEquals(JwtAuthenticationConverter.class, securityConfig.jwtAuthenticationConverter().getClass());
    }

    @Test
    @Tag(TAG)
    void testJwtDecoder() {
        assertEquals(NimbusJwtDecoder.class, securityConfig.jwtDecoder().getClass());
    }

    @Test
    @Tag(TAG)
    void testJwtEncoder() {
        assertEquals(NimbusJwtEncoder.class, securityConfig.jwtEncoder().getClass());
    }

    @Test
    @Tag(TAG)
    void testPasswordEncoder() {
        assertEquals(BCryptPasswordEncoder.class, securityConfig.passwordEncoder().getClass());
    }

    @Test
    @Tag(TAG)
    void testAuthenticationProvider() {
        assertEquals(DaoAuthenticationProvider.class, securityConfig.authenticationProvider().getClass());
    }
}
