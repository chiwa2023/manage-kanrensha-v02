package net.seijishikin.jp.normalize.manage.kanrensha.service.yotei;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.config.CronTask;
import org.springframework.scheduling.config.ScheduledTask;
import org.springframework.scheduling.config.ScheduledTaskHolder;
import org.springframework.scheduling.support.CronExpression;
import org.springframework.scheduling.support.SimpleTriggerContext;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import net.seijishikin.jp.normalize.manage.kanrensha.BackApplication;

/**
 * TimerYoteiExecuteService単体テスト
 */
@SpringJUnitConfig
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@SpringBatchTest
@ContextConfiguration(classes = { BackApplication.class, // 全体起動
        TimerYoteiExecuteService.class // テスト対象)
})
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class TimerYoteiExecuteServiceTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private TimerYoteiExecuteService timerYoteiExecuteService;

    /** spring boot タスク保持クラス */
    @Autowired
    private ScheduledTaskHolder scheduledTaskHolder;

    @Test
    @Tag("TableTruncate")
    void testTimer() throws Exception {

        // 実行時間のテスト
        assertEquals(1, scheduledTaskHolder.getScheduledTasks().size(), "登録されているタイムスケジュールは1件であること");
        ScheduledTask scheduledTask = scheduledTaskHolder.getScheduledTasks().iterator().next();
        assertNotNull(scheduledTask, "確実に代入されていること");

        if (scheduledTask.getTask() instanceof CronTask) {
            CronTask cronTask = (CronTask) scheduledTask.getTask(); // NOPMD

            // テストだと現在時間でなく過去(未来)日付で基準日付はOK
            LocalDateTime dateTime = LocalDateTime.of(2022, 2, 22, 12, 34, 18);

            // SpringBoot登録側
            Instant instant = dateTime.toInstant(ZoneOffset.UTC);
            Instant instantRegist = cronTask.getTrigger() // NOPMD
                    .nextExecution(new SimpleTriggerContext(instant, instant, instant));

            // コードから力ずくで取得して予測時間を計算
            String expression = timerYoteiExecuteService.getClass().getMethod("practice").getAnnotation(Scheduled.class)
                    .cron();
            Instant instantExpect = CronExpression.parse(expression).next(dateTime).toInstant(ZoneOffset.UTC);

            // 記載したコードの通りbatch側スケジュールに登録されている
            assertEquals(instantExpect, instantRegist, "ソースからのスケジュール値が実行環境に違いなく登録されている");

            // 起動したい時間通りに計算されている
            OffsetDateTime offsetDateTime = instantRegist.atOffset(ZoneOffset.UTC); // NOPMD

            assertEquals(2022, offsetDateTime.getYear(), "起動時間の確認(年)");
            assertEquals(2, offsetDateTime.getMonthValue(), "起動時間の確認(月)");
            assertEquals(22, offsetDateTime.getDayOfMonth(), "起動時間の確認(日)");
            assertEquals(13, offsetDateTime.getHour(), "起動時間の確認(時間)");
            assertEquals(0, offsetDateTime.getMinute(), "起動時間の確認(分)");
            assertEquals(0, offsetDateTime.getSecond(), "起動時間の確認(秒)");
        }
    }
}
