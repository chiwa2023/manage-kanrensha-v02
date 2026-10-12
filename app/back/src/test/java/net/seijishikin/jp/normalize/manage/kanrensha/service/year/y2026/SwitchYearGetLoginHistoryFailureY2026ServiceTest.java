package net.seijishikin.jp.normalize.manage.kanrensha.service.year.y2026;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import net.seijishikin.jp.normalize.manage.kanrensha.service.year.SwitchYearGetLoginHistoryFailureService;

/**
 * SwitchYearGetLoginHistoryFailureService単体テスト
 */
@SpringJUnitConfig
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("SwitchYearGetLoginHistoryFailureY2026ServiceTest.sql")
class SwitchYearGetLoginHistoryFailureY2026ServiceTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private SwitchYearGetLoginHistoryFailureService switchYearGetLoginHistoryFailureService;

    @Test
    @Tag("TableTruncate")
    void test2026() {

        LocalDate nowDate = LocalDate.of(2026, 4, 6);
        // メアドが該当しない
        assertTrue(switchYearGetLoginHistoryFailureService.practice("wrong@example.com", nowDate));

        // 取得件数が3件なのでtrue(2026年Logicだけを起動している)
        assertTrue(switchYearGetLoginHistoryFailureService.practice("aaa@politician.balanse.report.net", nowDate));
    }

}
