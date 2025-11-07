package net.seijishikin.jp.normalize.manage.kanrensha.logic.file;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import net.seijishikin.jp.normalize.manage.kanrensha.dto.storage_file.StorageFileDto;


/**
 * GetTempFilePathLogic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class GetTempFilePathLogicTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private GetTempFilePathLogic getTempFilePathLogic;

    @Test
    @Tag("TableTruncate")
    void test() {

        final String fileName = "abcde.csv";
        StorageFileDto storageFileDto = getTempFilePathLogic.practice(7, fileName);

        assertTrue(storageFileDto.getFileName().endsWith("_" + fileName));
        assertEquals("temp/07", storageFileDto.getSavedDir());
    }

}
