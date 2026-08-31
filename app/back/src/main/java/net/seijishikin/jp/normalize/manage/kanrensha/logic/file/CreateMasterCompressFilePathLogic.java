package net.seijishikin.jp.normalize.manage.kanrensha.logic.file;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * マスタZip圧縮用ファイルPathを生成する
 */
@Component
@ConfigurationProperties(prefix = "net.seijishikin.jp.normalize.kanrensha")
public class CreateMasterCompressFilePathLogic {

    /** propertiesからインジェクションされたフロントの共通ダンプCSV保存先 */
    private String frontDumpFolder;

    /**
     * フロントの共通ダンプCSV保存先を取得する
     *
     * @return フロントの共通ダンプCSV保存先
     */
    public String getFrontDumpFolder() {
        return frontDumpFolder;
    }

    /**
     * フロントの共通ダンプCSV保存先を設定する
     *
     * @param frontDumpFolder フロントの共通ダンプCSV保存先
     */
    public void setFrontDumpFolder(final String frontDumpFolder) {
        this.frontDumpFolder = frontDumpFolder;
    }

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
     * zipファイルPathを生成する
     *
     * @param fileName zipファイル名
     * @return zipファイルPath
     */
    public Path practiceZipFile(final String fileName) {

        return Paths.get(storageFolder, frontDumpFolder, fileName);
    }

    /**
     * 圧縮ファイルPathリストを生成する
     *
     * @param folder      生成ファイルのサブフォルダ
     * @param pathCorp    企業団体ファイル名
     * @param pathPerson  個人ファイル名
     * @param pathPoliOrg 政治団体ファイル名
     * @return 圧縮ファイルPathリスト
     */
    public List<Path> practiceFileList(final String folder, final String pathCorp, // NOPMD Argument Clear
            final String pathPerson, final String pathPoliOrg) {

        List<Path> listFiles = new ArrayList<>();
        Path pathFileCorp = Paths.get(storageFolder, frontDumpFolder, folder, pathCorp);
        listFiles.add(pathFileCorp);
        Path pathFilePerson = Paths.get(storageFolder, frontDumpFolder, folder, pathPerson);
        listFiles.add(pathFilePerson);
        Path pathFilePoliOrg = Paths.get(storageFolder, frontDumpFolder, folder, pathPoliOrg);
        listFiles.add(pathFilePoliOrg);

        return listFiles;

    }
}
