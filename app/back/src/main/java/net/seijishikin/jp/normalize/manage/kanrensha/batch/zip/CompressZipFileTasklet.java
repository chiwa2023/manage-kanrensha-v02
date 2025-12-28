package net.seijishikin.jp.normalize.manage.kanrensha.batch.zip;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

/**
 * 指定されたフォルダを一括してZip圧縮するTasklet
 */
@Component
public class CompressZipFileTasklet implements Tasklet, StepExecutionListener {

    /** 1回で処理するバイト数 */
    private static final int BUFFER_SIZE = 4096;

    /** 圧縮対象フォルダ */
    private String srcPath;

    /** 圧縮対象フォルダ */
    private String zipFilePath;

    /**
     * 起動条件を設定する
     *
     * @param stepExecution StepExecution
     */
    @BeforeStep
    @Override
    public void beforeStep(final StepExecution stepExecution) {
        String path = stepExecution.getJobParameters().getString("srcPath");
        srcPath = path + "/";
        zipFilePath = path + ".zip";
    }

    /**
     * 実行メソッド
     */
    @Override
    public RepeatStatus execute(final StepContribution contribution, final ChunkContext chunkContext) throws Exception {

        // 圧縮先ファイルの出力ストリームを作成
        try (FileOutputStream fileOutputStream = new FileOutputStream(zipFilePath); // NOPMD
                ZipOutputStream zipOutputStream = new ZipOutputStream(fileOutputStream)) {

            Path entryPath = Paths.get(srcPath);

            // フォルダ内のファイルとサブフォルダを再帰的にZIPに追加
            this.compressEntry(entryPath, entryPath.getFileName().toString(), zipOutputStream);
        }

        // 処理終了
        return RepeatStatus.FINISHED;
    }

    private void compressEntry(final Path targetPath, final String parentDirName, final ZipOutputStream zipOutputStream)
            throws IOException {

        // フォルダ内のフォルダとファイルを取得
        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(targetPath)) {
            for (Path filePath : directoryStream) {
                zipOutputStream.putNextEntry(this.createEntry(parentDirName, filePath));
                if (this.copyByte(zipOutputStream, filePath)) {
                    zipOutputStream.closeEntry();
                }
            }
        }
    }

    private ZipEntry createEntry(final String parentDirName, final Path filePath) {
        return new ZipEntry(parentDirName + "/" + filePath.getFileName().toString());
    }

    private boolean copyByte(final ZipOutputStream zipOutputStream, final Path filePath)
            throws FileNotFoundException, IOException {
        byte[] buffer = new byte[BUFFER_SIZE];
        int length;
        try (FileInputStream fileInputStream = new FileInputStream(filePath.toFile()); // NOPMD
                BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream)) {
            while ((length = bufferedInputStream.read(buffer, 0, buffer.length)) != -1) {
                zipOutputStream.write(buffer, 0, length);
            }
        }

        return true;
    }

}
