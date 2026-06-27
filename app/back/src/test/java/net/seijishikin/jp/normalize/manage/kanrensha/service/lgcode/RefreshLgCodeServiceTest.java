package net.seijishikin.jp.normalize.manage.kanrensha.service.lgcode;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.manage.kanrensha.constants.FileTypeConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.constants.GetCurrentResourcePath;
import net.seijishikin.jp.normalize.manage.kanrensha.constants.TaskInfoConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.kanrensha.RegistDataByCsvFileCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.task.TaskPlanWithUseFileDto;
import net.seijishikin.jp.normalize.manage.kanrensha.service.file.CopyTempToUseSavedFileService;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * RefreshLgCodeService単体テスト
 */
@SpringJUnitConfig
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("RefreshLgCodeServiceTest.sql")
@ConfigurationProperties(prefix = "net.seijishikin.jp.normalize.kanrensha")
@Transactional
class RefreshLgCodeServiceTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private RefreshLgCodeService refreshLgCodeService;

    /** 年切り替えタスク計画挿入Servce */
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

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        final String fileName = "mt_city_all_sample.csv";
        final String dirName = "190/test/";

        Path readFilePath = Paths.get(dirName, fileName);

        Path readFilePathAbs = Paths.get(storageFolder, readFilePath.toString());

        // サンプルファイルが存在しないときは複写
        if (!Files.exists(readFilePathAbs)) {
            Path pathSrc = Paths.get(GetCurrentResourcePath.getBackTestResourcePath(), "/file/batch/address_base/",
                    fileName);
            Files.copy(pathSrc, readFilePathAbs);
        }
        assertTrue(Files.exists(readFilePathAbs));

        RegistDataByCsvFileCapsuleDto capsuleDto = new RegistDataByCsvFileCapsuleDto();
        capsuleDto.setUserDto(CreateLeastUserForTestUtil.practice());
        capsuleDto.getStorageFileDto().setSavedDir(dirName);
        capsuleDto.getStorageFileDto().setFileName(fileName);
        capsuleDto.setUserDto(CreateLeastUserForTestUtil.practice());

        Map<String, String> mapParam = new TreeMap<>();

        LocalDateTime dateTimeStart = LocalDateTime.of(2026, 3, 23, 12, 34, 56);

        TaskPlanWithUseFileDto planFileDto = copyTempToUseSavedFileService.practice(dateTimeStart,
                capsuleDto.getStorageFileDto(), capsuleDto.getUserDto(), FileTypeConstants.FILE_TYPE,
                TaskInfoConstants.SAVE_ALL_LGCODE_CSV, mapParam);

        // 非同期なのでとりあえず何も起きないだけをテスト
        assertDoesNotThrow(
                () -> refreshLgCodeService.practice(dateTimeStart.getYear(), capsuleDto.getUserDto(), planFileDto));
    }

}
