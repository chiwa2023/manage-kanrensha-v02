package net.seijishikin.jp.normalize.manage.kanrensha.config; // NOPMD

import java.util.List; // NOPMD ExcessiveImports

import org.springframework.beans.factory.annotation.Autowired; // NOPMD
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;

import net.seijishikin.jp.normalize.manage.kanrensha.constants.UserRoleConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.controller.PathRouteConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.service.security.AuthorizeFilter;
import net.seijishikin.jp.normalize.manage.kanrensha.service.security.CustomUserDetailsManager;

/**
 * セキュリティ設定
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    /** ログインユーザ詳細取得Manager */
    @Autowired
    private CustomUserDetailsManager customUserDetailsManager;

    /** jwt鍵 */
    @Autowired
    private JwtKeyProperties jwtKeyProperties;

    /** ドメイン(frontend)URL */
    @Value("${app.cors.allowed-origins:http://localhost:5173}")
    private List<String> allowedOrigins;

    /**
     * AuthenticationManagerをBeanとする
     *
     * @param authenticationConfiguration 認証設定
     * @return AuthenticationManager
     * @throws Exception 一般例外
     */
    @Bean
    protected AuthenticationManager authenticationManager(final AuthenticationConfiguration authenticationConfiguration)
            throws Exception { // NOPMD
        return authenticationConfiguration.getAuthenticationManager();
    }

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
                    requests.requestMatchers(PathRouteConstants.ROOT + "/", PathRouteConstants.ROOT + "/login",
                            PathRouteConstants.ROOT + "/refresh-token", PathRouteConstants.ROOT + "/replace-token",
                            PathRouteConstants.ROOT + "/add-user/**", PathRouteConstants.ROOT + "/trial-access",
                            PathRouteConstants.ROOT + "/reset-password/**",
                            PathRouteConstants.ROOT + "/api-for-partner/**")//
                            .permitAll() //
                            /* 以下はコンパイル時(front接続時)にのみ有効 */

                            // .requestMatchers( // 運営者(とSE権限)
                            // PathRouteConstants.ROOT + "/address-rsdt/**", //
                            // PathRouteConstants.ROOT + "/analysis-xml/execute", //
                            // PathRouteConstants.ROOT + "/city-lgcode-all/reflesh", //
                            // PathRouteConstants.ROOT + "/lgcode/search", //
                            // PathRouteConstants.ROOT + "/lgcode-delete/**", //
                            // PathRouteConstants.ROOT + "/postal-code/**", //
                            // PathRouteConstants.ROOT + "/postal-irregular/**", //
                            // PathRouteConstants.ROOT + "/postal-wktbl/**", //
                            // PathRouteConstants.ROOT + "/regist-bulk-history/**", //
                            // PathRouteConstants.ROOT + "/regist-bulk-master-min/**", //
                            // PathRouteConstants.ROOT + "/regist-bulk-master-std/**", //
                            // PathRouteConstants.ROOT + "/regist-by-xml/**", //
                            // PathRouteConstants.ROOT + "/regist-combine/**", //
                            // PathRouteConstants.ROOT + "/riyousha/save-manage",
                            // PathRouteConstants.ROOT + "/riyousha-org/accept-combine",
                            // PathRouteConstants.ROOT + "/riyousha-org/delete-person",
                            // PathRouteConstants.ROOT + "/riyousha-org/get**",
                            // PathRouteConstants.ROOT + "/riyousha-org/insert-combine",
                            // PathRouteConstants.ROOT + "/riyousha-org/invite-person",
                            // PathRouteConstants.ROOT + "/user-role/**", // admin推薦：本当に運営者限定
                            // PathRouteConstants.ROOT + "/wktbl-address-rsdt/**", //
                            // PathRouteConstants.ROOT + "/works-approval/**", //
                            // PathRouteConstants.ROOT + "/xml/look-ahead")
                            // .hasAnyRole(UserRoleConstants.ADMIN, UserRoleConstants.MANAGER) //
                            // .requestMatchers( // APIパートナーだけ
                            // PathRouteConstants.ROOT + "/partner-api/**")
                            // .hasRole(UserRoleConstants.PARTNER_API) //
                            // .requestMatchers( // SE権限のみ
                            // PathRouteConstants.ROOT + "/dump-history/**", //
                            // PathRouteConstants.ROOT + "/dump-master-min/**", //
                            // PathRouteConstants.ROOT + "/dump-master-std/**", //
                            // PathRouteConstants.ROOT + "/edit-user/search", //
                            // PathRouteConstants.ROOT + "/stack-trace/get-by-code", //
                            // PathRouteConstants.ROOT + "/task-info/**", //
                            // PathRouteConstants.ROOT + "/timer-yotei/**",
                            // PathRouteConstants.ROOT + "/riyousha/delete",
                            // PathRouteConstants.ROOT + "/riyousha/search-all",
                            // PathRouteConstants.ROOT + "/riyousha-org/delete",
                            // PathRouteConstants.ROOT + "/riyousha-org/search**",
                            // PathRouteConstants.ROOT + "/user-role/promote")
                            // .hasRole(UserRoleConstants.ADMIN) //
                            //
                            // .requestMatchers( // 関連者を除く利用者限定
                            // PathRouteConstants.ROOT + "/riyousha/get**",
                            // PathRouteConstants.ROOT + "/riyousha/save**",
                            // PathRouteConstants.ROOT + "/user-kanrensha/search**")
                            // .hasAnyRole(UserRoleConstants.ADMIN, UserRoleConstants.MANAGER,
                            // UserRoleConstants.PARTNER_API) //
                            //
                            // .requestMatchers( // 関連者と利用者
                            // PathRouteConstants.ROOT + "/edit-user/delete",
                            // PathRouteConstants.ROOT + "/edit-user/get",
                            // PathRouteConstants.ROOT + "/edit-user/refresh-password",
                            // PathRouteConstants.ROOT + "/user-kanrensha/add**", //
                            // PathRouteConstants.ROOT + "/user-kanrensha/edit**", //
                            // PathRouteConstants.ROOT + "/user-kanrensha/get**", //
                            // PathRouteConstants.ROOT + "/postal-search/**",
                            // PathRouteConstants.ROOT + "/lgcode-city/search",
                            // PathRouteConstants.ROOT + "/lgcode-pref/search", //
                            // PathRouteConstants.ROOT + "/task-plan/**") //
                            // .hasAnyRole(UserRoleConstants.ADMIN, UserRoleConstants.MANAGER,
                            // UserRoleConstants.PARTNER_API, UserRoleConstants.KANRENSHA_PERSON,
                            // UserRoleConstants.KANRENSHA_KIGYOU_DT,
                            // UserRoleConstants.KANRENSHA_SEIJIDANTAI) //

                            /* ここまで */
                            .anyRequest().authenticated();

                })
                .oauth2ResourceServer(
                        oauth2 -> oauth2.jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter())))
                .addFilterBefore(new AuthorizeFilter(jwtDecoder()), UsernamePasswordAuthenticationFilter.class)
                .logout(logout -> logout.logoutSuccessUrl("/"))
                .cors(cors -> cors.configurationSource(this.corsConfigurationSource())).build();
    }

    /**
     * PasswordEncoderを取得する
     *
     * @return PasswordEncoder
     */
    @Bean
    protected PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * DaoAuthenticationProvider を取得する
     *
     * @return DaoAuthenticationProvider
     */
    @Bean
    protected DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(customUserDetailsManager);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    /**
     * 権限(role)コンバータを取得する
     *
     * @return 権限(role)コンバータ
     */
    @Bean
    protected JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter grantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        grantedAuthoritiesConverter.setAuthoritiesClaimName("roles");
        grantedAuthoritiesConverter.setAuthorityPrefix("ROLE_");

        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(grantedAuthoritiesConverter);
        return jwtAuthenticationConverter;
    }

    /**
     * JwtDecoderを取得する
     *
     * @return JwtDecoder
     */
    @Bean
    protected JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withPublicKey(jwtKeyProperties.getPublicKey()).build();
    }

    /**
     * JwtEncoderを設定する
     *
     * @return JwtEncoder
     */
    @Bean
    protected JwtEncoder jwtEncoder() {
        JWK jwk = new RSAKey.Builder(jwtKeyProperties.getPublicKey()).privateKey(jwtKeyProperties.getPrivateKey())
                .build();
        JWKSource<SecurityContext> jwkSource = new ImmutableJWKSet<>(new JWKSet(jwk));
        return new NimbusJwtEncoder(jwkSource);
    }

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
        corsConfiguration.setAllowedOrigins(allowedOrigins);
        corsConfiguration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource corsSource = new UrlBasedCorsConfigurationSource();
        corsSource.registerCorsConfiguration("/**", corsConfiguration);
        return corsSource;
    }
}
