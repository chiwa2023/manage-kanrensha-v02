package net.seijishikin.jp.normalize.manage.kanrensha.constants;

import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * このサービスを配備している各種設定ディレクトリを取得する
 * Windowsマシンでgithubからc:\abcdeに展開しているならc:\abcdが配備ディレクトリ
 */
public final class GetCurrentResourcePath {

    private GetCurrentResourcePath() {

    }

    /**
     * 配下ディレクトリdirで指定された配備ディレクトリを返却する
     *
     * @param dir 配下ディレクトリ名
     * @return 絶対パス
     */
    public static String getBackSrcPath(final String dir) {

        return FileSystems.getDefault().getPath("src/" + dir).toAbsolutePath().toString();
    }

    /**
     * テストリソースを取得する
     *
     * @return テストリソースフォルダ
     */
    public static String getBackTestResourcePath() {

        return FileSystems.getDefault().getPath("src/test/resources/").toAbsolutePath().toString();
    }

    /**
     * テストリソースを取得する
     *
     * @return テストリソースフォルダ
     */
    public static String getBackTestFilePath() {

        return FileSystems.getDefault().getPath("src/test/java/").toAbsolutePath().toString();
    }

    /**
     * プロジェクトディレクトリを取得する
     * 
     * @param dir 取得したい配下ディレクトリ
     * @return Path
     */
    public static Path getProject(final String dir) {

        Path current = Paths.get("").toAbsolutePath();
        Path configDir = null;

        while (current != null) {
            Path candidate = current.resolve("manage-kanrensha-v02");
            if (Files.exists(candidate) && Files.isDirectory(candidate)) {
                configDir = candidate;
                break;
            }
            current = current.getParent();
        }

        if (configDir == null) {
            throw new IllegalStateException("projectディレクトリが見つかりませんでした");
        }
        return configDir.resolve(dir);
    }

}
