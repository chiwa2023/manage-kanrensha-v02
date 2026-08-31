package net.seijishikin.jp.normalize.manage.kanrensha.dto.storage_file;

import java.io.Serializable;

import net.seijishikin.jp.normalize.common_tool.dto.FrameworkCapsuleDto;

/**
 * アップロードファイル内容Dto
 */
public class UploadContentCapsuleDto extends FrameworkCapsuleDto implements Serializable { // NOPMD DataClass

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** アップロードファイルDto */
    private UploadFileDto uploadFileDto = new UploadFileDto();

    /**
     * アップロードファイルDtoを取得する
     *
     * @return アップロードファイルDto
     */
    public UploadFileDto getUploadFileDto() {
        return uploadFileDto;
    }

    /**
     * アップロードファイルDtoを取得する
     *
     * @param uploadFileDto アップロードファイルDto
     */
    public void setUploadFileDto(final UploadFileDto uploadFileDto) {
        this.uploadFileDto = uploadFileDto;
    }

}
