package net.seijishikin.jp.normalize.manage.kanrensha.service.year.undefined;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import net.seijishikin.jp.normalize.manage.kanrensha.service.year.SwitchYearLoginHistoryService;

/**
 * SwitchYearLoginHistoryService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class SwitchYearLoginHistoryUndefinedServiceTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private SwitchYearLoginHistoryService switchYearLoginHistoryService;

    @Test
    @Tag("TableTruncate")
    void test2019() throws Exception {

        final String email = "abc@example.com";
        final String ipAddress = "127.0.0.1"; // NOPMD テストデータにつき
        final String userAgent = "Netscape";
        final boolean isSuccess = false;
        LocalDateTime createDateTime = LocalDateTime.of(1001, 11, 13, 11, 32, 10);

        assertThrows(IllegalArgumentException.class,
                () -> switchYearLoginHistoryService.practice(email, ipAddress, userAgent, isSuccess, createDateTime));

    }

}
