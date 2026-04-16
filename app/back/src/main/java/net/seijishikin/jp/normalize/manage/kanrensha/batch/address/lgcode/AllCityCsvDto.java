package net.seijishikin.jp.normalize.manage.kanrensha.batch.address.lgcode;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Column;
import net.seijishikin.jp.normalize.common_tool.dto.DtoEntityInitialValueInterface;

/**
 * 市区町村Csv格納Dto
 */
public class AllCityCsvDto // NOPMD DataClass
        implements Serializable, DtoEntityInitialValueInterface {

    /** serialId */
    private static final long serialVersionUID = 1L;

    /** 地方自治体コード */
    private String lgCode = INIT_STRING;


    /** 住所かな */
    private String addressNameKana = INIT_STRING;
    
    /** 適用日 */
    private LocalDate effectDate = INIT_DATE;

    /** 廃止日 */
    private LocalDate abolishDate = INIT_DATE;

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
     * 住所かなを取得する
     *
     * @return 住所かな
     */
    public String getAddressNameKana() {
        return addressNameKana;
    }

    /**
     * 住所かなを設定する
     *
     * @param addressNameKana 住所かな
     */
    public void setAddressNameKana(final String addressNameKana) {
        this.addressNameKana = addressNameKana;
    }
    /** 県名称 */
    @Column(name = "pref")
    private String pref = INIT_STRING;

    /**
     * 県名称を取得する
     *
     * @return 県名称
     */
    public String getPref() {
        return pref;
    }

    /**
     * 県名称を設定する
     *
     * @param pref 県名称
     */
    public void setPref(final String pref) {
        this.pref = pref;
    }

    /** 郡名称 */
    @Column(name = "county")
    private String county = INIT_STRING;

    /**
     * 郡名称を取得する
     *
     * @return 郡名称
     */
    public String getCounty() {
        return county;
    }

    /**
     * 郡名称を設定する
     *
     * @param county 郡名称
     */
    public void setCounty(final String county) {
        this.county = county;
    }

    /** 市名称 */
    @Column(name = "city")
    private String city = INIT_STRING;

    /**
     * 市名称を取得する
     *
     * @return 市名称
     */
    public String getCity() {
        return city;
    }

    /**
     * 市名称を設定する
     *
     * @param city 市名称
     */
    public void setCity(final String city) {
        this.city = city;
    }

    /** 特別区名称 */
    @Column(name = "ward")
    private String ward = INIT_STRING;

    /**
     * 特別区名称を取得する
     *
     * @return 特別区名称
     */
    public String getWard() {
        return ward;
    }

    /**
     * 特別区名称を設定する
     *
     * @param ward 特別区名称
     */
    public void setWard(final String ward) {
        this.ward = ward;
    }

    /**
     * 適用日を取得する
     *
     * @return 適用日
     */
    public LocalDate getEffectDate() {
        return effectDate;
    }

    /**
     * 適用日を設定する
     *
     * @param effectDate 適用日
     */
    public void setEffectDate(final LocalDate effectDate) {
        this.effectDate = effectDate;
    }

    /**
     * 廃止日
     * 
     * @return 廃止日
     */
    public LocalDate getAbolishDate() {
        return abolishDate;
    }

    /**
     * 廃止日
     * 
     * @param abolishDate 廃止日
     */
    public void setAbolishDate(final LocalDate abolishDate) {
        this.abolishDate = abolishDate;
    }

}
