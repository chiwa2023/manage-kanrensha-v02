package net.seijishikin.jp.normalize.manage.kanrensha.controller.passkey;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.controller.PathRouteConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.passkey.PasskeyAuthenticationChallengeRequestDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.passkey.PasskeyAuthenticationChallengeResponseDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.passkey.PasskeyAuthenticationVerifyRequestDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.passkey.PasskeyRegistrationChallengeDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.passkey.PasskeyRegistrationResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.passkey.PasskeyRegistrationVerifyRequestDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.passkey.PasskeySummaryDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.sequrity.LoginUserResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.user.GetLoginLeastUserLogic;
import net.seijishikin.jp.normalize.manage.kanrensha.service.passkey.PasskeyAuthenticationService;
import net.seijishikin.jp.normalize.manage.kanrensha.service.passkey.PasskeyManageService;
import net.seijishikin.jp.normalize.manage.kanrensha.service.passkey.PasskeyRegistrationService;

/**
 * WebAuthn (パスキー) API Controller
 */
@RestController
@RequestMapping(PathRouteConstants.ROOT + "/passkey")
public class PasskeyController {

    /** ログイン中ユーザ取得Logic */
    @Autowired
    private GetLoginLeastUserLogic getLoginLeastUserLogic;

    /** パスキー登録Service */
    @Autowired
    private PasskeyRegistrationService passkeyRegistrationService;

    /** パスキー認証Service */
    @Autowired
    private PasskeyAuthenticationService passkeyAuthenticationService;

    /** パスキー管理Service */
    @Autowired
    private PasskeyManageService passkeyManageService;

    /**
     * パスキー登録Challengeを生成する (ログイン済みユーザ用)
     *
     * @return 登録用Challenge Dto
     */
    @PostMapping("/register/challenge")
    public ResponseEntity<PasskeyRegistrationChallengeDto> registerChallenge() {
        LeastUserDto user = getLoginLeastUserLogic.practice();
        if (user.getUserPersonCode() == null || user.getUserPersonCode() == 0) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        PasskeyRegistrationChallengeDto challengeDto = passkeyRegistrationService.generateRegistrationChallenge(
                user.getUserPersonCode(), user.getUserPersonName(), user.getUserPersonName());
        return ResponseEntity.ok(challengeDto);
    }

    /**
     * パスキー登録Attestationを検証し登録する (ログイン済みユーザ用)
     *
     * @param requestDto 検証リクエストDto
     * @param origin     リクエスト元Originヘッダ
     * @return 登録結果Dto
     */
    @PostMapping("/register/verify")
    public ResponseEntity<PasskeyRegistrationResultDto> registerVerify(
            @RequestBody final PasskeyRegistrationVerifyRequestDto requestDto,
            @RequestHeader(value = "Origin", required = false) final String origin) {

        LeastUserDto user = getLoginLeastUserLogic.practice();
        if (user.getUserPersonCode() == null || user.getUserPersonCode() == 0) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        try {
            PasskeyRegistrationResultDto result = passkeyRegistrationService.verifyAndRegister(user.getUserPersonCode(),
                    requestDto, origin);
            return ResponseEntity.ok(result);
        } catch (IllegalArgumentException | IllegalStateException e) {
            PasskeyRegistrationResultDto errorResult = new PasskeyRegistrationResultDto();
            errorResult.setSuccess(false);
            errorResult.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResult);
        }
    }

    /**
     * 2段階認証用Challengeを要求する (公開エンドポイント、Pre-Auth Tokenで照合)
     *
     * @param requestDto 要求Dto
     * @return 認証Challenge応答Dto
     */
    @PostMapping("/authenticate/challenge")
    public ResponseEntity<PasskeyAuthenticationChallengeResponseDto> authenticateChallenge(
            @RequestBody final PasskeyAuthenticationChallengeRequestDto requestDto) {

        try {
            PasskeyAuthenticationChallengeResponseDto responseDto = passkeyAuthenticationService
                    .generateAuthenticationChallenge(requestDto.getPreAuthToken());
            return ResponseEntity.ok(responseDto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    /**
     * パスキーAssertionを検証し、正式なJWTを発行する (公開エンドポイント)
     *
     * @param requestDto 検証リクエストDto
     * @param origin     リクエスト元Originヘッダ
     * @return ログイン結果Dto
     */
    @PostMapping("/authenticate/verify")
    public ResponseEntity<LoginUserResultDto> authenticateVerify(
            @RequestBody final PasskeyAuthenticationVerifyRequestDto requestDto,
            @RequestHeader(value = "Origin", required = false) final String origin) {

        try {
            LoginUserResultDto resultDto = passkeyAuthenticationService.verifyAndAuthenticate(requestDto, origin);
            return ResponseEntity.ok(resultDto);
        } catch (SecurityException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    /**
     * 登録済みパスキー一覧を取得する (ログイン済みユーザ用)
     *
     * @return パスキーサマリーリスト
     */
    @GetMapping("/list")
    public ResponseEntity<List<PasskeySummaryDto>> listPasskeys() {
        LeastUserDto user = getLoginLeastUserLogic.practice();
        if (user.getUserPersonCode() == null || user.getUserPersonCode() == 0) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        List<PasskeySummaryDto> list = passkeyManageService.listPasskeys(user.getUserPersonCode());
        return ResponseEntity.ok(list);
    }

    /**
     * パスキーを削除する (ログイン済みユーザ用)
     *
     * @param passkeyId 削除対象のパスキー内部ID
     * @return 削除結果
     */
    @DeleteMapping("/{passkeyId}")
    public ResponseEntity<Void> deletePasskey(@PathVariable final Long passkeyId) {
        LeastUserDto user = getLoginLeastUserLogic.practice();
        if (user.getUserPersonCode() == null || user.getUserPersonCode() == 0) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        boolean deleted = passkeyManageService.deletePasskey(passkeyId, user.getUserPersonCode());
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
