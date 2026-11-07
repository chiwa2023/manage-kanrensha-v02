package net.seijishikin.jp.normalize.manage.kanrensha.batch.reflesh.schema_table;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

/**
 * CreateTableOnlyNothingTasklet単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class CreateTableOnlyNothingTaskletTest {

    /** テスト対象 */
    @Autowired
    private CreateTableOnlyNothingTasklet createTableOnlyNothingTasklet;

    @Test
    void test() throws Exception {

        // とりあえず問題なく起動
        assertDoesNotThrow(() -> createTableOnlyNothingTasklet.execute(null, null));

        /*
         * MEMO : 
         * DBから任意のテーブルを削除→このテストを起動→削除されたテーブルが復活
         * DDLを一部修正→このテストを起動→DDLの修正内容が反映されない(CREATE TABLE IF NOT EXISTS)
         * をコードで書けば内容テストとなるが、本番環境にDDLを持ち込んでいないので今後未使用の可能性大
         */
    }

}
