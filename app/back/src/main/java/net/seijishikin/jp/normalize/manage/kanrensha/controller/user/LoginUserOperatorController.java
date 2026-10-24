package net.seijishikin.jp.normalize.manage.kanrensha.controller.user;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.sequrity.JwtTokenDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.sequrity.LoginUserCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.sequrity.LoginUserResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.UserPasskeyCredentialEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.UserPasskeyCredentialRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.service.passkey.PreAuthTokenService;
import net.seijishikin.jp.normalize.manage.kanrensha.service.passkey.WebAuthnChallengeService;
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

    /** パスキークレデンシャルRepository */
    @Autowired
    private UserPasskeyCredentialRepository userPasskeyCredentialRepository;

    /** Pre-Auth Token管理Service */
    @Autowired
    private PreAuthTokenService preAuthTokenService;

    /** WebAuthn Challenge管理Service */
    @Autowired
    private WebAuthnChallengeService webAuthnChallengeService;

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

            // ユーザ情報を取得する
            LeastUserDto userDto = getLeastUserByMailService.practice(capsuleDto.getUserId(), authentication);

            // パスキー登録有無の確認
            boolean hasPasskey = userDto.getUserPersonCode() != null
                    && userPasskeyCredentialRepository.countByUserPersonCode(userDto.getUserPersonCode()) > 0;

            if (hasPasskey) {
                // パスキー登録ユーザの場合、2FA移行 (Pre-Auth TokenとChallenge発行、本JWTは未発行)
                SecurityContextHolder.clearContext();

                String preAuthToken = preAuthTokenService.createPreAuthToken(
                        userDto.getUserPersonCode(), capsuleDto.getUserId(), authentication, userDto);
                String challenge = webAuthnChallengeService.generateAuthenticationChallenge(preAuthToken);

                List<UserPasskeyCredentialEntity> credList = userPasskeyCredentialRepository
                        .findByUserPersonCode(userDto.getUserPersonCode());
                List<String> allowCredentials = credList.stream().map(UserPasskeyCredentialEntity::getCredentialId)
                        .toList();

                LoginUserResultDto resultDto = new LoginUserResultDto();
                resultDto.setUserDto(userDto);
                resultDto.setRequiresPasskey(true);
                resultDto.setPreAuthToken(preAuthToken);
                resultDto.setChallenge(challenge);
                resultDto.setAllowCredentials(allowCredentials);
                resultDto.setJwtTokenDto(null);

                return ResponseEntity.status(HttpStatus.OK).body(resultDto);
            }

            // トークン生成処理 (通常ログイン)
            JwtTokenDto jwtToken = jwtService.generateToken(authentication);

            LoginUserResultDto resultDto = new LoginUserResultDto();
            resultDto.setJwtTokenDto(jwtToken);
            resultDto.setUserDto(userDto);
            resultDto.setRequiresPasskey(false);

            return ResponseEntity.status(HttpStatus.OK).body(resultDto);

        } catch (LockedException e) {
            return ResponseEntity.status(HttpStatus.LOCKED).build();

        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        } catch (Exception exception) { // NOPMD 業務的な理由から積極的に許容
            LocalDateTime now = LocalDateTime.now();
            saveStackTraceService.practice(exception, now.getYear(), 0);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
