package net.seijishikin.jp.normalize.manage.kanrensha.controller.file;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

/**
 * DeleteStorageTempFileController単体テスト
 */
@SpringJUnitConfig
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class DeleteStorageTempFileControllerTest {

    /** テスト対象 */
    @Autowired
    private DeleteStorageTempFileController deleteStorageTempFileController;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        // 起動時例外がなければTrueが戻る
        assertTrue(deleteStorageTempFileController.practice());

        // MEMO 重要：実行時の半年前(10月→4月)のフォルダがクリアされていることを目視で確認すること
        // 削除ディレクトリが実行時基準で算出しているのはこのテストだけ
    }

}
