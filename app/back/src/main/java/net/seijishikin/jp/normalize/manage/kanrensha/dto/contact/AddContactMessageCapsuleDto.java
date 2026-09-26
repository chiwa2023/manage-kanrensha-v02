package net.seijishikin.jp.normalize.manage.kanrensha.dto.contact;

import java.io.Serializable;

import net.seijishikin.jp.normalize.common_tool.dto.DtoEntityInitialValueInterface;
import net.seijishikin.jp.normalize.common_tool.dto.FrameworkCapsuleDto;

/**
 * 問い合わせメッセージ追加設定Dto
 */
public class AddContactMessageCapsuleDto extends FrameworkCapsuleDto // NOPMD DataClass
        implements Serializable, DtoEntityInitialValueInterface {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 運営者問い合わせコード */
    private Integer contactManagerCode = INIT_INTEGER;

    /**
     * 運営者問い合わせコードを取得する
     *
     * @return 運営者問い合わせコード
     */
    public Integer getContactManagerCode() {
        return contactManagerCode;
    }

    /**
     * 運営者問い合わせコードを設定する
     *
     * @param contactManagerCode 運営者問い合わせコード
     */
    public void setContactManagerCode(final Integer contactManagerCode) {
        this.contactManagerCode = contactManagerCode;
    }

    /** 問い合わせクローズ該非 */
    private Boolean isColsed = INIT_BOOLEAN;

    /**
     * 問い合わせクローズ該非を取得する
     *
     * @return 問い合わせクローズ該非
     */
    public Boolean getIsColsed() {
        return isColsed;
    }

    /**
     * 問い合わせクローズ該非を設定する
     *
     * @param isColsed 問い合わせクローズ該非
     */
    public void setIsColsed(final Boolean isColsed) {
        this.isColsed = isColsed;
    }

    /** 問い合わせタイトル */
    private String inquireTitle = INIT_STRING;

    /**
     * 問い合わせタイトルを取得する
     *
     * @return 問い合わせタイトル
     */
    public String getInquireTitle() {
        return inquireTitle;
    }

    /**
     * 問い合わせタイトルを設定する
     *
     * @param inquireTitle 問い合わせタイトル
     */
    public void setInquireTitle(final String inquireTitle) {
        this.inquireTitle = inquireTitle;
    }

    /** 問い合わせ内容 */
    private String inquireContent = INIT_STRING;

    /**
     * 問い合わせ内容を取得する
     *
     * @return 問い合わせ内容
     */
    public String getInquireContent() {
        return inquireContent;
    }

    /**
     * 問い合わせ内容を設定する
     *
     * @param inquireContent 問い合わせ内容
     */
    public void setInquireContent(final String inquireContent) {
        this.inquireContent = inquireContent;
    }

}
