package net.seijishikin.jp.normalize.manage.kanrensha.service.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import net.seijishikin.jp.normalize.manage.kanrensha.logic.file.GetStackTraceFolderLogic;

/**
 * SaveStackTraceService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class SaveStackTraceServiceTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private SaveStackTraceService saveStackTraceService;

    /** StackTrace保存フォルダ取得Logic */
    @Autowired
    private GetStackTraceFolderLogic getStackTraceFolderLogic;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        Integer taskCode = 329;
        Integer taskYear = 2025;
        // 例外がnullの場合は例外を投げる。実装段階での把握を早くする
        assertThrows(IllegalArgumentException.class, () -> saveStackTraceService.practice(null, taskYear, taskCode));

        try {
            List<Integer> list = new ArrayList<>();

            // 空リストから存在しない10番目の要素を呼んで強制IndexOutOfBoundsException発火
            list.get(10);

        } catch (IndexOutOfBoundsException exception) {

            Path path0 = saveStackTraceService.practice(exception, taskYear, taskCode);

            assertTrue(Files.exists(path0));
            // フォルダがロジックを使用して作成されていること
            assertNotEquals(-1,
                    path0.toString().indexOf(getStackTraceFolderLogic.practice(taskYear, taskCode, null).toString()));
            // ファイル名に例外クラス名が含まれていること
            assertNotEquals(-1, path0.toString().indexOf("IndexOutOfBoundsException"));

            // ファイルの中身は発生例外のtoStringで始まっていること
            List<String> list = Files.readAllLines(path0);
            assertEquals(exception.toString(), list.get(0));

            // MEMO このサービス内で例外発生時はログが流れるので目視で確認
        }
    }

}
