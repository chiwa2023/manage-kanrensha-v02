package net.seijishikin.jp.normalize.manage.kanrensha.dto.postal;

import java.io.Serializable;

import net.seijishikin.jp.normalize.common_tool.dto.FrameworkCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.storage_file.StorageFileDto;

/**
 * 郵便番号差分準備条件Dto
 */
public class PrepareSabunWktblPostalCodeCapsuleDto extends FrameworkCapsuleDto // NOPMD DataClass
        implements Serializable {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 差分追加ファイルDto */
    private StorageFileDto addFileDto = new StorageFileDto();

    /** 差分削除ファイルDto */
    private StorageFileDto deleteFileDto = new StorageFileDto();

    /**
     * 差分追加ファイルDtoを取得する
     * 
     * @return 差分追加ファイルDto
     */
    public StorageFileDto getAddFileDto() {
        return addFileDto;
    }

    /**
     * 差分追加ファイルDtoを設定する
     * 
     * @param addFileDto 差分追加ファイルDto
     */
    public void setAddFileDto(final StorageFileDto addFileDto) {
        this.addFileDto = addFileDto;
    }

    /**
     * 差分削除ファイルDtoを取得する
     * 
     * @return 差分削除ファイルDto
     */
    public StorageFileDto getDeleteFileDto() {
        return deleteFileDto;
    }

    /**
     * 差分削除ファイルDtoを設定する
     * 
     * @param deleteFileDto 差分削除ファイルDto
     */
    public void setDeleteFileDto(final StorageFileDto deleteFileDto) {
        this.deleteFileDto = deleteFileDto;
    }

}
