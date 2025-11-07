package net.seijishikin.jp.normalize.manage.kanrensha.logic.file;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * このプロジェクトでの絶対パスLogic
 */
@Component
@ConfigurationProperties(prefix = "net.seijishikin.jp.normalize.kanrensha")
public class GetAbsolutePathLogic {

    /** propertiesからインジェクションされた最上位保存フォルダ絶対パス */
    private String storageFolder;

    /**
     * 最上位保存フォルダ絶対パスを取得する
     *
     * @return 最上位保存フォルダ絶対パス
     */
    public String getStorageFolder() {
        return storageFolder;
    }

    /**
     * 最上位保存フォルダ絶対パスを設定する
     *
     * @param storageFolder 最上位保存フォルダ絶対パス
     */
    public void setStorageFolder(final String storageFolder) {
        this.storageFolder = storageFolder;
    }

    /**
     * 処理を行う
     *
     * @param dir      保存ディレクトリ
     * @param fileName ファイル名
     * @return 絶対パス
     */
    public Path practice(final String dir, final String fileName) {

        return Paths.get(storageFolder, dir, fileName);
    }

}
