package net.seijishikin.jp.normalize.manage.kanrensha.logic.file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.stereotype.Component;

/**
 * 再帰ファイル削除Logic
 */
@Component
public class DeleteFolderWalkTreeAllLogic {

    /**
     * 処理を行う
     *
     * @param sourceDir 削除フォルダ
     * @return 複写先フォルダPath
     * @throws IOException ファイル書き込み不可例外
     */
    public Path practice(final Path sourceDir) throws IOException {
        Path pathResult = Files.walkFileTree(sourceDir, new SimpleAllFileFolderDeleteVisitor());
        // 最後に削除された指定したディレクトリは復元する
        Files.createDirectories(pathResult);
        return pathResult;
    }

}
