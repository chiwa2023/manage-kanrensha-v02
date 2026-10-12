package net.seijishikin.jp.normalize.manage.kanrensha.service.year.undefined;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import net.seijishikin.jp.normalize.manage.kanrensha.service.year.SwitchYearGetLoginHistoryFailureService;

/**
 * SwitchYearGetLoginHistoryFailureService単体テスト
 */
@SpringJUnitConfig
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class SwitchYearGetLoginHistoryFailureUndefinedServiceTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private SwitchYearGetLoginHistoryFailureService switchYearGetLoginHistoryFailureService;

    @Test
    @Tag("TableTruncate")
    void test() {

        // 年切り替え範囲外
        LocalDate nowDate = LocalDate.of(1001, 4, 6);

        assertThrows(IllegalArgumentException.class,
                () -> switchYearGetLoginHistoryFailureService.practice("wrong@example.com", nowDate));

    }

}
