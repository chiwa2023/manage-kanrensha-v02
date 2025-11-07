package net.seijishikin.jp.normalize.manage.kanrensha.logic.file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.stereotype.Component;

/**
 * すべてのファイル・フォルダを再帰複写Logic
 */
@Component
public class CopyFolderWalkTreeAllLogic {

    /**
     * 処理を行う
     *
     * @param sourceDir 複写元フォルダ
     * @param targetDir 複写先フォルダ
     * @return 複写先フォルダPath
     * @throws IOException ファイル書き込み不可例外
     */
    public Path practice(final Path sourceDir, final Path targetDir) throws IOException {

        return Files.walkFileTree(sourceDir, new SimpleAllFileFolderCopyVisitor(sourceDir, targetDir));
    }

}
