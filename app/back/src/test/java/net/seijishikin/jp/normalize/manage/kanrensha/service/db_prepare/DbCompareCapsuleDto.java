package net.seijishikin.jp.normalize.manage.kanrensha.service.db_prepare;

import java.io.Serializable;
import java.nio.file.Path;

/**
 * Db比較処理条件Dto
 */
public class DbCompareCapsuleDto implements Serializable { // NOPMD DataClass

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** ローカルDbテーブル一覧結果テキストパス */
    private Path pathLocalDbTableList;

    /** AwsDbテーブル一覧結果テキストパス */
    private Path pathAwsTableList;

    /** テーブルDDLパス */
    private String pathTableDdl;

    /** アップロードSQL格納パス */
    private Path pathUpload;

    /**
     * ローカルDbテーブル一覧結果テキストパスを取得する
     * 
     * @return ローカルDbテーブル一覧結果テキストパス
     */
    public Path getPathLocalDbTableList() {
        return pathLocalDbTableList;
    }

    /**
     * ローカルDbテーブル一覧結果テキストパスを設定する
     * 
     * @param pathLocalDbTableList ローカルDbテーブル一覧結果テキストパス
     */
    public void setPathLocalDbTableList(final Path pathLocalDbTableList) {
        this.pathLocalDbTableList = pathLocalDbTableList;
    }

    /**
     * AwsDbテーブル一覧結果テキストパスを取得する
     * 
     * @return AwsDbテーブル一覧結果テキストパス
     */
    public Path getPathAwsTableList() {
        return pathAwsTableList;
    }

    /**
     * AwsDbテーブル一覧結果テキストパスを設定する
     * 
     * @param pathAwsTableList AwsDbテーブル一覧結果テキストパス
     */
    public void setPathAwsTableList(final Path pathAwsTableList) {
        this.pathAwsTableList = pathAwsTableList;
    }

    /**
     * テーブルDDLパスを取得する
     * 
     * @return テーブルDDLパス
     */
    public String getPathTableDdl() {
        return pathTableDdl;
    }

    /**
     * テーブルDDLパスを設定する
     * 
     * @param pathTableDdl テーブルDDLパス
     */
    public void setPathTableDdl(final String pathTableDdl) {
        this.pathTableDdl = pathTableDdl;
    }

    /**
     * アップロードSQL格納パスを取得する
     * 
     * @return アップロードSQL格納パス
     */
    public Path getPathUpload() {
        return pathUpload;
    }

    /**
     * アップロードSQL格納パスを設定する
     * 
     * @param pathUpload アップロードSQL格納パス
     */
    public void setPathUpload(final Path pathUpload) {
        this.pathUpload = pathUpload;
    }
}
