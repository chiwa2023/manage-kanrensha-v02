package net.seijishikin.jp.normalize.manage.kanrensha.service.year;

import static org.junit.jupiter.api.Assertions.assertFalse;

import java.time.LocalDate;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

/**
 * SwitchYearGetLoginHistoryFailureService単体テスト(年またぎ)
 */
@SpringJUnitConfig
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("SwitchYearGetLoginHistoryFailureServiceTest.sql")
class SwitchYearGetLoginHistoryFailureServiceTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private SwitchYearGetLoginHistoryFailureService switchYearGetLoginHistoryFailureService;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        // 年またぎテスト
        LocalDate nowDate = LocalDate.of(2026, 1, 1);

        // 取得件数が6件なのでfalse(2026-2025年)
        assertFalse(switchYearGetLoginHistoryFailureService.practice("aaa@politician.balanse.report.net", nowDate));
    }
}
