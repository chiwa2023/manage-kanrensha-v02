package net.seijishikin.jp.normalize.manage.kanrensha.service.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.web.servlet.util.matcher.PathPatternRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import net.seijishikin.jp.normalize.manage.kanrensha.controller.PathRouteConstants;

/**
 * トークンからログインするフィルタ
 */
public class AuthorizeFilter extends OncePerRequestFilter {

    /** JwtDecoder */
    private final JwtDecoder jwtDecoder;

    /** 認証なしパスリスト */
    private List<RequestMatcher> list;

    /** リクエストパスMatcherリスト */
    private static final String HEADER_NAME = "X-AUTH-TOKEN";

    /** bearer */
    private static final String BEARER = "Bearer ";

    /** トークン取得位置 */
    private static final int POS_TOKEN = BEARER.length();

    /**
     * コンストラクタ
     *
     * @param jwtDecoder JwtDecoder
     */
    public AuthorizeFilter(final JwtDecoder jwtDecoder) {
        super();
        this.jwtDecoder = jwtDecoder;
    }

    /**
     * 内部フィルタ処理を行う
     */
    @Override
    protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response,
            final FilterChain filterChain) throws ServletException, IOException, BadCredentialsException { // NOPMD

        if (!this.listMatches(request)) {

            // headersのkeyを指定してトークンを取得します
            String xAuthToken = request.getHeader(HEADER_NAME);

            // ヘッダがない時、トークンが埋め込まれていないときは次のフィルタに委譲しているが
            // TODO 挙動確認後委譲処理を削除する(パスを正確に指定することで実現すればよい)
            if (xAuthToken == null || !xAuthToken.startsWith(BEARER)) {
                // throw new BadCredentialsException("X-AUTH-TOKENが取得できないか、Bearerで設定されていません");
                filterChain.doFilter(request, response);
                return;
            }

            // tokenの検証と認証
            Jwt jwt = jwtDecoder.decode(xAuthToken.substring(POS_TOKEN));

            // usernameの取得
            String username = jwt.getSubject();
            jwt.getClaim("roles");

            // ログイン状態の設定
            SecurityContextHolder.getContext()
                    .setAuthentication(new UsernamePasswordAuthenticationToken(username, null, new ArrayList<>()));

        }

        filterChain.doFilter(request, response);
    }

    private boolean listMatches(final HttpServletRequest request) {

        // 最初の1回だけリスト生成
        if (Objects.isNull(list)) {
            String[] publicUrls = { PathRouteConstants.ROOT + "/login", PathRouteConstants.ROOT + "/refresh-token",
                    PathRouteConstants.ROOT + "/add-user/**" };
            list = new ArrayList<>();
            for (String path : publicUrls) {
                list.add(PathPatternRequestMatcher.withDefaults().matcher(path));
            }
        }

        // 用意したフィルタMatcherから1件でも該当したらtrue
        for (RequestMatcher matcher : list) {
            if (matcher.matches(request)) {
                return true;
            }
        }

        return false;
    }

}
