package net.seijishikin.jp.normalize.manage.kanrensha.dto.add_xml;

import java.io.Serializable;

import net.seijishikin.jp.normalize.common_tool.dto.FrameworkCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.storage_file.StorageFileDto;

/**
 * Csvファイルから登録条件Dto
 */
public class RegistDataByXmlCapsuleDto extends FrameworkCapsuleDto implements Serializable { // NOPMD DataClass

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** ストレージ保存ファイルDto */
    private StorageFileDto storageFileDto = new StorageFileDto();

    /**
     * ストレージ保存ファイルDtoを取得する
     *
     * @return ストレージ保存ファイルDto
     */
    public StorageFileDto getStorageFileDto() {
        return storageFileDto;
    }

    /**
     * ストレージ保存ファイルDtoを設定する
     *
     * @param storageFileDto ストレージ保存ファイルDto
     */
    public void setStorageFileDto(final StorageFileDto storageFileDto) {
        this.storageFileDto = storageFileDto;
    }

    /** 備考1項目解析該否 */
    private Boolean isNotBiko = false;

    /** 名前住所2項目解析該否 */
    private Boolean isNotNameAddress = false;

    /**
     * 備考1項目解析該否を取得する
     *
     * @return 備考1項目解析該否
     */
    public Boolean getIsNotBiko() {
        return isNotBiko;
    }

    /**
     * 備考1項目解析該否を設定する
     *
     * @param isNotBiko 備考1項目解析該否
     */
    public void setIsNotBiko(final Boolean isNotBiko) {
        this.isNotBiko = isNotBiko;
    }

    /**
     * 名前住所2項目解析該否を取得する
     *
     * @return 名前住所2項目解析該否
     */
    public Boolean getIsNotNameAddress() {
        return isNotNameAddress;
    }

    /**
     * 名前住所2項目解析該否を設定する
     *
     * @param isNotNameAddress 名前住所2項目解析該否
     */
    public void setIsNotNameAddress(final Boolean isNotNameAddress) {
        this.isNotNameAddress = isNotNameAddress;
    }

}
