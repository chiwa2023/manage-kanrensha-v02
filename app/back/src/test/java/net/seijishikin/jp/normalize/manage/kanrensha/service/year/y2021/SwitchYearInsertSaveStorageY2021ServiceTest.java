package net.seijishikin.jp.normalize.manage.kanrensha.service.year.y2021;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.year.y2021.SaveFileStorage2021Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.year.y2021.SaveFileStorage2021Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.service.year.SwitchYearInsertSaveStorageService;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * SwitchYearInsertSaveStorageService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@ConfigurationProperties(prefix = "net.seijishikin.jp.normalize.kanrensha")
@Sql("SwitchYearInsertSaveStorageY2021ServiceTest.sql")
class SwitchYearInsertSaveStorageY2021ServiceTest {
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
    private SaveFileStorage2021Repository saveFileStorage2021Repository;

    @Test
    @Tag("TableTruncate")
    void test2021() throws Exception {
        

        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        String childDir = "138/20221205123456/219432-cmzlkf-uo";
        String fileName = "qwerty.txt";
        Path path = Paths.get(storageFolder, childDir, fileName);
        Short shoshoKbn = Short.valueOf("123");

        assertNotEquals(0, switchYearInsertSaveStorageService.practice(2021, userDto, path, shoshoKbn));

        List<SaveFileStorage2021Entity> list = saveFileStorage2021Repository.findAll();
        assertEquals(1, list.size());
        SaveFileStorage2021Entity entity = list.get(0);

        assertEquals(childDir, entity.getChildDir());
        assertEquals(fileName, entity.getFileName());
        assertEquals(true, entity.getIsLatest());
        assertEquals("20221205123456", entity.getRegistTimeText());
        assertEquals(shoshoKbn, entity.getShoshoKbn());
    }

}
