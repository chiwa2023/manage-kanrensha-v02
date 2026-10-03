package net.seijishikin.jp.normalize.manage.kanrensha.logic.db_prepare;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.nio.file.Files;
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
import net.seijishikin.jp.normalize.manage.kanrensha.service.db_prepare.DbCompareCapsuleDto;

/**
 * CompareDbTableLocalToAwsLogic単体テスト
 */
@SpringJUnitConfig
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class CompareDbTableLocalToAwsLogicTest {

    /** テスト対象 */
    @Autowired
    private CompareDbTableLocalToAwsLogic compareDbTableLocalToAwsLogic;

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
        // 開発時用チェック
        assertFalse(Files.exists(Paths.get(pathUpload.toString(), "address_city_delete.sql")));
        assertTrue(Files.exists(Paths.get(GetCurrentResourcePath.getProject(capsuleDto.getPathTableDdl()).toString(),
                "address_city_delete.sql")));

        // 運用時用に実際の結果がほしい場合
        // capsuleDto.setPathAwsTableList(Paths.get("C:/temp/db/aws_db.txt"));
        // capsuleDto.setPathLocalDbTableList(Paths.get("C:/temp/db/local_test_db.txt"));

        // 運用時用に実際の結果がほしい場合。とりあえず落ちない
        assertDoesNotThrow(() -> compareDbTableLocalToAwsLogic.practice(capsuleDto));

        // 以下開発時テスト
        // yyy_zzzがawsにはあるがローカルにはない。ログ吐き出しを目視で確認。

        // address_city_deleteがローカルにはあるがawsにはない。ログ吐き出しを目視で確認。

        // アップロード格納ファイルの中に複写されている
        assertTrue(Files.exists(Paths.get(pathUpload.toString(), "address_city_delete.sql")));
    }

}
