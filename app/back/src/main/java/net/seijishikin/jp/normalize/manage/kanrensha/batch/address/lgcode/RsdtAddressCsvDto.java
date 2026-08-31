package net.seijishikin.jp.normalize.manage.kanrensha.batch.address.lgcode;

import java.io.Serializable;
import java.time.LocalDate;

import net.seijishikin.jp.normalize.common_tool.dto.DtoEntityInitialValueInterface;

/**
 * アドレス・ベース・レジストリ住居表示－住居Csv(mt_rsdtdsp_rsdt_prefxx.csv)格納Dto
 */
public class RsdtAddressCsvDto implements Serializable, DtoEntityInitialValueInterface { // NOPMD

    /** serialId */
    private static final long serialVersionUID = 1L;

    /** 地方自治体コード */
    private String lgCode = INIT_STRING;

    /** 町字コード */
    private String machiazaId = INIT_STRING;

    /** 街区コード */
    private String blkId = INIT_STRING;

    /** 住居lコード */
    private String rsdtId = INIT_STRING;

    /** 住居コード2 */
    private String rsdt2Id = INIT_STRING;

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

    /** 街区符号 */
    private String blkNum = INIT_STRING;

    /** 住居番号 */
    private String rsdtNum = INIT_STRING;

    /** 住居番号2 */
    private String rsdtNum2 = INIT_STRING;

    /** 基礎番号・住居番号区分 */
    private Integer basicRsdtDiv = INIT_INTEGER;

    /** 住居表示フラグ */
    private Integer rsdtAddrFlg = INIT_INTEGER;

    /** 住居表示方式コード */
    private Integer rsdtAddrMtdCode = INIT_INTEGER;

    /** 状態フラグ */
    private Integer statusFlg = INIT_INTEGER;

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
     * 街区コードを取得する
     *
     * @return 街区コード
     */
    public String getBlkId() {
        return blkId;
    }

    /**
     * 街区コードを設定する
     *
     * @param blkId 街区コード
     */
    public void setBlkId(final String blkId) {
        this.blkId = blkId;
    }

    /**
     * 住居lコードを取得する
     *
     * @return 住居lコード
     */
    public String getRsdtId() {
        return rsdtId;
    }

    /**
     * 住居lコードを設定する
     *
     * @param rsdtId 住居lコード
     */
    public void setRsdtId(final String rsdtId) {
        this.rsdtId = rsdtId;
    }

    /**
     * 住居コード2を取得する
     *
     * @return 住居コード2
     */
    public String getRsdt2Id() {
        return rsdt2Id;
    }

    /**
     * 住居コード2を設定する
     *
     * @param rsdt2Id 住居コード2
     */
    public void setRsdt2Id(final String rsdt2Id) {
        this.rsdt2Id = rsdt2Id;
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
     * 街区符号を取得する
     *
     * @return 街区符号
     */
    public String getBlkNum() {
        return blkNum;
    }

    /**
     * 街区符号を設定する
     *
     * @param blkNum 街区符号
     */
    public void setBlkNum(final String blkNum) {
        this.blkNum = blkNum;
    }

    /**
     * 住居番号を取得する
     *
     * @return 住居番号
     */
    public String getRsdtNum() {
        return rsdtNum;
    }

    /**
     * 住居番号を設定する
     *
     * @param rsdtNum 住居番号
     */
    public void setRsdtNum(final String rsdtNum) {
        this.rsdtNum = rsdtNum;
    }

    /**
     * 住居番号2を取得する
     *
     * @return 住居番号2
     */
    public String getRsdtNum2() {
        return rsdtNum2;
    }

    /**
     * 住居番号2を設定する
     *
     * @param rsdtNum2 住居番号2
     */
    public void setRsdtNum2(final String rsdtNum2) {
        this.rsdtNum2 = rsdtNum2;
    }

    /**
     * 基礎番号・住居番号区分を取得する
     *
     * @return 基礎番号・住居番号区分
     */
    public Integer getBasicRsdtDiv() {
        return basicRsdtDiv;
    }

    /**
     * 基礎番号・住居番号区分を設定する
     *
     * @param basicRsdtDiv 基礎番号・住居番号区分
     */
    public void setBasicRsdtDiv(final Integer basicRsdtDiv) {
        this.basicRsdtDiv = basicRsdtDiv;
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
     * 住居表示方式コードを取得する
     *
     * @return 住居表示方式コード
     */
    public Integer getRsdtAddrMtdCode() {
        return rsdtAddrMtdCode;
    }

    /**
     * 住居表示方式コードを設定する
     *
     * @param rsdtAddrMtdCode 住居表示方式コード
     */
    public void setRsdtAddrMtdCode(final Integer rsdtAddrMtdCode) {
        this.rsdtAddrMtdCode = rsdtAddrMtdCode;
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
     * 状態フラグを取得する
     *
     * @return 状態フラグ
     */
    public Integer getStatusFlg() {
        return statusFlg;
    }

    /**
     * 状態フラグを設定する
     *
     * @param statusFlg 状態フラグ
     */
    public void setStatusFlg(final Integer statusFlg) {
        this.statusFlg = statusFlg;
    }

}
