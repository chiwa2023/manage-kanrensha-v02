package net.seijishikin.jp.normalize.manage.kanrensha.service.year.y2022;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import net.seijishikin.jp.normalize.manage.kanrensha.entity.year.y2022.TaskPlan2022Entity;
import net.seijishikin.jp.normalize.manage.kanrensha.repository.year.y2022.TaskPlan2022Repository;
import net.seijishikin.jp.normalize.manage.kanrensha.service.year.SwitchYearTaskSuccessService;
import net.seijishikin.jp.normalize.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * SwitchYearTaskSuccessService単体テスト
 */
@SpringJUnitConfig
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("SwitchYearTaskSuccessY2022ServiceTest.sql")
class SwitchYearTaskSuccessY2022ServiceTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private SwitchYearTaskSuccessService switchYearTaskSuccessService;

    /** タスク計画Repository(2022) */
    @Autowired
    private TaskPlan2022Repository taskPlan2022Repository;

    @Test
    @Tag("TableTruncate")
    void test2022() throws Exception {

        final Integer year = 2022;
        final Integer taskId = 453;
        LocalDateTime endDateTime = LocalDateTime.of(2022, 12, 5, 12, 34, 56);
        Integer newId = switchYearTaskSuccessService.practice(year, CreateLeastUserForTestUtil.practice(), taskId,
                endDateTime);

        TaskPlan2022Entity oldEntity = taskPlan2022Repository.findById(taskId).get();
        assertFalse(oldEntity.getIsLatest()); // 履歴になった

        TaskPlan2022Entity newEntity = taskPlan2022Repository.findById(newId).get();
        assertTrue(newEntity.getIsLatest());
        assertTrue(newEntity.getIsFinished());
        assertFalse(newEntity.getIsSuspended());
        assertEquals(endDateTime, newEntity.getEndDateimte());
        assertEquals(LocalDateTime.of(1990, 7, 24, 23, 34, 56), newEntity.getStartDatetime()); // 元の開始日時が維持
    }

}
