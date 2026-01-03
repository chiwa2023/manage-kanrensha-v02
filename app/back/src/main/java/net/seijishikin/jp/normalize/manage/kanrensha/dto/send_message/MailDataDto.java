package net.seijishikin.jp.normalize.manage.kanrensha.dto.send_message;

import java.io.Serializable;

import org.springframework.mail.SimpleMailMessage;

import net.seijishikin.jp.normalize.common_tool.dto.DtoEntityInitialValueInterface;

/**
 * 各ユーザに送信するデータを格納するDto
 */
public class MailDataDto // NOPMD DataClass
        implements Serializable, DtoEntityInitialValueInterface {

    /** SerialId */
    private static final long serialVersionUID = 1L;

    /** メールメッセージDto */
    private SimpleMailMessage simpleMailMessage;

    /** 項目名称取得Id */
    private Integer sendAlertMailId = INIT_INTEGER;

    /** 項目名称取得同一識別コード */
    private Integer sendAlertMailCode = INIT_INTEGER;

    /** 再送信該否 */
    private Boolean isRepeat = INIT_BOOLEAN;

    /**
     * メール送信メッセージを取得する
     *
     * @return メール送信メッセージ
     */
    public SimpleMailMessage getSimpleMailMessage() {
        return simpleMailMessage;
    }

    /**
     * メール送信メッセージを設定する
     *
     * @param simpleMailMessage メール送信メッセージ
     */
    public void setSimpleMailMessage(final SimpleMailMessage simpleMailMessage) {
        this.simpleMailMessage = simpleMailMessage;
    }

    /**
     * 項目名称取得Idを取得する
     *
     * @return 項目名称取得Id
     */
    public Integer getSendAlertMailId() {
        return sendAlertMailId;
    }

    /**
     * 項目名称取得Idを設定する
     *
     * @param sendAlertMailId 項目名称取得Id
     */
    public void setSendAlertMailId(final Integer sendAlertMailId) {
        this.sendAlertMailId = sendAlertMailId;
    }

    /**
     * 項目名称取得同一識別コードを取得する
     *
     * @return 項目名称取得同一識別コード
     */
    public Integer getSendAlertMailCode() {
        return sendAlertMailCode;
    }

    /**
     * 項目名称取得同一識別コードを設定する
     *
     * @param sendAlertMailCode 項目名称取得同一識別コード
     */
    public void setSendAlertMailCode(final Integer sendAlertMailCode) {
        this.sendAlertMailCode = sendAlertMailCode;
    }

    /**
     * 再送信該否
     * 
     * @return 再送信該否
     */
    public Boolean getIsRepeat() { // NOPMD
        return isRepeat;
    }

    /**
     * 再送信該否
     * 
     * @param isRepeat 再送信該否
     */
    public void setIsRepeat(final Boolean isRepeat) {
        this.isRepeat = isRepeat;
    }

}
