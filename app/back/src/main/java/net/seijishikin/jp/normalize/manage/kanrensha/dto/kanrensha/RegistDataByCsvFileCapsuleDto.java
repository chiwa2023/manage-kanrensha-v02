package net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha;

import java.io.Serializable;

import net.seijishikin.jp.normalize.common_tool.dto.FrameworkCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.storage_file.StorageFileDto;

/**
 * Csvファイルから登録条件Dto
 */
public class RegistDataByCsvFileCapsuleDto extends FrameworkCapsuleDto // NOPMD DataClass
        implements Serializable {

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

}
