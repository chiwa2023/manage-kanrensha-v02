package net.seijishikin.jp.normalize.manage.kanrensha.logic.address.registory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 住所準備時のみの専用ログ書き出しLogic
 */
@Component
public class WriteLogAddressFormatLogic {

    /** エラーログ */
    public static final int ERROR = 1;
    /** 警告ログ */
    public static final int WARN = 2;
    /** 情報ログ */
    public static final int INFO = 3;

    /** 二重引用符 */
    private static final String QUOTE = "\"";

    /** カンマ */
    private static final String COMMA = ",";

    /** Logger */
    private final Logger log = LoggerFactory.getLogger(WriteLogAddressFormatLogic.class);

    /**
     * 処理を行う
     * 
     * @param state    ログ状態
     * @param messages メッセージ
     */
    public void practice(final int state, final String... messages) {

        switch (state) {
            case ERROR:
                log.error(this.createMessage(messages));
                break;
            case WARN:
                log.warn(this.createMessage(messages));
                break;
            case INFO:
                log.info(this.createMessage(messages));
                break;
            default:
                throw new IllegalArgumentException("エラー内容が不正です");
        }
    }

    private String createMessage(final String... messages) {

        StringBuilder builder = new StringBuilder(COMMA);
        for (String mess : messages) {
            builder.append(QUOTE).append(mess).append(QUOTE).append(COMMA);
        }

        return builder.toString();
    }

}
