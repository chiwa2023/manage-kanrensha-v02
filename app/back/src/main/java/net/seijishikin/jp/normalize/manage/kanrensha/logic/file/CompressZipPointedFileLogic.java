package net.seijishikin.jp.normalize.manage.kanrensha.logic.file;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.springframework.stereotype.Component;

/**
 * 指定ファイルをすべてルート直下でzip圧縮Logic
 */
@Component
public class CompressZipPointedFileLogic {

    /** 1回で処理するバイト数 */
    private static final int BUFFER_SIZE = 4096;

    /**
     * 処理を行う
     *
     * @param pathZipFile          zipファイルPath
     * @param listCompressFilePath 圧縮対象ファイルPathリスト
     * @throws IOException ファイル書き込み不可
     */
    public boolean practice(final Path pathZipFile, final List<Path> listCompressFilePath) throws IOException {

        if (!Files.exists(pathZipFile.getParent())) {
            Files.createDirectories(pathZipFile.getParent());
        }

        // 圧縮先ファイルの出力ストリームを作成
        try (FileOutputStream fileOutputStream = new FileOutputStream(pathZipFile.toFile()); // NOPMD
                ZipOutputStream zipOutputStream = new ZipOutputStream(fileOutputStream)) {

            // 圧縮対象をループ
            for (Path filePath : listCompressFilePath) {

                zipOutputStream.putNextEntry(this.createEntry(filePath));
                if (this.copyByte(zipOutputStream, filePath)) {
                    zipOutputStream.closeEntry();
                }
            }
        }

        return Files.exists(pathZipFile);
    }

    // 仮にディレクトリが異なっていたとしてもすべてのファイルを直下に置く
    private ZipEntry createEntry(final Path filePath) {
        return new ZipEntry(filePath.getFileName().toString());
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
