package net.seijishikin.jp.normalize.manage.kanrensha.service.year.y2023;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.year.y2023.LoginHistory2023Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.year.y2023.LoginHistory2023Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.service.year.SwitchYearLoginHistoryService;

/**
 * SwitchYearLoginHistoryService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
// @Transactional
@Sql("SwitchYearLoginHistoryY2023ServiceTest.sql")
class SwitchYearLoginHistoryY2023ServiceTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private SwitchYearLoginHistoryService switchYearLoginHistoryService;

    /** ログイン履歴Respository(2023) */
    @Autowired
    private LoginHistory2023Repository loginHistory2023Repository;

    @Test
    @Tag("TableTruncate")
    void test2023() throws Exception {

        final String email = "abc@example.com";
        final String ipAddress = "127.0.0.1"; // NOPMD テストデータにつき
        final String userAgent = "Netscape";
        final boolean isSuccess = false;

        LocalDateTime createDateTime = LocalDateTime.of(2023, 11, 13, 11, 32, 10);

        Integer newId = switchYearLoginHistoryService.practice(email, ipAddress, userAgent, isSuccess, createDateTime);

        LoginHistory2023Entity entity0 = loginHistory2023Repository.findById(newId).get();

        assertEquals(email, entity0.getEmail());
        assertEquals(ipAddress, entity0.getIpAddress());
        assertEquals(userAgent, entity0.getUserAgent());
        assertEquals(createDateTime, entity0.getAttemptTime());
        assertEquals(isSuccess, entity0.getIsSuccess());
    }

}
