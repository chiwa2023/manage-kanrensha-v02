package net.seijishikin.jp.normalize.manage.kanrensha.logic.file;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * ファイル削除FileVisitor
 */
public class SimpleAllFileFolderDeleteVisitor extends SimpleFileVisitor<Path> {

    /**
     * ディレクトリ削除処理
     */
    @Override
    public FileVisitResult postVisitDirectory(final Path dir, final IOException exception) throws IOException {
        Files.deleteIfExists(dir);
        return FileVisitResult.CONTINUE;
    }

    /**
     * ファイル削除処理
     */
    @Override
    public FileVisitResult visitFile(final Path file, final BasicFileAttributes attrs) throws IOException {
        Files.deleteIfExists(file);
        return FileVisitResult.CONTINUE;
    }

}
