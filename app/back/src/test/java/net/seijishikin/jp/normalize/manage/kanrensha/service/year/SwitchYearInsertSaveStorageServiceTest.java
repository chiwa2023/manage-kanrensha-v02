package net.seijishikin.jp.normalize.manage.kanrensha.service.year;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.year.y2025.SaveFileStorage2025Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.year.y2025.SaveFileStorage2025Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * SwitchYearInsertSaveStorageService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("SwitchYearInsertSaveStorageServiceTest.sql")
@Transactional
@ConfigurationProperties(prefix = "net.seijishikin.jp.normalize.kanrensha")
class SwitchYearInsertSaveStorageServiceTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private SwitchYearInsertSaveStorageService switchYearInsertSaveStorageService;


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

    /** 書証保存Repository */
    @Autowired
    private SaveFileStorage2025Repository saveFileStorage2025Repository;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {
        

        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        String childDir = "138/20221205123456/219432-cmzlkf-uo";
        String fileName = "qwerty.txt";
        Path path = Paths.get(storageFolder, childDir, fileName);
        Short shoshoKbn = Short.valueOf("123");

        assertNotEquals(0, switchYearInsertSaveStorageService.practice(2025, userDto, path, shoshoKbn));

        List<SaveFileStorage2025Entity> list = saveFileStorage2025Repository.findAll();
        assertEquals(1, list.size());
        SaveFileStorage2025Entity entity = list.get(0);

        assertEquals(childDir, entity.getChildDir());
        assertEquals(fileName, entity.getFileName());
        assertEquals(true, entity.getIsLatest());
        assertEquals("20221205123456", entity.getRegistTimeText());
        assertEquals(shoshoKbn, entity.getShoshoKbn());
    }

}
