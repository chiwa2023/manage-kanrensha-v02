package net.seijishikin.jp.normalize.manage.kanrensha.batch.address.postalcode;

import java.io.Serializable;

import net.seijishikin.jp.normalize.common_tool.dto.DtoEntityInitialValueInterface;

/**
 * 編集用郵便番号住所Dto
 */
public class EditPostalCodeOneLineDto // NOPMD DataClass
        implements Serializable, DtoEntityInitialValueInterface {

    /** serialId */
    private static final long serialVersionUID = 1L;

    /** 地方自治体コード */
    private String lgCode = INIT_STRING;

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

    /** 郵便番号5桁 */
    private String postalcode5 = INIT_STRING;

    /**
     * 郵便番号5桁を取得する
     *
     * @return 郵便番号5桁
     */
    public String getPostalcode5() {
        return postalcode5;
    }

    /**
     * 郵便番号5桁を設定する
     *
     * @param postalcode5 郵便番号5桁
     */
    public void setPostalcode5(final String postalcode5) {
        this.postalcode5 = postalcode5;
    }

    /** 郵便番号7桁 */
    private String postalcode7 = INIT_STRING;

    /**
     * 郵便番号7桁を取得する
     *
     * @return 郵便番号7桁
     */
    public String getPostalcode7() {
        return postalcode7;
    }

    /**
     * 郵便番号7桁を設定する
     *
     * @param postalcode7 郵便番号7桁
     */
    public void setPostalcode7(final String postalcode7) {
        this.postalcode7 = postalcode7;
    }

    /** 県名カナ */
    private String prefNameKana = INIT_STRING;

    /**
     * 県名カナを取得する
     *
     * @return 県名カナ
     */
    public String getPrefNameKana() {
        return prefNameKana;
    }

    /**
     * 県名カナを設定する
     *
     * @param prefNameKana 県名カナ
     */
    public void setPrefNameKana(final String prefNameKana) {
        this.prefNameKana = prefNameKana;
    }

    /** 市区町名カナ */
    private String cityNameKana = INIT_STRING;

    /**
     * 市区町名カナを取得する
     *
     * @return 市区町名カナ
     */
    public String getCityNameKana() {
        return cityNameKana;
    }

    /**
     * 市区町名カナを設定する
     *
     * @param cityNameKana 市区町名カナ
     */
    public void setCityNameKana(final String cityNameKana) {
        this.cityNameKana = cityNameKana;
    }

    /** 原文書名カナ */
    private String orgNameKana = INIT_STRING;

    /**
     * 原文書名カナを取得する
     *
     * @return 原文書名カナ
     */
    public String getOrgNameKana() {
        return orgNameKana;
    }

    /**
     * 原文書名カナを設定する
     *
     * @param orgNameKana 原文書名カナ
     */
    public void setOrgNameKana(final String orgNameKana) {
        this.orgNameKana = orgNameKana;
    }

    /** 県名 */
    private String prefName = INIT_STRING;

    /**
     * 県名を取得する
     *
     * @return 県名
     */
    public String getPrefName() {
        return prefName;
    }

    /**
     * 県名を設定する
     *
     * @param prefName 県名
     */
    public void setPrefName(final String prefName) {
        this.prefName = prefName;
    }

    /** 市区町名 */
    private String cityName = INIT_STRING;

    /**
     * 市区町名を取得する
     *
     * @return 市区町名
     */
    public String getCityName() {
        return cityName;
    }

    /**
     * 市区町名を設定する
     *
     * @param cityName 市区町名
     */
    public void setCityName(final String cityName) {
        this.cityName = cityName;
    }

    /** 原文書名 */
    private String orgName = INIT_STRING;

    /**
     * 原文書名を取得する
     *
     * @return 原文書名
     */
    public String getOrgName() {
        return orgName;
    }

    /**
     * 原文書名を設定する
     *
     * @param orgName 原文書名
     */
    public void setOrgName(final String orgName) {
        this.orgName = orgName;
    }

    /** 属性1フラグ */
    private String flgProp1 = INIT_STRING;

    /**
     * 属性1フラグを取得する
     *
     * @return 属性1フラグ
     */
    public String getFlgProp1() {
        return flgProp1;
    }

    /**
     * 属性1フラグを設定する
     *
     * @param flgProp1 属性1フラグ
     */
    public void setFlgProp1(final String flgProp1) {
        this.flgProp1 = flgProp1;
    }

    /** 属性2フラグ */
    private String flgProp2 = INIT_STRING;

    /**
     * 属性2フラグを取得する
     *
     * @return 属性2フラグ
     */
    public String getFlgProp2() {
        return flgProp2;
    }

    /**
     * 属性2フラグを設定する
     *
     * @param flgProp2 属性2フラグ
     */
    public void setFlgProp2(final String flgProp2) {
        this.flgProp2 = flgProp2;
    }

    /** 属性3フラグ */
    private String flgProp3 = INIT_STRING;

    /**
     * 属性3フラグを取得する
     *
     * @return 属性3フラグ
     */
    public String getFlgProp3() {
        return flgProp3;
    }

    /**
     * 属性3フラグを設定する
     *
     * @param flgProp3 属性3フラグ
     */
    public void setFlgProp3(final String flgProp3) {
        this.flgProp3 = flgProp3;
    }

    /** 属性4フラグ */
    private String flgProp4 = INIT_STRING;

    /**
     * 属性4フラグを取得する
     *
     * @return 属性4フラグ
     */
    public String getFlgProp4() {
        return flgProp4;
    }

    /**
     * 属性4フラグを設定する
     *
     * @param flgProp4 属性4フラグ
     */
    public void setFlgProp4(final String flgProp4) {
        this.flgProp4 = flgProp4;
    }

    /** 更新表示フラグ */
    private String flgKoushin = INIT_STRING;

    /**
     * 更新表示フラグを取得する
     *
     * @return 更新表示フラグ
     */
    public String getFlgKoushin() {
        return flgKoushin;
    }

    /**
     * 更新表示フラグを設定する
     *
     * @param flgKoushin 更新表示フラグ
     */
    public void setFlgKoushin(final String flgKoushin) {
        this.flgKoushin = flgKoushin;
    }

    /** 変更理由フラグ */
    private String flgHenkouRiyu = INIT_STRING;

    /**
     * 変更理由フラグを取得する
     *
     * @return 変更理由フラグ
     */
    public String getFlgHenkouRiyu() {
        return flgHenkouRiyu;
    }

    /**
     * 変更理由フラグを設定する
     *
     * @param flgHenkouRiyu 変更理由フラグ
     */
    public void setFlgHenkouRiyu(final String flgHenkouRiyu) {
        this.flgHenkouRiyu = flgHenkouRiyu;
    }

}
