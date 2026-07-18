package net.seijishikin.jp.normalize.manage.kanrensha.dto.storage_file;

import java.io.Serializable;

import net.seijishikin.jp.normalize.common_tool.dto.DtoEntityInitialValueInterface;
import net.seijishikin.jp.normalize.common_tool.dto.FrameworkMessageAndResultDto;

/**
 * ファイルバイナリBas64テキストDto
 */
public class OneFileBlobResultDto extends FrameworkMessageAndResultDto // NOPMD DataClass
        implements Serializable, DtoEntityInitialValueInterface {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** ファイル名 */
    private String fileName = INIT_STRING;

    /** ファイル内容バイナリBase64 */
    private String fileContentBase64 = INIT_STRING;

    /**
     * ファイル名を取得する
     *
     * @return ファイル名
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * ファイル名を設定する
     *
     * @param fileName ファイル名
     */
    public void setFileName(final String fileName) {
        this.fileName = fileName;
    }

    /**
     * ファイル内容バイナリBase64を取得する
     *
     * @return ファイル内容バイナリBase64
     */
    public String getFileContentBase64() {
        return fileContentBase64;
    }

    /**
     * ファイル内容バイナリBase64を設定する
     *
     * @param fileContentBase64 ファイル内容バイナリBase64
     */
    public void setFileContentBase64(final String fileContentBase64) {
        this.fileContentBase64 = fileContentBase64;
    }

}
