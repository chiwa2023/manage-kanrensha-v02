package net.seijishikin.jp.normalize.manage.kanrensha.batch.address.postalcode;

import java.io.Serializable;

import net.seijishikin.jp.normalize.common_tool.dto.DtoEntityInitialValueInterface;

/**
 * 郵便番号CSV読み込みDto
 */
public class PostalCodeCsvJigyoushoDto implements Serializable, DtoEntityInitialValueInterface { // NOPMD DataClass

    /** serialId */
    private static final long serialVersionUID = 1L;

    /** 地方自治体コード */
    private String lgCode = INIT_STRING;

    /** 郵便番号7桁 */
    private String postalcode = INIT_STRING;

    /** 都道府県 */
    private String pref = INIT_STRING;

    /** 市区町村 */
    private String city = INIT_STRING;

    /** 原文書住所 */
    private String addressOrg = INIT_STRING;

    /** 番地等 */
    private String addressBlock = INIT_STRING;

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
     * 郵便番号7桁を取得する
     * 
     * @return 郵便番号7桁
     */
    public String getPostalcode() {
        return postalcode;
    }

    /**
     * 郵便番号7桁を設定する
     * 
     * @param postalcode 郵便番号7桁
     */
    public void setPostalcode(final String postalcode) {
        this.postalcode = postalcode;
    }

    /**
     * 都道府県を取得する
     * 
     * @return 都道府県
     */
    public String getPref() {
        return pref;
    }

    /**
     * 都道府県を設定する
     * 
     * @param pref 都道府県
     */
    public void setPref(final String pref) {
        this.pref = pref;
    }

    /**
     * 市区町村を取得する
     * 
     * @return 市区町村
     */
    public String getCity() {
        return city;
    }

    /**
     * 市区町村を設定する
     * 
     * @param city 市区町村
     */
    public void setCity(final String city) {
        this.city = city;
    }

    /**
     * 原文書住所を取得する
     *
     * @return 原文書住所
     */
    public String getAddressOrg() {
        return addressOrg;
    }

    /**
     * 原文書住所を設定する
     *
     * @param addressOrg 原文書住所
     */
    public void setAddressOrg(final String addressOrg) {
        this.addressOrg = addressOrg;
    }

    /**
     * 番地等を取得する
     * 
     * @return 番地等
     */
    public String getAddressBlock() {
        return addressBlock;
    }

    /**
     * 番地等を設定する
     * 
     * @param addressBlock 番地等
     */
    public void setAddressBlock(final String addressBlock) {
        this.addressBlock = addressBlock;
    }

}
