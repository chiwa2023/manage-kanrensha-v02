package net.seijishikin.jp.normalize.manage.kanrensha.logic.file;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import net.seijishikin.jp.normalize.manage.kanrensha.constants.GetCurrentResourcePath;


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

    /** 全体保存ディレクトリ */
    private final String pathSaved;

    /**
     * コンストラクタ
     */
    public CreateMasterCompressFilePathLogic() {
        pathSaved = Paths.get(GetCurrentResourcePath.getBackSrcPath("")).getParent().getParent().toString();

    }

    /**
     * zipファイルPathを生成する
     *
     * @param fileName zipファイル名
     * @return zipファイルPath
     */
    public Path practiceZipFile(final String fileName) {

        return Paths.get(pathSaved, frontDumpFolder, fileName);
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
        Path pathFileCorp = Paths.get(pathSaved, frontDumpFolder, folder, pathCorp);
        listFiles.add(pathFileCorp);
        Path pathFilePerson = Paths.get(pathSaved, frontDumpFolder, folder, pathPerson);
        listFiles.add(pathFilePerson);
        Path pathFilePoliOrg = Paths.get(pathSaved, frontDumpFolder, folder, pathPoliOrg);
        listFiles.add(pathFilePoliOrg);

        return listFiles;

    }
}
