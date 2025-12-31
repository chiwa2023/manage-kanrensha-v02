package net.seijishikin.jp.normalize.manage.kanrensha.service.file;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

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

import net.seijishikin.jp.normalize.manage.kanrensha.constants.GetCurrentResourcePath;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.storage_file.StorageFileDto;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.year.y2025.SaveFileStorage2025Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.year.y2025.TaskPlan2025Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * CopyTempToUseSavedFileService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("CopyTempToUseSavedFileServiceTest.sql")
@ConfigurationProperties(prefix = "net.seijishikin.jp.normalize.kanrensha")
class CopyTempToUseSavedFileServiceTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private CopyTempToUseSavedFileService copyTempToUseSavedFileService;

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

    /** タスク計画Repository(2025) */
    @Autowired
    private TaskPlan2025Repository taskPlan2025Repository;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        String fileName = "mt_city_all.csv";

        Path pathSrc = Paths.get(GetCurrentResourcePath.getBackTestResourcePath(), "file", fileName);

        String copyFolder = "test";
        StorageFileDto storageFileDto = new StorageFileDto();
        storageFileDto.setSavedDir(copyFolder);
        storageFileDto.setFileName(fileName);

        Path pathCopy = Paths.get(storageFolder, copyFolder, fileName);

        Files.copy(pathSrc, pathCopy, StandardCopyOption.REPLACE_EXISTING);

        Path path = copyTempToUseSavedFileService.practice(2025, storageFileDto, CreateLeastUserForTestUtil.practice(),
                Short.valueOf("205"), 101);

        // コピー成功
        assertTrue(Files.exists(path));

        // 空の予定テーブル、空のタスクテーブルに何か行が追加されたことだけをチェック
        // 内容は各Serviceで確認する
        assertEquals(1L, saveFileStorage2025Repository.count());
        assertEquals(1L, taskPlan2025Repository.count());

    }

}
