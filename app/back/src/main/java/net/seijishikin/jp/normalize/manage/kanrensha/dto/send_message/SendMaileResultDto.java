package net.seijishikin.jp.normalize.manage.kanrensha.dto.send_message;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import net.seijishikin.jp.normalize.common_tool.dto.FrameworkMessageAndResultDto;

/**
 * メール送信結果を格納する
 */
public class SendMaileResultDto extends FrameworkMessageAndResultDto implements Serializable { // NOPMD DataClass

    /** SerialId */
    private static final long serialVersionUID = 1L;

    /** 送信失敗者 */
    private List<MailDataDto> listFailure = new ArrayList<>();

    /** 送信成功者 */
    private List<MailDataDto> listSuccess = new ArrayList<>();

    /**
     * 送信失敗者リストを取得する
     *
     * @return 送信失敗者リスト
     */
    public List<MailDataDto> getListFailure() {
        return listFailure;
    }

    /**
     * 送信失敗者リストを設定する
     *
     * @param listFailure 送信失敗者リスト
     */
    public void setListFailure(final List<MailDataDto> listFailure) {
        this.listFailure = listFailure;
    }

    /**
     * 送信成功者を取得する
     * 
     * @return 送信成功者
     */
    public List<MailDataDto> getListSuccess() {
        return listSuccess;
    }

    /**
     * 送信成功者を設定する
     * 
     * @param listSuccess 送信成功者
     */
    public void setListSuccess(final List<MailDataDto> listSuccess) {
        this.listSuccess = listSuccess;
    }

}
