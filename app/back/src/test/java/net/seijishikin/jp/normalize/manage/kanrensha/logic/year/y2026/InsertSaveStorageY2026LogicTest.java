package net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2026;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
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
import net.seijishikin.jp.normalize.manage.kanrensha.entity.year.y2026.SaveFileStorage2026Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.year.y2026.SaveFileStorage2026Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * InsertSaveStorageY2026Logic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@ConfigurationProperties(prefix = "net.seijishikin.jp.normalize.kanrensha")
@Sql("InsertSaveStorageY2026LogicTest.sql")
class InsertSaveStorageY2026LogicTest {

    /** テスト対象 */
    @Autowired
    private InsertSaveStorageY2026Logic insertSaveStorageY2026Logic;

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
    private SaveFileStorage2026Repository saveFileStorage2026Repository;

    @Test
    @Tag("TableTruncate")
    @Transactional
    void test() throws Exception {

        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();

        String childDir = "138/20221205123456/219432-cmzlkf-uo";
        String fileName = "qwerty.txt";
        Path path = Paths.get(storageFolder, childDir, fileName);
        Short shoshoKbn = Short.valueOf("123");
        assertNotEquals(0, insertSaveStorageY2026Logic.practice(userDto, path, shoshoKbn));

        List<SaveFileStorage2026Entity> list = saveFileStorage2026Repository.findAll();
        assertEquals(1, list.size());
        SaveFileStorage2026Entity entity = list.get(0);

        assertEquals(childDir, entity.getChildDir());
        assertEquals(fileName, entity.getFileName());
        assertEquals(true, entity.getIsLatest());
        assertEquals("20221205123456", entity.getRegistTimeText());
        assertEquals(shoshoKbn, entity.getShoshoKbn());

        fail("Not yet implemented");
    }

}
