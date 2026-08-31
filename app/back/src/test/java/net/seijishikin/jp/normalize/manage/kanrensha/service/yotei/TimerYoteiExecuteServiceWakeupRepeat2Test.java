package net.seijishikin.jp.normalize.manage.kanrensha.service.yotei;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

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

import net.seijishikin.jp.normalize.manage.kanrensha.entity.TimerYoteiEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.TimerYoteiRepository;

/**
 * TimerYoteiExecuteService単体テスト(実行用)
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("TimerYoteiExecuteServiceWakeupRepeat2Test.sql")
class TimerYoteiExecuteServiceWakeupRepeat2Test {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private TimerYoteiExecuteService timerYoteiExecuteService;

    /** 予約実行Repository */
    @Autowired
    private TimerYoteiRepository timerYoteiRepository;

    @Test
    @Tag("TableTruncate")
    void testWakeupRepeat() throws Exception {

        // 次回予定を設定
        Integer newId = timerYoteiExecuteService.practice();

        // 旧データは履歴にした
        TimerYoteiEntity entityHistory = timerYoteiRepository.findById(123).get();
        assertFalse(entityHistory.getIsLatest());

        TimerYoteiEntity entityLatest = timerYoteiRepository.findById(newId).get();
        assertTrue(entityLatest.getIsLatest());

        // 次回指定時から次回時刻を算出する(起動条件に起動時間を見て、常に指定スケジュールに戻ろうとする)
        // 前回＝現試行時間は目視確認
        assertEquals(this.addPeriod(entityHistory.getSabunTimestamp(), entityHistory),
                entityLatest.getSabunTimestamp());
        assertEquals(this.addPeriod(entityHistory.getEndTimestamp(), entityHistory), entityLatest.getEndTimestamp());

        // 2026-10-28 01:00:00 から 2年7か月4日9時間後
        assertEquals(2029, entityLatest.getNextTimestamp().getYear());
        assertEquals(6, entityLatest.getNextTimestamp().getMonthValue());
        assertEquals(1, entityLatest.getNextTimestamp().getDayOfMonth());
        assertEquals(10, entityLatest.getNextTimestamp().getHour());
        assertEquals(2029, entityLatest.getYearPointed());
        assertEquals(6, entityLatest.getMonthPointed());
        assertEquals(1, entityLatest.getDayPointed());
        assertEquals(10, entityLatest.getHourPointed());
    }

    private LocalDateTime addPeriod(final LocalDateTime src, final TimerYoteiEntity entity) {

        return src.plusYears(entity.getYearPeriod()).plusMonths(entity.getMonthPeriod()) //
                .plusDays(entity.getDayPeriod()).plusHours(entity.getHourPeriod()).withMinute(0).withSecond(0);
    }

}
