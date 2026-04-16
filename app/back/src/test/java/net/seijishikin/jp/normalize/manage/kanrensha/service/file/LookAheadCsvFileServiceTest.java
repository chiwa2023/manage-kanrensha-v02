package net.seijishikin.jp.normalize.manage.kanrensha.service.file;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import net.seijishikin.jp.normalize.manage.kanrensha.constants.GetCurrentResourcePath;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.storage_file.LookAheadCsvResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.storage_file.StorageFileDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.storage_file.UploadFileDto;

/**
 * LookAheadCsvFileService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class LookAheadCsvFileServiceTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private LookAheadCsvFileService lookAheadCsvFileService;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        final String fileName = "mt_city_all.csv";
        UploadFileDto uploadFileDto = new UploadFileDto();
        uploadFileDto.setFileName(fileName);
        uploadFileDto.setCharset("UTF-8");

        // ファイルをバイナリで呼び出し
        Path path = Paths.get(GetCurrentResourcePath.getBackTestResourcePath(), "/file", fileName);
        byte[] bytes = Files.readAllBytes(path);
        uploadFileDto.setFileContent(Base64.getEncoder().encodeToString(bytes));

        LookAheadCsvResultDto resultDto = lookAheadCsvFileService.practice(1, uploadFileDto);

        StorageFileDto storageFileDto = resultDto.getStorageFileDto();
        assertEquals("temp/01", storageFileDto.getSavedDir());
        assertTrue(storageFileDto.getFileName().endsWith("_" + fileName));

        List<List<String>> listData = resultDto.getTableData();
        assertEquals(11, listData.size());

        List<String> line0 = listData.get(0);

        assertEquals("lg_code", line0.get(0));
        assertEquals("pref", line0.get(1));
        assertEquals("pref_kana", line0.get(2));
        assertEquals("pref_roma", line0.get(3));
        assertEquals("county", line0.get(4));
        assertEquals("county_kana", line0.get(5));
        assertEquals("county_roma", line0.get(6));
        assertEquals("city", line0.get(7));
        assertEquals("city_kana", line0.get(8));
        assertEquals("city_roma", line0.get(9));
        assertEquals("ward", line0.get(10));
        assertEquals("ward_kana", line0.get(11));
        assertEquals("ward_roma", line0.get(12));
        assertEquals("efct_date", line0.get(13));
        assertEquals("ablt_date", line0.get(14));
        assertEquals("remarks", line0.get(15));

        List<String> line1 = listData.get(1);

        assertEquals("011002", line1.get(0));
        assertEquals("北海道", line1.get(1));
        assertEquals("ホッカイドウ", line1.get(2));
        assertEquals("Hokkaido", line1.get(3));
        assertEquals("", line1.get(4));
        assertEquals("", line1.get(5));
        assertEquals("", line1.get(6));
        assertEquals("札幌市", line1.get(7));
        assertEquals("サッポロシ", line1.get(8));
        assertEquals("Sapporo-shi", line1.get(9));
        assertEquals("", line1.get(10));
        assertEquals("", line1.get(11));
        assertEquals("", line1.get(12));
        assertEquals("1947-04-17", line1.get(13));
        assertEquals("", line1.get(14));
        assertEquals("", line1.get(15));
    }

}
