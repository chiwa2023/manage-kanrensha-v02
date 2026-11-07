package net.seijishikin.jp.normalize.manage.kanrensha.service.yotei.function;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

/**
 * YoteiFuncDeleteStorageTempFileService単体テスト
 */
@SpringJUnitConfig
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("YoteiFuncForceDumpHistorySabunServiceTest.sql")
class YoteiFuncDeleteStorageTempFileServiceTest {

    /** テスト対象 */
    @Autowired
    private YoteiFuncDeleteStorageTempFileService yoteiFuncDeleteStorageTempFileService;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        // MEMO 重要：実行時の半年前(10月→4月)のフォルダに必要に応じて削除想定のファイルを入れること

        assertTrue(yoteiFuncDeleteStorageTempFileService.practice());

        // MEMO 重要：実行時の半年前(10月→4月)のフォルダがクリアされていることを目視で確認すること
    }

}
