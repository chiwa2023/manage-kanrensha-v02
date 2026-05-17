package net.seijishikin.jp.normalize.manage.kanrensha.controller.api_partner;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.oauth2.server.resource.InvalidBearerTokenException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import net.seijishikin.jp.normalize.common_tool.dto.FrameworkMessageAndResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.controller.PathRouteConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.service.security.PartnerApiLoginService;

/**
 * APiパートナー接続試行Controller
 */
@RestController
@RequestMapping(PathRouteConstants.ROOT + "/api-for-partner")
public class PartnerApiTrialLoginController {

    /** APIパートナー接続Service */
    @Autowired
    private PartnerApiLoginService partnerApiLoginService;

    /**
     * 処理を行う
     * 
     * @param httpServletRequest HttpRewuest
     * @return レスポンス
     */
    @PostMapping("/trial-login")
    public ResponseEntity<FrameworkMessageAndResultDto> practice(final HttpServletRequest httpServletRequest) {

        FrameworkMessageAndResultDto resultDto = new FrameworkMessageAndResultDto();
        String authorizationHeader = httpServletRequest.getHeader(HttpHeaderKeyConstants.HEADER_KEY_AUTH);
        if (authorizationHeader != null && authorizationHeader.startsWith(HttpHeaderKeyConstants.HEADER_KEY_BEARE)) {
            try {
                String token = authorizationHeader.substring(HttpHeaderKeyConstants.HEADER_KEY_BEARE.length());
                String userAgent = httpServletRequest.getHeader(HttpHeaderKeyConstants.HEADER_KEY_AGENT);
                String ipAddress = httpServletRequest.getRemoteAddr();

                String accessUrl = PathRouteConstants.ROOT + "/api-for-partner" + "/trial-login";

                partnerApiLoginService.practice(token, accessUrl, userAgent, ipAddress, LocalDateTime.now());
                return ResponseEntity.status(HttpStatus.OK).body(resultDto);

            } catch (InvalidBearerTokenException | LockedException | AccessDeniedException unauthorizedException) {
                // 401 token不正
                resultDto.setIsFailure(true);
                resultDto.setMessage(unauthorizedException.getMessage());
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(resultDto);
            } catch (Exception exception) { // NOPMD
                // internal server error
                resultDto.setIsFailure(true);
                resultDto.setMessage("システム例外が発生しています。システム担当者にお問い合わせください");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resultDto);
            }
        } else {
            // 以下はBearer設定がされていなかった場合におかえりいただく処理
            resultDto.setIsFailure(true);
            resultDto.setMessage("リクエストヘッダにBearerが設定されていません");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(resultDto);
        }
    }

}
