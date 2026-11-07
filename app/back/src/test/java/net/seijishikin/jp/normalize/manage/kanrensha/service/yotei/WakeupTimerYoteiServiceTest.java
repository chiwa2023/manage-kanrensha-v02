package net.seijishikin.jp.normalize.manage.kanrensha.service.yotei;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.time.LocalDateTime;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.constants.YoteiTaskConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.TimerYoteiEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.GetAutoTaskExecuteUserUtil;

/**
 * WakeupTimerYoteiService単体テスト
 */
@SpringJUnitConfig
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("WakeupTimerYoteiServiceTest.sql")
class WakeupTimerYoteiServiceTest { //NOPMD TooManyMethods
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private WakeupTimerYoteiService wakeupTimerYoteiService;

    /** 自動処理ユーザ取得 */
    @Autowired
    private GetAutoTaskExecuteUserUtil getAutoTaskExecuteUserUtil;

    @AfterEach
    void tearDown() throws Exception {
        // sleepを入れる
        Thread.sleep(2000); // NOPMD DoNotUseThreads・・・テストのため
    }

    @Test
    @Tag("TableTruncate") // NOPMD
    void test105() throws Exception {

        LeastUserDto userDto = getAutoTaskExecuteUserUtil.practice();

        assertFalse(wakeupTimerYoteiService.practice(new TimerYoteiEntity(), userDto));

        assertTrue(wakeupTimerYoteiService.practice(this.createYotei(YoteiTaskConstants.DUMP_STD), userDto));
    }

    @Test
    @Tag("TableTruncate")
    void test101() throws Exception {

        LeastUserDto userDto = getAutoTaskExecuteUserUtil.practice();

        assertFalse(wakeupTimerYoteiService.practice(new TimerYoteiEntity(), userDto));

        assertTrue(wakeupTimerYoteiService.practice(this.createYotei(YoteiTaskConstants.DUMP_HISTORY), userDto));
    }

    @Test
    @Tag("TableTruncate")
    void test103() throws Exception {

        LeastUserDto userDto = getAutoTaskExecuteUserUtil.practice();

        assertFalse(wakeupTimerYoteiService.practice(new TimerYoteiEntity(), userDto));

        assertTrue(wakeupTimerYoteiService.practice(this.createYotei(YoteiTaskConstants.DUMP_MIN), userDto));
    }

    @Test
    @Tag("TableTruncate")
    void testSabun102() throws Exception {

        LeastUserDto userDto = getAutoTaskExecuteUserUtil.practice();

        assertTrue(wakeupTimerYoteiService.practice(this.createYotei(YoteiTaskConstants.DUMP_HISTORY_SABUN), userDto));
    }

    @Test
    @Tag("TableTruncate")
    void testSabun104() throws Exception {

        LeastUserDto userDto = getAutoTaskExecuteUserUtil.practice();

        assertTrue(wakeupTimerYoteiService.practice(this.createYotei(YoteiTaskConstants.DUMP_MIN_SABUN), userDto));
    }

    @Test
    @Tag("TableTruncate")
    void testSabun106() throws Exception {

        LeastUserDto userDto = getAutoTaskExecuteUserUtil.practice();

        assertTrue(wakeupTimerYoteiService.practice(this.createYotei(YoteiTaskConstants.DUMP_STD_SABUN), userDto));
    }

    @Test
    @Tag("TableTruncate")
    void testSabun107() throws Exception {

        LeastUserDto userDto = getAutoTaskExecuteUserUtil.practice();

        assertTrue(wakeupTimerYoteiService.practice(this.createYotei(YoteiTaskConstants.TEMP_FILE_DELETE), userDto));
    }

    @Test
    @Tag("TableTruncate")
    void testSabun108() throws Exception {

        LeastUserDto userDto = getAutoTaskExecuteUserUtil.practice();

        assertTrue(
                wakeupTimerYoteiService.practice(this.createYotei(YoteiTaskConstants.EXPIRED_TOKEN_DELETE), userDto));
    }

    @Test
    @Tag("TableTruncate")
    void testSabun109() throws Exception {

        LeastUserDto userDto = getAutoTaskExecuteUserUtil.practice();

        assertTrue(wakeupTimerYoteiService.practice(this.createYotei(YoteiTaskConstants.NOTIFY_TOKEN_LIMIT), userDto));
    }

    @Test
    @Tag("TableTruncate")
    void testSabun110() throws Exception {

        LeastUserDto userDto = getAutoTaskExecuteUserUtil.practice();

        assertTrue(wakeupTimerYoteiService.practice(this.createYotei(YoteiTaskConstants.DUMP_CODE_MOVE), userDto));
    }

    @Test
    @Tag("TableTruncate")
    void testNoteExecute() throws Exception {
        // 想定していない予定区分を入れたら何も実行しない
        LeastUserDto userDto = getAutoTaskExecuteUserUtil.practice();
        assertFalse(wakeupTimerYoteiService.practice(this.createYotei((short) 696), userDto));
    }

    private TimerYoteiEntity createYotei(final Short yoteiKbn) {

        TimerYoteiEntity entity = new TimerYoteiEntity();
        entity.setYoyakuTaskKbn(yoteiKbn);
        entity.setEndTimestamp(LocalDateTime.of(2025, 1, 1, 0, 0, 0));
        entity.setEndTimestamp(LocalDateTime.of(2024, 1, 1, 0, 0, 0));

        return entity;
    }

}
