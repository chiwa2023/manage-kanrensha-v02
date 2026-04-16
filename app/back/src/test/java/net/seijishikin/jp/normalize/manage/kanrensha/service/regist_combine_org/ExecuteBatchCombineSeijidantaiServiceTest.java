package net.seijishikin.jp.normalize.manage.kanrensha.service.regist_combine_org;

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

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.constants.GetCurrentResourcePath;
import net.seijishikin.jp.normalize.manage.kanrensha.dto.task.TaskPlanWithUseFileDto;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * ExecuteBatchCombineSeijidantaiService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("ExecuteBatchCombineSeijidantaiServiceTest.sql")
@ConfigurationProperties(prefix = "net.seijishikin.jp.normalize.kanrensha")
class ExecuteBatchCombineSeijidantaiServiceTest {
    //CHECKSTYLE:OFF MagicNumber
    
    /** テスト対象 */
    @Autowired
    private ExecuteBatchCombineSeijidantaiService executeBatchCombineSeijidantaiService;

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

        final String fileName = "sample_combine_poli_org.csv";

        // 読み取りファイルを設定
        Path readFilePath = Paths.get("190/test/", fileName);
        Path readFilePathAbs = Paths.get(storageFolder, readFilePath.toString());

        // サンプルファイルが存在しないときは複写
        if (!Files.exists(readFilePathAbs)) {
            Path pathSrc = Paths.get(GetCurrentResourcePath.getBackTestResourcePath(),
                    "/file/batch/kanrensha/combine_org", fileName);
            Files.copy(pathSrc, readFilePathAbs);
        }
        assertTrue(Files.exists(readFilePathAbs));

        Integer year = 2026;
        TaskPlanWithUseFileDto planFileDto = new TaskPlanWithUseFileDto();
        planFileDto.setReadFile(readFilePath);
        planFileDto.setTaskPlanId(453);
        planFileDto.setTaskPlanCode(187);

        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        assertDoesNotThrow(() -> executeBatchCombineSeijidantaiService.practice(year, userDto, planFileDto));
    }

}
