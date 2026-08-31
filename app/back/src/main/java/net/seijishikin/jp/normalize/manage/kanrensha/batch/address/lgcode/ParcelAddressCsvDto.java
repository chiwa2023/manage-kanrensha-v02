package net.seijishikin.jp.normalize.manage.kanrensha.batch.address.lgcode;

import java.io.Serializable;
import java.time.LocalDate;

import net.seijishikin.jp.normalize.common_tool.dto.DtoEntityInitialValueInterface;

/**
 * アドレス・ベース・レジストリ地番住所CsvDto
 */
public class ParcelAddressCsvDto implements Serializable,DtoEntityInitialValueInterface { // NOPMD

    /** serialId */
    private static final long serialVersionUID = 1L;

    /** 地方自治体コード */
    private String lgCode = INIT_STRING;

    /** 町字コード */
    private String machiazaId = INIT_STRING;

    /** 地番コード */
    private String prcId = INIT_STRING;

    /** 市区町村名 */
    private String city = INIT_STRING;

    /** 政令市区名 */
    private String ward = INIT_STRING;

    /** 大字・町名 */
    private String oazaCho = INIT_STRING;

    /** 丁目名 */
    private String chome = INIT_STRING;

    /** 小字名 */
    private String koaza = INIT_STRING;

    /** 同一町字識別情報 */
    private String machiazaDist = INIT_STRING;

    /** 地番1 */
    private String prcNum1 = INIT_STRING;

    /** 地番2 */
    private String prcNum2 = INIT_STRING;

    /** 地番3 */
    private String prcNum3 = INIT_STRING;

    /** 住居表示フラグ */
    private Integer rsdtAddrFlg = INIT_INTEGER;

    /** 地番レコード区分フラグ */
    private Integer prcRecFlg = INIT_INTEGER;

    /** 地番区域コード */
    private Integer prcAreaCode = INIT_INTEGER;

    /** 適用開始日 */
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
     * 町字コードを取得する
     *
     * @return 町字コード
     */
    public String getMachiazaId() {
        return machiazaId;
    }

    /**
     * 町字コードを設定する
     *
     * @param machiazaId 町字コード
     */
    public void setMachiazaId(final String machiazaId) {
        this.machiazaId = machiazaId;
    }

    /**
     * 地番コードを取得する
     *
     * @return 地番コード
     */
    public String getPrcId() {
        return prcId;
    }

    /**
     * 地番コードを設定する
     *
     * @param prcId 地番コード
     */
    public void setPrcId(final String prcId) {
        this.prcId = prcId;
    }

    /**
     * 市区町村名を取得する
     *
     * @return 市区町村名
     */
    public String getCity() {
        return city;
    }

    /**
     * 市区町村名を設定する
     *
     * @param city 市区町村名
     */
    public void setCity(final String city) {
        this.city = city;
    }

    /**
     * 政令市区名を取得する
     *
     * @return 政令市区名
     */
    public String getWard() {
        return ward;
    }

    /**
     * 政令市区名を設定する
     *
     * @param ward 政令市区名
     */
    public void setWard(final String ward) {
        this.ward = ward;
    }

    /**
     * 大字・町名を取得する
     *
     * @return 大字・町名
     */
    public String getOazaCho() {
        return oazaCho;
    }

    /**
     * 大字・町名を設定する
     *
     * @param oazaCho 大字・町名
     */
    public void setOazaCho(final String oazaCho) {
        this.oazaCho = oazaCho;
    }

    /**
     * 丁目名を取得する
     *
     * @return 丁目名
     */
    public String getChome() {
        return chome;
    }

    /**
     * 丁目名を設定する
     *
     * @param chome 丁目名
     */
    public void setChome(final String chome) {
        this.chome = chome;
    }

    /**
     * 小字名を取得する
     *
     * @return 小字名
     */
    public String getKoaza() {
        return koaza;
    }

    /**
     * 小字名を設定する
     *
     * @param koaza 小字名
     */
    public void setKoaza(final String koaza) {
        this.koaza = koaza;
    }

    /**
     * 同一町字識別情報を取得する
     *
     * @return 同一町字識別情報
     */
    public String getMachiazaDist() {
        return machiazaDist;
    }

    /**
     * 同一町字識別情報を設定する
     *
     * @param machiazaDist 同一町字識別情報
     */
    public void setMachiazaDist(final String machiazaDist) {
        this.machiazaDist = machiazaDist;
    }

    /**
     * 地番1を取得する
     *
     * @return 地番1
     */
    public String getPrcNum1() {
        return prcNum1;
    }

    /**
     * 地番1を設定する
     *
     * @param prcNum1 地番1
     */
    public void setPrcNum1(final String prcNum1) {
        this.prcNum1 = prcNum1;
    }

    /**
     * 地番2を取得する
     *
     * @return 地番2
     */
    public String getPrcNum2() {
        return prcNum2;
    }

    /**
     * 地番2を設定する
     *
     * @param prcNum2 地番2
     */
    public void setPrcNum2(final String prcNum2) {
        this.prcNum2 = prcNum2;
    }

    /**
     * 地番3を取得する
     *
     * @return 地番3
     */
    public String getPrcNum3() {
        return prcNum3;
    }

    /**
     * 地番3を設定する
     *
     * @param prcNum3 地番3
     */
    public void setPrcNum3(final String prcNum3) {
        this.prcNum3 = prcNum3;
    }

    /**
     * 住居表示フラグを取得する
     *
     * @return 住居表示フラグ
     */
    public Integer getRsdtAddrFlg() {
        return rsdtAddrFlg;
    }

    /**
     * 住居表示フラグを設定する
     *
     * @param rsdtAddrFlg 住居表示フラグ
     */
    public void setRsdtAddrFlg(final Integer rsdtAddrFlg) {
        this.rsdtAddrFlg = rsdtAddrFlg;
    }

    /**
     * 地番レコード区分フラグを取得する
     *
     * @return 地番レコード区分フラグ
     */
    public Integer getPrcRecFlg() {
        return prcRecFlg;
    }

    /**
     * 地番レコード区分フラグを設定する
     *
     * @param prcRecFlg 地番レコード区分フラグ
     */
    public void setPrcRecFlg(final Integer prcRecFlg) {
        this.prcRecFlg = prcRecFlg;
    }

    /**
     * 地番区域コードを取得する
     *
     * @return 地番区域コード
     */
    public Integer getPrcAreaCode() {
        return prcAreaCode;
    }

    /**
     * 地番区域コードを設定する
     *
     * @param prcAreaCode 地番区域コード
     */
    public void setPrcAreaCode(final Integer prcAreaCode) {
        this.prcAreaCode = prcAreaCode;
    }

    /**
     * 適用開始日を取得する
     *
     * @return 適用開始日
     */
    public LocalDate getEffectDate() {
        return effectDate;
    }

    /**
     * 適用開始日を設定する
     *
     * @param effectDate 適用開始日
     */
    public void setEffectDate(final LocalDate effectDate) {
        this.effectDate = effectDate;
    }

    /**
     * 廃止日を取得する
     * 
     * @return 廃止日
     */
    public LocalDate getAbolishDate() {
        return abolishDate;
    }

    /**
     * 廃止日を設定する
     * 
     * @param abolishDate 廃止日
     */
    public void setAbolishDate(final LocalDate abolishDate) {
        this.abolishDate = abolishDate;
    }

}
