package net.seijishikin.jp.normalize.manage.kanrensha.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.seijishikin.jp.normalize.common_tool.dto.FrameworkMessageAndResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.controller.PathRouteConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.sequrity.ResetPassswordCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.service.security.ResetPasswordChangeService;

/**
 * パスワードリセットController
 */
@RestController
@RequestMapping(PathRouteConstants.ROOT + "/reset-password")
public class ResetPasswordChangeController {

    /** パスワードリセットService */
    @Autowired
    private ResetPasswordChangeService resetPasswordChangeService;

    /** パスワードリセットService */
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 処理を行う
     * 
     * @param capsuleDto パスワードリセット処理条件Dto
     * @return レスポンス
     */
    @PostMapping("/save")
    public ResponseEntity<FrameworkMessageAndResultDto> practice(
            @RequestBody final ResetPassswordCapsuleDto capsuleDto) {

        try {
            // パスワード暗号化
            capsuleDto.setPassword(passwordEncoder.encode(capsuleDto.getPassword()));

            FrameworkMessageAndResultDto resultDto = resetPasswordChangeService.practice(capsuleDto);

            if (resultDto.getIsFailure()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(resultDto);

            } else {
                // MEMO パスワードリセットがログインページに飛ばす。
                // そのままログインしたいという要望があれば検討する(レスポンスの中身が変わるので結構な大工事)
                return ResponseEntity.status(HttpStatus.OK).body(resultDto);
            }

        } catch (Exception exception) {
            FrameworkMessageAndResultDto resultDto = new FrameworkMessageAndResultDto();
            resultDto.setIsFailure(true);
            resultDto.setMessage("システム例外が発生しました");

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resultDto);
        }
    }
}
