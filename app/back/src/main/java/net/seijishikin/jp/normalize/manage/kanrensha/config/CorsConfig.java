package net.seijishikin.jp.normalize.manage.kanrensha.config;

import java.util.ArrayList;
import java.util.List;

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

    /** サイトをまたいでアクセスを許可するURL(自front側・開発用) */
    public static final String ALLOW_URL_KANRENSHA = "http://localhost:5173";
    /** ポート違い */
    public static final String ALLOW_URL_PORT = "http://localhost:5174";
    /** Dockerドメイン */
    public static final String ALLOW_URL_DOCKER = "http://host.docker.internal:5174";

    /**
     * クロスサイトアクセスフィルタ
     *
     * @return 許可リストが挿入されたフィルタ
     */
    @Bean
    protected CorsFilter corsFilter() {

        List<String> listAllow = new ArrayList<>();
        listAllow.add(ALLOW_URL_KANRENSHA);
        listAllow.add(ALLOW_URL_PORT);
        listAllow.add(ALLOW_URL_DOCKER);

        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(listAllow);
        config.setAllowCredentials(true);
        config.addAllowedMethod("*");
        config.addAllowedHeader("*");
        config.addExposedHeader("Set-Cookie");

        UrlBasedCorsConfigurationSource configSource = new UrlBasedCorsConfigurationSource();
        configSource.registerCorsConfiguration("/**", config);

        return new CorsFilter(configSource);
    }
}
