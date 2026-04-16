package net.seijishikin.jp.normalize.manage.kanrensha.service.yotei;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

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
@Sql("TimerYoteiExecuteServiceTestWakeupRepeat1Test.sql")
class TimerYoteiExecuteServiceWakeupRepeat1Test {
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

        // 現在時刻から次回時刻を算出する(起動条件に起動時間を見ないパターン)
        final LocalDateTime pre = LocalDateTime.now();
        LocalDateTime nextTime = this.addPeriod(pre, entityHistory);

        assertEquals(this.addPeriod(entityHistory.getSabunTimestamp(), entityHistory),
                entityLatest.getSabunTimestamp());
        assertEquals(this.addPeriod(entityHistory.getEndTimestamp(), entityHistory), entityLatest.getEndTimestamp());
        assertEquals(nextTime.getYear(), entityLatest.getNextTimestamp().getYear());
        assertEquals(nextTime.getMonthValue(), entityLatest.getNextTimestamp().getMonthValue());
        assertEquals(nextTime.getDayOfMonth(), entityLatest.getNextTimestamp().getDayOfMonth());
        assertEquals(nextTime.getHour(), entityLatest.getNextTimestamp().getHour());
        assertEquals(nextTime.getYear(), entityLatest.getYearPointed());
        assertEquals(nextTime.getMonthValue(), entityLatest.getMonthPointed());
        assertEquals(nextTime.getDayOfMonth(), entityLatest.getDayPointed());
        assertEquals(nextTime.getHour(), entityLatest.getHourPointed());
    }

    private LocalDateTime addPeriod(final LocalDateTime src, final TimerYoteiEntity entity) {

        return src.plusYears(entity.getYearPeriod()).plusMonths(entity.getMonthPeriod()) //
                .plusDays(entity.getDayPeriod()).plusHours(entity.getHourPeriod()).withMinute(0).withSecond(0);
    }

}
