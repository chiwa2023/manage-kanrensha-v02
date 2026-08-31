package net.seijishikin.jp.normalize.manage.kanrensha.service.postal;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.TreeMap;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.constants.GetCurrentResourcePath;
import net.seijishikin.jp.normalize.manage.kanrensha.constants.TaskInfoConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.storage_file.StorageFileDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.task.InsertTaskPlanResultDto;
import net.seijishikin.jp.normalize.manage.kanrensha.service.year.SwitchYearInsertTaskPlanService;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * PrepareSabunWktblPostalCodeService単体テスト
 */
@SpringJUnitConfig
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("PrepareSabunWktblPostalCodeServiceTest.sql")
@ConfigurationProperties(prefix = "net.seijishikin.jp.normalize.kanrensha")
class PrepareSabunWktblPostalCodeServiceTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private PrepareSabunWktblPostalCodeService prepareSabunWktblPostalCodeService;

    /** 郵便番号差分準備非同期Service */
    @Autowired
    private SwitchYearInsertTaskPlanService switchYearInsertTaskPlanService;

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

        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();

        Map<String, String> mapParam = new TreeMap<>();
        LocalDateTime dateTimeStart = LocalDateTime.of(2026, 4, 13, 22, 33, 44);

        InsertTaskPlanResultDto taskPlanDto = switchYearInsertTaskPlanService.practice(null, userDto, dateTimeStart,
                TaskInfoConstants.SAVE_POSTAL_REPAIR_CSV, mapParam);

        assertDoesNotThrow(() -> prepareSabunWktblPostalCodeService.practice(userDto, taskPlanDto,
                this.createStorageFile("utf_add_2603-05.csv"), this.createStorageFile("utf_del_2603-05.csv")));
    }

    private StorageFileDto createStorageFile(final String fileName) throws IOException {

        Path readFilePath = Paths.get("190/test/", fileName);

        Path readFilePathAbs = Paths.get(storageFolder, readFilePath.toString());

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
