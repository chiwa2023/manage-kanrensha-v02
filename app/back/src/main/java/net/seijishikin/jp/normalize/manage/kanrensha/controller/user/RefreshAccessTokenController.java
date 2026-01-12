package net.seijishikin.jp.normalize.manage.kanrensha.controller.user;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.seijishikin.jp.normalize.manage.kanrensha.dto.sequrity.JwtTokenDto;
import net.seijishikin.jp.normalize.manage.kanrensha.service.security.CustomUserDetailsManager;
import net.seijishikin.jp.normalize.manage.kanrensha.service.security.JwtService;
import net.seijishikin.jp.normalize.manage.kanrensha.service.util.SaveStackTraceService;
import net.seijishikin.jp.normalize.manage.kanrensha.controller.PathRouteConstants;

/**
 * アクセストークンを更新Controller
 */
@RestController
@RequestMapping(PathRouteConstants.ROOT)
public class RefreshAccessTokenController {

    /** JwtDecoder */
    @Autowired
    private JwtDecoder jwtDecoder;

    /** CustomUserDetailsManager */
    @Autowired
    private CustomUserDetailsManager customUserDetailsManager;

    /** JwtService */
    @Autowired
    private JwtService jwtService;

    /** JwtService */
    @Autowired
    private SaveStackTraceService saveStackTraceService;

    /**
     * 処理を行う
     *
     * @param tokenDto トークンDto
     * @return 更新されたToken
     */
    @PostMapping("/refresh-token")
    public ResponseEntity<JwtTokenDto> practice(final @RequestBody JwtTokenDto tokenDto) {

        try {
            Jwt jwt = jwtDecoder.decode(tokenDto.getRefreshToken());

            // サブジェクトからユーザー名を取得
            String username = jwt.getSubject();

            // ユーザー情報の取得
            UserDetails userDetails = customUserDetailsManager.loadUserByUsername(username);

            // 新しいトークンの生成
            JwtTokenDto jwtToken = jwtService.generateToken(userDetails);

            return ResponseEntity.status(HttpStatus.OK).body(jwtToken);

        } catch (Exception e) { // NOPMD AvoidGenericException
            saveStackTraceService.practice(e, LocalDate.now().getYear(), 0);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
