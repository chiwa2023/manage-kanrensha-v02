package net.seijishikin.jp.normalize.manage.kanrensha.dto.postal;

import java.io.Serializable;

import net.seijishikin.jp.normalize.common_tool.dto.DtoEntityInitialValueInterface;

/**
 * 郵便番号検索条件Dto
 */
public class PostalCodeCapsuleDto implements Serializable, DtoEntityInitialValueInterface { // NOPMD DataClass

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 郵便番号1 */
    private String postal1 = INIT_STRING;

    /** 郵便番号2 */
    private String postal2 = INIT_STRING;

    /** 行政区検索 */
    private Boolean isGyouseikuData = INIT_BOOLEAN;

    /** 選択された住所郵便番号まで */
    private Integer selectedPostal = INIT_INTEGER;

    /** 選択された住所番地まで */
    private String selectedBlock = INIT_STRING;

    /** 地方自治体コード */
    private String lgCode = INIT_STRING;

    /** 住居テーブルId */
    private Integer selectedRsdtId = INIT_INTEGER;

    /**
     * 郵便番号1を取得する
     *
     * @return 郵便番号1
     */
    public String getPostal1() {
        return postal1;
    }

    /**
     * 郵便番号1を設定する
     *
     * @param postal1 郵便番号1
     */
    public void setPostal1(final String postal1) {
        this.postal1 = postal1;
    }

    /**
     * 郵便番号2を取得する
     *
     * @return 郵便番号2
     */
    public String getPostal2() {
        return postal2;
    }

    /**
     * 郵便番号2を設定する
     *
     * @param postal2 郵便番号2
     */
    public void setPostal2(final String postal2) {
        this.postal2 = postal2;
    }

    /**
     * 行政区検索該否を取得する
     *
     * @return 行政区検索該否
     */
    public Boolean getIsGyouseikuData() {
        return isGyouseikuData;
    }

    /**
     * 行政区検索該否を設定する
     *
     * @param isGyouseikuData 行政区検索該否
     */
    public void setIsGyouseikuData(final Boolean isGyouseikuData) {
        this.isGyouseikuData = isGyouseikuData;
    }

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
     * 選択された住所郵便番号までを取得する
     *
     * @return 選択された住所郵便番号まで
     */
    public Integer getSelectedPostal() {
        return selectedPostal;
    }

    /**
     * 選択された住所郵便番号までを設定する
     *
     * @param selectedPostal 選択された住所郵便番号まで
     */
    public void setSelectedPostal(final Integer selectedPostal) {
        this.selectedPostal = selectedPostal;
    }

    /**
     * 選択された住所番地までを取得する
     *
     * @return 選択された住所番地まで
     */
    public String getSelectedBlock() {
        return selectedBlock;
    }

    /**
     * 選択された住所番地までを設定する
     *
     * @param selectedBlock 選択された住所番地まで
     */
    public void setSelectedBlock(final String selectedBlock) {
        this.selectedBlock = selectedBlock;
    }

    /**
     * 住居テーブルIdを指定する
     * 
     * @return 住居テーブルId
     */
    public Integer getSelectedRsdtId() {
        return selectedRsdtId;
    }

    /**
     * 住居テーブルIdを設定する
     * 
     * @param selectedRsdtId 住居テーブルId
     */
    public void setSelectedRsdtId(final Integer selectedRsdtId) {
        this.selectedRsdtId = selectedRsdtId;
    }

}
