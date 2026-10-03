package net.seijishikin.jp.normalize.manage.kanrensha.service.db_prepare;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import net.seijishikin.jp.normalize.manage.kanrensha.constants.GetCurrentResourcePath;

/**
 * DbCompareService単体テスト
 */
@SpringJUnitConfig
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class DbCompareServiceTest {

    /** テスト対象 */
    @Autowired
    private DbCompareService dbCompareService;

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

        // 運用時用に実際の結果がほしい場合
        // capsuleDto.setPathAwsTableList(Paths.get("C:/temp/db/aws_db.txt"));
        // capsuleDto.setPathLocalDbTableList(Paths.get("C:/temp/db/local_test_db.txt"));

        // とりあえず落ちない
        // 結果は目視で確認(各所gitのファイル削除等をしないと確認できないので)
        assertDoesNotThrow(() -> dbCompareService.practice(capsuleDto));
    }
}
