package net.seijishikin.jp.normalize.manage.kanrensha.service.regist_by_xml;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
import net.seijishikin.jp.normalize.manage.kanrensha.dto.add_xml.RegistDataByXmlCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.task.TaskPlanWithUseFileDto;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * AnalysisUploadXmlWktblCommonByXmlService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@ConfigurationProperties(prefix = "net.seijishikin.jp.normalize.kanrensha")
@Transactional
@Sql("AnalysisUploadXmlWktblCommonByXmlServiceTest.sql")
class AnalysisUploadXmlWktblCommonByXmlServiceTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private AnalysisUploadXmlWktblCommonByXmlService analysisUploadXmlWktblCommonByXmlService;

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
        final String fileName = "2022_ホリエモン新党_SYUUSI.xml";

        // 読み取りファイルを設定
        Path readFilePath = Paths.get("190/test/", fileName);
        Path readFilePathAbs = Paths.get(storageFolder, readFilePath.toString());

        // サンプルファイルが存在しないときは複写
        if (!Files.exists(readFilePathAbs)) {
            Path pathSrc = Paths.get(GetCurrentResourcePath.getBackTestResourcePath(), "/file", fileName);
            Files.copy(pathSrc, readFilePathAbs);
        }
        assertTrue(Files.exists(readFilePathAbs));

        RegistDataByXmlCapsuleDto capsuleDto = new RegistDataByXmlCapsuleDto();
        capsuleDto.setUserDto(CreateLeastUserForTestUtil.practice());
        // capsuleDtoのstarge情報はすでにControllerで利用済であるのでServiceで使用しない
        // capsuleDto.getStorageFileDto().setSavedDir(readFilePath.toString());
        // capsuleDto.getStorageFileDto().setFileName(fileName);

        Integer year = 2026;
        TaskPlanWithUseFileDto planFileDto = new TaskPlanWithUseFileDto();
        planFileDto.setReadFile(readFilePathAbs);
        planFileDto.setTaskPlanId(453);
        planFileDto.setTaskPlanCode(187);

        // 非同期処理なのでとりあえず例外にならないのを目視
        assertDoesNotThrow(() -> analysisUploadXmlWktblCommonByXmlService.practice(year, capsuleDto, planFileDto));

    }

}
