package net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2024;

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
import net.seijishikin.jp.normalize.manage.kanrensha.entity.year.y2024.SaveFileStorage2024Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.year.y2024.SaveFileStorage2024Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * InsertSaveStorageY2024Logic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@ConfigurationProperties(prefix = "net.seijishikin.jp.normalize.kanrensha")
@Sql("InsertSaveStorageY2024LogicTest.sql")
class InsertSaveStorageY2024LogicTest {

    /** テスト対象 */
    @Autowired
    private InsertSaveStorageY2024Logic insertSaveStorageY2024Logic;

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
    private SaveFileStorage2024Repository saveFileStorage2024Repository;

    @Test
    @Tag("TableTruncate")
    @Transactional
    void test() throws Exception {

        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();

        String childDir = "138/20221205123456/219432-cmzlkf-uo";
        String fileName = "qwerty.txt";
        Path path = Paths.get(storageFolder, childDir, fileName);
        Short shoshoKbn = Short.valueOf("123");
        assertNotEquals(0, insertSaveStorageY2024Logic.practice(userDto, path, shoshoKbn));

        List<SaveFileStorage2024Entity> list = saveFileStorage2024Repository.findAll();
        assertEquals(1, list.size());
        SaveFileStorage2024Entity entity = list.get(0);

        assertEquals(childDir, entity.getChildDir());
        assertEquals(fileName, entity.getFileName());
        assertEquals(true, entity.getIsLatest());
        assertEquals("20221205123456", entity.getRegistTimeText());
        assertEquals(shoshoKbn, entity.getShoshoKbn());
    }

}
