package net.seijishikin.jp.normalize.manage.kanrensha.service.file;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

/**
 * DeleteStorageTempFileService単体テスト
 */
@SpringJUnitConfig
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class DeleteStorageTempFileServiceTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private DeleteStorageTempFileService deleteStorageTempFileService;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        // 非同期処理の結果は目視で確認
        // 9月の半年前3月を削除している
        assertDoesNotThrow(() -> deleteStorageTempFileService.practice(9));
    }
}
