package net.seijishikin.jp.normalize.manage.kanrensha.dto.houjin_no;

import java.io.Serializable;

import net.seijishikin.jp.normalize.common_tool.dto.DtoEntityInitialValueInterface;

/**
 * 法人番号情報DTO
 */
public class HoujinNoDto // NOPMD DataClass
        implements Serializable, DtoEntityInitialValueInterface {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 法人番号 (CSVの1項目目) */
    private String houjinNo = INIT_STRING;

    /** 商号又は名称 (CSVの6項目目) */
    private String houjinName = INIT_STRING;

    /** 法人種別 (CSVの8項目目) */
    private String kind = INIT_STRING;

    /**
     * 法人番号を取得する
     * 
     * @return 法人番号
     */
    public String getHoujinNo() {
        return houjinNo;
    }

    /**
     * 法人番号を設定する
     * 
     * @param houjinNo 法人番号
     */
    public void setHoujinNo(final String houjinNo) {
        this.houjinNo = houjinNo;
    }

    /**
     * 商号又は名称を取得する
     * 
     * @return 商号又は名称
     */
    public String getHoujinName() {
        return houjinName;
    }

    /**
     * 商号又は名称を設定する
     * 
     * @param houjinName 商号又は名称
     */
    public void setHoujinName(final String houjinName) {
        this.houjinName = houjinName;
    }

    /**
     * 法人種別を取得する
     * 
     * @return 法人種別
     */
    public String getKind() {
        return kind;
    }

    /**
     * 法人種別を設定する
     * 
     * @param kind 法人種別
     */
    public void setKind(final String kind) {
        this.kind = kind;
    }
}
