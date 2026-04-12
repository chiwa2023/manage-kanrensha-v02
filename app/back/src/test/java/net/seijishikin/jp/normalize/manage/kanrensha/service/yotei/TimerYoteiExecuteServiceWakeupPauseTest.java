package net.seijishikin.jp.normalize.manage.kanrensha.service.yotei;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;

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

import net.seijishikin.jp.normalize.manage.kanrensha.entity.TimerYoteiEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.TimerYoteiRepository;

/**
 * TimerYoteiExecuteService単体テスト(実行用)
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("TimerYoteiExecuteServiceTestWakeupPause.sql")
class TimerYoteiExecuteServiceWakeupPauseTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private TimerYoteiExecuteService timerYoteiExecuteService;

    /** 予約実行Repository */
    @Autowired
    private TimerYoteiRepository timerYoteiRepository;

    @Test
    @Tag("TableTruncate")
    void testWakeupPause()throws Exception {
        // 中断している
        Integer newId = timerYoteiExecuteService.practice();

        // 旧データは履歴にした
        TimerYoteiEntity entityHistory = timerYoteiRepository.findById(123).get();
        assertFalse(entityHistory.getIsLatest());

        TimerYoteiEntity entityLatest = timerYoteiRepository.findById(newId).get();
        assertTrue(entityLatest.getIsLatest());

        // 前回実行 = 今回実行(開始前時刻と終了後時刻の間)
        assertEquals(entityHistory.getEndTimestamp(), entityLatest.getSabunTimestamp());
        assertNull(entityLatest.getNextTimestamp()); // 次回抽出はされないが予定実行編集時には最新として呼び出せる
        assertNull(entityLatest.getEndTimestamp()); // 未決定
        assertNull(entityLatest.getYearPointed()); // 未決定
        assertNull(entityLatest.getMonthPointed()); // 未決定
        assertNull(entityLatest.getDayPointed()); // 未決定
        assertNull(entityLatest.getHourPointed()); // 未決定
    }
}
