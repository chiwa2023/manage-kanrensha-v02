package net.seijishikin.jp.normalize.manage.kanrensha.dto.address_rsdt;

import java.io.Serializable;

import net.seijishikin.jp.normalize.common_tool.dto.DtoEntityInitialValueInterface;
import net.seijishikin.jp.normalize.common_tool.dto.FrameworkCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.storage_file.StorageFileDto;

/**
 * 住居差分作成準備Dto
 */
public class SabunPreparAddressRsdtCapsuleDto extends FrameworkCapsuleDto // NOPMD DataClass
        implements Serializable, DtoEntityInitialValueInterface {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 地番追加ファイルDto */
    private StorageFileDto parcelFileDto = new StorageFileDto();

    /** 住居ファイルDto */
    private StorageFileDto rsdtFileDto = new StorageFileDto();

    /**
     * 地番追加ファイルDtoを取得する
     * 
     * @return 地番追加ファイルDto
     */
    public StorageFileDto getParcelFileDto() {
        return parcelFileDto;
    }

    /**
     * 地番追加ファイルDtoを設定する
     * 
     * @param parcelFileDto 地番追加ファイルDto
     */
    public void setParcelFileDto(final StorageFileDto parcelFileDto) {
        this.parcelFileDto = parcelFileDto;
    }

    /**
     * 住居ファイルDtoを取得する
     * 
     * @return 住居ファイルDto
     */
    public StorageFileDto getRsdtFileDto() {
        return rsdtFileDto;
    }

    /**
     * 住居ファイルDtoを設定する
     * 
     * @param rsdtFileDto 住居ファイルDto
     */
    public void setRsdtFileDto(final StorageFileDto rsdtFileDto) {
        this.rsdtFileDto = rsdtFileDto;
    }

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
}
