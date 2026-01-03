package net.seijishikin.jp.normalize.manage.kanrensha.controller.user;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.seijishikin.jp.normalize.manage.kanrensha.controller.PathRouteConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.user.NewComerDto;
import net.seijishikin.jp.normalize.manage.kanrensha.service.security.PublishNewUserCodeService;
import net.seijishikin.jp.normalize.manage.kanrensha.service.util.SaveStackTraceService;

/**
 * 新規ユーザ用仮コード発行Controller
 */
@RestController
@RequestMapping(PathRouteConstants.ROOT + "/add-user")
public class PublishNewUserCodeController {

    /** 新規ユーザ用メール疎通確認コード発行Service */
    @Autowired
    private PublishNewUserCodeService publishNewUserCodeService;

    /** StackTrace保存Service */
    @Autowired
    private SaveStackTraceService saveStackTraceService;

    /**
     * 処理を行う
     *
     * @param newComerDto 新規ユーザDto
     * @return 値が入力された新規ユーザDto
     */
    @PostMapping("/publish-code")
    public ResponseEntity<NewComerDto> practice(final @RequestBody NewComerDto newComerDto) {

        // 有効期限2時間(サーバ側で設定)
        newComerDto.setLimitDateTime(LocalDateTime.now().plusHours(2));
        
        try {
            NewComerDto resultDto = publishNewUserCodeService.practice(newComerDto);

            if (Objects.isNull(resultDto)) {
                // コード発行、メール送信関連で事故が発生時
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new NewComerDto());
            } else {
                // 正常動作
                return ResponseEntity.status(HttpStatus.OK).body(resultDto);
            }

        } catch (IOException ioException) {
            // メールテンプレート呼び出し不可
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new NewComerDto());

        } catch (Exception exception) { // NOPMD Avoid Catch General Exception
            // exceptionが出所不明のためstack traceを取る
            LocalDate now = LocalDate.now();
            saveStackTraceService.practice(exception, now.getYear(), 0);

            // その他の例外全般
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new NewComerDto());
        }

    }

}
