package net.seijishikin.jp.normalize.manage.kanrensha.dto.storage_file;

import java.io.Serializable;

/**
 * アップロードファイルDto
 */
public class UploadFileDto implements Serializable { // NOPMD DataClass

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 初期データ(String) */
    private static final String INIT_String = "";

    /** ファイル名 */
    private String fileName = INIT_String;

    /** 文字コード */
    private String charset = INIT_String;

    /** ファイル内容(Base64) */
    private String fileContent = INIT_String;

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
     * 文字コードを取得する
     *
     * @return 文字コード
     */
    public String getCharset() {
        return charset;
    }

    /**
     * 文字コードを設定する
     *
     * @param charset 文字コード
     */
    public void setCharset(final String charset) {
        this.charset = charset;
    }

    /**
     * ファイル内容(Base64)を取得する
     *
     * @return ファイル内容(Base64)
     */
    public String getFileContent() {
        return fileContent;
    }

    /**
     * ファイル内容(Base64)を設定する
     *
     * @param fileContent ファイル内容(Base64)
     */
    public void setFileContent(final String fileContent) {
        this.fileContent = fileContent;
    }

}
