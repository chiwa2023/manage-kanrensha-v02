package net.seijishikin.jp.normalize.manage.kanrensha.config;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import net.seijishikin.jp.normalize.manage.kanrensha.service.year.SwitchYearLoginHistoryService;

/**
 * 認証発生イベント
 */
@Component
public class AuthenticationEvents {

    /** ログイン履歴保存Service */
    @Autowired
    private SwitchYearLoginHistoryService switchYearLoginHistoryService;

    /** HttpRequest */
    @Autowired(required = false) // Webアプリケーションでないテスト実行なども考慮
    // @Autowired
    private HttpServletRequest request;

    /** データ未取得 */
    private static final String UNKNOWN = "Unknown";

    /**
     * 認証成功時に呼び出されるリスナー
     * 
     * @param event 認証成功イベント
     */
    @EventListener
    public void onSuccess(final AuthenticationSuccessEvent event) {
        String username = event.getAuthentication().getName();
        String ipAddress = getIpAddressFromSuccessEvent(event);
        String userAgent = getUserAgent();

        switchYearLoginHistoryService.practice(username, ipAddress, userAgent, true);
    }

    /**
     * 認証失敗時に呼び出されるリスナー
     * 
     * @param event 認証失敗イベント
     */
    @EventListener
    public void onFailure(final AbstractAuthenticationFailureEvent event) {
        // 存在しないユーザIDでのログイン試行の場合もある

        String username = "N/A";
        if (event.getAuthentication() != null && event.getAuthentication().getPrincipal() != null) {
            username = event.getAuthentication().getPrincipal().toString(); //NOPMD LowDemeter
        }

        String ipAddress = getIpAddressFromFailureEvent(event);
        String userAgent = getUserAgent();

        // 失敗理由なども取得可能
        // String exceptionMessage = event.getException().getMessage();

        switchYearLoginHistoryService.practice(username, ipAddress, userAgent, false);
    }

    /**
     * 失敗イベントからIPアドレスを取得する
     */
    private String getIpAddressFromFailureEvent(final AbstractAuthenticationFailureEvent event) {
        if (event.getAuthentication() == null) {
            return UNKNOWN;
        }
        Object details = event.getAuthentication().getDetails(); // NOPMD LawDemeter
        if (details instanceof WebAuthenticationDetails) {
            return ((WebAuthenticationDetails) details).getRemoteAddress();
        }
        return UNKNOWN;
    }

    /**
     * 成功イベントからIPアドレスを取得する
     */
    private String getIpAddressFromSuccessEvent(final AuthenticationSuccessEvent event) {
        Object details = event.getAuthentication().getDetails(); // NOPMD LawDemeter
        if (details instanceof WebAuthenticationDetails) {
            return ((WebAuthenticationDetails) details).getRemoteAddress();
        }
        return UNKNOWN;
    }

    /**
     * HttpServletRequestからUserAgentを取得する
     */
    private String getUserAgent() {
        if (request != null) {
            String agent = request.getHeader("User-Agent");
            if (Objects.isNull(agent)) {
                agent = "";
            }
            return agent;
        }
        return UNKNOWN;
    }
}
