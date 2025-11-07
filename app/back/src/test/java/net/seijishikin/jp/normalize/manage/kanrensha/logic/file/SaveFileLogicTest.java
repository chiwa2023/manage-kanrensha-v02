package net.seijishikin.jp.normalize.manage.kanrensha.logic.file;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
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
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.constants.GetCurrentResourcePath;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;


/**
 * SaveFileLogic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@ConfigurationProperties(prefix = "net.seijishikin.jp.normalize.kanrensha")
class SaveFileLogicTest {

    /** テスト対象 */
    @Autowired
    private SaveFileLogic saveFileLogic;
    
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
        String fileName = "mt_parcel_city011011.csv";
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        String userDir = String.valueOf(userDto.getUserPersonCode());
        Path pathParent = Paths.get(storageFolder,userDir );
        Files.createDirectories(pathParent);
        Path pathWrite = Paths.get(pathParent.toString(), fileName);
        File file = new File(pathWrite.toString());
        // これから保存する場所に、ファイルを削除しているのでファイルが存在しない
        if (file.exists()) {
            file.delete();
        }
        assertFalse(file.exists());

        // 読み取りファイルを取得
        Path path = Paths.get(GetCurrentResourcePath.getBackTestResourcePath(), "/file/", fileName);
        byte[] bytes = Files.readAllBytes(path);
        String content = Base64.getEncoder().encodeToString(bytes);
        
        assertTrue(saveFileLogic.practice(pathWrite , content));

        // 実施後にはファイルが存在
        assertTrue(file.exists());

        String readText = Files.readString(path);
        String writeText = Files.readString(pathWrite);

        assertEquals(readText, writeText);

    }

    @Test
    void testBase64() throws Exception {

        String fileName = "src.md";
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        String userDir = String.valueOf(userDto.getUserPersonCode());
        Path pathParent = Paths.get(storageFolder, userDir);
        Files.createDirectories(pathParent);
        Path pathWrite = Paths.get(pathParent.toString(), fileName);
        File file = new File(pathWrite.toString());
        // これから保存する場所に、ファイルを削除しているのでファイルが存在しない
        if (file.exists()) {
            file.delete();
        }
        assertFalse(file.exists());

        // 読み取りファイルを取得
        Path path = Paths.get(GetCurrentResourcePath.getBackTestResourcePath(), "/file/", "srcmd_base64.txt");
        String content = Files.readString(path);
        assertTrue(saveFileLogic.practice(pathWrite, content));

        // 実施後にはファイルが存在
        assertTrue(file.exists());
        List<String> readText = Files
                .readAllLines(Paths.get(GetCurrentResourcePath.getBackTestResourcePath(), "/file/", "src.md"));
        List<String> writeText = Files.readAllLines(pathWrite);

        for (int index = 0; index < writeText.size(); index++) {
            assertEquals(readText.get(index), writeText.get(index));
        }
    }

}
