package net.seijishikin.jp.normalize.manage.kanrensha.controller.user;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.seijishikin.jp.normalize.manage.kanrensha.dto.sequrity.JwtTokenDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.sequrity.LoginUserCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.sequrity.LoginUserResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.service.security.GetLeastUserByMailService;
import net.seijishikin.jp.normalize.manage.kanrensha.service.security.JwtService;
import net.seijishikin.jp.normalize.manage.kanrensha.service.util.SaveStackTraceService;
import net.seijishikin.jp.normalize.manage.kanrensha.controller.PathRouteConstants;

/**
 * ログイン処理Controller ユーザ情報に基づき、JWTトークンとUser情報を変返却する
 */
@RestController
@RequestMapping(PathRouteConstants.ROOT)
public class LoginUserOperatorController {

    /** 認証プロバイダ */
    @Autowired
    private AuthenticationManager authenticationManager;

    /** JWTトークン生成Service */
    @Autowired
    private JwtService jwtService;

    /** ユーザ最低限Service */
    @Autowired
    private GetLeastUserByMailService getLeastUserByMailService;

    /** ユーザ最低限Service */
    @Autowired
    private SaveStackTraceService saveStackTraceService;

    /**
     * 処理を行う
     *
     * @param capsuleDto ログイン情報Dto
     * @return ログイン結果Dto
     */
    @PostMapping("/login")
    public ResponseEntity<LoginUserResultDto> practice(final @RequestBody LoginUserCapsuleDto capsuleDto) {

        try {
            // ログイン処理
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(capsuleDto.getUserId(), capsuleDto.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // トークン生成処理
            JwtTokenDto jwtToken = jwtService.generateToken(authentication);

            // ユーザ情報は別途取得する
            LoginUserResultDto resultDto = new LoginUserResultDto();
            resultDto.setJwtTokenDto(jwtToken);
            resultDto.setUserDto(getLeastUserByMailService.practice(capsuleDto.getUserId(), authentication));

            return ResponseEntity.status(HttpStatus.OK).body(resultDto);

        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        } catch (Exception exception) { // NOPMD 業務的な理由から積極的に許容
            LocalDateTime now = LocalDateTime.now();
            saveStackTraceService.practice(exception, now.getYear(), 0);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
