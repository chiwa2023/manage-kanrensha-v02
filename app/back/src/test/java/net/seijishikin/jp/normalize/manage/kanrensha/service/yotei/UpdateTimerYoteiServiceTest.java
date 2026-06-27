package net.seijishikin.jp.normalize.manage.kanrensha.service.yotei;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import net.seijishikin.jp.normalize.manage.kanrensha.dto.yotei.EditTimerYoteiCapsuleDto;
import net.seijishikin.jp.normalize.manage.kanrensha.entity.TimerYoteiEntity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.TimerYoteiRepository;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * UpdateTimerYoteiService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("UpdateTimerYoteiServiceTest.sql")
class UpdateTimerYoteiServiceTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private UpdateTimerYoteiService updateTimerYoteiService;

    /** 予約実行Repository */
    @Autowired
    private TimerYoteiRepository timerYoteiRepository;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        // 引数が空の場合はNullPointer
        assertThrows(NullPointerException.class, () -> updateTimerYoteiService.practice(null));

        // 指定したIdが存在しない
        EditTimerYoteiCapsuleDto capsuleDto0 = new EditTimerYoteiCapsuleDto();
        assertThrows(EmptyResultDataAccessException.class, () -> updateTimerYoteiService.practice(capsuleDto0));

        EditTimerYoteiCapsuleDto capsuleDto1 = new EditTimerYoteiCapsuleDto();
        capsuleDto1.setUserDto(CreateLeastUserForTestUtil.practice());
        final Integer callId = 123;
        TimerYoteiEntity updateEntity = timerYoteiRepository.findById(callId).get();

        updateEntity.setYoyakuTaskKbn((short) 9292);
        updateEntity.setTimerYoteiName("中断タスク");
        updateEntity.setIsPause(false);
        updateEntity.setIsPeriod(false);
        updateEntity.setIsRepeat(false);
        final LocalDateTime time0 = LocalDateTime.of(1998, 1, 2, 3, 4, 5);
        updateEntity.setEndTimestamp(time0);
        final LocalDateTime time1 = LocalDateTime.of(1998, 2, 3, 4, 5, 6);
        updateEntity.setSabunTimestamp(time1);
        final LocalDateTime time2 = LocalDateTime.of(1998, 2, 3, 4, 5, 6);
        updateEntity.setPreviousTimestamp(time2);
        final LocalDateTime time3 = LocalDateTime.of(1998, 3, 4, 5, 6, 7);
        updateEntity.setNextTimestamp(time3);

        updateEntity.setYearPeriod(301);
        updateEntity.setMonthPeriod(302);
        updateEntity.setDayPeriod(303);
        updateEntity.setHourPeriod(304);
        updateEntity.setYearPointed(305);
        updateEntity.setMonthPointed(306);
        updateEntity.setDayPointed(307);
        updateEntity.setHourPointed(308);

        capsuleDto1.setTimerYoteiEntity(updateEntity);

        Integer updateId = updateTimerYoteiService.practice(capsuleDto1);
        TimerYoteiEntity entityAns = timerYoteiRepository.findById(updateId).get();

        assertEquals(updateEntity.getTimerYoteiName(), entityAns.getTimerYoteiName());
        assertEquals(updateEntity.getYoyakuTaskKbn(), entityAns.getYoyakuTaskKbn());
        assertEquals(updateEntity.getIsPause(), entityAns.getIsPause());
        assertEquals(updateEntity.getIsPeriod(), entityAns.getIsPeriod());
        assertEquals(updateEntity.getIsRepeat(), entityAns.getIsRepeat());
        assertEquals(updateEntity.getEndTimestamp(), entityAns.getEndTimestamp());
        assertEquals(updateEntity.getSabunTimestamp(), entityAns.getSabunTimestamp());
        assertEquals(updateEntity.getPreviousTimestamp(), entityAns.getPreviousTimestamp());
        assertEquals(updateEntity.getNextTimestamp(), entityAns.getNextTimestamp());

        assertEquals(updateEntity.getYearPeriod(), entityAns.getYearPeriod());
        assertEquals(updateEntity.getMonthPeriod(), entityAns.getMonthPeriod());
        assertEquals(updateEntity.getDayPeriod(), entityAns.getDayPeriod());
        assertEquals(updateEntity.getHourPeriod(), entityAns.getHourPeriod());
        assertEquals(updateEntity.getYearPointed(), entityAns.getYearPointed());
        assertEquals(updateEntity.getMonthPointed(), entityAns.getMonthPointed());
        assertEquals(updateEntity.getDayPointed(), entityAns.getDayPointed());
        assertEquals(updateEntity.getHourPointed(), entityAns.getHourPointed());
        assertTrue(entityAns.getIsLatest());

        TimerYoteiEntity entityPre = timerYoteiRepository.findById(callId).get();
        assertFalse(entityPre.getIsLatest());
    }

}
