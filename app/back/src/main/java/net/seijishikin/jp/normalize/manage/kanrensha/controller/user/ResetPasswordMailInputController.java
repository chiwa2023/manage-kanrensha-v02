package net.seijishikin.jp.normalize.manage.kanrensha.controller.user;

import java.time.LocalDate;

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
import net.seijishikin.jp.normalize.manage.kanrensha.service.security.ResetPasswordMailInputService;
import net.seijishikin.jp.normalize.manage.kanrensha.service.util.SaveStackTraceService;

/**
 * 指定したメールアドレスへ認証コード送信Controller
 */
@RestController
@RequestMapping(PathRouteConstants.ROOT + "/reset-password")
public class ResetPasswordMailInputController {

    /** 指定したメールアドレスへ認証コード送信Service */
    @Autowired
    private ResetPasswordMailInputService resetPasswordMailInputService;

    /** StackTrace保存Service */
    @Autowired
    private SaveStackTraceService saveStackTraceService;

    /**
     * 処理を行う
     * 
     * @param capsuleDto パスワードリセット実行条件Dto
     * @return レスポンス
     */
    @PostMapping("/send-code")
    public ResponseEntity<FrameworkMessageAndResultDto> practice(
            @RequestBody final ResetPassswordCapsuleDto capsuleDto) {

        try {
            FrameworkMessageAndResultDto resultDto = resetPasswordMailInputService.practice(capsuleDto);

            if (resultDto.getIsFailure()) {
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(resultDto);
            } else {
                return ResponseEntity.status(HttpStatus.OK).body(resultDto);
            }
        } catch (Exception exception) { // NOPMD
            saveStackTraceService.practice(exception, LocalDate.now().getYear(), 0);
            FrameworkMessageAndResultDto resultDto = new FrameworkMessageAndResultDto();
            resultDto.setIsFailure(true);
            resultDto.setMessage("システム例外が発生しました");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resultDto);
        }
    }

}
