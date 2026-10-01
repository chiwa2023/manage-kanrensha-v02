package net.seijishikin.jp.normalize.manage.kanrensha.service.year.undefined;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.manage.kanrensha.service.year.SwitchYearTaskSuccessService;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * SwitchYearTaskSuccessService単体テスト
 */
@SpringJUnitConfig
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
class SwitchYearTaskSuccessUndefinedServiceTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private SwitchYearTaskSuccessService switchYearTaskSuccessService;

    @Test
    @Tag("TableTruncate")
    void test2019() throws Exception {

        final Integer year = 1001;
        final Integer taskId = 453;
        LocalDateTime endDateTime = LocalDateTime.of(1001, 12, 5, 12, 34, 56);

        assertThrows(IllegalArgumentException.class, () -> switchYearTaskSuccessService.practice(year,
                CreateLeastUserForTestUtil.practice(), taskId, endDateTime));

    }

}
