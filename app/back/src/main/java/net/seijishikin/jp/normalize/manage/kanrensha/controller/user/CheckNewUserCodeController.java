package net.seijishikin.jp.normalize.manage.kanrensha.controller.user;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import net.seijishikin.jp.normalize.manage.kanrensha.controller.PathRouteConstants;

import net.seijishikin.jp.normalize.manage.kanrensha.dto.user.NewComerDto;
import net.seijishikin.jp.normalize.manage.kanrensha.service.security.CheckNewUserCodeService;
import net.seijishikin.jp.normalize.manage.kanrensha.service.util.SaveStackTraceService;

/**
 * 新規コード確認Controller
 */
@RestController
@RequestMapping(PathRouteConstants.ROOT + "/add-user")
public class CheckNewUserCodeController {

    /** 新規コード確認Service */
    @Autowired
    private CheckNewUserCodeService checkNewUserCodeService;

    /** StackTrace保存Service */
    @Autowired
    private SaveStackTraceService saveStackTraceService;

    /**
     * 処理を行う
     *
     * @param newComerDto 新規ユーザDto
     * @return チェック結果
     */
    @PostMapping("/check-code")
    public ResponseEntity<NewComerDto> practice(final @RequestBody NewComerDto newComerDto) {
        // 比較日時はサーバ側で設定
        newComerDto.setLimitDateTime(LocalDateTime.now());
        try {
            return ResponseEntity.status(HttpStatus.OK).body(checkNewUserCodeService.practice(newComerDto));

        } catch (Exception exception) { // NOPMD 業務上の理由から積極的に許容
            // exceptionが出所不明のためstack traceを取る
            LocalDate now = LocalDate.now();
            saveStackTraceService.practice(exception, now.getYear(), 0);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new NewComerDto());
        }
    }

}
