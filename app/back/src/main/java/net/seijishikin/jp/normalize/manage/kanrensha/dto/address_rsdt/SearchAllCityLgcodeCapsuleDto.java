package net.seijishikin.jp.normalize.manage.kanrensha.dto.address_rsdt;

import java.io.Serializable;

import net.seijishikin.jp.normalize.common_tool.dto.DtoEntityInitialValueInterface;

/**
 * 地方自治体コード地名取得Dto
 */
public class SearchAllCityLgcodeCapsuleDto implements Serializable, DtoEntityInitialValueInterface {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 地方自治体コード */
    private String lgCode = INIT_STRING;

    /** 値5桁検索フラグ */
    private Boolean isSearch5Digit = INIT_BOOLEAN;

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
     * 値5桁検索フラグを取得する
     * 
     * @return 値5桁検索フラグ
     */
    public Boolean getIsSearch5Digit() {
        return isSearch5Digit;
    }

    /**
     * 値5桁検索フラグを設定する
     * 
     * @param isSearch5Digit 値5桁検索フラグ
     */
    public void setIsSearch5Digit(final Boolean isSearch5Digit) {
        this.isSearch5Digit = isSearch5Digit;
    }

}
