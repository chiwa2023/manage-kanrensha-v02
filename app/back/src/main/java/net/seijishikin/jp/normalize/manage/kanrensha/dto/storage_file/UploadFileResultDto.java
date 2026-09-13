package net.seijishikin.jp.normalize.manage.kanrensha.dto.storage_file;

import java.io.Serializable;

import net.seijishikin.jp.normalize.common_tool.dto.FrameworkMessageAndResultDto;

/**
 * アップロードファイル結果Dto
 */
public class UploadFileResultDto extends FrameworkMessageAndResultDto //
        implements Serializable {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** ファイル格納Dto */
    private StorageFileDto storageFileDto;

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

}
