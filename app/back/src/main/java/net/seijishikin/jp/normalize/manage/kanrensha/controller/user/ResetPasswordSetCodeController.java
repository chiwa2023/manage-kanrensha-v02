package net.seijishikin.jp.normalize.manage.kanrensha.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.seijishikin.jp.normalize.common_tool.dto.FrameworkMessageAndResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.controller.PathRouteConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.sequrity.ResetPassswordCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.service.security.ResetPasswordSetCodeService;

/**
 * 送信した認証コード確認Controller
 */
@RestController
@RequestMapping(PathRouteConstants.ROOT + "/reset-password")
public class ResetPasswordSetCodeController {

    /** 送信した認証コード確認Service */
    @Autowired
    private ResetPasswordSetCodeService resetPasswordSetCodeService;

    /**
     * 処理を行う
     * 
     * @param capsuleDto パスワードリセット処理条件Dto
     * @return レスポンス
     */
    @PostMapping("/check-code")
    public ResponseEntity<FrameworkMessageAndResultDto> practice(
            @RequestBody final ResetPassswordCapsuleDto capsuleDto) {

        try {
            FrameworkMessageAndResultDto resultDto = resetPasswordSetCodeService.practice(capsuleDto);

            if (resultDto.getIsFailure()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(resultDto);
            } else {
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
