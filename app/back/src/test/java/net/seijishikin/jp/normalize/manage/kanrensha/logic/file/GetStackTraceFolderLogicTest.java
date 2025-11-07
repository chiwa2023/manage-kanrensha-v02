package net.seijishikin.jp.normalize.manage.kanrensha.logic.file;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

/**
 * GetStackTraceFolderLogic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class GetStackTraceFolderLogicTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private GetStackTraceFolderLogic getStackTraceFolderLogic;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        // タスク計画コードも日付も指定されない場合は例外
        assertThrows(IllegalArgumentException.class, () -> getStackTraceFolderLogic.practice(null, null, null));

        // タスク計画コードがあっても発生年の指定がない場合は例外
        assertThrows(IllegalArgumentException.class, () -> getStackTraceFolderLogic.practice(null, 76, null));

        LocalDateTime datetime = LocalDateTime.of(2022, 12, 5, 12, 34, 56);

        Integer taskYear = 2025;

        // タスク計画コードを指定しないときは日付を含む定義したフォルダ
        String path1 = getStackTraceFolderLogic.practice(null, null, datetime).toString();
        DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("yyyyMMdd");
        assertTrue(path1.endsWith("stack_trace" + File.separator + datetime.format(formatterDate)));

        // タスク計画コードを指定するときはコードを含む定義したフォルダ
        Integer code = 149;
        String path2 = getStackTraceFolderLogic.practice(taskYear, code, null).toString();
        assertTrue(path2.endsWith(
                "stack_trace" + File.separator + "task_plan" + File.separator + taskYear + File.separator + code));

        // 両方指定するときはコード優先
        String path3 = getStackTraceFolderLogic.practice(taskYear, code, null).toString();
        assertTrue(path3.endsWith(
                "stack_trace" + File.separator + "task_plan" + File.separator + taskYear + File.separator + code));
    }

}
