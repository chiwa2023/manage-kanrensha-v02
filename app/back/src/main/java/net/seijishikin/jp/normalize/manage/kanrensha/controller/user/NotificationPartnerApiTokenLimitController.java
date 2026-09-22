package net.seijishikin.jp.normalize.manage.kanrensha.controller.user;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.seijishikin.jp.normalize.common_tool.dto.FrameworkMessageAndResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.controller.PathRouteConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.user.NotifyPartnerApiLimitCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.user.ValidateAuthoraizeUserDetailLogic;
import net.seijishikin.jp.normalize.manage.kanrensha.service.user.NotificationPartnerApiTokenLimitAsyncService;
import net.seijishikin.jp.normalize.manage.kanrensha.service.util.SaveStackTraceService;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.ConvertDatetimeToLocalUtil;

/**
 * APIパートナー長期トークン期限切れ通知Controller
 */
@RestController
@RequestMapping(PathRouteConstants.ROOT + "/partner-api")
public class NotificationPartnerApiTokenLimitController {

    /** APIパートナー長期トークン期限切通知非同期Service */
    @Autowired
    private NotificationPartnerApiTokenLimitAsyncService notificationPartnerApiTokenLimitAsyncService;

    /** StackTrace保存Service */
    @Autowired
    private SaveStackTraceService saveStackTraceService;

    /** ユーザ妥当性検証Logic */
    @Autowired
    private ValidateAuthoraizeUserDetailLogic validateAuthoraizeUserDetailLogic;

    /**
     * 処理を行う
     *
     * @param capsuleDto ユーザログインDto
     * @return トークン
     */
    @PostMapping("/notify-limit")
    public ResponseEntity<FrameworkMessageAndResultDto> practice(
            final @RequestBody NotifyPartnerApiLimitCapsuleDto capsuleDto) {

        FrameworkMessageAndResultDto resultDto = new FrameworkMessageAndResultDto();
        try {
            // ユーザチェック
            validateAuthoraizeUserDetailLogic.practice(capsuleDto.getUserDto());

            LocalDateTime createDatetime = LocalDateTime.now();
            // front側から確認日付が指定されている場合は補正
            capsuleDto.setCheckDate(ConvertDatetimeToLocalUtil.practice(capsuleDto.getCheckDate()));

            notificationPartnerApiTokenLimitAsyncService.practice(createDatetime, capsuleDto);

            resultDto.setMessage("処理を開始しました(タスク登録はありません)");

            return ResponseEntity.status(HttpStatus.OK).body(resultDto);

        } catch (UsernameNotFoundException exception) {
            resultDto.setIsFailure(true);
            resultDto.setMessage("tokenとユーザ(userDto)が不整合です");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(resultDto);
        } catch (Exception exception) { // NOPMD
            saveStackTraceService.practice(exception, LocalDate.now().getYear(), 0);
            resultDto.setIsFailure(true);
            resultDto.setMessage(FrameworkMessageAndResultDto.MESSAGE_INTERNAL_ERROR);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resultDto);
        }
    }

}
