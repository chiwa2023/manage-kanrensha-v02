package net.seijishikin.jp.normalize.manage.kanrensha.service.year;

import static org.junit.jupiter.api.Assertions.assertEquals;


import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.year.y2026.LoginHistory2026Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.year.y2026.LoginHistory2026Repository;

/**
 * SwitchYearLoginHistoryService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
// @Transactional
@Sql("SwitchYearLoginHistoryServiceTest.sql")
class SwitchYearLoginHistoryServiceTest {

    /** テスト対象 */
    @Autowired
    private SwitchYearLoginHistoryService switchYearLoginHistoryService;

    /** ログイン履歴Respository(2026) */
    @Autowired
    private LoginHistory2026Repository loginHistory2026Repository;

    @Test
    @Tag("TableTruncate")
    void test2026() throws Exception {
        // テスト試行日時が2026年限定

        final String email = "abc@example.com";
        final String ipAddress = "127.0.0.1"; // NOPMD テストデータにつき
        final String userAgent = "Netscape";
        final boolean isSuccess = false;

        Integer newId = switchYearLoginHistoryService.practice(email, ipAddress, userAgent, isSuccess);

        LoginHistory2026Entity entity0 = loginHistory2026Repository.findById(newId).get();

        assertEquals(email, entity0.getEmail());
        assertEquals(ipAddress, entity0.getIpAddress());
        assertEquals(userAgent, entity0.getUserAgent());
        // 試行日時は現在日時につきテスト不可
        assertEquals(isSuccess, entity0.getIsSuccess());
    }

}
