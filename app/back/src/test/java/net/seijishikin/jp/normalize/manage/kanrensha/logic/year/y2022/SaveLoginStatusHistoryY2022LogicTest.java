package net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2022;

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

import net.seijishikin.jp.normalize.manage.kanrensha.entity.LoginHistoryBaseEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.year.y2022.LoginHistory2022Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.year.y2022.LoginHistory2022Repository;

/**
 * SaveLoginStatusHistoryY2022Logic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
// @Transactional
@Sql("SaveLoginStatusHistoryY2022LogicTest.sql")
class SaveLoginStatusHistoryY2022LogicTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private SaveLoginStatusHistoryY2022Logic saveLoginStatusHistoryY2022Logic;

    /** ログイン履歴Respository(2022) */
    @Autowired
    private LoginHistory2022Repository loginHistory2022Repository;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        LoginHistoryBaseEntity baseEntity0 = new LoginHistoryBaseEntity();
        baseEntity0.setEmail("abc@example.com");
        baseEntity0.setUserAgent("Netscape");
        baseEntity0.setIpAddress("127.0.0.1"); // NOPMD テストデータにつき
        baseEntity0.setIsSuccess(false);
        baseEntity0.setAttemptTime(LocalDateTime.of(2022, 12, 5, 12, 34, 56));

        Integer newId = saveLoginStatusHistoryY2022Logic.practice(baseEntity0);

        LoginHistory2022Entity entity0 = loginHistory2022Repository.findById(newId).get();
        assertEquals(baseEntity0.getEmail(), entity0.getEmail());
        assertEquals(baseEntity0.getUserAgent(), entity0.getUserAgent());
        assertEquals(baseEntity0.getIpAddress(), entity0.getIpAddress());
        assertEquals(baseEntity0.getAttemptTime(), entity0.getAttemptTime());
        assertEquals(baseEntity0.getIsSuccess(), entity0.getIsSuccess());
    }

}
