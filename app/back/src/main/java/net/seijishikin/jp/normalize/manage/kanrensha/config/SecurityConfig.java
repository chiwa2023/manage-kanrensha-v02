package net.seijishikin.jp.normalize.manage.kanrensha.config; // NOPMD

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.oauth2.jwt.JwtDecoder;
//import org.springframework.security.oauth2.jwt.JwtEncoder;
//import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
//import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
//import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
//import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

//import com.nimbusds.jose.jwk.JWK;
//import com.nimbusds.jose.jwk.JWKSet;
//import com.nimbusds.jose.jwk.RSAKey;
//import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
//import com.nimbusds.jose.jwk.source.JWKSource;
//import com.nimbusds.jose.proc.SecurityContext;

//import mitei.mitei.political.balancesheet.manage.kanrensha.service.security.AuthorizeFilter;
//import mitei.mitei.political.balancesheet.manage.kanrensha.service.security.CustomUserDetailsManager;

/**
 * セキュリティ設定
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
//
//    /** ログインユーザ詳細取得Manager */
//    @Autowired
//    private CustomUserDetailsManager customUserDetailsManager;
//
//    /** jwt鍵 */
//    @Autowired
//    private JwtKeyProperties jwtKeyProperties;

    /**
     * SecurityFilterChainを取得する
     *
     * @param http HttpSecurity
     * @return フィルタチェーン
     * @throws Exception 一般例外
     */
    @Bean
    protected SecurityFilterChain securityFilterChain(final HttpSecurity http) throws Exception { // NOPMD

        http.headers(headers -> headers.frameOptions(frame -> frame.sameOrigin()));
        return http.csrf(AbstractHttpConfigurer::disable) //
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(requests -> {
                    // 401,403エラー処理
                    requests.requestMatchers("/", "/login", "/reflesh-token", "/replace-token", "/add-user/**","/trial-access")
                            .permitAll() //
                            .anyRequest().authenticated();
                    // TODO roleによる分岐が必要なら設定
                })
//                .oauth2ResourceServer(
//                        oauth2 -> oauth2.jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter())))
//                .addFilterBefore(new AuthorizeFilter(jwtDecoder()), UsernamePasswordAuthenticationFilter.class)
                .logout(logout -> logout.logoutSuccessUrl("/"))
                .cors(cors -> cors.configurationSource(this.corsConfigurationSource())).build();
    }

//    /**
//     * PasswordEncoderを取得する
//     *
//     * @return PasswordEncoder
//     */
//    @Bean
//    protected PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

//    /**
//     * DaoAuthenticationProvider を取得する
//     *
//     * @return DaoAuthenticationProvider
//     */
//    @Bean
//    protected DaoAuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(customUserDetailsManager);
//        provider.setPasswordEncoder(passwordEncoder());
//        return provider;
//    }

//    /**
//     * 権限(role)コンバータを取得する
//     *
//     * @return 権限(role)コンバータ
//     */
//    @Bean
//    protected JwtAuthenticationConverter jwtAuthenticationConverter() {
//        JwtGrantedAuthoritiesConverter grantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
//        grantedAuthoritiesConverter.setAuthoritiesClaimName("roles");
//        grantedAuthoritiesConverter.setAuthorityPrefix("ROLE_");
//
//        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
//        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(grantedAuthoritiesConverter);
//        return jwtAuthenticationConverter;
//    }

//    /**
//     * JwtDecoderを取得する
//     *
//     * @return JwtDecoder
//     */
//    @Bean
//    protected JwtDecoder jwtDecoder() {
//        return NimbusJwtDecoder.withPublicKey(jwtKeyProperties.getPublicKey()).build();
//    }

//    /**
//     * JwtEncoderを設定する
//     *
//     * @return JwtEncoder
//     */
//    @Bean
//    protected JwtEncoder jwtEncoder() {
//        JWK jwk = new RSAKey.Builder(jwtKeyProperties.getPublicKey()).privateKey(jwtKeyProperties.getPrivateKey())
//                .build();
//        JWKSource<SecurityContext> jwkSource = new ImmutableJWKSet<>(new JWKSet(jwk));
//        return new NimbusJwtEncoder(jwkSource);
//    }

    /**
     * Cors設定を取得する
     *
     * @return CorsConfiguration
     */
    private CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedMethod(CorsConfiguration.ALL);
        corsConfiguration.addAllowedHeader(CorsConfiguration.ALL);
        corsConfiguration.addExposedHeader("X-AUTH-TOKEN");
        corsConfiguration.addAllowedOrigin(CorsConfig.ALLOW_URL_KANRENSHA);
        corsConfiguration.addAllowedOrigin(CorsConfig.ALLOW_URL_PORT);
        corsConfiguration.addAllowedOrigin(CorsConfig.ALLOW_URL_DOCKER);
        corsConfiguration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource corsSource = new UrlBasedCorsConfigurationSource();
        corsSource.registerCorsConfiguration("/**", corsConfiguration);
        return corsSource;
    }
}
