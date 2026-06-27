package net.seijishikin.jp.normalize.manage.kanrensha.service.yotei;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

/**
 * TimerYoteiExecuteService単体テスト(実行用)
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class TimerYoteiExecuteServiceWakeupZeroTest {

    /** テスト対象 */
    @Autowired
    private TimerYoteiExecuteService timerYoteiExecuteService;

    @Test
    @Tag("TableTruncate")
    @Sql("TimerYoteiExecuteServiceTestNoData.sql")
    void testNoData() {
        // 実行する予定がないときはなにも更新しない
        assertEquals(0, timerYoteiExecuteService.practice());
    }

    @Test
    @Tag("TableTruncate")
    @Sql("TimerYoteiExecuteServiceTestNotWakeup.sql")
    void testNotWakeup() {
        // データは取得できても起動するタスクがないときはなにも更新しない
        assertEquals(0, timerYoteiExecuteService.practice());
    }
}
