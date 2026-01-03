package net.seijishikin.jp.normalize.manage.kanrensha.service.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * ログ書き出しService
 */
@Component
public class WriteLogService {

    /** Logger */
    private final Logger log = LoggerFactory.getLogger(WriteLogService.class);

    /**
     * エラーログ書き出し処理を行う
     *
     * @param throwable throwable
     */
    public void practiceError(final String mess, final Throwable throwable) {

        log.error(mess, throwable);
    }

    /**
     * メッセージを排出する
     *
     * @param mess メッセージ
     */
    public void writeInfo(final String mess) {

        log.info("---info:" + mess);
    }
    
    /**
     * メッセージを排出する
     *
     * @param mess メッセージ
     */
    public void writeWarn(final String mess) {

        log.warn("---warn:" + mess);
    }


}
