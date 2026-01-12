package net.seijishikin.jp.normalize.manage.kanrensha.controller.user;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.seijishikin.jp.normalize.common_tool.dto.FrameworkMessageAndResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.controller.PathRouteConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.sequrity.RefreshPasswordCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.UserPersonEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.UserPersonRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.service.user.RefreshPasswordService;
import net.seijishikin.jp.normalize.manage.kanrensha.service.util.SaveStackTraceService;

/**
 * パスワード更新Controller
 */
@RestController
@RequestMapping(PathRouteConstants.ROOT + "/edit-user")
public class RefreshPasswordController {

    /** パスワード更新Service */
    @Autowired
    private RefreshPasswordService refreshPasswordService;

    /** ユーザ人物Respoitory */
    @Autowired
    private UserPersonRepository userPersonRepository;

    /** 認証プロバイダ */
    @Autowired
    private AuthenticationManager authenticationManager;

    /** StackTrace保存Service */
    @Autowired
    private SaveStackTraceService saveStackTraceService;

    /**
     * 処理を行う
     *
     * @param capsuleDto パスワード更新Dto
     * @return 処理結果
     */
    @PostMapping("/refresh-password")
    public ResponseEntity<FrameworkMessageAndResultDto> practice(
            @RequestBody final RefreshPasswordCapsuleDto capsuleDto) {

        Optional<UserPersonEntity> optional = userPersonRepository.findById(capsuleDto.getUserDto().getUserPersonId());

        FrameworkMessageAndResultDto resultDto = new FrameworkMessageAndResultDto();
        if (optional.isEmpty()) {
            resultDto.setIsFailure(true);
            resultDto.setMessage("指定されたユーザが存在しません");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(resultDto);
        }

        String email = optional.get().getEmail();
        capsuleDto.setEmail(email);

        try {
            // ログイン処理
            authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(email, capsuleDto.getOldPassword()));

        } catch (BadCredentialsException e) {
            // 古いパスワード指定誤り
            resultDto.setIsFailure(true);
            resultDto.setMessage("現在のパスワードに不正な値が入力されています");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(resultDto);
        } catch (Exception exception) {// NOPMD AvoidGenericException
            saveStackTraceService.practice(exception, LocalDate.now().getYear(), 0);
            resultDto.setIsFailure(true);
            resultDto.setMessage("現在のパスワードの再確認中に予期せぬ例外が発生しました");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(resultDto);
        }

        // パスワード更新
        resultDto = refreshPasswordService.practice(capsuleDto);

        if (resultDto.getIsFailure()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(resultDto);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(resultDto);
        }

    }

}
