package net.seijishikin.jp.normalize.manage.kanrensha.logic.file;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import net.seijishikin.jp.normalize.manage.kanrensha.constants.GetCurrentResourcePath;

/**
 * CompressZipPointedFileLogic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@ConfigurationProperties(prefix = "net.seijishikin.jp.normalize.kanrensha")
class CompressZipPointedFileLogicTest {

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

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        // 格納ファイルを準備
        String srcRoot = GetCurrentResourcePath.getBackTestResourcePath();
        String srcFile = "file";
        
        Path pathRoot = Paths.get(storageFolder, "/190/test");
        String pathRootString = pathRoot.toString();
        
        final String fileName1 = "関連者企業団体最小登録.csv";
        Path pathSrc1 = Paths.get(srcRoot,srcFile,fileName1);
        Path pathFile1 = Paths.get(pathRootString, fileName1);
        Files.copy(pathSrc1, pathFile1,StandardCopyOption.REPLACE_EXISTING);

        final String fileName2 = "関連者企業団体標準.csv";
        Path pathSrc2 = Paths.get(srcRoot,srcFile,fileName2);
        Path pathFile2 = Paths.get(pathRootString, fileName2);
        Files.copy(pathSrc2, pathFile2,StandardCopyOption.REPLACE_EXISTING);

        final String fileName3 = "関連者企業団体履歴.csv";
        Path pathSrc3 = Paths.get(srcRoot,srcFile,fileName3);
        Path pathFile3 = Paths.get(pathRootString, fileName3);
        Files.copy(pathSrc3, pathFile3,StandardCopyOption.REPLACE_EXISTING);

        // 格納ファイルをリストアップ
        List<Path> listFilePath = new ArrayList<>();
        listFilePath.add(pathFile1);
        listFilePath.add(pathFile2);
        listFilePath.add(pathFile3);

        // 圧縮ファイル
        Path pathZipDir = Paths.get(storageFolder, "190/test/compress/make");
        Path pathZipFile = Paths.get(pathZipDir.toString(), "関連者企業団体.zip");

        // 中身を削除
        if (Files.exists(pathZipDir)) {
            Files.walk(pathZipDir).sorted(Comparator.reverseOrder()).map(Path::toFile).forEach(java.io.File::delete);
        }

        // 圧縮
        CompressZipPointedFileLogic compressZipPointedFileLogic = new CompressZipPointedFileLogic();
        assertTrue(compressZipPointedFileLogic.practice(pathZipFile, listFilePath));

        // 生成物を解凍
        List<Path> listFilePathNew = new ArrayList<>();
        try (ZipInputStream inputStream = new ZipInputStream(Files.newInputStream(pathZipFile))) {
            ZipEntry entity;
            String zipName = pathZipFile.getFileName().toString();
            Path root = Paths.get(pathZipFile.getParent().toString(),
                    zipName.substring(0, zipName.length() - ".zip".length()));
            if (!Files.exists(root)) {
                Files.createDirectories(root);
            }
            while ((entity = inputStream.getNextEntry()) != null) {
                Path dst = Paths.get(root.toString(), entity.getName());
                listFilePathNew.add(dst);
                Files.createDirectories(dst.getParent());
                Files.write(dst, inputStream.readAllBytes());
            }
        }

        // 解凍した中身同士を比較
        for (Path src : listFilePath) {
            String srcName = src.getFileName().toString();
            for (Path copy : listFilePath) {
                if (srcName.equals(copy.getFileName().toString())) {
                    assertTrue(Arrays.equals(Files.readAllBytes(src), Files.readAllBytes(copy)));
                }
            }
        }
    }

}
