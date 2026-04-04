package net.seijishikin.jp.normalize.manage.kanrensha.service.regist_by_xml;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

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
import net.seijishikin.jp.normalize.manage.kanrensha.dto.add_xml.RegistDataByXmlCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.storage_file.StorageFileDto;
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
        String fileName = "2022_ホリエモン新党_SYUUSI.xml";

        Path pathSrc = Paths.get(GetCurrentResourcePath.getBackTestResourcePath(), "/file", fileName);

        String copyFolder = "temp/test";
        StorageFileDto storageFileDto = new StorageFileDto();
        storageFileDto.setSavedDir(copyFolder);
        storageFileDto.setFileName(fileName);

        Path pathCopy = Paths.get(storageFolder, copyFolder, fileName);

        Files.copy(pathSrc, pathCopy, StandardCopyOption.REPLACE_EXISTING);

        RegistDataByXmlCapsuleDto capsuleDto = new RegistDataByXmlCapsuleDto();
        capsuleDto.setUserDto(CreateLeastUserForTestUtil.practice());
        capsuleDto.getStorageFileDto().setSavedDir(copyFolder);
        capsuleDto.getStorageFileDto().setFileName(fileName);

        // 非同期処理なのでとりあえず例外にならないのを目視
        assertDoesNotThrow(() -> analysisUploadXmlWktblCommonByXmlService.practice(capsuleDto));

    }

}
