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

    /** 処理区分 (CSVの2項目目) */
    private String process = INIT_STRING;

    /** 所在地県名 (CSVの9項目目) */
    private String prefectureName = INIT_STRING;

    /** 所在地市町村 (CSVの10項目目) */
    private String cityName = INIT_STRING;

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

    /**
     * 処理区分を取得する
     * 
     * @return 処理区分
     */
    public String getProcess() {
        return process;
    }

    /**
     * 処理区分を設定する
     * 
     * @param process 処理区分
     */
    public void setProcess(final String process) {
        this.process = process;
    }

    /**
     * 所在地県名を取得する
     * 
     * @return 所在地県名
     */
    public String getPrefectureName() {
        return prefectureName;
    }

    /**
     * 所在地県名を設定する
     * 
     * @param prefectureName 所在地県名
     */
    public void setPrefectureName(final String prefectureName) {
        this.prefectureName = prefectureName;
    }

    /**
     * 所在地市町村を取得する
     * 
     * @return 所在地市町村
     */
    public String getCityName() {
        return cityName;
    }

    /**
     * 所在地市町村を設定する
     * 
     * @param cityName 所在地市町村
     */
    public void setCityName(final String cityName) {
        this.cityName = cityName;
    }

}
