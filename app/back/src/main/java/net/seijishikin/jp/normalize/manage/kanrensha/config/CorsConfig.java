package net.seijishikin.jp.normalize.manage.kanrensha.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * クロスサイトアクセス設定
 * 
 */
@Configuration
public class CorsConfig {

    /** ドメイン(frontend)URL */
    @Value("${app.frontend.base-url:http://localhost:5173}")
    private List<String> allowedOrigins;

    /**
     * クロスサイトアクセスフィルタ
     *
     * @return 許可リストが挿入されたフィルタ
     */
    @Bean
    protected CorsFilter corsFilter() {

        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(allowedOrigins);
        config.setAllowCredentials(true);
        config.addAllowedMethod("*");
        config.addAllowedHeader("*");
        config.addExposedHeader("Set-Cookie");

        UrlBasedCorsConfigurationSource configSource = new UrlBasedCorsConfigurationSource();
        configSource.registerCorsConfiguration("/**", config);

        return new CorsFilter(configSource);
    }
}
