package net.seijishikin.jp.normalize.manage.kanrensha.dto.storage_file;

import java.io.Serializable;

import net.seijishikin.jp.normalize.common_tool.dto.FrameworkMessageAndResultDto;

/**
 * 公式発行XML頭出しDto
 */
public class LookAheadPublishXmlResultDto extends FrameworkMessageAndResultDto // NOPMD DataClass
        implements Serializable {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** ファイル格納Dto */
    private StorageFileDto storageFileDto;

    /** 作成アプリ名 */
    private String app;

    /** 作成アプリバージョン */
    private String version;

    /** 団体名称 */
    private String dantaiName;

    /** 報告年 */
    private Integer houkokuNen;

    /**
     * ファイル格納Dtoを取得する
     *
     * @return ファイル格納Dto
     */
    public StorageFileDto getStorageFileDto() {
        return storageFileDto;
    }

    /**
     * ファイル格納Dtoを設定する
     *
     * @param storageFileDto ファイル格納Dto
     */
    public void setStorageFileDto(final StorageFileDto storageFileDto) {
        this.storageFileDto = storageFileDto;
    }

    /**
     * 作成アプリ名を取得する
     *
     * @return 作成アプリ名
     */
    public String getApp() {
        return app;
    }

    /**
     * 作成アプリ名を設定する
     *
     * @param app 作成アプリ名
     */
    public void setApp(final String app) {
        this.app = app;
    }

    /**
     * 作成アプリバージョンを取得する
     *
     * @return 作成アプリバージョン
     */
    public String getVersion() {
        return version;
    }

    /**
     * 作成アプリバージョンを設定する
     *
     * @param version 作成アプリバージョン
     */
    public void setVersion(final String version) {
        this.version = version;
    }

    /**
     * 団体名称を取得する
     *
     * @return 団体名称
     */
    public String getDantaiName() {
        return dantaiName;
    }

    /**
     * 団体名称を設定する
     *
     * @param dantaiName 団体名称
     */
    public void setDantaiName(final String dantaiName) {
        this.dantaiName = dantaiName;
    }

    /**
     * 報告年を取得する
     *
     * @return 報告年
     */
    public Integer getHoukokuNen() {
        return houkokuNen;
    }

    /**
     * 報告年を設定する
     *
     * @param houkokuNen 報告年
     */
    public void setHoukokuNen(final Integer houkokuNen) {
        this.houkokuNen = houkokuNen;
    }

}
