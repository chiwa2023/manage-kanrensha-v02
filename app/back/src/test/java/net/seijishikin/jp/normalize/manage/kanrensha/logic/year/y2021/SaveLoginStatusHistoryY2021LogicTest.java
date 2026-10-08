package net.seijishikin.jp.normalize.manage.kanrensha.logic.year.y2021;

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
import net.seijishikin.jp.normalize.manage.kanrensha.entity.year.y2021.LoginHistory2021Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.year.y2021.LoginHistory2021Repository;

/**
 * SaveLoginStatusHistoryY2021Logic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
// @Transactional
@Sql("SaveLoginStatusHistoryY2021LogicTest.sql")
class SaveLoginStatusHistoryY2021LogicTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private SaveLoginStatusHistoryY2021Logic saveLoginStatusHistoryY2021Logic;

    /** ログイン履歴Respository(2021) */
    @Autowired
    private LoginHistory2021Repository loginHistory2021Repository;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        LoginHistoryBaseEntity baseEntity0 = new LoginHistoryBaseEntity();
        baseEntity0.setEmail("abc@example.com");
        baseEntity0.setUserAgent("Netscape");
        baseEntity0.setIpAddress("127.0.0.1"); // NOPMD テストデータにつき
        baseEntity0.setIsSuccess(false);
        baseEntity0.setAttemptTime(LocalDateTime.of(2021, 12, 5, 12, 34, 56));

        Integer newId = saveLoginStatusHistoryY2021Logic.practice(baseEntity0);

        LoginHistory2021Entity entity0 = loginHistory2021Repository.findById(newId).get();
        assertEquals(baseEntity0.getEmail(), entity0.getEmail());
        assertEquals(baseEntity0.getUserAgent(), entity0.getUserAgent());
        assertEquals(baseEntity0.getIpAddress(), entity0.getIpAddress());
        assertEquals(baseEntity0.getAttemptTime(), entity0.getAttemptTime());
        assertEquals(baseEntity0.getIsSuccess(), entity0.getIsSuccess());
    }

}
