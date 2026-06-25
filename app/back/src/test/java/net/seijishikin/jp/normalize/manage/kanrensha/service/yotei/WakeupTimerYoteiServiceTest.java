package net.seijishikin.jp.normalize.manage.kanrensha.service.yotei;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import net.seijishikin.jp.normalize.common_tool.dto.LeastUserDto;
import net.seijishikin.jp.normalize.manage.kanrensha.constants.YoteiTaskConstants;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.TimerYoteiEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * WakeupTimerYoteiService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("WakeupTimerYoteiServiceTest.sql")
class WakeupTimerYoteiServiceTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private WakeupTimerYoteiService wakeupTimerYoteiService;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();

        assertFalse(wakeupTimerYoteiService.practice(new TimerYoteiEntity(), userDto));

        /* 関連者ダンプ */
        assertTrue(wakeupTimerYoteiService.practice(this.createYotei(YoteiTaskConstants.DUMP_HISTORY), userDto));
        assertTrue(wakeupTimerYoteiService.practice(this.createYotei(YoteiTaskConstants.DUMP_MIN), userDto));
        assertTrue(wakeupTimerYoteiService.practice(this.createYotei(YoteiTaskConstants.DUMP_STD), userDto));
    }

    @Test
    @Tag("TableTruncate")
    void testSabun() throws Exception {

        LeastUserDto userDto = CreateLeastUserForTestUtil.practice();

        /* 関連者ダンプ(差分) */
        assertTrue(wakeupTimerYoteiService.practice(this.createYotei(YoteiTaskConstants.DUMP_HISTORY_SABUN), userDto));
        assertTrue(wakeupTimerYoteiService.practice(this.createYotei(YoteiTaskConstants.DUMP_MIN_SABUN), userDto));
        assertTrue(wakeupTimerYoteiService.practice(this.createYotei(YoteiTaskConstants.DUMP_STD_SABUN), userDto));

    }

    private TimerYoteiEntity createYotei(final Short yoteiKbn) {

        TimerYoteiEntity entity = new TimerYoteiEntity();
        entity.setYoyakuTaskKbn(yoteiKbn);
        entity.setEndTimestamp(LocalDateTime.of(2025, 1, 1, 0, 0, 0));
        entity.setEndTimestamp(LocalDateTime.of(2024, 1, 1, 0, 0, 0));

        return entity;
    }

}
