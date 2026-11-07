package net.seijishikin.jp.normalize.manage.kanrensha.config;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

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
@SpringBootTest
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

    @Test
    @Tag(TAG)
    void testCorsConfigurationSource() {
        org.springframework.web.cors.CorsConfigurationSource source = securityConfig.corsConfigurationSource();
        org.springframework.mock.web.MockHttpServletRequest request = new org.springframework.mock.web.MockHttpServletRequest();
        request.setRequestURI("/api/test");
        org.springframework.web.cors.CorsConfiguration config = source.getCorsConfiguration(request);

        org.junit.jupiter.api.Assertions.assertNotNull(config);
        org.junit.jupiter.api.Assertions.assertTrue(config.getAllowCredentials());
        assertEquals(SecurityConfig.MAX_AGE, config.getMaxAge());
        org.junit.jupiter.api.Assertions.assertTrue(config.getExposedHeaders().contains("X-AUTH-TOKEN"));
        org.junit.jupiter.api.Assertions.assertTrue(config.getExposedHeaders().contains("Authorization"));
    }
}
