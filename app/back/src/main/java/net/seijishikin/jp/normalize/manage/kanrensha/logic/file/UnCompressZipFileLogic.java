package net.seijishikin.jp.normalize.manage.kanrensha.logic.file;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Iterator;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * zip解凍Logic
 */
public class UnCompressZipFileLogic {

    /**
     * 処理を行う
     *
     * @param compressPath    zip保存フォルダ
     * @param unCommpressPath zip展開フォルダ
     * @return true
     * @throws IOException ファイル例外
     */
    public boolean practice(final String compressPath, final String unCommpressPath) throws IOException {

        File dir = new File(compressPath);

        File[] files = dir.listFiles();
        for (File file : files) {

            try (ZipFile zipFile = new ZipFile(file, StandardCharsets.UTF_8)) { // NOPMD
                Iterator<? extends ZipEntry> iterator = zipFile.entries().asIterator();
                Path pathUnCompress;
                while (iterator.hasNext()) {
                    ZipEntry zipEntry = iterator.next();

                    pathUnCompress = Paths.get(unCommpressPath, zipEntry.getName());

                    if (zipEntry.isDirectory()) {
                        // ディレクトリ書き込み
                        Files.createDirectories(pathUnCompress);
                    } else {

                        // 親ディレクトリが存在しない場合は作成
                        if (!Files.exists(pathUnCompress.getParent())) {
                            Files.createDirectories(pathUnCompress.getParent());
                        }

                        Files.write(pathUnCompress, zipFile.getInputStream(zipEntry).readAllBytes(),
                                StandardOpenOption.CREATE, StandardOpenOption.WRITE);
                    }
                }
            }

        }

        return true;
    }

}
