package net.seijishikin.jp.normalize.manage.kanrensha.logic.db_prepare;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import net.seijishikin.jp.normalize.manage.kanrensha.constants.GetCurrentResourcePath;
import net.seijishikin.jp.normalize.manage.kanrensha.service.db_prepare.DbCompareCapsuleDto;

/**
 * CreateTableDdlLogic単体テスト
 */
@SpringJUnitConfig
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class CreateTableDdlLogicTest {

    /** テスト対象 */
    @Autowired
    private CreateTableDdlLogic createTableDdlLogic;
    
    @Test
    @Tag("TableTruncate")
    void test() throws Exception {
        
        DbCompareCapsuleDto capsuleDto = new DbCompareCapsuleDto();
        capsuleDto.setPathTableDdl("config/database/DDL");
        final Path pathUpload = Paths.get("C:/temp/sql/tmp");
        capsuleDto.setPathUpload(pathUpload);

        // 開発時
        String testResource = GetCurrentResourcePath.getBackTestResourcePath();
        capsuleDto.setPathAwsTableList(Paths.get(testResource, "file/db_compare", "aws_db.txt"));
        capsuleDto.setPathLocalDbTableList(Paths.get(testResource, "file/db_compare", "local_test_db.txt"));

        createTableDdlLogic.practice(capsuleDto);
        
        List<String> listLocal = Files.readAllLines(capsuleDto.getPathLocalDbTableList());

        for (String tableName : listLocal) {
            if (!tableName.startsWith("address_rsdt")) {
                
                String sqlFile = tableName + ".sql";
                Path pathSql = Paths.get(GetCurrentResourcePath.getProject(capsuleDto.getPathTableDdl()).toString(),
                        sqlFile);

                // すべてのテーブルが存在する(実際のテストでは仮でconfigからDDLをいくつか消したが
                // このロジックを実行すると新たに作成されるのように使う)
                assertTrue(Files.exists(pathSql));
            }
        }        
    }

}
