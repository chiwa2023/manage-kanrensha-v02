package net.seijishikin.jp.normalize.manage.kanrensha.service.regist_bulk_master_std;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
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
 * ExecuteBatchMasterStdPersonService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@ConfigurationProperties(prefix = "net.seijishikin.jp.normalize.kanrensha")
@Sql("ExecuteBatchMasterStdPersonServiceTest.sql")
class ExecuteBatchMasterStdPersonServiceTest {
    //CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private ExecuteBatchMasterStdPersonService executeBatchMasterStdPersonService;

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

        final String fileName = "関連者個人標準.csv";

        Path readFilePath = Paths.get("190/test/", fileName);

        Path readFilePathAbs = Paths.get(storageFolder, readFilePath.toString());

        // サンプルファイルが存在しないときは複写
        if (!Files.exists(readFilePathAbs)) {
            Path pathSrc = Paths.get(GetCurrentResourcePath.getBackTestResourcePath(), "/file/batch/kanrensha",
                    fileName);
            Files.copy(pathSrc, readFilePathAbs);
        }
        assertTrue(Files.exists(readFilePathAbs));

        Integer year = 2026;
        TaskPlanWithUseFileDto planFileDto = new TaskPlanWithUseFileDto();
        planFileDto.setReadFile(readFilePathAbs);
        planFileDto.setTaskPlanId(453);
        planFileDto.setTaskPlanCode(187);

        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();
        assertDoesNotThrow(() -> executeBatchMasterStdPersonService.practice(year, userDto, planFileDto));
    }

}
