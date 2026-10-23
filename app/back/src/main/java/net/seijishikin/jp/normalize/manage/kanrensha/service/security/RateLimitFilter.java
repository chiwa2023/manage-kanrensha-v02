package net.seijishikin.jp.normalize.manage.kanrensha.service.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.security.web.servlet.util.matcher.PathPatternRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.seijishikin.jp.normalize.manage.kanrensha.config.RateLimitConfig;
import net.seijishikin.jp.normalize.manage.kanrensha.controller.PathRouteConstants;

/**
 * APIレートリミットフィルター
 */
public class RateLimitFilter extends OncePerRequestFilter {

    /** レートリミットService */
    private final RateLimitService rateLimitService;

    /** レートリミット設定 */
    private final RateLimitConfig rateLimitConfig;

    /** レートリミット適用パスMatcherリスト */
    private final List<RequestMatcher> targetMatchers;

    /**
     * コンストラクタ
     *
     * @param rateLimitService レートリミットService
     * @param rateLimitConfig  レートリミット設定
     */
    public RateLimitFilter(final RateLimitService rateLimitService, final RateLimitConfig rateLimitConfig) {
        super();
        this.rateLimitService = rateLimitService;
        this.rateLimitConfig = rateLimitConfig;

        this.targetMatchers = new ArrayList<>();
        this.targetMatchers.add(PathPatternRequestMatcher.withDefaults().matcher(PathRouteConstants.ROOT + "/api-for-partner/**"));
        this.targetMatchers.add(PathPatternRequestMatcher.withDefaults().matcher(PathRouteConstants.ROOT + "/login"));
        this.targetMatchers.add(PathPatternRequestMatcher.withDefaults().matcher(PathRouteConstants.ROOT + "/refresh-token"));
    }

    /**
     * 内部フィルター処理
     */
    @Override
    protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response, final FilterChain filterChain) throws ServletException, IOException {
        
        // レートリミットが無効の場合はスルー
        if (!rateLimitConfig.isEnabled() || !this.isTargetRequest(request)) {
            filterChain.doFilter(request, response);
            return;
        }

        // クライアントキー特定
        String clientKey = resolveClientKey(request);

        // トークン消費試行
        if (!rateLimitService.tryConsume(clientKey)) {
            response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
            response.setContentType("application/json;charset=UTF-8");
            response.setHeader("Retry-After", String.valueOf(rateLimitConfig.getRefillDurationSeconds()));
            response.getWriter().write("{\"status\":429,\"error\":\"Too Many Requests\",\"message\":\"APIの呼び出し上限を超過しました。しばらく経ってから再試行してください。\"}"); // NOPMD
            return;
        }

        filterChain.doFilter(request, response);
    }

    /**
     * リクエストがレートリミット対象か判定する
     *
     * @param request HttpRequest
     * @return 対象の場合true
     */
    private boolean isTargetRequest(final HttpServletRequest request) {
        for (RequestMatcher matcher : targetMatchers) {
            if (matcher.matches(request)) {
                return true;
            }
        }
        return false;
    }

    /**
     * クライアント識別キーを解決する
     *
     * @param request HttpRequest
     * @return 識別キー文字列
     */
    private String resolveClientKey(final HttpServletRequest request) {

        // パートナートークンヘッダーがあれば最優先でキーとする
        String authToken = request.getHeader("X-AUTH-TOKEN");
        if (authToken != null && !authToken.isBlank()) {
            return "token:" + authToken;
        }

        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && !authHeader.isBlank()) {
            return "token:" + authHeader;
        }

        // なければIPアドレス（X-Forwarded-For 考慮）
        String xForwardedFor = request.getHeader("X-Forwarded-For");
        if (xForwardedFor != null && !xForwardedFor.isBlank()) {
            String[] ips = xForwardedFor.split(",");
            return "ip:" + ips[0].trim();
        }

        return "ip:" + request.getRemoteAddr();
    }
}
