package net.seijishikin.jp.normalize.manage.kanrensha.service.file;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
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

import jakarta.transaction.Transactional;
import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.constants.FileTypeConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.constants.GetCurrentResourcePath;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.storage_file.StorageFileDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.year.y2026.SaveFileStorage2026Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.year.y2026.SaveFileStorage2026Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * ModifyTempToStorageFileService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@ConfigurationProperties(prefix = "net.seijishikin.jp.normalize.kanrensha")
@Sql("ModifyTempToStorageFileServiceTest.sql")
class ModifyTempToStorageFileServiceTest {

    /** テスト対象 */
    @Autowired
    private ModifyTempToStorageFileService modifyTempToStorageFileService;

    /** ファイル記録Service(2026) */
    @Autowired
    private SaveFileStorage2026Repository saveFileStorage2026Repository;

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
    @Transactional
    void testUtf() throws Exception {

        final Integer year = 2026;
        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();

        final String finleName = "utf_del_2603-05.csv";
        StorageFileDto fileDto = this.createStorageFile(finleName);

        String path = modifyTempToStorageFileService.practice(year, userDto, fileDto, FileTypeConstants.FILE_TYPE);

        // ファイルが複写先に存在すること
        assertTrue(Files.exists(Paths.get(path)));

        List<SaveFileStorage2026Entity> listAns = saveFileStorage2026Repository.findAll();

        assertEquals(1, listAns.size());

        SaveFileStorage2026Entity entityAns = listAns.get(0);

        assertEquals(userDto.getUserPersonCode(), entityAns.getInsertUserCode());
        assertEquals(true, entityAns.getIsLatest());
        assertEquals(finleName, entityAns.getFileName());
        assertTrue(entityAns.getChildDir().contains("190"));
        assertEquals(FileTypeConstants.FILE_TYPE, entityAns.getShoshoKbn());
    }

    private StorageFileDto createStorageFile(final String fileName) throws IOException {

        Path readFilePath = Paths.get("190/test/", fileName);

        Path readFilePathAbs = Paths.get(storageFolder, readFilePath.toString());

        // 中身を削除
        if (Files.exists(readFilePathAbs.getParent())) {
            Files.walk(readFilePathAbs.getParent()).sorted(Comparator.reverseOrder()).map(Path::toFile)
                    .forEach(java.io.File::delete);
        }

        Files.createDirectories(readFilePathAbs.getParent());

        // サンプルファイルが存在しないときは複写
        if (!Files.exists(readFilePathAbs)) {
            Path pathSrc = Paths.get(GetCurrentResourcePath.getBackTestResourcePath(), "/file/batch/postalcode",
                    fileName);
            Files.copy(pathSrc, readFilePathAbs);
        }
        assertTrue(Files.exists(readFilePathAbs));

        StorageFileDto fileDto = new StorageFileDto();
        fileDto.setSavedDir("190/test/");
        fileDto.setFileName(fileName);

        return fileDto;
    }

}
