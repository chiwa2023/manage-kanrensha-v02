package net.seijishikin.jp.normalize.manage.kanrensha.controller.api_partner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;

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
import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.constants.UserRoleConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.controller.PathRouteConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.sequrity.JwtTokenDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.sequrity.JwtTokenResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.service.security.JwtService;
import net.seijishikin.jp.normalize.manage.kanrensha.service.security.PartnerApiLoginService;
import net.seijishikin.jp.normalize.manage.kanrensha.service.util.SaveStackTraceService;

/**
 * APIパートナー長期TokenをアクセスTokenh変換Controller
 */
@RestController
@RequestMapping(PathRouteConstants.ROOT + "/api-for-partner")
public class ConvertPartnerApiTokenToAccessTokenController {

    /** APIパートナー接続Service */
    @Autowired
    private PartnerApiLoginService partnerApiLoginService;

    /** JWT Token Service */
    @Autowired
    private JwtService jwtService;

    /** StackTrace保存Service */
    @Autowired
    private SaveStackTraceService saveStackTraceService;

    /**
     * 処理を行う
     * 
     * @param httpServletRequest HttpRequest
     * @return JwtToken
     */
    @PostMapping("/convert")
    public ResponseEntity<JwtTokenResultDto> practice(final HttpServletRequest httpServletRequest) {

        String authorizationHeader = httpServletRequest.getHeader(HttpHeaderKeyConstants.HEADER_KEY_AUTH);
        JwtTokenResultDto resultDto = new JwtTokenResultDto();

        if (authorizationHeader != null && authorizationHeader.startsWith(HttpHeaderKeyConstants.HEADER_KEY_BEARE)) {
            try {
                String token = authorizationHeader.substring(HttpHeaderKeyConstants.HEADER_KEY_BEARE.length());
                String userAgent = httpServletRequest.getHeader(HttpHeaderKeyConstants.HEADER_KEY_AGENT);
                String ipAddress = httpServletRequest.getRemoteAddr();
                String accessUrl = PathRouteConstants.ROOT + "/api-for-partner" + "/convert";

                LeastUserDto leastUserDto = partnerApiLoginService.practice(token, accessUrl, userAgent, ipAddress,
                        LocalDateTime.now());

                // アクセストークンとリフレッシュトークン生成
                JwtTokenDto jwtTokenDto = jwtService.generateToken(String.valueOf(leastUserDto.getUserPersonCode()),
                        Collections.singletonList(UserRoleConstants.PARTNER_API));

                resultDto.setJwtTokenDto(jwtTokenDto);
                resultDto.setMessage("正常に取得できました");
                return ResponseEntity.status(HttpStatus.OK).body(resultDto);

            } catch (InvalidBearerTokenException | LockedException | AccessDeniedException unautohrizedException) {
                // 401 token不正
                resultDto.setIsFailure(true);
                resultDto.setMessage(unautohrizedException.getMessage());
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(resultDto);
            } catch (Exception exception) { // NOPMD
                saveStackTraceService.practice(exception, LocalDate.now().getYear(), 0);
                // internal server error
                resultDto.setIsFailure(true);
                resultDto.setMessage("システムで例外が発生しています。システム担当者にお問い合わせください");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resultDto);
            }
        } else {
            // 以下はBearer設定がされていなかった場合におかえりいただく処理
            resultDto.setIsFailure(true);
            resultDto.setMessage("Bearerが取得できませんでした");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(resultDto);
        }
    }

}
