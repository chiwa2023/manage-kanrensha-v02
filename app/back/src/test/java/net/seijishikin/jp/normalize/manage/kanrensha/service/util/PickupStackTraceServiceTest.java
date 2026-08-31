package net.seijishikin.jp.normalize.manage.kanrensha.service.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import net.seijishikin.jp.normalize.manage.kanrensha.constants.GetCurrentResourcePath;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.storage_file.OneFileBlobResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.file.CompressZipPointedFileLogic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.file.CopyFolderWalkTreeAllLogic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.file.GetAbsolutePathLogic;
import net.seijishikin.jp.normalize.manage.kanrensha.logic.file.GetStackTraceFolderLogic;

/**
 * PickupStackTraceService単体テスト
 */
@SpringJUnitConfig
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class PickupStackTraceServiceTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private PickupStackTraceService pickupStackTraceService;

    /** StackTrace保存フォルダ取得Logic */
    @Autowired
    private GetStackTraceFolderLogic getStackTraceFolderLogic;

    /** ファイル置き場絶対パス取得Logic */
    @Autowired
    private GetAbsolutePathLogic getAbsolutePathLogic;

    /** フォルダ圧縮Logic */
    @Autowired
    private CompressZipPointedFileLogic compressZipPointedFileLogic;

    /** ディレクトリ構造複写Logic */
    @Autowired
    private CopyFolderWalkTreeAllLogic copyFolderWalkTreeAllLogic;

    /** zip拡張子 */
    private static final String EXPANDS = ".zip";

    @Test
    @Tag("TableTruncate")
    void testTaskCode() throws Exception {

        // テストデータを複写
        this.copyTestData();

        Path pathCompare = Paths.get(getAbsolutePathLogic.getStorageFolder(), "temp", "test_stack_zip_code.zip");
        Files.deleteIfExists(pathCompare);

        int year = 2025;
        int code = 329;

        OneFileBlobResultDto blobDto = pickupStackTraceService.practiceByTaskCode(year, code);

        Path pathFolder = getStackTraceFolderLogic.practice(year, code, null);
        final String HYPHEN = "-";
        int length = pathFolder.getNameCount();

        StringBuilder builder = new StringBuilder();
        builder.append(pathFolder.getName(length - 3)) // SUPPRESS CHECKSTYLE MagicNumber
                .append(HYPHEN).append(pathFolder.getName(length - 2)).append(HYPHEN)
                .append(pathFolder.getName(length - 1)).append(EXPANDS);

        List<Path> listFile = Files.list(pathFolder).toList();

        compressZipPointedFileLogic.practice(pathCompare, listFile);

        assertEquals(builder.toString(), blobDto.getFileName());
        assertEquals(new String(Base64.getEncoder().encode(Files.readAllBytes(pathCompare))),
                blobDto.getFileContentBase64());
    }

    //    @Test
    //    @Tag("TableTruncate")
    //    void testPointedDate() throws Exception {
    //
    //        // テストデータを複写
    //        this.copyTestData();
    //
    //        Path pathCompare = Paths.get(getAbsolutePathLogic.getStorageFolder(), "temp", "test_stack_zip_date.zip");
    //        Files.deleteIfExists(pathCompare);
    //
    //        LocalDate localDate = LocalDate.of(2022, 12, 5);
    //
    //        OneFileBlobDto blobDto = pickupStackTraceService.practiceByDate(localDate);
    //
    //        Path pathFolder = getStackTraceFolderLogic.practice(null, null, LocalDateTime.of(localDate, LocalTime.MIN));
    //        final String HYPHEN = "-";
    //        int length = pathFolder.getNameCount();
    //
    //        StringBuilder builder = new StringBuilder();
    //        builder.append(pathFolder.getName(length - 2)).append(HYPHEN).append(pathFolder.getName(length - 1))
    //                .append(EXPANDS);
    //
    //        List<Path> listFile = Files.list(pathFolder).toList();
    //
    //        compressZipPointedFileLogic.practice(pathCompare, listFile);
    //
    //        assertEquals(builder.toString(), blobDto.getFileName());
    //        assertEquals(new String(Base64.getUrlEncoder().encode(Files.readAllBytes(pathCompare))),
    //                blobDto.getFileContentBase64());
    //    }

    // @Test
    // @Tag("TableTruncate")
    // void testEmpty() throws Exception {
    //
    // // テストデータを複写
    // this.copyTestData();
    //
    // final String blank = "";

    // フォルダが存在しない
    // LocalDate localDate0 = LocalDate.of(2023, 12, 5);
    // OneFileBlobResultDto blobDto0 =
    // pickupStackTraceService.practiceByDate(localDate0);
    // assertEquals(blank, blobDto0.getFileName());
    // }

    private void copyTestData() throws IOException {
        // テストファイルを複写
        Path pathSrc = Paths.get(GetCurrentResourcePath.getBackTestResourcePath(), "file", "stack_trace");
        Path pathCopy = Paths.get(getAbsolutePathLogic.getStorageFolder(), "stack_trace");

        copyFolderWalkTreeAllLogic.practice(pathSrc, pathCopy);
    }
}
