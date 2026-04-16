package net.seijishikin.jp.normalize.manage.kanrensha.service.file;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.fasterxml.jackson.databind.DatabindException;

import jakarta.transaction.Transactional;
import net.seijishikin.jp.normalize.manage.kanrensha.constants.GetCurrentResourcePath;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.storage_file.LookAheadPublishXmlResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.storage_file.StorageFileDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.storage_file.UploadFileDto;

/**
 * LookAheadPublishXmlService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class LookAheadPublishXmlServiceTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private LookAheadPublishXmlService lookAheadPublishXmlService;

    @Test
    @Tag("TableTruncate")
    @Transactional
    void testUtf() throws Exception {

        final String fileName = "2022_政治家女子48党_SYUUSI.xml";
        UploadFileDto uploadFileDto = new UploadFileDto();
        uploadFileDto.setFileName(fileName);
        // uploadFileDto.setCharset("UTF-8");

        // ファイルをバイナリで呼び出し
        Path path = Paths.get(GetCurrentResourcePath.getBackTestResourcePath(), "/file", fileName);
        byte[] bytes = Files.readAllBytes(path);
        uploadFileDto.setFileContent(Base64.getEncoder().encodeToString(bytes));

        LookAheadPublishXmlResultDto resultDto = lookAheadPublishXmlService.practice(2, uploadFileDto);

        StorageFileDto storageFileDto = resultDto.getStorageFileDto();
        assertEquals("temp/02", storageFileDto.getSavedDir());
        assertTrue(storageFileDto.getFileName().endsWith("_" + fileName));

        assertEquals("収支報告書作成ソフト〔収支報告書作成ソフト〕", resultDto.getApp());
        assertEquals("20191220", resultDto.getVersion());
        assertEquals("政治家女子４８党", resultDto.getDantaiName());
        assertEquals(2022, resultDto.getHoukokuNen());
    }

    @Test
    @Tag("TableTruncate")
    @Transactional
    void testSJIS() throws Exception {

        final String fileName = "2022_ホリエモン新党_SYUUSI.xml";
        UploadFileDto uploadFileDto = new UploadFileDto();
        uploadFileDto.setFileName(fileName);
        // uploadFileDto.setCharset("UTF-8");

        // ファイルをバイナリで呼び出し
        Path path = Paths.get(GetCurrentResourcePath.getBackTestResourcePath(), "/file", fileName);
        byte[] bytes = Files.readAllBytes(path);
        uploadFileDto.setFileContent(Base64.getEncoder().encodeToString(bytes));

        LookAheadPublishXmlResultDto resultDto = lookAheadPublishXmlService.practice(2, uploadFileDto);

        StorageFileDto storageFileDto = resultDto.getStorageFileDto();
        assertEquals("temp/02", storageFileDto.getSavedDir());
        assertTrue(storageFileDto.getFileName().endsWith("_" + fileName));

        assertEquals("収支報告書作成ソフト〔収支報告書作成ソフト〕", resultDto.getApp());
        assertEquals("20191220", resultDto.getVersion());
        assertEquals("ホリエモン新党", resultDto.getDantaiName());
        assertEquals(2022, resultDto.getHoukokuNen());
    }

    @Test
    @Tag("TableTruncate")
    @Transactional
    void testWrongXml() throws Exception {

        final String fileName = "2022_政治家女子48党_SITO.xml";
        UploadFileDto uploadFileDto = new UploadFileDto();
        uploadFileDto.setFileName(fileName);
        // uploadFileDto.setCharset("UTF-8");

        // ファイルをバイナリで呼び出し
        Path path = Paths.get(GetCurrentResourcePath.getBackTestResourcePath(), "/file", fileName);
        byte[] bytes = Files.readAllBytes(path);
        uploadFileDto.setFileContent(Base64.getEncoder().encodeToString(bytes));

        assertThrows(DatabindException.class, () -> lookAheadPublishXmlService.practice(2, uploadFileDto));
    }

}
