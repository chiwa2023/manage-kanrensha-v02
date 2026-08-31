package net.seijishikin.jp.normalize.manage.kanrensha.dto.postal;

import java.io.Serializable;

import net.seijishikin.jp.normalize.common_tool.dto.DtoEntityInitialValueInterface;
import net.seijishikin.jp.normalize.common_tool.dto.FrameworkCapsuleDto;

/**
 * 郵便番号移動Dto]
 */
public class MovePostalCodeCapsuleDto extends FrameworkCapsuleDto // NOPMD DataClass
        implements Serializable, DtoEntityInitialValueInterface {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 地方自治体コード */
    private String lgCode = INIT_STRING;

    /** 郵便番号1旧 */
    private String postalOld1 = INIT_STRING;

    /** 郵便番号2旧 */
    private String postalOld2 = INIT_STRING;

    /** 郵便番号1新 */
    private String postalNew1 = INIT_STRING;

    /** 郵便番号2新 */
    private String postalNew2 = INIT_STRING;

    /**
     * 地方自治体コードを取得する
     * 
     * @return 地方自治体コード
     */
    public String getLgCode() {
        return lgCode;
    }

    /**
     * 地方自治体コードを設定する
     * 
     * @param lgCode 地方自治体コード
     */
    public void setLgCode(final String lgCode) {
        this.lgCode = lgCode;
    }

    /**
     * 郵便番号1旧を取得する
     * 
     * @return 郵便番号1旧
     */
    public String getPostalOld1() {
        return postalOld1;
    }

    /**
     * 郵便番号1旧を設定する
     * 
     * @param postalOld1 郵便番号1旧
     */
    public void setPostalOld1(final String postalOld1) {
        this.postalOld1 = postalOld1;
    }

    /**
     * 郵便番号2旧を取得する
     * 
     * @return 郵便番号2旧
     */
    public String getPostalOld2() {
        return postalOld2;
    }

    /**
     * 郵便番号2旧を設定する
     * 
     * @param postalOld2 郵便番号2旧
     */
    public void setPostalOld2(final String postalOld2) {
        this.postalOld2 = postalOld2;
    }

    /**
     * 郵便番号1新を取得する
     * 
     * @return 郵便番号1新
     */
    public String getPostalNew1() {
        return postalNew1;
    }

    /**
     * 郵便番号1新を設定する
     * 
     * @param postalNew1 郵便番号1新
     */
    public void setPostalNew1(final String postalNew1) {
        this.postalNew1 = postalNew1;
    }

    /**
     * 郵便番号2新を取得する
     * 
     * @return 郵便番号2新
     */
    public String getPostalNew2() {
        return postalNew2;
    }

    /**
     * 郵便番号2新を設定する
     * 
     * @param postalNew2 郵便番号2新
     */
    public void setPostalNew2(final String postalNew2) {
        this.postalNew2 = postalNew2;
    }

}
