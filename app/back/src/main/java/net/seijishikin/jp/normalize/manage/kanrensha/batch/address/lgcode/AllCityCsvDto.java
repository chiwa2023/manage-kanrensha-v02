package net.seijishikin.jp.normalize.manage.kanrensha.batch.address.lgcode;

import java.io.Serializable;
import java.time.LocalDate;

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

    /** 住所 */
    private String addressName = INIT_STRING;

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
     * 住所を取得する
     *
     * @return 住所
     */
    public String getAddressName() {
        return addressName;
    }

    /**
     * 住所を設定する
     *
     * @param addressName 住所
     */
    public void setAddressName(final String addressName) {
        this.addressName = addressName;
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
