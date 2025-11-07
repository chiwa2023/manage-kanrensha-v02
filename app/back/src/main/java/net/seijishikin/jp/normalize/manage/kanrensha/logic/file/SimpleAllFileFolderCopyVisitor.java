package net.seijishikin.jp.normalize.manage.kanrensha.logic.file;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * 全ファイルディレクトリ再帰Visitor
 */
public class SimpleAllFileFolderCopyVisitor extends SimpleFileVisitor<Path> {

    /** 複写元フォルダ */
    private final Path sourceDir;

    /** 複写先フォルダ */
    private final Path targetDir;

    /**
     * コンストラクタ
     *
     * @param sourceDir 複写元フォルダ
     * @param targetDir 複写先フォルダ
     */
    public SimpleAllFileFolderCopyVisitor(final Path sourceDir, final Path targetDir) {
        super();
        
        this.sourceDir = sourceDir;
        this.targetDir = targetDir;
    }

    /**
     * ディレクトリ複写処理
     */
    @Override
    public FileVisitResult preVisitDirectory(final Path dir, final BasicFileAttributes attrs) throws IOException {
        Path newDirPath = targetDir.resolve(sourceDir.relativize(dir));
        Files.createDirectories(newDirPath);
        return FileVisitResult.CONTINUE;
    }

    /**
     * ファイル複写処理
     */
    @Override
    public FileVisitResult visitFile(final Path file, final BasicFileAttributes attrs) throws IOException {
        Files.copy(file, targetDir.resolve(sourceDir.relativize(file)), StandardCopyOption.REPLACE_EXISTING);
        return FileVisitResult.CONTINUE;
    }
}
